package eu.execom.instagramlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

@EActivity(R.layout.activity_navigation)
public class NavigationActivity extends AppCompatActivity {

    @Extra
    String username;

    @AfterInject
    void welcomeUser() {
        Toast.makeText(NavigationActivity.this,
                String.format(getString(R.string.welcome_login_msg), username), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        super.onBackPressed();
    }
}
