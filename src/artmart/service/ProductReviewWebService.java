/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmart.service;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import artmart.entities.Category;
import artmart.entities.ReadyProduct;
import artmart.entities.ProductReview;

public class ProductReviewWebService {

    private static final String BASE_URL = "http://127.0.0.1:8000/api";
    private ConnectionRequest connection;

    public ProductReviewWebService() {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
    }

    public List<ProductReview> getAllProductReviews(int readyProductId) {
        String url = BASE_URL + "/productreview/" + readyProductId;
        this.connection.setUrl(url);
        this.connection.setHttpMethod("GET");
        List<ProductReview> reviews = new ArrayList<>();
        this.connection.addResponseListener(e -> {
            if (this.connection.getResponseCode() == 200) {
                String response = new String(this.connection.getResponseData());
                try {
                    JSONArray jsonReviews = new JSONArray(response);
                    for (int i = 0; i < jsonReviews.length(); i++) {
                        JSONObject jsonReview = jsonReviews.getJSONObject(i);
                        ProductReview review = new ProductReview(
                                jsonReview.getInt("productReviewId"),
                                jsonReview.getInt("readyProductId"),
                                jsonReview.getString("title"),
                                jsonReview.getString("text"),
                                jsonReview.getFloat("rating"),
                                jsonReview.getString("date"),
                                jsonReview.getInt("user")
                        );
                        reviews.add(review);
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            } else {
                // handle error
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(this.connection);
        return reviews;
    }

    public void newProductReview(ProductReview e) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/productreview/add" + e.getProductReviewId());
        this.connection.setHttpMethod("POST");

        connection.addArgument("title", e.getTitle());
        connection.addArgument("text", e.getText());
        connection.addArgument("rating", e.getRating() + "");
        connection.addArgument("date", e.getDate() + "");
        connection.addArgument("user", e.getUser() + "");

        NetworkManager.getInstance().addToQueue(connection);
    }

    public void deleteProductReview(ProductReview e) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/productreview/" + e.getProductReviewId());
        this.connection.setHttpMethod("DELETE");
        NetworkManager.getInstance().addToQueue(connection);
    }

    public float getAverageRatings(int productId) {
        List<Float> ratings = getRatingsForProduct(productId);

        if (ratings.isEmpty()) {
            return 0.0f;
        } else {
            int sum = 0;
            for (float rating : ratings) {
                sum += rating;
            }
            float average = (float) sum / ratings.size();
            return average;
        }
    }

    private List<Float> getRatingsForProduct(int productId) {
        List<Float> ratings = new ArrayList<>();
        ProductReviewWebService service = new ProductReviewWebService();
        List<ProductReview> reviews = service.getAllProductReviews(productId);
        for (ProductReview review : reviews) {
            ratings.add(review.getRating());
        }

        return ratings;
    }

}
