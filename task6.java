import java.util.*;

public class DigitalLibrarySystem {

    
    static class Book {
        String id, title, author;
        boolean isIssued;

        Book(String id, String title, String author) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.isIssued = false;
        }

        void display() {
            System.out.println("[" + id + "] " + title + " by " + author +
                    (isIssued ? " (Issued)" : " (Available)"));
        }
    }

    
    static class User {
        String username, role;

        User(String username, String role) {
            this.username = username;
            this.role = role;
        }
    }

    
    static User login(String username, String password) {
        if (username.equals("admin") && password.equals("admin123")) {
            return new User("admin", "admin");
        } else if (username.equals("user") && password.equals("user123")) {
            return new User("user", "user");
        }
        return null;
    }

    
    static class Library {
        List<Book> books = new ArrayList<>();

        void addBook(String id, String title, String author) {
            books.add(new Book(id, title, author));
            System.out.println("✅ Book added successfully!");
        }

        void viewBooks() {
            if (books.isEmpty()) {
                System.out.println("📭 No books in library.");
            } else {
                System.out.println("\n📚 Book List:");
                for (Book book : books) {
                    book.display();
                }
            }
        }

        void issueBook(String id) {
            for (Book book : books) {
                if (book.id.equals(id)) {
                    if (book.isIssued) {
                        System.out.println("⚠ Book is already issued.");
                    } else {
                        book.isIssued = true;
                        System.out.println("✅ Book issued.");
                    }
                    return;
                }
            }
            System.out.println("❌ Book ID not found.");
        }

        void returnBook(String id) {
            for (Book book : books) {
                if (book.id.equals(id)) {
                    if (!book.isIssued) {
                        System.out.println("⚠ Book is not issued.");
                    } else {
                        book.isIssued = false;
                        System.out.println("✅ Book returned.");
                    }
                    return;
                }
            }
            System.out.println("❌ Book ID not found.");
        }
    }

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();

        System.out.print("👤 Enter username: ");
        String uname = sc.nextLine();
        System.out.print("🔒 Enter password: ");
        String pass = sc.nextLine();

        User user = login(uname, pass);

        if (user == null) {
            System.out.println("❌ Invalid credentials. Exiting...");
            return;
        }

        System.out.println("\n✅ Welcome, " + user.username + " (" + user.role + ")");
        boolean run = true;

        while (run) {
            if (user.role.equals("admin")) {
                System.out.println("\n📌 Admin Menu:\n1. Add Book\n2. View Books\n3. Logout");
                System.out.print("👉 Choice: ");
                int ch = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (ch) {
                    case 1:
                        System.out.print("📖 Book ID: ");
                        String id = sc.nextLine();
                        System.out.print("📗 Title: ");
                        String title = sc.nextLine();
                        System.out.print("✍ Author: ");
                        String author = sc.nextLine();
                        lib.addBook(id, title, author);
                        break;
                    case 2:
                        lib.viewBooks();
                        break;
                    case 3:
                        run = false;
                        break;
                    default:
                        System.out.println("⚠ Invalid choice.");
                }

            } else if (user.role.equals("user")) {
                System.out.println("\n📌 User Menu:\n1. View Books\n2. Issue Book\n3. Return Book\n4. Logout");
                System.out.print("👉 Choice: ");
                int ch = sc.nextInt();
                sc.nextLine();

                switch (ch) {
                    case 1:
                        lib.viewBooks();
                        break;
                    case 2:
                        System.out.print("Enter Book ID to issue: ");
                        lib.issueBook(sc.nextLine());
                        break;
                    case 3:
                        System.out.print("Enter Book ID to return: ");
                        lib.returnBook(sc.nextLine());
                        break;
                    case 4:
                        run = false;
                        break;
                    default:
                        System.out.println("⚠ Invalid choice.");
                }
            }
        }

        System.out.println("👋 Thank you for using the Digital Library System.");
    }
}