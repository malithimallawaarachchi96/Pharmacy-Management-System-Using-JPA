/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.dao;

import lk.ijse.pharmacy.dao.custom.impl.MedicineDAOImpl;
import lk.ijse.pharmacy.dao.custom.impl.PatientDAOImpl;
import lk.ijse.pharmacy.dao.custom.impl.PrescriptionDAOImpl;
import lk.ijse.pharmacy.dao.custom.impl.PrescriptiondetailDAOImpl;
import lk.ijse.pharmacy.dao.custom.impl.ReceiptDAOImpl;

/**
 *
 * @author mcs
 */
public class DAOFactory {
    private static DAOFactory dAOFactory;

    public DAOFactory() {
    }
    public static  DAOFactory getInstance(){
        if(dAOFactory==null){
            dAOFactory=new DAOFactory();
        }
        return dAOFactory;
    }
    public enum DAOType{
        Patient,Medicine,PreSCRIPTION,PrescriptionDetail,Receipt
    }
   public SuperDAO getDAO(DAOType type){
       switch(type){
           case Patient:
               return  new PatientDAOImpl();
           case Medicine:
               return  new MedicineDAOImpl();
           case PreSCRIPTION:
               return  new PrescriptionDAOImpl();
           case PrescriptionDetail:
               return new PrescriptiondetailDAOImpl();
           case Receipt:
               return new ReceiptDAOImpl();
       }
        return null;
   }
}
