package fifteen;

public class Johan1 {

    private static long [] sequence = {2,1,10,11,0,6};
    private static long [] testSequence = {2,1,3};
    private static int roundToCheck = 2020;
    private static long [] rounds;


    public static void main(String[] args) {
        rounds = new long [roundToCheck];
        playGame(sequence);
        System.out.println(rounds[rounds.length-1]);
    }

    private static void playGame (long [] sequence)  {
        setInitialNumbers(sequence);
        for (int round = sequence.length ; round < rounds.length ; round++ ) {
            long previousIndexOfLastEntry = getPreviousIndex(round - 2, rounds[round - 1]);
            //System.out.printf("No: %d was last spoken round %d%n", rounds[round-1], previousIndexOfLastEntry);
            if (previousIndexOfLastEntry != -1 ) {
                rounds[round] = round - previousIndexOfLastEntry;
            }
            // System.out.println(rounds[round]);
        }
    }

    private static void setInitialNumbers (long [] sequence) {
        for (int i = 0 ; i < sequence.length ; i++ ) {
            rounds[i] = sequence[i];
        }
    }

    private static int getPreviousIndex(int currentIndex, long checkfor) {
        for (int i = currentIndex ; i >= 0 ; i--) {
            if (rounds[i] == checkfor) {
                return i + 1;
            }
        }
        return -1;
    }
}

