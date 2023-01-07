package sdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Game {

    public static void main(String[] args) throws Exception, FileNotFoundException {
        
        // Read file samplegps.csv
        Path gamePath = Paths.get("./samplegps.csv");
        File gpc = gamePath.toFile(); // Put the file path into a file

        // Check if the file exist
        if (!gpc.exists()) {
            System.err.println("Cannot find file");
            System.exit(1);
        }else {
            System.out.println("File exists!\n");
        }

        FileReader fr = new FileReader(gpc);
        BufferedReader br = new BufferedReader(fr);
        String line;

        Map<String, PlayStoreData> playstore = new HashMap<>();

        // Ignores first line
        br.readLine();

        while (null != (line = br.readLine())) {
            // Ignores blank line
            if (line.isEmpty()) {
                continue;
            }

            String[] values = line.split(",");
            
            /*// Replace Nan into 1
            for (int i = 0; i < values.length; i++) {
                if (values[i].equals("NaN")) {
                    values[i] = "1";
                }
            }*/

            if (values.length < 3) {
                continue;
            }
            String name = values[0].trim();
            String category = values[1].trim();
            String strRating = values[2].trim();
            Float rating = 0f;
            // Ignore the App with a rating with NaN
            if (strRating.toLowerCase().equals("nan")) {
                  continue;
            }
            System.out.println(name + " , " + category + " , " + strRating);
                    
        }

        br.close();
        fr.close();
        
    }
}
