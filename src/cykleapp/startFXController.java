/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cykleapp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSpinner;
import insidefx.undecorator.Undecorator;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class startFXController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private JFXButton BTNanalizujWtryskarke;
    @FXML
    private JFXButton BTNpodgladHali;
    @FXML
    private JFXButton BTNmodułAutomatyka;
    @FXML
    private JFXButton BTNanalizujWiele;
    
    private DeskaUstronFXController deskaUstronController;
    private DeskaSkoczowFXController deskaSkoczowController;
    private cykleFXController wtryskarkaController;
    private AutomatykFXController automatykController;
    private obciazenieFXController wtryskarkiController;
    
    private double rozdzielczoscEkranuX = 0;
    private double rozdzielczoscEkranuY = 0;
    @FXML
    private JFXSpinner progressDeska;
    @FXML
    private JFXSpinner progressWtryskarki;
    @FXML
    private JFXSpinner progressWtryskarka;
    @FXML
    private JFXSpinner progressAutomatyk;
    @FXML
    private MenuButton BTNmenu;
    @FXML
    private MenuItem pomocMIT;
    @FXML
    private MenuItem oProgramieMIT;
    @FXML
    private RadioMenuItem rMenuSkoczow;
    @FXML
    private ToggleGroup serwer;
    @FXML
    private RadioMenuItem rMenuUstron;
    
    public static Stage stageDeskaUstron;
    public static Stage stageDeskaSkoczow;
    public static Stage stageAnalizujWtryskarke;
    public static Stage stageAnalizujWiele;
    public static Stage stageModulAutomatyka;
    @FXML
    private MenuButton CBserwer;
    @FXML
    private JFXComboBox<String> CBmiejsce;


    /**
     * Initializes the controller class.
     */
      boolean jestInternet()
    {
        try 
        {
            URL url = new URL("http://www.google.com");

            URLConnection connection = url.openConnection();
            connection.connect();
            return true;

        }catch (IOException e){
  
           Alert alert = new Alert(Alert.AlertType.ERROR);
           Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
           alertStage.getIcons().add(new Image(this.getClass().getResource("img/icon48.png").toString()));
           alert.setTitle("TECHNIPLAST Analizator pracy wtryskarek: Błąd");
           alert.setHeaderText("Brak połączenia internetowego");
           alert.setContentText("Przywróć połączenie internetowe i spróbuj ponownie");

           alert.showAndWait();
           System.err.println("Błąd: " + e.getMessage());
           return false;


        } 
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mysqlconnect.setMiejsce(mysqlconnect.miejsce.SKOCZOW);
        CBserwer.setVisible(false);
        progressDeska.setVisible(false);
        progressWtryskarka.setVisible(false);
        progressWtryskarki.setVisible(false);
        progressAutomatyk.setVisible(false);
        
        CBmiejsce.getItems().add("Skoczów");
        CBmiejsce.getItems().add("Ustroń");
        
         switch (CykleApp.rootPref.get("START_MIEJSCE", "")) {
            case "SKOCZOW":
                CBmiejsce.setValue("Skoczów");
                        break;
            case "USTRON":
                CBmiejsce.setValue("Ustroń");
                break;
            default:
                CBmiejsce.setValue("Skoczów");
                break;
         }

        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        rozdzielczoscEkranuX = screenSize.getWidth();
        rozdzielczoscEkranuY = screenSize.getHeight();

        Timeline updater = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    if(deskaUstronController != null && deskaUstronController.getZaladowanoOkno())
                    {
                           progressDeska.setVisible(false);   
                           //BTNpodgladHali.setDisable(false);
                           //deskaUstronController = null;
                    }
                    if(deskaSkoczowController != null && deskaSkoczowController.getZaladowanoOkno())
                    {
                           progressDeska.setVisible(false);   
                           //BTNpodgladHali.setDisable(false);
                           //deskaSkoczowController = null;
                    }
                    if(deskaSkoczowController != null && deskaSkoczowController.getZamknietoOkno())
                    {
                           BTNpodgladHali.setDisable(false);
                           deskaSkoczowController = null;
                    }
                    if(deskaUstronController != null && deskaUstronController.getZamknietoOkno())
                    {
                           BTNpodgladHali.setDisable(false);
                           deskaUstronController = null;
                    }
                    if(wtryskarkaController != null && wtryskarkaController.getZaladowanoOkno())
                    {
                           progressWtryskarka.setVisible(false);   
                           BTNanalizujWtryskarke.setDisable(false);
                           wtryskarkaController = null;
                    }
                    if(wtryskarkiController != null && wtryskarkiController.getZaladowanoOkno())
                    {
                           progressWtryskarki.setVisible(false);   
                           BTNanalizujWiele.setDisable(false);
                           wtryskarkiController = null;
                           
                    }
                    if(automatykController != null && automatykController.getZaladowanoOkno())
                    {
                           progressAutomatyk.setVisible(false);   
                           BTNmodułAutomatyka.setDisable(false);
                           automatykController = null;
                    }
                }
            }));
        
        updater.setCycleCount(Timeline.INDEFINITE);
        updater.play();
    }    

    @FXML
    private void BTNanalizujWtryskarkeMouseExited(MouseEvent event) {
        BTNanalizujWtryskarke.setEffect(null);
    }

    @FXML
    private void BTNanalizujWtryskarkeMouseEntered(MouseEvent event) {
        BTNanalizujWtryskarke.setEffect(new Glow(0.5));
    }

    @FXML
    private void BTNanalizujWtryskarkeAction(ActionEvent event) {
        if(jestInternet())          
          {
            Runnable taskAnalizujWtryskarke = new Runnable()
            {
                public void run(){
                try {
                    progressWtryskarka.setVisible(true);   
                    BTNanalizujWtryskarke.setDisable(true);
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cykleFX.fxml"));
                    Parent rootAnalizujWtryskarke = (Parent) fxmlLoader.load();
                    wtryskarkaController = fxmlLoader.getController();

                    cykleFXController wtryskarkaControllerLocal = wtryskarkaController;
  
                    Scene sceneAnalizujWtryskarke = new Scene(rootAnalizujWtryskarke);

                    Platform.runLater(new Runnable(){

                    @Override
                    public void run(){
                        stageAnalizujWtryskarke = new Stage();

                        //stage.getScene().getStylesheets().add(getClass().getResource("styleAutomatyk.css").toExternalForm());
                        //stage.initModality(Modality.APPLICATION_MODAL);


                        //stage.initStyle(StageStyle.DECORATED);
                        stageAnalizujWtryskarke.setTitle("Analiza wtryskarki");
                        stageAnalizujWtryskarke.getIcons().add(new Image(getClass().getResourceAsStream("img/icon48.png")));
                        stageAnalizujWtryskarke.setScene(sceneAnalizujWtryskarke);  

                        String css = this.getClass().getResource("styleWtryskarka.css").toExternalForm(); 
                        sceneAnalizujWtryskarke.getStylesheets().clear();
                        sceneAnalizujWtryskarke.getStylesheets().add(css);
                        //stage.setOnHidden(e -> controller.shutdown());

                        //Undecorator undecorator = new Undecorator(stage, (Region) rootDeska);
                        //undecorator.getStylesheets().add(css);
                        //undecorator.getStylesheets().add("skin/undecorator.css");
                        stageAnalizujWtryskarke.setOnCloseRequest(new EventHandler<WindowEvent>(){

                            @Override
                            public void handle(WindowEvent event) {

                                //event.consume();
                                wtryskarkaControllerLocal.shutdown();
                                //undecorator.setFadeOutTransition();


                            }
                        });
                        //stage.initStyle(StageStyle.TRANSPARENT);
                         stageAnalizujWtryskarke.initStyle(StageStyle.DECORATED);


                        //Scene scene = new Scene(undecorator);
                        //undecorator.installAccelerators(scene);
                        //undecorator.setFadeInTransition();
                        //scene.setFill(Color.TRANSPARENT);

                        stageAnalizujWtryskarke.setScene(sceneAnalizujWtryskarke);

                        stageAnalizujWtryskarke.setWidth(1280);
                        stageAnalizujWtryskarke.setHeight(800);

                        stageAnalizujWtryskarke.setMinWidth(800);
                        stageAnalizujWtryskarke.setMinHeight(600);

                        stageAnalizujWtryskarke.show();
                         }

                    });//END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_




                }
            catch (IOException e) {
                System.err.println("Błąd: " + e.getMessage());
                progressWtryskarka.setVisible(false);  
                BTNanalizujWtryskarke.setDisable(false);
            }
            }//END RUN END RUN END RUN END RUN END RUN END RUN END RUN END RUN END RUN END RUN

            };

            Thread wczytywanieDanych = new Thread(taskAnalizujWtryskarke);
            wczytywanieDanych.setName("Analiza wtryskarki-uruchamianie okna");
            wczytywanieDanych.setDaemon(true);
            wczytywanieDanych.start();
        }
    }

    @FXML
    private void BTNpodgladHaliMouseExited(MouseEvent event) {
        BTNpodgladHali.setEffect(null);
    }

    @FXML
    private void BTNpodgladHaliMouseEntered(MouseEvent event) {
         BTNpodgladHali.setEffect(new Glow(0.5));
    }

    @FXML
    private void BTNpodgladHaliAction(ActionEvent event) {
        if(jestInternet())
        {
            if(CBmiejsce.getValue().equals("Ustroń"))
            {
            Runnable taskDeskaRozdzielcza = new Runnable()
            {
                public void run(){
            try {
                progressDeska.setVisible(true);
                BTNpodgladHali.setDisable(true);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("deskaUstronFX.fxml"));
                Parent rootDeska = (Parent) fxmlLoader.load();
                deskaUstronController = fxmlLoader.getController();
                DeskaUstronFXController deskaControllerLocal = deskaUstronController;
                //marker okna
                Scene sceneDeska = new Scene(rootDeska);

                Platform.runLater(new Runnable(){

                        @Override
                        public void run(){

                stageDeskaUstron = new Stage();



                //stage.getScene().getStylesheets().add(getClass().getResource("styleAutomatyk.css").toExternalForm());
                //stage.initModality(Modality.APPLICATION_MODAL);


                //stage.initStyle(StageStyle.DECORATED);
                stageDeskaUstron.setTitle("Hala produkcyjna-Ustroń");
                stageDeskaUstron.getIcons().add(new Image(getClass().getResourceAsStream("img/icon48.png")));
                stageDeskaUstron.setScene(sceneDeska);  

                String css = this.getClass().getResource("styleDeskaUstron.css").toExternalForm(); 
                sceneDeska.getStylesheets().clear();
                sceneDeska.getStylesheets().add(css);


                //stage.setOnHidden(e -> controller.shutdown());

                //Undecorator undecorator = new Undecorator(stage, (Region) rootDeska);
                 //undecorator.getStylesheets().add(css);
                 //undecorator.getStylesheets().add("skin/undecoratorDeska.css");
                stageDeskaUstron.setOnCloseRequest(new EventHandler<WindowEvent>(){

                    @Override
                    public void handle(WindowEvent event) {

                        //event.consume();
                        deskaControllerLocal.shutdown();
                        //undecorator.setFadeOutTransition();


                    }
                });

                //stage.initStyle(StageStyle.TRANSPARENT);
                stageDeskaUstron.initStyle(StageStyle.DECORATED);


                //Scene scene = new Scene(undecorator);
                //undecorator.installAccelerators(scene);
                //undecorator.setFadeInTransition();
                //scene.setFill(Color.TRANSPARENT);
                stageDeskaUstron.show();
                stageDeskaUstron.setScene(sceneDeska);



                //stage.setWidth(1900);
                //stage.setHeight(1060);

                if(rozdzielczoscEkranuX != 0 && rozdzielczoscEkranuY != 0)
                {
                    stageDeskaUstron.setWidth(rozdzielczoscEkranuX*9/10);
                    stageDeskaUstron.setHeight(rozdzielczoscEkranuY*9/10);
                }
                else
                {
                    stageDeskaUstron.setWidth(800);
                    stageDeskaUstron.setHeight(600);
                }
                stageDeskaUstron.setMinWidth(800);
                stageDeskaUstron.setMinHeight(600);
                // to samo co w pliku Controler.java przy borderPane

                //stage.show();
                Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                stageDeskaUstron.setX((primScreenBounds.getWidth() - stageDeskaUstron.getWidth()) / 2);
                stageDeskaUstron.setY((primScreenBounds.getHeight() - stageDeskaUstron.getHeight()) / 2);


                }

                        });//END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_


            }
            catch (IOException e) {
                System.err.println("Błąd: " + e.getMessage());
                BTNpodgladHaliAction(new ActionEvent());
                progressDeska.setVisible(false);
                BTNpodgladHali.setDisable(false);
            }
        }//END RUN END RUN END RUN END RUN END RUN END RUN END RUN END RUN END RUN END RUN

            };

            Thread wczytywanieDanychUstron = new Thread(taskDeskaRozdzielcza);
            wczytywanieDanychUstron.setName("Analiza obciazenia-uruchamianie okna");
            wczytywanieDanychUstron.setDaemon(true);
            wczytywanieDanychUstron.start();            
        }//koniec if Ustroń
            
        //HALA SKOCZÓW
        if(CBmiejsce.getValue().equals("Skoczów"))
            {
            Runnable taskDeskaRozdzielczaSkoczow = new Runnable()
            {
                public void run(){
            try {
                progressDeska.setVisible(true);
                BTNpodgladHali.setDisable(true);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("deskaSkoczowFX.fxml"));
                Parent rootDeska = (Parent) fxmlLoader.load();
                deskaSkoczowController = fxmlLoader.getController();
                DeskaSkoczowFXController deskaControllerLocal = deskaSkoczowController;
                //marker okna
                Scene sceneDeska = new Scene(rootDeska);

                Platform.runLater(new Runnable(){

                        @Override
                        public void run(){

                stageDeskaSkoczow = new Stage();



                //stage.getScene().getStylesheets().add(getClass().getResource("styleAutomatyk.css").toExternalForm());
                //stage.initModality(Modality.APPLICATION_MODAL);


                //stage.initStyle(StageStyle.DECORATED);
                stageDeskaSkoczow.setTitle("Hala produkcyjna-Skoczów");
                stageDeskaSkoczow.getIcons().add(new Image(getClass().getResourceAsStream("img/icon48.png")));
                stageDeskaSkoczow.setScene(sceneDeska);  

                String css = this.getClass().getResource("styleDeskaSkoczow.css").toExternalForm(); 
                sceneDeska.getStylesheets().clear();
                sceneDeska.getStylesheets().add(css);


                //stage.setOnHidden(e -> controller.shutdown());

                //Undecorator undecorator = new Undecorator(stage, (Region) rootDeska);
                 //undecorator.getStylesheets().add(css);
                 //undecorator.getStylesheets().add("skin/undecoratorDeska.css");
                stageDeskaSkoczow.setOnCloseRequest(new EventHandler<WindowEvent>(){

                    @Override
                    public void handle(WindowEvent event) {

                        //event.consume();
                        deskaControllerLocal.shutdown();
                        //undecorator.setFadeOutTransition();


                    }
                });

                //stage.initStyle(StageStyle.TRANSPARENT);
                stageDeskaSkoczow.initStyle(StageStyle.DECORATED);


                //Scene scene = new Scene(undecorator);
                //undecorator.installAccelerators(scene);
                //undecorator.setFadeInTransition();
                //scene.setFill(Color.TRANSPARENT);
                stageDeskaSkoczow.show();
                stageDeskaSkoczow.setScene(sceneDeska);



                //stage.setWidth(1900);
                //stage.setHeight(1060);

                if(rozdzielczoscEkranuX != 0 && rozdzielczoscEkranuY != 0)
                {
                    stageDeskaSkoczow.setWidth(rozdzielczoscEkranuX*9/10);
                    stageDeskaSkoczow.setHeight(rozdzielczoscEkranuY*9/10);
                }
                else
                {
                    stageDeskaSkoczow.setWidth(800);
                    stageDeskaSkoczow.setHeight(600);
                }
                stageDeskaSkoczow.setMinWidth(800);
                stageDeskaSkoczow.setMinHeight(600);
                // to samo co w pliku Controler.java przy borderPane

                //stage.show();
                Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                stageDeskaSkoczow.setX((primScreenBounds.getWidth() - stageDeskaSkoczow.getWidth()) / 2);
                stageDeskaSkoczow.setY((primScreenBounds.getHeight() - stageDeskaSkoczow.getHeight()) / 2);


                }

                        });//END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_


            }
            catch (IOException e) {
                System.err.println("Błąd: " + e.getMessage());
                BTNpodgladHaliAction(new ActionEvent());
                progressDeska.setVisible(false);
                BTNpodgladHali.setDisable(false);
            }
        }//END RUN END RUN END RUN END RUN END RUN END RUN END RUN END RUN END RUN END RUN

            };

            Thread wczytywanieDanychSkoczow = new Thread(taskDeskaRozdzielczaSkoczow);
            wczytywanieDanychSkoczow.setName("Analiza obciazenia-uruchamianie okna");
            wczytywanieDanychSkoczow.setDaemon(true);
            wczytywanieDanychSkoczow.start();            
        }//koniec if Skoczów
        }//koniec if jest internet
    }

    @FXML
    private void BTNmodułAutomatykaMouseExited(MouseEvent event) {
        BTNmodułAutomatyka.setEffect(null);
    }

    @FXML
    private void BTNmodułAutomatykaMouseEntered(MouseEvent event) {
        BTNmodułAutomatyka.setEffect(new Glow(0.5));
    }

    @FXML
    private void BTNmodułAutomatykaAction(ActionEvent event) {
         if(jestInternet())
        {
            Runnable taskModulAutomatyka = new Runnable()
            {
                public void run(){
        try {
            progressAutomatyk.setVisible(true);   
            BTNmodułAutomatyka.setDisable(true);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("automatykFX.fxml"));
            Parent rootAutomatyk = (Parent) fxmlLoader.load();
            automatykController = fxmlLoader.getController();
            AutomatykFXController automatykControllerLocal = automatykController;
            Scene sceneAutomatyk = new Scene(rootAutomatyk);
            
            
             Platform.runLater(new Runnable(){

                    @Override
                    public void run(){
            stageModulAutomatyka = new Stage();
            
            //stage.getScene().getStylesheets().add(getClass().getResource("styleAutomatyk.css").toExternalForm());
            //stage.initModality(Modality.APPLICATION_MODAL);
            
            
            //stage.initStyle(StageStyle.DECORATED);
            stageModulAutomatyka.setTitle("TECHNIPLAST Analizator pracy wtryskarek-moduł automatyka");
            stageModulAutomatyka.getIcons().add(new Image(getClass().getResourceAsStream("img/icon48.png")));
            stageModulAutomatyka.setScene(sceneAutomatyk);  
            
            String css = this.getClass().getResource("styleAutomatyk.css").toExternalForm(); 
            sceneAutomatyk.getStylesheets().clear();
            sceneAutomatyk.getStylesheets().add(css);
            //stage.setOnHidden(e -> controller.shutdown());
            
            //Undecorator undecorator = new Undecorator(stage, (Region) rootAutomatyk);
             //undecorator.getStylesheets().add(css);
             //undecorator.getStylesheets().add("skin/undecoratorAutoma.css");
            stageModulAutomatyka.setOnCloseRequest(new EventHandler<WindowEvent>(){
            
                @Override
                public void handle(WindowEvent event) {

                    //event.consume();
                    automatykControllerLocal.shutdown();
                    //undecorator.setFadeOutTransition();
                    

                }
            });
            //stage.initStyle(StageStyle.TRANSPARENT);
             stageModulAutomatyka.initStyle(StageStyle.DECORATED);
             
            
            //Scene scene = new Scene(undecorator);
            //undecorator.installAccelerators(scene);
            //undecorator.setFadeInTransition();
            //scene.setFill(Color.TRANSPARENT);
   
            stageModulAutomatyka.setScene(sceneAutomatyk);
            stageModulAutomatyka.setWidth(1280);
            stageModulAutomatyka.setHeight(900);
            
            stageModulAutomatyka.setMinWidth(1024);
            stageModulAutomatyka.setMinHeight(768);
            
            stageModulAutomatyka.show();
            
             }

                    });//END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_


     
        }
        catch (IOException e) {
            System.err.println("Błąd: " + e.getMessage());
            progressAutomatyk.setVisible(false);  
            BTNmodułAutomatyka.setDisable(false);
        }
                      }//END RUN END RUN END RUN END RUN END RUN END RUN END RUN END RUN END RUN END RUN

            };

            Thread wczytywanieDanych = new Thread(taskModulAutomatyka);
            wczytywanieDanych.setName("Analiza obciazenia-uruchamianie okna");
            wczytywanieDanych.setDaemon(true);
            wczytywanieDanych.start();
        }
        
    }

    @FXML
    private void BTNanalizujWieleMouseExited(MouseEvent event) {
        BTNanalizujWiele.setEffect(null);
    }

    @FXML
    private void BTNanalizujWieleMouseEntered(MouseEvent event) {
        BTNanalizujWiele.setEffect(new Glow(0.5));
    }

    @FXML
    private void BTNanalizujWieleAction(ActionEvent event) {
        if(jestInternet())          
          {
            Runnable taskAnalizujWiele = new Runnable()
            {
                public void run(){
                try {
                    progressWtryskarki.setVisible(true);   
                    BTNanalizujWiele.setDisable(true);
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("obciazenieFX.fxml"));
                    Parent rootObciazenieWtryskarek = (Parent) fxmlLoader.load();
                    wtryskarkiController = fxmlLoader.getController();
                    obciazenieFXController wtryskarkiControllerLocal = wtryskarkiController;
                    Scene sceneObciazenieWtryskarek = new Scene(rootObciazenieWtryskarek);

                    Platform.runLater(new Runnable(){

                    @Override
                    public void run(){
                        stageAnalizujWiele = new Stage();

                        //stage.getScene().getStylesheets().add(getClass().getResource("styleAutomatyk.css").toExternalForm());
                        //stage.initModality(Modality.APPLICATION_MODAL);


                        //stage.initStyle(StageStyle.DECORATED);
                        stageAnalizujWiele.setTitle("Analiza obciążenia wtryskarek");
                        stageAnalizujWiele.getIcons().add(new Image(getClass().getResourceAsStream("img/icon48.png")));
                        stageAnalizujWiele.setScene(sceneObciazenieWtryskarek);  

                        String css = this.getClass().getResource("styleObciazenie.css").toExternalForm(); 
                        sceneObciazenieWtryskarek.getStylesheets().clear();
                        sceneObciazenieWtryskarek.getStylesheets().add(css);
                        //stage.setOnHidden(e -> controller.shutdown());

                        //Undecorator undecorator = new Undecorator(stage, (Region) rootDeska);
                        //undecorator.getStylesheets().add(css);
                        //undecorator.getStylesheets().add("skin/undecoratorObciazenie.css");
                        stageAnalizujWiele.setOnCloseRequest(new EventHandler<WindowEvent>(){

                            @Override
                            public void handle(WindowEvent event) {

                                //event.consume();
                                wtryskarkiControllerLocal.shutdown();
                                //undecorator.setFadeOutTransition();


                            }
                        });
                        //stage.initStyle(StageStyle.TRANSPARENT);
                         stageAnalizujWiele.initStyle(StageStyle.DECORATED);


                        //Scene scene = new Scene(undecorator);
                        //undecorator.installAccelerators(scene);
                        //undecorator.setFadeInTransition();
                        //scene.setFill(Color.TRANSPARENT);

                        stageAnalizujWiele.setScene(sceneObciazenieWtryskarek);

                        stageAnalizujWiele.setWidth(1280);
                        stageAnalizujWiele.setHeight(800);

                        stageAnalizujWiele.setMinWidth(800);
                        stageAnalizujWiele.setMinHeight(600);

                        stageAnalizujWiele.show();
                         }

                    });//END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_




                }
            catch (IOException e) {
                System.err.println("Błąd: " + e.getMessage());
                progressWtryskarki.setVisible(false);   
                BTNanalizujWiele.setDisable(false);
            }
            }//END RUN END RUN END RUN END RUN END RUN END RUN END RUN END RUN END RUN END RUN

            };

            Thread wczytywanieDanych = new Thread(taskAnalizujWiele);
            wczytywanieDanych.setName("Analiza obciazenia-uruchamianie okna");
            wczytywanieDanych.setDaemon(true);
            wczytywanieDanych.start();
        }
    }

    @FXML
    private void pomocMITaction(ActionEvent event) {
        if (Desktop.isDesktopSupported()) {
    try {
        File myFile = new File("pomoc.pdf");
        Desktop.getDesktop().open(myFile);
    } catch (Exception ex) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
           Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
           alertStage.getIcons().add(new Image(this.getClass().getResource("img/icon48.png").toString()));
           alert.setTitle("TECHNIPLAST Analizator pracy wtryskarek: Błąd");
           alert.setHeaderText("Brak programu obsługującego pliki PDF,\nlub plik pomocy jest niedostępny");
           alert.setContentText("Pomoc nie zostanie uruchomiona");

           alert.show();
    }
}
    }

    @FXML
    private void oProgramieMITaction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
         Image logo = new Image(getClass().getResource("img/logo_male.png").toExternalForm());
         Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
         ImageView imageView = new ImageView(logo);
         alert.setGraphic(imageView);
         alertStage.getIcons().add(new Image(this.getClass().getResource("img/icon48.png").toString()));
         alert.setTitle("TECHNIPLAST Analizator pracy wtryskarek");
         
         alert.setHeaderText("System analizy pracy wtryskarek\nwersja 2020");
         
         
         VBox vbox = new VBox();
         Label lbl = new Label("Marek Madyda\n");
         Hyperlink email_gmail = new Hyperlink("marekmadyda@gmail.com");
         email_gmail.setOnAction(new EventHandler<ActionEvent>()
	{
		@Override
		public void handle(ActionEvent event)
		{
			try
			{
                            Desktop desktop;
				if (Desktop.isDesktopSupported() && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
                                  URI mailto = new URI("mailto:marekmadyda@gmail.com?subject=Analizator_pracy_wtryskarek");
                                  desktop.mail(mailto);
                                } else {
                                  // TODO fallback to some Runtime.exec(..) voodoo?
                                  throw new RuntimeException("desktop doesn't support mailto; mail is dead anyway ;)");
                                }
			}
			catch (Exception e)
			{
				System.err.println("Błąd: " + e.getMessage());
			}
		}
	});
         Hyperlink email_techniplast = new Hyperlink("marek_madyda@techniplast.pl");
         email_techniplast.setOnAction(new EventHandler<ActionEvent>()
	{
		@Override
		public void handle(ActionEvent event)
		{
			try
			{
                            Desktop desktop;
				if (Desktop.isDesktopSupported() && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
                                  URI mailto = new URI("mailto:marek_madyda@techniplast.pl?subject=Analizator_pracy_wtryskarek");
                                  desktop.mail(mailto);
                                } else {
                                  // TODO fallback to some Runtime.exec(..) voodoo?
                                  throw new RuntimeException("desktop doesn't support mailto; mail is dead anyway ;)");
                                }
			}
			catch (Exception e)
			{
				System.err.println("Błąd: " + e.getMessage());
			}
		}
	});
         Label lb_tel = new Label("tel: +48 514 626 091\n");
         vbox .getChildren().addAll( lbl, new HBox(email_gmail,email_techniplast),lb_tel);
         alert.getDialogPane().contentProperty().set( vbox  );


          alert.showAndWait();
    }

    @FXML
    private void rMenuSkoczowAction(ActionEvent event) {
        mysqlconnect.setMiejsce(mysqlconnect.miejsce.SKOCZOW);
    }

    @FXML
    private void rMenuUstronAction(ActionEvent event) {
        mysqlconnect.setMiejsce(mysqlconnect.miejsce.USTRON);
    }

    @FXML
    private void CBmiejsceAction(ActionEvent event) {
        if(CBmiejsce.getValue().equals("Ustroń"))
        {
            CykleApp.rootPref.put("START_MIEJSCE", "USTRON");
        }
        if(CBmiejsce.getValue().equals("Skoczów"))
        {
            CykleApp.rootPref.put("START_MIEJSCE", "SKOCZOW");
        }
    }
    
}
