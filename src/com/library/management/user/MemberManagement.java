package com.library.management.user;

public interface MemberManagement {
    long getMemberId();
    int getNoBooksIssued();
    int getMaxBookLimit();
    String getMemberName();
    String getDateOfMembership();
    String getAddress();
    String getPhoneNumber();
    void decBooksIssued();
    void incBooksIssued();
    void setNoBooksIssued(int i);
}
