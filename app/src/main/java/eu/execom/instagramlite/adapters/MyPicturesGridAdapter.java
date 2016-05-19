package eu.execom.instagramlite.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

import eu.execom.instagramlite.models.UserPost;
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

    private List<UserPost> posts;

    @AfterInject
    void afterInject() {
        posts = new ArrayList<>();
    }

    public List<UserPost> getPosts() {
        return posts;
    }

    public void setPosts(List<UserPost> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    @RootContext
    Context context;

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
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

        gridPictureItemView.bind(posts.get(position));

        return gridPictureItemView;
    }

}
