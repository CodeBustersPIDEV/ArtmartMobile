package artmart.service;

import artmart.entities.PaymentOption;
import artmart.entities.Shippingoption;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PaymentShipWebService {

    private static final String BASE_URL = "http://127.0.0.1:8000/api";
    private ConnectionRequest connection;

    public PaymentShipWebService() {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
    }

    public List<PaymentOption> getAllList() {
        String url = BASE_URL + "/paymentoption";
        this.connection.setUrl(url);
        this.connection.setHttpMethod("GET");
        List<PaymentOption> list = new ArrayList<>();
        this.connection.addResponseListener(e -> {
            if (this.connection.getResponseCode() == 200) {
                String response = new String(this.connection.getResponseData());
                try {
                    JSONArray jsonEvents = new JSONArray(response);
                    for (int i = 0; i < jsonEvents.length(); i++) {
                        JSONObject jsonEvent = jsonEvents.getJSONObject(i);
                        PaymentOption mylit = new PaymentOption(
                                jsonEvent.getInt("paymentoptionId"),
                                jsonEvent.getString("name"),
                                jsonEvent.getString("availablecountries")
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

    public List<Shippingoption> getAllListSo() {
        String url = BASE_URL + "/shippingoption";
        this.connection.setUrl(url);
        this.connection.setHttpMethod("GET");
        List<Shippingoption> list = new ArrayList<>();
        this.connection.addResponseListener(e -> {
            if (this.connection.getResponseCode() == 200) {
                String response = new String(this.connection.getResponseData());
                try {
                    JSONArray jsonEvents = new JSONArray(response);
                    for (int i = 0; i < jsonEvents.length(); i++) {
                        JSONObject jsonEvent = jsonEvents.getJSONObject(i);
                        Shippingoption mylit = new Shippingoption(
                                jsonEvent.getString("name"),
                                jsonEvent.getString("carrier"),
                                jsonEvent.getString("shippingspeed"),
                                jsonEvent.getString("shippingfee"),
                                jsonEvent.getString("availableregions")
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
}
