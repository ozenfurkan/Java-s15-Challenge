
package com.library.management;

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

    @Override
    public String toString() {
        return "Magazines{" +
                "issueNumber=" + issueNumber +
                "} " + super.toString();
    }
}
