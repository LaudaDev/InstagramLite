package eu.execom.instagramlite;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import eu.execom.instagramlite.models.User;
import eu.execom.instagramlite.repository.UserRepository;
import eu.execom.instagramlite.utils.Preferences_;

@EActivity(R.layout.activity_register)
public class RegisterActivity extends AppCompatActivity {

    @Bean
    UserRepository userRepository;

    @ViewById
    EditText username;

    @ViewById
    EditText email;

    @ViewById
    EditText password;

    @Pref
    Preferences_ prefs;

    @EditorAction(R.id.password)
    @Click(R.id.registerButton)
    void register() {
        final User user = new User();
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());
        user.setUsername(username.getText().toString());
        final boolean success = userRepository.registerUser(user);

        if (!success)
            Toast.makeText(this, getString(R.string.register_fail_msg), Toast.LENGTH_LONG).show();
        else {
            Toast.makeText(this, getString(R.string.register_success_msg), Toast.LENGTH_LONG).show();
            resetFields();
            prefs.loggedIn().put(true);
            NavigationActivity_.intent(this).username(userRepository.getUser().getUsername()).start();
        }
    }

    void resetFields() {
        username.setText("");
        email.setText("");
        password.setText("");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
