package artmart.service;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import artmart.entities.Tags;

public class TagWebService {

    private static final String BASE_URL = "http://127.0.0.1:8000/api";
    private ConnectionRequest connection;

    public TagWebService() {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
    }

    public List<Tags> getAllCategorie() {
        String url = BASE_URL + "/Tags";
        this.connection.setUrl(url);
        this.connection.setHttpMethod("GET");
        List<Tags> categories = new ArrayList<>();
        this.connection.addResponseListener(e -> {
            if (this.connection.getResponseCode() == 200) {
                String response = new String(this.connection.getResponseData());
                try {
                    JSONArray jsonEvents = new JSONArray(response);
                    for (int i = 0; i < jsonEvents.length(); i++) {
                        JSONObject jsonEvent = jsonEvents.getJSONObject(i);
                        Tags categorie = new Tags(
                                jsonEvent.getInt("tagId"),
                                jsonEvent.getString("name")
                        );
                        categories.add(categorie);
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            } else {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(this.connection);
        return categories;
    }

    public Tags getOneBlogCategory(int id) {
        String url = BASE_URL + "/OneTag/";
        this.connection.setUrl(BASE_URL + "/OneBlogCategory/" + id);
        this.connection.setHttpMethod("GET");
        Tags category = new Tags();
        this.connection.addResponseListener(e -> {
            if (this.connection.getResponseCode() == 200) {
                String response = new String(this.connection.getResponseData());
                try {
                    JSONArray jsonEvents = new JSONArray(response);
                    for (int i = 0; i < jsonEvents.length(); i++) {
                        JSONObject jsonEvent = jsonEvents.getJSONObject(i);
                        if (jsonEvent.getInt("tagId") == id) {
                            category.setId(jsonEvent.getInt("tagId"));
                            category.setName(jsonEvent.getString("name"));
                        }
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            } else {
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(this.connection);
        return category;
    }

    public void newCategorie(Tags c) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/Tag/add");
        this.connection.setHttpMethod("POST");

        connection.addArgument("name", c.getName());

        NetworkManager.getInstance().addToQueue(connection);
    }

    public void editCategorie(Tags c) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/Tag/" + c.getId());
        this.connection.setHttpMethod("PUT");

        connection.addArgument("name", c.getName());

        NetworkManager.getInstance().addToQueue(connection);
    }

    public void delCategorie(Tags c) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/TagDel/" + c.getId());
        this.connection.setHttpMethod("DELETE");
        NetworkManager.getInstance().addToQueue(connection);
    }

}
