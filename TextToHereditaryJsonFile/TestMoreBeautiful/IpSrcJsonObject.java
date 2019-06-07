package TextToHereditaryJsonFile.TestMoreBeautiful;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Created by c1972519 on 6/6/2019.
 */
public class IpSrcJsonObject {
    //String futObjet;
    JSONArray arrayOfIpSrc;

    public IpSrcJsonObject(){
        //futObjet = str;
        //createNewObj(futObjet);
        arrayOfIpSrc = new JSONArray();
    }

    public void createNewObj(String ipsrc){
        JSONObject object = new JSONObject();
        object.put("ipsrc", ipsrc);
        object.put("size", 1);
        arrayOfIpSrc.add(object);
    }
}
