package eight;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Johan1 {

    private final static String file = "src/main/java/eight/commands.txt";
    private static List<String> commands;
    private static Set<Integer> previousStack;


    public static void main(String[] args) throws IOException {

        previousStack = new HashSet<Integer>();
        commands = readAnswersToList();

        operation();

    }

    private static void operation () {
        int currentIndex = 0;
        int currentAcc = 0;
        boolean newToStack = true;


        while (newToStack) {
            newToStack = previousStack.add(currentIndex);

            if (newToStack) {
                String [] commandString = readCommand(commands.get(currentIndex));
                String command = commandString[0];
                int movement = getMovement(commandString[1]);
                System.out.printf("Do: %s  Move: %d  Acc = %d %n", command, movement, currentAcc);

                if (command.equals("jmp")) {
                    currentIndex += movement;
                } else if (command.equals("acc")) {
                    currentAcc += movement;
                    currentIndex++;
                } else {
                    currentIndex++;
                }
            }
        }

        System.out.println(currentAcc);


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

    private static String [] readCommand (String command) {
        return command.split("\\s");
    }

    private static int getMovement (String movement) {
        return movement.charAt(0) == '-' ?
                -1 * Integer.parseInt(movement.replaceAll("\\D", ""))
                :
                Integer.parseInt(movement.replaceAll("\\D", ""));
    }
}
