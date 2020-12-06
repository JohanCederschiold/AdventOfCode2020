package Sixth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Johan2 {

    private final static String file = "src/main/java/Sixth/answers.txt";


    public static void main(String[] args) throws IOException {

        List<String> answers = readAnswersToList();
        System.out.println(readList(answers));
    }


    private static int readList (List<String> listOfAnswers) {

        Map<Character, Integer> answerRegister = new TreeMap<Character, Integer>();
        int personsInGroup = 0;
        int totalScore = 0;

        for (String answers : listOfAnswers) {
            if (answers.length() < 1) {
                totalScore += countResultsForGroup(answerRegister, personsInGroup);
                personsInGroup = 0;
                answerRegister = new TreeMap<Character, Integer>();
            } else {
                personsInGroup++;
                char [] answerByAnswer = answers.toCharArray();
                for (char a : answerByAnswer) {
                    if (answerRegister.containsKey(a)) {
                        answerRegister.put(a, answerRegister.get(a) + 1);
                    } else {
                        answerRegister.put(a, 1);
                    }
                }
            }
        }
        totalScore += countResultsForGroup(answerRegister, personsInGroup);
        return totalScore;

    }

    private static int countResultsForGroup(Map<Character, Integer> answerRegister, int personsInGroup) {
        int totalScore = 0;
        for (Map.Entry<Character, Integer> entry : answerRegister.entrySet()) {
            if (entry.getValue() == personsInGroup) {
                totalScore++;
            }
        }
        return totalScore;
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
