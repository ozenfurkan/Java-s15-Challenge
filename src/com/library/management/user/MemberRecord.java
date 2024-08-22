package com.library.management.user;

import java.util.Objects;

public abstract class MemberRecord {
    private final long memberId;
    private final String dateOfMembership;
    private int noBooksIssued;
    private final int maxBookLimit;
    private final String memberName;
    private final String address;
    private final String phoneNumber;

    public MemberRecord(long memberId, String dateOfMembership, int noBooksIssued, int maxBookLimit, String memberName, String phoneNumber, String address) {
        this.memberId = memberId;
        this.dateOfMembership = dateOfMembership;
        this.noBooksIssued = noBooksIssued;
        this.maxBookLimit = maxBookLimit;
        this.memberName = memberName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public long getMemberId() {
        return memberId;
    }

    public int getNoBooksIssued() {
        return noBooksIssued;
    }

    public void setNoBooksIssued(int noBooksIssued) {
        this.noBooksIssued = noBooksIssued;
    }

    public int getMaxBookLimit() {
        return maxBookLimit;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getDateOfMembership() {
        return dateOfMembership;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "MemberRecord{" +
                "memberId=" + memberId +
                ", memberName='" + memberName + '\'' +
                ", dateOfMembership='" + dateOfMembership + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberRecord that = (MemberRecord) o;
        return memberId == that.memberId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }
}



