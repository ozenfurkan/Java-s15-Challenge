package com.library.management;

import com.library.management.Books;
import com.library.management.LibraryMaterials;

import java.time.LocalDate;

public  class StudyBooks extends LibraryMaterials {

    private final String subject;
    private final String ISBN;

    public String getISBN() {
        return ISBN;
    }

    public StudyBooks(Author author, String title, String status, double price, String edition,
                      String publicationDate, LocalDate dateOfPurchase, MemberRecord owner,
                      String subject, String ISBN) {
        super(author, title, status, price, edition, publicationDate, dateOfPurchase, owner);
        this.subject = subject;
        this.ISBN = ISBN;
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
                "} " + super.toString();
    }


}