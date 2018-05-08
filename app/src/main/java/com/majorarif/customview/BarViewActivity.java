package com.majorarif.customview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.majorarif.customviews.ValueBar;
import com.majorarif.customviews.ValueSelector;

public class BarViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_view);


        final ValueSelector valueSelector = (ValueSelector) findViewById(R.id.valueSelector);
        valueSelector.setMinValue(0);
        valueSelector.setMaxValue(100);

        final ValueBar valueBar = (ValueBar) findViewById(R.id.valueBar);
        valueBar.setMaxValue(100);
        valueBar.setAnimated(true);
        valueBar.setAnimationDuration(4000l);

        Button updateButton = (Button) findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = valueSelector.getValue();
                valueBar.setValue(value);

                //code to use Object Animation instead of the built-in ValueBar animation
                //if you use this, be sure the call valueBar.setAnimated(false);
                /*
                ObjectAnimator anim = ObjectAnimator.ofInt(valueBar, "value", valueBar.getValue(), value);
                anim.setDuration(1000);
                anim.start();
                */
            }
        });
    }

}
