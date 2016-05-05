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

        // Initialization of the Fresco library. This is needed if you want to use Fresco.
        Fresco.initialize(this, config);
    }

}
