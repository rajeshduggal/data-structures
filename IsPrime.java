
import java.util.stream.*;

public class IsPrime {

    public static void main(String[] args) {
        IntStream.rangeClosed(1,20).forEach(num -> {
            System.out.println(isPrime(num) ? "Yes " + num + " PRIME!" : num);
        });
    }

    static boolean isPrime(int num) {
        if (num <= 1) return false;
        int sqrt = (int)Math.sqrt(num);
        return IntStream.rangeClosed(2,sqrt).noneMatch(i -> num % i == 0);
    }

}
