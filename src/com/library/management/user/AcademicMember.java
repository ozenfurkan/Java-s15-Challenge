package com.library.management.user;

public class AcademicMember extends MemberRecord {

    public AcademicMember(long memberId, String dateOfMembership, int noBooksIssued, int maxBookLimit, String memberName, String phoneNumber, String address) {
        super(memberId, dateOfMembership, noBooksIssued, maxBookLimit, memberName, phoneNumber, address);
    }
}

