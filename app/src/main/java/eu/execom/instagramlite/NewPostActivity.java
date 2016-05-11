package eu.execom.instagramlite;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.common.util.UriUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import eu.execom.instagramlite.database.dao.wrapper.UserPostDAOWrapper;
import eu.execom.instagramlite.models.UserPost;

@EActivity(R.layout.activity_new_post)
public class NewPostActivity extends AppCompatActivity {

    @Extra
    String imagePath;

    @ViewById
    SimpleDraweeView image;

    @ViewById
    EditText description;

    @Bean
    UserPostDAOWrapper userPostDAOWrapper;

    @AfterViews
    void setImage() {
        Uri uri = new Uri.Builder().scheme(UriUtil.LOCAL_FILE_SCHEME).path(imagePath).build();
        image.setImageURI(uri);
    }

    @Click
    @EditorAction(R.id.description)
    void submitPost() {
        Toast.makeText(this, description.getText().toString(), Toast.LENGTH_SHORT).show();

        UserPost post = new UserPost();
        post.setDescription(description.getText().toString());
        post.setImageUrl(imagePath);

        userPostDAOWrapper.createPost(post);
        finish();
    }

}
