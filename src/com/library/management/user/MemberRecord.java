package com.library.management.user;

import com.library.management.management.BorrowingManagement;
import com.library.management.observer.MaterialObserver;
import com.library.management.model.LibraryMaterials;

public abstract class MemberRecord {
    private final long memberId;
    private final String memberType;
    private final String dateOfMembership;
    private int noBooksIssued;
    private final int maxBookLimit;
    private final String memberName;
    private final String address;
    private final String phoneNumber;
    private final BorrowingManagement borrowingManagement;
    private MaterialObserver materialObserver;

    public MemberRecord(long memberId, String memberType, String dateOfMembership,
                        int noBooksIssued, int maxBookLimit, String memberName,
                        String address, String phoneNumber, BorrowingManagement borrowingManagement) {
        this.memberId = memberId;
        this.memberType = memberType;
        this.dateOfMembership = dateOfMembership;
        this.noBooksIssued = noBooksIssued;
        this.maxBookLimit = maxBookLimit;
        this.memberName = memberName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.borrowingManagement = borrowingManagement;
    }

    public void setMaterialObserver(MaterialObserver materialObserver) {
        this.materialObserver = materialObserver;
    }

    public boolean borrowBook(String title) {
        if (noBooksIssued < maxBookLimit) {
            if (borrowingManagement.borrowMaterial(this, title)) {
                noBooksIssued++;
                LibraryMaterials material = borrowingManagement.getBorrowedMaterials().get(title).get(0);

                if (materialObserver != null) {
                    materialObserver.onMaterialAdded(material);
                } else {
                    System.out.println("No MaterialObserver set for member " + memberName);
                }
                return true;
            }
        } else {
            System.out.println("Max book limit reached for " + memberName);
        }
        return false;
    }

    public void returnBook(String title) {
        if (noBooksIssued > 0) {
            borrowingManagement.returnMaterial(this, title);
            noBooksIssued--;
        } else {
            System.out.println(memberName + " has no books to return.");
        }
    }

    public String getMemberName() {
        return memberName;
    }
}
