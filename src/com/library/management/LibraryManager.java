package com.library.management;

import java.util.*;

public class LibraryManager implements IInventoryManager, ILendingManager {
    private final Map<String, List<LibraryMaterials>> materialsMap;
    private final Map<String, List<LibraryMaterials>> borrowedMaterialsMap;

    public LibraryManager() {
        materialsMap = new HashMap<>();
        borrowedMaterialsMap = new HashMap<>();
    }

    @Override
    public void addNewMaterial(LibraryMaterials material) {
        materialsMap.computeIfAbsent(material.getTitle(), k -> new ArrayList<>()).add(material);
    }

    @Override
    public Map<String, List<LibraryMaterials>> getMaterialsMap() {
        return materialsMap;
    }

    @Override
    public void displayAllMaterials() {
        if (materialsMap.isEmpty()) {
            System.out.println("There isn't available materials");
        } else {
            for (Map.Entry<String, List<LibraryMaterials>> availableMaterials : materialsMap.entrySet()) {
                System.out.println("Available material title: " + availableMaterials.getKey() +
                        " Other info " + availableMaterials.getValue());
            }
        }
    }

    @Override
    public void lendMaterial(String title) {
        List<LibraryMaterials> materials = materialsMap.get(title);
        if (materials != null && !materials.isEmpty()) {
            LibraryMaterials materialToLend = materials.remove(0);
            borrowedMaterialsMap.computeIfAbsent(title, k -> new ArrayList<>()).add(materialToLend);
            System.out.println("Lended: " + materialToLend.getTitle() + " by " + materialToLend.getAuthor());

            if (materials.isEmpty()) {
                materialsMap.remove(title);
            }
        } else {
            System.out.println("Material not available or already lended.");
        }
    }

    @Override
    public void returnMaterial(String title) {
        List<LibraryMaterials> borrowedMaterials = borrowedMaterialsMap.get(title);

        if (borrowedMaterials == null || borrowedMaterials.isEmpty()) {
            System.out.println("No borrowed material with this title to return.");
            return;
        }

        LibraryMaterials materialsToReturn = borrowedMaterials.remove(0);
        materialsMap.computeIfAbsent(title, k -> new ArrayList<>()).add(materialsToReturn);
        System.out.println("Returned: " + materialsToReturn.getTitle() + " by " + materialsToReturn.getAuthor());

        if (borrowedMaterials.isEmpty()) {
            borrowedMaterialsMap.remove(title);
        }
    }

    @Override
    public void displayBorrowedMaterials() {
        if (borrowedMaterialsMap.isEmpty()) {
            System.out.println("There isn't borrowed materials");
        } else {
            for (Map.Entry<String, List<LibraryMaterials>> borrowedMaterials : borrowedMaterialsMap.entrySet()) {
                System.out.println("Borrowed material title: " + borrowedMaterials.getKey() +
                        " Other info " + borrowedMaterials.getValue());
            }
        }
    }
}
