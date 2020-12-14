package twelve;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Johan2 {

    private final static String file = "src/main/java/twelve/input.txt";
    private static int waypointVertical = 1;
    private static int waypointHorizontal = 10;
    private static int shipVertical = 0;
    private static int shipHorizontal = 0;


    public static void main(String[] args) throws IOException {
        List<String> commands = readAnswersToList();
        for (String command : commands) {
            readCommand(command);
        }

        System.out.printf("Vertical shipposition: %d Horizontal shiposition: %d %n and Manhattan Distance is: %d",
                shipVertical,
                shipHorizontal,
                getManhattanDistance(shipVertical, shipHorizontal)
        );
    }

    private static int getManhattanDistance (int vertical, int horizontal) {
        if (vertical < 0 ) {
            vertical *= -1;
        }
        if (horizontal < 0) {
            horizontal *= -1;
        }
        return vertical + horizontal;
    }

    private static void readCommand (String command) {
        char direction = command.charAt(0);
        int value = Integer.parseInt(command.replaceAll("[A-Z]", ""));

        switch (direction)  {
            case 'N':
                waypointVertical += value;
                break;
            case 'S':
                waypointVertical -= value;
                break;
            case 'E':
                waypointHorizontal += value;
                break;
            case 'W':
                waypointHorizontal -= value;
                break;
            case 'L':
                //heading = getNewHeading(heading, -1 * value);
                if (value == 90) {
                    int temp = waypointHorizontal;
                    waypointHorizontal = -1 * waypointVertical;
                    waypointVertical = temp;
                }
                if (value == 180) {
                    waypointHorizontal *= -1;
                    waypointVertical *= -1;
                }
                if (value == 270) {
                    int temp = waypointVertical;
                    waypointVertical = -1 * waypointHorizontal;
                    waypointHorizontal = temp;
                }
                break;
            case 'R':
                if (value == 90) {
                    int temp = waypointVertical;
                    waypointVertical = -1 * waypointHorizontal;
                    waypointHorizontal = temp;
                }
                if (value == 180) {
                    waypointHorizontal *= -1;
                    waypointVertical *= -1;
                }
                if (value == 270) {
                    int temp = waypointHorizontal;
                    waypointHorizontal = -1 * waypointVertical;
                    waypointVertical = temp;
                }
                break;
            case 'F':
                shipHorizontal += waypointHorizontal * value;
                shipVertical += waypointVertical * value;
                break;
        }
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

    private static int getNewHeading (int heading, int headingChange) {
        int newHeading = heading + headingChange;
        if (newHeading >= 0 && newHeading < 360 )  {
            return newHeading;
        } else {
            return newHeading < 0 ? newHeading + 360 : newHeading - 360;
        }

    }

}

