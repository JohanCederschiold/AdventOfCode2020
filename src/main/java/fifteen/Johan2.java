package fifteen;

import java.util.HashMap;
import java.util.Map;

public class Johan2 {

    private static long [] sequence = {2,1,10,11,0,6};
    private static long [] testSequence = {0,3,6};
    private static long noRounds = 2020;
    private static Map<Long, Long> previouslySpoken;


    public static void main(String[] args) {
        playGame(testSequence);
    }

    private static void playGame (long [] sequence)  {
        previouslySpoken = new HashMap<Long, Long>();
        instantiateSequence(sequence);
        long latestNumber = sequence[sequence.length - 1];
        for (long round = sequence.length + 1 ; round <= noRounds ; round ++  ) {
            if (previouslySpoken.containsKey(latestNumber)) {

            }
        }
    }

    private static void instantiateSequence(long[] sequence) {
        for (long round = 1 ; round <= sequence.length ; round++ ){
            previouslySpoken.put(sequence[(int) round - 1], round);
        }
    }

}
