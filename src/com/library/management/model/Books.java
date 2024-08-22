package com.library.management.model;

import com.library.management.user.MemberRecord;

import java.time.LocalDate;

public class Books extends LibraryMaterials {
    private String ISBN;

    public Books(String materialId, Author author, String title, double price, Status status,
                 String edition, String publicationDate, LocalDate dateOfPurchase, String ISBN) {
        super(materialId, author, title, price, status, edition, publicationDate, dateOfPurchase);
        this.ISBN = ISBN;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public void displaySpecificInfo() {
        System.out.println("ISBN: " + ISBN);
    }
}
