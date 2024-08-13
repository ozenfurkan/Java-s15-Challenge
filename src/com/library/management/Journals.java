package com.library.management;

public class Journals extends LibraryMaterials {

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
