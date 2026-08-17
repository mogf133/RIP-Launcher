package com.rip.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {

    private final int BG = Color.rgb(7, 9, 16);
    private final int PURPLE = Color.rgb(120, 80, 255);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER_HORIZONTAL);
        root.setPadding(35, 45, 35, 35);
        root.setBackgroundColor(BG);

        TextView title = new TextView(this);
        title.setText("RIP LAUNCHER");
        title.setTextColor(Color.WHITE);
        title.setTextSize(30);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);

        root.addView(title);

        TextView sub = new TextView(this);
        sub.setText("Minecraft Android Launcher");
        sub.setTextColor(Color.LTGRAY);
        sub.setTextSize(15);
        sub.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams subParams =
                new LinearLayout.LayoutParams(-1, -2);

        subParams.setMargins(0, 8, 0, 35);
        root.addView(sub, subParams);

        TextView versionText = new TextView(this);
        versionText.setText("🎮 Minecraft Version");
        versionText.setTextColor(Color.WHITE);
        versionText.setTextSize(17);

        root.addView(versionText);

        Spinner versions = new Spinner(this);

        String[] versionList = {
                "1.21.10",
                "1.21.8",
                "1.21.5",
                "1.21.4",
                "1.21.1"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        versionList
                );

        versions.setAdapter(adapter);

        root.addView(
                versions,
                new LinearLayout.LayoutParams(-1, -2)
        );

        TextView loaderText = new TextView(this);
        loaderText.setText("🧩 Mod Loader");
        loaderText.setTextColor(Color.WHITE);
        loaderText.setTextSize(17);

        LinearLayout.LayoutParams loaderParams =
                new LinearLayout.LayoutParams(-1, -2);

        loaderParams.setMargins(0, 25, 0, 5);
        root.addView(loaderText, loaderParams);

        Spinner loader = new Spinner(this);

        String[] loaders = {
                "Vanilla",
                "Fabric",
                "Forge",
                "NeoForge"
        };

        ArrayAdapter<String> loaderAdapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        loaders
                );

        loader.setAdapter(loaderAdapter);

        root.addView(
                loader,
                new LinearLayout.LayoutParams(-1, -2)
        );

        Button controls = new Button(this);
        controls.setText("🎛️  Import Pojav Controls");

        controls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sub.setText("Pojav Controls: آماده برای Import");
            }
        });

        LinearLayout.LayoutParams controlParams =
                new LinearLayout.LayoutParams(-1, -2);

        controlParams.setMargins(0, 30, 0, 10);
        root.addView(controls, controlParams);

        Button play = new Button(this);
        play.setText("▶  PLAY MINECRAFT");
        play.setTextColor(Color.WHITE);
        play.setBackgroundColor(PURPLE);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String selectedVersion =
                        versions.getSelectedItem().toString();

                String selectedLoader =
                        loader.getSelectedItem().toString();

                sub.setText(
                        "Version: " + selectedVersion +
                        "\nLoader: " + selectedLoader +
                        "\n\nLauncher Engine به‌زودی..."
                );
            }
        });

        root.addView(
                play,
                new LinearLayout.LayoutParams(-1, -2)
        );

        TextView footer = new TextView(this);
        footer.setText("Rip Launcher • v1.0");
        footer.setTextColor(Color.GRAY);
        footer.setTextSize(12);
        footer.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams footerParams =
                new LinearLayout.LayoutParams(-1, -2);

        footerParams.setMargins(0, 35, 0, 0);
        root.addView(footer, footerParams);

        setContentView(root);
    }
}
