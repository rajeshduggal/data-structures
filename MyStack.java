///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS info.picocli:picocli:4.5.0

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import static java.lang.System.*;

/**
 * Stack
 *
 * https://www.youtube.com/watch?v=wjI1WNcIntg
 * use "push 10" to push 10 onto the stack.
 * use "pop" to pop the value off the stack.
 */
@Command(
    name = "MyStack",
    mixinStandardHelpOptions = true,
    version = "MyStack 0.1",
    description = "Stack implemention")
class MyStack implements Callable<Integer> {

  private Node head; // Push and pop things onto the head.

  @Parameters(index = "0", arity = "0..*", description = "Initial numbers to load into the stack", defaultValue = "1,2,3,4", split=",")
  private List<Integer> numbers;

  public static void main(String... args) {
    int exitCode = new CommandLine(new MyStack()).execute(args);
    System.exit(exitCode);
  }

  @Override
  public Integer call() throws Exception {
    Scanner input = new Scanner(System.in);
    String cmd;

    initQueue();

    while (input.hasNext()) {
      cmd = input.nextLine();
      out.println("CMD > " + cmd);
      if (cmd.equals("pop")) {
        out.println("POPPED! " + pop());
      } else if (cmd.startsWith("push")) {
        String[] x = cmd.split("\\s+");
        push(Integer.parseInt(x[1]));
      }
      out.println(this);
    }

    return 0;
  }

  class Node {
    Node next;
    int data;

    Node(int data) { this.data = data; }
  }

  public void initQueue() {
    for (int n : numbers) {
      push(n);
    }
  }

  public boolean isEmpty() {
    return (head == null);
  }

  public int peek() {
    return head.data;
  }

  public void push(int data) {
    Node n = new Node(data);
    if (isEmpty()) {
      head = n;
    } else {
      n.next = head;
      head = n;
    }
  }

  public int pop() {
    if (isEmpty()) return -1;
    int retval = head.data;
    head = head.next;
    return retval;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Node pointer = head;
    while (pointer != null) {
      sb.append(pointer.data + ":");
      pointer = pointer.next;
    }
    return sb.toString();
  }

}
