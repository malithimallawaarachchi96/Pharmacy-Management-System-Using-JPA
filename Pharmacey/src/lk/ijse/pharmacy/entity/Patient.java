/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mcs
 */
@Entity
@Table(name = "Patient")
public class Patient {
    @Id
    private String PatientID;
    private String  Address;
    private String  Name;
    @OneToMany(mappedBy = "patient",cascade = CascadeType.REMOVE)
    private List<Prescription>prescriptions=new ArrayList<>();
    @OneToMany(mappedBy = "patient",fetch = FetchType.LAZY)
    private List<Receipt> receipts=new ArrayList<>();

    public Patient() {
    }

    public Patient(String PatientID, String Address, String Name) {
        this.setPatientID(PatientID);
        this.setAddress(Address);
        this.setName(Name);
    }


    public String getPatientID() {
        return PatientID;
    }

    public void setPatientID(String patientID) {
        PatientID = patientID;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "PatientID='" + PatientID + '\'' +
                ", Address='" + Address + '\'' +
                ", Name='" + Name + '\'' +
                ", prescriptions=" + prescriptions +
                ", receipts=" + receipts +
                '}';
    }
}
