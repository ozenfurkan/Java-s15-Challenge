package com.library.management.user;

import com.library.management.management.BorrowingManagement;

public class GeneralMember extends MemberRecord {

    public GeneralMember(long memberId, String dateOfMembership,
                         int noBooksIssued, int maxBookLimit,
                         String memberName, String address,
                         String phoneNumber, BorrowingManagement borrowingManagement) {
        super(memberId, "General", dateOfMembership, noBooksIssued,
                maxBookLimit, memberName, address, phoneNumber, borrowingManagement);
    }

    @Override
    public boolean borrowBook(String title) {
        // Örneğin, genel üyeler için belirli bir ücretlendirme politikası olabilir.
        return super.borrowBook(title);
    }
}
