package com.library.management.model;


import java.time.LocalDate;

public class Magazines extends LibraryMaterials {
    private final int issueNumber;

    public Magazines(String materialId, Author author, String title, double price,
                     Status status, String edition, String publicationDate, LocalDate dateOfPurchase, int issueNumber) {
        super(materialId, author, title, price, status, edition, publicationDate, dateOfPurchase);
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    @Override
    public void displaySpecificInfo() {
        System.out.println("Issue Number: " + issueNumber);
    }
}
