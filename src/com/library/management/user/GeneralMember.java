package com.library.management.user;

import com.library.management.library.EventType;
import com.library.management.model.LibraryMaterials;

public class GeneralMember extends MemberRecord {

    public GeneralMember(long memberId,
                         String dateOfMembership, int noBooksIssued, int maxBookLimit,
                         String memberName, String phoneNumber, String address) {
        super(memberId, dateOfMembership, noBooksIssued, maxBookLimit,
                memberName, phoneNumber, address);
    }


}



