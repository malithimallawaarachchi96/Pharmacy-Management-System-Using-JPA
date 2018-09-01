/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.business.custom.Impl;

import java.util.ArrayList;
import java.util.List;

import lk.ijse.pharmacy.busines.custom.PatientBO;
import lk.ijse.pharmacy.dao.DAOFactory;
import lk.ijse.pharmacy.dao.custom.PatientDAO;
import lk.ijse.pharmacy.db.JPAUtil;
import lk.ijse.pharmacy.dto.PatientDTO;
import lk.ijse.pharmacy.entity.Patient;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mcs
 */
public class PatientBOImpl implements PatientBO {
    private PatientDAO patientDAO;
    private EntityManagerFactory entityManagerFactory;


    public PatientBOImpl() {
        this.patientDAO = (PatientDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Patient);
         entityManagerFactory = JPAUtil.getEntityManagerFactory();
    }

    @Override
    public boolean savePatient(PatientDTO patient) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        patientDAO.setEntityManager(entityManager);
        entityManager.getTransaction().begin();
            Patient patient1 = new Patient(patient.getPatientID(), patient.getAddress(), patient.getName());
            patientDAO.save(patient1);
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;


    }

    @Override
    public boolean updatePatient(PatientDTO patient) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        patientDAO.setEntityManager(entityManager);
        entityManager.getTransaction().begin();
            Patient patient1=patientDAO.find(patient.getPatientID());
            patient1.setAddress(patient.getAddress());
            patient1.setName(patient.getName());
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;

    }


    @Override
    public boolean DeletePatient(String id) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        patientDAO.setEntityManager(entityManager);
        entityManager.getTransaction().begin();
            patientDAO.delete(id);
            entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }


    @Override
    public ArrayList<PatientDTO> getAllPatient() throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        patientDAO.setEntityManager(entityManager);
        entityManager.getTransaction().begin();
            List<Patient> allpatients=patientDAO.getAll();

            entityManager.getTransaction().commit();

            ArrayList<PatientDTO> pdtos=new ArrayList<>();
            for(Patient patient:allpatients){
                PatientDTO patientDTO=new PatientDTO(patient.getPatientID(),patient.getAddress(),patient.getName());
                pdtos.add(patientDTO);
            }
            entityManager.close();
            return pdtos;

    }
}
