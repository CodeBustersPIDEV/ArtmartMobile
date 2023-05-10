package artmart.service;

import artmart.entities.BlogCategories;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import artmart.entities.Blogs;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;

public class BlogsWebService {

    private static final String BASE_URL = "http://127.0.0.1:8000/api";
    private ConnectionRequest connection;
    BlogCategories finalCategory = new BlogCategories();
    BlogCategories notFoundcategory = new BlogCategories(0, "N/A");

    public BlogsWebService() {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
    }

    public List<Blogs> getAllBlogs() {
        String url = BASE_URL + "/AllBlogs";
        this.connection.setUrl(url);
        this.connection.setHttpMethod("GET");
        BlogCategoriesWebService serviceCat = new BlogCategoriesWebService();
        List<Blogs> blogs = new ArrayList<>();
        this.connection.addResponseListener(e -> {
            if (this.connection.getResponseCode() == 200) {
                String response = new String(this.connection.getResponseData());
                try {
                    JSONArray jsonEvents = new JSONArray(response);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    for (int i = 0; i < jsonEvents.length(); i++) {
                        JSONObject jsonEvent = jsonEvents.getJSONObject(i);
                        float rating = jsonEvent.isNull("rating") ? 0 : jsonEvent.getFloat("rating");
                        int nbViews = jsonEvent.isNull("nbViews") ? 0 : jsonEvent.getInt("nbViews");
                        Blogs event = new Blogs(
                                jsonEvent.getInt("blogID"),
                                jsonEvent.getString("title"),
                                jsonEvent.getString("content"),
                                dateFormat.parse(jsonEvent.getJSONObject("date").getString("date")),
                                rating,
                                nbViews,
                                serviceCat.getOneBlogCategory(jsonEvent.getInt("category")),
                                jsonEvent.getInt("author"),
                                jsonEvent.getString("image")
                        );
                        blogs.add(event);
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                } catch (ParseException ex) {
                    System.out.println(ex);
                }
            } else {
                // handle error
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(this.connection);
        return blogs;
    }

    public void newBlog(Blogs e) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/blogNew/add");
        this.connection.setHttpMethod("POST");

        connection.addArgument("title", e.getTitle());
        connection.addArgument("content", e.getContent());
        connection.addArgument("author", e.getAuthor() + "");
        connection.addArgument("categoryId", e.getCategory() + "");

        NetworkManager.getInstance().addToQueue(connection);
    }
//
//    public void editCp(CustomProduct e) {
//        connection = new ConnectionRequest();
//        connection.setInsecure(true);
//        this.connection.setUrl(BASE_URL + "/customproduct/" + e.getCustomProductId());
//        this.connection.setHttpMethod("PUT");
//
//        connection.addArgument("name", e.getName());
//        connection.addArgument("description", e.getDescription());
//        connection.addArgument("dimensions", e.getDimensions());
//        connection.addArgument("weight", e.getWeight() + "");
//        connection.addArgument("material", e.getMaterial());
//        connection.addArgument("image", e.getImage());
//        connection.addArgument("client", e.getClient() + "");
//        connection.addArgument("categoryId", e.getIdCategorie().getCategoriesId() + "");
//
//        System.out.println(BASE_URL + "/customproduct/" + e.getCustomProductId());
//        NetworkManager.getInstance().addToQueue(connection);
//    }
//

    public void delBlog(Blogs e) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/blog/" + e.getId());
        this.connection.setHttpMethod("DELETE");
        NetworkManager.getInstance().addToQueue(connection);
    }
//    
//public void applyCustomProduct(int customProductId) {
//    String url = BASE_URL + "/customproduct/" + customProductId + "/apply";
//
//    // Create a new JSON object to send as the request payload (if needed)
//    JSONObject requestBody = new JSONObject();
//
//    // Send a POST request to the Symfony API endpoint
//    connection.setUrl(url);
//    connection.setHttpMethod("POST");
//    connection.setContentType("application/json");
//    connection.setRequestBody(requestBody.toString());
//
//    // Handle the response from the Symfony API endpoint
//    connection.addResponseListener((evt) -> {
//        String response = new String(connection.getResponseData());
//
//        // Parse the JSON response and handle any errors or exceptions
//        try {
//            JSONObject json = new JSONObject(response);
//            String status = json.getString("status");
//
//            // Handle the response based on the status value
//            if (status.equals("edited")) {
//                // The apply was successfully created, do something
//            } else {
//                // Handle other possible status values
//            }
//        } catch (JSONException ex) {
//            // Handle any JSON parsing errors
//        }
//    });
//
//    NetworkManager.getInstance().addToQueueAndWait(connection);
//}

}
