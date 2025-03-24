import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Modernize {

    record Rectangle(double length, double width) {
        // Modernized to use the record class compact constructor feature
        public Rectangle(double length, double width) {
            if (length <= 0 || width <= 0) {
                throw new java.lang.IllegalArgumentException(
                    String.format("Invalid dimensions: %f, %f", length, width));
            }
        }
    }

    public static void main(String[] args) {
        // Given
        String dayName;
        int dayOfWeek = 1;
        // Modernize
        dayName = switch (dayOfWeek) {
            case 1 -> "Sunday";
            case 2 -> "Monday";
            default -> "Unknown";
        };
        System.out.println(dayName);

        ///////////////////////


        // Given
        enum Day {
            MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
        }
        Day day = Day.WEDNESDAY;
        int numLetters = 0;
        // Modernize
        numLetters = switch (day) {
            case MONDAY, FRIDAY, SUNDAY -> 6;
            case TUESDAY -> 7;
            case THURSDAY, SATURDAY -> 8;
            case WEDNESDAY -> 9;
            default -> throw new IllegalStateException("Invalid day: " + day);
        };
        System.out.println(day + " has " + numLetters + " letters.");

        //////////////////////////

        // Given
        List<String> arrList = new ArrayList<>();
        arrList = List.of("a","b","c");
        // Modernize
        String[] strArr = arrList.toArray(new String[arrList.size()]);
        Arrays.stream(strArr).forEach(System.out::println);

        //////////////////////////

        // Given
        List<String> names = List.of("Alice", "Bob", "Charlie");
        // Modernize this
        List<String> shortNames = new ArrayList<>();
        for (String name : names) {
            if (name.length() <= 4) {
                shortNames.add(name);
            }
        }
        shortNames.forEach(i -> System.out.println("Short name = " + i));

        //////////////////////////

        Object obj = "Hello";
        if (obj instanceof String s) {
            System.out.println(s.toUpperCase());
        }

        /////////////////////////
        public static double getPerimeter(Shape s) throws IllegalArgumentException {
            return switch (s) {
                case Rectangle r -> 2 * r.length() + 2 * r.width();
                case Circle c    -> 2 * c.radius() * Math.PI;
                default          -> throw new IllegalArgumentException("Unrecognized shape");
            };
        }

        /////////////////////////

        Stream.iterate(1, n -> <= 19, n -> n + 2)
            .forEach(System.out::println);

        /////////////////////////
        
        DoubleSupplier onceComputedSupplier = memoize(() -> Math.random() * 100);

        System.out.println(onceComputedSupplier.getAsDouble()); // Same value every time
        System.out.println(onceComputedSupplier.getAsDouble());


        IntSupplier fibSupplier = new IntSupplier() {
            private int previous = 0, current = 1;

            @Override
            public int getAsInt() {
                int next = previous + current;
                previous = current;
                current = next;
                return previous;
            }
        };

        IntStream.generate(fibSupplier).limit(10).forEach(System.out::println);


        IntSupplier randomSupplier = Modernize::generateRandomNumber;
        System.out.println(randomSupplier.getAsInt());

        List<Integer> list = IntStream.range(1, 5)
                              .boxed()
                              .toList();
    }

    public static int generateRandomNumber() {
        return (int)(Math.random() * 100);
    }

    public static DoubleSupplier memoize(DoubleSupplier supplier) {
        final double cache[] = {Double.NaN};
        return () -> {
            if (Double.isNaN(cache[0])) {
                cache[0] = supplier.getAsDouble();
            }
            return cache[0];
        };
    }
}
