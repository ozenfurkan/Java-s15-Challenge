package com.library.management.management;

public abstract class MemberRecord implements MemberManagement {
    private final long memberId;
    private final String memberType;
    private final String dateOfMembership;
    private int noBooksIssued;
    private final int maxBookLimit;
    private final String memberName;
    private final String address;
    private final String phoneNumber;

    public MemberRecord(long memberId, String memberType, String dateOfMembership,
                        int noBooksIssued, int maxBookLimit, String memberName,
                        String phoneNumber, String address) {
        this.memberId = memberId;
        this.memberType = memberType;
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
    public String getMemberType() {
        return memberType;
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
        // Borç ödeme işlemleri burada tanımlanabilir
    }

    @Override
    public void setNoBooksIssued(int noBooksIssued) {
        this.noBooksIssued = noBooksIssued;
    }
}
