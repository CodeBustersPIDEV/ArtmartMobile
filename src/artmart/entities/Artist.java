/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artmart.entities;

/**
 *
 * @author 21697
 */
public class Artist extends User{
    

    private int artist_id,nbr_artwork;
    private String bio;

   

    public Artist() {
    }

    public Artist(int artist_id, int nbr_artwork, String bio) {
        this.artist_id = artist_id;
        this.nbr_artwork = nbr_artwork;
        this.bio = bio;
    }

  
    

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }

    public Artist(int nbr_artwork, String bio) {
        this.nbr_artwork = nbr_artwork;
        this.bio = bio;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public int getNbr_artwork() {
        return nbr_artwork;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public void setNbr_artwork(int nbr_artwork) {
        this.nbr_artwork = nbr_artwork;
    }

}
