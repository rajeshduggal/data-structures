///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.junit.jupiter:junit-jupiter-engine:5.10.1
//DEPS org.junit.platform:junit-platform-console:1.9.3
//SOURCES PeakInMountainArray.java

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.platform.console.tasks.ConsoleTestExecutor;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.console.options.CommandLineOptions;

import java.io.PrintWriter;
import java.util.List;

public class PeakInMountainArrayTest {

    @Test
    public void testExecuter() {
        var executer = new PeakInMountainArray();
        assertEquals(1, executer.execute(new int[]{0,1,0}));
        assertEquals(1, executer.execute(new int[]{0,2,1,0}));
        assertEquals(1, executer.execute(new int[]{0,10,5,2}));
        assertEquals(2, executer.execute(new int[]{1,2,3,1}));
        assertEquals(5, executer.execute(new int[]{1,2,1,3,5,6,4}));
    }

    public static void main(String... args) throws Exception {
        CommandLineOptions options = new CommandLineOptions();
        options.setSelectedClasses(List.of(DiscoverySelectors.selectClass(PeakInMountainArrayTest.class)));
        new ConsoleTestExecutor(options).execute(new PrintWriter(System.out));
    }
}
