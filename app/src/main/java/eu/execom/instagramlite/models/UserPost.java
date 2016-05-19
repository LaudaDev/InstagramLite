package eu.execom.instagramlite.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by Alex on 4/16/16.
 */
@DatabaseTable(tableName = "post")
public class UserPost {
    public static final String ID_FIELD_NAME = "id";

    @DatabaseField(columnName = ID_FIELD_NAME, generatedId = true)
    private int id;

    @DatabaseField(columnName = "user", canBeNull = false, foreign = true)
    private User user;

    @DatabaseField(columnName = "description", canBeNull = false)
    private String description;

    @DatabaseField(columnName = "imageUrl", canBeNull = false)
    private String imageUrl;

    @DatabaseField(columnName = "createdAt", canBeNull = false)
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
