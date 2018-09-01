/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.pharmacy.view.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author mcs
 */
public class MainPharmacyController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private ImageView ImgPatient;
    @FXML
    private ImageView ImgPrescription;
    @FXML
    private ImageView ImgMedicine;
    @FXML
    private ImageView ImgReceipt;
    @FXML
    private Label lblpharmacy;
    @FXML
    private Label lblPrescription;
    @FXML
    private Label lblMedicine;
    @FXML
    private Label lblReceipt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }    

    @FXML
    private void Mouse_ExitAnimation(MouseEvent event) {
         if (event.getSource() instanceof ImageView){
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play(); 
            
            icon.setEffect(null);
            lblpharmacy.setText("");
            lblPrescription.setText("");
            lblMedicine.setText("");
            lblReceipt.setText("");
    }
    }

    @FXML
    private void Mouse_EnteredAnimation(MouseEvent event) {
         if (event.getSource() instanceof ImageView){
            ImageView icon = (ImageView) event.getSource();
            
            switch(icon.getId()){
                case "ImgPatient":
                    lblpharmacy.setText("Patient-Click to enter");
                    
                    break;
                case "ImgPrescription":
                    lblPrescription.setText("Prescription-Click to add");
                    
                    break;
                case "ImgMedicine":
                    lblMedicine.setText("Medicine-click here");
                    
                    break;
                case "ImgReceipt":
                    lblReceipt.setText("Receipt-click to add");
                    
                    break;
    }
             ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play(); 
            
            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
}
    
    }

    @FXML
    private void navigate(MouseEvent event) throws IOException {
         if (event.getSource() instanceof ImageView){
            ImageView icon = (ImageView) event.getSource();
            
            Parent root = null;
            
            switch(icon.getId()){
                case "ImgPatient":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/pharmacy/view/Pateint.fxml"));
                    break;
                case "ImgPrescription":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/pharmacy/view/Prescription.fxml"));
                    break;
                case "ImgMedicine":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/pharmacy/view/Medicine.fxml"));
                    break;
                case "ImgReceipt":
                    root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/pharmacy/view/Receipt.fxml"));
                    break;
            }
            
            if (root != null){
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();
                
                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();
                
            }
        }
    }   
    }
    

