import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Modernize {

    record Rectangle(double length, double width) {
        // Modernize this to use the record class compact constructor feature
        public Rectangle(double length, double width) {
            if (length <= 0 || width <= 0) {
                throw new java.lang.IllegalArgumentException(
                    String.format("Invalid dimensions: %f, %f", length, width));
            }
            this.length = length;
            this.width = width;
        }
    }

    public static void main(String[] args) {
        // Given
        String dayName;
        int dayOfWeek = 1;
        // Modernize
        switch (dayOfWeek) {
            case 1: dayName = "Sunday"; break;
            case 2: dayName = "Monday"; break;
            default: dayName = "Unknown";
        }
        System.out.println(dayName);

        ///////////////////////


        // Given
        enum Day {
            MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
        }
        Day day = Day.WEDNESDAY;
        int numLetters = 0;
        // Modernize
        switch (day) {
            case MONDAY:
            case FRIDAY:
            case SUNDAY:
                numLetters = 6;
                break;
            case TUESDAY:
                numLetters = 7;
                break;
            case THURSDAY:
            case SATURDAY:
                numLetters = 8;
                break;
            case WEDNESDAY:
                numLetters = 9;
                break;
            default:
                throw new IllegalStateException("Invalid day: " + day);
        }
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

        // Given
        Object obj = "Hello";
        // Modernize
        if (obj instanceof String) {
            String s = (String) obj;
            System.out.println(s.toUpperCase());
        }

        /////////////////////////
        public static double getPerimeter(Shape s) throws IllegalArgumentException {
            if (s instanceof Rectangle r) {
                return 2 * r.length() + 2 * r.width();
            } else if (s instanceof Circle c) {
                return 2 * c.radius() * Math.PI;
            } else {
                throw new IllegalArgumentException("Unrecognized shape");
            }
        }

        /////////////////////////

        Stream.iterate(1, n -> n + 2) // Generates an infinite sequence
            .limit(10) // Limits it to 10 elements
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
