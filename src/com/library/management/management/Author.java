package com.library.management.management;

import com.library.management.observer.MaterialObserver;
import com.library.management.model.LibraryMaterials;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Author {
    private final String authorName;
    private final Map<String, List<LibraryMaterials>> writtenMaterials = new HashMap<>();
    private MaterialObserver materialObserver;

    public Author(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setMaterialObserver(MaterialObserver materialObserver) {
        this.materialObserver = materialObserver;
    }

    public void addMaterialbyAuthor(LibraryMaterials material) {
        writtenMaterials.computeIfAbsent(material.getTitle(), k -> new ArrayList<>()).add(material);

        if (materialObserver != null) {
            materialObserver.onMaterialAdded(material);
        } else {
            System.out.println("No MaterialObserver set for author " + authorName);
        }
    }

    public Map<String, List<LibraryMaterials>> getMaterialsByAuthor() {
        return writtenMaterials;
    }

    public List<LibraryMaterials> findMaterialsByAuthor(String authorName) {
        return writtenMaterials.getOrDefault(authorName, new ArrayList<>());
    }
}
