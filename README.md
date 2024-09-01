Library Management System
Overview
This Library Management System is a Java-based application that allows for basic management of a library's book collection. It provides functionality for adding books, borrowing books, returning books, and viewing available books.
Features

Add new books to the library
Borrow books from the library
Return books to the library
View all available books
Robust error handling for invalid operations

Prerequisites

Java Development Kit (JDK) 11 or later
JUnit 5 for running tests

Setup

Clone the repository:
Copygit clone https://github.com/yourusername/library-management-system.git

Navigate to the project directory:
Copycd library-management-system

Compile the Java files:
Copyjavac *.java


Running the Tests
To run the tests, use the following command:
Copyjava -cp .:junit-platform-console-standalone-1.7.0-all.jar org.junit.platform.console.ConsoleLauncher --scan-classpath
Note: Make sure you have the JUnit standalone jar in your classpath or adjust the command accordingly.
Usage
Here's a basic example of how to use the Library Management System:
javaCopypublic class Main {
public static void main(String[] args) {
LibraryManagementSystem library = new LibraryManagementSystem();

        // Adding a book
        Book book = new Book("9780061120084", "To Kill a Mockingbird", "Harper Lee", 1960);
        library.addBook(book);

        // Borrowing a book
        try {
            library.borrowBook("9780061120084");
            System.out.println("Book borrowed successfully");
        } catch (Exception e) {
            System.out.println("Error borrowing book: " + e.getMessage());
        }

        // Returning a book
        try {
            library.returnBook("9780061120084");
            System.out.println("Book returned successfully");
        } catch (Exception e) {
            System.out.println("Error returning book: " + e.getMessage());
        }

        // Viewing available books
        List<Book> availableBooks = library.getAvailableBooks();
        System.out.println("Available books: " + availableBooks.size());
    }
}
Contributing
Contributions to this Library Management System are welcome. Please feel free to submit a Pull Request.
Future Enhancements

Implement user management system
Add book quantity tracking
Implement due date system for borrowed books
Add search functionality (by author, title, etc.)
Implement data persistence (database integration)
