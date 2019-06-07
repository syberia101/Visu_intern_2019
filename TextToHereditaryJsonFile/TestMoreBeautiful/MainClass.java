package TextToHereditaryJsonFile.TestMoreBeautiful;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by c1972519 on 5/27/2019.
 */

public class MainClass {
    public static void main(String[] args) throws Exception {
        File input = new File("C:\\Users\\c1972519\\Desktop\\test.txt");
        File output = new File("C:\\Users\\c1972519\\Desktop\\output.json");

        try {
            BufferedReader buff = new BufferedReader(new FileReader(input));

            IpSrcJsonObject newIpSrc = new IpSrcJsonObject();
            IpDestJsonObject newIpDest = new IpDestJsonObject();
            PortJsonObject newPort = new PortJsonObject();

            try {
                String line;

                /*JSONArray arrayOfIpsrc = new JSONArray();
                JSONArray arrayOfIpDst = new JSONArray();
                JSONArray arrayOfDestPort = new JSONArray();*/

                //line = buff.readLine();

                //MyIps ipClass = new MyIps(line);

                ArrayList<MyIps> arrayOfIps = new ArrayList<MyIps>();

                //arrayOfIps.add(ipClass);

                //String newIpsrc = ipClass.ipSrc;
                //String oldIpdest = ipClass.ipDest;
                //String oldPort = ipClass.port;

                //arrayOfIpsrc.add(createNewIpSrc(ipClass.ipSrc));

                //JSONObject objPort = createPortDest(createNewPort(ipClass.port), arrayOfIpsrc);
                //arrayOfDestPort.add(objPort);

                //JSONObject objIpDst = createIpDest(createNewIpDest(ipClass.ipDest) ,arrayOfDestPort);
                //arrayOfIpDst.add(objIpDst);

                while ((line = buff.readLine()) != null) {
                    //newIpsrc = findPositionSrc(line);
                    //String newIpdest = findPositionDest(line);
                    //String newPort = findPositionPort(line);

                    MyIps ipClas = new MyIps(line);
                    arrayOfIps.add(ipClas);

                    //arrayOfIpDst.add(createNewIpDest(arrayOfIps.get(0).ipDest));
                    //arrayOfIpsrc.add(newIpSrc);
                    //arrayOfDestPort.add(PortJsonObject.createSurObj(newPort.object, arrayOfIpsrc));
                    //arrayOfIpDst.add(IpDestJsonObject.createSurObj(newIpDest.object, arrayOfIpsrc));

                    //arrayOfDestPort.add(createPortDest(createNewPort(arrayOfIps.get(0).port), arrayOfIpsrc));
                    //arrayOfIpDst.add(createIpDest(createNewIpDest(arrayOfIps.get(0).ipDest) ,arrayOfDestPort));
                }

                for(int i = 1; i<arrayOfIps.size(); i++) {
                    if(arrayOfIps.get(i).ipDest == arrayOfIps.get(i-1).ipDest){
                        if(arrayOfIps.get(i).port == arrayOfIps.get(i-1).port) {
                            //IpSrcJsonObject newIpSrc = new IpSrcJsonObject(arrayOfIps.get(0).ipSrc);
                            //arrayOfIpsrc.add(newIpSrc);
                            newIpSrc.createNewObj(arrayOfIps.get(i).ipSrc);
                        }else{
                            //JSONArray otherArrayOfDestPort = new JSONArray();
                            //JSONArray otherArrayOfIpsrc = new JSONArray();
                            //IpSrcJsonObject newIpSrc = new IpSrcJsonObject(arrayOfIps.get(0).ipSrc);
                            newIpSrc.createNewObj(arrayOfIps.get(i).ipSrc);
                            //arrayOfIpsrc.add(newIpSrc);
                            //PortJsonObject newPort = new PortJsonObject(arrayOfIps.get(0).port);
                            newPort.createNewObj(arrayOfIps.get(i).port);
                            //arrayOfDestPort.add(PortJsonObject.createSurObj(newPort.object, arrayOfIpsrc));
                            //PortJsonObject.createSurObj(newPort.object, newIpSrc.arrayOfIpSrc);
                            newPort.createSurObj(newPort.oldObject, newIpSrc.arrayOfIpSrc);
                        }
                    }else{
                        //JSONArray otherArrayOfIpsrc = new JSONArray();
                        //IpDestJsonObject newIpDest = new IpDestJsonObject(arrayOfIps.get(0).ipDest);
                        //arrayOfIpDst.add(IpDestJsonObject.createSurObj(newIpDest.object, arrayOfIpsrc));
                        //IpDestJsonObject.createSurObj(newIpDest.object, newIpDest.arrayOfIpDest);
                        newIpDest.createNewObj(arrayOfIps.get(i).ipDest);
                        newIpDest.createSurObj(newIpDest.oldObject, newIpDest.arrayOfIpDest);
                    }
                }

                /*
                    if(ipClas.ipDest.equals(ipClass.ipDest)){
                        if(ipClas.port.equals(ipClass.port)){
                            arrayOfIpsrc.add(createNewIpSrc(ipClas.ipSrc));
                            //arrayOfDestPort.add(arrayOfIpsrc);
                        }else{
                            //JSONArray otherArrayOfDestPort = new JSONArray();
                            //JSONArray otherArrayOfIpsrc = new JSONArray();
                            arrayOfIpsrc.add(createNewIpSrc(ipClas.ipSrc));
                            arrayOfDestPort.add(createPortDest(createNewPort(ipClas.port), arrayOfIpsrc));
                        }
                    }else{
                        //JSONArray otherArrayOfIpsrc = new JSONArray();
                        arrayOfIpDst.add(createIpDest(createNewIpDest(ipClas.ipDest) ,arrayOfDestPort));
                    }

                    oldIpdest = newIpdest;
                    oldPort = newPort;
                }*/
                try (FileWriter writer = new FileWriter(output)) {

                    writer.write(createFirstLine(newIpDest.arrayOfIpDest).toJSONString());
                    writer.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }finally{
                buff.close();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /////////////// Trouver la position des elements ////////////////


    ////////////////// Creer nouvel objet JSON ////////////////


    /////////////////// Creer surobjet ////////////////////
    /*public static JSONObject createPortDest(JSONObject port, JSONArray arrayForPort){
        port.put("children", arrayForPort);

        return port;
    }

    public static JSONObject createIpDest(JSONObject ipdest, JSONArray arrayForIpDst){
            ipdest.put("children", arrayForIpDst);

            return ipdest;
    }
    */
    public static JSONObject createFirstLine(JSONArray arrayOfIpDst){
        JSONObject firstLine = new JSONObject();
        firstLine.put("IPDEST", "null");
        firstLine.put("children", arrayOfIpDst);

        return firstLine;
    }
}

//Add data to CSV
/*String[] data = {ipsrc, ipdst, destPort};
String oui = "\r\n";
String[] space = {oui};
writer.writeNext(data);
writer.writeNext(space);*/