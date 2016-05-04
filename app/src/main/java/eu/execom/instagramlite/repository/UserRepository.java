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
        if (!preferences.registered().getOr(false)) {
            return null;
        }
        if (user == null) {
            deserializeUser();
        }
        return user;
    }

    /**
     * Mocks rest registration call
     *
     * @param user the user returned from the server
     * @return user successfully registered or not
     */
    public boolean registerUser(User user) {

        if (user != null) {
            preferences.username().put(user.getUsername());
            preferences.email().put(user.getEmail());
            preferences.password().put(user.getPassword());
            preferences.imageUrl().put(user.getImageUrl());
            preferences.loggedIn().put(true);
            preferences.registered().put(true);
            this.user = user;
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
        deserializeUser();
        // true if user exists and the credentials are valid (android studio made me write this way)
        return user != null && email.equals(user.getEmail()) && password.equals(user.getPassword());
    }

    /**
     * Deserialization of user
     *
     * @return user object
     */
    private void deserializeUser() {
        // if the user is not registered no need to deserialie
        if (!preferences.registered().getOr(false)) {
            return;
        }
        user = new User();
        user.setEmail(preferences.email().get());
        user.setUsername(preferences.username().get());
        user.setPassword(preferences.password().get());
        user.setImageUrl(preferences.imageUrl().get());
    }

    /**
     * Logging out the user
     */
    public void invalidateSession() {
        preferences.loggedIn().put(false);
    }
}
