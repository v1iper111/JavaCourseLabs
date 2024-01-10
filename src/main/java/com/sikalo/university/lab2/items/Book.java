package com.sikalo.university.lab2.items;

import com.sikalo.university.lab2.Item;

public class Book extends Item {
    private final String author;
    private final String isbn;
    private final int publishingYear;

    public Book(String name, String author, String isbn, int publishingYear) {
        super(name);
        this.author = author;
        this.isbn = isbn;
        this.publishingYear = publishingYear;
    }

    public String getName() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getPublishingYear() {
        return publishingYear;
    }
}
