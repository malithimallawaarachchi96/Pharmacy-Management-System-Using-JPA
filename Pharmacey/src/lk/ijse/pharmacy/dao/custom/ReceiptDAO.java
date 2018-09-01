/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.dao.custom;

import lk.ijse.pharmacy.dao.CrudDAO;
import lk.ijse.pharmacy.entity.Receipt;

/**
 *
 * @author mcs
 */
public interface ReceiptDAO extends CrudDAO<Receipt,String> {
    public  void   UpdateReceiptQty(String receiptid)throws Exception;
}
