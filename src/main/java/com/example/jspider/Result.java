package com.example.jspider;

import java.util.ArrayList;
import java.util.List;

public class Result {
    private int id;
    private String title;
    private String url;
    private String abstracts;
    private String date;
    static int resultNum = 0;
    static List<Result> resultList = new ArrayList<>();

    public Result() {
    }
    public static void appendToResultList(Result result){
        resultList.add(result);
        resultNum++;
    }
    public Result(int id, String title, String url, String abstracts, String date) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.abstracts = abstracts;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", abstracts='" + abstracts + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
