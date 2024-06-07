package com.example.dcu_image_viewer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private List<String> imagePaths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);
        imagePaths = getImagePaths();

        ImageAdapter adapter = new ImageAdapter(this, imagePaths);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("imagePaths", new ArrayList<>(imagePaths));
                intent.putExtra("currentPosition", position);
                startActivity(intent);
            }
        });
    }

    private List<String> getImagePaths() {
        List<String> paths = new ArrayList<>();
        File directory = new File(Environment.getExternalStorageDirectory().getPath() + "/DCU_Image_Viewer/");
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".jpg")) {
                    paths.add(file.getAbsolutePath());
                }
            }
        }

        return paths;
    }
}