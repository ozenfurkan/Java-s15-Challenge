package com.library.management.model;


import java.time.LocalDate;

public class Journals extends LibraryMaterials {
    private String discipline;

    public Journals(String materialId, Author author, String title, double price,
                    Status status, String edition, String publicationDate, LocalDate dateOfPurchase, String discipline) {
        super(materialId, author, title, price, status, edition, publicationDate, dateOfPurchase);
        this.discipline = discipline;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    @Override
    public void displaySpecificInfo() {
        System.out.println("Discipline: " + discipline);
    }
}
