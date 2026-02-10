import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class GuessLogger {
    public void logStats(GameResult result, CSVWriter writer) {
        String [] record = new String[2];
        record[0] = LocalDateTime.now().toString();
        record[1] = Integer.toString(result.numGuesses);

        writer.writeNext(record);
    }

    public void logStats(GameResult result) {
        try {
            logStats(result, new CSVWriter(new FileWriter(StatsFile.FILENAME, true)));
        } catch (IOException e) {
            // NOTE: In a full implementation, we would log this error and possibly alert the user
            // NOTE: For this project, you do not need unit tests for handling this exception.
        }
    }
}
