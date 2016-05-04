package eu.execom.instagramlite.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 4/16/16.
 */
public class User {

    private String username;

    private String imageUrl;

    private String email;

    private String password;

    private List<UserPost> favouritePosts = new ArrayList<>();

    public User() {
    }

    public User(String name, String password, String email) {
        this.username = name;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserPost> getFavouritePosts() {
        return favouritePosts;
    }

    public void setFavouritePosts(List<UserPost> favouritePosts) {
        this.favouritePosts = favouritePosts;
    }
}
