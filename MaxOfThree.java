import java.util.*;

public class MaxOfThree  {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int a = s.nextInt();
        int b = s.nextInt();
        int c = s.nextInt();

        System.out.println( Math.max(a, Math.max(b,c)));
    }
}
