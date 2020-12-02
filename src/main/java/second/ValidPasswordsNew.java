package second;

import java.io.*;

public class ValidPasswordsNew {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/java/second/codes.txt")));
        int counter = 0;

        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            Code code = separateDirectivesAndCode(line);
            if (isCodeOkayNewStandard(code)) {
                counter++;
            }
        }
        reader.close();
        System.out.println(counter);
    }

    private static boolean isCodeOkayNewStandard(Code codeToCheck) {
        return isLetterOnPostionEqualTo(codeToCheck, codeToCheck.getLowFigure())
               ==
               !isLetterOnPostionEqualTo(codeToCheck, codeToCheck.getHighFigure());
    }

    private static boolean isLetterOnPostionEqualTo(Code codeToCheck, Integer minimumAmount) {
        return codeToCheck
                .getCode()
                .substring(minimumAmount - 1, minimumAmount)
                .equals(codeToCheck.getLetter());
    }

    private static Code separateDirectivesAndCode (String line) {
        Code code = new Code();
        String [] components = line.split("\\s");
        String [] minAndMax = components[0].split("\\-");
        code.setLowFigure(Integer.parseInt(minAndMax[0]));
        code.setHighFigure(Integer.parseInt(minAndMax[1]));
        components[1] = components[1].replace(":", "");
        code.setLetter(components[1]);
        code.setCode(components[2]);
        return code;
    }

}

