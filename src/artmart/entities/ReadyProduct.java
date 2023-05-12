/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmart.entities;


/**
 *
 * @author rymae
 */
public class ReadyProduct {

    private int readyProductId;
    private String name;
    private String description;
    private String dimensions;
    private float weight;
    private String material;
    private String image;
    private int user;
    private int price;
    private Category categoryId;

    public ReadyProduct(int readyProductId, String name) {
        this.readyProductId = readyProductId;
        this.name = name;
    }

    public ReadyProduct() {
    }

    public ReadyProduct(int readyProductId, String name, String description, String dimensions, float weight, String material, String image, int user, int price, Category categoryId) {
        this.readyProductId = readyProductId;
        this.name = name;
        this.description = description;
        this.dimensions = dimensions;
        this.weight = weight;
        this.material = material;
        this.image = image;
        this.user = user;
        this.price = price;
        this.categoryId = categoryId;
    }

    public ReadyProduct(String name, String description, String dimensions, float weight, String material, String image, int user, int price, Category categoryId) {
        this.name = name;
        this.description = description;
        this.dimensions = dimensions;
        this.weight = weight;
        this.material = material;
        this.image = image;
        this.user = user;
        this.price = price;
        this.categoryId = categoryId;
    }

    public int getReadyProductId() {
        return readyProductId;
    }

    public void setReadyProductId(int readyProductId) {
        this.readyProductId = readyProductId;
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

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "ReadyProduct{" + "readyProductId=" + readyProductId + ", name=" + name + ", description=" + description + ", dimensions=" + dimensions + ", weight=" + weight + ", material=" + material + ", image=" + image + ", user=" + user + ", price=" + price + ", categoryId=" + categoryId + '}';
    }

}
