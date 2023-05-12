package artmart.entities;


public class CustomProduct {
    
    private int customProductId;
    private String name;
    private String description;
    private String dimensions;
    private float weight;
    private String material;
    private String image;
    private int client;
    private Category categoryId;

    public CustomProduct(int customProductId, String name) {
        this.customProductId = customProductId;
        this.name = name;
    }

    public CustomProduct() {
    }

    public CustomProduct(int customProductId, String name, String description, String dimensions, float weight, String material, String image, int client, Category categoryId) {
        this.customProductId = customProductId;
        this.name = name;
        this.description = description;
        this.dimensions = dimensions;
        this.weight = weight;
        this.material = material;
        this.image = image;
        this.client = client;
        this.categoryId = categoryId;
    }

public CustomProduct( String name, String description, String dimensions, float weight, String material, String image, int client, Category categoryId) {
        this.name = name;
        this.description = description;
        this.dimensions = dimensions;
        this.weight = weight;
        this.material = material;
        this.image = image;
        this.client = client;
        this.categoryId = categoryId;
    }

    public int getCustomProductId() {
        return customProductId;
    }

    public void setCustomProductId(int customProductId) {
        this.customProductId = customProductId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public Category getIdCategorie() {
        return categoryId;
    }

    public void setIdCategorie(Category idCategorie) {
        this.categoryId = idCategorie;
    }

    @Override
    public String toString() {
        return "Evenement{" + "idEvenement=" + customProductId + ", nom=" + name + ", descriptif=" + description + ", image=" + dimensions + ", likes=" + weight + ", date_debut=" + material + ", date_fin=" + image + ", nbParticipants=" + client + ", idCategorie=" + categoryId + '}';
    }
    
    
}
