package com.library.management;

public class Magazines extends LibraryMaterials {

    private int issueNumber;

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
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
