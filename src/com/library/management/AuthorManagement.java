package com.library.management;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface AuthorManagement {
    Map<String, List<LibraryMaterials>> getMaterialsByAuthor();
    List<LibraryMaterials>findMaterialsByAuthor(String authorName);
     void addMaterialbyAuthor(LibraryMaterials material);
