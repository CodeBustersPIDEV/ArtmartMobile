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
import artmart.entities.User;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author 21697
 */
public class UserWebService {

    private static final String BASE_URL = "http://127.0.0.1:8000/api_user";
    private ConnectionRequest connection;

    public UserWebService() {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
    }

    public List<User> getAllU() {
        String url = BASE_URL + "/user";
        this.connection.setUrl(url);
        this.connection.setHttpMethod("GET");
        List<User> events = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.connection.addResponseListener(e -> {
            if (this.connection.getResponseCode() == 200) {
                String response = new String(this.connection.getResponseData());

                JSONArray jsonEvents = new JSONArray(response);
                for (int i = 0; i < jsonEvents.length(); i++) {
                    JSONObject jsonEvent = jsonEvents.getJSONObject(i);
                    User event = new User(
                            jsonEvent.getInt("user_id"),
                            jsonEvent.getInt("phonenumber"),
                            jsonEvent.getString("name"),
                            jsonEvent.getString("email"),
                            jsonEvent.getString("username"),
                            jsonEvent.getString("password"),
                            jsonEvent.getString("role"),
                            jsonEvent.getString("file")
                    );
                    events.add(event);
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(this.connection);
        return events;
    }

    public void newU(User e) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/user/add");
        this.connection.setHttpMethod("POST");

        connection.addArgument("name", e.getName());
        connection.addArgument("username", e.getUsername());
        connection.addArgument("email", e.getEmail());
        connection.addArgument("password", e.getPwd());
        connection.addArgument("phonenumber", Integer.toString(e.getPhone_nbr()));
        connection.addArgument("birthday", e.getBirthday());
        connection.addArgument("role", e.getRole());

        NetworkManager.getInstance().addToQueue(connection);
    }

    public void delU(User e) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/user/delete/" + e.getUser_id());
        this.connection.setHttpMethod("DELETE");
        NetworkManager.getInstance().addToQueue(connection);
    }

    public void editU(User e) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/user/edit/" + e.getUser_id());
        this.connection.setHttpMethod("PUT");

        connection.addArgument("name", e.getName());
        connection.addArgument("username", e.getUsername());
        connection.addArgument("email", e.getEmail());
        connection.addArgument("password", e.getPwd());
        connection.addArgument("phonenumber", Integer.toString(e.getPhone_nbr()));
        connection.addArgument("role", e.getRole());
        connection.addArgument("birthday", e.getBirthday());

        System.out.println(BASE_URL + "/user/edit/" + e.getUser_id());
        NetworkManager.getInstance().addToQueue(connection);
    }

}
