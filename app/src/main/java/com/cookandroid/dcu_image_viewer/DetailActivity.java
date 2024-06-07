package com.cookandroid.dcu_image_viewer;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private ImageView mainImage;
    private LinearLayout horizontalList;
    private List<String> imagePaths;
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mainImage = findViewById(R.id.mainImage);
        horizontalList = findViewById(R.id.horizontalList);

        imagePaths = getIntent().getStringArrayListExtra("imagePaths");
        currentPosition = getIntent().getIntExtra("currentPosition", 0);

        Glide.with(this).load(new File(imagePaths.get(currentPosition))).into(mainImage);

        DetailImageAdapter adapter = new DetailImageAdapter(this, imagePaths, mainImage);
        for (int i = 0; i < adapter.getCount(); i++) {
            View item = adapter.getView(i, null, null);
            horizontalList.addView(item);
        }
    }
}