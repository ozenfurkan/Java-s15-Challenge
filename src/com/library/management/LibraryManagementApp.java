package com.library.management;

import com.library.management.library.DisplayOptions;
import com.library.management.library.EventType;
import com.library.management.library.Librarian;
import com.library.management.library.Library;
import com.library.management.model.Status;
import com.library.management.user.MemberRecord;
import com.library.management.user.StudentMember;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;
import java.time.LocalDate;
import com.library.management.model.Author;
import com.library.management.model.Books;
import com.library.management.model.LibraryMaterials;



public class LibraryManagementApp {

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        Librarian librarian = new Librarian("Jorge Luis Borges", "password123");

        while (true) {
            System.out.println("Kim olduğunu belirt (Librarian, Member, Author): ");
            String userType = scanner.nextLine().trim().toLowerCase();

            switch (userType) {
                case "librarian":
                    handleLibrarianActions(scanner, librarian, library);
                    break;

                case "member":
                    handleMemberActions(scanner, library);
                    break;

                case "author":
                    handleAuthorActions(scanner, library);
                    break;

                default:
                    System.out.println("Geçersiz kullanıcı türü.");
                    break;
            }
        }
    }

    private static void handleLibrarianActions(Scanner scanner, Librarian librarian, Library library) {
        while (true) {
            System.out.println("Librarian işlemleri: ");
            System.out.println("1. Üye ekle");
            System.out.println("2. Üye çıkar");
            System.out.println("3. Üyeleri listele");
            System.out.println("4. Üye ID ile kitapları görüntüle");
            System.out.println("5. Yazar ismi ile kitapları görüntüle");
            System.out.println("6. Ödünç alınan kitapları listele");
            System.out.println("7. Tüm kitapları listele");
            System.out.println("8. Kitap ekle");
            System.out.println("9. Kitap bilgilerini güncelle");
            System.out.println("10. Kitap sil");
            System.out.println("11. Kitap ödünç ver");
            System.out.println("12. Kitap geri al");
            System.out.println("13. Çıkış");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        addMember(scanner, librarian, library);
                        break;
                    case 2:
                        removeMember(scanner, librarian, library);
                        break;
                    case 3:
                        librarian.listMembers(library);
                        break;
                    case 4:
                        displayMaterialsByMember(scanner, librarian, library);
                        break;
                    case 5:
                        displayMaterialsByAuthor(scanner, librarian, library);
                        break;
                    case 6:
                        librarian.update(EventType.DISPLAY, null, library, DisplayOptions.BORROWED);
                        break;
                    case 7:
                        librarian.update(EventType.DISPLAY, null, library, DisplayOptions.ALL);
                        break;
                    case 8:
                        addMaterial(scanner, librarian, library);
                        break;
                    case 9:
                        updateMaterialInfo(scanner, librarian, library);
                        break;
                    case 10:
                        deleteMaterial(scanner, librarian, library);
                        break;
                    case 11:
                        lendMaterial(scanner, librarian, library);
                        break;
                    case 12:
                        takeBackMaterial(scanner, librarian, library);
                        break;
                    case 13:
                        return;
                    case 14:
                        System.out.println("Çıkış yapılıyor...");
                        System.exit(0);
                    default:
                        System.out.println("Geçersiz seçenek.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Geçersiz giriş. Lütfen bir sayı girin.");
            }
        }
    }

    private static void addMember(Scanner scanner, Librarian librarian, Library library) {
        System.out.println("Yeni üyenin ID'sini girin: ");
        long newMemberId = Long.parseLong(scanner.nextLine());
        System.out.println("Yeni üyenin ismini girin: ");
        String newMemberName = scanner.nextLine();
        System.out.println("Üyelik tarihini girin (yyyy-mm-dd): ");
        String dateOfMembership = scanner.nextLine();
        System.out.println("Telefon numarasını girin: ");
        String phoneNumber = scanner.nextLine();
        System.out.println("Adresini girin: ");
        String address = scanner.nextLine();

        MemberRecord newMember = new StudentMember(newMemberId, dateOfMembership, 0, 5, newMemberName, phoneNumber, address);
        librarian.addMember(newMember, library);
    }

    private static void removeMember(Scanner scanner, Librarian librarian, Library library) {
        System.out.println("Çıkartılacak üyenin ID'sini girin: ");
        long removeMemberId = Long.parseLong(scanner.nextLine());
        librarian.removeMember(removeMemberId, library);
    }

    private static void displayMaterialsByMember(Scanner scanner, Librarian librarian, Library library) {
        System.out.println("Üyenin ID'sini girin: ");
        long memberId = Long.parseLong(scanner.nextLine());
        librarian.update(EventType.DISPLAY, null, library, DisplayOptions.MEMBER);
    }

    private static void displayMaterialsByAuthor(Scanner scanner, Librarian librarian, Library library) {
        System.out.println("Yazar ismini girin: ");
        String authorName = scanner.nextLine();
        librarian.update(EventType.DISPLAY, null, library, DisplayOptions.AUTHOR);
    }

    private static void addMaterial(Scanner scanner, Librarian librarian, Library library) {
        System.out.println("Yeni kitabın başlığını girin: ");
        String title = scanner.nextLine();
        System.out.println("Yazarın ismini girin: ");
        String author = scanner.nextLine();
        Author authorObj = new Author(author);
        System.out.println("Kitabın fiyatını girin: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Kitabın durumunu girin: ");
        String status = scanner.nextLine();
        System.out.println("Kitabın baskısını girin: ");
        String edition = scanner.nextLine();
        System.out.println("Yayın tarihini girin (yyyy-mm-dd): ");
        String publicationDate = scanner.nextLine();
        LocalDate dateOfPurchase = LocalDate.now();

        LibraryMaterials newMaterial = new Books("1", authorObj, title, price, Status.valueOf(status.toUpperCase()), edition, publicationDate, dateOfPurchase, null);
        librarian.update(EventType.ADD, newMaterial, library, null);
    }

    private static void updateMaterialInfo(Scanner scanner, Librarian librarian, Library library) {
        System.out.println("Güncellenecek kitabın başlığını girin: ");
        String materialTitle = scanner.nextLine();
        System.out.println("Yeni başlık (boş bırakılırsa değişmez): ");
        String newTitle = scanner.nextLine();
        System.out.println("Yeni fiyat (boş bırakılırsa değişmez): ");
        String newPrice = scanner.nextLine();
        System.out.println("Yeni durum (boş bırakılırsa değişmez): ");
        String newStatus = scanner.nextLine();
        System.out.println("Yeni baskı (boş bırakılırsa değişmez): ");
        String newEdition = scanner.nextLine();

        LibraryMaterials materialToUpdate = library.getAllMaterialsMap().values().stream()
                .flatMap(List::stream)
                .filter(m -> m.getTitle().equals(materialTitle))
                .findFirst()
                .orElse(null);

        if (materialToUpdate != null) {
            materialToUpdate.setTitle(newTitle.isEmpty() ? materialToUpdate.getTitle() : newTitle);
            materialToUpdate.setPrice(newPrice.isEmpty() ? materialToUpdate.getPrice() : Double.parseDouble(newPrice));
            materialToUpdate.setStatus(newStatus.isEmpty() ? materialToUpdate.getStatus() : Status.valueOf(newStatus.toUpperCase()));
            materialToUpdate.setEdition(newEdition.isEmpty() ? materialToUpdate.getEdition() : newEdition);
            librarian.update(EventType.UPDATE, materialToUpdate, library, null);
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }

    private static void deleteMaterial(Scanner scanner, Librarian librarian, Library library) {
        System.out.println("Silinecek kitabın başlığını girin: ");
        String deleteTitle = scanner.nextLine();

        LibraryMaterials materialToDelete = library.getAllMaterialsMap().values().stream()
                .flatMap(List::stream)
                .filter(m -> m.getTitle().equals(deleteTitle))
                .findFirst()
                .orElse(null);

        if (materialToDelete != null) {
            librarian.update(EventType.DELETE, materialToDelete, library, null);
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }

    private static void lendMaterial(Scanner scanner, Librarian librarian, Library library) {
        System.out.println("Ödünç verilecek kitabın başlığını girin: ");
        String lendTitle = scanner.nextLine();
        System.out.println("Üyenin ID'sini girin: ");
        long lendMemberId = Long.parseLong(scanner.nextLine());
        MemberRecord borrower = library.getMemberMap().get(lendMemberId);

        if (borrower != null) {
            LibraryMaterials materialToLend = library.getAllMaterialsMap().values().stream()
                    .flatMap(List::stream)
                    .filter(m -> m.getTitle().equals(lendTitle))
                    .findFirst()
                    .orElse(null);

            if (materialToLend != null) {
                materialToLend.setCurrentHolder(borrower);
                librarian.update(EventType.LEND, materialToLend, library, null);
            } else {
                System.out.println("Kitap bulunamadı.");
            }
        } else {
            System.out.println("Üye bulunamadı.");
        }
    }

    private static void takeBackMaterial(Scanner scanner, Librarian librarian, Library library) {
        System.out.println("Geri alınacak kitabın başlığını girin: ");
        String returnTitle = scanner.nextLine();
        System.out.println("Üyenin ID'sini girin: ");
        long returnMemberId = Long.parseLong(scanner.nextLine());
        MemberRecord returnBorrower = library.getMemberMap().get(returnMemberId);

        if (returnBorrower != null) {
            LibraryMaterials materialToReturn = library.getAllMaterialsMap().values().stream()
                    .flatMap(List::stream)
                    .filter(m -> m.getTitle().equals(returnTitle) && m.getCurrentHolder() != null && m.getCurrentHolder().getMemberId() == returnMemberId)
                    .findFirst()
                    .orElse(null);

            if (materialToReturn != null) {
                materialToReturn.setCurrentHolder(null);
                librarian.update(EventType.RETURN, materialToReturn, library, null);
            } else {
                System.out.println("Kitap bulunamadı veya bu üye tarafından ödünç alınmamış.");
            }
        } else {
            System.out.println("Üye bulunamadı.");
        }
    }

    private static void handleMemberActions(Scanner scanner, Library library) {
        System.out.println("Üye ID'sini girin: ");
        long memberId = Long.parseLong(scanner.nextLine());

        library.getMaterialsByMember().getOrDefault(memberId, new ArrayList<>())
                .forEach(LibraryMaterials::displaySpecificInfo);
    }

    private static void handleAuthorActions(Scanner scanner, Library library) {
        System.out.println("Yazar ismini girin: ");
        String authorName = scanner.nextLine();

        library.getMaterialsByAuthor().getOrDefault(authorName, new ArrayList<>())
                .forEach(LibraryMaterials::displaySpecificInfo);
    }
}


