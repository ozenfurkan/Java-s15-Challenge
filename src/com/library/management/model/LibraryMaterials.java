package com.library.management.model;

import com.library.management.management.Author;
import com.library.management.user.MemberRecord;

import java.time.LocalDate;

public abstract class LibraryMaterials {

    private final Author author;
    private final String title;
    private final double price;
    private final String status;
    private final String edition;
    private final String publicationDate;
    private final LocalDate dateOfPurchase;
    private MemberRecord owner;
    private LocalDate borrowedDate;

    public LibraryMaterials(Author author, String title, String status,
                            double price, String edition, String publicationDate,
                            LocalDate dateOfPurchase, MemberRecord owner) {
        this.author = author;
        this.title = title;
        this.status = status;
        this.price = price;
        this.edition = edition;
        this.publicationDate = publicationDate;
        this.dateOfPurchase = dateOfPurchase;
        this.owner = owner;
    }

    public Author getAuthor() {
        return author;
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

    public MemberRecord getOwner() {
        return owner;
    }

    public void setOwner(MemberRecord owner) {
        this.owner = owner;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public abstract void displaySpecificInfo();

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
                ", owner='" + (owner != null ? owner.getMemberName() : "None") + '\'' +
                ", borrowedDate='" + (borrowedDate != null ? borrowedDate : "Not Borrowed") + '\'' +
                '}';
    }
}
