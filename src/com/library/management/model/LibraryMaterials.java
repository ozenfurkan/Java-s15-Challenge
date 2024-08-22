package com.library.management.model;

import com.library.management.user.MemberRecord;

import java.time.LocalDate;
import java.util.Objects;


public class LibraryMaterials {
    private final String materialId;
    private Author author;
    private String title;
    private double price;
    private Status status;
    private String edition;
    private String publicationDate;
    private LocalDate dateOfPurchase;
    private MemberRecord currentHolder;
    private LocalDate borrowedDate;

    public LibraryMaterials(String materialId, Author author, String title, double price, Status status,
                            String edition, String publicationDate, LocalDate dateOfPurchase) {
        this.materialId = materialId;
        this.author = author;
        this.title = title;
        this.price = price;
        this.status = status;
        this.edition = edition;
        this.publicationDate = publicationDate;
        this.dateOfPurchase = dateOfPurchase;
    }

    public String getMaterialId() {
        return materialId;
    }

    public String getAuthorName() {
        return author.getAuthorName();
    }
    public Author getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setStatus(Status status) {
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

    public Status getStatus() {
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
        if (currentHolder != null) {
            System.out.println("Current Holder: " + currentHolder.getMemberName());
            System.out.println("Borrowed Date: " + borrowedDate);
        } else {
            System.out.println("Current Holder: None");
        }
    }

    public void displayBasicInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author.getAuthorName());
        System.out.println("Edition: " + edition);
        System.out.println("Status: " + status);
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
                ", currentHolder='" + (currentHolder != null ? currentHolder.getMemberName() : "None") + '\'' +
                ", borrowedDate='" + (borrowedDate != null ? borrowedDate : "Not Borrowed") + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryMaterials that = (LibraryMaterials) o;
        return Objects.equals(materialId, that.materialId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(materialId);
    }
}

