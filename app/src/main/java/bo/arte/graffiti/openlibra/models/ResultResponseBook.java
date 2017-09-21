package bo.arte.graffiti.openlibra.models;

import java.util.List;

/**
 * Created by armin on 20-09-17.
 */

public class ResultResponseBook {

    private List<Book> records;

    public List<Book> getRecords() {
        return records;
    }

    public void setRecords(List<Book> records) {
        this.records = records;
    }
}
