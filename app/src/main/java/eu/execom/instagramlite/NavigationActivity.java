package eu.execom.instagramlite;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import eu.execom.instagramlite.adapters.NavigationPagerAdapter;

@EActivity(R.layout.activity_navigation)
public class NavigationActivity extends AppCompatActivity {

    @Extra
    String username;

    @ViewById
    ViewPager viewPager;

    @ViewById
    TabLayout tabLayout;

    @Bean
    NavigationPagerAdapter navigationPagerAdapter;


    @AfterViews
    void afterViews() {
        viewPager.setAdapter(navigationPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        setTabs();
    }

    void setTabs() {
        tabLayout.getTabAt(0).setText(R.string.home);
        tabLayout.getTabAt(1).setText(R.string.favourite_posts);
        tabLayout.getTabAt(2).setText(R.string.my_profile);
    }

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
}
