package TextToHereditaryJsonFile.TestMoreBeautiful;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Created by c1972519 on 6/6/2019.
 */
public class PortJsonObject {
    String futObjet;
    JSONObject oldObject = new JSONObject();
    JSONArray arrayOfPort;

    public PortJsonObject(){
        //if(obj == null && arr == null){
            //futObjet = str;
            //createNewObj(futObjet);
         arrayOfPort = new JSONArray();
        //}else{
          //  oldObject = obj;
            //createSurObj(arr);
        //}
    }

    public void createNewObj(String port){
        JSONObject object = new JSONObject();
        object.put("port", port);
        arrayOfPort.add(object);
        oldObject = object;
    }

    public JSONObject createSurObj(JSONObject oldObject, JSONArray arrayForPort){
        oldObject.put("children", arrayForPort);

        return oldObject;
    }
}
