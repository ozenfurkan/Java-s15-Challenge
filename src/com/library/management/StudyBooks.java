package com.library.management;

public class StudyBooks extends LibraryMaterials {

    private String subject;
    private String ISBN;

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public void displaySpecificInfo() {
        System.out.println("ISBN: " + ISBN);
        System.out.println("Subject: " + subject);
    }

    @Override
    public String toString() {
        return "StudyBooks{" +
                "subject='" + subject + '\'' +
                "} " + super.toString();
    }
}
