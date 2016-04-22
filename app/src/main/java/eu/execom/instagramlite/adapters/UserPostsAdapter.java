package eu.execom.instagramlite.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import eu.execom.instagramlite.models.UserPost;
import eu.execom.instagramlite.repository.UserPostRepository;
import eu.execom.instagramlite.views.UserPostItemView;
import eu.execom.instagramlite.views.UserPostItemView_;

/**
 * Created by Alex on 4/16/16.
 */
@EBean
public class UserPostsAdapter extends BaseAdapter {

    @Bean
    UserPostRepository userPostRepository;

    @RootContext
    Context context;

    @Override
    public int getCount() {
        return userPostRepository.getUserPosts().size();
    }

    @Override
    public Object getItem(int position) {
        return userPostRepository.getUserPosts().get(position);
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
