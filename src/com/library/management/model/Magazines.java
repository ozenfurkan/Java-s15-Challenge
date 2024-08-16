package com.library.management.model;

import com.library.management.management.MemberRecord;

import java.lang.reflect.Member;
import java.time.LocalDate;

public class Magazines extends LibraryMaterials {
    private final int issueNumber;

    public Magazines(Author author, String title, String status, double price,
                     String edition, String publicationDate, LocalDate dateOfPurchase,
                     MemberRecord currentHolder, int issueNumber) {
        super(author, title, status, price, edition, publicationDate, dateOfPurchase, currentHolder);
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
