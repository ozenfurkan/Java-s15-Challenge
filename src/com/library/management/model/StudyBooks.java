package com.library.management.model;


import java.time.LocalDate;

public class StudyBooks extends LibraryMaterials {

    private final String subject;
    private final String ISBN;

    public StudyBooks(String materialId, Author author, String title, double price, Status status,
                      String edition, String publicationDate, LocalDate dateOfPurchase, String subject, String ISBN) {
        super(materialId, author, title, price, status, edition, publicationDate, dateOfPurchase);
        this.subject = subject;
        this.ISBN = ISBN;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public void displaySpecificInfo() {
        System.out.println("ISBN: " + ISBN);
        System.out.println("Subject: " + subject);
    }

    @Override
    public String toString() {
        return "StudyBooks{" +
                "subject='" + subject + '\'' +
                ", ISBN='" + ISBN + '\'' +
                "} " + super.toString();
    }
}
