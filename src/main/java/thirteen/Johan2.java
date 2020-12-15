package thirteen;

public class Johan2 {

    //private static String busses = "19,x,x,x,x,x,x,x,x,41,x,x,x,x,x,x,x,x,x,823,x,x,x,x,x,x,x,23,x,x,x,x,x,x,x,x,17,x,x,x,x,x,x,x,x,x,x,x,29,x,443,x,x,x,x,x,37,x,x,x,x,x,x,13";
    private static String busses = "7,13,x,x,59,x,31,19";


    public static void main(String[] args) {

        /**
         *  Execution of the actual scenario takes a really long time
         *  If you really want to try it you can change it by commenting in the "real" data.
         */

        //1012814086400000
        //1058443396696855

        long [] test = convertToLongArray(makeBusArray());
        int id = findBusWithHighestId(test);
        int index = findIndexOfHighestBus(id, test);
        findSeries(index, test);


    }

    private static void findSeriesWithBigInteger () {

    }

    private static void findSeries (int index, long [] test) {

        int lengthOfArray = test.length;

        for (long i = 0;  i < Long.MAX_VALUE ; i += test[index]) {
            if (i % 100000 == 0) {
                System.out.println(i);
            }
            boolean straightFlush = true;
            //Looping upwards to check
            for (int upwards = index + 1 ; upwards < lengthOfArray ; upwards++) {
                if (test[upwards] != 0) {
                    if ((i + (upwards - index))% test[upwards] != 0) {
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
                        if ((i - (index - downwards )) % test[downwards] != 0 ) {
                            straightFlush = false;
                        }
                    }
                }
            }
            if (straightFlush) {
                System.out.println(i - index);
                break;
            }
        }

    }

    private static int findIndexOfHighestBus (int bussline, long [] busses) {
        for (int i = 0 ; i < busses.length ; i++ ) {
            if (busses[i] == bussline) {
                return i;
            }
        }
        return -1;
    }

    private static int findBusWithHighestId (long [] busses) {
        int highestId = 0;
        for (long s : busses) {
            highestId = Math.max( (int) highestId, (int) s);
        }
        return highestId;
    }

    private static String [] makeBusArray () {
        return busses.split(",");
    }

    private static long [] convertToLongArray(String [] busses) {
        long [] bussesArray = new long [busses.length];
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
