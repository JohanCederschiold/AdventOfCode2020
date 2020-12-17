package sixteen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Johan1 {

    private static String rulesFile = "src/main/java/sixteen/rulex.txt";
    private static String ticketsFile = "src/main/java/sixteen/tickets.txt";

    public static void main(String[] args) throws IOException {
        List<String> rules = readAnswersToList(rulesFile);
        List<String> tickets = readAnswersToList(ticketsFile);
        System.out.println(checkTickets(tickets, registerValidValues(rules)));




    }

    private static int checkTickets (List<String> tickets, Set<Integer> validValues) {
        int sum = 0;

        for (String ticket : tickets ) {
            String[] ticketvalues = ticket.split(",");
            for (String ticketValue : ticketvalues ) {
                int val = Integer.parseInt(ticketValue.trim());
                if (!validValues.contains(val)) {
                    sum += val;
                }
            }
        }
        return sum;

    }

    private static Set<Integer> registerValidValues (List<String> list) {
        Set<Integer> validValues = new HashSet<Integer>();
        for (String s : list) {
            String onlySpans = s.split(":")[1];
            String[] lowAndHigh = onlySpans.split(" or ");
            for (String span : lowAndHigh) {
                fillSet(span, validValues);
            }
        }
        return validValues;
    }

    private static void findGaps(Set<Integer> validValues, int low, int high) {
        for (int i = low ; i <= high ; i++ ) {
            if (!validValues.contains(i)){
                System.out.println(i);
            }
        }
    }

    private static void fillSet(String span, Set<Integer> validValues) {
        String[] separateValues = span.split("-");
        int low = Integer.parseInt(separateValues[0].trim());
        int high = Integer.parseInt(separateValues[1].trim());

        for (int i = low ; i <= high ; i++ ) {
            validValues.add(i);
        }
    }

    private static List<String> readAnswersToList(String file) throws IOException {
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
