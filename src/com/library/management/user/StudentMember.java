package com.library.management.user;

import com.library.management.management.BorrowingManagement;

public class StudentMember extends MemberRecord {

    public StudentMember(long memberId, String dateOfMembership,
                         int noBooksIssued, int maxBookLimit,
                         String memberName, String address,
                         String phoneNumber, BorrowingManagement borrowingManagement) {
        super(memberId, "Student", dateOfMembership, noBooksIssued,
                maxBookLimit, memberName, address, phoneNumber, borrowingManagement);
    }

    @Override
    public boolean borrowBook(String title) {
        //  öğrencilere özel bir indirim ya da gecikme ücreti muafiyeti olabilir.
        return super.borrowBook(title);
    }
}
