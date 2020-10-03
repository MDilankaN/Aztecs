package com.example.mad;

public class News {
    //assign variables
    private String news;
    private String id;

    //constructor
    public News() {
    }
    //constructor
    public News(String news,String id) {
        this.news = news;
        this.id = id;
    }

    //getters


    public String getNews() {
        return news;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //setters
    public void setNews(String news) {
        this.news = news;
    }
}
