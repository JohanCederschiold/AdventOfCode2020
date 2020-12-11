package eleventh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Johan1 {

    private final static String file = "src/main/java/eleventh/test.txt";
    private final static char FREE = 'L';
    private final static char FLOOR = '.';
    private final static char OCCUPIED = '#';
    private final static int adjacentLimit = 4;

    static char [][] seatingPlan;

    public static void main(String[] args) throws IOException {
        seatingPlan = convertToDoubleArray(readAnswersToList());
//        System.out.println(seatingPlan.length);
//        System.out.println(seatingPlan[0].length);
//        checkAdjacentSeatFree(1,1);
        for (int row = 0 ; row < seatingPlan.length ; row++ ) {
            for (int seat = 0 ; seat < seatingPlan[row].length ; seat++ ) {
                if (seatingPlan[row][seat] != FLOOR && !checkAdjacentSeatFree(row, seat)) {
                    seatingPlan[row][seat] = OCCUPIED;
                }
            }
        }

        for (int i = 0 ; i < seatingPlan.length ; i++) {
            for (char c : seatingPlan[i]) {
                System.out.print(c);
            }
            System.out.println("");
        }

    }

    private static boolean checkAdjacentSeatFree (int row, int seat) {
        int adjacentOccupiedSeats = 0;

        //upper left
        if (row > 0 &&  seat > 0 && seatingPlan[row-1][seat-1] == OCCUPIED) {
            adjacentOccupiedSeats++;
        }
        //upper middle
        if (row > 0 && seatingPlan[row-1][seat] == OCCUPIED) {
            adjacentOccupiedSeats++;
        }
        //upper right
        if (row > 0 &&  seat < seatingPlan[row].length - 1  && seatingPlan[row-1][seat+1] == OCCUPIED) {
            adjacentOccupiedSeats++;
        }
        //left side
        if (seat > 0  && seatingPlan[row][seat-1] == OCCUPIED) {
            adjacentOccupiedSeats++;
        }
        //right side
        if ( seat < seatingPlan[row].length -1  && seatingPlan[row][seat+1] == OCCUPIED) {
            adjacentOccupiedSeats++;
        }
        //lower left
        if (row < seatingPlan.length - 1 &&  seat > 0 && seatingPlan[row+1][seat-1] == OCCUPIED) {
            adjacentOccupiedSeats++;
        }
        //lower middle
        if (row < seatingPlan.length - 1 && seatingPlan[row+1][seat] == OCCUPIED) {
            adjacentOccupiedSeats++;
        }
        //lower right
        if (row < seatingPlan.length - 1 && seat < seatingPlan[row].length - 1  && seatingPlan[row+1][seat+1] == OCCUPIED) {
            adjacentOccupiedSeats++;
        }

        return adjacentOccupiedSeats >= adjacentLimit;
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

    private static char [][] convertToDoubleArray (List<String> listToConvert) {
        char [][] mappedSeats = new char[listToConvert.size()][listToConvert.get(0).length()];

        for (int i = 0 ; i < listToConvert.size() ; i++ ) {
            char [] seatsRow = listToConvert.get(i).toCharArray();
            mappedSeats[i] = seatsRow;
        }
        return mappedSeats;
    }

}
