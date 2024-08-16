package com.library.management;

import com.library.management.management.Library;
import com.library.management.management.Librarian;
import com.library.management.management.MemberRecord;
import com.library.management.model.Author;
import com.library.management.model.Books;
import com.library.management.model.LibraryMaterials;
import com.library.management.user.GeneralMember;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Kütüphaneyi oluştur
        Library library = new Library();

        // Kütüphaneci oluştur
        Librarian librarian = new Librarian("admin", "password", library);

        // Üyeler oluştur
        MemberRecord member1 = new GeneralMember(1, "General", "2023-08-16", 0, 5, "Alice", "123456789", "Wonderland");
        MemberRecord member2 = new GeneralMember(2, "General", "2023-08-16", 0, 5, "Bob", "987654321", "Neverland");

        // Yazar ve kitapları oluştur
        Author author = new Author("J.K. Rowling");

        LibraryMaterials book1 = new Books(author, "Harry Potter and the Philosopher's Stone", "Available", 39.99, "1st Edition", "1997", LocalDate.now(), null, "ED1231");
        LibraryMaterials book2 = new Books(author, "Harry Potter and the Chamber of Secrets", "Available", 29.99, "1st Edition", "1998", LocalDate.now(), null, "RD123141");

        // Kitapları kütüphaneye ekle
        librarian.addNewMaterial(book1);
        librarian.addNewMaterial(book2);

        // Kitapları ödünç ver
        librarian.lendMaterial(book1.getTitle(), member1);
        librarian.lendMaterial(book2.getTitle(), member2);

        // Kütüphanedeki tüm kitapları görüntüle
        library.displayAvailableBooks();

        // Ödünç alınan kitapları görüntüle
        library.displayBorrowedBooks();

        // Bir kitabı geri al
        librarian.takeBackBook(book1.getTitle(), member1);

        // Kitapları tekrar görüntüle
        library.displayAvailableBooks();
        library.displayBorrowedBooks();

        // Bir üyenin ödünç aldığı kitapları görüntüle
        System.out.println("Books borrowed by Alice:");
        for (LibraryMaterials material : library.getBooksBorrowedByMember(member1.getMemberId())) {
            System.out.println("  - " + material.getTitle());
        }

        // Yazar adına göre kitap arama
        System.out.println("Books by J.K. Rowling:");
        for (LibraryMaterials material : library.searchBooksByAuthor("J.K. Rowling")) {
            System.out.println("  - " + material.getTitle());
        }

        // Üyeye göre kitap arama
        System.out.println("Books borrowed by Bob:");
        for (LibraryMaterials material : library.searchBooksByMember(member2.getMemberId())) {
            System.out.println("  - " + material.getTitle());
        }

        while (true) {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Display Available Books");
            System.out.println("2. Display Borrowed Books");
            System.out.println("3. Lend a Book");
            System.out.println("4. Return a Book");
            System.out.println("5. Search Books by Author");
            System.out.println("6. Search Books by Member");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    library.displayAvailableBooks();
                    break;
                case 2:
                    library.displayBorrowedBooks();
                    break;
                case 3:
                    System.out.print("Enter the title of the book to lend: ");
                    String lendTitle = scanner.nextLine();
                    System.out.print("Enter the member ID to lend to (1 for Alice, 2 for Bob): ");
                    int lendMemberId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    MemberRecord lendMember = (lendMemberId == 1) ? member1 : member2;
                    librarian.lendMaterial(lendTitle, lendMember);
                    break;
                case 4:
                    System.out.print("Enter the title of the book to return: ");
                    String returnTitle = scanner.nextLine();
                    System.out.print("Enter the member ID who is returning the book (1 for Alice, 2 for Bob): ");
                    int returnMemberId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    MemberRecord returnMember = (returnMemberId == 1) ? member1 : member2;
                    librarian.takeBackBook(returnTitle, returnMember);
                    break;
                case 5:
                    System.out.print("Enter the author name to search books: ");
                    String authorName = scanner.nextLine();
                    for (LibraryMaterials material : library.searchBooksByAuthor(authorName)) {
                        System.out.println("  - " + material.getTitle());
                    }
                    break;
                case 6:
                    System.out.print("Enter the member ID to search books borrowed by them (1 for Alice, 2 for Bob): ");
                    int searchMemberId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    for (LibraryMaterials material : library.searchBooksByMember(searchMemberId)) {
                        System.out.println("  - " + material.getTitle());
                    }
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}


