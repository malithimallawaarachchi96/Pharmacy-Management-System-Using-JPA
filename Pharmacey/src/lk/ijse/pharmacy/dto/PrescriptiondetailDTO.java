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
public class PrescriptiondetailDTO {
  private String DoctorName;
   private String PID;
   private String MID;
  

    public PrescriptiondetailDTO() {
    }

    public PrescriptiondetailDTO(String DoctorName, String PID, String MID) {
        this.DoctorName = DoctorName;
        this.PID = PID;
        this.MID = MID;
       
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

    @Override
    public String toString() {
        return "PrescriptiondetailDTO{" + "DoctorName=" + DoctorName + ", PID=" + PID + ", MID=" + MID + '}';
    }

    


}
