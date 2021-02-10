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

/**
 * Create a Queue using Two Stacks
 *
 * https://www.youtube.com/watch?v=7ArHz8jPglw
 * use "push 10" to push 10 onto the stack.
 * use "pop" to pop the value off the stack.
 */
@Command(
    name = "QueueFromStacks",
    mixinStandardHelpOptions = true,
    version = "QueueFromStacks 0.1",
    description = "Queue implemented with two stacks")
class QueueFromStacks implements Callable<Integer> {

  MyQueue<Integer> q;

  @Parameters(index = "0", arity = "0..*", description = "Initial numbers to load into the queue", defaultValue = "1,2,3,4", split=",")
  private List<Integer> numbers;

  public static void main(String... args) {
    int exitCode = new CommandLine(new QueueFromStacks()).execute(args);
    System.exit(exitCode);
  }

  @Override
  public Integer call() throws Exception {
    Scanner input = new Scanner(System.in);
    String cmd;
    q = new MyQueue<Integer>(numbers);
    while (input.hasNext()) {
      cmd = input.nextLine();
      if (cmd.equals("pop")) {
       out.println("POPPED! " + q.pop());
      } else if (cmd.startsWith("push")) {
        String[] x = cmd.split("\\s+");
        q.push(Integer.parseInt(x[1]));
      }
      out.println(q);
    }
    return 0;
  }

  class MyQueue<T> {
    Deque<T> stackIn = new ArrayDeque<>();
    Deque<T> stackOut = new ArrayDeque<>();

    private void popStackInOntoStackOut() {
      while(!stackIn.isEmpty()) { 
        stackOut.push(stackIn.removeFirst());
      }
    }

    public void push(T e) {
      stackIn.addFirst(e);
    }

    public T pop() {
      if (stackOut.isEmpty()) {
        popStackInOntoStackOut();
      }
      if (stackOut.isEmpty() || stackOut.getFirst() == null) return null;

      return stackOut.removeFirst();
    } 

    public MyQueue(List<T> arr) {
      Collections.reverse(arr);
      stackIn.addAll(arr);
    }

    @Override
    public String toString() {
      return "MyQueue{" +
        "stackIn = " + stackIn +
        ", stackOut = " + stackOut +
        "}";
    }
  }
}
