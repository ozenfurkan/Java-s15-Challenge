package com.library.management.observer;

import com.library.management.model.LibraryMaterials;
import com.library.management.user.MemberRecord;

public interface MaterialObserver {
    void onMaterialAdded(LibraryMaterials material);
    void onMaterialBorrowed(LibraryMaterials material, MemberRecord member);
    void onMaterialReturned(LibraryMaterials material, MemberRecord member);
}