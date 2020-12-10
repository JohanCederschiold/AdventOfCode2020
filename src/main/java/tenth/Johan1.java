package tenth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Johan1 {

    private final static String file = "src/main/java/tenth/input.txt";

    public static void main(String[] args) throws IOException {

        List<Integer> adapters = readAnswersToIntegerList();
        Collections.sort(adapters);
        Map<Integer, Integer> testMap = findNoInsances(adapters);

        for (Map.Entry<Integer, Integer> entry : testMap.entrySet()) {
            System.out.printf("%d: %d pcs%n", entry.getKey(), entry.getValue());
        }

        System.out.println(testMap.get(1) * testMap.get(3));
    }

    private static Map<Integer, Integer> findNoInsances (List<Integer> adapterlist) {
        Collections.sort(adapterlist);
        Map<Integer, Integer> mappedAdapters = new HashMap<Integer, Integer>();
        for (int i = 0 ; i < adapterlist.size() - 1 ; i++ ) {
            Integer diff = adapterlist.get(i+1) - adapterlist.get(i);

            if (mappedAdapters.containsKey(diff)) {
                Integer noInstances = mappedAdapters.get(diff) + 1;
                mappedAdapters.put(diff, noInstances);
            } else {
                mappedAdapters.put(diff, 2);
            }
        }
        return mappedAdapters;
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
