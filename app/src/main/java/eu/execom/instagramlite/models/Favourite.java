package eu.execom.instagramlite.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by nikolalukic on 5/6/16.
 */
@DatabaseTable(tableName = "favourites")
public class Favourite {

    public static final String USER_ID_FIELD_NAME = "user";
    public static final String POST_ID_FIELD_NAME = "post";

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = USER_ID_FIELD_NAME, foreign = true)
    private User user;

    @DatabaseField(columnName = POST_ID_FIELD_NAME, foreign = true)
    private UserPost post;

    public Favourite() {
    }

    public Favourite(User user, UserPost post) {
        this.user = user;
        this.post = post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserPost getPost() {
        return post;
    }

    public void setPost(UserPost post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Favourite{" +
                "id=" + id +
                ", user=" + user +
                ", post=" + post +
                '}';
    }

}
