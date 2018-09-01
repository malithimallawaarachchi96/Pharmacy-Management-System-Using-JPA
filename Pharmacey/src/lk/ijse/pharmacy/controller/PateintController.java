/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.controller;

import com.jfoenix.controls.JFXTextField;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.pharmacy.busines.custom.PatientBO;
import lk.ijse.pharmacy.business.BOFactory;
import lk.ijse.pharmacy.dto.PatientDTO;
import lk.ijse.pharmacy.view.util.tblModel.PatientTM;
import sun.plugin2.main.server.LiveConnectSupport;

/**
 * FXML Controller class
 *
 * @author mcs
 */
public class PateintController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private ImageView Imgpatient;
    @FXML
    private JFXTextField txtpatientID;
    @FXML
    private JFXTextField txtPatientname;
    @FXML
    private JFXTextField PatientAddress;
    @FXML
    private TableView<PatientTM> tblPatient;
   private  boolean  addnew=true;
    private PatientBO patientbo;

    public PateintController() {
        this.patientbo = (PatientBO) BOFactory.getInstance().getBO(BOFactory.BOType.Patient);
    }
    public void loadpatients(){
        try {
            ArrayList<PatientDTO> allpatient=patientbo.getAllPatient();
            ArrayList<PatientTM> olpatient=new ArrayList<>();
            for (PatientDTO patient : allpatient) {
                PatientTM patients=new PatientTM(patient.getPatientID(), patient.getAddress(), patient.getName());
                olpatient.add(patients);
            }
            tblPatient.setItems(FXCollections.observableArrayList(olpatient));
                    
                    } catch (Exception ex) {
            Logger.getLogger(PateintController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblPatient.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("PatientID"));
        tblPatient.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Address"));
        tblPatient.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Name"));
        loadpatients();
        
        
    }    

    @FXML
    private void Save_patientOnAct(ActionEvent event) {
        Upadd();
        String pats=txtpatientID.getText();
         boolean pat=pats.matches("^\\D{1}\\d{3}$");
         if (pat==false) {
            Alert a=new Alert(Alert.AlertType.ERROR,"Invalid PatientID",ButtonType.OK);
            a.show();
            txtpatientID.requestFocus();
         }else{
               if (addnew) {
            savePatient();
        }else{
             Update();
         }
         }
           
       
    }

    @FXML
    private void Remove_PatientOnAct(ActionEvent event) {
        String Pid=tblPatient.getSelectionModel().getSelectedItem().getPatientID();
        
            try {
                boolean result=patientbo.DeletePatient(Pid);
                if(result==true){
                    Alert a=new Alert(Alert.AlertType.INFORMATION, "Patient is deleted", ButtonType.OK);
                    a.show();
                    loadpatients();
                }
                else{
                    Alert a=new Alert(Alert.AlertType.INFORMATION, "Patient is not deleted", ButtonType.OK);
                    a.show();
                }
            } catch (Exception ex) {
                Logger.getLogger(PateintController.class.getName()).log(Level.SEVERE, null, ex);
            }
    
        
    }

   
    @FXML
    private void navitoHome_Onclick(MouseEvent event) throws IOException {
         if (event.getSource() instanceof ImageView) {
            ImageView img = (ImageView) event.getSource();
            Parent root = null;
            switch (img.getId()) {
                case "Imgpatient":
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
    public  void savePatient(){
        
        PatientDTO patient=new PatientDTO(txtpatientID.getText(), PatientAddress.getText(), txtPatientname.getText());
        
        
        try {
            boolean result=patientbo.savePatient(patient);
            if (result) {
                Alert a=new Alert(Alert.AlertType.INFORMATION, "Patient is saved succefuly", ButtonType.OK);
                a.show();
                txtpatientID.setText("");
                txtPatientname.setText("");
                PatientAddress.setText("");
                loadpatients();
            }
        } catch (Exception ex) {
            Logger.getLogger(PateintController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void Update(){
         PatientDTO patient=new PatientDTO(txtpatientID.getText(), PatientAddress.getText(), txtPatientname.getText());
        try {
            boolean result=patientbo.updatePatient(patient);
            if(result){
                Alert a=new Alert(Alert.AlertType.INFORMATION, "Patient is Updated succefuly", ButtonType.OK);
                a.show();
                loadpatients();
            }else{
                Alert a=new Alert(Alert.AlertType.INFORMATION, "Patient is not updated", ButtonType.OK);
                a.show();
            }
        } catch (Exception ex) {
            Logger.getLogger(PateintController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void Upadd(){
        try {
            ArrayList<PatientDTO>patient=patientbo.getAllPatient();
            for (PatientDTO patientDTO : patient) {
                String pid= patientDTO.getPatientID();
                if(txtpatientID.getText().equals(pid)){
                    System.out.println("hyty");
                   addnew=false;
                    break;
                }
                
            }
           
        } catch (Exception ex) {            
            Logger.getLogger(PateintController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void tblClick(MouseEvent event) {
        PatientTM patient=tblPatient.getSelectionModel().getSelectedItem();
        txtpatientID.setText(patient.getPatientID());
        txtPatientname.setText(patient.getName());
        PatientAddress.setText(patient.getAddress());
    }

    @FXML
    private void btnaddnewact(ActionEvent event) {
        addnew=true;
        txtpatientID.requestFocus();
    }
     
    }

        
        
