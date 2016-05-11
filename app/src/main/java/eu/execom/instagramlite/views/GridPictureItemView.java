package eu.execom.instagramlite.views;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.facebook.common.util.UriUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import eu.execom.instagramlite.R;
import eu.execom.instagramlite.models.UserPost;

/**
 * {@link GridPictureItemView} represents the items in the grid view on the user profile fragment.
 * <p/>
 * Created by sbratic on 4/20/2016.
 */
@EViewGroup(R.layout.view_item_picture_grid)
public class GridPictureItemView extends LinearLayout {

    @ViewById
    SimpleDraweeView picture;

    public GridPictureItemView(Context context) {
        super(context);
    }

    public void bind(UserPost userPost) {
        picture.setImageURI(new Uri.Builder().scheme(UriUtil.LOCAL_FILE_SCHEME).path(userPost.getImageUrl()).build());
    }

}
