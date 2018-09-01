/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mcs
 */
@Entity
@Table(name = "prescription")
public class Prescription {

    @Id
      private String PID;
      @Temporal(TemporalType.DATE)
      private Date Date;
      @OneToMany
      private List<Prescriptiondetail> prescriptiondetails=new ArrayList<>();
      @ManyToOne  (cascade =CascadeType.REMOVE)
      @JoinColumn(name = "PatientID",referencedColumnName = "PatientID",insertable=false,updatable=false)
      private  Patient patient;
      private String PatientID;

    public Prescription() {
    }


    public Prescription(String PID, java.util.Date date, List<Prescriptiondetail> prescriptiondetails, Patient patient) {
        this.setPID(PID);
        setDate(date);
        this.setPrescriptiondetails(prescriptiondetails);
        this.setPatient(patient);
    }

    public Prescription(String PID, Patient patient, java.util.Date date) {
        this.setPID(PID);
        this.setPatient(patient);
        setDate (date);
    }
    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public List<Prescriptiondetail> getPrescriptiondetails() {
        return prescriptiondetails;
    }

    public void setPrescriptiondetails(List<Prescriptiondetail> prescriptiondetails) {
        this.prescriptiondetails = prescriptiondetails;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "PID='" + PID + '\'' +
                ", Date=" + Date +
                ", prescriptiondetails=" + prescriptiondetails +
               /* ", patient=" + patient +*/
                '}';
    }








}
