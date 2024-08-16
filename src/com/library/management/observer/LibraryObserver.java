package com.library.management.observer;

import com.library.management.model.LibraryMaterials;
import java.util.List;
import java.util.Map;

public interface LibraryObserver {
    void onMaterialsUpdated(Map<String, List<LibraryMaterials>> allMaterials, Map<String, List<LibraryMaterials>> borrowedMaterials);
    void onBookBorrowed(String title, String currentHolder, LibraryMaterials material);
    void onBookReturned(String title, LibraryMaterials material);
    void onNewBookAdded(String authorName, String title, LibraryMaterials material);
    void onBookInfoUpdated(String title, String currentHolder, LibraryMaterials material);
}
