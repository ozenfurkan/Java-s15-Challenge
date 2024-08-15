package com.library.management;

import java.util.List;
import java.util.Map;

public interface MaterialManagement {
    Map<String, List<LibraryMaterials>> getMaterialsByTitle();
    List<LibraryMaterials> findMaterialsByTitle(String title);
    void addMaterial(LibraryMaterials material);
}