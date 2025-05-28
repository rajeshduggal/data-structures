/**
 * Find the next smallest letter greater than target
 *
 * Given a sorted array "letters", return the smallest
 * character in the array that is larger than target.
 * 
 * The letters wrap around, so that if the target is
 * greater than all letters in the array return 
 * the first element of the array.
 */
public class SmallestLetterGreaterThanTarget {
    public char execute(char[] letters, char target) {
        int start = 0;
        int end = letters.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (target < letters[mid]) {
                end = mid - 1;
            } else {
                // If mid is the last element, it's possible for
                // start to point to an index out of bound. In which
                // case we wrap to index 0 for the answer.
                start = mid + 1;
            }
        }
        // If the target is greater than the last element
        // start will be 1 more than the size of the arr
        // to answer with the first element of the array.y
        // So we use modulo to wrap back to index 0
        // to answer with the first element of the array.
        return letters[start % letters.length];
    }
}
