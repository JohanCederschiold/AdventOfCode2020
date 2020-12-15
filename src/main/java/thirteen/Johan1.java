package thirteen;

public class Johan1 {

    private static int timestamp = 1005162;
    private static int [] testtable = {19,41,823,23,17,29,443,37,13};

    public static void main(String[] args) {
        System.out.println(calculateValue());
    }

    private static int calculateValue () {
        int busId = 0;
        int waitingtime = 10000;
        for (int i = 0 ; i < testtable.length ; i++) {
            int waitingTimeCurrentBus = earliestTimestamp(testtable[i]) - timestamp;
            if (waitingTimeCurrentBus < waitingtime) {
                waitingtime = waitingTimeCurrentBus;
                busId = testtable[i];
            }
        }
        return busId * waitingtime;
    }

    private static int earliestTimestamp(int intervall) {
        return (timestamp / intervall) * intervall + intervall;
    }

}
