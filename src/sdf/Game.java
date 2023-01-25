package sdf;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Game {

    public static void main(String[] args) throws Exception, FileNotFoundException {
        // Instantiate Hashmap
        Map<String, PlayStoreData> playstore = new HashMap<>();

        // Instantiate reader
        FileReader r = new FileReader("googleplaystore.csv");
        BufferedReader br = new BufferedReader(r);

        // Discard the first line
        String input = br.readLine();
        String[] columns;

        // reads line by line
        while (null != (input = br.readLine())) {
            input = input.trim();
            // If the line is empty, discard it
            if (input.length() <= 0) {
                continue;
            }

            // Split the line by comma
            columns = input.split(",");

            // If the number of columns is less than 3, discard it
            if (columns.length < 3) {
                continue;
            }

            // Add the information as a string seperated by comma and add it into the categorry
            String name = columns[0].trim();
            String category = columns[1].trim();
            String strRating = columns[2].trim();
            Float rating = 0f;
            // If the rating is not a number NaN, discard it
            try {
                rating = Float.parseFloat(strRating);
            } catch (NumberFormatException ex) {
                continue;
            }
            // If rating is NaN, then discard it
            if (Float.isNaN(rating))
                continue;

            // Get the category from the map, if it is null
            // then this is a new category, create it and add it to the map
            PlayStoreData data = playstore.get(category);
            if (null == data) {
                data = new PlayStoreData(category);
                playstore.put(category, data);
            }
            // Add the rating
            data.add(rating);
            // Evaluate the highest and lowest rating
            data.evaluate(name, rating);
            
        }

        br.close();
        r.close();

        System.out.print("---------- Playstore Ratings -------------\n");
        for (String cat : playstore.keySet()) {
            PlayStoreData data = playstore.get(cat);
            System.out.printf("Category: %s\n", cat);
            System.out.printf("\taverage rating: %.2f\n", data.getAverageRating());
            System.out.printf("\tHighest rated game: %s (%.2f)\n", data.getHighestRatedGame(), data.getHighestRating());
            System.out.printf("\tLowest rated game: %s (%.2f)\n", data.getLowestRatedGame(), data.getLowestRating());
            
        }

        

    }
}
