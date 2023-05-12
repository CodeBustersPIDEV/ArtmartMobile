/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmart.forms;

import artmart.entities.User;

/**
 *
 * @author 21697
 */public class SessionManager {
    private static SessionManager instance;
    private String sessionData,role;
    private int userId;
    private User user;

    private SessionManager() {
        // Private constructor to prevent instantiation
    }

    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void setSession(String data) {
        sessionData = data;
    }

    public String getSession() {
        return sessionData;
    }

    public void setUserId(int id) {
        userId = id;
    }

    public int getUserId() {
        return userId;
    }

    public void clearSession() {
        sessionData = null;
        userId = 0;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}

