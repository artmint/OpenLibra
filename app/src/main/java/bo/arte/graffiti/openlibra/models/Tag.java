package bo.arte.graffiti.openlibra.models;

/**
 * Created by armin on 20-09-17.
 */

public class Tag {
    private String tag_id;
    private String name;
    private String nicename;

    public String getTag_id() {
        return tag_id;
    }

    public void setTag_id(String tag_id) {
        this.tag_id = tag_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNicename() {
        return nicename;
    }

    public void setNicename(String nicename) {
        this.nicename = nicename;
    }
}
