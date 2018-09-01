/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.dao;

import lk.ijse.pharmacy.dto.MedicineDTO;

import java.util.ArrayList;

/**
 *
 * @author mcs
 */
public interface CrudDAO<T,ID> extends SuperDAO {
    public void save(T entity) throws Exception;
    
    public void update(T entity) throws Exception;
    
    public void delete(ID id) throws Exception;
    
    public T find(ID id) throws Exception;
    
    public ArrayList<T> getAll() throws Exception;    
}
