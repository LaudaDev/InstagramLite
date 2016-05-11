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
import eu.execom.instagramlite.views.UserPostItemView;
import eu.execom.instagramlite.views.UserPostItemView_;

/**
 * Adapter for our posts lists, done in the Android Annotations way.
 * <p/>
 * Created by Alex on 4/16/16.
 */
@EBean
public class UserPostsAdapter extends BaseAdapter {

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
        UserPostItemView userPostItemView;
        if (convertView == null) {
            userPostItemView = UserPostItemView_.build(context);
        } else {
            userPostItemView = (UserPostItemView) convertView;
        }

        userPostItemView.bind((UserPost) getItem(position));

        return userPostItemView;
    }

}
