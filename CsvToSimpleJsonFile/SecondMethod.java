package CsvToSimpleJsonFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by c1972519 on 5/16/2019.
 */
public class SecondMethod {

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(","); //to parse the csv file

        File csvFile = new File("C:\\Users\\c1972519\\Desktop\\data1.csv");

        try (BufferedReader in = new BufferedReader(new FileReader(csvFile));) {
            List<CsvToJava> jsonfile = in
                    .lines()
                    .skip(1)
                    .map(line -> {
                        String[] x = pattern.split(line);
                        return new CsvToJava(x[0], x[1], x[2]);
                    })
                    .collect(Collectors.toList());

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File("C:\\Users\\c1972519\\Desktop\\data1.json"), jsonfile);

        } catch (IOException ex) {
            System.out.println("Please fill in a file !");
        }
    }

    public static class CsvToJava{
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
}