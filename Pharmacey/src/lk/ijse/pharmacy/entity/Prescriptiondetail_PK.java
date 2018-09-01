/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 *
 * @author mcs
 */
@Embeddable
public class Prescriptiondetail_PK implements Serializable {
    private String  PID;
    private String  MID;

    public Prescriptiondetail_PK() {
    }

    public Prescriptiondetail_PK(String PID, String MID) {
        this.PID = PID;
        this.MID = MID;
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
    
}
