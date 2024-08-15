package com.library.management;


public abstract class MemberRecord {
    private long memberId;
    private String memberType;
    private String dateOfMembership;
    private int noBooksIssued;
    private int maxBookLimit;
    private String memberName;
    private String address;
    private String phoneNumber;
    private BorrowingManagement borrowingManagement;

    public MemberRecord(long memberId, String memberType, String dateOfMembership,
                        int noBooksIssued,
                        int maxBookLimit, String memberName,
                        String address, String phoneNumber,
                        BorrowingManagement borrowingManagement) {
        this.memberId = memberId;
        this.memberType = memberType;
        this.dateOfMembership = dateOfMembership;
        this.noBooksIssued = 0;
        this.maxBookLimit = maxBookLimit;
        this.memberName = memberName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.borrowingManagement = borrowingManagement;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getDateOfMembership() {
        return dateOfMembership;
    }

    public void setDateOfMembership(String dateOfMembership) {
        this.dateOfMembership = dateOfMembership;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
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

    public void setMaxBookLimit(int maxBookLimit) {
        this.maxBookLimit = maxBookLimit;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BorrowingManagement getBorrowingManagement() {
        return borrowingManagement;
    }

    public void setBorrowingManagement(BorrowingManagement borrowingManagement) {
        this.borrowingManagement = borrowingManagement;
    }

    public boolean borrowBook(String title) {
        if (noBooksIssued < maxBookLimit) {
            if (borrowingManagement.borrowMaterial(this, title)) {
                noBooksIssued++;
                LibraryMaterials material = borrowingManagement.getBorrowedMaterials().get(title).get(0);
                generateInvoice(material, "borrowed");
                return true;
            }
        } else {
            System.out.println("Max book limit reached for " + memberName);
        }
        return false;
    }

    public void returnBook(String title) {
        if (noBooksIssued > 0) {
            borrowingManagement.returnMaterial(this, title);
            LibraryMaterials material = borrowingManagement.getBorrowedMaterials().get(title).get(0);
            generateInvoice(material, "returned");
            noBooksIssued--;
        } else {
            System.out.println(memberName + " has no books to return.");
        }
    }

    private void generateInvoice(LibraryMaterials material, String action) {
        if ("student".equalsIgnoreCase(memberType) || "academic".equalsIgnoreCase(memberType)) {
            System.out.println("Invoice generated:");
            System.out.println("Member: " + memberName);
            System.out.println("Action: " + action);
            System.out.println("Material: " + material.getTitle());
            System.out.println("Price: $0.00 (No charge for student or academic members)");
            System.out.println("Thank you for your transaction!");
        } else {
            double price = material.getPrice();
            System.out.println("Invoice generated:");
            System.out.println("Member: " + memberName);
            System.out.println("Action: " + action);
            System.out.println("Material: " + material.getTitle());
            System.out.println("Price: $" + price);
            System.out.println("Thank you for your transaction!");
        }
    }
}






