///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.junit.jupiter:junit-jupiter-engine:5.10.1
//DEPS org.junit.platform:junit-platform-console:1.9.3
//SOURCES FirstAndLastPositionOfElement.java

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.platform.console.tasks.ConsoleTestExecutor;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.console.options.CommandLineOptions;

import java.io.PrintWriter;
import java.util.List;

public class FirstAndLastPositionOfElementTest {

    @Test
    public void testExecuter() {
        var executer = new FirstAndLastPositionOfElement();
        assertArrayEquals(new int[]{3,4}, executer.execute(new int[]{5,7,7,8,8,10},8));
        assertArrayEquals(new int[]{-1,-1}, executer.execute(new int[]{5,7,7,8,8,10},6));
        assertArrayEquals(new int[]{-1,-1}, executer.execute(new int[]{},0));
    }

    public static void main(String... args) throws Exception {
        CommandLineOptions options = new CommandLineOptions();
        options.setSelectedClasses(List.of(DiscoverySelectors.selectClass(FirstAndLastPositionOfElementTest.class)));
        new ConsoleTestExecutor(options).execute(new PrintWriter(System.out));
    }
}
