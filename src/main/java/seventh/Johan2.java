package seventh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Johan2 {

    private final static String file = "src/main/java/seventh/bagage.txt";
    private static Map<String, List<String>> test;

    public static void main(String[] args) throws IOException {

        List<String> input = readAnswersToList();
        test = mapContent(input);

        List<String> searchBags = getContent("shiny gold");
        List<String> accumulatedBags = new ArrayList<String>();
        accumulatedBags.addAll(searchBags);

        while (true) {

            List<String> foundBags = new ArrayList<String>();
            for (String s : searchBags) {
                foundBags.addAll(getContent(s));
            }
            if (foundBags.isEmpty()) {
                break;
            } else {
                accumulatedBags.addAll(foundBags);
                searchBags = foundBags;
            }
        }

        for (String g : accumulatedBags) {

        }

        System.out.println(accumulatedBags.size());
    }

    private static List<String> getContent (String container) {

        List<String> nestedBags = new ArrayList<String>();
        List<String> content = test.get(container);

        for (String bag : content) {

            int pcs = 0;
            try {
                pcs = Integer.parseInt(bag.substring(0,1));
            } catch (NumberFormatException e) {
                return Collections.emptyList();
            }

            String newContainer = bag.replaceAll("[0-9]", "").trim();
            for (int i = 0 ; i < pcs ; i++ ) {
                nestedBags.add(newContainer);
            }
        }
        return nestedBags;

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
