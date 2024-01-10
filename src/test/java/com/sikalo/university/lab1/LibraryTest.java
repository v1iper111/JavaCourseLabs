package com.sikalo.university.lab1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sikalo.university.lab1.book.*;
import com.sikalo.university.lab1.library.*;

public class LibraryTest {
    private Library library;
    private Book book;

    @BeforeEach
    public void setUp() {
        this.library = new Library();
        this.book = new Book(12345, "TestTitleInLibrary", "TestAuthorInLibrary", 1997); 
    }

    @Test
    void testAddBook() {
        library.addBook(book);
        assertNotNull(library.getBookByTitle("TestTitleInLibrary"));
    }

    @Test
    void testDelBookByIsbn() {
        library.addBook(book);
        assertTrue(library.delBookByIsbn(12345));
        assertNull(library.getBookByTitle("TestTitleInLibrary"));
    }

    @Test
    void testGetBookByTitle() {
        library.addBook(book);
        assertEquals(book, library.getBookByTitle("TestTitleInLibrary"));
        assertNull(library.getBookByTitle("IncorrectBookTitle"));
    }

    @Test
    void testGettingBooksListNonEmpty() {
        library.addBook(book);

        ArrayList<Book> list = new ArrayList<Book>();
        list.add(book);

        assertEquals(list, library.booksList());
    }

    @Test
    void testGettingBooksListEmpty() {
        assertNull(library.booksList());
    }

    @Test
    void testDelBookByIncorrectIsbn() {
        assertFalse(library.delBookByIsbn(243234324));
    }

    @Test
    void testEmptyLibrary() {
        assertFalse(library.delBookByIsbn(243234324));
        assertNull(library.getBookByTitle("TestTitleInLibrary"));
    }
}
