package com.ukom.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.ukom.view.StickyRadioGroup;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewGroup layout = findViewById(R.id.grid_layout);

        StickyRadioGroup radioGroup = new StickyRadioGroup(
                new StickyRadioGroup.OnCheckedChangedListener(){
            @Override
            public void onCheckedChanged(StickyRadioGroup group, int checkedId) {
                //just like RadioGroup
            }
        });
        radioGroup.stickTo(layout);

    }
}