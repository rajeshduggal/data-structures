///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.junit.jupiter:junit-jupiter-engine:5.10.1
//DEPS org.junit.platform:junit-platform-console:1.9.3
//SOURCES SearchInRotatedSortedArrayWithDups.java

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.platform.console.tasks.ConsoleTestExecutor;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.console.options.CommandLineOptions;

import java.io.PrintWriter;
import java.util.List;

public class SearchInRotatedSortedArrayWithDupsTest {

    @Test
    public void testExecuter() {
        var executer = new SearchInRotatedSortedArrayWithDups();
        assertEquals(4, executer.execute(new int[]{4,5,6,7,0,1,2},0));
        assertEquals(-1, executer.execute(new int[]{4,5,6,7,0,1,2},3));
        assertEquals(1, executer.execute(new int[]{2,9,2,2,2,2},9));
        assertEquals(0, executer.execute(new int[]{9,2,2,2,2},9));
        assertEquals(4, executer.execute(new int[]{2,2,2,2,9},9));
        assertEquals(-1, executer.execute(new int[]{1},0));
    }

    public static void main(String... args) throws Exception {
        CommandLineOptions options = new CommandLineOptions();
        options.setSelectedClasses(List.of(DiscoverySelectors.selectClass(SearchInRotatedSortedArrayWithDupsTest.class)));
        new ConsoleTestExecutor(options).execute(new PrintWriter(System.out));
    }
}
