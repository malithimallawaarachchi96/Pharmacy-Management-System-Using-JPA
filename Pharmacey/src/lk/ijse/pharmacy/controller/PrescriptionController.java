/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.pharmacy.busines.custom.MedicineBO;
import lk.ijse.pharmacy.busines.custom.PatientBO;
import lk.ijse.pharmacy.busines.custom.PrescriptionBO;
import lk.ijse.pharmacy.busines.custom.PrescriptionDetailBO;
import lk.ijse.pharmacy.business.BOFactory;
import lk.ijse.pharmacy.dto.MedicineDTO;
import lk.ijse.pharmacy.dto.PatientDTO;
import lk.ijse.pharmacy.dto.PrescriptionDTO;
import lk.ijse.pharmacy.dto.PrescriptiondetailDTO;
import lk.ijse.pharmacy.view.util.tblModel.PrescriptionTM;

/**
 * FXML Controller class
 *
 * @author mcs
 */
public class PrescriptionController implements Initializable {

    @FXML
    private JFXTextField txtPid;
    @FXML
    private TableView<PrescriptionTM> tblpres;
    @FXML
    private JFXComboBox<String> cmbMID;
    @FXML
    private JFXTextField txtDocname;
    @FXML
    private JFXComboBox<String> cmbPatientID;
    @FXML
    private JFXDatePicker txtDate;
    
    PrescriptionBO Presbo;
    PrescriptionDetailBO presdetailbo;
    PatientBO patientbo;
    MedicineBO medicinebo;
    @FXML
    private ImageView ImgPres;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtmedidescription;
    @FXML
    private JFXTextField txtQty;
    
    public PrescriptionController() {
        this.presdetailbo = (PrescriptionDetailBO) BOFactory.getInstance().getBO(BOFactory.BOType.PrescriptionDetail);
        this.medicinebo = (MedicineBO) BOFactory.getInstance().getBO(BOFactory.BOType.Medicine);
        this.patientbo = (PatientBO) BOFactory.getInstance().getBO(BOFactory.BOType.Patient);
       this.Presbo = (PrescriptionBO) BOFactory.getInstance().getBO(BOFactory.BOType.PRESCRIPTION);
    }
    public void loadPres(){
        try {
            ArrayList<PrescriptionDTO>presdto=Presbo.getAllPrescriptions();
            ArrayList<PrescriptionTM>prestm=new ArrayList<>();
            for (PrescriptionDTO pres : presdto) {
                PrescriptionTM prest=new PrescriptionTM(pres.getPID(), pres.getPatientID(), (Date) pres.getDate());
                prestm.add(prest);
            }
            tblpres.setItems(FXCollections.observableArrayList(prestm));
        } catch (Exception ex) {
            Logger.getLogger(PrescriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void loadCmbMid(){
        
        try {
            ArrayList<MedicineDTO>midDto=medicinebo.getAllMedicine();
            ArrayList<String>medi=new ArrayList<>();
            for (MedicineDTO md : midDto) 
            {
               medi.add(md.getMID());
            }
          cmbMID.setItems(FXCollections.observableArrayList(medi));
                   
        } catch (Exception ex) {
            Logger.getLogger(PrescriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void loadCmbpatient(){
        try {
            ArrayList<PatientDTO>patientDTO=patientbo.getAllPatient();
            ArrayList<String>patie=new ArrayList<>();
            for (PatientDTO pa : patientDTO) {
                patie.add(pa.getPatientID());
            }
            cmbPatientID.setItems(FXCollections.observableArrayList(patie));
        } catch (Exception ex) {
            Logger.getLogger(PrescriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblpres.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("PID"));
        tblpres.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("PatientID"));
        tblpres.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Date"));
        loadPres();
        loadCmbMid();
        loadCmbpatient();
    }    

    @FXML
    private void tbleclick(MouseEvent event) {
       PrescriptionTM medi= tblpres.getSelectionModel().getSelectedItem();
            txtPid.setText(medi.getPID());
            cmbPatientID.setValue(medi.getPatientID());
            
        
    }

    @FXML
    private void RemoveOnAct(ActionEvent event) {
        deletePresDetail();
        String select=tblpres.getSelectionModel().getSelectedItem().getPID();
        try {
            boolean result=Presbo.DeletePrescription(select);
            if (result) {
                
                new Alert(Alert.AlertType.INFORMATION, "Prescription is deleted", ButtonType.OK).show();
                txtPid.setText("");
                cmbPatientID.setValue("");
                cmbMID.setValue("");
                loadPres();
            }else{
                new Alert(Alert.AlertType.INFORMATION, "Prescription is not deleted", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(PrescriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void SaveOnAct(ActionEvent event) {
        savepres();
       
        
        
        }
        
       
        
    

    @FXML
    private void NaviToHome(MouseEvent event) throws IOException {
         if (event.getSource() instanceof ImageView) {
            ImageView img = (ImageView) event.getSource();
            Parent root = null;
            switch (img.getId()) {
                case "ImgPres":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/pharmacy/view/mainPharmacy.fxml"));
                    break;

            }
            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();
                primaryStage.show();
                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();

            }

        }
    }

    @FXML
    private void OncmbMid(ActionEvent event) {
        
    
}
    
    public boolean savetopresdetail(){
        PrescriptiondetailDTO presdetdto=new PrescriptiondetailDTO(txtDocname.getText(),txtPid.getText(),cmbMID.getValue());
        //System.out.println(presdetdto.toString());
        try {
            boolean result=presdetailbo.savePrescriptiondetail(presdetdto);
            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "Saved to precriptiondetail", ButtonType.OK).show();
            }else{
                new Alert(Alert.AlertType.ERROR, " Not Saved to precriptiondetail", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(PrescriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    public  boolean savepres(){
        PrescriptionDTO presd=new PrescriptionDTO(txtPid.getText(),cmbPatientID.getValue(),Date.valueOf(txtDate.getValue()));
        PrescriptiondetailDTO presdetdto=new PrescriptiondetailDTO(txtDocname.getText(),txtPid.getText(),cmbMID.getValue());
        try {
            boolean result=  Presbo.SavePrescription(presd,presdetdto);

            if(result){
                 savetopresdetail();
                new Alert(Alert.AlertType.INFORMATION, "Prescription is saved", ButtonType.OK).show();
                txtPid.setText("");
                txtDocname.setText("");
                cmbMID.setValue("");
                cmbPatientID.setValue("");
                loadPres();
            }else{
               new Alert(Alert.AlertType.INFORMATION, "Prescription is not saved", ButtonType.OK).show(); 
               txtPid.requestFocus();
            }
        } catch (Exception ex) {
            Logger.getLogger(PrescriptionController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return true;
    }
    public  void deletePresDetail(){
       
        try {
             boolean result=presdetailbo.DeletePrescriptiondetail(txtPid.getText(),cmbMID.getValue());
            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "prescription detail is deleted", ButtonType.OK).show();
            }else{
            new Alert(Alert.AlertType.ERROR, " Couldn't deleted  prescription detail", ButtonType.OK).show();
           
        }
                
            
        } catch (Exception ex) {
            Logger.getLogger(PrescriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
}
