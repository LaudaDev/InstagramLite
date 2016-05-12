package eu.execom.instagramlite.fragments;

import android.support.v4.app.Fragment;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import eu.execom.instagramlite.R;
import eu.execom.instagramlite.adapters.UserPostsAdapter;
import eu.execom.instagramlite.repository.UserPostRepository;

/**
 * Created by nikolalukic on 5/13/16.
 */
@EFragment(R.layout.fragment_post)
public class FavouritesFragment extends Fragment {
    @ViewById
    ListView listView;

    @Bean
    UserPostsAdapter userPostsAdapter;

    @Bean
    UserPostRepository userPostRepository;

    @AfterViews
    @UiThread(delay = 100)
    void setAdapter() {
        listView.setAdapter(userPostsAdapter);
        userPostsAdapter.setPosts(userPostRepository.getUserPosts());
    }

}
