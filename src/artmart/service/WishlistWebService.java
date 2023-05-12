package artmart.service;

import artmart.entities.Wishlist;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WishlistWebService {
    
   private static final String BASE_URL = "http://127.0.0.1:8000/api";
    private ConnectionRequest connection;

    public WishlistWebService() {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
    }
    
       public List<Wishlist> getAllList() {
        String url = BASE_URL + "/list";
        this.connection.setUrl(url);
        this.connection.addArgument("id", "1");
        this.connection.setHttpMethod("GET");
        List<Wishlist> list = new ArrayList<>();
        this.connection.addResponseListener(e -> {
            if (this.connection.getResponseCode() == 200) {
                String response = new String(this.connection.getResponseData());
                try {
                    JSONArray jsonEvents = new JSONArray(response);
                    for (int i = 0; i < jsonEvents.length(); i++) {
                        JSONObject jsonEvent = jsonEvents.getJSONObject(i);
                        Wishlist mylit = new Wishlist(
                                jsonEvent.getInt("wishlistId"),
                                jsonEvent.getInt("userid"),
                                jsonEvent.getInt("productid"),
                                jsonEvent.getString("date"),
                                jsonEvent.getInt("quantity"),
                                jsonEvent.getInt("price")
                       
                        );
                        list.add(mylit);
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            } else {
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(this.connection);
        return list;
    }

    public void newList(Wishlist c) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/list/add");
        this.connection.setHttpMethod("POST");

        connection.addArgument("userid", c.getUserid()+"");
        connection.addArgument("productid", c.getProductid()+"");
        connection.addArgument("date", c.getDate()+"");
        connection.addArgument("quantity", c.getQuantity()+"");
        connection.addArgument("price", c.getPrice()+"");
    

        NetworkManager.getInstance().addToQueue(connection);
    }
    
    public void editList(Wishlist c) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/list/"+c.getWishlistId());
        this.connection.setHttpMethod("PUT");
        
        connection.addArgument("userid", c.getUserid()+"");
        connection.addArgument("productid", c.getProductid()+"");
        connection.addArgument("date", c.getDate()+"");
        connection.addArgument("quantity", c.getQuantity()+"");
        connection.addArgument("price", c.getPrice()+"");
  
        NetworkManager.getInstance().addToQueue(connection);
    }
    
    public void delList(Wishlist c) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/list/"+c.getWishlistId());
        this.connection.setHttpMethod("DELETE");
        NetworkManager.getInstance().addToQueue(connection);
    }
}

