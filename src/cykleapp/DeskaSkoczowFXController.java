/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cykleapp;

import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextArea;
import eu.hansolo.medusa.Clock;
import eu.hansolo.medusa.Gauge;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class DeskaSkoczowFXController implements Initializable {

    private Connection conn;
    private ResultSet rs;
    private PreparedStatement pst;
    private ArrayList<Maszyna> istniejace_maszyny;
    
    private String data;
    private int wtrysk;
    private int wybrak;
    private int brak_operatora;
    private int awaria_m;
    private int awaria_f;
    private int przezbrajanie;
    private int proby_tech;
    private int brak_zaop;
    private int postoj;
    private Timeline updater;
    private Timeline animacja;
    private Alert alertSQL;
    private Alert alertInternet;
    private static int czas_odswierzania = 20;
    private static int czas_animacji = 3;
    private static int obecnyKomunikat = 0;
    private static int liczbaKomunikatow = 0;
    
    private double postep;

    private static final int czasZmianyKomunikatu = 20;
    private boolean zaladowano_okno = false;
            
    @FXML
    private BorderPane borderPane;
    @FXML
    private Label Lstatus;
    @FXML
    private Color x4;
    @FXML
    private Font x3;
    @FXML
    private ImageView imgHala;
    @FXML
    private JFXProgressBar progressBar;
    @FXML
    private Button btnLEG_PRACA;
    @FXML
    private Label labLEG_PRACA;
    @FXML
    private Button btnLEG_WYBRAK;
    @FXML
    private Label labLEG_WYBRAK;
    @FXML
    private Button btnLEG_AWARIA_MASZYNY;
    @FXML
    private Label labLEG_AWARIA_MASZYNY;
    @FXML
    private Button btnLEG_AWARIA_FORMY;
    @FXML
    private Label labLEG_AWARIA_FORMY;
    @FXML
    private Button btnLEG_PRZEZBRAJANIE;
    @FXML
    private Label labLEG_PRZEZBRAJANIE;
    @FXML
    private Button btnLEG_PROBY;
    @FXML
    private Label labLEG_PROBY;
    @FXML
    private Button btnLEG_BRAK_Z;
    @FXML
    private Label labLEG_BRAK_Z;
    @FXML
    private Button btnLEG_POSTOJ_P;
    @FXML
    private Label labLEG_POSTOJ_P;
    @FXML
    private Button btnLEG_POSTOJ_N;
    @FXML
    private Label labLEG_POSTOJ_N;
    @FXML
    private Button btnLEG_BRAK_DANYCH;
    @FXML
    private Label labLEG_BRAK_DANYCH;
    @FXML
    private Circle cien_zegar;
    @FXML
    private Clock zegar;
    @FXML
    private Gauge wskaznik;
    @FXML
    private Circle cien_zegar1;
    @FXML
    private JFXTextArea komunikaty;
    @FXML
    private Circle cien_zegar111;
    @FXML
    private Circle cien_zegar11;

    @FXML
    private Button btnARBURG_34;
    
    
    @FXML
    private ScrollPane ScrollPane;
    @FXML
    private Button btnARBURG_35;
    @FXML
    private Button btnARBURG_33;
    @FXML
    private Button btnARBURG_32;
    @FXML
    private Button btnARBURG_31;
    @FXML
    private Button btnARBURG_36;
    @FXML
    private Button btnHAITIAN_39;
    @FXML
    private Button btnHAITIAN_41;
    @FXML
    private Button btnHAITIAN_42;
  

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ScrollPane.setPannable(true);
        borderPane.setMinWidth(640);
        borderPane.setMinHeight(480);
        System.out.println("jestem w initialize DeskaSkoczowFXController");
        conn = mysqlconnect.ConnecrDb();

        Platform.runLater(new Runnable(){

        @Override
        public void run(){
        //wczytywanie komunikatów

        // koniec wczytywania komunikatów
        kolorLegenda();
        progressBar.setVisible(false);
        System.out.println("jestem w initialize DeskaSkoczowFXController 2");
        
        alertSQL = new Alert(Alert.AlertType.ERROR);
        Stage alertSQLStage = (Stage) alertSQL.getDialogPane().getScene().getWindow();
        alertSQLStage.getIcons().add(new Image(this.getClass().getResource("img/icon48.png").toString()));
        alertSQL.setTitle("TECHNIPLAST Analizator pracy wtryskarek: Błąd");
        alertSQL.setHeaderText("Problem bazy danych SQL");
        alertSQL.setContentText("Nie można wczytać danych");
        
        System.out.println("jestem w initialize DeskaSkoczowFXController 3");
        
        alertInternet = new Alert(Alert.AlertType.WARNING);
        Stage alertInternetStage = (Stage) alertInternet.getDialogPane().getScene().getWindow();
        alertInternetStage.getIcons().add(new Image(this.getClass().getResource("img/icon48.png").toString()));
        alertInternet.setTitle("TECHNIPLAST Analizator pracy wtryskarek: Błąd");
        alertInternet.setHeaderText("Brak połączenia internetowego");
        alertInternet.setContentText("Przywróć połączenie internetowe");
        istniejace_maszyny = IstniejaceMaszynyUstron.LadujNazwyMaszyn();
           }

                    });//END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_
       

        aktualizuj();
       
        //new Thread(task).start();
        animacja = new Timeline(new KeyFrame(Duration.seconds(czas_animacji), new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    progressBar.setVisible(false);
                    Lstatus.setText("TECHNIPLAST");

                }
            }));
        updater = new Timeline(new KeyFrame(Duration.seconds(czas_odswierzania), new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    
                    //System.out.println("wczytuje...");
                    Lstatus.setText("Ładowanie danych...");
                    progressBar.setVisible(true);
                    postep = 0;
                    progressBar.setProgress(postep);
                    Runnable task = new Runnable()

                    {
                            public void run()

                            {
                                    aktualizuj();
                            }
                    };
                    Thread backgroundThread = new Thread(task);

                    backgroundThread.setDaemon(true);

                    backgroundThread.start();

                    animacja.play();
                    

                }
            }));
        
        updater.setCycleCount(Timeline.INDEFINITE);
        updater.play();
        

        
         
        zaladowano_okno = true;
    }

    

    public void aktualizuj()
    {
        if(jestInternet())
        {
            System.out.println("Aktualizowanie...");
            int i =1;
            for(Maszyna masfor:istniejace_maszyny)
            {
                postep = i/(new Double(istniejace_maszyny.size()));
                
                progressBar.setProgress(postep);
                i++;
                try
                {
                if(alertSQL.isShowing())
                {
                    alertSQL.hide();
                }
                
                
                Boolean brakDanych = true;
                wtrysk =0;
                wybrak =0;
                brak_operatora =0;
                awaria_m =0;
                awaria_f =0;
                przezbrajanie =0;
                proby_tech =0;
                brak_zaop =0;
                postoj =0;

                String sql = " SELECT data_g, wtrysk, wybrak, postoj_n, awaria_m, awaria_f, przezbrajanie, proby_tech, brak_zaop, postoj FROM techniplast.cykle_szybkie where maszyna = '"+masfor.getNazwa()+"' and data_g > DATE_SUB(NOW(), INTERVAL 10 MINUTE) and lp=(SELECT max(lp) FROM techniplast.cykle_szybkie where maszyna = '"+masfor.getNazwa()+"' and data_g > DATE_SUB(NOW(), INTERVAL 2 MINUTE));";

                //System.out.println(sql);
                pst = conn.prepareStatement(sql);

                rs = pst.executeQuery(sql);
                if(rs != null)
                {
                while(rs.next()) {
                    brakDanych=false;
                    data = rs.getString("data_g").substring(10,19);

                    wtrysk += Integer.parseInt(rs.getString("wtrysk"));
                    wybrak += Integer.parseInt(rs.getString("wybrak"));
                    brak_operatora += (int)Float.parseFloat(rs.getString("postoj_n"));
                    awaria_m += (int)Float.parseFloat(rs.getString("awaria_m"));
                    awaria_f += (int)Float.parseFloat(rs.getString("awaria_f"));
                    przezbrajanie += (int)Float.parseFloat(rs.getString("przezbrajanie"));
                    proby_tech += (int)Float.parseFloat(rs.getString("proby_tech"));
                    brak_zaop += (int)Float.parseFloat(rs.getString("brak_zaop"));
                    postoj += (int)Float.parseFloat(rs.getString("postoj"));
                    

                }
                }
                //conn.close();
                if(brakDanych)
                {
                masfor.setStatus(Maszyna.Stan.BRAK_DANYCH);
                }
                 else
                    {
                        if(wtrysk > 0)
                        {
                             masfor.setStatus(Maszyna.Stan.PRACA);
                        }
                        else if(wybrak > 0)
                        {
                             masfor.setStatus(Maszyna.Stan.WYBRAK);
                        }
                        else if(brak_operatora > 0)
                        {
                             masfor.setStatus(Maszyna.Stan.POSTOJ_NIEUZASADNIONY);
                        }
                        else if(awaria_m > 0)
                        {
                             masfor.setStatus(Maszyna.Stan.AWARIA_M);
                        }
                        else if(awaria_f > 0)
                        {
                             masfor.setStatus(Maszyna.Stan.AWARIA_F);
                        }
                        else if( przezbrajanie > 0)
                        {
                             masfor.setStatus(Maszyna.Stan.PRZEZBRAJANIE);
                        }
                        else if(proby_tech > 0)
                        {
                             masfor.setStatus(Maszyna.Stan.PROBY);
                        }
                        else if(brak_zaop > 0)
                        {
                             masfor.setStatus(Maszyna.Stan.BRAK_ZAOPATRZENIA);
                        }
                        else if(postoj > 0)
                        {
                             masfor.setStatus(Maszyna.Stan.POSTOJ_PLANOWANY);
                        }

                    }
                    ////////////////////////////////////////////////////////////
                    //Przypisanie kolorów kwadratom

                    //conn.close();
                }
                catch(NumberFormatException | SQLException ex)
                {
                        if(!alertSQL.isShowing())
                        {
                        alertSQL.show();
                        }
                    
                        System.err.println("Błąd: " + ex.getMessage());
                }
                finally
                {
                    
                    Platform.runLater(new Runnable()
                    {
                            @Override
                            public void run()
                            {
                                przypiszKolory(masfor);
                                
                            }

                    });

                    
                }
                

            }
            
        }
 
    }
    //DODAWANIE WTRYSKAREK DO LAYOUT DODAWANIE WTRYSKAREK DO LAYOUT DODAWANIE WTRYSKAREK DO LAYOUT DODAWANIE WTRYSKAREK DO LAYOUT
    public void przypiszKolory(Maszyna mas)
    {
        
        
        switch (mas.getNazwa()) {
            case "ARBURG_34":
                btnARBURG_34.setStyle(mas.getStyl());
                break;
              
            default:
                break;
        }
    }
    public void shutdown(){
        //System.out.println("zamykanie okna");
        if(updater != null)
        {
            try {
                if(conn != null)
                {
                conn.close();
                }
                if(updater != null)
                {
                updater.stop();
                }
               

            } catch (SQLException ex) {
                System.err.println("Błąd: " + ex.getMessage());
            }
        }
    }
    public void kolorLegenda()
    {
        btnLEG_PRACA.setStyle(Maszyna.STYL_BPRACA);

        btnLEG_WYBRAK.setStyle(Maszyna.STYL_WYBRAK);

        btnLEG_AWARIA_MASZYNY.setStyle(Maszyna.STYL_AWARIA_MASZYNY);

        btnLEG_AWARIA_FORMY.setStyle(Maszyna.STYL_AWARIA_FORMY);

        btnLEG_PRZEZBRAJANIE.setStyle(Maszyna.STYL_PRZEZBRAJANIE);

        btnLEG_PROBY.setStyle(Maszyna.STYL_PROBY_TECHNOLOGICZNE);

        btnLEG_BRAK_Z.setStyle(Maszyna.STYL_BRAK_ZAOPATRZENIA);

        btnLEG_POSTOJ_P.setStyle(Maszyna.STYL_POSTOJ_PLANOWANY);

        btnLEG_POSTOJ_N.setStyle(Maszyna.STYL_POSTOJ_NIEUZASADNIONY);

        btnLEG_BRAK_DANYCH.setStyle(Maszyna.STYL_BRAK_DANYCH);
        

    }
     boolean jestInternet()
    {
        try 
        {
            URL url = new URL("http://www.google.com");

            URLConnection connection = url.openConnection();
            connection.connect();
            alertInternet.hide();
            return true;

        }catch (IOException e){
           System.err.println("Błąd: " + e.getMessage());
           if(!alertInternet.isShowing())
           {
               alertInternet.show();
           }

           return false;


        } 
    }

    public boolean getZaladowanoOkno()
    {
        return zaladowano_okno;
    }

    @FXML
    private void oknoMouseClickedAction(MouseEvent event) {
        if(event.getClickCount() == 2)
        {
            if(!startFXController.stageDeskaSkoczow.isFullScreen())
            {
            startFXController.stageDeskaSkoczow.setFullScreen(true);
            }
            else
            {
                startFXController.stageDeskaSkoczow.setFullScreen(false);
            }
        }
    }

    @FXML
    private void komunikatyLoad(MouseEvent event) {
    }

  
    
    
}
