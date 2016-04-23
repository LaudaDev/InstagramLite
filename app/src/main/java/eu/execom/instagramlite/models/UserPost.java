package eu.execom.instagramlite.models;

/**
 * Created by Alex on 4/16/16.
 */
public class UserPost {

    private User user;

    private String description;

    private String imageRes;

    public UserPost() {
    }

    public UserPost(User user, String description, String imageResId) {
        this.user = user;
        this.description = description;
        this.imageRes = imageResId;
    }

    public User getUser() {
        return user;
    }

    public String getDescription() {
        return description;
    }

    public String getImageResId() {
        return imageRes;
    }

    @Override
    public String toString() {
        return "UserPost{" +
                "user=" + user +
                ", description='" + description + '\'' +
                ", imageResId=" + imageRes
                +
                '}';
    }
}
