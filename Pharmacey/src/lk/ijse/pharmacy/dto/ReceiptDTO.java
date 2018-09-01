/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.dto;


import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author mcs
 */
public class ReceiptDTO {
    private String  ReceiptID;
    private String PatientID;
    private String PID;
    private String MID;
    private BigDecimal  Unitprice;
    private int  Qty;
    private java.util.Date date;



    public ReceiptDTO(String receiptID, String patientID, String PID, String MID, BigDecimal unitprice, int qty, Date date) {
        setReceiptID(receiptID);
        setPatientID(patientID);
        this.setPID(PID);
        this.setMID(MID);
        setUnitprice(unitprice);
        setQty(qty);
        this.setDate(date);
    }

    public String getReceiptID() {
        return ReceiptID;
    }

    public void setReceiptID(String receiptID) {
        ReceiptID = receiptID;
    }

    public String getPatientID() {
        return PatientID;
    }

    public void setPatientID(String patientID) {
        PatientID = patientID;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getMID() {
        return MID;
    }

    public void setMID(String MID) {
        this.MID = MID;
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
        return "ReceiptDTO{" +
                "ReceiptID='" + ReceiptID + '\'' +
                ", PatientID='" + PatientID + '\'' +
                ", PID='" + PID + '\'' +
                ", MID='" + MID + '\'' +
                ", Unitprice=" + Unitprice +
                ", Qty=" + Qty +
                ", date=" + date +
                '}';
    }
}
