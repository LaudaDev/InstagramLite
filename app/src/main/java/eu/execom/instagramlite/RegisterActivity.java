package eu.execom.instagramlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import eu.execom.instagramlite.repository.UserRepository;
import eu.execom.instagramlite.utils.Preferences_;

@EActivity(R.layout.activity_register)
public class RegisterActivity extends AppCompatActivity {

    @Bean
    UserRepository userRepository;

    @ViewById
    EditText nameEditText;

    @ViewById
    EditText emailEditText;

    @ViewById
    EditText passwordEditText;

    @Pref
    Preferences_ prefs;


    @Click(R.id.registerButton)
    void register() {
        final boolean success = userRepository.registerUser(nameEditText.getText().toString(), emailEditText.getText().toString(), passwordEditText.getText().toString());

        if (!success)
            Toast.makeText(this, getString(R.string.register_fail_msg), Toast.LENGTH_LONG).show();
        else {
            Toast.makeText(this, getString(R.string.register_success_msg), Toast.LENGTH_LONG).show();
            resetFields();
            prefs.loggedIn().put(true);
            NavigationActivity_.intent(this)
                    .username(userRepository.getUser().getName()).start();
        }
    }

    void resetFields() {
        nameEditText.setText("");
        emailEditText.setText("");
        passwordEditText.setText("");
    }

}
