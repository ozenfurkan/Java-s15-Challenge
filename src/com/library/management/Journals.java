package com.library.management;

import java.time.LocalDate;

public class Journals extends LibraryMaterials {
    public Journals(Author author, String title, String status, double price, String edition,
                    String publicationDate, LocalDate dateOfPurchase, MemberRecord owner, String discipline) {
        super(author, title, status, price, edition, publicationDate, dateOfPurchase, owner);
        this.discipline = discipline;
    }

    private String discipline;

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

    @Override
    public String toString() {
        return "Journals{" +
                "discipline='" + discipline + '\'' +
                "} " + super.toString();
    }
}
