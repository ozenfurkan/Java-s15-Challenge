package com.library.management;

import java.time.LocalDate;

public abstract class LibraryMaterials {

    private String author;
    private String title;
    private double price;
    private String status;
    private String edition;
    private String publicationDate;
    private LocalDate dateOfPurchase;

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        if (author == null) {
            author = "Collective";
        }
        return author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public abstract void displaySpecificInfo();

    @Override
    public String toString() {
        return "LibraryMaterials{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", edition='" + edition + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", dateOfPurchase=" + dateOfPurchase +
                '}';
    }
}
