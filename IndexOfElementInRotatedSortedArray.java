/**
 * @param args command-line arguments. 1) comma separated numbers, 2) target num.
 */
void main(String[] args) {
    int[] nums = Arrays.stream(args[0].split(","))
    .mapToInt(Integer::parseInt)
    .toArray();
   int target = Integer.parseInt(args[1]);
   IO.println(search(nums, target));
}

int search(int[] nums, int target) {
    int lo = 0;
    int hi = nums.length - 1;

    while (lo <= hi) {
        int mid = (lo + hi) / 2;
        
        if (nums[mid] == target) return mid;

        if (nums[lo] <= nums[mid]) {
            if (nums[lo] <= target && target < nums[mid]) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        } else {
            if (nums[mid] < target && target <= nums[hi]) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
    }
    return -1;
}