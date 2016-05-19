package eu.execom.instagramlite.views;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.common.util.UriUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.LongClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import eu.execom.instagramlite.R;
import eu.execom.instagramlite.database.dao.wrapper.FavouritesDAOWrapper;
import eu.execom.instagramlite.models.Favourite;
import eu.execom.instagramlite.models.UserPost;
import eu.execom.instagramlite.utils.Preferences_;

/**
 * {@link UserPostItemView} represents the items in the posts and favourites lists.
 * <p>
 * Created by Alex on 4/16/16.
 */
@EViewGroup(R.layout.view_item_user_post)
public class UserPostItemView extends FrameLayout {

    private static final float MAX_BOUNCE = 4f;

    private static final float MIDDLE_BOUNCE = 3f;

    private static final float END_BOUNCE = 1f;

    private static final int DURATION = 800;

    private boolean liked;

    @Bean
    FavouritesDAOWrapper favouritesDAOWrapper;

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

    @ViewById
    ImageView favouriteIcon;

    @ViewById
    ImageView centralImage;

    private UserPost userPost;

    private Favourite favourite;

    public UserPostItemView(Context context) {
        super(context);
    }

    public void bind(UserPost userPost) {
        this.userPost = userPost;
        checkFavourite();

        // check if user image exists, and if so, set it in the view.
        if (userPost.getUser().getImageUrl() != null && !userPost.getUser().getImageUrl().isEmpty()) {
            userImage.setImageURI(Uri.parse(userPost.getUser().getImageUrl()));
        }
        username.setText(userPost.getUser().getUsername());

        image.setImageURI(new Uri.Builder().scheme(UriUtil.LOCAL_FILE_SCHEME).path(userPost.getImageUrl()).build());
        description.setText(userPost.getDescription());
    }

    @Background
    void checkFavourite() {
        favourite = favouritesDAOWrapper.userLiked(userPost.getId());
        updateIcon(favourite != null);
    }

    @LongClick(R.id.image)
    @Click(R.id.favouriteIcon)
    @Background
    void toggleFavourite() {
        final boolean favorited = favouritesDAOWrapper.toggleFavourite(userPost);
        updateIcon(favorited);
        animateIcon();
    }

    @UiThread
    void updateIcon(boolean favorited) {
        favouriteIcon.setImageResource(favorited ? R.drawable.ic_favorite_white_36dp : R.drawable.ic_favorite_border_white_36dp);
        centralImage.setImageResource(favorited ? R.drawable.ic_favorite_white_36dp : R.drawable.ic_favorite_border_white_36dp);
    }

    @UiThread
    void animateIcon() {
        ObjectAnimator.ofFloat(centralImage, "alpha", 0f, 1f, .8f, 1f, 0f).setDuration(DURATION).start();
        ObjectAnimator.ofFloat(centralImage, "scaleX", END_BOUNCE, MAX_BOUNCE, MIDDLE_BOUNCE, MAX_BOUNCE, END_BOUNCE).setDuration(DURATION).start();
        ObjectAnimator.ofFloat(centralImage, "scaleY", END_BOUNCE, MAX_BOUNCE, MIDDLE_BOUNCE, MAX_BOUNCE, END_BOUNCE).setDuration(DURATION).start();
    }

}
