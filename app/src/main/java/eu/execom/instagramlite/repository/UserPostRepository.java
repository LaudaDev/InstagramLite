package eu.execom.instagramlite.repository;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

import eu.execom.instagramlite.R;
import eu.execom.instagramlite.models.User;
import eu.execom.instagramlite.models.UserPost;

/**
 * Created by Alex on 4/16/16.
 */
@EBean
public class UserPostRepository {

    private List<UserPost> userPosts;

    @AfterInject
    void mockData () {
        userPosts = new ArrayList<>();
        final User user =
                new User("Aleksandar", R.drawable.aleksandar);
        for (int i = 0; i < 10; i++) {
            userPosts.add(new UserPost(user,
                    "Workshop in progress!", R.drawable.rektorat));
        }
    }

    public List<UserPost> getUserPosts() {
        return userPosts;
    }
}
