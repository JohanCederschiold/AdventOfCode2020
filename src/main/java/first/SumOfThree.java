package first;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SumOfThree {

    public static void main(String[] args) throws IOException {

        //236873508

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

        Collections.sort(numbers);
        System.out.println(findThree(numbers));

    }

    private static Integer findThree (List<Integer> numbers) {

        for (int i = 0 ; i < numbers.size() - 2 ; i++ ) {
            for(int j = i ; j < numbers.size() -1; j++ ) {
                if(numbers.get(i) + numbers.get(j) > 2020) {
                    break;
                }
                for(int k = j ; k < numbers.size() ; k++ ) {
                    if(numbers.get(i) + numbers.get(j) + numbers.get(k) > 2020) {
                        break;
                    }
                    if (
                            numbers.get(i) +
                            numbers.get(j) +
                            numbers.get(k) == 2020
                    ) {
                        return numbers.get(i) * numbers.get(j) * numbers.get(k);
                    }
                }
            }
        }
        return null;
    }

}

