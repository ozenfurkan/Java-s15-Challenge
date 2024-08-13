package com.library.management;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
    //Author bazen null geliyor!!!! İskeletin ardından ilgilenilmeli!!
        LibraryManager libraryManager = new LibraryManager();

        Journals journal = new Journals();
        journal.setTitle("Journal of Computer Science");
        journal.setAuthor("Dr. John Doe");
        journal.setPrice(99.99);
        journal.setStatus("Available");
        journal.setEdition("1st");
        journal.setPublicationDate("2024");
        journal.setDateOfPurchase(LocalDate.now());
        journal.setDiscipline("Computer Science");

        Magazines magazine = new Magazines();
        magazine.setTitle("Tech Today");
        magazine.setPrice(5.99);
        magazine.setStatus("Available");
        magazine.setEdition("February 2024");
        magazine.setPublicationDate("2024");
        magazine.setDateOfPurchase(LocalDate.now());
        magazine.setIssueNumber(42);

        StudyBooks studyBook = new StudyBooks();
        studyBook.setTitle("Data Structures and Algorithms");
        studyBook.setAuthor("Prof. Alice Johnson");
        studyBook.setPrice(49.99);
        studyBook.setStatus("Available");
        studyBook.setEdition("3rd");
        studyBook.setPublicationDate("2023");
        studyBook.setDateOfPurchase(LocalDate.now());
        studyBook.setSubject("Computer Science");

        libraryManager.addNewMaterial(journal);
        libraryManager.addNewMaterial(magazine);
        libraryManager.addNewMaterial(studyBook);

        System.out.println("Initial Library Materials:");
        libraryManager.displayAllMaterials();

        System.out.println("\nLending 'Journal of Computer Science':");
        libraryManager.lendMaterial("Journal of Computer Science");

        System.out.println("\nLending 'Tech Today':");
        libraryManager.lendMaterial("Tech Today");

        System.out.println("\nLending 'Data Structures and Algorithms':");
        libraryManager.lendMaterial("Data Structures and Algorithms");

        System.out.println("\nLibrary Materials After Lending:");
        libraryManager.displayAllMaterials();

        System.out.println("\nReturning 'Journal of Computer Science':");
        libraryManager.returnMaterial("Journal of Computer Science");

        System.out.println("\nReturning 'Tech Today':");
        libraryManager.returnMaterial("Tech Today");

        System.out.println("\nReturning 'Data Structures and Algorithms':");
        libraryManager.returnMaterial("Data Structures and Algorithms");


        System.out.println("\nLibrary Materials After Returning:");
        libraryManager.displayAllMaterials();
    }
}
