package eu.execom.instagramlite.models;

/**
 * Created by Alex on 4/16/16.
 */
public class User {

    private String name;

    private int imageResId;

    private String email;

    private String password;

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
}
