import com.opencsv.CSVWriter;

import java.io.Writer;
import java.util.ArrayList;

public class CSVWriterMock extends CSVWriter {
    private ArrayList<String[]> writtenRecords = new ArrayList<>();

    public CSVWriterMock(Writer writer) {
        super(writer);
    }

    @Override
    public void writeNext(String[] record) {
        writtenRecords.add(record);
    }

    @Override
    public void close() {}

    public String[] getWrittenRecords(int index) {
        return writtenRecords.get(index);
    }

    public int getRecordCount() {
        return writtenRecords.size();
    }
}