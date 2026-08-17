package com.rip.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView text = new TextView(this);
        text.setText("RIP LAUNCHER\n\nبرنامه اجرا شد!");
        text.setTextColor(Color.WHITE);
        text.setTextSize(24);
        text.setGravity(17);
        text.setBackgroundColor(Color.rgb(7, 9, 16));

        setContentView(text);
    }
}
