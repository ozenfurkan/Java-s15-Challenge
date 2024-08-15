import com.library.management.management.Author;
import com.library.management.management.Library;
import com.library.management.model.*;
import com.library.management.user.GeneralMember;
import com.library.management.user.MemberRecord;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Library library = new Library();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;


        while (!exit) {
            System.out.println("\nLibrary Management System:");
            System.out.println("1. Add Material");
            System.out.println("2. Borrow Material");
            System.out.println("3. Return Material");
            System.out.println("4. Display All Materials");
            System.out.println("5. Display Borrowed Materials");
            System.out.println("6. Search Materials by Author");
            System.out.println("7. Search Materials by Member");
            System.out.println("8. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addMaterial(scanner);
                    break;
                case 2:
                    borrowMaterial(scanner);
                    break;
                case 3:
                    returnMaterial(scanner);
                    break;
                case 4:
                    library.displayAllMaterials();
                    break;
                case 5:
                    library.displayBorrowedMaterials();
                    break;
                case 6:
                    searchMaterialsByAuthor(scanner);
                    break;
                case 7:
                    searchMaterialsByMember(scanner);
                    break;
                case 8:
                    exit = true;
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }




    private static void addMaterial(Scanner scanner) {
        System.out.print("Enter material title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author name: ");
        String authorName = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter edition: ");
        String edition = scanner.nextLine();
        System.out.print("Enter publication date (YYYY-MM-DD): ");
        String publicationDate = scanner.nextLine();

        Author author = new Author(authorName);
        author.setMaterialObserver(library);

        Books book = new Books(author, title, "Available", price, edition, publicationDate, LocalDate.now(), null, "123456789");
        author.addMaterialbyAuthor(book);

        System.out.println("Material added successfully.");
    }

    private static void borrowMaterial(Scanner scanner) {
        System.out.print("Enter member name: ");
        String memberName = scanner.nextLine();
        System.out.print("Enter material title to borrow: ");
        String title = scanner.nextLine();

        MemberRecord member = new GeneralMember(1L, "2024-01-01", 0, 3, memberName, "Unknown", "1234567890", library);
        boolean borrowed = member.borrowBook(title);

        if (borrowed) {
            System.out.println("Material borrowed successfully.");
        } else {
            System.out.println("Failed to borrow material.");
        }
    }

    private static void returnMaterial(Scanner scanner) {
        System.out.print("Enter member name: ");
        String memberName = scanner.nextLine();
        System.out.print("Enter material title to return: ");
        String title = scanner.nextLine();

        MemberRecord member = new GeneralMember(1L, "2024-01-01", 0, 3, memberName, "Unknown", "1234567890", library);
        member.returnBook(title);

        System.out.println("Material returned successfully.");
    }

    private static void searchMaterialsByAuthor(Scanner scanner) {
        System.out.print("Enter author name: ");
        String authorName = scanner.nextLine();

        List<LibraryMaterials> materials = library.searchMaterialsByAuthor(authorName);

        if (materials.isEmpty()) {
            System.out.println("No materials found for author: " + authorName);
        } else {
            for (LibraryMaterials material : materials) {
                System.out.println(material);
            }
        }
    }

    private static void searchMaterialsByMember(Scanner scanner) {
        System.out.print("Enter member name: ");
        String memberName = scanner.nextLine();

        List<LibraryMaterials> materials = library.searchMaterialsByMember(memberName);

        if (materials.isEmpty()) {
            System.out.println("No materials found for member: " + memberName);
        } else {
            for (LibraryMaterials material : materials) {
                System.out.println(material);
            }
        }
    }
}
