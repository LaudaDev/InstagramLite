package eu.execom.instagramlite.repository;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.sharedpreferences.Pref;

import eu.execom.instagramlite.models.User;
import eu.execom.instagramlite.utils.Preferences_;

/**
 * Created by sbratic on 4/20/2016.
 * <p/>
 * Klasa koja rukuje registracijom, logovanjem i dobavaljenjem korisnika
 */
@EBean(scope = EBean.Scope.Singleton)
public class UserRepository {

    private User user = null;

    @Pref
    Preferences_ preferences;


    /**
     * Dobavljanja podataka o korisniku preko "REST-a"
     *
     * @return korisnik ukoliko je pronadjen
     */
    public User getUser() {
        if (user == null) {
            user = deserializeUser(preferences.user().get());
        }
        return user;
    }

    /**
     * Imitiranje registracije korisnika za Rest
     *
     * @param name     ime korisnika
     * @param email    email korisnika
     * @param password sifra korisnika
     * @return boolean da li je user uspesno registrovan ili ne
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
     * Mimika autentikacije
     *
     * @param email
     * @param password
     * @return da li postoji korisnik
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
     * Persistencija korisnika nakon registracije
     *
     * @param user
     * @return # separated vrednosti korisnika
     */
    private String serializeUser(User user) {
        final String userString = user.getName() + "#" + user.getEmail() + "#" + user.getPassword() + "#" + user.getImageResId();

        return userString;

    }

    /**
     * Deserijalizacija korisnika koji se registrovao
     *
     * @param userString # separated vrednosti korisnika
     * @return objekat korisnik
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
}
