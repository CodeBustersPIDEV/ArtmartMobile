/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmart.entities;
import java.util.Date;
//import java.sql.Date;
/**
 *
 * @author 21697
 */
public class User {
  
    private int user_id, phone_nbr;
    private String name, email, username, pwd, role, picture,token;
    boolean blocked,enabled;
    String birthday;
    public User() {
    }

    public User(int user_id, int phone_nbr, String name, String email, String username, String pwd, String role,String picture) {
        this.user_id = user_id;
        this.phone_nbr = phone_nbr;
        this.name = name;
        this.email = email;
        this.username = username;
        this.pwd = pwd;
        this.role = role;
        this.picture=picture;

    }

   
     public User(int user_id, String username, String pwd, String role) {
        this.user_id = user_id;
        this.username = username;
        this.pwd = pwd;
        this.role = role;
    }

    
    
   

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getPhone_nbr() {
        return phone_nbr;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPwd() {
        return pwd;
    }

    public String getBirthday() {
        return birthday;
    }


    public String getPicture() {
        return picture;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setPhone_nbr(int phone_nbr) {
        this.phone_nbr = phone_nbr;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String usename) {
        this.username = usename;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

   
}
