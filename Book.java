public class Book {
    private String isbn;
    private String title;
    private String author;
    private int pubYear;
    private boolean available;

    public Book(String isbn, String title, String author, int pubYear) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be empty");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be empty");
        }
        if (!isValidISBN(isbn)) {
            throw new IllegalArgumentException("Invalid ISBN format");
        }

        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.pubYear = pubYear;
        this.available = true;
    }

    private boolean isValidISBN(String isbn) {
        // This is a simplified ISBN validation
        // In a real system, you'd want a more robust check
        return isbn.matches("\\d{13}|\\d{10}");
    }

    // Getters and setters
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPubYear() { return pubYear; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
