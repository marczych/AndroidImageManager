package com.ifixit.android.imagemanager.sample;

import android.app.Application;

import com.ifixit.android.imagemanager.ImageManager;

public class SampleApplication extends Application {
   private ImageManager mImageManager;

   public ImageManager getImageManager() {
      if (mImageManager == null) {
         mImageManager = new ImageManager(this);
      }

      return mImageManager;
   }
}
