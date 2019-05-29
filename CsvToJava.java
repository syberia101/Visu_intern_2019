/**
 * Created by c1972519 on 5/15/2019.
 */

import java.io.*;
import java.util.List;

public class CsvToJava{
    private String ipdest;
    private String port;
    private String ipsrc;

    public CsvToJava(String id, String p, String is){
        this.ipdest = id;
        this.port = p;
        this.ipsrc = is;
    }

    public String getIpdest(){
        return ipdest;
    }

    public String getPort(){
        return port;
    }

    public String getIpsrc(){
        return ipsrc;
    }

    //I didn't wrote the setters because I think we don't mind here
}
