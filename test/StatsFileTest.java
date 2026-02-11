import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StatsFileTest {

    @Test
    void testInvalidNumberThrowsException() {
        String badInput = "2024-01-01T12:00:00,abc";
        StringReader stubReader = new StringReader(badInput);

        StatsFile statsFile = new StatsFile();
        LocalDateTime limit = LocalDateTime.now().minusDays(30);

        assertThrows(NumberFormatException.class, () -> {
            statsFile.readFromCSV(stubReader, limit);
        });
    }

    @Test
    void testInvalidDateThrowsException() {
        String badInput = "not-a-date,5";
        StringReader stubReader = new StringReader(badInput);

        StatsFile statsFile = new StatsFile();
        LocalDateTime limit = LocalDateTime.now().minusDays(30);

        assertThrows(DateTimeParseException.class, () -> {
            statsFile.readFromCSV(stubReader, limit);
        });
    }


    @Test
    void testNumGamesWithExistingGuesses() {
        StatsFile statsFile = new StatsFile();
        statsFile.getStatsMap().clear();
        statsFile.getStatsMap().put(1, 2);
        statsFile.getStatsMap().put(3, 5);

        assertEquals(2, statsFile.numGames(1));
        assertEquals(5, statsFile.numGames(3));
    }

    @Test
    void testNumGamesWithNonExistentGuesses() {
        StatsFile statsFile = new StatsFile();
        statsFile.getStatsMap().clear();
        statsFile.getStatsMap().put(2, 4);

        assertEquals(0, statsFile.numGames(5)); // key 5 not present
    }

    @Test
    void testNumGamesWithEmptyMap() {
        StatsFile statsFile = new StatsFile();
        statsFile.getStatsMap().clear();

        assertEquals(0, statsFile.numGames(1));
    }


    @Test
    void testMaxNumGuessesWithMultipleEntries() {
        StatsFile statsFile = new StatsFile();
        statsFile.getStatsMap().clear();
        statsFile.getStatsMap().put(1, 2);
        statsFile.getStatsMap().put(4, 1);
        statsFile.getStatsMap().put(3, 5);

        assertEquals(4, statsFile.maxNumGuesses());
    }

    @Test
    void testMaxNumGuessesWithSingleEntry() {
        StatsFile statsFile = new StatsFile();
        statsFile.getStatsMap().clear();
        statsFile.getStatsMap().put(7, 1);

        assertEquals(7, statsFile.maxNumGuesses());
    }

    @Test
    void testMaxNumGuessesWithEmptyMap() {
        StatsFile statsFile = new StatsFile();
        statsFile.getStatsMap().clear();

        assertEquals(0, statsFile.maxNumGuesses());
    }
}
