///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS info.picocli:picocli:4.5.0

import java.util.*;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import static java.lang.System.*;

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

  private Node head = null;

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
        Node head = buildLinkedList(str);
        out.println(str + " : " + hasCycle(head));
    });
    return 0;
  }

  private Node buildLinkedList(String str) {
    String[] pairs = str.split(",");
    head = new Node((pairs[0].split(" ")[0]).charAt(0)); 

    char[] arr;
    for (String srcdest : pairs) {
      arr = srcdest.toCharArray();
      link(arr[0],arr[2]);
    }
    return head;
  }

  private boolean hasCycle(Node head) {
      Node fast = head;
      Node slow = head;

      while (fast != null && fast.next != null) {
          // out.println(" fast = " + fast.name);
          // out.println(" slow = " + slow.name);
          fast = fast.next.next;
          slow = slow.next;
          if (fast == slow) return true;
      }
      return false;
  }

  class Node {
    Node next;
    char name;

    Node(char name) { this.name = name; }
  }


  private Node find(char term) {
    Node current = head;
    int counter = 0; // it might be a cycle, so we give up after 10 steps.
    while (current != null && counter < 10) {
        counter++;
        if (current.name == term) return current;
        current = current.next;
    }
    return null;
  }

  private void link(char src, char dest) {
    //out.println("Linking " + src + " to " + dest);
    Node destNode = find(dest);
    if (destNode == null) {
        destNode = new Node(dest);
    }
    Node srcNode = find(src);
    if (srcNode == null) {
        srcNode = new Node(src);
        head = srcNode;
    }
    srcNode.next = destNode;
  }
}
