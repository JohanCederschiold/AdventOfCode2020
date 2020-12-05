package fifth;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Johan1 {

    private final static String file = "src/main/java/fifth/boarding_passes.txt";
    private final static int numberOfRows = 128;
    private final static int numberOfSeatsPerRow = 8;

    public static void main(String[] args) throws IOException {

        System.out.println(findHighestId());

    }

    private static int findHighestId () throws IOException {

        List<String> boardingPasses = readBoardinPassesToList();
        int highestId = 0;

        for (String boardingPass : boardingPasses) {
            String [] rowsAndSeats = divideRowsFromSeats(boardingPass);
            int rowNo = getRow(rowsAndSeats[0]);
            int seatNo = getSeat(rowsAndSeats[1]);
            int currentId = calculateSeatId(rowNo, seatNo);
            highestId = Math.max(highestId, currentId);
        }
        return highestId;
    }

    private static String [] divideRowsFromSeats (String boardingPass) {
        String [] divided = {boardingPass.substring(0, 7), boardingPass.substring(7, boardingPass.length())};
        return divided;
    }

    private static int calculateSeatId (int rowNo, int seatNo) {
        return rowNo * 8 + 5;
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
