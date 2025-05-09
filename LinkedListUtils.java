///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS info.picocli:picocli:4.5.0

import java.util.*;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import static java.lang.System.*;

@Command(
    name = "LinkedListUtilsnLinkedList",
    mixinStandardHelpOptions = true,
    version = "LinkedListUtilsnLinkedList 0.1",
    description = "Do some interesting things with linked lists")
class LinkedListUtilsnLinkedList implements Callable<Integer> {

  @Parameters(index = "0", arity = "0..*", description = "Initial set of values to load into the linked list")
  private List<String> inputStrings;

  public static void main(String... args) {
    int exitCode = new CommandLine(new LinkedListUtilsnLinkedList()).execute(args);
    exit(exitCode);
  }

  @Override
  public Integer call() throws Exception {
    LL<Character> myLinkedList = new LL<>("a b,b c,c d");
    out.println(myLinkedList); // a b c d
    myLinkedList.insertUsingRecursion('X', 1);
    out.println(myLinkedList); // a X b c d
    return 0;
  }
}
