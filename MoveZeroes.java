/**
 * Move all 0's of an int[] to the end
 * 
 * While maintaining the relative order of the non-zero elements.
 * e.g. Input: nums = [0,1,0,3,12] Output: [1,3,12,0,0]
 *
 * @param args command-line arg. Comma separated list of numbers
 */
void main(String[] args) {
    int[] nums = Arrays.stream(args[0].split(","))
    .mapToInt(Integer::parseInt)
    .toArray();
    moveZeroes(nums);
    IO.println(Arrays.toString(nums)) ;
}
void moveZeroes(int[] nums) {
    // Iterate the list, and if it's not a zero insert it at the earlier position in
    // the list where we are keeping all non-zeros.
    int insertPos = 0;
    for (int num: nums) {
        if (num != 0) {
            nums[insertPos++] = num;
        }
    }
   
    // After completing the entire list,
    // we zero out all the elements after the insertPos.
    while (insertPos < nums.length) {
        nums[insertPos++] = 0;
    }
}