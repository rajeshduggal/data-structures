

void main() {
    int[] nums = {1,3,5,6,9};
    int l = 0, r = nums.length-1, m = 0;
    int target = 6;

    while (l <= r) {
        m = l + (r-l) / 2;
        int midVal = nums[m];
        if (target > midVal) {
            l = m + 1;
        } else if (target < midVal) {
            r = m - 1;
        } else {
            break;
        }
    }
    IO.println("m = " + m);
    IO.println("Val = " + nums[m]);
}
