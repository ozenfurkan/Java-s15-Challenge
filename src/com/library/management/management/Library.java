package com.library.management.management;

import com.library.management.model.LibraryMaterials;
import com.library.management.observer.LibraryObserver;
import com.library.management.observer.LibrarySubject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library implements LibraryObserver, LibrarySubject {
    private final Map<String, List<LibraryMaterials>> allMaterialsMap;
    private final Map<String, List<LibraryMaterials>> borrowedMaterialsMap;
    private final Map<String, List<LibraryMaterials>> materialsByAuthor;
    private final Map<Long, List<LibraryMaterials>> materialsByMember;  // Üyelere göre kitapların tutulduğu harita
    private final List<LibraryObserver> observers;  // Gözlemcileri tutmak için liste

    public Library() {
        this.allMaterialsMap = new HashMap<>();
        this.borrowedMaterialsMap = new HashMap<>();
        this.materialsByAuthor = new HashMap<>();
        this.materialsByMember = new HashMap<>();
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(LibraryObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(LibraryObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Map<String, List<LibraryMaterials>> allMaterials, Map<String, List<LibraryMaterials>> borrowedMaterials) {
        for (LibraryObserver observer : observers) {
            observer.onMaterialsUpdated(allMaterials, borrowedMaterials);
        }
    }

    public Map<String, List<LibraryMaterials>> getAllMaterialsMap() {
        return allMaterialsMap;
    }

    public Map<String, List<LibraryMaterials>> getBorrowedMaterialsMap() {
        return borrowedMaterialsMap;
    }

    public Map<String, List<LibraryMaterials>> getMaterialsByAuthor() {
        return materialsByAuthor;
    }

    public Map<Long, List<LibraryMaterials>> getMaterialsByMember() {
        return materialsByMember;
    }

    public void displayAvailableBooks() {
        System.out.println("Available Books:");
        for (Map.Entry<String, List<LibraryMaterials>> entry : allMaterialsMap.entrySet()) {
            for (LibraryMaterials material : entry.getValue()) {
                System.out.println("  - " + material.getTitle() + " (by " + material.getAuthor() + ")");
            }
        }
    }

    public void displayBorrowedBooks() {
        System.out.println("Borrowed Books:");
        for (Map.Entry<String, List<LibraryMaterials>> entry : borrowedMaterialsMap.entrySet()) {
            for (LibraryMaterials material : entry.getValue()) {
                if (material.getCurrentHolder() != null) {
                    System.out.println("  - " + material.getTitle() + " (by " + material.getAuthor() + "), borrowed by " + material.getCurrentHolder().getMemberName());
                } else {
                    System.out.println("  - " + material.getTitle() + " (by " + material.getAuthor() + "), not currently borrowed.");
                }
            }
        }
    }

    public List<LibraryMaterials> getBooksBorrowedByMember(long memberId) {
        return materialsByMember.getOrDefault(memberId, new ArrayList<>());
    }

    // Yazara göre kitapları arama
    public List<LibraryMaterials> searchBooksByAuthor(String authorName) {
        return materialsByAuthor.getOrDefault(authorName, new ArrayList<>());
    }

    // Üyeye göre ödünç alınan kitapları arama
    public List<LibraryMaterials> searchBooksByMember(long memberId) {
        return materialsByMember.getOrDefault(memberId, new ArrayList<>());
    }

    // LibraryObserver implementasyonları
    @Override
    public void onMaterialsUpdated(Map<String, List<LibraryMaterials>> allMaterials, Map<String, List<LibraryMaterials>> borrowedMaterials) {
        this.allMaterialsMap.clear();
        this.allMaterialsMap.putAll(allMaterials);

        this.borrowedMaterialsMap.clear();
        this.borrowedMaterialsMap.putAll(borrowedMaterials);

        System.out.println("Library: All materials have been updated.");
        notifyObservers(allMaterialsMap, borrowedMaterialsMap);  // Bildirim gönder
    }

    @Override
    public void onBookBorrowed(String title, String currentHolder, LibraryMaterials material) {
        borrowedMaterialsMap.computeIfAbsent(title, k -> new ArrayList<>()).add(material);
        allMaterialsMap.getOrDefault(title, new ArrayList<>()).remove(material);

        materialsByMember.computeIfAbsent(material.getCurrentHolder().getMemberId(), k -> new ArrayList<>()).add(material);

        System.out.println("Library: The book '" + title + "' has been borrowed by " + currentHolder + ".");
        notifyObservers(allMaterialsMap, borrowedMaterialsMap);  // Bildirim gönder
    }

    @Override
    public void onBookReturned(String title, LibraryMaterials material) {
        // currentHolder null olup olmadığını kontrol et
        if (material.getCurrentHolder() != null) {
            borrowedMaterialsMap.getOrDefault(title, new ArrayList<>()).remove(material);
            allMaterialsMap.computeIfAbsent(title, k -> new ArrayList<>()).add(material);

            List<LibraryMaterials> memberBooks = materialsByMember.get(material.getCurrentHolder().getMemberId());
            if (memberBooks != null) {
                memberBooks.remove(material);
                if (memberBooks.isEmpty()) {
                    materialsByMember.remove(material.getCurrentHolder().getMemberId());
                }
            }

            System.out.println("Library: The book '" + title + "' has been returned and is now available.");
            notifyObservers(allMaterialsMap, borrowedMaterialsMap);  // Bildirim gönder
        } else {
            System.out.println("Error: The book does not have a current holder.");
        }
    }

    @Override
    public void onNewBookAdded(String authorName, String title, LibraryMaterials material) {
        allMaterialsMap.computeIfAbsent(title, k -> new ArrayList<>()).add(material);
        materialsByAuthor.computeIfAbsent(authorName, k -> new ArrayList<>()).add(material);

        System.out.println("Library: A new book '" + title + "' by " + authorName + " has been added.");
        notifyObservers(allMaterialsMap, borrowedMaterialsMap);  // Bildirim gönder
    }

    @Override
    public void onBookInfoUpdated(String title, String currentHolder, LibraryMaterials material) {
        for (LibraryMaterials mat : allMaterialsMap.getOrDefault(title, new ArrayList<>())) {
            if (mat.equals(material)) {
                mat.setCurrentHolder(material.getCurrentHolder());
                mat.setBorrowedDate(material.getBorrowedDate());
                break;
            }
        }

        System.out.println("Library: The book '" + title + "' information has been updated.");
        notifyObservers(allMaterialsMap, borrowedMaterialsMap);  // Bildirim gönder
    }
}
