package com.library.management.observer;

import com.library.management.library.DisplayOptions;
import com.library.management.library.EventType;
import com.library.management.library.Library;
import com.library.management.model.LibraryMaterials;

import java.util.List;

public interface LibraryObserver {
    void update(EventType eventType, LibraryMaterials material, Library library, DisplayOptions displayOptions);
    String whoYouAre();
}
