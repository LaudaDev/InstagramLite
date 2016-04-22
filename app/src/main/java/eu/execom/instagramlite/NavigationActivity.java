package eu.execom.instagramlite;

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

    @Extra
    String username;

    @Bean
    UserRepository userRepository;





    @AfterInject
    void welcomeUser() {
        Toast.makeText(NavigationActivity.this,
                String.format(getString(R.string.welcome_login_msg), username), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        //preskakanje logina
        moveTaskToBack(true);
        super.onBackPressed();
    }


    @OptionsItem(R.id.item_logout)
    void itemLogout() {
        userRepository.invalidateSession();
        finish();
    }

}
