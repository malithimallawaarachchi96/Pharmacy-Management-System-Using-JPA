/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.view.util.tblModel;

import java.sql.Date;

/**
 *
 * @author mcs
 */
public class PrescriptiondetailTM {
     private String DoctorName;
   private String PID;
   private String MID;
  private String  ReceiptID;  

    public PrescriptiondetailTM() {
    }

    public PrescriptiondetailTM(String DoctorName, String PID, String MID, String ReceiptID) {
        this.DoctorName = DoctorName;
        this.PID = PID;
        this.MID = MID;
        this.ReceiptID = ReceiptID;
    }

    /**
     * @return the DoctorName
     */
    public String getDoctorName() {
        return DoctorName;
    }

    /**
     * @param DoctorName the DoctorName to set
     */
    public void setDoctorName(String DoctorName) {
        this.DoctorName = DoctorName;
    }

    /**
     * @return the PID
     */
    public String getPID() {
        return PID;
    }

    /**
     * @param PID the PID to set
     */
    public void setPID(String PID) {
        this.PID = PID;
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
     * @return the ReceiptID
     */
    public String getReceiptID() {
        return ReceiptID;
    }

    /**
     * @param ReceiptID the ReceiptID to set
     */
    public void setReceiptID(String ReceiptID) {
        this.ReceiptID = ReceiptID;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}