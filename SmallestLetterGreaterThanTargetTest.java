///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.junit.jupiter:junit-jupiter-engine:5.10.1
//DEPS org.junit.platform:junit-platform-console:1.9.3
//SOURCES SmallestLetterGreaterThanTarget.java

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.platform.console.tasks.ConsoleTestExecutor;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.console.options.CommandLineOptions;

import java.io.PrintWriter;
import java.util.List;

public class SmallestLetterGreaterThanTargetTest {

    @Test
    public void testExecuter() {
        var executer = new SmallestLetterGreaterThanTarget();
        assertEquals('a', executer.execute(new char[]{'a','b'}, 'z'));
        assertEquals('c', executer.execute(new char[]{'c','f','j'}, 'a'));
        assertEquals('f', executer.execute(new char[]{'c','f','j'}, 'c'));
        assertEquals('f', executer.execute(new char[]{'c','f','j'}, 'd'));
    }

    public static void main(String... args) throws Exception {
        CommandLineOptions options = new CommandLineOptions();
        options.setSelectedClasses(List.of(DiscoverySelectors.selectClass(SmallestLetterGreaterThanTargetTest.class)));
        new ConsoleTestExecutor(options).execute(new PrintWriter(System.out));
    }
}
