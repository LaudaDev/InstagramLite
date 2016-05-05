package eu.execom.instagramlite.fragments;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import eu.execom.instagramlite.R;
import eu.execom.instagramlite.adapters.MyPicturesGridAdapter;
import eu.execom.instagramlite.models.User;
import eu.execom.instagramlite.repository.UserRepository;

/**
 * User that handles profile data
 */
@EFragment(R.layout.fragment_user_profile)
public class UserProfileFragment extends Fragment {

    @Bean
    UserRepository userRepository;

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
        final User user = userRepository.getUser();
        name.setText(user.getUsername());
        picturesGrid.setAdapter(myPicturesGridAdapter);
        if (user.getImageUrl().isEmpty()) {
            profileImage.setImageURI(Uri.parse(user.getImageUrl()));
        }
    }

}
