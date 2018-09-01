/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.busines.custom;

import java.util.ArrayList;
import lk.ijse.pharmacy.business.SuperBO;
import lk.ijse.pharmacy.dto.PatientDTO;

/**
 *
 * @author mcs
 */
public interface PatientBO extends  SuperBO{
    public boolean savePatient(PatientDTO patient)throws  Exception;
    public boolean updatePatient(PatientDTO patient)throws  Exception;
    public boolean DeletePatient(String id)throws  Exception;
    public ArrayList<PatientDTO>getAllPatient()throws  Exception;
}
