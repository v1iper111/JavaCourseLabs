package com.sikalo.university.lab1;

import com.sikalo.university.lab1.book.*;
import com.sikalo.university.lab1.library.*;

public class App 
{
    public static void main( String[] args )
    {
        Library lib = new Library();

        lib.addBook(new Book(12345, "Book1", "Vanya", 2000));
        lib.addBook(new Book(67890, "Book2", "Vasyl", 1967));
        lib.addBook(new Book(11123, "Book3", "Igor", 2007));

        System.out.println(lib.getBookByTitle("Book2").toString());
        // Book info:
        // - Title: Book2
        // - Author: Vasyl
        // - Year: 1967
        // - ISBN: 67890

        lib.showBooks();
        // Books list:
        // 1) ISBN: 12345; Title: Book1; Author: Vanya; Year: 2000.
        // 2) ISBN: 67890; Title: Book2; Author: Vasyl; Year: 1967.
        // 3) ISBN: 11123; Title: Book3; Author: Igor; Year: 2007.

        lib.delBookByIsbn(11123);

        lib.showBooks();
        // Books list:
        // 1) ISBN: 12345; Title: Book1; Author: Vanya; Year: 2000.
        // 2) ISBN: 67890; Title: Book2; Author: Vasyl; Year: 1967.

        lib.addBook(new Book(96874597, "Book4", "Ph", 2011));
        // Books list:
        // 1) ISBN: 12345; Title: Book1; Author: Vanya; Year: 2000.
        // 2) ISBN: 67890; Title: Book2; Author: Vasyl; Year: 1967.
        // 3) ISBN: 96874597; Title: Book4; Author: Ph; Year: 2011.

        lib.showBooks();
    }
}
