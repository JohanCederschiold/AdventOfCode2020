package sixteen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Johan2 {

    private static String rulesFile = "src/main/java/sixteen/rulex.txt";
    private static String ticketsFile = "src/main/java/sixteen/tickets.txt";

    public static void main(String[] args) throws IOException {
        List<String> rules = readAnswersToList(rulesFile);
        List<String> tickets = readAnswersToList(ticketsFile);
        List<String> validTickets = filterValidTickets(tickets, registerValidValues(rules));
        Map<Integer, List<Integer>> testMap = mapFields(makeTicketsToArrays(validTickets));

        List<TicketField> tf = mapRulesToTicketFields(rules);
        //printMap(testMap);
    }

    private static List<TicketField> mapRulesToTicketFields (List<String> rules ) {

        List<TicketField> ticketFields = new ArrayList<TicketField>();
        for (String rule : rules ){
            TicketField tf = new TicketField();
            String fieldName = rule.split(":")[0];
            String onlyNumbers = rule.replace(fieldName, "").replaceAll("[0-9]", ":");
            System.out.println(onlyNumbers);

//             tf.setLowSpanBottomValue(sc.nextInt());
//            tf.setLowSpanTopValue(sc.nextInt());
//            tf.setHighSpanBottomValue(sc.nextInt());
//            tf.setHighSpanTopValue(sc.nextInt());
            ticketFields.add(tf);
        }
        return ticketFields;
    }

    private static void printMap(Map<Integer, List<Integer>> testMap) {
        for (Map.Entry<Integer, List<Integer>> entry : testMap.entrySet()) {
            System.out.printf("-------------Field no %d----------------------%n", entry.getKey());
            for (Integer i : entry.getValue()) {
                System.out.printf("%d ", i);
            }
            System.out.println("-------------------------------------------------");
        }
    }

    private static Map<Integer, List<Integer>> mapFields (List<String[]> ticketsAndFields) {
        Map<Integer, List<Integer>> valuesPerFields = new HashMap<Integer, List<Integer>>();
        for (int i = 0 ; i < ticketsAndFields.get(0).length ; i++) {
            List<Integer>valuesCurrentField = new ArrayList<Integer>();
            for (String[] s : ticketsAndFields) {
                valuesCurrentField.add(Integer.parseInt(s[i]));
            }
            Collections.sort(valuesCurrentField);
            valuesPerFields.put(i, valuesCurrentField);
        }
        return valuesPerFields;
    }

    private static List<String[]> makeTicketsToArrays(List<String> tickets) {
        List<String[]> ticketFields = new ArrayList<String[]>();
        for (String ticket : tickets) {
            ticketFields.add(ticket.split(","));
        }
        return ticketFields;
    }

    private static List<String> filterValidTickets(List<String> tickets, Set<Integer> validValues) {
        List<String> validTickets = new ArrayList<String>();

        for (String ticket : tickets ) {
            boolean ticketValid = true;
            String[] ticketvalues = ticket.split(",");
            for (String ticketValue : ticketvalues ) {
                int val = Integer.parseInt(ticketValue.trim());
                if (!validValues.contains(val)) {
                    ticketValid = false;
                }
            }
            if(ticketValid) {
                validTickets.add(ticket);
            }
        }
        return validTickets;
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
