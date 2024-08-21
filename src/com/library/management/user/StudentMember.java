package com.library.management.user;

public class StudentMember extends MemberRecord {

    public StudentMember(long memberId, String dateOfMembership,
                         int noBooksIssued, int maxBookLimit,
                         String memberName, String address,
                         String phoneNumber) {
        super(memberId, dateOfMembership, noBooksIssued,
                maxBookLimit, memberName, address, phoneNumber);
    }


}
