package eu.execom.instagramlite.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import eu.execom.instagramlite.fragments.PostFragment;
import eu.execom.instagramlite.fragments.PostFragment_;
import eu.execom.instagramlite.fragments.UserProfileFragment;
import eu.execom.instagramlite.fragments.UserProfileFragment_;

/**
 * Created by sbratic on 4/20/2016.
 */
@EBean
public class NavigationPagerAdapter extends FragmentStatePagerAdapter {

    private static final int FRAGMENT_COUNT = 3;

    private static final String FRAGMENT_MODE = "mode";

    @Bean
    UserPostsAdapter userPostsAdapter;

    private final PostFragment homeFragment = PostFragment_.builder().arg(FRAGMENT_MODE, PostFragment.MODE.HOME).build();
    private final PostFragment favouriteFragment = PostFragment_.builder().arg(FRAGMENT_MODE, PostFragment.MODE.FAVOURITE).build();
    private final UserProfileFragment userProfileFragment = UserProfileFragment_.builder().build();

    public NavigationPagerAdapter(Context context) {
        super(((FragmentActivity) context).getSupportFragmentManager());
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return homeFragment;
            case 1:
                return favouriteFragment;
            case 2:
                return userProfileFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }
}
