package goodJSON;

/**
 * Created by c1972519 on 5/23/2019.
 */
public class JavaObject {

    private String ipdest;
    private String port;
    private String ipsrc;

    public JavaObject(String id, String p, String is){
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

}
