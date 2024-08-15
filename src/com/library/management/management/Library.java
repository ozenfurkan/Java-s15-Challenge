package com.library.management.management;

import com.library.management.model.LibraryMaterials;
import com.library.management.user.MemberRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library implements LibraryData {
    private final Map<String, List<LibraryMaterials>> materialsByTitle = new HashMap<>();
    private final Map<String, List<LibraryMaterials>> borrowedMaterialsMap = new HashMap<>();



    @Override
    public void addMaterial(LibraryMaterials material) {
        materialsByTitle.computeIfAbsent(material.getTitle(), (k) -> new ArrayList<>()).add(material);
    }

    @Override
    public void onMaterialAdded(LibraryMaterials material) {
        addMaterial(material);
    }

    @Override
    public boolean borrowMaterial(MemberRecord memberRecord, String title) {
        List<LibraryMaterials> availableMaterials = materialsByTitle.getOrDefault(title, new ArrayList<>());

        if (!availableMaterials.isEmpty()) {
            LibraryMaterials material = availableMaterials.remove(0);
            borrowedMaterialsMap.computeIfAbsent(title, k -> new ArrayList<>()).add(material);
            material.setOwner(memberRecord);
            material.setBorrowedDate(java.time.LocalDate.now());

            System.out.println(memberRecord.getMemberName() + " borrowed: " + material.getTitle());
            onMaterialBorrowed(material, memberRecord);
            return true;
        } else {
            System.out.println("No materials available with the title: " + title);
            return false;
        }
    }

    @Override
    public void returnMaterial(MemberRecord memberRecord, String title) {
        List<LibraryMaterials> borrowedMaterials = borrowedMaterialsMap.get(title);

        if (borrowedMaterials == null || borrowedMaterials.isEmpty()) {
            System.out.println("No borrowed material with this title to return.");
            return;
        }

        LibraryMaterials materialToReturn = borrowedMaterials.remove(0);
        materialsByTitle.computeIfAbsent(title, (k) -> new ArrayList<>()).add(materialToReturn);
        materialToReturn.setOwner(null);
        materialToReturn.setBorrowedDate(null);

        System.out.println("Returned: " + materialToReturn.getTitle() + " by " + materialToReturn.getAuthor().getAuthorName());
        onMaterialReturned(materialToReturn, memberRecord);

        if (borrowedMaterials.isEmpty()) {
            borrowedMaterialsMap.remove(title);
        }
    }

    @Override
    public Map<String, List<LibraryMaterials>> getBorrowedMaterials() {
        return borrowedMaterialsMap;
    }

    @Override
    public void displayAllMaterials() {
        if (materialsByTitle.isEmpty()) {
            System.out.println("There isn't available materials");
        } else {
            for (Map.Entry<String, List<LibraryMaterials>> entry : materialsByTitle.entrySet()) {
                for (LibraryMaterials material : entry.getValue()) {
                    System.out.println(material);
                }
            }
        }
    }

    @Override
    public void displayBorrowedMaterials() {
        if (borrowedMaterialsMap.isEmpty()) {
            System.out.println("There isn't borrowed materials");
        } else {
            for (Map.Entry<String, List<LibraryMaterials>> entry : borrowedMaterialsMap.entrySet()) {
                for (LibraryMaterials material : entry.getValue()) {
                    System.out.println(material);
                }
            }
        }
    }
//bu bir interface'ten alınabilir.!!!!!!!!
    public List<LibraryMaterials> searchMaterialsByAuthor(String authorName) {
        List<LibraryMaterials> result = new ArrayList<>();
        for (List<LibraryMaterials> materialsList : materialsByTitle.values()) {
            for (LibraryMaterials material : materialsList) {
                if (material.getAuthor().getAuthorName().equalsIgnoreCase(authorName)) {
                    result.add(material);
                }
            }
        }
        return result;
    }

    public List<LibraryMaterials> searchMaterialsByMember(String memberName) {
        List<LibraryMaterials> result = new ArrayList<>();
        for (List<LibraryMaterials> materialsList : borrowedMaterialsMap.values()) {
            for (LibraryMaterials material : materialsList) {
                if (material.getOwner() != null && material.getOwner().getMemberName().equalsIgnoreCase(memberName)) {
                    result.add(material);
                }
            }
        }
        return result;
    }

    @Override
    public void onMaterialBorrowed(LibraryMaterials material, MemberRecord member) {
        System.out.println("Observer: " + member.getMemberName() + " borrowed the material: " + material.getTitle());
        // Gözlemcinin ödünç alma ile ilgili yapacağı işlemler burada tanımlanabilir
    }

    @Override
    public void onMaterialReturned(LibraryMaterials material, MemberRecord member) {
        System.out.println("Observer: " + member.getMemberName() + " returned the material: " + material.getTitle());
        // Gözlemcinin iade ile ilgili yapacağı işlemler burada tanımlanabilir
    }
}
