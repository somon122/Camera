package com.example.shariful.camera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowImageActivity extends AppCompatActivity {

    MydbHelper mydbHelper;
    private ListView listView;

    ArrayList<ImageMethod> imageList = new ArrayList<>();
    ImageAdapter imageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        mydbHelper=new MydbHelper(getApplicationContext());

        listView = findViewById(R.id.listView_id);

        imageList = mydbHelper.GetImage();
        imageAdapter = new ImageAdapter(this,imageList);
        listView.setAdapter(imageAdapter);

    }
}
