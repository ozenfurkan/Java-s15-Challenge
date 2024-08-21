package com.library.management.observer;

import com.library.management.library.EventType;
import com.library.management.model.LibraryMaterials;

import com.library.management.model.LibraryMaterials;

public interface LibrarySubject {
    void registerObserver(LibraryObserver observer);
    void removeObserver(LibraryObserver observer);
    void notifyObservers(EventType eventType, LibraryMaterials material);
}