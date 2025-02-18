///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.junit.jupiter:junit-jupiter-engine:5.10.1
//DEPS org.junit.platform:junit-platform-console:1.9.3
//SOURCES GreeterMain.java

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.platform.console.tasks.ConsoleTestExecutor;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.console.options.CommandLineOptions;

import java.io.PrintWriter;
import java.util.List;

public class GreeterTest {

    @Test
    public void testGreeting() {
        var greeterMain = new GreeterMain();
        var greeter = greeterMain.new Greeter();
        assertEquals("Hello John", greeter.greeting("John"));
    }

    public static void main(String... args) throws Exception {
        CommandLineOptions options = new CommandLineOptions();
        options.setSelectedClasses(List.of(DiscoverySelectors.selectClass(GreeterTest.class)));
        new ConsoleTestExecutor(options).execute(new PrintWriter(System.out));
    }
}