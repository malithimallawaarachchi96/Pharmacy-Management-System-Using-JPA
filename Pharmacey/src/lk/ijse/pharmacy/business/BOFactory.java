/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.business;

import lk.ijse.pharmacy.busines.custom.PatientBO;
import lk.ijse.pharmacy.business.custom.Impl.MedicineBOImpl;
import lk.ijse.pharmacy.business.custom.Impl.PatientBOImpl;
import lk.ijse.pharmacy.business.custom.Impl.PrescriptionBOImpl;
import lk.ijse.pharmacy.business.custom.Impl.PrescriptionDetailBOImpl;
import lk.ijse.pharmacy.business.custom.Impl.ReceiptBOImpl;

/**
 *
 * @author mcs
 */
public class BOFactory {
    private  static BOFactory bOFactory;
    

    public BOFactory() {
    }
    public static BOFactory getInstance(){
        if(bOFactory==null){
            bOFactory=new BOFactory();
        }
        return bOFactory;
    }
    public enum BOType{
        Patient,Medicine,PRESCRIPTION,PrescriptionDetail,Receipt;
    }
    public SuperBO getBO(BOType type){
        switch(type){
            case Patient:
                return new PatientBOImpl();
            case Medicine:
                return new MedicineBOImpl();
            case PRESCRIPTION:
                return new PrescriptionBOImpl();
            case PrescriptionDetail:
                return new PrescriptionDetailBOImpl();
            case Receipt:  
                return new ReceiptBOImpl();
        }
        return null;
    }
        
    
}
       
        
