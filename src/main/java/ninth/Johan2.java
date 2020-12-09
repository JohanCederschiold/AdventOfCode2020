package ninth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Johan2 {

    private final static String file = "src/main/java/ninth/input.txt";
    private final static int offset = 25;
    private final static int target = 18272118;
    private final static int maxIndex = 511;

    public static void main(String[] args) throws IOException {

        List<Long> numbers = readAnswersToLongList();
        System.out.println(findConsecutiveNumbers(numbers));

    }

    private static Long findConsecutiveNumbers (List<Long> listofnumbers) {

        for ( int i = maxIndex - 1 ; i >= 0 ; i-- ) {
            int counter = i - 1;
            Long currentSum = listofnumbers.get(counter);
            while (currentSum < target) {
                counter--;
                currentSum += listofnumbers.get(counter);
            }
            if (currentSum == target) {
                System.out.printf("%d to %d %n", i, counter);
                return getSumOfMinAndMax(i, counter, listofnumbers);
            }
        }
        return (long) 0;
    }

    private static Long getSumOfMinAndMax (int indexOne, int indexTwo, List<Long> list) {
        List<Long> listChunk = new ArrayList<Long>();
        int start = Math.min(indexOne, indexTwo);
        int stop = Math.max(indexOne, indexTwo);
        for (int i = start ; i <= stop ; i++ ) {
            listChunk.add(list.get(i));
        }
        Collections.sort(listChunk);
        return listChunk.get(0) + listChunk.get(listChunk.size()-1);
    }


    private static Long findFirstWrongNumber (List<Long> listofnumbers) {
        for (int i = offset ; i < listofnumbers.size() ; i++  )  {
            boolean foundMatch = false;
            for (int j = i - offset ; j < listofnumbers.size() - offset ; j++ ) {
                foundMatch = false;
                for (int k = i - offset ; k < listofnumbers.size() - offset ; k++ ) {
                    if (k != j && listofnumbers.get(i) == listofnumbers.get(k) + listofnumbers.get(j)) {
                        foundMatch = true;
                        break;
                    }
                }
                if(foundMatch) {
                    break;
                }
            }
            if (!foundMatch) {
                return (long) i;
            }
        }
        return 0L;
    }
    private static List<Long> readAnswersToLongList () throws IOException {
        List<Long> answers = new ArrayList<Long>();
        BufferedReader reader = new BufferedReader(new FileReader(new File(file)));

        String line = reader.readLine();

        while (line != null ) {
            answers.add(Long.parseLong(line));
            line = reader.readLine();
        }

        reader.close();

        return answers;
    }
}

