/**
 * Get the first and last position of element in a sorted array
 *
 * e.g. targeting 8, in the array {5,7,7,8,8,10} will return {3,4}
 *
 * If the target is not found, returns [-1, -1]
 * Runs at O(log n)
 */
public class FirstAndLastPositionOfElement {
    public int[] execute(int[] nums, int target) {
        var ans = new int[2];
        ans[0] = search(nums, target, true);
        // If we didnt' find the target in the first search,
        // we don't need search for it another time.
        ans[1] = (ans[0] == -1 ? -1 : search(nums, target, false));
        return ans;
    }

    private int search(int[] nums, int target, boolean findStartIndex) {
        int ans = -1;
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (target < nums[mid]) {
                end = mid - 1;
            } else if (target > nums[mid]) {
                start = mid + 1;
            } else {
                //Potential answer found!
                ans = mid;
                //But we want to break out when our we've
                //fully divide and conquered all the way.
                if (findStartIndex) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }

        }
        return ans;
    }
}
