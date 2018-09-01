/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


/**
 *
 * @author mcs
 */
public class JPAUtil {
    private  static  EntityManagerFactory entityManagerFactory=buildEntityFactory();

    private  static  EntityManagerFactory buildEntityFactory(){
        File file=new File("Pharmacey/application.properties");
        FileReader fileReader=null;
        try{
            fileReader=new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties jpaProp=new Properties();
        try{
            jpaProp.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("pharmacy",jpaProp);
        return  entityManagerFactory;
    }
    public  static  EntityManagerFactory getEntityManagerFactory(){
        return  entityManagerFactory;
    }

}
