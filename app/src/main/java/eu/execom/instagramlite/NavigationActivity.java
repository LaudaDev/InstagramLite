package eu.execom.instagramlite;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.io.File;
import java.io.IOException;

import eu.execom.instagramlite.adapters.TabAdapter;
import eu.execom.instagramlite.utils.FileUtils;
import eu.execom.instagramlite.utils.Preferences_;

@EActivity(R.layout.activity_navigation)
@OptionsMenu(R.menu.menu_options_navigation)
public class NavigationActivity extends AppCompatActivity {

    private static final String TAG = NavigationActivity.class.getSimpleName();

    private static final String PHOTO_PATH = "PHOTO_PATH";

    static final int REQUEST_IMAGE_CAPTURE = 1;

    boolean doubleBackToExitPressedOnce = false;

    String currentPhotoPath;

    @Pref
    Preferences_ preferences;

    @Bean
    FileUtils fileUtils;

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

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position <= 1) {
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
        preferences.id().remove();
        LoginActivity_.intent(this).start();
        finish();
    }

    @Click
    void newPost() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            try {
                final File photoFile = fileUtils.createImageFile();

                currentPhotoPath = photoFile.getAbsolutePath();
                Log.d(TAG, currentPhotoPath);

                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            } catch (IOException e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }
    }

    @OnActivityResult(REQUEST_IMAGE_CAPTURE)
    void onResult(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Toast.makeText(this, currentPhotoPath, Toast.LENGTH_LONG).show();
            Log.d(TAG, currentPhotoPath);

            NewPostActivity_.intent(this).imagePath(currentPhotoPath).start();
        }
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
