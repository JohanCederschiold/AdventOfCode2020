package eight;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Johan2 {


    private final static String file = "src/main/java/eight/commands.txt";
    private static List<String> commands;
    private static Set<Integer> previousStack;


    public static void main(String[] args) throws IOException {

        commands = readAnswersToList();
        int answer = 0;

        for (int i = 0 ; i < commands.size() ; i++) {
            if(checkIfCommandIsJmpOrNop(commands.get(i))) {
                previousStack = new HashSet<Integer>();
                answer = operation(i);
            }
            if (answer > 0 ) {
                break;
            }
        }
        System.out.println(answer);
    }

    private static boolean checkIfCommandIsJmpOrNop (String cmd) {
        return cmd.startsWith("jmp") || cmd.startsWith("nop");
    }

    private static int operation (int toggleCommandNo) {
        int currentIndex = 0;
        int currentAcc = 0;
        boolean newToStack = true;


        while (newToStack ) {
            newToStack = previousStack.add(currentIndex);

            try {
                if (newToStack ) {
                    String [] commandString = readCommand(commands.get(currentIndex));
                    String command = commandString[0];

                    if (currentIndex == toggleCommandNo) {
                        command = command.equals("jmp") ? "nop" : "jmp";
                    }

                    int movement = getMovement(commandString[1]);

                    if (command.equals("jmp")) {
                        currentIndex += movement;
                    } else if (command.equals("acc")) {
                        currentAcc += movement;
                        currentIndex++;
                    } else {
                        currentIndex++;
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                return currentAcc;
            }
        }
        return 0;
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
