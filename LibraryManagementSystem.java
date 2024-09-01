import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LibraryManagementSystem {
    private Map<String, Book> bookCollection;

    public LibraryManagementSystem() {
        this.bookCollection = new HashMap<>();
    }

    public void addBook(Book book) {
        if (bookCollection.containsKey(book.getIsbn())) {
            throw new IllegalArgumentException("Duplicate ISBN: " + book.getIsbn());
        }
        bookCollection.put(book.getIsbn(), book);
    }

    public void borrowBook(String isbn) throws Exception {
        Book book = bookCollection.get(isbn);
        if (book == null) {
            throw new Exception("Book not found: " + isbn);
        }
        if (!book.isAvailable()) {
            throw new Exception("Book is already borrowed: " + isbn);
        }
        book.setAvailable(false);
    }

    public void returnBook(String isbn) throws Exception {
        Book book = bookCollection.get(isbn);
        if (book == null) {
            throw new Exception("Book not found: " + isbn);
        }
        book.setAvailable(true);
    }

    public List<Book> getAvailableBooks() {
        return bookCollection.values().stream()
                .filter(Book::isAvailable)
                .collect(java.util.stream.Collectors.toList());
    }

    public int getTotalBooks() {
        return bookCollection.size();
    }
}
