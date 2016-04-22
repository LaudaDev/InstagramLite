package eu.execom.instagramlite;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;

import eu.execom.instagramlite.repository.UserRepository;

@EActivity(R.layout.activity_navigation)
@OptionsMenu(R.menu.menu_options_navigation)
public class NavigationActivity extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;

    @Extra
    String username;

    @Bean
    UserRepository userRepository;

    @AfterInject
    void welcomeUser() {
        Toast.makeText(NavigationActivity.this, String.format(getString(R.string.welcome_login_msg), username), Toast.LENGTH_LONG).show();
    }

    /**
     * This is how you implement the "press back again to exit" feature
     */
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            moveTaskToBack(true);
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @OptionsItem(R.id.item_logout)
    void itemLogout() {
        userRepository.invalidateSession();
        finish();
    }

}
