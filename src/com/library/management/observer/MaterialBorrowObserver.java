package com.library.management.observer;

import com.library.management.model.LibraryMaterials;
import com.library.management.user.MemberRecord;

public interface MaterialBorrowObserver {
    void onMaterialBorrowed(LibraryMaterials material, MemberRecord member);
    void onMaterialReturned(LibraryMaterials material, MemberRecord member);
}
