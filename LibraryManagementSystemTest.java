import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryManagementSystemTest {
    private LibraryManagementSystem lib;

    @BeforeEach
    void setUp() {
        lib = new LibraryManagementSystem();
    }

    @Nested
    class AddingBooks {
        @Test
        void testAddBookWithValidDetails() {
            Book book = new Book("9780061120084", "To Kill a Mockingbird", "Harper Lee", 1960);
            lib.addBook(book);
            assertEquals(1, lib.getTotalBooks());
        }

        @Test
        void testAddBookWithMissingTitle() {
            assertThrows(IllegalArgumentException.class, () -> new Book("9780061120084", "", "Harper Lee", 1960));
        }

        @Test
        void testAddBookWithMissingAuthor() {
            assertThrows(IllegalArgumentException.class, () -> new Book("9780061120084", "To Kill a Mockingbird", "", 1960));
        }

        @Test
        void testAddBookWithMissingISBN() {
            assertThrows(IllegalArgumentException.class, () -> new Book("", "To Kill a Mockingbird", "Harper Lee", 1960));
        }

        @Test
        void testAddBookWithInvalidISBNFormat() {
            assertThrows(IllegalArgumentException.class, () -> new Book("invalid-isbn", "To Kill a Mockingbird", "Harper Lee", 1960));
        }

        @Test
        void testAddDuplicateBook() {
            Book book1 = new Book("9780061120084", "To Kill a Mockingbird", "Harper Lee", 1960);
            Book book2 = new Book("9780061120084", "To Kill a Mockingbird", "Harper Lee", 1960);
            lib.addBook(book1);
            assertThrows(IllegalArgumentException.class, () -> lib.addBook(book2));
        }

        @Test
        void testAddMultipleBooksInSuccession() {
            lib.addBook(new Book("9780061120084", "To Kill a Mockingbird", "Harper Lee", 1960));
            lib.addBook(new Book("9780141439518", "Pride and Prejudice", "Jane Austen", 1813));
            lib.addBook(new Book("9780743273565", "The Great Gatsby", "F. Scott Fitzgerald", 1925));
            assertEquals(3, lib.getTotalBooks());
        }
    }

    @Nested
    class BorrowingBooks {
        @Test
        void testBorrowAvailableBook() throws Exception {
            Book book = new Book("9780061120084", "To Kill a Mockingbird", "Harper Lee", 1960);
            lib.addBook(book);
            lib.borrowBook("9780061120084");
            assertEquals(0, lib.getAvailableBooks().size());
        }

        @Test
        void testBorrowNonexistentBook() {
            assertThrows(Exception.class, () -> lib.borrowBook("9780999999999"));
        }

        @Test
        void testBorrowUnavailableBook() throws Exception {
            Book book = new Book("9780061120084", "To Kill a Mockingbird", "Harper Lee", 1960);
            lib.addBook(book);
            lib.borrowBook("9780061120084");
            assertThrows(Exception.class, () -> lib.borrowBook("9780061120084"));
        }

        // Note: We can't test user-specific scenarios or quantity-based tests
        // with our current implementation. We'd need to extend the system to support these.
    }

    @Nested
    class ReturningBooks {
        @Test
        void testReturnBorrowedBook() throws Exception {
            Book book = new Book("9780061120084", "To Kill a Mockingbird", "Harper Lee", 1960);
            lib.addBook(book);
            lib.borrowBook("9780061120084");
            lib.returnBook("9780061120084");
            assertEquals(1, lib.getAvailableBooks().size());
        }

        @Test
        void testReturnNonBorrowedBook() throws Exception {
            Book book = new Book("9780061120084", "To Kill a Mockingbird", "Harper Lee", 1960);
            lib.addBook(book);
            lib.returnBook("9780061120084");  // Book wasn't borrowed, but this should work
            assertEquals(1, lib.getAvailableBooks().size());
        }

        @Test
        void testReturnNonexistentBook() {
            assertThrows(Exception.class, () -> lib.returnBook("9780999999999"));
        }
    }

    @Nested
    class ViewingAvailableBooks {
        @Test
        void testViewAvailableBooksWhenLibraryIsEmpty() {
            List<Book> availableBooks = lib.getAvailableBooks();
            assertTrue(availableBooks.isEmpty());
        }

        @Test
        void testViewAvailableBooksWithMixOfAvailableAndUnavailable() throws Exception {
            lib.addBook(new Book("9780061120084", "To Kill a Mockingbird", "Harper Lee", 1960));
            lib.addBook(new Book("9780141439518", "Pride and Prejudice", "Jane Austen", 1813));
            lib.borrowBook("9780061120084");
            List<Book> availableBooks = lib.getAvailableBooks();
            assertEquals(1, availableBooks.size());
            assertEquals("Pride and Prejudice", availableBooks.get(0).getTitle());
        }
    }

    @Nested
    class GeneralSystemTests {
        @Test
        void testSequenceOfOperations() throws Exception {
            // Add book
            lib.addBook(new Book("9780061120084", "To Kill a Mockingbird", "Harper Lee", 1960));
            assertEquals(1, lib.getTotalBooks());

            // Borrow book
            lib.borrowBook("9780061120084");
            assertTrue(lib.getAvailableBooks().isEmpty());

            // Return book
            lib.returnBook("9780061120084");
            assertEquals(1, lib.getAvailableBooks().size());
        }

        @Test
        void testLargeNumberOfBooks() {
            for (int i = 0; i < 1000; i++) {
                lib.addBook(new Book(String.format("978%010d", i), "Book " + i, "Author " + i, 2000 + (i % 30)));
            }
            assertEquals(1000, lib.getTotalBooks());
            assertEquals(1000, lib.getAvailableBooks().size());
        }
    }
}
