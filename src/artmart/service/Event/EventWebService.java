package artmart.service.Event;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import artmart.entities.Event;

public class EventWebService {

    private static final String BASE_URL = "http://127.0.0.1:8000/api";
    private ConnectionRequest connection;

    public EventWebService() {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
    }

    public List<Event> getAllEvents() {
        String url = BASE_URL + "/event";
        this.connection.setUrl(url);
        this.connection.setHttpMethod("GET");
        List<Event> events = new ArrayList<>();
        this.connection.addResponseListener(e -> {
            if (this.connection.getResponseCode() == 200) {
                String response = new String(this.connection.getResponseData());
                try {
                    JSONArray jsonEvents = new JSONArray(response);
                    for (int i = 0; i < jsonEvents.length(); i++) {
                        JSONObject jsonEvent = jsonEvents.getJSONObject(i);

                        Event event = new Event(
                                jsonEvent.getInt("eventid"),
                                jsonEvent.getInt("user"),
                                jsonEvent.getString("name"),
                                jsonEvent.getString("location"),
                                jsonEvent.getString("type"),
                                jsonEvent.getString("description"),
                                jsonEvent.getDouble("entryfee"),
                                jsonEvent.getInt("capacity"),
                                jsonEvent.getString("startdate"),
                                jsonEvent.getString("enddate"),
                                jsonEvent.getString("image"),
                                jsonEvent.getString("status")
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

    public List<Event> getArtistEvents(int artistID) {
        String url = BASE_URL + "/artist/event/" + artistID;
        this.connection.setUrl(url);
        this.connection.setHttpMethod("GET");
        List<Event> events = new ArrayList<>();
        this.connection.addResponseListener(e -> {
            if (this.connection.getResponseCode() == 200) {
                String response = new String(this.connection.getResponseData());
                try {
                    JSONArray jsonEvents = new JSONArray(response);
                    for (int i = 0; i < jsonEvents.length(); i++) {
                        JSONObject jsonEvent = jsonEvents.getJSONObject(i);

                        Event event = new Event(
                                jsonEvent.getInt("eventid"),
                                jsonEvent.getInt("user"),
                                jsonEvent.getString("name"),
                                jsonEvent.getString("location"),
                                jsonEvent.getString("type"),
                                jsonEvent.getString("description"),
                                jsonEvent.getDouble("entryfee"),
                                jsonEvent.getInt("capacity"),
                                jsonEvent.getString("startdate"),
                                jsonEvent.getString("enddate"),
                                jsonEvent.getString("image"),
                                jsonEvent.getString("status")
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

    public void newEvent(Event e) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/event/add");
        this.connection.setHttpMethod("POST");

        connection.addArgument("name", e.getName());
        connection.addArgument("location", e.getLocation());
        connection.addArgument("type", e.getType());
        connection.addArgument("description", e.getDescription());
        connection.addArgument("entryfee", e.getEntryfee() + "");
        connection.addArgument("capacity", e.getCapacity() + "");
        connection.addArgument("startdate", e.getStartdate());
        connection.addArgument("enddate", e.getEnddate());
        connection.addArgument("image", e.getImage());
        connection.addArgument("status", e.getStatus());
        connection.addArgument("user", e.getUser() + "");

        NetworkManager.getInstance().addToQueue(connection);
    }

    public void editEvent(Event e) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/event/" + e.getEventid());
        this.connection.setHttpMethod("PUT");

        connection.addArgument("name", e.getName());
        connection.addArgument("location", e.getLocation());
        connection.addArgument("type", e.getType());
        connection.addArgument("description", e.getDescription());
        connection.addArgument("entryfee", e.getEntryfee() + "");
        connection.addArgument("capacity", e.getCapacity() + "");
        connection.addArgument("startdate", e.getStartdate());
        connection.addArgument("enddate", e.getEnddate());
        connection.addArgument("image", e.getImage());
        connection.addArgument("status", e.getStatus());
        connection.addArgument("user", e.getUser() + "");

        System.out.println(BASE_URL + "/event/" + e.getEventid());
        NetworkManager.getInstance().addToQueue(connection);
    }

    public void delEvent(Event e) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/event/" + e.getEventid());
        this.connection.setHttpMethod("DELETE");
        NetworkManager.getInstance().addToQueue(connection);
    }

//    public void applyCustomProduct(int customProductId) {
//        String url = BASE_URL + "/customproduct/" + customProductId + "/apply";
//
//        // Create a new JSON object to send as the request payload (if needed)
//        JSONObject requestBody = new JSONObject();
//
//        // Send a POST request to the Symfony API endpoint
//        connection.setUrl(url);
//        connection.setHttpMethod("POST");
//        connection.setContentType("application/json");
//        connection.setRequestBody(requestBody.toString());
//
//        // Handle the response from the Symfony API endpoint
//        connection.addResponseListener((evt) -> {
//            String response = new String(connection.getResponseData());
//
//            // Parse the JSON response and handle any errors or exceptions
//            try {
//                JSONObject json = new JSONObject(response);
//                String status = json.getString("status");
//
//                // Handle the response based on the status value
//                if (status.equals("edited")) {
//                    // The apply was successfully created, do something
//                } else {
//                    // Handle other possible status values
//                }
//            } catch (JSONException ex) {
//                // Handle any JSON parsing errors
//            }
//        });
//
//        NetworkManager.getInstance().addToQueueAndWait(connection);
//    }

}
