package eu.execom.instagramlite.models;

import java.util.Date;

/**
 * Created by Alex on 4/16/16.
 */
public class UserPost {

    private int id;

    private User user;

    private String description;

    private String imageUrl;

    private Date createdAt;

    public UserPost() {
        this.createdAt = new Date();
    }

    public UserPost(User user, String description, String imageUrl) {
        this.user = user;
        this.description = description;
        this.imageUrl = imageUrl;
        this.createdAt = new Date();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "UserPost{" +
                "id=" + id +
                ", user=" + user +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
