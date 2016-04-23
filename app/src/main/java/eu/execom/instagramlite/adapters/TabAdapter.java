package eu.execom.instagramlite.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.EBean;

import eu.execom.instagramlite.fragments.PostFragment_;
import eu.execom.instagramlite.fragments.UserProfileFragment_;

/**
 * Created by nikolalukic on 4/23/16.
 */
public class TabAdapter extends FragmentStatePagerAdapter {

    private static final int MAX_TABS = 3;

    private Context context;

    public TabAdapter(AppCompatActivity activity) {
        super(activity.getSupportFragmentManager());
        this.context = activity;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return PostFragment_.builder().build();
            case 1:
                return PostFragment_.builder().build();
            case 2:
                return UserProfileFragment_.builder().build();
            default:
                throw new RuntimeException("Invalid number of tabs");
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Home";
            case 1:
                return "Favourites";
            case 2:
                return "Profile";
            default:
                throw new RuntimeException("Invalid number of tabs");
        }
    }

    @Override
    public int getCount() {
        return MAX_TABS;
    }
}
