/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.business.custom.Impl;

import java.util.ArrayList;
import java.util.List;

import lk.ijse.pharmacy.busines.custom.MedicineBO;
import lk.ijse.pharmacy.dao.DAOFactory;
import lk.ijse.pharmacy.dao.custom.MedicineDAO;
import lk.ijse.pharmacy.db.JPAUtil;
import lk.ijse.pharmacy.dto.MedicineDTO;
import lk.ijse.pharmacy.entity.Medicine;
import lk.ijse.pharmacy.main.StartUp;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author mcs
 */
public class MedicineBOImpl implements MedicineBO {
    private MedicineDAO medicinedao;
    private EntityManagerFactory entityManagerFactory;


    public MedicineBOImpl() {
        this.medicinedao = (MedicineDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.Medicine);
        entityManagerFactory = JPAUtil.getEntityManagerFactory();
    }

    @Override
    public boolean saveMedicine(MedicineDTO medicine) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        medicinedao.setEntityManager(entityManager);
            entityManager.getTransaction().begin();
            Medicine medicine1 = new Medicine(medicine.getMID(), medicine.getDescription(), medicine.getQty(), medicine.getApproval());
            medicinedao.save(medicine1);
            entityManager.getTransaction().commit();
            entityManager.close();
        return true;
    }

    @Override
    public boolean updateMedicine(MedicineDTO medicine) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        medicinedao.setEntityManager(entityManager);
            entityManager.getTransaction().begin();
            Medicine medicine1=medicinedao.find(medicine.getMID());
            medicine1.setDescription(medicine.getDescription());
            medicine1.setQty(medicine1.getQty());
            medicine1.setApproval(medicine.getApproval());
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;

    }

    @Override
    public boolean deleteMedicine(String id) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        medicinedao.setEntityManager(entityManager);
            entityManager.getTransaction().begin();
            medicinedao.delete(id);
            entityManager.getTransaction().commit();
            entityManager.close();
            return true;

    }

    @Override
    public ArrayList<MedicineDTO> getAllMedicine() throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        medicinedao.setEntityManager(entityManager);
            entityManager.getTransaction().begin();

            List<Medicine> medi=medicinedao.getAll();

            entityManager.getTransaction().commit();

            ArrayList<MedicineDTO>medidto=new ArrayList<>();

            for(Medicine medicine:medi){
                MedicineDTO medto=new MedicineDTO(medicine.getMID(),medicine.getDescription(),medicine.getQty(),medicine.getApproval());
                medidto.add(medto);
            }
            entityManager.close();
            return medidto;

    }

    @Override
    public boolean updateQty(int Qty, String id) throws Exception {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        medicinedao.setEntityManager(entityManager);
            entityManager.getTransaction().begin();
            medicinedao.UpdateQty(Qty, id);
            entityManager.getTransaction().commit();
            entityManager.close();

        return true;
    }
}
