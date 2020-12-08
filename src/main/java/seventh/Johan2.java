package seventh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Johan2 {

    private final static String file = "src/main/java/seventh/bagage.txt";

    public static void main(String[] args) throws IOException {

        List<String> input = readAnswersToList();
        Map<String, List<String>> test = mapContent(input);
        System.out.println(test.get("shiny gold"));


    }

    private static Map<String, List<String>> mapContent (List<String> bags) {

        Map<String, List<String>> contentMap = new HashMap<String, List<String>>();

        for(String bag : bags) {
            List<String> content = new ArrayList<String>();
            String [] containerAndContent = bag .replace( "bags", "")
                                                .replace("bag", "")
                                                .replace(".", "")
                                                .split("contain");
            String [] bagContent = containerAndContent[1].split(",");
            for (String con : bagContent) {
                content.add(con.trim());
            }
            contentMap.put(containerAndContent[0].trim(), content);
        }
        return contentMap;
    }



    private static List<String> readAnswersToList () throws IOException {
        List<String> answers = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new FileReader(new File(file)));

        String line = reader.readLine();

        while (line != null ) {
            answers.add(line);
            line = reader.readLine();
        }

        reader.close();

        return answers;
    }

}
