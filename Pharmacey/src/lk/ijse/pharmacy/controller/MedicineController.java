/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.pharmacy.busines.custom.MedicineBO;
import lk.ijse.pharmacy.busines.custom.ReceiptBO;
import lk.ijse.pharmacy.business.BOFactory;
import lk.ijse.pharmacy.dto.MedicineDTO;
import lk.ijse.pharmacy.view.util.tblModel.MedicineTM;

/**
 * FXML Controller class
 *
 * @author mcs
 */
public class MedicineController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TableView<MedicineTM> tblmedicine;
    @FXML
    private JFXTextField txtmedicine;
    @FXML
    private JFXTextField txtDescription;
    @FXML
    private JFXTextField txtQty;
    @FXML
    private JFXComboBox<String> cmbApproval;
    MedicineBO medicinebo;
    @FXML
    private ImageView ImgMedicine;
    ReceiptBO receiptbo;
    boolean addnew =true;
    public MedicineController() {
        this.receiptbo = (ReceiptBO) BOFactory.getInstance().getBO(BOFactory.BOType.Receipt);
        this.medicinebo = (MedicineBO) BOFactory.getInstance().getBO(BOFactory.BOType.Medicine);
    }

    public void LoadMedicine() {
        try {
            ArrayList<MedicineDTO> medicineDTO = medicinebo.getAllMedicine();
            ArrayList<MedicineTM> medicinetm = new ArrayList<>();
            for (MedicineDTO medicine : medicineDTO) {
                MedicineTM medicineTM = new MedicineTM(medicine.getMID(), medicine.getDescription(), medicine.getQty(), medicine.getApproval());
                medicinetm.add(medicineTM);
            }
            tblmedicine.setItems(FXCollections.observableArrayList(medicinetm));
        } catch (Exception ex) {
            Logger.getLogger(MedicineController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblmedicine.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("MID"));
        tblmedicine.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Description"));
        tblmedicine.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Qty"));
        tblmedicine.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Approval"));
        LoadMedicine();
        addCmb();
    }

    @FXML
    private void save_onAct(ActionEvent event) {
       UpdateAdd();
        String mid = txtmedicine.getText();
        boolean medi = mid.matches("^\\D{1}\\d{3}$");
        if (medi == false) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Invalid medicine ID", ButtonType.OK);
            a.show();
            txtmedicine.requestFocus();
        } else {
            if (addnew) {
               
              saveMedicine();
            }else{
                updateMedicine();
            }
        }

    }

    @FXML
    private void Remove_OnAct(ActionEvent event) {
        String mid = tblmedicine.getSelectionModel().getSelectedItem().getMID();
        try {
            boolean result = medicinebo.deleteMedicine(mid);
            if (result) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Deleted", ButtonType.OK);
                a.show();
                LoadMedicine();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, " Not Deleted", ButtonType.OK);
                a.show();
            }
        } catch (Exception ex) {
            Logger.getLogger(MedicineController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addCmb() {
        ArrayList<String> cmb = new ArrayList<>();
        cmb.add("Yes");
        cmb.add("No");
        cmbApproval.setItems(FXCollections.observableArrayList(cmb));

    }

    public void saveMedicine() {
        int Qty = Integer.parseInt(txtQty.getText());
        MedicineDTO medicinedto = new MedicineDTO(txtmedicine.getText(), txtDescription.getText(), Qty, cmbApproval.getValue());
        try {
            boolean result = medicinebo.saveMedicine(medicinedto);
            if (result) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Medicine is saved succefully", ButtonType.OK);
                a.show();
                txtDescription.setText("");
              //  txtQty.setText("");
                txtmedicine.setText("");
                cmbApproval.setValue("");
                txtmedicine.requestFocus();
                LoadMedicine();
            } else {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Medicine is not saved", ButtonType.OK);
                a.show();
            }
        } catch (Exception ex) {
            Logger.getLogger(MedicineController.class.getName()).log(Level.SEVERE, null, ex);
        }
             updateqty();
    }

    public void updateMedicine() {
        int Qty = Integer.parseInt(txtQty.getText());
        System.out.println(Qty);
        MedicineDTO medicinedto = new MedicineDTO(txtmedicine.getText(), txtDescription.getText(), Qty, cmbApproval.getValue());
        try {
            boolean result = medicinebo.updateMedicine(medicinedto);
            if (result) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Medicine is Updated succefully", ButtonType.OK);
                a.show();
                LoadMedicine();
            } else {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Medicine is not Updated", ButtonType.OK);
                a.show();
            }
           
        } catch (Exception ex) {
            Logger.getLogger(MedicineController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }

    public void UpdateAdd() {
        try {
            ArrayList<MedicineDTO> medi = medicinebo.getAllMedicine();
            for (MedicineDTO medicineDTO : medi) {
                String mid = medicineDTO.getMID();
                if (txtmedicine.getText().equals(mid)) {
                    System.out.println("dwcds");
                    addnew=false;
                    break;
                }
            }
        
        } catch (Exception ex) {
            Logger.getLogger(MedicineController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Img_ONclick(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView img = (ImageView) event.getSource();
            Parent root = null;
            switch (img.getId()) {
                case "ImgMedicine":
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
    private void tbleOn_click(MouseEvent event) {
        MedicineTM medicine = tblmedicine.getSelectionModel().getSelectedItem();

        txtmedicine.setText(medicine.getMID());
        txtDescription.setText(medicine.getDescription());
        txtQty.setText(String.valueOf(medicine.getQty()));
        cmbApproval.setValue(medicine.getApproval());

    }

    public  void updateqty(){
        int qty=Integer.parseInt(txtQty.getText());
        try {
            boolean result=medicinebo.updateQty(qty, txtmedicine.getText());
        } catch (Exception ex) {
            Logger.getLogger(MedicineController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addnewOnact(ActionEvent event) {
        addnew=true;
        txtmedicine.requestFocus();
    }

}
