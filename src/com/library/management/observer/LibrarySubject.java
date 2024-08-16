package com.library.management.observer;

import com.library.management.model.LibraryMaterials;

import java.util.List;
import java.util.Map;

public interface LibrarySubject {
    void registerObserver(LibraryObserver observer);
    void removeObserver(LibraryObserver observer);
    void notifyObservers(Map<String, List<LibraryMaterials>> allMaterials, Map<String, List<LibraryMaterials>> borrowedMaterials);
}
