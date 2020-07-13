package com.example.qlbhcdio.Utils;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class LoadImageUtils {

    public static void LoadImageUrlCenterFit(String pathUrl, ImageView imageView) {
        Picasso.with(imageView.getContext())
                .load(pathUrl)
                .centerCrop()
                .fit()
                .into(imageView);
    }

    public static void LoadImageUrlWithSize(String pathUrl, ImageView imageView, int width, int height) {
        Picasso.with(imageView.getContext())
                .load(pathUrl)
                .resize(width, height)
                .into(imageView);
    }
}
