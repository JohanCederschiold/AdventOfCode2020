package sixteen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Johan2 {

    private static String rulesFile = "src/main/java/sixteen/rulex.txt";
    private static String ticketsFile = "src/main/java/sixteen/tickets.txt";
    private static int [] myticket = {89,139,79,151,97,67,71,53,59,149,127,131,103,109,137,73,101,83,61,107};

    public static void main(String[] args) throws IOException {
        List<String> rules = readAnswersToList(rulesFile);
        List<String> tickets = readAnswersToList(ticketsFile);
        List<String> validTickets = filterValidTickets(tickets, registerValidValues(rules));
        Map<Integer, List<Integer>> testMap = mapFields(makeTicketsToArrays(validTickets));
        List<TicketField> tf = mapRulesToTicketFields(rules);
        Map<Integer, List<String>> possibleMatches = getPossibleFields(testMap, tf);
        Map<Integer, List<String>> filtered = filterDownFields(possibleMatches);
        System.out.println(calculateTicketValue(filtered));
     }

     private static long calculateTicketValue (Map<Integer, List<String>> filteredFields) {
        long sum = 0;
        for (Map.Entry<Integer, List<String>> entry : filteredFields.entrySet()) {
            if (entry.getValue().get(0).startsWith("departure")) {
                if (sum == 0) {
                    sum = myticket[entry.getKey()];
                } else {
                    sum *= myticket[entry.getKey()];
                }
            }
        }
        return sum;
     }

     private static Map<Integer, List<String>> filterDownFields(Map<Integer, List<String>> filterThis) {
         List<Integer> completedKeys = new ArrayList<Integer>();

        while (true) {
            int currentFilterKey = -1;
            for (Map.Entry<Integer, List<String>> entry : filterThis.entrySet()) {
                if (!completedKeys.contains(entry.getKey()) && entry.getValue().size() == 1) {
                    currentFilterKey = entry.getKey();
                    completedKeys.add(entry.getKey());
                    break;
                }
            }
            if (completedKeys.size() == filterThis.size()) {
                break;
            }
            for (Map.Entry<Integer, List<String>> entry : filterThis.entrySet()) {
                if (entry.getKey() != currentFilterKey) {
                    //entry.getValue().remove(filterThis.get(currentFilterKey).get(0));
                    List<String> fields = entry.getValue();
                    fields.remove(filterThis.get(currentFilterKey).get(0));
                    filterThis.put(entry.getKey(), fields);
                }
            }
        }
        return filterThis;
     }

    private static Map<Integer, List<String>> getPossibleFields(Map<Integer, List<Integer>> testMap, List<TicketField> tf) {
        Map<Integer, List<String>> possibleMatches = new HashMap<Integer, List<String>>();

        for (Map.Entry<Integer, List<Integer>> entry : testMap.entrySet()) {
            List<String> possibleFieldsMatching = new ArrayList<String>();
            for (TicketField ticketField : tf) {
                boolean isMatchingSoFar = true;
                for (Integer ticketValue : entry.getValue()) {
                    if (ticketValue > ticketField.getHighSpanTopValue()) {
                        isMatchingSoFar = false;
                    }
                    if (ticketValue < ticketField.getLowSpanBottomValue()) {
                        isMatchingSoFar = false;
                    }
                    if (ticketValue > ticketField.getLowSpanTopValue() && ticketValue < ticketField.getHighSpanBottomValue()){
                        isMatchingSoFar = false;
                    }
                    if(!isMatchingSoFar) {
                        break;
                    }
                }
                if (isMatchingSoFar) {
                    possibleFieldsMatching.add(ticketField.getFieldName());
                }
            }
            possibleMatches.put(entry.getKey(), possibleFieldsMatching);
        }
        return possibleMatches;
    }

    private static List<TicketField> mapRulesToTicketFields (List<String> rules ) {

        List<TicketField> ticketFields = new ArrayList<TicketField>();
        for (String rule : rules ){
            TicketField tf = new TicketField();
            String fieldName = rule.split(":")[0];
            tf.setFieldName(fieldName);
            String [] onlyNumbers = rule.replace(fieldName, "").replaceAll("\\D+", " ").split(" ");
            tf.setLowSpanBottomValue(Integer.parseInt(onlyNumbers[1].trim()));
            tf.setLowSpanTopValue(Integer.parseInt(onlyNumbers[2].trim()));
            tf.setHighSpanBottomValue(Integer.parseInt(onlyNumbers[3].trim()));
            tf.setHighSpanTopValue(Integer.parseInt(onlyNumbers[4].trim()));
            ticketFields.add(tf);
        }
        return ticketFields;
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
