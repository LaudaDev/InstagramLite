package eu.execom.instagramlite.views;

import android.content.Context;
import android.net.Uri;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import eu.execom.instagramlite.R;
import eu.execom.instagramlite.models.UserPost;
import eu.execom.instagramlite.utils.Preferences_;

/**
 * {@link UserPostItemView} represents the items in the posts and favourites lists.
 *
 * Created by Alex on 4/16/16.
 */
@EViewGroup(R.layout.view_item_user_post)
public class UserPostItemView extends FrameLayout {

    @Pref
    Preferences_ preferences;

    @ViewById
    SimpleDraweeView userImage;

    @ViewById
    TextView username;

    @ViewById
    SimpleDraweeView image;

    @ViewById
    TextView description;

    public UserPostItemView(Context context) {
        super(context);
    }

    public void bind(UserPost userPost) {
        // check if user image exists, and if so, set it in the view.
        if (userPost.getUser().getImageUrl() != null && !userPost.getUser().getImageUrl().isEmpty()) {
            userImage.setImageURI(Uri.parse(userPost.getUser().getImageUrl()));
        }
        username.setText(userPost.getUser().getUsername());
        image.setImageURI(Uri.parse(userPost.getImageRes()));
        description.setText(userPost.getDescription());
    }

}
