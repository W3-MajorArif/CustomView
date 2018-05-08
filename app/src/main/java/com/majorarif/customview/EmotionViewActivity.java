package com.majorarif.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class EmotionViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotion_view);


        EmotionalFaceView happyButton = findViewById(R.id.happyButton);
        final EmotionalFaceView emotionalFaceView = findViewById(R.id.emotionalFaceView);
        EmotionalFaceView sadButton = findViewById(R.id.sadButton);


        happyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("button click ", "happy");
                emotionalFaceView.setHappinessState(EmotionalFaceView.HAPPY);
            }
        });

        sadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("button click ", "sad");
                emotionalFaceView.setHappinessState(EmotionalFaceView.SAD);
            }
        });
    }
}
