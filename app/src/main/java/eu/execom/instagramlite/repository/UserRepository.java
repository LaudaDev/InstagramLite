package eu.execom.instagramlite.repository;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.sharedpreferences.Pref;

import eu.execom.instagramlite.models.User;
import eu.execom.instagramlite.utils.Preferences_;

/**
 * Created by sbratic on 4/20/2016.
 * Class that handles user registration and authentication
 */
@EBean(scope = EBean.Scope.Singleton)
public class UserRepository {

    private User user = null;

    @Pref
    Preferences_ preferences;


    /**
     * Rest mocking of user get
     *
     * @return user if uuser is registered
     */
    public User getUser() {
        if (user == null) {
            user = deserializeUser(preferences.user().get());
        }
        return user;
    }

    /**
     * Mocks rest registration call
     *
     * @param name
     * @param email
     * @param password
     * @return user successfully registered or not
     */
    public boolean registerUser(String name, String email, String password) {

        if (name != null && email != null && password != null) {
            user = new User(name, email, password);
            preferences.user().put(serializeUser(user));
            return true;
        }

        return false;
    }

    /**
     * Authentication mocking
     *
     * @param email
     * @param password
     * @return user exists
     */
    public boolean authenticate(String email, String password) {
        user = getUser();
        if (user == null)
            return false;

        if (email.equals(user.getEmail()) && password.equals(user.getPassword()))
            return true;

        return false;
    }

    /**
     * User serialization
     *
     * @param user
     * @return # separated values
     */
    private String serializeUser(User user) {
        final String userString = user.getName() + "#" + user.getEmail() + "#" + user.getPassword() + "#" + user.getImageResId();

        return userString;

    }

    /**
     * Deserialization of user
     *
     * @param userString # separated values
     * @return user object
     */
    private User deserializeUser(String userString) {

        if (userString == null)
            return null;


        final String[] userFields = userString.split("#");

        if (userFields.length < 3)
            return null;


        final User user = new User();
        user.setName(userFields[0]);
        user.setEmail(userFields[1]);
        user.setPassword(userFields[2]);

        if (userFields.length > 3) {
            user.setImageResId(Integer.parseInt(userFields[3]));
        }

        return user;
    }

    /**
     * Logging out the user
     */
    public void invalidateSession() {
        preferences.loggedIn().put(false);
    }
}
