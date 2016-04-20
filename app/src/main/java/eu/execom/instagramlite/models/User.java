package eu.execom.instagramlite.models;

/**
 * Created by Alex on 4/16/16.
 */
public class User {

    private String name;

    private int imageResId;

    public User() {
    }

    public User(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", imageResId=" + imageResId +
                '}';
    }
}
