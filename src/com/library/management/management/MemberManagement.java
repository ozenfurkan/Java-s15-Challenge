package com.library.management.management;

public interface MemberManagement {
    long getMemberId();
    String getMemberType();
    int getNoBooksIssued();
    int getMaxBookLimit();
    String getMemberName();
    String getDateOfMembership();
    String getAddress();
    String getPhoneNumber();
    void decBooksIssued();
    void incBooksIssued();
    void payBill();
    void setNoBooksIssued(int i);
}