package com.library.management.user;

import com.library.management.management.MemberRecord;

public class StudentMember extends MemberRecord {

    public StudentMember(long memberId, String dateOfMembership,
                         int noBooksIssued, int maxBookLimit,
                         String memberName, String address,
                         String phoneNumber) {
        super(memberId, "Student", dateOfMembership, noBooksIssued,
                maxBookLimit, memberName, address, phoneNumber);
    }


}
