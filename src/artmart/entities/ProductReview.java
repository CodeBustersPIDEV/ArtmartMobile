/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmart.entities;

public class ProductReview {

    private int productReviewId;
    private int readyProductId;
    private String title;
    private String text;
    private float rating;
    private String date;
    private int user;

    public ProductReview() {
    }

    public ProductReview(int productReviewId, int readyProductId, String title, String text, float rating, String date, int user) {
        this.productReviewId = productReviewId;
        this.readyProductId = readyProductId;
        this.title = title;
        this.text = text;
        this.rating = rating;
        this.date = date;
        this.user = user;
    }

    public ProductReview(int readyProductId, String title, String text, float rating, String date, int user) {
        this.readyProductId = readyProductId;
        this.title = title;
        this.text = text;
        this.rating = rating;
        this.date = date;
        this.user = user;
    }

    public int getProductReviewId() {
        return productReviewId;
    }

    public void setProductReviewId(int productReviewId) {
        this.productReviewId = productReviewId;
    }

    public int getReadyProductId() {
        return readyProductId;
    }

    public void setReadyProductId(int readyProductId) {
        this.readyProductId = readyProductId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ProductReview{" + "productReviewId=" + productReviewId + ", readyProductId=" + readyProductId + ", title=" + title + ", text=" + text + ", rating=" + rating + ", date=" + date + ", user=" + user + '}';
    }

}
