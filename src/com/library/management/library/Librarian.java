package com.library.management.library;

import com.library.management.model.LibraryMaterials;
import com.library.management.observer.LibraryObserver;
import com.library.management.user.MemberRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Librarian implements LibraryObserver {
    private final String name;
    private final String password;

    public Librarian(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public void update(EventType eventType, LibraryMaterials material) {
        switch (eventType) {
            case ADD:
                System.out.println("Librarian: A new material has been added: " + material.getTitle());
                break;
            case LEND:
                System.out.println("Librarian: The material has been lent: " + material.getTitle());
                break;
            case RETURN:
                System.out.println("Librarian: The material has been returned: " + material.getTitle());
                break;
            case DELETE:
                System.out.println("Librarian: A material has been deleted: " + material.getTitle());
                break;
            default:
                System.out.println("Librarian: Unknown event type.");
                break;
        }
    }

    public void addNewMaterial(LibraryMaterials material, Library library) {
        Map<String, List<LibraryMaterials>> allMaterialsMap = library.getAllMaterialsMap();
        Map<String, List<LibraryMaterials>> materialsByAuthor = library.getMaterialsByAuthor();

        allMaterialsMap.computeIfAbsent(material.getTitle(), k -> new ArrayList<>()).add(material);
        materialsByAuthor.computeIfAbsent(material.getAuthor(), k -> new ArrayList<>()).add(material);

        library.notifyObservers(EventType.ADD, material);
    }

    public void lendMaterial(String materialTitle, MemberRecord borrower, Library library) {
        Map<String, List<LibraryMaterials>> allMaterialsMap = library.getAllMaterialsMap();
        Map<Long, List<LibraryMaterials>> materialsByMember = library.getMaterialsByMember();

        List<LibraryMaterials> borrowedMaterials = materialsByMember.get(borrower.getMemberId());


        if (borrowedMaterials != null) {
            for (LibraryMaterials material : borrowedMaterials) {
                if (material.getTitle().equals(materialTitle)) {
                    System.out.println("Üye zaten bu başlıkta bir kitabı ödünç aldı: " + materialTitle);
                    return;
                }
            }
        }

        List<LibraryMaterials> availableMaterials = allMaterialsMap.get(materialTitle);
        if (availableMaterials != null && !availableMaterials.isEmpty()) {
            LibraryMaterials material = availableMaterials.remove(0);
            material.setCurrentHolder(borrower);

            materialsByMember.computeIfAbsent(borrower.getMemberId(), k -> new ArrayList<>()).add(material);

            library.notifyObservers(EventType.LEND, material);
            System.out.println("Kitap başarıyla ödünç alındı: " + materialTitle);
        } else {
            System.out.println("Kitap mevcut değil veya zaten ödünç alınmış: " + materialTitle);
        }
    }
    public void takeBackMaterial(String materialTitle, MemberRecord borrower, Library library) {
        Map<Long, List<LibraryMaterials>> materialsByMember = library.getMaterialsByMember();
        Map<String, List<LibraryMaterials>> allMaterialsMap = library.getAllMaterialsMap();

        List<LibraryMaterials> borrowedMaterials = materialsByMember.get(borrower.getMemberId());
        if (borrowedMaterials != null) {
            LibraryMaterials materialToReturn = null;
            for (LibraryMaterials material : borrowedMaterials) {
                if (material.getTitle().equals(materialTitle) && material.getCurrentHolder().equals(borrower)) {
                    materialToReturn = material;
                    break;
                }
            }

            if (materialToReturn != null) {
                borrowedMaterials.remove(materialToReturn);
                materialToReturn.setCurrentHolder(null);
                allMaterialsMap.computeIfAbsent(materialTitle, k -> new ArrayList<>()).add(materialToReturn);

                library.notifyObservers(EventType.RETURN, materialToReturn);
            } else {
                System.out.println("This material was not borrowed by " + borrower.getMemberName());
            }
        } else {
            System.out.println("Material not found in borrowed materials.");
        }
    }

    public void updateMaterialInfo(String materialTitle, String newTitle, double newPrice, String newStatus, String newEdition, Library library) {
        LibraryMaterials materialToUpdate = findMaterialByTitle(materialTitle, library);

        if (materialToUpdate != null) {
            if (newTitle != null && !newTitle.isEmpty()) {
                materialToUpdate.setTitle(newTitle);
            }
            if (newPrice > 0) {
                materialToUpdate.setPrice(newPrice);
            }
            if (newStatus != null && !newStatus.isEmpty()) {
                materialToUpdate.setStatus(newStatus);
            }
            if (newEdition != null && !newEdition.isEmpty()) {
                materialToUpdate.setEdition(newEdition);
            }

            System.out.println("Material information updated successfully.");
        } else {
            System.out.println("Material not found.");
        }
    }

    public void deleteMaterial(String materialTitle, Library library) {
        Map<String, List<LibraryMaterials>> allMaterialsMap = library.getAllMaterialsMap();
        Map<String, List<LibraryMaterials>> materialsByAuthor = library.getMaterialsByAuthor();

        if (allMaterialsMap.containsKey(materialTitle)) {
            LibraryMaterials removedMaterial = allMaterialsMap.get(materialTitle).remove(0);
            if (allMaterialsMap.get(materialTitle).isEmpty()) {
                allMaterialsMap.remove(materialTitle);
            }

            String authorName = removedMaterial.getAuthor();
            List<LibraryMaterials> authorMaterials = materialsByAuthor.get(authorName);
            authorMaterials.remove(removedMaterial);
            if (authorMaterials.isEmpty()) {
                materialsByAuthor.remove(authorName);
            }

            library.notifyObservers(EventType.DELETE, removedMaterial);
            System.out.println("Material titled '" + materialTitle + "' has been deleted from the library.");
        } else {
            System.out.println("Material not found in the library.");
        }
    }

    public LibraryMaterials findMaterialByTitle(String title, Library library) {
        Map<String, List<LibraryMaterials>> allMaterialsMap = library.getAllMaterialsMap();

        for (List<LibraryMaterials> materials : allMaterialsMap.values()) {
            for (LibraryMaterials material : materials) {
                if (material.getTitle().trim().equalsIgnoreCase(title.trim())) {
                    return material;
                }
            }
        }
        return null;
    }

    public void displayMaterialsByMember(long memberId, Library library) {
        Map<Long, List<LibraryMaterials>> materialsByMember = library.getMaterialsByMember();
        List<LibraryMaterials> borrowedMaterials = materialsByMember.get(memberId);
        if (borrowedMaterials != null && !borrowedMaterials.isEmpty()) {
            System.out.println("Üye ID'si: " + memberId + " tarafından ödünç alınan kitaplar:");
            for (LibraryMaterials material : borrowedMaterials) {
                material.displayBasicInfo();
            }
        } else {
            System.out.println("Bu üyenin ödünç aldığı kitap yok.");
        }
    }

    public void displayMaterialsByAuthor(String authorName, Library library) {
        Map<String, List<LibraryMaterials>> materialsByAuthor = library.getMaterialsByAuthor();
        List<LibraryMaterials> authorMaterials = materialsByAuthor.get(authorName);
        if (authorMaterials != null && !authorMaterials.isEmpty()) {
            System.out.println("Yazar: " + authorName + " tarafından yazılan kitaplar:");
            for (LibraryMaterials material : authorMaterials) {
                material.displayBasicInfo();
            }
        } else {
            System.out.println("Bu yazarın kitapları yok.");
        }
    }

    public void displayBorrowedMaterials(Library library) {
        Map<Long, List<LibraryMaterials>> materialsByMember = library.getMaterialsByMember();
        System.out.println("Ödünç Alınan Kitaplar:");
        for (Map.Entry<Long, List<LibraryMaterials>> entry : materialsByMember.entrySet()) {
            for (LibraryMaterials material : entry.getValue()) {
                material.displayBasicInfo();
            }
        }
    }

    public void displayAllMaterials(Library library) {
        Map<String, List<LibraryMaterials>> allMaterialsMap = library.getAllMaterialsMap();
        System.out.println("Tüm Kitaplar:");
        for (Map.Entry<String, List<LibraryMaterials>> entry : allMaterialsMap.entrySet()) {
            for (LibraryMaterials material : entry.getValue()) {
                material.displayBasicInfo();
            }
        }
    }

    public void addMember(MemberRecord member, Library library) {
        Map<Long, MemberRecord> memberMap = library.getMemberMap();
        memberMap.put(member.getMemberId(), member);
        System.out.println("Üye eklendi: " + member.getMemberName());
    }

    public void removeMember(long memberId, Library library) {
        Map<Long, MemberRecord> memberMap = library.getMemberMap();
        MemberRecord removedMember = memberMap.remove(memberId);
        if (removedMember != null) {
            System.out.println("Üye çıkarıldı: " + removedMember.getMemberName());
        } else {
            System.out.println("Üye bulunamadı.");
        }
    }

    public void listMembers(Library library) {
        Map<Long, MemberRecord> memberMap = library.getMemberMap();
        System.out.println("Kayıtlı Üyeler:");
        for (MemberRecord member : memberMap.values()) {
            System.out.println("ID: " + member.getMemberId() + ", İsim: " + member.getMemberName());
        }
    }

    public void createBill(long memberId, double amount) {
        System.out.println("Üye ID'si: " + memberId + " için " + amount + " TL fatura oluşturuldu.");
    }

    @Override
    public String whoYouAre() {
        return "Librarian: " + name;
    }
}
