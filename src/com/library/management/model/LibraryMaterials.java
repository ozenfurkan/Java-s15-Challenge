package com.library.management.model;

import com.library.management.user.MemberRecord;

import java.time.LocalDate;

public abstract class LibraryMaterials {

    private  Author author;
    private  String title;
    private  double price;
    private  String status;
    private  String edition;
    private  String publicationDate;
    private  LocalDate dateOfPurchase;
    private MemberRecord currentHolder;
    private LocalDate borrowedDate;

    public LibraryMaterials(Author author, String title, String status,
                            double price, String edition, String publicationDate,
                            LocalDate dateOfPurchase, MemberRecord currentHolder) {
        this.author = author;
        this.title = title;
        this.status = status;
        this.price = price;
        this.edition = edition;
        this.publicationDate = publicationDate;
        this.dateOfPurchase = dateOfPurchase;
        this.currentHolder = currentHolder;
    }

    public String getAuthor() {
        return author.getAuthorName();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public String getEdition() {
        return edition;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public MemberRecord getCurrentHolder() {
        return currentHolder;
    }

    public void setCurrentHolder(MemberRecord currentHolder) {
        this.currentHolder = currentHolder;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }
    public void displaySpecificInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author.getAuthorName());
        System.out.println("Price: $" + price);
        System.out.println("Status: " + status);
        System.out.println("Edition: " + edition);
        System.out.println("Publication Date: " + publicationDate);
        System.out.println("Date of Purchase: " + dateOfPurchase);
        System.out.println("Current Holder: " + (currentHolder != null ? currentHolder.getMemberName() : "None"));
        System.out.println("Borrowed Date: " + (borrowedDate != null ? borrowedDate : "Not Borrowed"));
    }
    public void displayBasicInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author.getAuthorName());
        System.out.println("Edition: " + edition);
        System.out.println("Status: " + status);
        System.out.println("Available Copies: " + (currentHolder == null ? 1 : 0));
        if (currentHolder != null) {
            System.out.println("Borrowed by: " + currentHolder.getMemberName());
            System.out.println("Borrowed Date: " + borrowedDate);
        }
    }

    @Override
    public String toString() {
        return "LibraryMaterials{" +
                "author='" + author.getAuthorName() + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", edition='" + edition + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", dateOfPurchase=" + dateOfPurchase +
                ", owner='" + (currentHolder != null ? currentHolder.getMemberName() : "None") + '\'' +
                ", borrowedDate='" + (borrowedDate != null ? borrowedDate : "Not Borrowed") + '\'' +
                '}';
    }
}
