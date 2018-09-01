/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.view.util.tblModel;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author mcs
 */
public class ReceiptTM {
     private String  ReceiptID;
     private  String PatientID;
    private  String PID;
    private  String MID;
    private BigDecimal  Unitprice,Total;
    private int  Qty;
    private Date  Date;

    public ReceiptTM() {
    }

    public ReceiptTM(String ReceiptID, String PatientID, String PID, String MID, BigDecimal Unitprice, BigDecimal Total, int Qty, Date Date) {
        this.ReceiptID = ReceiptID;
        this.PatientID = PatientID;
        this.PID = PID;
        this.MID = MID;
        this.Unitprice = Unitprice;
        this.Total = Total;
        this.Qty = Qty;
        this.Date = Date;
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

    /**
     * @return the Unitprice
     */
    public BigDecimal getUnitprice() {
        return Unitprice;
    }

    /**
     * @param Unitprice the Unitprice to set
     */
    public void setUnitprice(BigDecimal Unitprice) {
        this.Unitprice = Unitprice;
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
     * @return the Total
     */
    public BigDecimal getTotal() {
        return Total;
    }

    /**
     * @param Total the Total to set
     */
    public void setTotal(BigDecimal Total) {
        this.Total = Total;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}
