///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.junit.jupiter:junit-jupiter-engine:5.10.1
//DEPS org.junit.platform:junit-platform-console:1.9.3
//SOURCES Chess.java

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.platform.console.tasks.ConsoleTestExecutor;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.console.options.CommandLineOptions;

import java.io.PrintWriter;
import java.util.List;

public class ChessTest {

    @Test
    public void testQueenSolvesIn4SizedBoard() {
        var solves = new Chess(Chess.Piece.QUEEN).getSolvesForBoardSize(4);
        assertEquals(
                """
                [X][Q][X][X]
                [X][X][X][Q]
                [Q][X][X][X]
                [X][X][Q][X]
                """, solves.get(0));
        assertEquals(
                """
                [X][X][Q][X]
                [Q][X][X][X]
                [X][X][X][Q]
                [X][Q][X][X]
                """, solves.get(1));
        assertEquals(2, solves.size());
    }
    @Test
    public void testQueenSolvesIn5SizedBoard() {
        var solves = new Chess(Chess.Piece.QUEEN).getSolvesForBoardSize(5);
        assertEquals(10, solves.size());
    }

    public static void main(String... args) throws Exception {
        CommandLineOptions options = new CommandLineOptions();
        options.setSelectedClasses(List.of(DiscoverySelectors.selectClass(ChessTest.class)));
        new ConsoleTestExecutor(options).execute(new PrintWriter(System.out));
    }
}
