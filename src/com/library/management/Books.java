package com.library.management;

public class Books extends LibraryMaterials {

    private String ISBN;

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