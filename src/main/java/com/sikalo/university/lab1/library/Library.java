package com.sikalo.university.lab1.library;

import java.util.ArrayList;
import com.sikalo.university.lab1.book.*;

public class Library {
    private ArrayList<Book> books;


    public Library() {
        this.books = new ArrayList<Book>();
    }


    public void addBook(Book book) {
        books.add(book);
    }

    public Book getBookByTitle(String title) {
        for(Book book : this.books) {
            if(book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }

        return null;
    }

    public boolean delBookByIsbn(int isbn) {
        for(Book book : this.books) {
            if(book.getIsbn() == isbn) {
                this.books.remove(book);
                return true;
            }
        }

        return false;
    }

    public ArrayList<Book> booksList() {
        if(this.books.isEmpty()) return null;
        return this.books;
    }

    public void showBooks() {
        String books = "Books list:\n";

        int counter = 0;

        for(Book book : this.books) {
            counter++;

            books += " " + counter + ") ISBN: " + book.getIsbn() + "; Title: " + book.getTitle() + "; Author: " + book.getAuthor() + "; Year: " + book.getYear() + ".\n";
        }

        System.out.println(books);
    }
}
