package fifteen;

import java.util.HashMap;
import java.util.Map;

public class Johan2 {

    private static long [] sequence = {2,1,10,11,0,6};
    private static long [] testSequence = {3,2,1};
    private static long noRounds = 30000000;
    private static Map<Long, Long> previouslySpoken;
    private static long latestNumber;


    public static void main(String[] args) {

        previouslySpoken = new HashMap<Long, Long>();
        playGame(sequence);
    }

    private static void playGame (long [] sequence)  {
        instantiateSequence(sequence);
        for (long round = sequence.length + 1 ; round <= noRounds ; round ++  ) {
            long indexLastSpoken = checkIfPreviouslySpoken(latestNumber);
            previouslySpoken.put(latestNumber, round - 1);
            if (indexLastSpoken == -1 ) {
                latestNumber = 0;
            } else {
                latestNumber = round - indexLastSpoken -1 ;
            }
        }
        System.out.println(latestNumber);
    }

    private static void instantiateSequence(long[] sequence) {
        for (long round = 1 ; round < sequence.length ; round++ ){
            previouslySpoken.put(sequence[(int) round - 1], round);
        }
        latestNumber = sequence[sequence.length - 1];
    }

    private static long checkIfPreviouslySpoken (long numberToCheck) {
        long index;
        try {
            index = previouslySpoken.get(numberToCheck);
        } catch (NullPointerException e) {
            index = -1;
        }
        return index;
    }
}
