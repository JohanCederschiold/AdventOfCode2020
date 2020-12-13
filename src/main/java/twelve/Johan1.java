package twelve;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Johan1 {

    private final static String file = "src/main/java/twelve/input.txt";
    private static int heading = 90;
    private static int verticalDirection = 0;
    private static int horizontalDirection = 0;


    public static void main(String[] args) throws IOException {
        List<String> commands = readAnswersToList();
        for(String command : commands) {
            readCommand(command);
        }
        System.out.println(getManhattanDistance(verticalDirection, horizontalDirection));


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
        //System.out.println("Direction: " + direction + " Value: " + value);

        switch (direction)  {
            case 'N':
              verticalDirection += value;
              break;
            case 'S':
              verticalDirection -= value;
              break;
            case 'E':
              horizontalDirection += value;
              break;
            case 'W':
              horizontalDirection -= value;
              break;
            case 'L':
              heading = getNewHeading(heading, -1 * value);
              break;
            case 'R':
              heading = getNewHeading(heading, value);
              break;
            case 'F':
              if (heading == 90) {
                  horizontalDirection += value;
              } else if (heading == 180) {
                  verticalDirection -= value;
              } else if (heading == 270) {
                  horizontalDirection -= value;
              } else if (heading == 0) {
                  verticalDirection += value;
              }
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

