/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.entity;

import javax.persistence.*;

/**
 *
 * @author mcs
 */
@Entity
@Table(name = "prescription_detail")
public class Prescriptiondetail {
     private String DoctorName;
     @EmbeddedId
     private Prescriptiondetail_PK prescriptiondetail_PK;
     @ManyToOne
     private Medicine medicine;
     @ManyToOne(cascade ={CascadeType.PERSIST,CascadeType.REMOVE})
     private Prescription prescription;

    public Prescriptiondetail() {
    }

    public Prescriptiondetail(String doctorName, Prescriptiondetail_PK prescriptiondetail_PK, Medicine medicine, Prescription prescription) {
        DoctorName = doctorName;
        this.prescriptiondetail_PK = prescriptiondetail_PK;
        this.medicine = medicine;
        this.prescription = prescription;
    }

    public Prescriptiondetail(String doctorName,String PID,String MID) {
        DoctorName = doctorName;
        this.prescriptiondetail_PK=new Prescriptiondetail_PK(PID,MID);
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public Prescriptiondetail_PK getPrescriptiondetail_PK() {
        return prescriptiondetail_PK;
    }

    public void setPrescriptiondetail_PK(Prescriptiondetail_PK prescriptiondetail_PK) {
        this.prescriptiondetail_PK = prescriptiondetail_PK;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    @Override
    public String toString() {
        return "Prescriptiondetail{" +
                "DoctorName='" + DoctorName + '\'' +
                ", prescriptiondetail_PK=" + prescriptiondetail_PK +
                ", medicine=" + medicine +
                ", prescription=" + prescription +
                '}';
    }
}
