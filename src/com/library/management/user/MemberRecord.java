package com.library.management.user;

import com.library.management.library.EventType;
import com.library.management.model.LibraryMaterials;
import com.library.management.observer.LibraryObserver;

import java.util.List;
import java.util.Map;

public abstract class MemberRecord implements MemberManagement, LibraryObserver {
    private final long memberId;
    private final String dateOfMembership;
    private int noBooksIssued;
    private final int maxBookLimit;
    private final String memberName;
    private final String address;
    private final String phoneNumber;

    public MemberRecord(long memberId,  String dateOfMembership,
                        int noBooksIssued, int maxBookLimit, String memberName,
                        String phoneNumber, String address) {
        this.memberId = memberId;
        this.dateOfMembership = dateOfMembership;
        this.noBooksIssued = noBooksIssued;
        this.maxBookLimit = maxBookLimit;
        this.memberName = memberName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    @Override
    public long getMemberId() {
        return memberId;
    }




    @Override
    public int getNoBooksIssued() {
        return noBooksIssued;
    }

    @Override
    public String getDateOfMembership() {
        return dateOfMembership;
    }

    @Override
    public int getMaxBookLimit() {
        return maxBookLimit;
    }

    @Override
    public String getMemberName() {
        return memberName;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void decBooksIssued() {
        if (noBooksIssued > 0) {
            noBooksIssued--;
        } else {
            System.out.println("No books issued to decrease.");
        }
    }

    @Override
    public void incBooksIssued() {
        if (noBooksIssued < maxBookLimit) {
            noBooksIssued++;
        } else {
            System.out.println("Max issuing limit has been reached, member cannot borrow more materials.");
        }
    }

    @Override
    public void payBill() {
    }

    @Override
    public void setNoBooksIssued(int noBooksIssued) {
        this.noBooksIssued = noBooksIssued;
    }


    @Override
    public void update(EventType eventType, LibraryMaterials material) {
        if (material.getCurrentHolder() != null && material.getCurrentHolder().getMemberId() == this.memberId) {
            switch (eventType) {
                case LEND:
                    System.out.println("Member " + memberName + ": You have borrowed the material titled '" + material.getTitle() + "'.");
                    break;
                case RETURN:
                    System.out.println("Member " + memberName + ": You have returned the material titled '" + material.getTitle() + "'.");
                    break;
                default:
                    System.out.println("Member " + memberName + ": An update occurred for the material titled '" + material.getTitle() + "'.");
                    break;
            }
        }
    }


    @Override
    public String whoYouAre() {
        return "Member: " + getMemberName();
    }

}



