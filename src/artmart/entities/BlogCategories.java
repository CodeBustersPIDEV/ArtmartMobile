package artmart.entities;

public class BlogCategories {

    private int id;
    private String name;
    
    public BlogCategories() {

    }
public BlogCategories(int id) {
        this.id = id;
    }
    public BlogCategories(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public BlogCategories(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
