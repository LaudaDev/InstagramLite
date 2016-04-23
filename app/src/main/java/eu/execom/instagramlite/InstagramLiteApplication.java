package eu.execom.instagramlite;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import org.androidannotations.annotations.EApplication;

/**
 * Created by nikolalukic on 4/23/16.
 */
@EApplication
public class InstagramLiteApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true).build();
        Fresco.initialize(this, config);
    }

}
