/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.busines.custom;

import java.util.ArrayList;
import lk.ijse.pharmacy.business.SuperBO;
import lk.ijse.pharmacy.dto.PrescriptionDTO;
import lk.ijse.pharmacy.dto.PrescriptiondetailDTO;

/**
 *
 * @author mcs
 */
public interface PrescriptionBO extends  SuperBO{
    public  boolean SavePrescription(PrescriptionDTO presd,PrescriptiondetailDTO predetailto)throws  Exception;
    public  boolean UpdatePrescription(PrescriptionDTO presd,PrescriptiondetailDTO predetailto)throws  Exception;
    public  boolean DeletePrescription(String id)throws  Exception;
    public  ArrayList<PrescriptionDTO>getAllPrescriptions()throws  Exception;


}
