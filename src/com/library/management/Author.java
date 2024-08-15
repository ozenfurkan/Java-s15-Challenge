package com.library.management;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record Author(MaterialManagement materialManagement, String authorName,
                     Map<String, List<LibraryMaterials>> writtenMaterials) implements AuthorManagement {


    @Override
    public Map<String, List<LibraryMaterials>> getMaterialsByAuthor() {
        return writtenMaterials;
    }

    @Override
    public void addMaterialbyAuthor(LibraryMaterials material) {
        writtenMaterials.computeIfAbsent(material.getTitle(), (k) -> new ArrayList<>()).add(material);
    }

    //public void addMaterial(LibraryMaterials material) {
    //        materialsByTitle.computeIfAbsent(material.getTitle(), (k) -> new ArrayList<>()).add(material);
    //    }

    @Override
    public List<LibraryMaterials> findMaterialsByAuthor(String authorName) {
        return List.of();
    }
}
