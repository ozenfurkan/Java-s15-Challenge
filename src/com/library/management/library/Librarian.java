package com.library.management.library;
import java.util.Objects;

import com.library.management.model.Bill;
import com.library.management.model.BillStatus;
import com.library.management.model.LibraryMaterials;
import com.library.management.model.Status;
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
    public void update(EventType eventType, LibraryMaterials material, Library library, DisplayOptions displayOption) {
        switch (eventType) {
            case DISPLAY:
                switch (displayOption) {
                    case ALL:
                        System.out.println("Displaying all materials:");
                        library.getAllMaterialsMap().values().stream()
                                .flatMap(List::stream)
                                .forEach(LibraryMaterials::displayBasicInfo);
                        break;
                    case BORROWED:
                        System.out.println("Displaying borrowed materials:");
                        library.getBorrowedMaterialsMap().values().stream()
                                .flatMap(List::stream)
                                .forEach(LibraryMaterials::displayBasicInfo);
                        break;
                    case AUTHOR:
                        System.out.println("Displaying materials by author:");
                        library.getMaterialsByAuthor().forEach((author, materials) -> {
                            System.out.println("Author: " + author);
                            materials.forEach(LibraryMaterials::displayBasicInfo);
                        });
                        break;
                    case MEMBER:
                        System.out.println("Displaying materials by member:");
                        library.getMaterialsByMember().forEach((memberId, materials) -> {
                            System.out.println("Member ID: " + memberId);
                            materials.forEach(LibraryMaterials::displayBasicInfo);
                        });
                        break;
                    default:
                        System.out.println("Invalid display option.");
                        break;
                }
                break;

            case ADD:
                library.getAllMaterialsMap().computeIfAbsent(material.getTitle(), k -> new ArrayList<>()).add(material);
                library.getMaterialsByAuthor().computeIfAbsent(material.getAuthorName(), k -> new ArrayList<>()).add(material);
                System.out.println("Material added: " + material.getTitle());
                break;

            case LEND:
                if (material.getCurrentHolder() != null) {
                    library.getBorrowedMaterialsMap().computeIfAbsent(material.getTitle(), k -> new ArrayList<>()).add(material);
                    library.getMaterialsByMember().computeIfAbsent(material.getCurrentHolder().getMemberId(), k -> new ArrayList<>()).add(material);

                    library.getAllMaterialsMap().values().stream()
                            .flatMap(List::stream)
                            .filter(m -> m.getMaterialId().equals(material.getMaterialId()))
                            .forEach(m -> m.setStatus(Status.BORROWED));

                    library.getMaterialsByAuthor().values().stream()
                            .flatMap(List::stream)
                            .filter(m -> m.getMaterialId().equals(material.getMaterialId()))
                            .forEach(m -> m.setStatus(Status.BORROWED));

                    System.out.println("Material lent: " + material.getTitle() + " to " + material.getCurrentHolder().getMemberName());
                } else {
                    System.out.println("Material cannot be lent: No current holder.");
                }
                break;

            case RETURN:
                library.getAllMaterialsMap().values().stream()
                        .flatMap(List::stream)
                        .filter(m -> m.getMaterialId().equals(material.getMaterialId()))
                        .forEach(m -> m.setStatus(Status.AVAILABLE));

                library.getBorrowedMaterialsMap().getOrDefault(material.getTitle(), new ArrayList<>()).removeIf(m -> m.getMaterialId().equals(material.getMaterialId()));
                if (material.getCurrentHolder() != null) {
                    library.getMaterialsByMember().getOrDefault(material.getCurrentHolder().getMemberId(), new ArrayList<>()).removeIf(m -> m.getMaterialId().equals(material.getMaterialId()));
                }
                System.out.println("Material returned: " + material.getTitle());
                break;

            case DELETE:
                library.getAllMaterialsMap().getOrDefault(material.getTitle(), new ArrayList<>()).removeIf(m -> m.getMaterialId().equals(material.getMaterialId()));
                library.getMaterialsByAuthor().getOrDefault(material.getAuthorName(), new ArrayList<>()).removeIf(m -> m.getMaterialId().equals(material.getMaterialId()));
                library.getBorrowedMaterialsMap().getOrDefault(material.getTitle(), new ArrayList<>()).removeIf(m -> m.getMaterialId().equals(material.getMaterialId()));
                if (material.getCurrentHolder() != null) {
                    library.getMaterialsByMember().getOrDefault(material.getCurrentHolder().getMemberId(), new ArrayList<>()).removeIf(m -> m.getMaterialId().equals(material.getMaterialId()));
                }
                System.out.println("Material deleted: " + material.getTitle());
                break;

            case UPDATE:
                library.getAllMaterialsMap().values().stream()
                        .flatMap(List::stream)
                        .filter(m -> m.getMaterialId().equals(material.getMaterialId()))
                        .findFirst()
                        .ifPresent(existingMaterial -> {
                            existingMaterial.setTitle(material.getTitle());
                            existingMaterial.setAuthor(material.getAuthor());
                            existingMaterial.setPrice(material.getPrice());
                            existingMaterial.setStatus(material.getStatus());
                            existingMaterial.setEdition(material.getEdition());
                            existingMaterial.setPublicationDate(material.getPublicationDate());
                            System.out.println("Material updated: " + material.getTitle());
                        });

                library.getMaterialsByAuthor().values().stream()
                        .flatMap(List::stream)
                        .filter(m -> m.getMaterialId().equals(material.getMaterialId()))
                        .forEach(existingMaterial -> {
                            existingMaterial.setTitle(material.getTitle());
                            existingMaterial.setAuthor(material.getAuthor());
                            existingMaterial.setPrice(material.getPrice());
                            existingMaterial.setStatus(material.getStatus());
                            existingMaterial.setEdition(material.getEdition());
                            existingMaterial.setPublicationDate(material.getPublicationDate());
                        });
                break;

            default:
                System.out.println("Librarian: Unknown event type.");
                break;
        }
    }

    public void createBill(long memberId, LibraryMaterials materials, BillStatus status, Library library) {
        Bill bill = new Bill(library.getMemberMap().get(memberId), materials, status);
        library.getBillMap().put(memberId, bill);
        System.out.println("Üye ID'si: " + memberId + " için " + materials.getPrice() + " TL fatura oluşturuldu.");
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

    @Override
    public String whoYouAre() {
        return "Librarian: " + name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Librarian librarian = (Librarian) o;
        return Objects.equals(name, librarian.name) &&
                Objects.equals(password, librarian.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "name='" + name + '\'' +
                ", password='[PROTECTED]'" +  // Şifreyi gizlemek için.
                '}';
    }
}
