package eu.execom.instagramlite.database.dao.wrapper;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.ormlite.annotations.OrmLiteDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eu.execom.instagramlite.database.DatabaseHelper;
import eu.execom.instagramlite.database.dao.FavouritesDAO;
import eu.execom.instagramlite.database.dao.UserDAO;
import eu.execom.instagramlite.database.dao.UserPostDAO;
import eu.execom.instagramlite.models.Favourite;
import eu.execom.instagramlite.models.UserPost;

/**
 * Created by nikolalukic on 5/11/16.
 */
@EBean
public class UserPostDAOWrapper {

    @OrmLiteDao(helper = DatabaseHelper.class)
    UserPostDAO userPostDAO;

    @OrmLiteDao(helper = DatabaseHelper.class)
    FavouritesDAO favouritesDAO;

    @OrmLiteDao(helper = DatabaseHelper.class)
    UserDAO userDAO;

    private PreparedQuery<UserPost> postsForOneUserQuery;

    @Bean
    UserDAOWrapper userDAOWrapper;

    public void createPost(UserPost post) {
        post.setUser(userDAOWrapper.getLoggedInUser());

        userPostDAO.create(post);
    }

    public List<UserPost> findAll() {
        final List<UserPost> posts = userPostDAO.queryForAll();
        refreshUserForPosts(posts);
        return posts;
    }

    public List<UserPost> findFavorited() {
        try {
            if (postsForOneUserQuery == null) {
                final QueryBuilder<Favourite, Integer> favouritesQB = favouritesDAO.queryBuilder();
                final SelectArg userId = new SelectArg();
                // select post ids that the current user has favourited
                favouritesQB.selectColumns(Favourite.POST_ID_FIELD_NAME).where().eq(Favourite.USER_ID_FIELD_NAME, userId);
                // get all posts whose id is in the favourited list
                postsForOneUserQuery = userPostDAO.queryBuilder().where().in(UserPost.ID_FIELD_NAME, favouritesQB).prepare();
            }

            postsForOneUserQuery.setArgumentHolderValue(0, userDAOWrapper.getLoggedInUser().getId());
            final List<UserPost> posts = userPostDAO.query(postsForOneUserQuery);

            refreshUserForPosts(posts);
            return posts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private void refreshUserForPosts(List<UserPost> posts) {
        for (UserPost post : posts) {
            userDAO.refresh(post.getUser());
        }
    }

}
