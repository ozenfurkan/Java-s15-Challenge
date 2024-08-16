package com.library.management.model;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Author {
    private String authorName;
    private final Map<String, List<LibraryMaterials>> materialsByAuthor; // Her bir yazarın kitaplarını tutan harita

    public Author(String authorName) {
        this.authorName = authorName;
        this.materialsByAuthor = new HashMap<>();
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Map<String, List<LibraryMaterials>> getMaterialsByAuthor() {
        return materialsByAuthor;
    }

    public void addMaterial(LibraryMaterials material) {
        materialsByAuthor.computeIfAbsent(authorName, k -> new ArrayList<>()).add(material);
    }

    public List<LibraryMaterials> getMaterials() {
        return materialsByAuthor.getOrDefault(authorName, new ArrayList<>());
    }
}
