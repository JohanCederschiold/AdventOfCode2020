package seventh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Johan1 {

    private final static String file = "src/main/java/seventh/bagage.txt";

    public static void main(String[] args) throws IOException {

        List<String> bagage = readAnswersToList();
        Map<String, List<String>> mappedContent = mapContent(bagage);
        Set<String> lookForTheseBags = bagsThatContainBag(mappedContent, "shiny gold");

        while (true) {
            String [] arr = new String[lookForTheseBags.size()];
            lookForTheseBags.toArray(arr);
            for (String s : arr ) {
                lookForTheseBags.addAll(bagsThatContainBag(mappedContent, s));
            }
            if (lookForTheseBags.size() == arr.length) {
                break;
            }
        }

        System.out.println(lookForTheseBags.size());
    }

    private static Set<String> bagsThatContainBag (Map<String, List<String>> contentMap, String bag) {
        Set<String> matches = new HashSet<String>();

        for (Map.Entry<String, List<String>> entry : contentMap.entrySet()) {
            if (entry.getValue().contains(bag)) {
                matches.add(entry.getKey());
            }
        }
        return matches;
    }

    private static Map<String, List<String>> mapContent (List<String> bags) {

        Map<String, List<String>> contentMap = new HashMap<String, List<String>>();

        for(String bag : bags) {
            List<String> content = new ArrayList<String>();
            String [] containerAndContent = bag  .replace( "bags", "")
                                                        .replace("bag", "")
                                                        .replace(".", "")
                                                        .split("contain");
            String [] bagContent = containerAndContent[1].split(",");
            for (String con : bagContent) {
                content.add(con.replaceAll("[0-9]", "").trim());
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
