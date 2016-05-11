package eu.execom.instagramlite.database.dao.wrapper;

import com.j256.ormlite.stmt.QueryBuilder;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.ormlite.annotations.OrmLiteDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eu.execom.instagramlite.database.DatabaseHelper;
import eu.execom.instagramlite.database.dao.FavouritesDAO;
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

    @Bean
    UserDAOWrapper userDAOWrapper;

    public void createPost(UserPost post) {
        post.setUser(userDAOWrapper.getLoggedInUser());

        userPostDAO.create(post);
    }

    public List<UserPost> findAll() {
        return userPostDAO.queryForAll();
    }

    public List<UserPost> findFavorited() {
        try {
            QueryBuilder<Favourite, Integer> favouritesQB = favouritesDAO.queryBuilder();
            favouritesQB.selectColumns(Favourite.POST_ID_FIELD_NAME).where().eq(Favourite.USER_ID_FIELD_NAME, userDAOWrapper.getLoggedInUser().getId());
            return userPostDAO.queryBuilder().where().in(UserPost.ID_FIELD_NAME, favouritesQB).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
