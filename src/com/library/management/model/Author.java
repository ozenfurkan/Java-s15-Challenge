package com.library.management.model;

import com.library.management.library.EventType;
import com.library.management.observer.LibraryObserver;


public class Author implements LibraryObserver {
    private final String authorName;

    public Author(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorName() {
        return authorName;
    }

    @Override
    public void update(EventType eventType, LibraryMaterials material) {
        if (material.getAuthor().equals(this.authorName)) {
            switch (eventType) {
                case ADD:
                    System.out.println("Author: New material by " + authorName + " added: " + material.getTitle());
                    break;
                case LEND:
                    System.out.println("Author: Material by " + authorName + " has been lent: " + material.getTitle());
                    break;
                case RETURN:
                    System.out.println("Author: Material by " + authorName + " has been returned: " + material.getTitle());
                    break;
                default:
                    System.out.println("Author: Unknown event type.");
                    break;
            }
        }
    }

    @Override
    public String whoYouAre() {
        return "Author: " + getAuthorName();
    }
}
