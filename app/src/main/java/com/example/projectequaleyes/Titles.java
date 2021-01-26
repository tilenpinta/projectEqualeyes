package com.example.projectequaleyes;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Titles{
    String title;

    public Titles(String title) {
        this.title = title;
    }

    public static ArrayList<Titles> createTitlesList(Elements ele) {
        ArrayList<Titles> titles = new ArrayList<Titles>();
        for (Element e : ele) {
            titles.add(new Titles(e.text()));
        }
        return titles;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
