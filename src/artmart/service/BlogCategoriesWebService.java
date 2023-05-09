package artmart.service;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import artmart.entities.BlogCategories;

public class BlogCategoriesWebService {

    private static final String BASE_URL = "http://127.0.0.1:8000/api";
    private ConnectionRequest connection;

    public BlogCategoriesWebService() {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
    }

    public List<BlogCategories> getAllCategorie() {
        String url = BASE_URL + "/BlogCategory";
        this.connection.setUrl(url);
        this.connection.setHttpMethod("GET");
        List<BlogCategories> categories = new ArrayList<>();
        this.connection.addResponseListener(e -> {
            if (this.connection.getResponseCode() == 200) {
                String response = new String(this.connection.getResponseData());
                try {
                    JSONArray jsonEvents = new JSONArray(response);
                    for (int i = 0; i < jsonEvents.length(); i++) {
                        JSONObject jsonEvent = jsonEvents.getJSONObject(i);
                        BlogCategories categorie = new BlogCategories(
                                jsonEvent.getInt("categoriesId"),
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

    public void newCategorie(BlogCategories c) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/BlogCategory/add");
        this.connection.setHttpMethod("POST");
        
        connection.addArgument("name", c.getName());
    

        NetworkManager.getInstance().addToQueue(connection);
    }
    
    public void editCategorie(BlogCategories c) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/BlogCategory/"+c.getId());
        this.connection.setHttpMethod("PUT");
        
        connection.addArgument("name", c.getName());
  
        NetworkManager.getInstance().addToQueue(connection);
    }
    
    public void delCategorie(BlogCategories c) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/BlogCategoryDel/"+c.getId());
        this.connection.setHttpMethod("DELETE");
        NetworkManager.getInstance().addToQueue(connection);
    }

}
