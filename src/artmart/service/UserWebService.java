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
import org.json.JSONObject;
import artmart.entities.User;
import artmart.forms.GetUserForm;
import com.codename1.io.JSONParser;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import java.util.Map;
import com.codename1.ui.Dialog;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import artmart.forms.SessionManager;
 
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
    public static UserWebService instance = null;

    public static UserWebService getInstance() {
        if (instance == null) {
            instance = new UserWebService();
        }
        return instance;
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
            this.connection.setHttpMethod("POST");

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

   public void signin(TextField username, TextField password, Resources rs) {

    connection = new ConnectionRequest() {
        @Override
        protected void readResponse(InputStream input) throws IOException {
    JSONParser parser = new JSONParser();
    Map<String, Object> response = parser.parseJSON(new InputStreamReader(input));
int id;
    if (response != null && response.containsKey("success")) {
       String successStr = (String) response.get("success");
boolean success = Boolean.parseBoolean(successStr);

        if (success) {
            
            double data =(double)response.get("data");
            id=(int)data;
            SessionManager.getInstance().setUserId(id);
            System.out.println(id);
             GetUserForm f = null;
            try {
                f = new GetUserForm();
            } catch (IOException ex) {
            }
            f.show();
        } else {
            // Handle failed login
            if (response.containsKey("message")) {
                String errorMessage = (String) response.get("message");
                Dialog.show("Login Failed", errorMessage, "OK", null);
            } else {
                Dialog.show("Login Failed", "Unknown error occurred", "OK", null);
            }
        }
    } else {
        Dialog.show("Login Failed", "Invalid server response", "OK", null);
    }}};

    // Set the URL for the login request
    String loginUrl = BASE_URL + "/user/signin"+"?username="+username.getText().toString()+
                "&password="+password.getText().toString(); // Replace with your authentication endpoint
    this.connection.setUrl(loginUrl);
    this.connection.setHttpMethod("POST");

    // Add the username and password as request parameters
    connection.addArgument("username", username.getText());
    connection.addArgument("password", password.getText());

    // Send the login request
    NetworkManager.getInstance().addToQueue(this.connection);
}}