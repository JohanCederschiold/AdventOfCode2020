package fourteen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Johan2 {

    private final static String file = "src/main/java/fourteen/input.txt";
    private static Map<Long, Long> valueMemory;


    public static void main(String[] args) throws IOException {

        valueMemory = new HashMap<Long, Long>();
        readMaskByMask();
        System.out.println(calculateSum());


    }


    private static void readMaskByMask () throws IOException {
        List<String> commands = readAnswersToList();
        String currentMask = null;

        for (String command : commands ) {
            if (command.startsWith("mask")) {
                currentMask = command.replace("mask = ", "");
            } else {
                String [] separateValueAndPosition = command.split(" = ");
                int memoryPosition = Integer.parseInt(separateValueAndPosition[0].replaceAll("[^0-9]", ""));
                long value = Long.parseLong(separateValueAndPosition[1].trim());
                List<Long> listOfMemoryPositions = convertListOfBinariesToLongs(getBinaryMemoryPositionsBasedOnMaskAndNumber(currentMask, memoryPosition));
                for(Long l : listOfMemoryPositions) {
                    valueMemory.put(l, value);
                }
            }
        }
    }

    private static long calculateSum () {
        long sum = 0;
        for (Map.Entry<Long, Long> entry : valueMemory.entrySet()) {
            sum += entry.getValue();
        }
        return sum;
    }

    private static List<String> getBinaryMemoryPositionsBasedOnMaskAndNumber(String mask, long number) {
        char [] binaryString = convertToBinaryString(mask.length(), number).toCharArray();
        char [] charMask = mask.toCharArray();

        for (int i = 0 ; i < charMask.length ; i++ ) {
            if (charMask[i] == '1') {
                binaryString[i] = '1';
            } else if (charMask[i] == 'X') {
                binaryString[i] = 'X';
            }
        }
        List<String> binaryvariants = getAllVariants(charMask);
        List<String> allVariants = replaceXsWithFloats(binaryString, binaryvariants);
        return allVariants;
    }

    private static List<Long> convertListOfBinariesToLongs (List<String> listOfBinaries) {
        List<Long> longs = new ArrayList<Long>();
        for(String s : listOfBinaries) {
            longs.add(convertBinaryStringToLong(s));
        }
        return longs;
    }


    private static List<String> replaceXsWithFloats(char[] binaryString, List<String> binaryvariants) {

        List<String> binaryAlternatives = new ArrayList<String>();

        for (String variant : binaryvariants) {
            int counter = 0;
            String binary = "";
            for (int i = 0 ; i < binaryString.length ; i++ ) {
                if (binaryString[i] != 'X') {
                    binary += binaryString[i];
                } else {
                    binary += variant.charAt(counter);
                    counter++;
                }
            }
            binaryAlternatives.add(binary);
        }
        return binaryAlternatives;
    }

    private static List<String> getAllVariants(char[] binaryString) {
        List<String> variants = new ArrayList<String>();
        int noXs = String.copyValueOf(binaryString).replaceAll("[0-1]", "").length();
        int noVariants = (int) Math.pow(2, noXs);
        for (int i = 0 ; i < noVariants ; i++ ) {
            String binary = Integer.toBinaryString(i);
            String prefix = "";
            for (int k = 0; k < (noXs - binary.length()) ; k++ ) {
                prefix += 0;
            }

            variants.add(prefix + binary);
        }
        return variants;
    }


    private static String convertToBinaryString (int length, long number) {
        String binary = Long.toBinaryString(number);
        String prefix = "";
        for (int i = binary.length()  ; i < length ; i++ ) {
            prefix += "0";
        }

        prefix += binary;

        return prefix;
    }

    private static long convertBinaryStringToLong ( String binaryString) {
        return Long.parseLong(binaryString, 2);
    }


    private static List<String> readAnswersToList() throws IOException {
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
