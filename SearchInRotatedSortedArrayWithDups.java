import java.util.*;
/**
 * Return the index of the target in a rotated sorted array, else -1
 */
public class SearchInRotatedSortedArrayWithDups {
    public int execute(int[] arr, int target) {
        int pivot = getPivot(arr);

        if (pivot == -1) {
            return search(arr, 0, arr.length - 1, target);
        }

        if (arr[pivot] == target) return pivot;

        int start;
        int end;
        if (target >= arr[0]) {
            // search the elements before the pivot
            start = 0;
            end = pivot - 1;
        } else {
            // search the elements after the pivot
            start = pivot + 1;
            end = arr.length - 1;
        }
        return search(arr, start, end, target);
    }

    private int getPivot(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (mid < end && arr[mid] > arr[mid+1]) return mid;
            if (mid > start && arr[mid] < arr[mid-1]) return mid-1;

            // if the element at start, mid, end are the same, then skip the dups
            if (start != end && arr[mid] == arr[start] && arr[mid] == arr[end]) {
                if (arr[start] > arr[start+1]) return start; // check if the start is the pivot
                if (arr[end] < arr[end-1]) return end-1; // check is the end is the pivot
                // skip the ends since they're dups
                start++;
                end--;
            } else if (arr[start] <= arr[mid] || (arr[start] == arr[mid] && arr[mid] > arr[end])) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    private int search(int[] arr, int start, int end, int target) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (target == arr[mid]) return mid;
            if (target < arr[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
}
