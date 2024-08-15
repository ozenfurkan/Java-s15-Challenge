package com.library.management.management;

import com.library.management.model.LibraryMaterials;

import java.util.List;
import java.util.Map;

public interface AuthorManagement {
    Map<String, List<LibraryMaterials>> getMaterialsByAuthor();
    List<LibraryMaterials> findMaterialsByAuthor(String authorName);
    void addMaterialbyAuthor(LibraryMaterials material);
}
