package com.library.management.library;

import com.library.management.model.Bill;
import com.library.management.model.LibraryMaterials;
import com.library.management.observer.LibraryObserver;
import com.library.management.observer.LibrarySubject;
import com.library.management.user.MemberRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library implements LibrarySubject {
    private final Map<String, List<LibraryMaterials>> allMaterialsMap;
    private final Map<String, List<LibraryMaterials>> borrowedMaterialsMap;
    private final Map<String, List<LibraryMaterials>> materialsByAuthor;
    private final Map<Long, List<LibraryMaterials>> materialsByMember;
    private final List<LibraryObserver> observers;
    private final Map<Long, MemberRecord> memberMap;
    private final Map<Long, Bill> billMap;

    public Library() {
        this.allMaterialsMap = new HashMap<>();
        this.borrowedMaterialsMap = new HashMap<>();
        this.materialsByAuthor = new HashMap<>();
        this.materialsByMember = new HashMap<>();
        this.billMap = new HashMap<>();
        this.memberMap = new HashMap<>();
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
    public void notifyObservers(EventType eventType, LibraryMaterials material, Library library, DisplayOptions displayOptions) {
        for (LibraryObserver observer : observers) {
            observer.update(eventType, material, library, displayOptions);
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

    public Map<Long, MemberRecord> getMemberMap() {
        return memberMap;
    }

    public List<LibraryObserver> getObservers() {
        return observers;
    }

    public Map<Long, Bill> getBillMap() {
        return billMap;
    }
}
