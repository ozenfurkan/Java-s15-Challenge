package com.library.management;

import com.library.management.library.Librarian;
import com.library.management.library.Library;
import com.library.management.user.MemberRecord;
import com.library.management.user.StudentMember;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import com.library.management.model.Author;
import com.library.management.model.Books;
import com.library.management.model.LibraryMaterials;

public class LibraryManagementApp {

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Kim olduğunu belirt (Librarian, Member, Author): ");
        String userType = scanner.nextLine().trim().toLowerCase();

        switch (userType) {
            case "librarian":
                System.out.println("Librarian ismi: ");
                String librarianName = scanner.nextLine();
                System.out.println("Librarian şifresi: ");
                String librarianPassword = scanner.nextLine();
                Librarian librarian = new Librarian(librarianName, librarianPassword);
                handleLibrarianActions(scanner, librarian, library);
                break;

            case "member":
                try {
                    System.out.println("Üye ID'sini girin: ");
                    long memberId = Long.parseLong(scanner.nextLine());
                    if (library.getMemberMap().containsKey(memberId)) {
                        handleMemberActions(scanner, memberId, library);
                    } else {
                        System.out.println("Üye bulunamadı.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Geçersiz ID formatı. Lütfen tekrar deneyin.");
                }
                break;

            case "author":
                System.out.println("Yazar ismini girin: ");
                String authorName = scanner.nextLine();
                handleAuthorActions(scanner, authorName, library);
                break;

            default:
                System.out.println("Geçersiz kullanıcı türü.");
                break;
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
            System.out.println("13. Bir önceki menüye dön");
            System.out.println("14. Çıkış");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
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
                        break;
                    case 2:
                        System.out.println("Çıkartılacak üyenin ID'sini girin: ");
                        long removeMemberId = Long.parseLong(scanner.nextLine());
                        librarian.removeMember(removeMemberId, library);
                        break;
                    case 3:
                        librarian.listMembers(library);
                        break;
                    case 4:
                        System.out.println("Üyenin ID'sini girin: ");
                        long memberId = Long.parseLong(scanner.nextLine());
                        librarian.displayMaterialsByMember(memberId, library);
                        break;
                    case 5:
                        System.out.println("Yazar ismini girin: ");
                        String authorName = scanner.nextLine();
                        librarian.displayMaterialsByAuthor(authorName, library);
                        break;
                    case 6:
                        librarian.displayBorrowedMaterials(library);
                        break;
                    case 7:
                        librarian.displayAllMaterials(library);
                        break;
                    case 8:
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

                        LibraryMaterials newMaterial = new Books(authorObj, title, status, price, edition, publicationDate, dateOfPurchase, null, "1234567890");
                        librarian.addNewMaterial(newMaterial, library);
                        break;
                    case 9:
                        System.out.println("Güncellenecek kitabın başlığını girin: ");
                        String materialTitle = scanner.nextLine();
                        System.out.println("Yeni başlık (boş bırakılırsa değişmez): ");
                        String newTitle = scanner.nextLine();
                        System.out.println("Yeni fiyat (boş bırakılırsa değişmez): ");
                        double newPrice = Double.parseDouble(scanner.nextLine());
                        System.out.println("Yeni durum (boş bırakılırsa değişmez): ");
                        String newStatus = scanner.nextLine();
                        System.out.println("Yeni baskı (boş bırakılırsa değişmez): ");
                        String newEdition = scanner.nextLine();
                        librarian.updateMaterialInfo(materialTitle, newTitle, newPrice, newStatus, newEdition, library);
                        break;
                    case 10:
                        System.out.println("Silinecek kitabın başlığını girin: ");
                        String deleteTitle = scanner.nextLine();
                        librarian.deleteMaterial(deleteTitle, library);
                        break;
                    case 11:
                        System.out.println("Ödünç verilecek kitabın başlığını girin: ");
                        String lendTitle = scanner.nextLine();
                        System.out.println("Üyenin ID'sini girin: ");
                        long lendMemberId = Long.parseLong(scanner.nextLine());
                        librarian.createBill(lendMemberId, 50.0);
                        MemberRecord borrower = library.getMemberMap().get(lendMemberId);
                        if (borrower != null) {
                            librarian.lendMaterial(lendTitle, borrower, library);
                        } else {
                            System.out.println("Üye bulunamadı.");
                        }
                        break;
                    case 12:
                        System.out.println("Geri alınacak kitabın başlığını girin: ");
                        String returnTitle = scanner.nextLine();
                        System.out.println("Üyenin ID'sini girin: ");
                        long returnMemberId = Long.parseLong(scanner.nextLine());
                        librarian.createBill(returnMemberId, 0.0);
                        MemberRecord returnBorrower = library.getMemberMap().get(returnMemberId);
                        if (returnBorrower != null) {
                            librarian.takeBackMaterial(returnTitle, returnBorrower, library);
                        } else {
                            System.out.println("Üye bulunamadı.");
                        }
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

    private static void handleMemberActions(Scanner scanner, long memberId, Library library) {
        while (true) {
            System.out.println("Member işlemleri: ");
            System.out.println("1. Ödünç alınan kitapları listele");
            System.out.println("2. Kitap ara (Başlık ile)");
            System.out.println("3. Kitap ara (Yazar ile)");
            System.out.println("4. Bir önceki menüye dön");
            System.out.println("5. Çıkış");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:

                        library.getMaterialsByMember().getOrDefault(memberId, new ArrayList<>())
                                .forEach(LibraryMaterials::displayBasicInfo);
                        break;
                    case 2:
                        System.out.println("Başlık girin: ");
                        String title = scanner.nextLine();
                        library.getAllMaterialsMap().getOrDefault(title, new ArrayList<>()).forEach(LibraryMaterials::displayBasicInfo);
                        break;
                    case 3:
                        System.out.println("Yazar ismini girin: ");
                        String authorName = scanner.nextLine();
                        library.getMaterialsByAuthor().getOrDefault(authorName, new ArrayList<>()).forEach(LibraryMaterials::displayBasicInfo);
                        break;
                    case 4:
                        return;
                    case 5:
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

    private static void handleAuthorActions(Scanner scanner, String authorName, Library library) {
        while (true) {
            System.out.println("Author işlemleri: ");
            System.out.println("1. Yazara ait kitapları listele");
            System.out.println("2. Bir önceki menüye dön");
            System.out.println("3. Çıkış");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        library.getMaterialsByAuthor().getOrDefault(authorName, new ArrayList<>())
                                .forEach(LibraryMaterials::displayBasicInfo);
                        break;
                    case 2:
                        return;
                    case 3:
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
}
