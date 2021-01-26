package com.example.projectequaleyes;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import static com.example.projectequaleyes.Titles.createTitlesList;


public class MainActivity extends AppCompatActivity {

    ArrayList<Titles> titlesArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getTitles();

    }

    private void getTitles() {
        new Thread(new Runnable() {
            StringBuilder strBuild = new StringBuilder();
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect("https://www.reddit.com/").get();
                    Elements elements = doc.select("h3._eYtD2XCVieq6emjKBH3m");

                    titlesArrayList = createTitlesList(elements);

                } catch (Exception ex) {
                    ex.fillInStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        RecyclerView rvTitles = (RecyclerView) findViewById(R.id.rvTitles);
                        TitlesAdapter adapter = new TitlesAdapter(titlesArrayList);
                        rvTitles.setAdapter(adapter);
                        rvTitles.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    }
                });
            }
        }).start();
    }
}