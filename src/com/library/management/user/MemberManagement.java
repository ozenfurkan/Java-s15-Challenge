package com.library.management.user;

import com.library.management.model.LibraryMaterials;

import java.util.List;
import java.util.Map;

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
    void payBill();
    void setNoBooksIssued(int i);

}