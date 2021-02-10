///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS info.picocli:picocli:4.5.0

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import static java.lang.System.*;

@Command(
    name = "MyQueue",
    mixinStandardHelpOptions = true,
    version = "MyQueue 0.1",
    description = "Queue implemention")
class MyQueue implements Callable<Integer> {

  private Node head; // Pop things onto the head.
  private Node tail; // Push things onto the tail.

  @Parameters(index = "0", arity = "0..*", description = "Initial numbers to load into the queue", defaultValue = "1,2,3,4", split=",")
  private List<Integer> numbers;

  public static void main(String... args) {
    int exitCode = new CommandLine(new MyQueue()).execute(args);
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
      tail = n;
    } else {
      tail.next = n;
      tail = tail.next;
    }
  }

  public int pop() {
    if (isEmpty()) return -1;
    int retval = head.data;
    if (head.next == null) {
      head = null;
      tail = null;
    } else {
      head = head.next;
    }
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
