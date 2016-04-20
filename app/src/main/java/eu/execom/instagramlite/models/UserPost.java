package eu.execom.instagramlite.models;

/**
 * Created by Alex on 4/16/16.
 */
public class UserPost {

    private User user;

    private String description;

    private int imageResId;

    public UserPost() {
    }

    public UserPost(User user, String description, int imageResId) {
        this.user = user;
        this.description = description;
        this.imageResId = imageResId;
    }

    public User getUser() {
        return user;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }

    @Override
    public String toString() {
        return "UserPost{" +
                "user=" + user +
                ", description='" + description + '\'' +
                ", imageResId=" + imageResId +
                '}';
    }
}
