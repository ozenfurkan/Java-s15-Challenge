package com.library.management;

import java.time.LocalDate;

public class Books extends LibraryMaterials {

    private String ISBN;

    public Books(Author author, String title, String status, double price, String edition,
                 String publicationDate, LocalDate dateOfPurchase, MemberRecord owner, String ISBN) {
        super(author, title, status, price, edition, publicationDate, dateOfPurchase, owner);
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