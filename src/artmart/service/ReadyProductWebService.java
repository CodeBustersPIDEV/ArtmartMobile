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

public class ReadyProductWebService {

    private static final String BASE_URL = "http://127.0.0.1:8000/api";
    private ConnectionRequest connection;

    public ReadyProductWebService() {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
    }

    public List<ReadyProduct> getAllReadyProducts() {
        String url = BASE_URL + "/readyproduct";
        this.connection.setUrl(url);
        this.connection.setHttpMethod("GET");
        List<ReadyProduct> events = new ArrayList<>();
        this.connection.addResponseListener(e -> {
            if (this.connection.getResponseCode() == 200) {
                String response = new String(this.connection.getResponseData());
                try {
                    JSONArray jsonEvents = new JSONArray(response);
                    for (int i = 0; i < jsonEvents.length(); i++) {
                        JSONObject jsonEvent = jsonEvents.getJSONObject(i);
                        ReadyProduct event = new ReadyProduct(
                                jsonEvent.getInt("readyProductId"),
                                jsonEvent.getString("product"),
                                jsonEvent.getString("product1"),
                                jsonEvent.getString("product2"),
                                jsonEvent.getFloat("product3"),
                                jsonEvent.getString("product4"),
                                jsonEvent.getString("product5"),
                                jsonEvent.getInt("user"),
                                jsonEvent.getInt("product6"),
                                new Category(jsonEvent.getInt("product7"))
                        );
                        events.add(event);
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            } else {
                // handle error
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(this.connection);
        return events;
    }

    public void newReadyProduct(ReadyProduct e) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/readyproduct/add");
        this.connection.setHttpMethod("POST");

        connection.addArgument("name", e.getName());
        connection.addArgument("description", e.getDescription());
        connection.addArgument("dimensions", e.getDimensions());
        connection.addArgument("weight", e.getWeight() + "");
        connection.addArgument("material", e.getMaterial());
        connection.addArgument("image", e.getImage());
        connection.addArgument("user", e.getUser() + "");
        connection.addArgument("price", e.getPrice() + "");
        System.out.println(e.getCategoryId().getCategoriesId());
        connection.addArgument("categoryId", e.getCategoryId().getCategoriesId() + "");

        NetworkManager.getInstance().addToQueue(connection);
    }

    public void editReadyProduct(ReadyProduct e) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/readyproduct/" + e.getReadyProductId());
        this.connection.setHttpMethod("PUT");

        connection.addArgument("name", e.getName());
        connection.addArgument("description", e.getDescription());
        connection.addArgument("dimensions", e.getDimensions());
        connection.addArgument("weight", e.getWeight() + "");
        connection.addArgument("material", e.getMaterial());
        connection.addArgument("image", e.getImage());
        connection.addArgument("user", e.getUser() + "");
        connection.addArgument("price", e.getPrice() + "");
        connection.addArgument("categoryId", e.getCategoryId().getCategoriesId() + "");

        System.out.println(BASE_URL + "/readyproduct/" + e.getReadyProductId());
        NetworkManager.getInstance().addToQueue(connection);
    }

    public void deleteReadyProduct(ReadyProduct e) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/readyproduct/" + e.getReadyProductId());
        this.connection.setHttpMethod("DELETE");
        NetworkManager.getInstance().addToQueue(connection);
    }

    public ReadyProduct getReadyProductById(int id) {
        String url = BASE_URL + "/readyproduct/" + id;
        this.connection.setUrl(url);
        this.connection.setHttpMethod("GET");
        final ReadyProduct[] product = {null};

        this.connection.addResponseListener(e -> {
            if (this.connection.getResponseCode() == 200) {
                String response = new String(this.connection.getResponseData());
                try {
                    JSONObject jsonProduct = new JSONObject(response);
                    product[0] = new ReadyProduct(
                            jsonProduct.getInt("readyProductId"),
                            jsonProduct.getString("product"),
                            jsonProduct.getString("product1"),
                            jsonProduct.getString("product2"),
                            jsonProduct.getFloat("product3"),
                            jsonProduct.getString("product4"),
                            jsonProduct.getString("product5"),
                            jsonProduct.getInt("user"),
                            jsonProduct.getInt("product6"),
                            new Category(jsonProduct.getInt("product7"))
                    );
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println("Ready Product not found !");
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(this.connection);
        return product[0];
    }

}
