package eu.execom.instagramlite.utils;

import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by Alex on 4/16/16.
 */
@SharedPref(SharedPref.Scope.UNIQUE)
public interface Preferences {

    boolean loggedIn();

    boolean registered();

    String username();

    String password();

    String email();

    String imageUrl();

    int id();

}
