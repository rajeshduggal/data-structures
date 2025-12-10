import java.util.*;

public class FindFirstMissingNumberInIncrementingArrayStartingAt1 {

    static int execute(int[] arr){
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) / 2);
            if (arr[mid] == mid+1) {
                left = mid+1;
            } else {
                right = mid-1;
            }
        }
        return left+1;
    }
    
    void main() {
        int[] input;
        input = new int[]{1,2,3,4,6,7,8,10,11};
        IO.println("The missing number in " + Arrays.toString(input) + " is " + execute(input));
        input = new int[]{1,3};
        IO.println("The missing number in " + Arrays.toString(input) + " is " + execute(input));
        input = new int[]{1,2,3,4,6,8};
        IO.println("The missing number in " + Arrays.toString(input) + " is " + execute(input));
        input = new int[]{2,3,4};
        IO.println("The missing number in " + Arrays.toString(input) + " is " + execute(input));
    }
}

