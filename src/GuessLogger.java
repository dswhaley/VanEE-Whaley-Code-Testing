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

        // Debugging...
        System.out.println("Logged game result: " + record[0] + ", " + record[1]);

        // None of our games are making it to the CSV file even though they did on the fresh copy...
        // Not sure if it's a bug to keep (writer is never flushed, nor closed) or if someone broke something

        // Looks like the lack of a flush/close was the issue. Going to check our tests
        // but since we aren't supposed to be fixing bugs will leave it commented out
//        try {
//            writer.close();
//        } catch (IOException e) {
//        }
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
