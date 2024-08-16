package com.library.management.management;

import com.library.management.model.LibraryMaterials;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Librarian {
    private final String name;
    private final String password;
    private final Library library;

    public Librarian(String name, String password, Library library) {
        this.name = name;
        this.password = password;
        this.library = library;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void addNewMaterial(LibraryMaterials material) {
        Map<String, List<LibraryMaterials>> allMaterialsMap = library.getAllMaterialsMap();
        Map<String, List<LibraryMaterials>> materialsByAuthor = library.getMaterialsByAuthor();

        allMaterialsMap.computeIfAbsent(material.getTitle(), k -> new ArrayList<>()).add(material);
        materialsByAuthor.computeIfAbsent(material.getAuthor(), k -> new ArrayList<>()).add(material);

        library.onNewBookAdded(material.getAuthor(), material.getTitle(), material);
    }

    public void lendMaterial(String materialTitle, MemberRecord borrower) {
        Map<String, List<LibraryMaterials>> allMaterialsMap = library.getAllMaterialsMap();
        Map<String, List<LibraryMaterials>> borrowedMaterialsMap = library.getBorrowedMaterialsMap();

        List<LibraryMaterials> availableMaterials = allMaterialsMap.get(materialTitle);
        if (availableMaterials != null && !availableMaterials.isEmpty()) {
            LibraryMaterials material = availableMaterials.remove(0); // İlk müsait materyali seçer
            material.setCurrentHolder(borrower);
            material.setBorrowedDate(LocalDate.now());

            borrowedMaterialsMap.computeIfAbsent(materialTitle, k -> new ArrayList<>()).add(material);

            library.onBookBorrowed(materialTitle, borrower.getMemberName(), material);
        } else {
            System.out.println("Material not found or already borrowed.");
        }
    }

    public void takeBackBook(String materialTitle, MemberRecord borrower) {
        Map<String, List<LibraryMaterials>> borrowedMaterialsMap = library.getBorrowedMaterialsMap();
        Map<String, List<LibraryMaterials>> allMaterialsMap = library.getAllMaterialsMap();

        List<LibraryMaterials> borrowedMaterials = borrowedMaterialsMap.get(materialTitle);
        if (borrowedMaterials != null) {
            LibraryMaterials materialToReturn = null;
            for (LibraryMaterials material : borrowedMaterials) {
                if (material.getCurrentHolder() != null && material.getCurrentHolder().equals(borrower)) {
                    materialToReturn = material;
                    break;
                }
            }

            if (materialToReturn != null) {
                borrowedMaterials.remove(materialToReturn);
                materialToReturn.setCurrentHolder(null);
                materialToReturn.setBorrowedDate(null);
                allMaterialsMap.computeIfAbsent(materialTitle, k -> new ArrayList<>()).add(materialToReturn);

                library.onBookReturned(materialTitle, materialToReturn);
            } else {
                System.out.println("This material was not borrowed by " + borrower.getMemberName());
            }
        } else {
            System.out.println("Material not found in borrowed materials.");
        }
    }
}
