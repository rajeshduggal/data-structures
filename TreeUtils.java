///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS info.picocli:picocli:4.5.0

import java.util.*;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import static java.lang.System.*;

@Command(
    name = "TreeUtils",
    mixinStandardHelpOptions = true,
    version = "TreeUtils 0.1",
    description = "Intersting things with Trees")
class TreeUtils implements Callable<Integer> {

  @Parameters(index = "0", arity = "0..*", description = "Initial set of values to load")
  private List<String> inputStrings;

  public static void main(String... args) {
    int exitCode = new CommandLine(new TreeUtils()).execute(args);
    exit(exitCode);
  }

  @Override
  public Integer call() throws Exception {
    if (inputStrings == null) {
        inputStrings = List.of(
            // Valid Binary Search Tree
            "50 10,10 5,10 30,30 20,30 40,50 80,80 70,80 90,90 85",
            // InValid Binary Search Tree (because of last node)
            "50 10,10 5,10 30,30 20,30 40,50 80,80 70,80 90,10 75"
            );
    }
    inputStrings.forEach( str -> {
        Tree<Integer> myTree = new Tree<>(str);
        out.println(str + " : Is Valid BST? " + myTree.isValidIntegerBST());
    });
    return 0;
  }
}
