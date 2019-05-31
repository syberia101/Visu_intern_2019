package textTJson;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by c1972519 on 5/27/2019.
 */

public class MainClass {
    public static void main(String[] args) throws Exception {
        File input = new File("C:\\Users\\c1972519\\Desktop\\test.txt"); //changer le file
        File output = new File("C:\\Users\\c1972519\\Desktop\\output.json");

        try {
            BufferedReader buff = new BufferedReader(new FileReader(input));

            //Create CSVWriter object with outputFile as parameter
            //CSVWriter writer = new CSVWriter(tempFile);

            try {
                String line;
                //String oldIpdest = new String("0");
                //String oldPort =  new String("0");

                JSONArray arrayOfIpsrc = new JSONArray();
                JSONArray arrayOfIpDst = new JSONArray();
                JSONArray arrayOfDestPort = new JSONArray();

                line = buff.readLine();

                String newIpsrc = findPositionSrc(line);
                String oldIpdest = findPositionDest(line);
                String oldPort = findPositionPort(line);

                arrayOfIpsrc.add(createNewIpSrc(newIpsrc));

                JSONObject objPort = createPortDest(createNewPort(oldPort), arrayOfIpsrc);
                arrayOfDestPort.add(objPort);

                JSONObject objIpDst = createIpDest(createNewIpDest(oldIpdest) ,arrayOfDestPort);
                arrayOfIpDst.add(objIpDst);


               while ((line = buff.readLine()) != null) {
                    newIpsrc = findPositionSrc(line);
                    String newIpdest = findPositionDest(line);
                    String newPort = findPositionPort(line);

                    if(newIpdest.equals(oldIpdest)){
                        if(newPort.equals(oldPort)){
                            arrayOfIpsrc.add(createNewIpSrc(newIpsrc));
                            //arrayOfDestPort.add(arrayOfIpsrc);
                        }else{
                            //JSONArray otherArrayOfDestPort = new JSONArray();
                            //JSONArray otherArrayOfIpsrc = new JSONArray();
                            arrayOfIpsrc.add(createNewIpSrc(newIpsrc));
                            arrayOfDestPort.add(createPortDest(createNewPort(newPort), arrayOfIpsrc));
                        }
                    }else{
                        //JSONArray otherArrayOfIpsrc = new JSONArray();
                        arrayOfIpDst.add(createIpDest(createNewIpDest(newIpdest) ,arrayOfDestPort));
                    }



                    oldIpdest = newIpdest;
                    oldPort = newPort;
                }
                try (FileWriter writer = new FileWriter(output)) {

                    writer.write(createFirstLine(arrayOfIpDst).toJSONString());
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
    static String findPositionSrc(String line){
        int positionSrc = line.indexOf("src=");
        int positionDst = line.indexOf("dst=");

        String ipsrc = line.substring(positionSrc + 4, positionDst - 1);
        return ipsrc;
    }

    static String findPositionDest(String line){
        int positionDst = line.indexOf("dst=");
        int positionSrcPort = line.indexOf("src_port=");

        String ipdst = line.substring(positionDst + 4, positionSrcPort - 1);
        return ipdst;
    }

    static String findPositionPort(String line){
        int positionDstPort = line.indexOf("dst_port=");
        int positionSrcXlated = line.indexOf("src-xlated");

        String destPort = line.substring(positionDstPort + 9, positionSrcXlated - 1);
        return destPort;
    }

    ////////////////// Creer nouvel objet JSON ////////////////
    public static JSONObject createNewIpSrc(String ipsrc){
        JSONObject ipsrcUp = new JSONObject();
        ipsrcUp.put("ipsrc", ipsrc);

        return ipsrcUp;
    }

    public static JSONObject createNewIpDest(String ipdst){
        JSONObject ipdstUp = new JSONObject();
        ipdstUp.put("ipdst", ipdst);

        return ipdstUp;
    }

    public static JSONObject createNewPort(String destPort){
        JSONObject destPortUp = new JSONObject();
        destPortUp.put("port", destPort);

        return destPortUp;
    }

    ///////////////// Ajouter JSON Object a la liste /////////////////
    /*public static JSONArray addIpSrc(JSONArray arrayOfIpsrc, JSONObject ipsrcUp){
        arrayOfIpsrc.add(ipsrcUp);

        return arrayOfIpsrc;
    }

    public static JSONArray addIpDest(JSONArray arrayOfIpDst, JSONObject ipdstUp){
        arrayOfIpDst.add(ipdstUp);

        return arrayOfIpDst;
    }

    public static JSONArray addPort(JSONArray arrayOfDestPort, JSONObject destPortUp){
        arrayOfDestPort.add(destPortUp);

        return arrayOfDestPort;
    }*/

    /////////////////// Creer surobjet ////////////////////
    public static JSONObject createPortDest(JSONObject port, JSONArray arrayForPort){
        //JSONObject objPort = new JSONObject();
        //port.put("port", port.get());
        port.put("children", arrayForPort);

        return port;
    }

    public static JSONObject createIpDest(JSONObject ipdest, JSONArray arrayForIpDst){

            //String ipdst = new String(arrayOfIpdst.get(i).toString());

            //JSONObject objIpDst = new JSONObject();
            //objIpDst.put("ipdst", ipdest);
            ipdest.put("children", arrayForIpDst);

            return ipdest;
    }

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