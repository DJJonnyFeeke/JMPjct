package com.github.jmpjct.mysql.proto;

/*
 * A MySQL Command Packet
 */

import java.util.ArrayList;
import org.apache.log4j.Logger;

public class Com_Dropdb extends Packet {
    public Logger logger = Logger.getLogger("MySQL.Com.Dropdb");
    
    public String schema = "";
    
    public ArrayList<byte[]> getPayload() {
        this.logger.trace("getPayload");
        ArrayList<byte[]> payload = new ArrayList<byte[]>();
        
        payload.add(Proto.build_byte(Flags.COM_DROP_DB));
        payload.add(Proto.build_fixed_str(this.schema.length(), this.schema));
        
        return payload;
    }
}
