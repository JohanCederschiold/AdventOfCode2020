package third;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Johan2 {

    public static void main(String[] args) throws IOException {

        List<String> rowsOfTrees = createList();
        int [] horizontalMove   = {1,3,5,7,1};
        int [] verticalMove     = {1,1,1,1,2};
        int rowWidth = rowsOfTrees.get(0).length();
        long theSum = 0;

        for (int i = 0 ; i < horizontalMove.length ; i++ ) {
        int accumulatedTrees = 0;
            int verticalPosition = 0;
            for(int j = 0 ; j < rowsOfTrees.size() ; j += verticalMove[i]) {
                if(isTree(rowsOfTrees.get(j).charAt(verticalPosition))){
                    accumulatedTrees++;
                }
                verticalPosition += horizontalMove[i];
                if (verticalPosition >= rowWidth) {
                    verticalPosition -= rowWidth;
                }
            }
            if(theSum < 1L ) {
                theSum = accumulatedTrees;
            } else {
                theSum = theSum * accumulatedTrees;
            }
        }
        System.out.println(theSum);
    }

    private static List<String> createList () throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/java/third/Trees.txt")));
        List<String>treeRows = new ArrayList<String>();
        String line = reader.readLine();
        while(line != null) {
            treeRows.add(line);
            line = reader.readLine();
        }
        reader.close();
        return treeRows;
    }

    private static boolean isTree(char coordinate) {
        return coordinate == '.' ? false : true;
    }
}
