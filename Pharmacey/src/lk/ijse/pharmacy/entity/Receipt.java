/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author mcs
 */
@Entity
@Table(name = "receipt")
public class Receipt {
    @Id
    private String  ReceiptID;
    @ManyToOne(cascade =CascadeType.REMOVE)
    @JoinColumn(name = "PatientID",referencedColumnName = "PatientID")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "PID",referencedColumnName = "PID")
    private Prescription prescription;
    @ManyToOne
    @JoinColumn(name = "mid",referencedColumnName = "MID")
    private Medicine medicine;
    private BigDecimal  Unitprice;
    private int  Qty;
    @Temporal(TemporalType.DATE)
    private java.util.Date date;

    public Receipt() {
    }

    public Receipt(String receiptID, Patient patient, Prescription prescription, Medicine medicine, BigDecimal unitprice, int qty, Date date) {
        ReceiptID = receiptID;
        this.patient = patient;
        this.prescription = prescription;
        this.medicine = medicine;
        Unitprice = unitprice;
        Qty = qty;
        this.date = date;
    }

    public String getReceiptID() {
        return ReceiptID;
    }

    public void setReceiptID(String receiptID) {
        ReceiptID = receiptID;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public BigDecimal getUnitprice() {
        return Unitprice;
    }

    public void setUnitprice(BigDecimal unitprice) {
        Unitprice = unitprice;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "ReceiptID='" + ReceiptID + '\'' +
                ", patient=" + patient +
                ", prescription=" + prescription +
                ", medicine=" + medicine +
                ", Unitprice=" + Unitprice +
                ", Qty=" + Qty +
                ", date=" + date +
                '}';
    }
}
