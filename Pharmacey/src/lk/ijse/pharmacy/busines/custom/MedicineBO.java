/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.busines.custom;

import java.util.ArrayList;
import lk.ijse.pharmacy.business.SuperBO;
import lk.ijse.pharmacy.dto.MedicineDTO;

/**
 *
 * @author mcs
 */
public interface MedicineBO extends  SuperBO{
    public boolean  saveMedicine(MedicineDTO medicine)throws  Exception;
    public boolean  updateMedicine(MedicineDTO medicine)throws  Exception;
    public boolean deleteMedicine(String id)throws Exception;
    public ArrayList<MedicineDTO>getAllMedicine() throws Exception;
    public boolean updateQty(int Qty, String id)throws  Exception;
}
