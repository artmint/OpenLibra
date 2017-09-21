package bo.arte.graffiti.openlibra.models;

import java.util.List;

/**
 * Created by armin on 20-09-17.
 */

public class ResultResponseCategory {

    private List<Categories> records;

    public List<Categories> getRecords() {
        return records;
    }

    public void setRecords(List<Categories> records) {
        this.records = records;
    }
}
