package eu.execom.instagramlite.models;

import java.util.ArrayList;
import java.util.List;

import eu.execom.instagramlite.R;

/**
 * Created by Alex on 4/16/16.
 */
public class User {

    private String name;

    private int imageResId;

    private String email;

    private String password;

    private List<UserPost> favouritePosts = new ArrayList<>();

    public User() {
    }

    public User(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
    }

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        if (imageResId == 0)
            return R.drawable.aleksandar;

        return imageResId;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", imageResId=" + imageResId +
                '}';
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
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
