package fourth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Johan1 {

    //private static String [] requiredFields = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid"};
    private static String [] requiredFields = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};

    public static void main(String[] args) throws IOException {

        List<String> result = readFileToList();
        List<String> passports = groupByPassport(result);
        System.out.println(passports.size());
        System.out.println(countValidPassports(passports));

    }

    private static int countValidPassports (List<String> passports) {
        int counter = 0;
        for(String passport : passports ) {
            if(isValid(passport)) {
                counter++;
            }
        }
        return counter;
    }

    private static boolean isValid(String passport) {
        for (String field : requiredFields) {
            if (!passport.contains(field)) {
                return false;
            }
        }
        return true;
    }

    private static List<String> readFileToList() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/main/java/fourth/passport.txt")));
        List<String>passports = new ArrayList<String>();
        String line = "";

        while( line != null ) {
            line = reader.readLine();
            if (line != null ) {
                passports.add(line);
            }
        }
        return passports;
    }

    private static List<String> groupByPassport (List<String> passportData) {
        List<String> groupedByPassport = new ArrayList<String>();
        String passport = "";
        for(int i = 0 ; i < passportData.size() ; i++ ) {
            if (passportData.get(i).contains(":")) {
                passport += passportData.get(i) + " ";
            } else {
                groupedByPassport.add(passport);
                passport = "";
            }
        }
        groupedByPassport.add(passport);
        return groupedByPassport;
    }
}
