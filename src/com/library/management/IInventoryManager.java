package com.library.management;

import java.util.List;
import java.util.Map;

public interface IInventoryManager {
    void addNewMaterial(LibraryMaterials material);
    Map<String, List<LibraryMaterials>> getMaterialsMap();
    void displayAllMaterials();
}
