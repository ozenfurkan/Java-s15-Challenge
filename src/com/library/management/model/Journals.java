package com.library.management.model;

import com.library.management.user.MemberRecord;

import java.time.LocalDate;

public class Journals extends LibraryMaterials {
    private String discipline;

    public Journals(Author author, String title, String status,
                    double price, String edition, String publicationDate,
                    LocalDate dateOfPurchase, MemberRecord currentHolder, String discipline) {
        super(author, title, status, price, edition, publicationDate, dateOfPurchase, currentHolder);
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
