package eu.execom.instagramlite.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import eu.execom.instagramlite.repository.UserPostRepository;
import eu.execom.instagramlite.views.GridPictureItemView;
import eu.execom.instagramlite.views.GridPictureItemView_;

/**
 * The {@link MyPicturesGridAdapter} that provides images for the grid view
 * on the user profile fragment.
 * <p/>
 * Created by sbratic on 4/20/2016.
 */
@EBean
public class MyPicturesGridAdapter extends BaseAdapter {

    @Bean
    UserPostRepository userPostRepository;

    @RootContext
    Context context;

    @Override
    public int getCount() {
        return userPostRepository.getMockDrawable().length;
    }

    @Override
    public Object getItem(int position) {
        return userPostRepository.getMockDrawable()[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridPictureItemView gridPictureItemView;
        if (convertView == null) {
            gridPictureItemView = GridPictureItemView_.build(context);
        } else {
            gridPictureItemView = (GridPictureItemView) convertView;
        }

        gridPictureItemView.bind((int) getItem(position));

        return gridPictureItemView;
    }

}
