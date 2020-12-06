package Sixth;

import com.sun.org.apache.xml.internal.serializer.SerializerTrace;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Johan1 {

    private final static String file = "src/main/java/Sixth/answers.txt";


    public static void main(String[] args) throws IOException {

        List<String> answers = readAnswersToList();
        System.out.println(readList(answers));


    }


    private static int readList (List<String> listOfAnswers) {

        Set<Character> answer = new HashSet<Character>();
        int totals = 0;

        for (String answers : listOfAnswers) {
            if (answers.length() < 1) {
                totals += answer.size();
                answer = new HashSet<Character>();
            } else {
                char [] answerByAnswer = answers.toCharArray();
                for (char a : answerByAnswer) {
                    answer.add(a);
                }
            }

        }

        totals += answer.size();

        return totals;

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
