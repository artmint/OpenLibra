package bo.arte.graffiti.openlibra.models;

/**
 * Created by armin on 20-09-17.
 */

public class BooksResponse {
    private boolean success;
    private ResultResponseBook result;

    public boolean isSuccess() {
        return success;
    }

    public ResultResponseBook getResult() {
        return result;
    }
}
