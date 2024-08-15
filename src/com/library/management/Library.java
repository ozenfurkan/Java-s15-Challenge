package com.library.management;

import java.util.*;


public class Library implements LibraryData {
    private final Map<String, List<LibraryMaterials>> materialsByTitle;

    private final Map<String, List<LibraryMaterials>> borrowedMaterialsMap;

    public Library(){
        materialsByTitle = new HashMap<>();

        borrowedMaterialsMap = new HashMap<>();
    }

    @Override
    public Map<String, List<LibraryMaterials>> getBorrowedMaterials() {
        return borrowedMaterialsMap;
    }

    @Override
    public Map<String, List<LibraryMaterials>> getMaterialsByTitle() {
        return materialsByTitle;
    }


    @Override
    public List<LibraryMaterials> findMaterialsByTitle(String title) {
        return materialsByTitle.getOrDefault(title, Collections.emptyList());
    }


    @Override
    public void addMaterial(LibraryMaterials material) {
        materialsByTitle.computeIfAbsent(material.getTitle(), (k) -> new ArrayList<>()).add(material);
    }

    @Override
    public boolean borrowMaterial(MemberRecord memberRecord, String title) {
        List<LibraryMaterials> availableMaterials = materialsByTitle.getOrDefault(title, Collections.emptyList());

        if (!availableMaterials.isEmpty()) {
            LibraryMaterials material = availableMaterials.remove(0);

            borrowedMaterialsMap.computeIfAbsent(title, (k) -> new ArrayList<>()).add(material);

            System.out.println(memberRecord.getMemberName() + " borrowed: " + material.getTitle());
            return true;
        } else {
            System.out.println("No materials available with the title: " + title);
            return false;
        }
    }

    @Override
    public void returnMaterial(MemberRecord memberRecord, String title) {

    }

}
