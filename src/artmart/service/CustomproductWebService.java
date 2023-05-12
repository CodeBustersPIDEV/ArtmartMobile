package artmart.service;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import artmart.entities.Category;
import artmart.entities.CustomProduct;

public class CustomproductWebService {

    private static final String BASE_URL = "http://127.0.0.1:8000/api";
    private ConnectionRequest connection;

    public CustomproductWebService() {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
    }

    public List<CustomProduct> getAllcp() {
        String url = BASE_URL + "/customproduct";
        this.connection.setUrl(url);
        this.connection.setHttpMethod("GET");
        List<CustomProduct> events = new ArrayList<>();
        this.connection.addResponseListener(e -> {
            if (this.connection.getResponseCode() == 200) {
                String response = new String(this.connection.getResponseData());
                try {
                    JSONArray jsonEvents = new JSONArray(response);
                    for (int i = 0; i < jsonEvents.length(); i++) {
                        JSONObject jsonEvent = jsonEvents.getJSONObject(i);
                        CustomProduct event = new CustomProduct(
                                jsonEvent.getInt("customProductId"),
                                jsonEvent.getString("product"),
                        jsonEvent.getString("product1"),
                        jsonEvent.getString("product2"),
                        jsonEvent.getFloat("product3"),
                        jsonEvent.getString("product4"),
                        jsonEvent.getString("product5"),
                        jsonEvent.getInt("client"),
                                 new Category(jsonEvent.getInt("product6"))
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

    public void newCp(CustomProduct e) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/customproduct/add");
        this.connection.setHttpMethod("POST");

        connection.addArgument("name", e.getName());
        connection.addArgument("description", e.getDescription());
        connection.addArgument("dimensions", e.getDimensions());
        connection.addArgument("weight", e.getWeight() + "");
        connection.addArgument("material", e.getMaterial());
        connection.addArgument("image", e.getImage());
        connection.addArgument("client", e.getClient() + "");
        System.out.println(e.getIdCategorie().getCategoriesId());
        connection.addArgument("categoryId", e.getIdCategorie().getCategoriesId() + "");

        NetworkManager.getInstance().addToQueue(connection);
    }

    public void editCp(CustomProduct e) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/customproduct/" + e.getCustomProductId());
        this.connection.setHttpMethod("PUT");

        connection.addArgument("name", e.getName());
        connection.addArgument("description", e.getDescription());
        connection.addArgument("dimensions", e.getDimensions());
        connection.addArgument("weight", e.getWeight() + "");
        connection.addArgument("material", e.getMaterial());
        connection.addArgument("image", e.getImage());
        connection.addArgument("client", e.getClient() + "");
        connection.addArgument("categoryId", e.getIdCategorie().getCategoriesId() + "");

        System.out.println(BASE_URL + "/customproduct/" + e.getCustomProductId());
        NetworkManager.getInstance().addToQueue(connection);
    }

    public void delcp(CustomProduct e) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/customproduct/" + e.getCustomProductId());
        this.connection.setHttpMethod("DELETE");
        NetworkManager.getInstance().addToQueue(connection);
    }
    
public void applyCustomProduct(int customProductId) {
    String url = BASE_URL + "/customproduct/" + customProductId + "/apply";

    // Create a new JSON object to send as the request payload (if needed)
    JSONObject requestBody = new JSONObject();

    // Send a POST request to the Symfony API endpoint
    connection.setUrl(url);
    connection.setHttpMethod("POST");
    connection.setContentType("application/json");
    connection.setRequestBody(requestBody.toString());

    // Handle the response from the Symfony API endpoint
    connection.addResponseListener((evt) -> {
        String response = new String(connection.getResponseData());

        // Parse the JSON response and handle any errors or exceptions
        try {
            JSONObject json = new JSONObject(response);
            String status = json.getString("status");

            // Handle the response based on the status value
            if (status.equals("edited")) {
                // The apply was successfully created, do something
            } else {
                // Handle other possible status values
            }
        } catch (JSONException ex) {
            // Handle any JSON parsing errors
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(connection);
}


}
