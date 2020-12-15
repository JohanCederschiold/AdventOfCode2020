package fourteen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Johan1 {

    private final static String file = "src/main/java/fourteen/test.txt";


    public static void main(String[] args) throws IOException {

        System.out.println(convertToBinaryString(10, 11));
        System.out.println(convertBinaryStringToInteger("1011"));
        //readMaskByMask();
        System.out.println(applyMaskToNumber("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X", 11));

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
                int value = Integer.parseInt(separateValueAndPosition[1].trim());

            }
        }

        System.out.println(currentMask);

    }

    private static int applyMaskToNumber (String mask, int number) {
        char [] binaryString = convertToBinaryString(mask.length(), number).toCharArray();
        char [] charMask = mask.toCharArray();

        for (int i = 0 ; i < charMask.length ; i++ ) {
            if (charMask[i] != 'X') {
                binaryString[i] = charMask[i];
            }
        }
        System.out.println(String.copyValueOf(binaryString));

        return convertBinaryStringToInteger(String.copyValueOf(binaryString));
    }



    private static String convertToBinaryString (int length, int number) {
        String binary = Integer.toBinaryString(number);
        String prefix = "";
        for (int i = binary.length()  ; i < length ; i++ ) {
            prefix += "0";
        }

        prefix += binary;

        return prefix;
    }

    private static int convertBinaryStringToInteger ( String binaryString) {
        return Integer.parseInt(binaryString, 2);
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
