package eu.execom.instagramlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.AnimationRes;
import org.androidannotations.annotations.sharedpreferences.Pref;

import eu.execom.instagramlite.database.dao.wrapper.UserDAOWrapper;
import eu.execom.instagramlite.models.User;
import eu.execom.instagramlite.utils.Preferences_;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    @ViewById
    EditText email;

    @ViewById
    EditText password;

    @Pref
    Preferences_ prefs;

    @Bean
    UserDAOWrapper userDAOWrapper;

    @AnimationRes(R.anim.activity_enter)
    Animation animation;

    @ViewById
    Button login;

    @ViewById
    RelativeLayout container;

    @Override
    protected void onResume() {
        // If the user is already logged in, just transfer him to the navigation activity.
        if (prefs.id().exists()) {
            // Activity transition, the Android Annotations way.
            NavigationActivity_.intent(this).start();
        }
        super.onResume();
    }

    @EditorAction(R.id.password)
    @Click
    void login() {
        // Check if the user has entered valid credentials.
        final User user = userDAOWrapper.logIn(email.getText().toString(), password.getText().toString());

        if (user != null) {
            prefs.id().put(user.getId());
            // Activity transition, the Android Annotations way.
            NavigationActivity_.intent(this).start();
        } else {
            Toast.makeText(this, R.string.login_fail_msg, Toast.LENGTH_LONG).show();
        }
    }

    @Click
    void register() {
        // Activity transition, the Android Annotations way.
        RegisterActivity_.intent(this).flags(Intent.FLAG_ACTIVITY_NO_HISTORY).start();
    }

}
