/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.dto;

/**
 *
 * @author mcs
 */
public class MedicineDTO {
    private String MID;
     private String Description;
     private int  Qty;
     private String  Approval;

    public MedicineDTO() {
    }

    public MedicineDTO(String MID, String Description, int Qty, String Approval) {
        this.setMID(MID);
        this.setDescription(Description);
        this.setQty(Qty);
        this.setApproval(Approval);
    }


    public String getMID() {
        return MID;
    }

    public void setMID(String MID) {
        this.MID = MID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public String getApproval() {
        return Approval;
    }

    public void setApproval(String approval) {
        Approval = approval;
    }

    @Override
    public String toString() {
        return "MedicineDTO{" +
                "MID='" + MID + '\'' +
                ", Description='" + Description + '\'' +
                ", Qty=" + Qty +
                ", Approval='" + Approval + '\'' +
                '}';
    }
}
