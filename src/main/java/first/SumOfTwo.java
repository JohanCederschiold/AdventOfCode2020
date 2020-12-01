package first;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SumOfTwo {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/java/first/numbers.txt")));
        List<Integer> numbers = new ArrayList<Integer>();
        String currentnumber = reader.readLine();
        numbers.add(Integer.parseInt(currentnumber));

        while (currentnumber != null) {
            currentnumber = reader.readLine();
            if (currentnumber != null) {
                numbers.add(Integer.parseInt(currentnumber));
            }
        }

        System.out.println(findTwo(numbers));

    }

    private static Integer findTwo (List<Integer> allNumbers) {

        for (int i = 0 ; i < allNumbers.size() ; i++ ) {

            int oneNumber = allNumbers.get(i);
            for (int j =  i + 1  ; j < allNumbers.size() ; j++ ) {
                if (oneNumber + allNumbers.get(j) == 2020) {
                    return oneNumber * allNumbers.get(j);
                }
            }
        }
        return null;
    }
}
