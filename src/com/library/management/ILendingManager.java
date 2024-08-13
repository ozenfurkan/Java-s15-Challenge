package com.library.management;

public interface ILendingManager {
    void lendMaterial(String title);
    void returnMaterial(String title);
    void displayBorrowedMaterials();
}
