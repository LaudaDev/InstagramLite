package eu.execom.instagramlite.fragments;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.widget.GridView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import eu.execom.instagramlite.R;
import eu.execom.instagramlite.adapters.MyPicturesGridAdapter;
import eu.execom.instagramlite.database.dao.wrapper.UserDAOWrapper;
import eu.execom.instagramlite.database.dao.wrapper.UserPostDAOWrapper;
import eu.execom.instagramlite.models.User;

/**
 * Used for the tab that shows users profile.
 */
@EFragment(R.layout.fragment_user_profile)
public class UserProfileFragment extends Fragment {

    @Bean
    UserDAOWrapper userDAOWrapper;

    @Bean
    UserPostDAOWrapper userPostDAOWrapper;

    @ViewById
    SimpleDraweeView profileImage;

    @ViewById
    TextView name;

    @Bean
    MyPicturesGridAdapter myPicturesGridAdapter;

    @ViewById
    GridView picturesGrid;

    @AfterViews
    void afterViews() {
        final User user = userDAOWrapper.getLoggedInUser();
        name.setText(user.getUsername());
        picturesGrid.setAdapter(myPicturesGridAdapter);
        myPicturesGridAdapter.setPosts(userPostDAOWrapper.findAll());

        // check if user image exists, and if so, set it in the view.
        if (user.getImageUrl() != null && user.getImageUrl().isEmpty()) {
            profileImage.setImageURI(Uri.parse(user.getImageUrl()));
        }
    }

}
