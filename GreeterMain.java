public class GreeterMain {

    public static void main(String... args) throws Exception {
        new GreeterMain();
    }

    public GreeterMain() {
        System.out.println(new Greeter().greeting("Rajesh"));
    }

    class Greeter {
        String greeting(String name) {
            return "Hello " + (name != null ? name : "");
        }
    }
}
