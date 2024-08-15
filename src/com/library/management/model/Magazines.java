package com.library.management.model;

import com.library.management.management.Author;
import com.library.management.user.MemberRecord;

import java.time.LocalDate;

public class Magazines extends LibraryMaterials {
    private final int issueNumber;

    public Magazines(Author author, String title, String status, double price,
                     String edition, String publicationDate, LocalDate dateOfPurchase,
                     MemberRecord owner, int issueNumber) {
        super(author, title, status, price, edition, publicationDate, dateOfPurchase, owner);
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
