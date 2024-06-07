package com.example.dcu_image_viewer;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

public class DetailImageAdapter extends BaseAdapter {
    private Context context;
    private List<String> imagePaths;
    private ImageView mainImage;

    public DetailImageAdapter(Context context, List<String> imagePaths, ImageView mainImage) {
        this.context = context;
        this.imagePaths = imagePaths;
        this.mainImage = mainImage;
    }

    @Override
    public int getCount() {
        return imagePaths.size();
    }

    @Override
    public Object getItem(int position) {
        return imagePaths.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        Glide.with(context).load(new File(imagePaths.get(position))).into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(context).load(new File(imagePaths.get(position))).into(mainImage);
            }
        });

        return imageView;
    }
}




