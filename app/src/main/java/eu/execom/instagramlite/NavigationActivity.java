package eu.execom.instagramlite;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import eu.execom.instagramlite.adapters.TabAdapter;
import eu.execom.instagramlite.repository.UserRepository;

@EActivity(R.layout.activity_navigation)
@OptionsMenu(R.menu.menu_options_navigation)
public class NavigationActivity extends AppCompatActivity {

    private static final String TAG = NavigationActivity.class.getSimpleName();

    private static final String PHOTO_PATH = "PHOTO_PATH";

    static final int REQUEST_IMAGE_CAPTURE = 1;

    boolean doubleBackToExitPressedOnce = false;

    String currentPhotoPath;

    @Extra
    String username;

    @Bean
    UserRepository userRepository;

    @ViewById
    TabLayout tabLayout;

    @ViewById
    ViewPager pager;

    TabAdapter adapter;

    @AfterViews
    void setUpTabs() {
        adapter = new TabAdapter(this);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
    }

    @AfterInject
    void welcomeUser() {
        Toast.makeText(NavigationActivity.this, String.format(getString(R.string.welcome_login_msg), username), Toast.LENGTH_LONG).show();
    }

    /**
     * This is how you implement the "press back again to exit" feature
     */
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            moveTaskToBack(true);
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @OptionsItem(R.id.item_logout)
    void itemLogout() {
        userRepository.invalidateSession();
        finish();
    }

    @Click
    void newPost() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                Log.e(TAG, e.getMessage(), e);
                e.printStackTrace();
            }

            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    @OnActivityResult(REQUEST_IMAGE_CAPTURE)
    void onResult(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Toast.makeText(this, currentPhotoPath, Toast.LENGTH_LONG).show();
            Log.d("paht", currentPhotoPath);

            NewPostActivity_.intent(this).imagePath(currentPhotoPath).start();
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        final String timeStamp = String.valueOf(System.currentTimeMillis());
        final String imageFileName = "JPEG_" + timeStamp + "_";
        Log.d("imageFileName", imageFileName);
        final File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        final File instagramLiteStorageDir = new File(String.format("%s/InstagramLite", storageDir.getAbsolutePath()));
        instagramLiteStorageDir.mkdir();
        final File image = File.createTempFile(
                imageFileName,           /* prefix    */
                ".jpg",                  /* suffix    */
                instagramLiteStorageDir  /* directory */
        );
        currentPhotoPath = image.getAbsolutePath();
        Log.d(TAG, currentPhotoPath);
        return image;
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(PHOTO_PATH, currentPhotoPath);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            currentPhotoPath = savedInstanceState.getString(PHOTO_PATH);
        }
    }

}
