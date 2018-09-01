/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.dto;

import lk.ijse.pharmacy.entity.Patient;

import java.util.Date;

/**
 *
 * @author mcs
 */
public class PrescriptionDTO {
     private String PID;
     private String PatientID;
      private Date Date;

    public PrescriptionDTO() {
    }

    public PrescriptionDTO(String PID, String PatientID, Date Date) {
        this.PID = PID;
        this.PatientID = PatientID;
        this.Date = Date;
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
     * @return the Date
     */
    public Date getDate() {
        return Date;
    }

    /**
     * @param Date the Date to set
     */
    public void setDate(Date Date) {
        this.Date = Date;
    }

    

    /**
     * @return the PatientID
     */
    public String getPatientID() {
        return PatientID;
    }

    /**
     * @param PatientID the PatientID to set
     */
    public void setPatientID(String PatientID) {
        this.PatientID = PatientID;
    }

    @Override
    public String toString() {
        return "PrescriptionDTO{" +
                "PID='" + PID + '\'' +
                ", PatientID='" + PatientID + '\'' +
                ", Date=" + Date +
                '}';
    }
}
