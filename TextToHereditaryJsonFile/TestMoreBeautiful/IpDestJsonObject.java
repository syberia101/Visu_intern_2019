package TextToHereditaryJsonFile.TestMoreBeautiful;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Created by c1972519 on 6/6/2019.
 */
public class IpDestJsonObject {
    String futObjet;
    JSONObject oldObject = new JSONObject();
    JSONArray arrayOfIpDest;

    public IpDestJsonObject(){ //, JSONObject obj, JSONArray arr
        //if(obj == null && arr == null){
        arrayOfIpDest = new JSONArray();
            //createNewObj(futObjet);
        //}else{
          //  oldObject = obj;
           // createSurObj(arr);
        //}
    }

    public void createNewObj(String ipdst){
        JSONObject object = new JSONObject();
        object.put("ipdst", ipdst);
        arrayOfIpDest.add(object);
        oldObject = object;
    }

    public JSONObject createSurObj(JSONObject oldObject,JSONArray arrayForIpDst){
        oldObject.put("children", arrayForIpDst);

        return oldObject;
    }
}
