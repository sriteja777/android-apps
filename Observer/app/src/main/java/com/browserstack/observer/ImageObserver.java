package com.browserstack.observer;

import android.annotation.SuppressLint;
import android.os.FileObserver;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.File;

public class ImageObserver extends FileObserver {
    private static final String TAG = "BROWSERSTACK_FRAMEWORK";
    private final Listener listener;
    private final File path;

    public interface Listener {
        void onFileUpdate(String str);
    }

    @SuppressLint("WrongConstant")
    @RequiresApi(api = 29)
    public ImageObserver(File path2, Listener listener2) {
        super(path2.getParentFile(), ALL_EVENTS);
        Log.d(TAG, "io werdsdsdfs " + path2.getAbsolutePath() + " " + path2.getParentFile() + " " + path2.getParentFile().getAbsolutePath());
        this.path = path2;
        this.listener = listener2;
    }

    @SuppressLint("WrongConstant")
    public ImageObserver(String path2, Listener listener2) {
        super(new File(path2).getParentFile().getAbsolutePath(), 770);
        Log.d(TAG, "io werds");
        this.path = new File(path2);
        this.listener = listener2;
    }

    @Override
    protected void finalize() {
        super.finalize();
        Log.d(TAG, "finasdgklsdsdnj");
    }

    public void onEvent(int event, String path2) {
        Log.d(TAG, "file update event got");
        if (this.path.getName().equals(path2)) {
            Listener listener2 = this.listener;
            if (listener2 != null) {
                listener2.onFileUpdate(this.path.getAbsolutePath());
            }
        }
    }
}
