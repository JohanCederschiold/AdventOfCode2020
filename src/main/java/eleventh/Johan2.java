package eleventh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Johan2 {

    private final static String file = "src/main/java/eleventh/input.txt";
    private final static char FREE = 'L';
    private final static char FLOOR = '.';
    private final static char OCCUPIED = '#';
    //private final static int adjacentLimit = 4;

    static char [][] seatingPlan;

    public static void main(String[] args) throws IOException {
        seatingPlan = convertToDoubleArray(readAnswersToList());

        int previousCount = 0;

        while (true) {
            changeSeats();
            int filledSeats = countOccupiedSeats();
            if (filledSeats == previousCount) {
                break;
            }
            previousCount = filledSeats;
        }
        System.out.println(previousCount);
    }

    private static void changeSeats() throws IOException {

        char [][] temporarySeating = convertToDoubleArray(readAnswersToList());

        for (int row = 0 ; row < seatingPlan.length ; row++) {
            for (int seat = 0 ; seat < seatingPlan[row].length ; seat++ ) {
                if (seatingPlan[row][seat] != FLOOR) {
                    int occupiedSeats;
                    if (seatingPlan[row][seat] == FREE) {
                        occupiedSeats = numberAdjacentOccupiedSeats(row, seat);
                        if (occupiedSeats == 0 ){
                            temporarySeating[row][seat] = OCCUPIED;
                        } else {
                            temporarySeating[row][seat] = FREE;
                        }
                    } else {
                        occupiedSeats = numberAdjacentOccupiedSeats(row, seat);
                        if (occupiedSeats > 4 ) {
                            temporarySeating[row][seat] = FREE;
                        } else {
                            temporarySeating[row][seat] = OCCUPIED;
                        }
                    }
                }
            }
        }
        seatingPlan = temporarySeating;
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

    private static int numberAdjacentOccupiedSeats (int row, int seat) {
        int counter=0;

        if (occupiedSeatsNorth(row, seat)){
            counter++;
        }
        if (occupiedSeatsNorthEast(row, seat)){
            counter++;
        }
        if (occupiedSeatsEast(row, seat)){
            counter++;
        }
        if (occupiedSeatsSouthEast(row, seat)){
            counter++;
        }
        if (occupiedSeatsSouth(row, seat)){
            counter++;
        }
        if (occupiedSeatsSouthWest(row, seat)){
            counter++;
        }
        if (occupiedSeatsWest(row, seat)){
            counter++;
        }
        if (occupiedSeatsNorthWest(row, seat)){
            counter++;
        }
        return counter;
    }

    //Should break for floor

    private static boolean occupiedSeatsNorth (int row, int seat) {
        if (row < 1 ) {
            return false;
        }
        while (row != 0 ) {
            row--;
            if (seatingPlan[row][seat] == OCCUPIED) {
                return true;
            } else if ( seatingPlan[row][seat] == FREE) {
                return false;
            }
        }
        return false;
    }

    private static boolean occupiedSeatsSouth (int row, int seat) {
        if (row > seatingPlan.length - 1 ) {
            return false;
        }
        while (row != seatingPlan.length -1) {
            row++;
            if (seatingPlan[row][seat] == OCCUPIED) {
                return true;
            } else if ( seatingPlan[row][seat] == FREE) {
                return false;
            }
        }
        return false;
    }

    private static boolean occupiedSeatsWest(int row, int seat) {
        if (seat < 1 ) {
            return false;
        }
        while (seat != 0) {
            seat--;
            if(seatingPlan[row][seat] == OCCUPIED) {
                return true;
            } else if ( seatingPlan[row][seat] == FREE) {
                return false;
            }
        }
        return false;
    }

    private static boolean occupiedSeatsEast(int row, int seat) {
        if (seat > seatingPlan[row].length - 1 ) {
            return false;
        }
        while (seat != seatingPlan[row].length - 1) {
            seat++;
            if(seatingPlan[row][seat] == OCCUPIED) {
                return true;
            } else if ( seatingPlan[row][seat] == FREE) {
                return false;
            }
        }
        return false;
    }

    private static boolean occupiedSeatsNorthEast(int row, int seat) {
        if (row < 1  || seat > seatingPlan[row].length - 1) {
            return false;
        }
        while (row != 0 && seat != seatingPlan[row].length - 1) {
            row--;
            seat++;
            if (seatingPlan[row][seat] == OCCUPIED) {
                return true;
            } else if ( seatingPlan[row][seat] == FREE) {
                return false;
            }
        }
        return false;
    }

    private static boolean occupiedSeatsSouthEast(int row, int seat) {
        if (row > seatingPlan.length - 1 || seat > seatingPlan[row].length - 1) {
            return false;
        }
        while (row != seatingPlan.length - 1  && seat != seatingPlan[row].length - 1) {
            row++;
            seat++;
            if (seatingPlan[row][seat] == OCCUPIED) {
                return true;
            } else if ( seatingPlan[row][seat] == FREE) {
                return false;
            }
        }
        return false;
    }

    private static boolean occupiedSeatsSouthWest(int row, int seat) {
        if (row > seatingPlan.length - 1 || seat < 1) {
            return false;
        }
        while (row != seatingPlan.length - 1  && seat != 0) {
            row++;
            seat--;
            if (seatingPlan[row][seat] == OCCUPIED) {
                return true;
            } else if ( seatingPlan[row][seat] == FREE) {
                return false;
            }
        }
        return false;
    }

    private static boolean occupiedSeatsNorthWest(int row, int seat) {
        if (row < 1 || seat < 1) {
            return false;
        }
        while (row != 0  && seat != 0) {
            row--;
            seat--;
            if (seatingPlan[row][seat] == OCCUPIED) {
                return true;
            } else if ( seatingPlan[row][seat] == FREE) {
                return false;
            }
        }
        return false;
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
