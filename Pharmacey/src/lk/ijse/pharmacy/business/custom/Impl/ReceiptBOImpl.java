/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.business.custom.Impl;

import java.util.ArrayList;
import java.util.List;

import lk.ijse.pharmacy.busines.custom.ReceiptBO;
import lk.ijse.pharmacy.dao.DAOFactory;
import lk.ijse.pharmacy.dao.custom.MedicineDAO;
import lk.ijse.pharmacy.dao.custom.PatientDAO;
import lk.ijse.pharmacy.dao.custom.PrescriptionDAO;
import lk.ijse.pharmacy.dao.custom.ReceiptDAO;
import lk.ijse.pharmacy.db.JPAUtil;
import lk.ijse.pharmacy.dto.ReceiptDTO;
import lk.ijse.pharmacy.entity.Medicine;
import lk.ijse.pharmacy.entity.Patient;
import lk.ijse.pharmacy.entity.Prescription;
import lk.ijse.pharmacy.entity.Receipt;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mcs
 */
public class ReceiptBOImpl implements ReceiptBO {
    private ReceiptDAO receiptdao;
    private MedicineDAO medicineDAO;
    private EntityManagerFactory entityManagerFactory;
    private PatientDAO patientDAO;
    private PrescriptionDAO prescriptionDAO;

    public ReceiptBOImpl() {
        this.medicineDAO = (MedicineDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Medicine);
        this.receiptdao = (ReceiptDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Receipt);
        entityManagerFactory = JPAUtil.getEntityManagerFactory();
        this.patientDAO = (PatientDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Patient);
        this.prescriptionDAO= (PrescriptionDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PreSCRIPTION);
    }

    @Override
    public boolean saveRecipt(ReceiptDTO receipt) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            receiptdao.setEntityManager(entityManager);
            patientDAO.setEntityManager(entityManager);
            medicineDAO.setEntityManager(entityManager);
            prescriptionDAO.setEntityManager(entityManager);
            Patient patient = patientDAO.find(receipt.getPatientID());
            Medicine medicine=medicineDAO.find(receipt.getMID());
            Prescription prescription=prescriptionDAO.find(receipt.getPID());
            Receipt receipt1 = new Receipt(receipt.getReceiptID(), patient, prescription, medicine, receipt.getUnitprice(), receipt.getQty(), receipt.getDate());
            receiptdao.save(receipt1);
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;


    }

    @Override
    public boolean updateRecipt(ReceiptDTO receipt) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            receiptdao.setEntityManager(entityManager);
            patientDAO.setEntityManager(entityManager);
            medicineDAO.setEntityManager(entityManager);
            prescriptionDAO.setEntityManager(entityManager);
            Receipt receipt1=receiptdao.find(receipt.getReceiptID());
            Patient patient = patientDAO.find(receipt.getPatientID());
            Medicine medicine=medicineDAO.find(receipt.getMID());
            Prescription prescription=prescriptionDAO.find(receipt.getPID());
            receipt1.setPatient(patient);
            receipt1.setPrescription(prescription);
            receipt1.setMedicine(medicine);
            receipt1.setUnitprice(receipt.getUnitprice());
            receipt1.setQty(receipt.getQty());
            receipt1.setDate(receipt.getDate());

            entityManager.getTransaction().commit();
            entityManager.close();
            return true;

    }


    @Override
    public boolean DeleteReceipt(String id) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
            receiptdao.setEntityManager(entityManager);
            entityManager.getTransaction().begin();

            receiptdao.delete(id);

            entityManager.getTransaction().commit();
            entityManager.close();
            return true;

    }


    @Override
    public ArrayList<ReceiptDTO> getAllReceipt() throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
            receiptdao.setEntityManager(entityManager);
            entityManager.getTransaction().begin();

            List<Receipt> res = receiptdao.getAll();

            ArrayList<ReceiptDTO> resdto = new ArrayList<>();
            for (Receipt receipt : res) {
                ReceiptDTO resdDTO = new ReceiptDTO(receipt.getReceiptID(),receipt.getPatient().getPatientID(),receipt.getPrescription().getPID(),receipt.getMedicine().getMID(),receipt.getUnitprice(),receipt.getQty(),receipt.getDate());
                resdto.add(resdDTO);
            }
            entityManager.getTransaction().commit();
            entityManager.close();
            return resdto;

    }

    @Override
    public boolean UpdateReceiptQty(String receiptid) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
            receiptdao.setEntityManager(entityManager);
            entityManager.getTransaction().begin();
            Receipt find = receiptdao.find(receiptid);

            if(find == null) {
                entityManager.getTransaction().commit();
                return false;
            }
            medicineDAO.UpdateQty(find.getQty(),find.getMedicine().getMID());
            entityManager.close();
            return true;

    }
}
