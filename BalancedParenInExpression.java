///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS info.picocli:picocli:4.5.0

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import static picocli.CommandLine.Parameters;

import static java.lang.System.*;

/**
 * https://www.youtube.com/watch?v=IhJGJG-9Dx8 {}, [], (). See if a given string is balanced or not.
 * e.g. {()[{({})[]()}]}([])
 *
 * <p>BALANCED: {}()[{}] [({})] ({[]})
 *
 * <p>UNBALANCED: [({(}] ({[}) ()}[]
 */
@Command(
    name = "BalancedParenInExpression",
    mixinStandardHelpOptions = true,
    version = "BalancedParenInExpression 0.1",
    description = "Report if the given set of brackets is balanced or not")
class BalancedParenInExpression implements Callable<Integer> {

  @Parameters(index = "0", arity = "0..*", description = "The string of brackets to evaluate.")
  private String[] inputStrings;


  private static HashMap<Character, Character> brackets = new HashMap<>(3);

  static {
    brackets.put('(', ')');
    brackets.put('[', ']');
    brackets.put('{', '}');
  }

  public static void main(String... args) {
    int exitCode = new CommandLine(new BalancedParenInExpression()).execute(args);
    System.exit(exitCode);
  }

  @Override
  public Integer call() throws Exception {
    if (inputStrings == null) {
      inputStrings = new String[6];
      // BALANCED:
      inputStrings[0]="{}()[{}]";
      inputStrings[1]="[({})]";
      inputStrings[2]="({[]})";

      // UNBALANCED:
      inputStrings[3]="[({(}]";
      inputStrings[4]="({[})";
      inputStrings[5]="()}[]";
    }

    for (String str : inputStrings) {
      printBalanced(str);
    }
    return 0;
  }

  private void printBalanced(String val) {
    System.out.println(val + " is" + (isBalanced(val) == false ? " not " : " ") + "balanced");
  }

  private static boolean isOpenBracket(char c) {
    return brackets.containsKey(c);
  }

  private static boolean isMatchingBracketType(char open, char close) {
    return (brackets.get(open) == (close));
  }

  private static boolean isBalanced(String expression) {
    Deque<Character> stack = new ArrayDeque<>();

    for (char c : expression.toCharArray()) {
      if (isOpenBracket(c)) {
        stack.push(c);
      } else {
        if (stack.isEmpty() || !isMatchingBracketType(stack.pop(), c)) {
          return false;
        }
      }
    }
    return stack.isEmpty();
  }
}
