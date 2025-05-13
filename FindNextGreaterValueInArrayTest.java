///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.junit.jupiter:junit-jupiter-engine:5.10.1
//DEPS org.junit.platform:junit-platform-console:1.9.3
//SOURCES FindNextGreaterValueInArray.java

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.platform.console.tasks.ConsoleTestExecutor;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.console.options.CommandLineOptions;

import java.io.PrintWriter;
import java.util.List;

public class FindNextGreaterValueInArrayTest {

    @Test
    public void testExecuter() {
        var executer = new FindNextGreaterValueInArray();
        assertArrayEquals(new int[]{1,2,-1,2}, executer.execute(new int[]{ 0,1,2,1 }));
    }

    public static void main(String... args) throws Exception {
        CommandLineOptions options = new CommandLineOptions();
        options.setSelectedClasses(List.of(DiscoverySelectors.selectClass(FindNextGreaterValueInArrayTest.class)));
        new ConsoleTestExecutor(options).execute(new PrintWriter(System.out));
    }
}
