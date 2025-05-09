///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS info.picocli:picocli:4.5.0

import java.util.*;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import static java.lang.System.*;

@Command(
    name = "BinarySearch",
    mixinStandardHelpOptions = true,
    version = "BinarySearch 0.1",
    description = "Intersting things with Binary Search")
class BinarySearch implements Callable<Integer> {

  @Parameters(index = "0", arity = "0..*", description = "Initial set of values to load")
  private List<int[]> inputStrings;

  public static void main(String... args) {
    int exitCode = new CommandLine(new BinarySearch()).execute(args);
    exit(exitCode);
  }

  @Override
  public Integer call() throws Exception {
    if (inputStrings == null) {
        inputStrings = List.of(
                new int[]{-18, -12, -4, 0, 2, 3, 4, 15, 16, 18, 22, 45, 89},
                new int[]{2,3,5,9,14,16,18}
            );
    }
    inputStrings.forEach( intArr -> {
        out.println("Searching " + Arrays.toString(intArr) + " for 16. Found it at index " + binarySearch(intArr,16));
    });

    inputStrings.forEach( intArr -> {
        int ceiling = ceiling(intArr,15);
        out.println("Searching for the ceiling of 10 in " + Arrays.toString(intArr) + ". Found it at index " + ceiling + ". Value of " + intArr[ceiling]);
    });
    
    inputStrings.forEach( intArr -> {
        int floor = floor(intArr,15);
        out.println("Searching for the floor of 15 in " + Arrays.toString(intArr) + ". Found it at index " + floor + ". Value of " + intArr[floor]);
    });

    return 0;
  }

  // return the index of the greatest number smaller than or equal to the target 
  int floor(int[] arr, int target) {
      // if the target is less than the lowest number in the array return -1
      if (target < arr[0]) {
          return -1;
      }
      int start = 0;
      int end = arr.length - 1;

      while(start <= end) {
          // find the middle element
          int mid = start + (end - start) / 2;
          if (target < arr[mid]) {
              end = mid - 1;
          } else if (target > arr[mid]) {
              start = mid + 1;
          } else {
              return mid;
          }
      }
      return end;
  }

  // return the index of the smallest number greater than or equal to the target 
  int ceiling(int[] arr, int target) {

      // if the target is greater than the greatest number in the array return -1
      if (target > arr[arr.length - 1]) {
          return -1;
      }
      int start = 0;
      int end = arr.length - 1;

      while(start <= end) {
          // find the middle element
          int mid = start + (end - start) / 2;
          if (target < arr[mid]) {
              end = mid - 1;
          } else if (target > arr[mid]) {
              start = mid + 1;
          } else {
              return mid;
          }
      }
      return start;
  }

  int binarySearch(int[] arr, int target) {
      int start = 0;
      int end = arr.length - 1;

      while(start <= end) {
          // find the middle element
          int mid = start + (end - start) / 2;
          if (target < arr[mid]) {
              end = mid - 1;
          } else if (target > arr[mid]) {
              start = mid + 1;
          } else {
              return mid;
          }
      }
      return -1;
  }
}
