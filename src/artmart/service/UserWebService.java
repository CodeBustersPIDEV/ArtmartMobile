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
import artmart.forms.SignUpForm;
import artmart.forms.getReadyProductForm;
import artmart.forms.tokenVerificationForm;

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
    User user = null;

    public User getUserInfo(int id) {
        String url = BASE_URL + "/user/user/" + id;
        this.connection.setUrl(url);
        this.connection.setHttpMethod("GET");

        this.connection.addResponseListener(e -> {
            if (this.connection.getResponseCode() == 200) {
                String response = new String(this.connection.getResponseData());
                JSONObject jsonResponse = new JSONObject(response);
                System.out.println("Response: " + response); // Add this line to log the response

                user = new User(
                        jsonResponse.getInt("user_id"),
                        jsonResponse.getInt("phonenumber"),
                        jsonResponse.getString("name"),
                        jsonResponse.getString("email"),
                        jsonResponse.getString("username"),
                        jsonResponse.getString("password"),
                        jsonResponse.getString("role"),
                        jsonResponse.getString("file")
                );
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(this.connection);

        return user;
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
                    String blockedString = jsonEvent.getString("blocked");
                    boolean blocked = blockedString.equals("1");
                    System.out.println(blocked);
                    User event = new User(
                            jsonEvent.getInt("user_id"),
                            jsonEvent.getInt("phonenumber"),
                            jsonEvent.getString("name"),
                            jsonEvent.getString("email"),
                            jsonEvent.getString("username"),
                            jsonEvent.getString("password"),
                            jsonEvent.getString("role"),
                            jsonEvent.getString("file"),
                            blocked
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
        connection.addResponseListener(r -> {
            if (connection.getResponseCode() == 201) {
                SignUpForm f = null;
                try {
                    f = new SignUpForm();
                } catch (IOException ex) {
                }
                f.show();
            }
        });
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

                        double data = (double) response.get("data");
                        String Role = (String) response.get("role");
                        String enableStr = (String) response.get("enabled");
                        boolean enabled = Boolean.parseBoolean(enableStr);

                        String blockedStr = (String) response.get("blocked");

                        boolean blocked = Boolean.parseBoolean(blockedStr);
                        id = (int) data;
                        if (enabled && !blocked) {
                            SessionManager.getInstance().setUserId(id);
                            SessionManager.getInstance().setRole(Role);

                            System.out.println(id);
                            getReadyProductForm f = null;
                            try {
                                f = new getReadyProductForm();
                            } catch (IOException ex) {
                            }
                            f.show();
                        } else if (!enabled && !blocked) {
                            tokenVerificationForm f2 = null;
                            try {
                                f2 = new tokenVerificationForm(id);
                            } catch (IOException ex) {
                            }
                            f2.show();
                        } else {
                            Dialog.show("Blocked", "This account is blocked", "OK", null);
                        }
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
                }
            }
        };

        // Set the URL for the login request
        String loginUrl = BASE_URL + "/user/signin" + "?username=" + username.getText().toString()
                + "&password=" + password.getText().toString(); // Replace with your authentication endpoint
        this.connection.setUrl(loginUrl);
        this.connection.setHttpMethod("POST");

        // Add the username and password as request parameters
        connection.addArgument("username", username.getText());
        connection.addArgument("password", password.getText());

        // Send the login request
        NetworkManager.getInstance().addToQueue(this.connection);
    }

    public void verifT(TextField token, int id) {
        connection = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream input) throws IOException {
                JSONParser parser = new JSONParser();
                Map<String, Object> response = parser.parseJSON(new InputStreamReader(input));
                if (response != null && response.containsKey("success")) {
                    String successStr = (String) response.get("success");
                    boolean success = Boolean.parseBoolean(successStr);

                    if (success) {

                        SignUpForm f = null;
                        try {
                            f = new SignUpForm();
                        } catch (IOException ex) {
                        }
                        f.show();

                    } else {
                        // Handle failed login
                        if (response.containsKey("message")) {
                            String errorMessage = (String) response.get("message");
                            Dialog.show("Incorrect token", errorMessage, "OK", null);
                        } else {
                            Dialog.show("Failed Verification", "Unknown error occurred", "OK", null);
                        }
                    }
                }
            }
        };

        // Set the URL for the login request
        String loginUrl = BASE_URL + "/user/verifyT/" + id + "?token=" + token.getText().toString(); // Replace with your authentication endpoint
        this.connection.setUrl(loginUrl);
        this.connection.setHttpMethod("POST");

        // Add the username and password as request parameters
        connection.addArgument("token", token.getText());

        // Send the login request
        NetworkManager.getInstance().addToQueue(this.connection);
    }

    public void block(int id) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/user/block/" + id);
        this.connection.setHttpMethod("POST");
        NetworkManager.getInstance().addToQueue(connection);
    }

    public void unblock(int id) {
        connection = new ConnectionRequest();
        connection.setInsecure(true);
        this.connection.setUrl(BASE_URL + "/user/unblock/" + id);
        this.connection.setHttpMethod("POST");
        NetworkManager.getInstance().addToQueue(connection);
    }
}
