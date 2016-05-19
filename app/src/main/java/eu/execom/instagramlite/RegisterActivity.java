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

import eu.execom.instagramlite.database.dao.wrapper.UserDAOWrapper;
import eu.execom.instagramlite.models.User;
import eu.execom.instagramlite.utils.Preferences_;

@EActivity(R.layout.activity_register)
public class RegisterActivity extends AppCompatActivity {

    @Bean
    UserDAOWrapper userDAOWrapper;

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

        if (!userDAOWrapper.emailAvailable(user.getEmail())) {
            Toast.makeText(this, getString(R.string.register_fail_msg), Toast.LENGTH_LONG).show();
        } else {
            userDAOWrapper.registerUser(user);
            Toast.makeText(this, getString(R.string.register_success_msg), Toast.LENGTH_LONG).show();
            NavigationActivity_.intent(this).start();
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
