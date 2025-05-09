///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS info.picocli:picocli:4.5.0

import java.util.*;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import static java.lang.System.*;

//import LL;

/**
 * Detect if there is a cycle in a linked list
 *
 * https://www.youtube.com/watch?v=MFOAbpfrJ8g
 * "a b,b c,c d,d b" to create a link list that cycles from d back to b.
 */
@Command(
    name = "CycleInLinkedList",
    mixinStandardHelpOptions = true,
    version = "CycleInLinkedList 0.1",
    description = "Detect if there is a cycle in a linked list")
class CycleInLinkedList implements Callable<Integer> {

  @Parameters(index = "0", arity = "0..*", description = "Initial set of values to load into the linked list")
  private List<String> inputStrings;

  public static void main(String... args) {
    int exitCode = new CommandLine(new CycleInLinkedList()).execute(args);
    exit(exitCode);
  }

  @Override
  public Integer call() throws Exception {
    if (inputStrings == null) {
        inputStrings = List.of(
            // CYCLED
            "a b,b c,c a",
            "a b,b c,c b",
            "a b,b c,c c",
            // NOT CYCLED
            "a b",
            "a b,b c",
            "a b,b c,c d");
    }
    inputStrings.forEach( str -> {
        LL<Character> myLinkedList = new LL<>(str);
        out.println(str + " : " + myLinkedList.hasCycle());
    });
    return 0;
  }
}
