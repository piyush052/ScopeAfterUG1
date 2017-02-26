package com.webandrioz.scopeafterug.models;

/**
 * Created by root on 26/2/17.
 */

public class Book {
    String name,author,buy;

    public Book(String name, String author, String buy) {
        this.name = name;
        this.author = author;
        this.buy = buy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }
}
