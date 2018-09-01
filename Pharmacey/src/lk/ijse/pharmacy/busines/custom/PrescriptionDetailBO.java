/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.busines.custom;

import java.util.ArrayList;
import lk.ijse.pharmacy.business.SuperBO;
import lk.ijse.pharmacy.dto.PrescriptiondetailDTO;

/**
 *
 * @author mcs
 */
public interface PrescriptionDetailBO extends SuperBO{
    public  boolean  savePrescriptiondetail(PrescriptiondetailDTO presdet)throws  Exception;

    public boolean DeletePrescriptiondetail(String PID,String MID)throws  Exception;

    public ArrayList<PrescriptiondetailDTO> getAllperesDetail()throws Exception;
}
