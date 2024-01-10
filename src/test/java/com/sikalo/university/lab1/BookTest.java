package com.sikalo.university.lab1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sikalo.university.lab1.book.*;

public class BookTest {

    private Book book;

    @BeforeEach
    public void setUp()
    {
        this.book = new Book(1468024, "TestBook", "TestAuthor", 2003);
    }

    @Test
    void testGetTitle() {
        assertEquals("TestBook", book.getTitle());
    }

    @Test
    void testGetAuthor() {
        assertEquals("TestAuthor", book.getAuthor());
    }

    @Test
    void testGetIsbn() {
        assertEquals(1468024, book.getIsbn());
    }

    @Test
    void testGetYear() {
        assertEquals(2003, book.getYear());
    }

    @Test
    void testInvalidYearInputting() {
        assertThrows
        (
            IllegalArgumentException.class, 
            () -> { 
                new Book(12345, "InvalidBookTitle", "InvalidBookAuthor", 1); 
            }
        );
    }
}