package ninth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Johan1 {

    private final static String file = "src/main/java/ninth/input.txt";
    private final static int offset = 25;

    public static void main(String[] args) throws IOException {

        List<Long> numbers = readAnswersToLongList();
        System.out.println(findFirstWrongNumber(numbers));

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
                return listofnumbers.get(i);
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
