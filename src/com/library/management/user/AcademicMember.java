package com.library.management.user;

import com.library.management.management.MemberRecord;

public class AcademicMember extends MemberRecord {

    public AcademicMember(long memberId, String memberType, String dateOfMembership, int noBooksIssued, int maxBookLimit, String memberName, String phoneNumber, String address) {
        super(memberId, memberType, dateOfMembership, noBooksIssued, maxBookLimit, memberName, phoneNumber, address);
    }


    }

