import java.util.*;

public class MergeIntervals {

    static int[][] process(int[][] in) {

        List<int[]> result = new ArrayList<>();

        for (int i = 0; i<in.length; i++) {
            int start = in[i][0];
            int end = in[i][1];
            if (!result.isEmpty() && end <=  result.getLast()[1]) {
                // if this range end earlier that what we already have, ignore it.
                continue;
            }
            for (int j = i+1; j<in.length; j++) {
                if (in[j][0] <= end) {
                    end = Math.max(end, in[j][1]); // merge this in
                } else {
                    break; // don't merge this one, and exit this inner loop
                }
            }
            result.add(new int[]{start,end});
        }
        return result.stream().toArray(int[][]::new);
    }


    void main() {
        int[][] in = {
            {1,3},
            {2,9},
            {3,5},
            {8,10},
            {15,18}};
        int[][] expected = {
            {1,10},
            {15,18}};

        int[][] merged = process(in);

        IO.println("In    = " + Arrays.deepToString(in));
        IO.println("Expected = " + Arrays.deepToString(expected));
        IO.println("Merged   = " + Arrays.deepToString(merged));
        IO.println("Equal? " + Arrays.deepEquals(expected, merged));
    }
}
