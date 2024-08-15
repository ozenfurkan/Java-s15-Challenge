package com.library.management.user;

import com.library.management.management.BorrowingManagement;

public class AcademicMember extends MemberRecord {

    public AcademicMember(long memberId, String dateOfMembership,
                          int noBooksIssued, int maxBookLimit,
                          String memberName, String address,
                          String phoneNumber, BorrowingManagement borrowingManagement) {
        super(memberId, "Academic", dateOfMembership, noBooksIssued,
                maxBookLimit, memberName, address, phoneNumber, borrowingManagement);
    }

    @Override
    public boolean borrowBook(String title) {
        // akademisyenlere özel daha fazla kitap ödünç alma hakkı olabilir.
        return super.borrowBook(title);
    }
}
