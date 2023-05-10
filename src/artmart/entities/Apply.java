/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmart.entities;

/**
 *
 * @author solta
 */
public class Apply {

    private int applyId;
    private String status;
    private String artist;
    private String customproduct;

    public Apply(int applyId, String status, String artist, String customproduct) {
        this.applyId = applyId;
        this.status = status;
        this.artist = artist;
        this.customproduct = customproduct;
    }

    public Apply() {
    }

    public Apply(String status, String artist, String customproduct) {
        this.status = status;
        this.artist = artist;
        this.customproduct = customproduct;
    }

    public int getApplyId() {
        return applyId;
    }

    public void setApplyId(int applyId) {
        this.applyId = applyId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getCustomproduct() {
        return customproduct;
    }

    public void setCustomproduct(String customproduct) {
        this.customproduct = customproduct;
    }

}