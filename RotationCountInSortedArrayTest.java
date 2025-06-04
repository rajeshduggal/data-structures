///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.junit.jupiter:junit-jupiter-engine:5.10.1
//DEPS org.junit.platform:junit-platform-console:1.9.3
//SOURCES RotationCountInSortedArray.java

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.platform.console.tasks.ConsoleTestExecutor;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.console.options.CommandLineOptions;

import java.io.PrintWriter;
import java.util.List;

public class RotationCountInSortedArrayTest {

    @Test
    public void testExecuter() {
        var executer = new RotationCountInSortedArray();
        assertEquals(2, executer.execute(new int[]{15,18,2,3,6,12}));
        assertEquals(4, executer.execute(new int[]{7,9,11,12,5}));
    }

    public static void main(String... args) throws Exception {
        CommandLineOptions options = new CommandLineOptions();
        options.setSelectedClasses(List.of(DiscoverySelectors.selectClass(RotationCountInSortedArrayTest.class)));
        new ConsoleTestExecutor(options).execute(new PrintWriter(System.out));
    }
}
