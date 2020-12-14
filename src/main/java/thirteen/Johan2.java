package thirteen;

public class Johan2 {

    private static String busses = "19,x,x,x,x,x,x,x,x,41,x,x,x,x,x,x,x,x,x,823,x,x,x,x,x,x,x,23,x,x,x,x,x,x,x,x,17,x,x,x,x,x,x,x,x,x,x,x,29,x,443,x,x,x,x,x,37,x,x,x,x,x,x,13";
    //private static String busses = "7,13,x,x,59,x,31,19";

    public static void main(String[] args) {

        //100 000 000 000 000

        int [] test = convertToIntegersArray(makeBusArray());
        int id = findBusWithHighestId(test);
        int index = findIndexOfHighestBus(id, test);
        findSeries(index, test);


    }

    private static void findSeries (int index, int [] test) {

        int lengthOfArray = test.length;

        for (int i = 0;  i < Integer.MAX_VALUE ; i += test[index]) {
            boolean straightFlush = true;
            //Looping upwards to check
            for (int upwards = index + 1 ; upwards < lengthOfArray ; upwards++) {
                if (test[upwards] != 0) {
                    if ((i + (upwards - index))% test[upwards] == 0) {
                        //System.out.printf("%d + %d / %d%n", i , upwards - index, test[upwards]);
                    } else {
                        straightFlush = false;
                    }
                }
                if (!straightFlush) {
                    break;
                }
            }
            //Looping downwards (only if previous loop completed)
            if (straightFlush) {
                for (int downwards = index -1 ; downwards >= 0; downwards--) {
                    //No need to check if id is 0
                    if (test[downwards] != 0) {
                        if ((i - (index - downwards )) % test[downwards] == 0 ) {
                            //System.out.println();
                        } else {
                            straightFlush = false;
                        }
                    }
                }
            }
            if (straightFlush) {
                System.out.println(i);
                break;
            }
        }

    }

    private static int findIndexOfHighestBus (int bussline, int [] busses) {
        for (int i = 0 ; i < busses.length ; i++ ) {
            if (busses[i] == bussline) {
                return i;
            }
        }
        return -1;
    }

    private static int findBusWithHighestId (int [] busses) {
        int highestId = 0;
        for (int s : busses) {
            highestId = Math.max(highestId, s);
        }
        return highestId;
    }

    private static String [] makeBusArray () {
        return busses.split(",");
    }

    private static int [] convertToIntegersArray (String [] busses) {
        int [] bussesArray = new int [busses.length];
        for (int i = 0 ; i < busses.length ; i++ ) {
            try {
                bussesArray[i] = Integer.parseInt(busses[i]);
            } catch (Exception e ) {
                bussesArray[i] = 0;
            }
        }
        return bussesArray;
    }

}
