/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.dao.custom;

import lk.ijse.pharmacy.dao.CrudDAO;
import lk.ijse.pharmacy.dao.CrudDAOImpl;
import lk.ijse.pharmacy.entity.Medicine;

/**
 *
 * @author mcs
 */
public interface MedicineDAO extends CrudDAO<Medicine,String> {

    public void UpdateQty(int qty,String id)throws Exception;

}
