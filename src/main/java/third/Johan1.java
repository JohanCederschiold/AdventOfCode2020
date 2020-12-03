package third;

import second.Code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Johan1 {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/java/third/Trees.txt")));
        String line = reader.readLine();
        int rowLength = line.length();
        int position = 0;
        int treeCounter = 0;


        while (true) {
            if (isTree(line.charAt(position))) {
                treeCounter++;
            }
            position += 3;
            if(position >= rowLength) {
                position -= rowLength;
            }

            line = reader.readLine();
            if(line == null) {
                break;
            }
        }

        System.out.println(treeCounter);
        reader.close();

    }

    private static boolean isTree(char koordinate) {
        return koordinate == '.' ? false : true;
    }


}
