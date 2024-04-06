package com.browserstack.observer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileObserver;
import android.util.Log;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private final String mockedImagePath =  "/data/local/tmp/testing.png";
//    private final String mockedImagePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/testing.png";
    private ImageObserver imageObserver;
    private UpdateListener updateListener = new UpdateListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("SDFge", mockedImagePath);
        File injectedImage = new File(mockedImagePath);
        if (Build.VERSION.SDK_INT >= 29) {
            this.imageObserver = new ImageObserver(injectedImage, (ImageObserver.Listener) updateListener);
        } else {
            this.imageObserver = new ImageObserver(injectedImage.getAbsolutePath(), (ImageObserver.Listener) updateListener);
        }
        this.imageObserver.startWatching();

    }

    private class UpdateListener implements ImageObserver.Listener{
        public void onFileUpdate(String path) {
            Log.d("sdfsfda", "Sdgeageaeg");
        }
    }
}