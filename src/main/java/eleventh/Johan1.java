package eleventh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Johan1 {

    private final static String file = "src/main/java/eleventh/input.txt";
    private final static char FREE = 'L';
    private final static char FLOOR = '.';
    private final static char OCCUPIED = '#';
    private final static int adjacentLimit = 3;

    static char [][] seatingPlan;

    public static void main(String[] args) throws IOException {
        seatingPlan = convertToDoubleArray(readAnswersToList());

        /*
        makeSeatChange();
        makeSeatChange();
        makeSeatChange();
         */



        int previousCount = 0;

        while (true) {
            makeSeatChange();
            int filledSeats = countOccupiedSeats();
            if (filledSeats == previousCount) {
                break;
            }
            previousCount = filledSeats;
        }

        System.out.println(previousCount);

    }

    private static void printSeating() {
        for (int i = 0 ; i < seatingPlan.length ; i++) {
            for (char c : seatingPlan[i]) {
                System.out.print(c);
            }
            System.out.println("");
        }
    }

    private static int countOccupiedSeats () {
        int counter = 0;
        for (int i = 0 ; i < seatingPlan.length ; i++ ) {
            for (int j = 0 ; j < seatingPlan[i].length ; j++) {
                if(seatingPlan[i][j] == '#') {
                    counter++;
                }
            }
        }
        return counter;
    }

    private static void makeSeatChange () throws IOException {
        char [][] temporarySeatingPlan = convertToDoubleArray(readAnswersToList());
        for (int row = 0 ; row < seatingPlan.length ; row++ ) {
            for (int seat = 0 ; seat < seatingPlan[row].length ; seat++) {
                if (seatingPlan[row][seat] != FLOOR) {
                    if (seatingPlan[row][seat] == FREE) {
                        emptySeat(row, seat, temporarySeatingPlan);
                        /*if (checkAdjacentSeat(row, seat, 0)) {
                            seatingPlan[row][seat] = OCCUPIED;
                        }*/
                    } else {
                        filledSeat(row, seat, temporarySeatingPlan);
                        /*
                        if (checkAdjacentSeat(row, seat, 4)) {
                            seatingPlan[row][seat] = FREE;
                        }*/
                    }
                }
            }
        }
        seatingPlan = temporarySeatingPlan;
    }

    private static void emptySeat (int row, int seat, char [][] newSeatingPlan) {
        if (checkAdjacentSeat(row, seat, 0)) {
            //occupySeat
            newSeatingPlan[row][seat] = FREE;
        } else {
            newSeatingPlan[row][seat] = OCCUPIED;
        }
    }

    private static void filledSeat (int row, int seat, char [][] newSeatingPlan) {
        if (checkAdjacentSeat(row, seat, adjacentLimit)) {
            //freeUpSeat
            newSeatingPlan[row][seat] = FREE;
        } else {
            newSeatingPlan[row][seat] = OCCUPIED;
        }
    }

    private static boolean checkAdjacentSeat(int row, int seat, int limit) {
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
        return adjacentOccupiedSeats > limit;
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
