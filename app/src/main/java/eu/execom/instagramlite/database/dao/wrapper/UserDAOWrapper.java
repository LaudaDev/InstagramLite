package eu.execom.instagramlite.database.dao.wrapper;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.androidannotations.ormlite.annotations.OrmLiteDao;

import java.sql.SQLException;

import eu.execom.instagramlite.database.DatabaseHelper;
import eu.execom.instagramlite.database.dao.UserDAO;
import eu.execom.instagramlite.models.User;
import eu.execom.instagramlite.utils.Preferences_;

/**
 * Created by nikolalukic on 5/11/16.
 */
@EBean
public class UserDAOWrapper {

    @Pref
    Preferences_ preferences;

    @OrmLiteDao(helper = DatabaseHelper.class)
    UserDAO userDAO;

    public boolean emailAvailable(String email) {
        try {
            return userDAO.queryBuilder().where().eq("email", email).countOf() == 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void registerUser(User user) {
        userDAO.create(user);
        preferences.id().put(user.getId());
    }

    public User logIn(String email, String password) {
        User user = null;
        try {
            user = userDAO.queryBuilder().where().eq("email", email).and().eq("password", password).queryForFirst();
            if (user != null) {
                preferences.id().put(user.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User findById(int id) {
        return userDAO.queryForId(id);
    }

    public User getLoggedInUser() {
        return findById(preferences.id().get());
    }

}
