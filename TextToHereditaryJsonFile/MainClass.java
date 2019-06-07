package TextToHereditaryJsonFile;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;

/**
 * Created by c1972519 on 5/27/2019.
 */

public class MainClass {
    public static void main(String[] args) throws Exception {
        File input = new File("C:\\Users\\c1972519\\Desktop\\test.txt");
        File output = new File("C:\\Users\\c1972519\\Desktop\\output.json");

        try {
            BufferedReader buff = new BufferedReader(new FileReader(input));

            try {
                String line;
                String line1;

                int lenghtFile = 0;
                boolean dosAtt = true;

                while ((line1 = buff.readLine()) != null) {
                    lenghtFile ++;
                }

                buff = new BufferedReader(new FileReader(input));

                line = buff.readLine();

                String oldIpdest = findPositionDest(line);
                String oldPort = findPositionPort(line);

                String newIpsrc = findPositionSrc(line);
                String newIpdest = findPositionDest(line);
                String newPort = findPositionPort(line);

                for(int i = 0; i<lenghtFile; i++){
                    if(!(newIpdest.equals(oldIpdest))){
                        dosAtt = false;
                    }else if(!(newPort.equals(oldPort))){
                        dosAtt = false;
                    }

                    newIpsrc = findPositionSrc(line);
                    newIpdest = findPositionDest(line);
                    newPort = findPositionPort(line);
                }

                if(dosAtt == true){
                    JSONArray arrayOfIpsrc = new JSONArray();
                    JSONArray arrayOfIpDst = new JSONArray();
                    JSONArray arrayOfDestPort = new JSONArray();

                    line = buff.readLine();

                    newIpsrc = findPositionSrc(line);
                    oldIpdest = findPositionDest(line);
                    oldPort = findPositionPort(line);

                    arrayOfIpsrc.add(createNewIpSrc(newIpsrc));

                    JSONObject objPort = createPortDest(createNewPort(oldPort), arrayOfIpsrc);
                    arrayOfDestPort.add(objPort);

                    JSONObject objIpDst = createIpDest(createNewIpDest(oldIpdest) ,arrayOfDestPort);
                    arrayOfIpDst.add(objIpDst);


                    while ((line = buff.readLine()) != null) {
                        newIpsrc = findPositionSrc(line);
                        newIpdest = findPositionDest(line);
                        newPort = findPositionPort(line);

                        if(newIpdest.equals(oldIpdest)){
                            if(newPort.equals(oldPort)){
                                arrayOfIpsrc.add(createNewIpSrc(newIpsrc));
                            }else{
                                arrayOfIpsrc.add(createNewIpSrc(newIpsrc));
                                arrayOfDestPort.add(createPortDest(createNewPort(newPort), arrayOfIpsrc));
                            }
                        }else{
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
                }else{
                    System.out.println("Sorry this is not a DOS attack, we can't help you for the moment. Please change the input file.");
                }

            }finally{
                buff.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /////////////// Find the element and return it as a String ////////////////
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

    ////////////////// Create a new JSON object ////////////////
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

    /////////////////// Add the children of each JSON object ////////////////////
    public static JSONObject createPortDest(JSONObject port, JSONArray arrayForPort){
        port.put("children", arrayForPort);

        return port;
    }

    public static JSONObject createIpDest(JSONObject ipdest, JSONArray arrayForIpDst){
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