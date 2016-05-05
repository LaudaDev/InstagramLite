package eu.execom.instagramlite.views;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import eu.execom.instagramlite.R;

/**
 * {@link GridPictureItemView} represents the items in the grid view on the user profile fragment.
 *
 * Created by sbratic on 4/20/2016.
 */
@EViewGroup(R.layout.view_item_picture_grid)
public class GridPictureItemView extends LinearLayout {

    @ViewById
    ImageView picture;

    public GridPictureItemView(Context context) {
        super(context);
    }

    public void bind(int drawable) {
        picture.setImageResource(drawable);
    }

}
