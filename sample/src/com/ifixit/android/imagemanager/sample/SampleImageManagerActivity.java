package com.ifixit.android.imagemanager.sample;

import java.util.ArrayList;

import com.ifixit.android.imagemanager.ImageManager;

import android.app.Activity;
import android.app.ListActivity;

import android.content.Context;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SampleImageManagerActivity extends ListActivity {
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      setListAdapter(new SampleListAdapter(this,
       ((SampleApplication)getApplication()).getImageManager()));
   }

   private class SampleListAdapter extends BaseAdapter {
      private static final String BASE_IMAGE_URL = 
       "http://goodmorningkitten.com/media/kittypix/1/kitten15";

      protected Activity mActivity;
      protected ImageManager mImageManager;
      protected ArrayList<String> mImageUrls;

      public SampleListAdapter(Activity activity, ImageManager imageManager) {
         mImageManager = imageManager;
         mActivity = activity;
         initImageUrls();
      }

      public int getCount() {
         return mImageUrls.size();
      }

      public Object getItem(int position) {
         return mImageUrls.get(position);
      }

      public long getItemId(int position) {
         return position;
      }

      public View getView(int position, View convertView, ViewGroup parent) {
         ListImage listImage = (ListImage)convertView;

         if (listImage == null) {
            listImage = new ListImage(mActivity);
         }

         mImageManager.displayImage(mImageUrls.get(position), mActivity,
          listImage.mImageView);
         listImage.mText.setText(mImageUrls.get(position));

         return listImage;
      }

      private void initImageUrls() {
         mImageUrls = new ArrayList<String>();

         for (int i = 10; i < 25; i ++) {
            mImageUrls.add(BASE_IMAGE_URL + i + ".jpg");
         }
      }
   }

   public class ListImage extends LinearLayout {
      public ImageView mImageView;
      public TextView mText;

      public ListImage(Context context) {
         super(context);

         LayoutInflater inflater = (LayoutInflater)context.
          getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         inflater.inflate(R.layout.list_image, this, true);

         mImageView = (ImageView)findViewById(R.id.list_image);
         mText = (TextView)findViewById(R.id.list_text);
      }
   }
}
