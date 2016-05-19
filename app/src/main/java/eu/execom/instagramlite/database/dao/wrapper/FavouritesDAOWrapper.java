package eu.execom.instagramlite.database.dao.wrapper;

import android.util.Log;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.androidannotations.ormlite.annotations.OrmLiteDao;

import java.sql.SQLException;

import eu.execom.instagramlite.database.DatabaseHelper;
import eu.execom.instagramlite.database.dao.FavouritesDAO;
import eu.execom.instagramlite.models.Favourite;
import eu.execom.instagramlite.models.UserPost;
import eu.execom.instagramlite.utils.Preferences_;

/**
 * Created by nikolalukic on 5/11/16.
 */
@EBean
public class FavouritesDAOWrapper {

    private static final String TAG = FavouritesDAOWrapper.class.getSimpleName();

    @Bean
    UserDAOWrapper userDAOWrapper;

    @Pref
    Preferences_ preferences;

    @OrmLiteDao(helper = DatabaseHelper.class)
    FavouritesDAO favouritesDAO;

    public Favourite userLiked(int postId) {
        try {
            return favouritesDAO.queryBuilder().where().eq(Favourite.POST_ID_FIELD_NAME, postId)
                    .and().eq(Favourite.USER_ID_FIELD_NAME, preferences.id().get()).queryForFirst();
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return null;
    }

    public boolean toggleFavourite(UserPost post) {
        final Favourite favourite = userLiked(post.getId());
        if (favourite == null) {
            favouritesDAO.create(new Favourite(userDAOWrapper.getLoggedInUser(), post));
            return true;
        } else {
            favouritesDAO.delete(favourite);
            return false;
        }
    }

}
