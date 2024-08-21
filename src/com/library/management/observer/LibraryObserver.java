package com.library.management.observer;

import com.library.management.library.EventType;
import com.library.management.model.LibraryMaterials;

import java.util.List;

public interface LibraryObserver {
    void update(EventType eventType, LibraryMaterials material);
    String whoYouAre();
}
