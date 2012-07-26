package com.github.jmpjct.mysql.proto;

/*
 * A MySQL Column Packet
 */

import java.util.ArrayList;
import org.apache.log4j.Logger;

public class Column extends Packet {
    public Logger logger = Logger.getLogger("MySQL.Column");
    
    public String catalog = "def";
    public String schema = "";
    public String table = "";
    public String org_table = "";
    public String name = "";
    public String org_name = "";
    public long characterSet = 0;
    public long columnLength = 0;
    public long type = Flags.MYSQL_TYPE_VAR_STRING;
    public long flags = 0;
    public long decimals = 31;
    
    public Column(String name) {
        // Set this up by default. Allow overrides if needed
        this.characterSet = ResultSet_Text.characterSet;
        this.name = name;
    }
    
    public ArrayList<byte[]> getPayload() {
        this.logger.trace("getPayload");
        ArrayList<byte[]> payload = new ArrayList<byte[]>();
        
        payload.add(Proto.build_lenenc_str(this.catalog));
        payload.add(Proto.build_lenenc_str(this.schema));
        payload.add(Proto.build_lenenc_str(this.table));
        payload.add(Proto.build_lenenc_str(this.org_table));
        payload.add(Proto.build_lenenc_str(this.name));
        payload.add(Proto.build_lenenc_str(this.org_name));
        payload.add(Proto.build_filler(1));
        payload.add(Proto.build_fixed_int(2, this.characterSet));
        payload.add(Proto.build_fixed_int(4, this.columnLength));
        payload.add(Proto.build_fixed_int(1, this.type));
        payload.add(Proto.build_fixed_int(2, this.flags));
        payload.add(Proto.build_fixed_int(1, this.decimals));
        payload.add(Proto.build_filler(2));
        
        return payload;
    }
}
