package eu.execom.instagramlite.repository;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

import eu.execom.instagramlite.R;
import eu.execom.instagramlite.models.User;
import eu.execom.instagramlite.models.UserPost;

/**
 * Class that serves as an in memory storage, so we have some data to test the design of our
 * application.
 *
 * Created by Alex on 4/16/16.
 */
@EBean
public class UserPostRepository {

    @Bean
    UserRepository userRepository;

    private String kahriman = "https://scontent-vie1-1.xx.fbcdn.net/t31.0-8/11228062_1024753717556682_6562491396931476827_o.jpg";
    private String ja = "https://pbs-0.twimg.com/media/CgK7H2QUEAAcpjo.jpg";

    private List<UserPost> userPosts;

    private int[] mockDrawable = new int[20];

    @AfterInject
    void mockData() {
        userPosts = new ArrayList<>();
        final User user = userRepository.getUser();
        for (int i = 0; i < 10; i++) {
            userPosts.add(new UserPost(user,
                    "Workshop in progress!", i % 2 == 0 ? kahriman : ja));
        }

        for (int i = 0; i < mockDrawable.length; i++) {
            mockDrawable[i] = i % 2 == 0 ? R.drawable.cupcake : R.drawable.execom;
        }
    }

    public List<UserPost> getUserPosts() {
        return userPosts;
    }

    public int[] getMockDrawable() {
        return mockDrawable;
    }

}
