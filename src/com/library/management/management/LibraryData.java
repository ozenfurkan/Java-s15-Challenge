package com.library.management.management;

import com.library.management.observer.MaterialBorrowObserver;
import com.library.management.observer.MaterialObserver;
import com.library.management.model.LibraryMaterials;

public interface LibraryData extends MaterialObserver, BorrowingManagement, MaterialBorrowObserver {
    void addMaterial(LibraryMaterials material);
    void displayAllMaterials();
    void displayBorrowedMaterials();
}
