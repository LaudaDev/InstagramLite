package eu.execom.instagramlite.fragments;

import android.support.v4.app.Fragment;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import eu.execom.instagramlite.R;
import eu.execom.instagramlite.adapters.UserPostsAdapter;

@EFragment(R.layout.fragment_home)
public class HomeFragment extends Fragment {

    @ViewById
    ListView listView;

    @Bean
    UserPostsAdapter userPostsAdapter;

    @ViewById
    ProgressBar progressBar;

    private int progress;

    @AfterViews
    @Background
    void makeProgress() {
        while (progress <= progressBar.getMax()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            progress += 1;
            updateProgress();
        }
    }

    @UiThread
    void updateProgress() {
        progressBar.setProgress(progress);
    }

    @AfterViews
    @UiThread(delay = 100)
    void setAdapter() {
        listView.setAdapter(userPostsAdapter);
    }

}
