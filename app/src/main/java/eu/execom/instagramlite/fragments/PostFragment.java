package eu.execom.instagramlite.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import eu.execom.instagramlite.R;
import eu.execom.instagramlite.adapters.UserPostsAdapter;
import eu.execom.instagramlite.database.dao.wrapper.UserPostDAOWrapper;

@EFragment(R.layout.fragment_post)
public class PostFragment extends Fragment {

    @ViewById
    SwipeRefreshLayout swipeRefresh;

    @ViewById
    ListView listView;

    @Bean
    UserPostsAdapter userPostsAdapter;

    @Bean
    UserPostDAOWrapper userPostDAOWrapper;

    @AfterViews
    @UiThread(delay = 100)
    void setAdapter() {
        listView.setAdapter(userPostsAdapter);
        userPostsAdapter.setPosts(userPostDAOWrapper.findAll());

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                userPostsAdapter.setPosts(userPostDAOWrapper.findAll());
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (userPostsAdapter != null) {
            userPostsAdapter.setPosts(userPostDAOWrapper.findAll());
        }
    }

}
