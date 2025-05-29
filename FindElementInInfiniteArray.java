/**
 * Get the index of the target element in a sorted infinite array (unknown end or size)
 *
 * e.g. targeting 8, in the array {5,7,8,10,12....} will return 2
 *
 * Runs at O(log n)
 */
public class FindElementInInfiniteArray {
    public int execute(int[] nums, int target) {
        return search(nums, target);
    }

    private int search(int[] arr, int target) {
        int start = 0;
        // The imaginary infinite array doesn't have an
        // end, so we will start with 1 and then keep
        // doubling it with each iteration to find an
        // end value that encompasses the target.
        int end = 1;
        while (target > arr[end]) {
            // Since the target wasn't within the previous
            // iteration we move the start to end of the
            // range we're searching within.
            int newStart = end + 1;
            // We double the size of each new search range
            end = end + (end - start + 1) * 2;
            start = newStart;
        }
        return search(arr, target, start, end);
    }

    private int search(int[] nums, int target, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (target < nums[mid]) {
                end = mid - 1;
            } else if (target > nums[mid]) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
