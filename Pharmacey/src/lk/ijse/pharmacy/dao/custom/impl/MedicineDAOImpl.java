/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.model.core.ID;
import lk.ijse.pharmacy.dao.CrudDAOImpl;
import lk.ijse.pharmacy.dao.custom.MedicineDAO;
import lk.ijse.pharmacy.entity.Medicine;
import org.hibernate.Session;

/**
 *
 * @author mcs
 */
public class MedicineDAOImpl extends CrudDAOImpl<Medicine,String> implements MedicineDAO{


    @Override
    public void UpdateQty(int qty, String id) throws Exception {

    }
}
