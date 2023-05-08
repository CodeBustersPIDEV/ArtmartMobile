package artmart.entities;

public class Category {
    private int categoriesId;
    private String name;


    public Category() {
    }

    public Category(int idCategorie, String nom) {
        this.categoriesId = idCategorie;
        this.name = nom;
  
    }

    public Category(String name) {
        this.name = name;
    }


    public Category(int idCategorie) {
        this.categoriesId = idCategorie;
    }

    public int getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(int categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return name;
    }
    
    
}
