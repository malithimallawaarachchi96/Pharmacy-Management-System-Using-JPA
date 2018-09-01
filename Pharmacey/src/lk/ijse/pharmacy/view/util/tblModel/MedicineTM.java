/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.view.util.tblModel;

/**
 *
 * @author mcs
 */
public class MedicineTM {
     private String MID;
     private String Description;
     private int  Qty;
     private String  Approval;

    public MedicineTM() {
    }

    public MedicineTM(String MID, String Description, int Qty, String Approval) {
        this.MID = MID;
        this.Description = Description;
        this.Qty = Qty;
        this.Approval = Approval;
    }

    /**
     * @return the MID
     */
    public String getMID() {
        return MID;
    }

    /**
     * @param MID the MID to set
     */
    public void setMID(String MID) {
        this.MID = MID;
    }

    /**
     * @return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     * @return the Qty
     */
    public int getQty() {
        return Qty;
    }

    /**
     * @param Qty the Qty to set
     */
    public void setQty(int Qty) {
        this.Qty = Qty;
    }

    /**
     * @return the Approval
     */
    public String getApproval() {
        return Approval;
    }

    /**
     * @param Approval the Approval to set
     */
    public void setApproval(String Approval) {
        this.Approval = Approval;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

}
