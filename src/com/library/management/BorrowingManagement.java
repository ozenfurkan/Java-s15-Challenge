package com.library.management;

import java.util.List;
import java.util.Map;

public  interface BorrowingManagement {
    boolean borrowMaterial(MemberRecord memberRecord, String title);
    void returnMaterial(MemberRecord memberRecord, String title);
    Map<String, List<LibraryMaterials>> getBorrowedMaterials();
}