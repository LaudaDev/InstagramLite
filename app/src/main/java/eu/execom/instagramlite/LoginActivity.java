package eu.execom.instagramlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import eu.execom.instagramlite.utils.Preferences;
import eu.execom.instagramlite.utils.Preferences_;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

    private static final String TAG =
            LoginActivity.class.getSimpleName();

    @ViewById
    EditText username;

    @ViewById
    EditText password;

    @Pref
    Preferences_ prefs;

    @AfterInject
    void init() {
        if (prefs.loggedIn().getOr(false)) {
            NavigationActivity_.intent(this).start();
        }
    }

    @Click
    void login() {
        Log.d(TAG, username.getText().toString() + "   "
                + password.getText().toString());
        NavigationActivity_.intent(this)
                .username(username.getText().toString()).start();
        prefs.loggedIn().put(true);
    }
}
