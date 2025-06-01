public class PeakInMountainArray {
    public int execute(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] > arr[mid+1]) {
                // in a decending part of the array
                end = mid;
            } else {
                // In ascending part of the array
                start = mid + 1;
            }
        }
        return start;
    }
}
