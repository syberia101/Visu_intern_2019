package goodJSON;

/**
 * Created by c1972519 on 5/23/2019.
 */
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
public class Principale {
/*
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(",");

        File csvFile = new File("C:\\Users\\c1972519\\Desktop\\data1.csv");

        try (BufferedReader in = new BufferedReader(new FileReader(csvFile));) {

            List<JavaObject> jsonfile = in
                    .lines()
                    .skip(1)
                    .map(line -> {
                        String[] x = pattern.split(line);

                        //if(strcmp(x[0], )){}
                        return new JavaObject(x[0], x[1], x[2]);
                    })
                    .collect(Collectors.toList());

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File("C:\\Users\\c1972519\\Desktop\\goodData.json"), jsonfile);

        } catch (IOException ex) {
            System.out.println("Please fill in a file !");
        }
    }
    */
}
