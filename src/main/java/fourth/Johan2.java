package fourth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Johan2 {

    private static String [] requiredFields = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
    static String testpassport = "byr:2010 pid:#1bb4d8 eyr:2021 hgt:186cm iyr:2020 ecl:grt";

    public static void main(String[] args) throws IOException {

        List<String> result = readFileToList();
        List<String> passports = groupByPassport(result);
        for(String test : breakDownToFields(testpassport)) {
            System.out.println(test);
        }
        System.out.println(validPid("021572400"));

    }

    private static String [] breakDownToFields (String passportString) {
        return passportString.split("\\s");
    }

    private static List<String> filterInitialValidPassports (List<String> passports) {
        List<String> firstScreening = new ArrayList<String>();
        for(String passport : passports ) {
            if(isValid(passport)) {
                firstScreening.add(passport);
            }
        }
        return firstScreening;
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

    private static boolean validByr (String byr) {
        int year = Integer.parseInt(byr.replace("byr:", ""));
        return year >= 1920 && year <= 2002;
    }

    private static boolean validIyr (String iyr) {
        int year = Integer.parseInt(iyr.replace("iyr:", ""));
        return year >= 2010 && year <= 2020;
    }

    private static boolean validEyr (String eyr) {
        int year = Integer.parseInt(eyr.replace("eyr:", ""));
        return year >= 2020 && year <= 2030;
    }

    private static boolean validHgt (String hgt) {
        String onlyHeight = hgt.replace("hgt:", "");
        int measurement = Integer.parseInt(onlyHeight.replaceAll("\\D", ""));
        String unit = onlyHeight.replaceAll("[0-9]", "");

        if(unit.equals("cm")) {
            return measurement >= 150 && measurement <= 193;
        } else {
            return measurement >= 59 && measurement <= 76;
        }
    }

    private static boolean validHcl (String hcl) {
        String onlyHaircolor = hcl.replace("hcl:", "");
        if(hcl.length() != 7) {
            return false;
        }
        if(hcl.charAt(0) != '#') {
            return false;
        }
        String filterNumbers = onlyHaircolor.replaceAll("\\d", "");
        String filterLetters = filterNumbers.replaceAll("[a-f]", "");
        return filterLetters.equals("#");
    }

    private static boolean validEcl (String ecl) {
        String onlyEyecolor = ecl.replace("ecl:", "");
        String [] validColors = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
        for (String eyeColor : validColors) {
            if(eyeColor.equals(onlyEyecolor)) {
                return true;
            }
        }
        return false;
    }

    private static boolean validPid (String pid) {
        String passportNumber = pid.replace("pid:", "");
        if(passportNumber.length() != 9) {
            return false;
        }
        if(passportNumber.charAt(0) != '0') {
            return false;
        }
        String allButDigits = passportNumber.replaceAll("[0-9]", "");
        return allButDigits.length() < 1;

    }



}
