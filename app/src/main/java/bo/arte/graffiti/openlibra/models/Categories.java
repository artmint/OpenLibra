package bo.arte.graffiti.openlibra.models;

/**
 * Created by armin on 20-09-17.
 */

public class Categories {
    private String category_id;
    private String name;
    private String nicenames;

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNicenames() {
        return nicenames;
    }

    public void setNicenames(String nicenames) {
        this.nicenames = nicenames;
    }
}
