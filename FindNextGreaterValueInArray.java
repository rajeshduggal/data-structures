import java.util.Arrays;
import java.util.stream.*;
/**
 * Return an int[] of the same size, and for each element first
 * element to the right that is greater, or cycle back around
 * to the beginning of the array.
 *
 * After exhausting the remainder of the array, circle back around
 * to the beginning and continue searching.  If there is no element
 * greater than the current element, get "-1" for that element.
 *
 * The returned array will be the same size as the input array.
 *
 * e.g. [0,1,2,1] Will return [1,2,-1,2]
 */
public class FindNextGreaterValueInArray {

    public static void main(String[] args) {
        new FindNextGreaterValueInArray();
    }

    public int[] execute(int[] input) {
        return IntStream.range(0,input.length)
            .map(idx->getFirstGreaterValue(idx,input))
            .toArray();
    }

    private int getFirstGreaterValue(int pos, int[] arr) {
        for (int incr = 1; incr < arr.length; incr++) {
            int potentialMatch = arr[(pos + incr) % arr.length];
            if (potentialMatch > arr[pos]) {
                return potentialMatch;
            }
        }
        return -1;
    }
}
