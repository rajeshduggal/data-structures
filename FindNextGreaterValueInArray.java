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
        return IntStream.of(input)
            .map(i->getFirstGreaterValue(i,input))
            .toArray();
    }

    private int getFirstGreaterValue(int pos, int[] arr) {
        for (int incr = 1; incr < (arr.length - 1); incr++) {
            int potentialMatch = arr[incr % arr.length];
            if (arr[pos] < potentialMatch) return potentialMatch;
        }
        return -1;
    }
}
