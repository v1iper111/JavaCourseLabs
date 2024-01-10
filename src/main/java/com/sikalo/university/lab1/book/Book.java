package com.sikalo.university.lab1.book;

public class Book {
    private int isbn;
    private String title;
    private String author;
    private int year;


    public Book
    (
        int isbn, 
        String title, 
        String author, 
        int year
    )
    {
        if(year <= 868) throw new IllegalArgumentException("Year can't be earlier than 868!");
        
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
    }


    public int getIsbn() {
        return this.isbn;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getYear() {
        return this.year;
    }


    @Override
    public String toString()
    {
        return "Book info:\n - Title: " + title + "\n - Author: " + author + "\n - Year: " + year + "\n - ISBN: " + isbn + "\n";
    }
}
