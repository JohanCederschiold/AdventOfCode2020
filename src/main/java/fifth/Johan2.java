package fifth;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Johan2 {

    private static Set<Integer> seating;
    private final static String file = "src/main/java/fifth/boarding_passes.txt";
    private final static int numberOfRows = 128;
    private final static int numberOfSeatsPerRow = 8;


    public static void main(String[] args) throws IOException {
        seating = new HashSet<Integer>();
        findVacantSeats();
    }

    private static void findVacantSeats () throws IOException {
        fillSeats();
        List<Integer> freeSeats = new ArrayList<Integer>();
        for (int row = 0 ; row < numberOfRows ; row++ ) {
            for (int seat = 0 ; seat < numberOfSeatsPerRow ; seat++ ) {
                if (fillseat(row, seat)) {
                    freeSeats.add(calculateId(row, seat));
                }
            }
        }

        System.out.println(findFirstIsolatedSeat(freeSeats));

    }

    private static int findFirstIsolatedSeat (List<Integer> seatIds) {

        for (int i = 0; i < seatIds.size() -2 ; i++ ) {
            if (seatIds.get( i + 1 ) - seatIds.get( i ) != 1 &&
                seatIds.get( i + 2 ) - seatIds.get( i +1 ) != 1) {
                return seatIds.get(i + 1);
            }
        }
        return 0;

    }


    private static void fillSeats() throws IOException {
        List<String> boardingPasses = readBoardinPassesToList();

        for (String boardingPass : boardingPasses) {
            String [] rowsAndSeats = divideRowsFromSeats(boardingPass);
            int rowNo = getRow(rowsAndSeats[0]);
            int seatNo = getSeat(rowsAndSeats[1]);
            if(!fillseat(rowNo,seatNo)) {
                throw new IllegalArgumentException("Seat is not vacant");
            }
        }
    }

    private static int calculateId (int row, int seat) {
        return row * 8 + seat;
    }

    private static String [] divideRowsFromSeats (String boardingPass) {
        String [] divided = {boardingPass.substring(0, 7), boardingPass.substring(7)};
        return divided;
    }

    private static int getSeat (String binarySeat) {

        char [] binarySeatLetterByLetter = binarySeat.toCharArray();
        int firstRow = 0;
        int lastRow = numberOfSeatsPerRow;

        int difference = numberOfSeatsPerRow;
        int counter = 0;

        while (difference > 1) {

            if (binarySeatLetterByLetter[counter] == 'R') {
                firstRow += difference / 2;
            } else {
                lastRow -= difference / 2;
            }
            counter++;
            difference = lastRow - firstRow;
        }
        return lastRow - 1;
    }

    private static int getRow (String binaryRow ) {

        char [] binaryRowLetterByLetter = binaryRow.toCharArray();
        int firstRow = 0;
        int lastRow = numberOfRows;

        int difference = 128;
        int counter = 0;

        while (difference > 1) {

            if (binaryRowLetterByLetter[counter] == 'B') {
                firstRow += difference / 2;
            } else {
                lastRow -= difference / 2;
            }
            counter++;
            difference = lastRow - firstRow;
        }
        return lastRow - 1;
    }

    private static boolean fillseat (int row, int seat) {
        return seating.add(calculateId(row, seat));
    }

    private static List<String> readBoardinPassesToList () throws IOException {
        List<String> boardingPasses = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new FileReader(new File(file)));

        String line = reader.readLine();

        while (line != null ) {
            boardingPasses.add(line);
            line = reader.readLine();
        }

        reader.close();

        return boardingPasses;
    }

}


