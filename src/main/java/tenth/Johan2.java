package tenth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Johan2 {

    private final static String file = "src/main/java/tenth/test.txt";

    public static void main(String[] args) throws IOException {

        List<Integer> adapters = readAnswersToIntegerList();
        Collections.sort(adapters);
        printList(adapters);




    }

    private static void printList (List<Integer> list) {
        for (Integer i : list) {
            System.out.println(i);
        }
    }

    private static List<Integer> readAnswersToIntegerList() throws IOException {
        List<Integer> answers = new ArrayList<Integer>();
        BufferedReader reader = new BufferedReader(new FileReader(new File(file)));

        String line = reader.readLine();

        while (line != null ) {
            answers.add(Integer.parseInt(line));
            line = reader.readLine();
        }

        reader.close();

        return answers;
    }
}
