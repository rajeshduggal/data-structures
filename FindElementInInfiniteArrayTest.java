///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.junit.jupiter:junit-jupiter-engine:5.10.1
//DEPS org.junit.platform:junit-platform-console:1.9.3
//SOURCES FindElementInInfiniteArray.java

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.platform.console.tasks.ConsoleTestExecutor;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.console.options.CommandLineOptions;

import java.io.PrintWriter;
import java.util.List;

public class FindElementInInfiniteArrayTest {

    @Test
    public void testExecuter() {
        var executer = new FindElementInInfiniteArray();
        assertEquals(4, executer.execute(new int[]{3,5,7,9,10,90,100,130,140,160,170},10));
    }

    public static void main(String... args) throws Exception {
        CommandLineOptions options = new CommandLineOptions();
        options.setSelectedClasses(List.of(DiscoverySelectors.selectClass(FindElementInInfiniteArrayTest.class)));
        new ConsoleTestExecutor(options).execute(new PrintWriter(System.out));
    }
}
