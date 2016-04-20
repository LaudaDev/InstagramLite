package eu.execom.instagramlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import eu.execom.instagramlite.repository.UserRepository;
import eu.execom.instagramlite.utils.Preferences;
import eu.execom.instagramlite.utils.Preferences_;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

    private static final String TAG =
            LoginActivity.class.getSimpleName();

    @ViewById
    EditText email;

    @ViewById
    EditText password;

    @Pref
    Preferences_ prefs;

    @Bean
    UserRepository userRepository;


    @Override
    protected void onStart() {
        if (prefs.loggedIn().getOr(false)) {
            NavigationActivity_.intent(this).username(userRepository.getUser().getEmail()).start();
        }
        super.onStart();
    }

    @Click
    void login() {
        final boolean isAuthenticated = userRepository.authenticate(email.getText().toString(), password.getText().toString());

        if (isAuthenticated) {
            NavigationActivity_.intent(this)
                    .username(userRepository.getUser().getEmail()).start();
            prefs.loggedIn().put(true);
        } else {
            Toast.makeText(this, R.string.login_fail_msg, Toast.LENGTH_LONG).show();
        }


    }

    @Click
    void register() {
        RegisterActivity_.intent(this).start();
    }
}
