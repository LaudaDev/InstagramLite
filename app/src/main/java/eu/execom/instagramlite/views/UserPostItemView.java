package eu.execom.instagramlite.views;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import eu.execom.instagramlite.R;
import eu.execom.instagramlite.models.UserPost;

/**
 * Created by Alex on 4/16/16.
 */
@EViewGroup(R.layout.view_item_user_post)
public class UserPostItemView extends CardView {

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
        userImage.setImageResource(userPost.getUser().getImageResId());
        username.setText(userPost.getUser().getName());
        image.setImageURI(Uri.parse(userPost.getImageResId()));
        description.setText(userPost.getDescription());
    }
}
