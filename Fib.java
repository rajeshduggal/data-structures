import static java.util.Map.*;
import java.util.stream.*;

/**
 * fibonacci sequence
 */
public class Fib {
    public static void main(String[] args) {
        Stream.iterate(
                    entry(1,1),
                    e -> entry(e.getValue(),e.getKey()+e.getValue())
                    )
                .map(Entry::getValue)
                .takeWhile(n -> n < 30)
                .forEach(System.out::println);
    }
}
