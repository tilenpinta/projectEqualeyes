package com.example.projectequaleyes;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainActivity extends AppCompatActivity {

    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = findViewById(R.id.titleRed);

        getTitles();
    }

    private void getTitles() {
        new Thread(new Runnable() {
            StringBuilder strBuild = new StringBuilder();
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect("https://www.reddit.com/").get();
                    Elements titles = doc.select("h3._eYtD2XCVieq6emjKBH3m");
                    int i = 1;
                    for (Element e : titles) {

                        strBuild.append(i + ". " + e.text() + "\n\n");
                        i++;
                    }
                    Log.d("aaa", strBuild.toString());
                } catch (Exception ex) {
                    ex.fillInStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvTitle.setText(strBuild.toString());
                    }
                });
            }
        }).start();
    }
}