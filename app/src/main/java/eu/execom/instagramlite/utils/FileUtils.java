package eu.execom.instagramlite.utils;

import android.os.Environment;
import android.util.Log;

import org.androidannotations.annotations.EBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by nikolalukic on 5/6/16.
 */
@EBean
public class FileUtils {

    private static final String TAG = FileUtils.class.getSimpleName();

    public File createImageFile() throws IOException {
        // Create an image file name
        final String timeStamp = String.valueOf(System.currentTimeMillis());
        final String imageFileName = "JPEG_" + timeStamp + "_";
        Log.d("imageFileName", imageFileName);
        final File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        final File instagramLiteStorageDir = new File(String.format("%s/InstagramLite", storageDir.getAbsolutePath()));
        instagramLiteStorageDir.mkdir();

        return File.createTempFile(
                imageFileName,           /* prefix    */
                ".jpg",                  /* suffix    */
                instagramLiteStorageDir  /* directory */
        );
    }

    public InputStream convertToStream(String path) {
        try {
            return new FileInputStream(path);
        } catch (FileNotFoundException e) {
            Log.e(TAG, e.getMessage(), e);
        }
        return null;
    }

}
