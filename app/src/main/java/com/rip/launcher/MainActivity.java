package com.rip.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import android.os.AsyncTask;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

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

        final  TextView sub = new TextView(this);
        sub.setText("Minecraft Android Launcher");
        sub.setTextColor(Color.LTGRAY);
        sub.setTextSize(15);
        sub.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams subParams =
                new LinearLayout.LayoutParams(-1, -2);
        subParams.setMargins(0, 8, 0, 35);
        root.addView(sub, subParams);

        TextView versionText = new TextView(this);
        versionText.setText("🎮  Minecraft Version");
        versionText.setTextColor(Color.WHITE);
        versionText.setTextSize(17);
        root.addView(versionText);

        final  Spinner versions = new Spinner(this);

        String[] versionsitems = {
    "1.20.1",
    "1.20.2",
    "1.20.3",
    "1.20.4",
    "1.20.5",
    "1.20.6",

    "1.21",
    "1.21.1",
    "1.21.2",
    "1.21.3",
    "1.21.4",
    "1.21.5",
    "1.21.6",
    "1.21.7",
    "1.21.8",
    "1.21.9",
    "1.21.10",
    "1.21.11",

    "26.1",
    "26.1.1",
    "26.1.2"
                      };
        ArrayAdapter<String> versionAdapter =
                new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        versionsitems
                );

        versions.setAdapter(versionAdapter);

        root.addView(
                versions,
                new LinearLayout.LayoutParams(-1, -2)
        );

        TextView loaderText = new TextView(this);
        loaderText.setText("🧩  Mod Loader");
        loaderText.setTextColor(Color.WHITE);
        loaderText.setTextSize(17);

        LinearLayout.LayoutParams loaderParams =
                new LinearLayout.LayoutParams(-1, -2);
        loaderParams.setMargins(0, 25, 0, 5);

        root.addView(loaderText, loaderParams);

        final  Spinner loader = new Spinner(this);

        String[] loaders = {
                "Vanilla",
                "Fabric",
                "Forge",
                "NeoForge"
        };

        ArrayAdapter<String> loaderAdapter =
                new ArrayAdapter<String>(
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
        
        Button updateButton = new Button(this);
        updateButton.setText("🔄  CHECK FOR UPDATE");
        updateButton.setTextColor(Color.WHITE);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sub.setText("🔄 بررسی بروزرسانی...");
            }
        });

        LinearLayout.LayoutParams updateParams =
                new LinearLayout.LayoutParams(-1, -2);
        updateParams.setMargins(0, 15, 0, 10);

        root.addView(updateButton, updateParams);

play.setText("▶  PLAY MINECRAFT");
        play.setTextColor(Color.WHITE);
        play.setBackgroundColor(PURPLE);

        play.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        final String selectedVersion =
                versions.getSelectedItem().toString();

        final String selectedLoader =
                loader.getSelectedItem().toString();

        sub.setText(
                "در حال آماده‌سازی دانلود...\n" +
                "Version: " + selectedVersion +
                "\nLoader: " + selectedLoader
        );

        new AsyncTask<Void, Integer, String>() {

            @Override
            protected String doInBackground(Void... params) {

                try {
                    String manifestUrl =
                            "https://piston-meta.mojang.com/mc/game/version_manifest_v2.json";

                    URL manifestURL = new URL(manifestUrl);

                    HttpURLConnection manifestConnection =
                            (HttpURLConnection) manifestURL.openConnection();

                    manifestConnection.setConnectTimeout(15000);
                    manifestConnection.setReadTimeout(30000);
                    manifestConnection.connect();

                    InputStream manifestInput =
                            manifestConnection.getInputStream();

                    java.io.ByteArrayOutputStream manifestOutput =
                            new java.io.ByteArrayOutputStream();

                    byte[] manifestBuffer = new byte[8192];
                    int manifestLength;

                    while ((manifestLength =
                            manifestInput.read(manifestBuffer)) != -1) {

                        manifestOutput.write(
                                manifestBuffer,
                                0,
                                manifestLength
                        );
                    }

                    manifestInput.close();
                    manifestConnection.disconnect();

                    JSONObject manifest =
                            new JSONObject(manifestOutput.toString("UTF-8"));

                    JSONArray versionsArray =
                            manifest.getJSONArray("versions");

                    String versionUrl = null;

                    for (int i = 0; i < versionsArray.length(); i++) {

                        JSONObject version =
                                versionsArray.getJSONObject(i);

                        if (version.getString("id")
                                .equals(selectedVersion)) {

                            versionUrl =
                                    version.getString("url");

                            break;
                        }
                    }

                    if (versionUrl == null) {
                        return "نسخه " + selectedVersion + " پیدا نشد!";
                    }

                    URL versionURL = new URL(versionUrl);

                    HttpURLConnection versionConnection =
                            (HttpURLConnection) versionURL.openConnection();

                    versionConnection.setConnectTimeout(15000);
                    versionConnection.setReadTimeout(30000);
                    versionConnection.connect();

                    InputStream versionInput =
                            versionConnection.getInputStream();

                    java.io.ByteArrayOutputStream versionOutput =
                            new java.io.ByteArrayOutputStream();

                    byte[] versionBuffer = new byte[8192];
                    int versionLength;

                    while ((versionLength =
                            versionInput.read(versionBuffer)) != -1) {

                        versionOutput.write(
                                versionBuffer,
                                0,
                                versionLength
                        );
                    }

                    versionInput.close();
                    versionConnection.disconnect();

                    JSONObject versionData =
                            new JSONObject(versionOutput.toString("UTF-8"));

                    JSONObject downloads =
                            versionData.getJSONObject("downloads");

                    JSONObject client =
                            downloads.getJSONObject("client");

                    String clientUrl =
                            client.getString("url");

                    URL clientURL = new URL(clientUrl);

                    HttpURLConnection clientConnection =
                            (HttpURLConnection) clientURL.openConnection();

                    clientConnection.setConnectTimeout(15000);
                    clientConnection.setReadTimeout(60000);
                    clientConnection.connect();

                    if (clientConnection.getResponseCode() != 200) {
                        return "خطا در دانلود Minecraft: "
                                + clientConnection.getResponseCode();
                    }

                    java.io.File folder =
                            new java.io.File(
                                    getExternalFilesDir(null),
                                    "versions/" + selectedVersion
                            );

                    if (!folder.exists()) {
                        folder.mkdirs();
                    }

                    java.io.File clientFile =
                            new java.io.File(folder, "client.jar");

                    InputStream input =
                            clientConnection.getInputStream();

                    FileOutputStream output =
                            new FileOutputStream(clientFile);

                    byte[] buffer = new byte[8192];
                    int length;

                    while ((length = input.read(buffer)) != -1) {
                        output.write(buffer, 0, length);
                    }

                    output.close();
                    input.close();
                    clientConnection.disconnect();

                    return "Minecraft "
                            + selectedVersion
                            + " دانلود شد!";

                  } catch (Exception e) {
                      return "خطا: " + e.getMessage();
                  }
              }

              @Override
              protected void onPostExecute(String result) {
                  sub.setText(result);
              }

          }.execute();
      }
    });

    setContentView(root);
    }
}
