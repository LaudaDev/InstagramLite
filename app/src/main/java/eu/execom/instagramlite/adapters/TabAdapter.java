package eu.execom.instagramlite.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;

import eu.execom.instagramlite.fragments.PostFragment_;
import eu.execom.instagramlite.fragments.UserProfileFragment_;

/**
 * The {@link TabAdapter} serves as an adapter to the TabLayout. Here you define how many tabs you
 * will have, what are their titles, and what are the fragments for each of them.
 * <p/>
 * Created by nikolalukic on 4/23/16.
 */
public class TabAdapter extends FragmentStatePagerAdapter {

    // good practise is to have a constant for the number of tabs
    private static final int MAX_TABS = 3;

    private Context context;

    public TabAdapter(AppCompatActivity activity) {
        super(activity.getSupportFragmentManager());
        this.context = activity;
    }

    /**
     * This method is automatically called by the view pager. Its role is to return the fragment
     * matching the currently selected tab.
     *
     * @param position index of the currently selected tab, starting with 0.
     * @return fragment that corresponds to the position.
     */
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

    /**
     * TabLayout uses this method to denote which are the titles for each tab.
     *
     * @param position index of a tab.
     * @return a string matching the name of the tab.
     */
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
