/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.business.custom.Impl;

import java.util.ArrayList;
import lk.ijse.pharmacy.busines.custom.PrescriptionDetailBO;
import lk.ijse.pharmacy.dao.DAOFactory;
import lk.ijse.pharmacy.dao.custom.PrescriptionDetailDAO;
import lk.ijse.pharmacy.db.JPAUtil;
import lk.ijse.pharmacy.dto.PrescriptiondetailDTO;
import lk.ijse.pharmacy.entity.Prescriptiondetail;
import lk.ijse.pharmacy.entity.Prescriptiondetail_PK;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mcs
 */
public class PrescriptionDetailBOImpl implements PrescriptionDetailBO {

    PrescriptionDetailDAO prescriptionDetailDAO;
    private EntityManagerFactory entityManagerFactory;

    public PrescriptionDetailBOImpl() {
        this.prescriptionDetailDAO = (PrescriptionDetailDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PrescriptionDetail);
         entityManagerFactory = JPAUtil.getEntityManagerFactory();
    }


    @Override
    public boolean savePrescriptiondetail(PrescriptiondetailDTO presdet) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
            prescriptionDetailDAO.setEntityManager(entityManager);
            entityManager.getTransaction().begin();
            Prescriptiondetail prescriptiondetail = new Prescriptiondetail(presdet.getDoctorName(), presdet.getPID(), presdet.getMID());
            prescriptionDetailDAO.save(prescriptiondetail);
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;


    }


    @Override
    public boolean DeletePrescriptiondetail(String PID, String MID) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
            prescriptionDetailDAO.setEntityManager(entityManager);
            entityManager.getTransaction().begin();
            prescriptionDetailDAO.delete(new Prescriptiondetail_PK(PID, MID));
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;


    }


    @Override
    public ArrayList<PrescriptiondetailDTO> getAllperesDetail() throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
            prescriptionDetailDAO.setEntityManager(entityManager);
            entityManager.getTransaction().begin();

            ArrayList<Prescriptiondetail> predetail = prescriptionDetailDAO.getAll();

            entityManager.getTransaction().commit();

            ArrayList<PrescriptiondetailDTO> Presdto = new ArrayList<>();
            for (Prescriptiondetail presc : predetail) {
                PrescriptiondetailDTO predto = new PrescriptiondetailDTO(presc.getDoctorName(), presc.getPrescriptiondetail_PK().getPID(),
                        presc.getPrescriptiondetail_PK().getMID());

                Presdto.add(predto);
            }
            entityManager.close();
            return Presdto;

    }
}
