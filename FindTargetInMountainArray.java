/**
 * Return the index of the target in a mountain array, else -1
 */
public class FindTargetInMountainArray {
    public int execute(int[] arr, int target) {
        int peakIndex = peakOfMountain(arr);
        int ans = searchOrderAgnostic(arr, 0, peakIndex, target);
        if (ans != -1) return ans;
        return searchOrderAgnostic(arr, peakIndex+1, arr.length-1, target);
    }

    // search for target in either ascending or dec
    private int searchOrderAgnostic(int[] arr, int start, int end, int target) {
        boolean isAscending = (arr[start] < arr[end] ? true : false);
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (target == arr[mid]) return mid;
            if (isAscending) {
                if (target < arr[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (target > arr[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
        return -1;
    }
    
    // Return the index of the peak of the mountain 
    private int peakOfMountain(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] > arr[mid+1]) {
                // mid is in the decending part
                end = mid;
            } else {
                // mid is in the ascending part
                start = mid +1;
            }
        }
        return start;
    }
}
