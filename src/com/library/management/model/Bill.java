package com.library.management.model;
import com.library.management.user.MemberRecord;
import java.time.LocalDate;

public class Bill {
    private final long memberIdBill;
    private final double amount;
    private final LocalDate billDate;
    private BillStatus billStatus;
    private final String materialIdBill;

    public Bill(MemberRecord memberRecord, LibraryMaterials materials, BillStatus billStatus) {
        this.memberIdBill = memberRecord.getMemberId();
        this.amount = materials.getPrice();
        this.billDate = LocalDate.now();
        this.billStatus = billStatus;
        this.materialIdBill = materials.getMaterialId();
    }

    public long getMemberIdBill() {
        return memberIdBill;
    }

    public String getMaterialIdBill() {
        return materialIdBill;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public BillStatus getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(BillStatus billStatus) {
        this.billStatus = billStatus;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "memberIdBill=" + memberIdBill +
                ", amount=" + amount +
                ", billDate=" + billDate +
                ", billStatus=" + billStatus +
                ", materialIdBill='" + materialIdBill + '\'' +
                '}';
    }
}

