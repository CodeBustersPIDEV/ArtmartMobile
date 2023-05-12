package artmart.service;

import artmart.entities.Apply;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import artmart.entities.Category;
public class ApplyWebService {
    
   private static final String BASE_URL = "http://127.0.0.1:8000/api";
    private ConnectionRequest connection;

    public ApplyWebService() {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
    }
    
       public List<Apply> getAllApply() {
        String url = BASE_URL + "/apply";
        this.connection.setUrl(url);
        this.connection.setHttpMethod("GET");
        List<Apply> applies = new ArrayList<>();
        this.connection.addResponseListener(e -> {
            if (this.connection.getResponseCode() == 200) {
                String response = new String(this.connection.getResponseData());
                try {
                    JSONArray jsonEvents = new JSONArray(response);
                    for (int i = 0; i < jsonEvents.length(); i++) {
                        JSONObject jsonEvent = jsonEvents.getJSONObject(i);
                        Apply apply = new Apply(
                                jsonEvent.getInt("applyId"),
                                jsonEvent.getString("status"),
                                   jsonEvent.getString("artist"),
                                              jsonEvent.getString("customproduct")
                       
                        );
                        applies.add(apply);
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            } else {
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(this.connection);
        return applies;
    }

public void applyCustomProduct(int applyId) {
    String url = BASE_URL + "/apply/" + applyId + "/apply";

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
public void delApply(Apply c) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/apply/"+c.getApplyId());
        this.connection.setHttpMethod("DELETE");
        NetworkManager.getInstance().addToQueue(connection);
    }
}

