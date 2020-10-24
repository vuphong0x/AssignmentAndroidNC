package com.phong.hotrohoctap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.phong.hotrohoctap.News.NewsActivity;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Màn Hình Chính");
    }

    public void openCourse(View view) {
        intent = new Intent(this, StudentActivity.class);
        startActivity(intent);
    }

    public void openMap(View view) {
        intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void openNews(View view) {
        intent = new Intent(this, NewsActivity.class);
        startActivity(intent);
    }

    public void openSocial(View view) {
        intent = new Intent(this, SocialActivity.class);
        startActivity(intent);
    }
}