package com.majorarif.customview;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.majorarif.customviews.ValueBar;
import com.majorarif.customviews.ValueSelector;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button updateButton = (Button) findViewById(R.id.barViewBtn);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this, BarViewActivity.class);
                startActivity(intent);
            }
        });

        Button emotionButton = (Button) findViewById(R.id.emotionViewBtn);
        emotionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this, EmotionViewActivity.class);
                startActivity(intent);
            }
        });
    }
}
