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
    private static Map<Integer, Long> valueMemory;
    private static String bigValue = "000100011010101100101001010011110000";


    public static void main(String[] args) throws IOException {

        valueMemory = new HashMap<Integer, Long>();
        //long v1 = applyMaskToNumber("0001101XX011X0X10X10000X010XX111000X", 716205);
        //long v2 = applyMaskToNumber("1X101010X01101110000001110X11XXX0100", 58824493);
        //getAllVariants("1X101010X01101110000001110X11XXX0100".toCharArray());
        applyMaskToNumber("1X101010X01101110000001110X11XXX0100", 32);

        //readMaskByMask();


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
                valueMemory.put(memoryPosition, applyMaskToNumber(currentMask, value));
            }
        }
        //printMap();
        System.out.println(calculateSum());
    }


    private static void printMap () {
        for (Map.Entry<Integer, Long> entry : valueMemory.entrySet()) {
            System.out.printf("Memoryposition(%d): %d%n", entry.getKey(), entry.getValue());
        }
    }

    private static long calculateSum () {
        long sum = 0;
        for (Map.Entry<Integer, Long> entry : valueMemory.entrySet()) {
            sum += entry.getValue();
        }
        return sum;
    }

    private static long applyMaskToNumber (String mask, long number) {
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
        System.out.println(binaryvariants.size());
        for(String s : binaryvariants) {
            System.out.println(s);
        }

        //Make method exchanging Xs for 1s or 0s, save to list
        List<String> allVariants = replaceXsWithFloats(binaryString, binaryvariants);

        return convertBinaryStringToLong(String.copyValueOf(binaryString));
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
        System.out.println(noVariants);
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
