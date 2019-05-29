package textTJson;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

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
                String line1;

                line1 = buff.readLine();
                int positionSrc1 = line1.indexOf("src=");
                int positionDst1 = line1.indexOf("dst=");
                String ipsrc1 = line1.substring(positionSrc1 + 4, positionDst1 - 1);

                while ((line = buff.readLine()) != null) {
                    int positionSrc = line.indexOf("src=");
                    int positionDst = line.indexOf("dst=");
                    int positionSrcPort = line.indexOf("src_port=");
                    int positionDstPort = line.indexOf("dst_port=");
                    int positionSrcXlated = line.indexOf("src-xlated");

                    String ipsrc = line.substring(positionSrc + 4, positionDst - 1);
                    String ipdst = line.substring(positionDst + 4, positionSrcPort - 1);
                    String destPort = line.substring(positionDstPort + 9, positionSrcXlated - 1);

                    //Add data to CSV
                    /*String[] data = {ipsrc, ipdst, destPort};
                    String oui = "\r\n";
                    String[] space = {oui};
                    writer.writeNext(data);
                    writer.writeNext(space);*/

                    JSONObject ipsrcUp = new JSONObject();
                    ipsrcUp.put("ipsrc", ipsrc);

                    JSONArray arrayOfIpsrc = new JSONArray();
                    arrayOfIpsrc.add(ipsrcUp);

                    JSONObject destPortUp = new JSONObject();
                    destPortUp.put("port", destPort);

                    JSONArray arrayOfDestPort = new JSONArray();
                    arrayOfDestPort.add(destPortUp);

                    JSONObject ipdstUp = new JSONObject();
                    ipdstUp.put("ipdst", ipdst);

                    JSONArray arrayOfIpDst = new JSONArray();
                    arrayOfIpDst.add(ipdstUp);

                    JSONObject firstLine = new JSONObject();
                    firstLine.put("IPDEST", "null");
                    firstLine.put("children", arrayOfIpDst);

                    /*if(ipsrc1 == ipsrc){

                    }*/
                    try (FileWriter writer = new FileWriter(output)) {

                        writer.write(ipsrcUp.toJSONString());
                        writer.flush();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }finally{
                buff.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
