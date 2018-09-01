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
public class PatientDTO {
    private String PatientID;
    private String  Address;
    private String  Name;

    public PatientDTO() {
    }

    public PatientDTO(String PatientID, String Address, String Name) {
        this.PatientID = PatientID;
        this.Address = Address;
        this.Name = Name;
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
     * @return the Address
     */
    public String getAddress() {
        return Address;
    }

    /**
     * @param Address the Address to set
     */
    public void setAddress(String Address) {
        this.Address = Address;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    @Override
    public String toString() {
        return "PatientDTO{" +
                "PatientID='" + PatientID + '\'' +
                ", Address='" + Address + '\'' +
                ", Name='" + Name + '\'' +
                '}';
    }
}
