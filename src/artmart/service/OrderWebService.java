package artmart.service;

import artmart.entities.Order;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OrderWebService {
    
   private static final String BASE_URL = "http://127.0.0.1:8000/api";
    private ConnectionRequest connection;

    public OrderWebService() {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
    }
    
       public List<Order> getAllList() {
        String url = BASE_URL + "/order";
        this.connection.setUrl(url);
        this.connection.setHttpMethod("GET");
        List<Order> list = new ArrayList<>();
        this.connection.addResponseListener(e -> {
            if (this.connection.getResponseCode() == 200) {
                String response = new String(this.connection.getResponseData());
                try {
                    JSONArray jsonEvents = new JSONArray(response);
                    for (int i = 0; i < jsonEvents.length(); i++) {
                        JSONObject jsonEvent = jsonEvents.getJSONObject(i);
                        Order order = new Order(
                                jsonEvent.getInt("orderId"),
                                jsonEvent.getInt("quantity"),
                                jsonEvent.getString("shippingaddress"),
                                jsonEvent.getString("orderdate"),
                                jsonEvent.getDouble("totalcost"),
                                jsonEvent.getInt("userid"),
                                jsonEvent.getInt("productid"),
                                jsonEvent.getInt("shippingmethod"),
                                jsonEvent.getInt("paymentmethod")
                        );
                        list.add(order);
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

    public void newOrder(Order c) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/order/add");
        this.connection.setHttpMethod("POST");
        System.out.println(BASE_URL + "/order/add");

        connection.addArgument("quantity", c.getQuantity()+"");
        connection.addArgument("shippingaddress", c.getShippingaddress()+"");
        connection.addArgument("orderdate", c.getOrderdate()+"");
        connection.addArgument("totalcost", c.getTotalcost()+"");
        connection.addArgument("userid", c.getUserid()+"");
        connection.addArgument("productid", c.getProductid()+"");
        connection.addArgument("shippingmethod", c.getShippingmethod()+"");
        connection.addArgument("paymentmethod", c.getPaymentmethod()+"");
    

        NetworkManager.getInstance().addToQueue(connection);
    }
    
}

