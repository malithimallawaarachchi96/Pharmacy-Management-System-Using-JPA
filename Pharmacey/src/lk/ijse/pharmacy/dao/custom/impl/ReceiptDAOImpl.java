/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;

import lk.ijse.pharmacy.dao.CrudDAOImpl;
import lk.ijse.pharmacy.dao.custom.ReceiptDAO;
import lk.ijse.pharmacy.entity.Receipt;

/**
 *
 * @author mcs
 */
public class ReceiptDAOImpl extends CrudDAOImpl<Receipt,String> implements  ReceiptDAO{

    @Override
    public void UpdateReceiptQty(String receiptid) throws Exception {

    }
}
