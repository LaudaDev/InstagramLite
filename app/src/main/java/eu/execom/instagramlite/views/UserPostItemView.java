package eu.execom.instagramlite.views;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import eu.execom.instagramlite.R;
import eu.execom.instagramlite.models.UserPost;

/**
 * Created by Alex on 4/16/16.
 */
@EViewGroup(R.layout.view_item_user_post)
public class UserPostItemView extends LinearLayout {

    @ViewById
    ImageView userImage;

    @ViewById
    TextView username;

    @ViewById
    ImageView image;

    @ViewById
    TextView description;

    public UserPostItemView(Context context) {
        super(context);
    }

    public void bind(UserPost userPost) {
        userImage.setImageResource(userPost.getUser().getImageResId());
        username.setText(userPost.getUser().getName());
        image.setImageResource(userPost.getImageResId());
        description.setText(userPost.getDescription());
    }
}
