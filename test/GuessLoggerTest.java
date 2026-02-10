import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.StringWriter;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class GuessLoggerTest {
    private CSVWriterMock mockWriter;
    private GuessLogger guessLogger;

    @BeforeEach
    void setUp() {
        mockWriter = new CSVWriterMock(new StringWriter());
        guessLogger = new GuessLogger();
    }

    @Test
    void testIncrementsRecordCount() {
        //using dependency injection
        GameResult result = new GameResult(true, 50, 5);
        assertEquals(0, mockWriter.getRecordCount(), "Should start with zero records.");
        guessLogger.logStats(result, mockWriter);
        assertEquals(1, mockWriter.getRecordCount(), "Should have exactly one record after logging.");
    }

    @Test
    void testMultipleSequentialLogs() {
        //using dependency injection
        GameResult res1 = new GameResult(true, 10, 1);
        GameResult res2 = new GameResult(true, 20, 2);
        GameResult res3 = new GameResult(true, 30, 3);

        guessLogger.logStats(res1, mockWriter);
        guessLogger.logStats(res2, mockWriter);
        guessLogger.logStats(res3, mockWriter);

        assertEquals(3, mockWriter.getRecordCount(), "Should track multiple logs correctly.");
        assertEquals("1", mockWriter.getWrittenRecords(0)[1]);
        assertEquals("2", mockWriter.getWrittenRecords(1)[1]);
        assertEquals("3", mockWriter.getWrittenRecords(2)[1]);
    }

    @Test
    void testLogTimestampIsWithinRange() {
        //using dependency injection
        GameResult result = new GameResult(true, 50, 5);
        LocalDateTime before = LocalDateTime.now();
        guessLogger.logStats(result, mockWriter);
        LocalDateTime after = LocalDateTime.now();
        String[] record = mockWriter.getWrittenRecords(0);
        LocalDateTime loggedTime = LocalDateTime.parse(record[0]);
        assertFalse(loggedTime.isBefore(before), "Timestamp should not be earlier than the start of the test.");
        assertFalse(loggedTime.isAfter(after), "Timestamp should not be later than the end of the test.");
    }
}