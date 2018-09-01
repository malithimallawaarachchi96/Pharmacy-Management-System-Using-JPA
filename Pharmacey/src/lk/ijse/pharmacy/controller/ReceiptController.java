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
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
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
import javafx.scene.control.Label;
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
import lk.ijse.pharmacy.busines.custom.ReceiptBO;
import lk.ijse.pharmacy.business.BOFactory;
import lk.ijse.pharmacy.db.JPAUtil;
import lk.ijse.pharmacy.dto.MedicineDTO;
import lk.ijse.pharmacy.dto.PatientDTO;
import lk.ijse.pharmacy.dto.PrescriptionDTO;
import lk.ijse.pharmacy.dto.ReceiptDTO;
import lk.ijse.pharmacy.view.util.tblModel.ReceiptTM;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author mcs
 */
public class ReceiptController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField txtReceiptId;
    @FXML
    private JFXTextField txtUnitprice;
    @FXML
    private JFXTextField txtQty;
    @FXML
    private TableView<ReceiptTM> tblReceipt;
    @FXML
    private Label lblgrandTotal;

    ReceiptBO receiptbo;
    @FXML
    private ImageView ImageReceipt;
    PatientBO patientbo;
    
    @FXML
    private JFXComboBox<String> cmbpid;
    @FXML
    private JFXComboBox<String> cmbmid;
    @FXML
    private JFXComboBox<String> cmbpatientID;
    MedicineBO medicinebo;
    PrescriptionDetailBO presdetailbo;
    @FXML
    private JFXDatePicker txtdate;
    private BigDecimal grandtotal=new BigDecimal(0);
    PrescriptionBO  presbo;
    @FXML
    private Button btngenerate;
    public ReceiptController() {
        this.presbo = (PrescriptionBO) BOFactory.getInstance().getBO(BOFactory.BOType.PRESCRIPTION);
        this.presdetailbo = (PrescriptionDetailBO) BOFactory.getInstance().getBO(BOFactory.BOType.PrescriptionDetail);
        this.medicinebo = (MedicineBO) BOFactory.getInstance().getBO(BOFactory.BOType.Medicine);
        this.patientbo = (PatientBO) BOFactory.getInstance().getBO(BOFactory.BOType.Patient);
        this.receiptbo = (ReceiptBO) BOFactory.getInstance().getBO(BOFactory.BOType.Receipt);
    }
   
     public void loadcmbPatient(){
        try {
            ArrayList<PatientDTO>patientdto=patientbo.getAllPatient();
            ArrayList<String>patient=new ArrayList<>();
            for (PatientDTO pa : patientdto) {
                patient.add(pa.getPatientID());
            }
           cmbpatientID.setItems(FXCollections.observableArrayList(patient));
        } catch (Exception ex) {
            Logger.getLogger(ReceiptController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     }
     public void loadcmbMID(){
        try {
            ArrayList<MedicineDTO>Medidto=medicinebo.getAllMedicine();
            ArrayList<String>allmedi=new ArrayList<>();
            for (MedicineDTO medi : Medidto) {
                allmedi.add(medi.getMID());
            }
            cmbmid.setItems(FXCollections.observableArrayList(allmedi));
        } catch (Exception ex) {
            Logger.getLogger(ReceiptController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     }
     public  void loadPID(){
        try {
            ArrayList<PrescriptionDTO>allprescri=presbo.getAllPrescriptions();
            ArrayList<String>olpresdetail=new ArrayList<>();
            for (PrescriptionDTO presdetaildTO : allprescri) {
               olpresdetail.add(presdetaildTO.getPID());
            }
            cmbpid.setItems(FXCollections.observableArrayList(olpresdetail));
        } catch (Exception ex) {
            Logger.getLogger(ReceiptController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblReceipt.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("ReceiptID"));
        tblReceipt.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("PatientID"));
        tblReceipt.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("PID"));
        tblReceipt.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("MID"));
        tblReceipt.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Unitprice"));
        tblReceipt.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Qty"));
        tblReceipt.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("Date"));
        tblReceipt.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("Total"));
        
        loadcmbPatient();
        loadcmbMID();
        loadPID();
    }    

    @FXML
    private void ReceeiptSave_onAct(ActionEvent event) {
        
       int Qty=Integer.parseInt(txtQty.getText());
       BigDecimal UnitPrice=new BigDecimal(txtUnitprice.getText());
       BigDecimal Total=UnitPrice.multiply(new  BigDecimal(Qty));
       LocalDate localDate=txtdate.getValue();
       Date receiptdate=Date.valueOf(localDate);
       ArrayList<ReceiptTM>recipttm=new ArrayList<>();
       ReceiptTM receipt=new ReceiptTM(txtReceiptId.getText(), cmbpatientID.getValue(), cmbpid.getValue(), cmbmid.getValue(), UnitPrice, Total, Qty,receiptdate);
       recipttm.add(receipt);
       tblReceipt.setItems(FXCollections.observableArrayList(recipttm));
       grandtotal=grandtotal.add(receipt.getTotal());
       lblgrandTotal.setText(grandtotal+"");
       Save();
      
       
    
    }

    @FXML
    private void Receiptremove_onAct(ActionEvent event) {
        try {
            String select=tblReceipt.getSelectionModel().getSelectedItem().getReceiptID();
            boolean result=receiptbo.DeleteReceipt(select);
            ReceiptTM recei=tblReceipt.getSelectionModel().getSelectedItem();
            tblReceipt.getItems().removeAll(recei);
            if (result) {
                new Alert(Alert.AlertType.INFORMATION, " receipt is deleted", ButtonType.OK).show();
                
            }else{
                new Alert(Alert.AlertType.ERROR, " Couldn't delete the receipt", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(ReceiptController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void NavitoHome(MouseEvent event) throws IOException {
         if (event.getSource() instanceof ImageView) {
            ImageView img = (ImageView) event.getSource();
            Parent root = null;
            switch (img.getId()) {
                case "ImageReceipt":
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
    public  void Save(){
        try {
            BigDecimal UnitPrice=new BigDecimal(txtUnitprice.getText());
            int Qty=Integer.parseInt(txtQty.getText());
            ReceiptDTO rece;
            LocalDate localDate=txtdate.getValue();
            Date receiptdate=Date.valueOf(localDate);
            rece = new ReceiptDTO(txtReceiptId.getText(),cmbpatientID.getValue(),cmbpid.getValue(),cmbmid.getValue(),UnitPrice,Qty,java.sql.Date.valueOf(localDate));
            boolean result=receiptbo.saveRecipt(rece);
            if (result) {
               new Alert(Alert.AlertType.INFORMATION, "receipt is saved ", ButtonType.OK).show();

            }else{
                 new Alert(Alert.AlertType.INFORMATION, "receipt is not saved properly", ButtonType.OK).show();

            }
        } catch (Exception ex) {
            Logger.getLogger(ReceiptController.class.getName()).log(Level.SEVERE, null, ex);
        }
       updatereceipt();
    }
   public  void updatereceipt(){

        try {
            boolean result=receiptbo.UpdateReceiptQty(txtReceiptId.getText());
        } catch (Exception ex) {
            Logger.getLogger(ReceiptController.class.getName()).log(Level.SEVERE, null, ex);
        }
   }

    @FXML
    private void GenerateOnAct(ActionEvent event) {
        InputStream resourseInputStream=getClass().getResourceAsStream("/lk/ijse/pharmacy/report/Jasperreceipt.jasper");
        HashMap map=new HashMap();
        map.put("ID",txtReceiptId.getText());
        try {
            JasperPrint fillReport=JasperFillManager.fillReport(resourseInputStream, map, (Connection) JPAUtil.getEntityManagerFactory().createEntityManager());
            JasperViewer.viewReport(fillReport);
           // JasperPrintManager.printReport(resourseInputStream, true);
            JasperExportManager.exportReportToPdf(fillReport);
        } catch (Exception ex) {
            Logger.getLogger(ReceiptController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    
}
