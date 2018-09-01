/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.busines.custom;

import java.util.ArrayList;
import lk.ijse.pharmacy.business.SuperBO;
import lk.ijse.pharmacy.dto.ReceiptDTO;

/**
 *
 * @author mcs
 */
public interface ReceiptBO extends  SuperBO{

    public boolean saveRecipt(ReceiptDTO receipt)throws Exception;

    public boolean updateRecipt(ReceiptDTO receipt)throws Exception;

    public  boolean DeleteReceipt(String id)throws  Exception;

    public ArrayList<ReceiptDTO>getAllReceipt()throws  Exception;

    public  boolean  UpdateReceiptQty(String receiptid)throws Exception;
}
