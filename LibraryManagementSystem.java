// ------------------ Library Management System ------------------

import java.util.*;

class Book {
    String bookId, title, author;
    boolean available = true;

    Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        String status = available ? "Available" : "Borrowed";
        return "[" + bookId + "] " + title + " by " + author + " - " + status;
    }
}

class Member {
    String memberId, name;
    List<String> borrowedBooks = new ArrayList<>();

    Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Member " + memberId + ": " + name + " | Borrowed: " 
                + borrowedBooks.size() + " book(s)";
    }
}

class Library {
    Map<String, Book> books = new HashMap<>();
    Map<String, Member> members = new HashMap<>();
    Scanner sc = new Scanner(System.in);

    // ------------ Book Management ------------
    void addBook() {
        System.out.print("Book ID: ");
        String id = sc.nextLine();
        System.out.print("Title: ");
        String title = sc.nextLine();
        System.out.print("Author: ");
        String author = sc.nextLine();

        if (books.containsKey(id)) {
            System.out.println("Book ID already exists!");
            return;
        }

        books.put(id, new Book(id, title, author));
        System.out.println("Book added successfully!");
    }

    void listBooks() {
        System.out.println("\n---- Book List ----");
        for (Book b : books.values()) {
            System.out.println(b);
        }
    }

    // ------------ Member Management ------------
    void addMember() {
        System.out.print("Member ID: ");
        String id = sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();

        if (members.containsKey(id)) {
            System.out.println("Member ID already exists!");
            return;
        }

        members.put(id, new Member(id, name));
        System.out.println("Member added successfully!");
    }

    void listMembers() {
        System.out.println("\n---- Member List ----");
        for (Member m : members.values()) {
            System.out.println(m);
        }
    }

    // ------------ Borrow & Return ------------
    void borrowBook() {
        System.out.print("Member ID: ");
        String mid = sc.nextLine();
        System.out.print("Book ID: ");
        String bid = sc.nextLine();

        if (!members.containsKey(mid)) {
            System.out.println("Member not found!");
            return;
        }
        if (!books.containsKey(bid)) {
            System.out.println("Book not found!");
            return;
        }

        Member m = members.get(mid);
        Book b = books.get(bid);

        if (!b.available) {
            System.out.println("Book is already borrowed!");
            return;
        }

        b.available = false;
        m.borrowedBooks.add(bid);
        System.out.println(m.name + " borrowed '" + b.title + "'");
    }

    void returnBook() {
        System.out.print("Member ID: ");
        String mid = sc.nextLine();
        System.out.print("Book ID: ");
        String bid = sc.nextLine();

        if (!members.containsKey(mid)) {
            System.out.println("Member not found!");
            return;
        }

        Member m = members.get(mid);

        if (!m.borrowedBooks.contains(bid)) {
            System.out.println("This member did not borrow this book!");
            return;
        }

        m.borrowedBooks.remove(bid);
        books.get(bid).available = true;
        System.out.println("Book returned successfully!");
    }

    // ------------ Menu ------------
    void menu() {
        while (true) {
            System.out.println("\n===== LIBRARY MENU =====");
            System.out.println("1. Add Book");
            System.out.println("2. List Books");
            System.out.println("3. Add Member");
            System.out.println("4. List Members");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");

            System.out.print("Enter choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1": addBook(); break;
                case "2": listBooks(); break;
                case "3": addMember(); break;
                case "4": listMembers(); break;
                case "5": borrowBook(); break;
                case "6": returnBook(); break;
                case "7": 
                    System.out.println("Goodbye!"); 
                    return;
                default: System.out.println("Invalid choice!");
            }
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        library.menu();
    }
}