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
    private final static int czas_odswierzania = 20;
    private final static int czas_animacji = 3;
    private final static int obecnyKomunikat = 0;
    private final static int liczbaKomunikatow = 0;
    
    private double postep;

    private static final int czasZmianyKomunikatu = 20;
    private boolean zaladowano_okno = false;
    private boolean zamknieto_okno = false;
            
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
    private ScrollPane ScrollPane;
    @FXML
    private Button btnST_29;
    @FXML
    private Button btnST_49;
    @FXML
    private Button btnST_50;
    @FXML
    private Button btnST_46;
    @FXML
    private Button btnST_59;
    @FXML
    private Button btnST_68;
    @FXML
    private Button btnST_57;
    @FXML
    private Button btnST_66;
    @FXML
    private Button btnST_51;
    @FXML
    private Button btnST_04;
    @FXML
    private Button btnST_27;
    @FXML
    private Button btnST_23;
    @FXML
    private Button btnST_25;
    @FXML
    private Button btnST_47;
    @FXML
    private Button btnST_55;
    @FXML
    private Button btnST_48;
    @FXML
    private Button btnST_52;
    @FXML
    private Button btnST_53;
    @FXML
    private Button btnST_63;
    @FXML
    private Button btnST_58;
    @FXML
    private Button btnST_67;
    @FXML
    private Button btnST_69;
    @FXML
    private Button btnST_61;
    @FXML
    private Button btnST_70;
    @FXML
    private Button btnST_65;
    @FXML
    private Button btnST_60;
    @FXML
    private Clock clock;
  

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
        istniejace_maszyny = IstniejaceMaszynySkoczow.LadujNazwyMaszyn();
        
        for(Maszyna masfor:istniejace_maszyny)
        {
            przypiszKolory(masfor);
        }
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
           }

                    });//END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_
       

        //aktualizuj();
       
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
                    Platform.runLater(new Runnable()
                    {
                            @Override
                            public void run()
                            {
                                progressBar.setProgress(postep);
                                
                            }

                    });
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
                
                Platform.runLater(new Runnable()
                    {
                            @Override
                            public void run()
                            {
                                progressBar.setProgress(postep);
                                
                            }

                    });
                i++;
                try
                {
                if(alertSQL.isShowing())
                {
                    Runnable task = new Runnable()

                    {
                            public void run()

                            {
                                    alertSQL.hide();
                            }
                    };
                    
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

                String sql = " SELECT data_g, wtrysk, wybrak, postoj_n, awaria_m, awaria_f, przezbrajanie, proby_tech, brak_zaop, postoj FROM techniplast.cykle_szybkie where maszyna = '"+masfor.getNazwa()+"' and data_g > DATE_SUB(NOW(), INTERVAL 10 MINUTE) and lp=(SELECT max(lp) FROM techniplast.cykle_szybkie where maszyna = '"+masfor.getNazwa()+"' and data_g > DATE_SUB(NOW(), INTERVAL 5 MINUTE));";
                
                ResultSet rs_a;
                PreparedStatement pst_a;
                //System.out.println(sql);
                pst_a = conn.prepareStatement(sql);
                
                rs_a = pst_a.executeQuery(sql);
                if(rs_a != null)
                {
                while(rs_a.next()) {
                    brakDanych=false;
                    data = rs_a.getString("data_g").substring(10,19);

                    wtrysk += Integer.parseInt(rs_a.getString("wtrysk"));
                    wybrak += Integer.parseInt(rs_a.getString("wybrak"));
                    brak_operatora += (int)Float.parseFloat(rs_a.getString("postoj_n"));
                    awaria_m += (int)Float.parseFloat(rs_a.getString("awaria_m"));
                    awaria_f += (int)Float.parseFloat(rs_a.getString("awaria_f"));
                    przezbrajanie += (int)Float.parseFloat(rs_a.getString("przezbrajanie"));
                    proby_tech += (int)Float.parseFloat(rs_a.getString("proby_tech"));
                    brak_zaop += (int)Float.parseFloat(rs_a.getString("brak_zaop"));
                    postoj += (int)Float.parseFloat(rs_a.getString("postoj"));
                }
                
                }
                //conn.close();
                if(brakDanych)
                {
                masfor.setStatus(Maszyna.Stan.BRAK_DANYCH);
                }
                 else
                    {
                        if(wybrak > 0)
                        {
                             masfor.setStatus(Maszyna.Stan.WYBRAK);
                        }
                        if(wtrysk > 0)
                        {
                             masfor.setStatus(Maszyna.Stan.PRACA);
                        }
                        if(brak_operatora > 0)
                        {
                             masfor.setStatus(Maszyna.Stan.POSTOJ_NIEUZASADNIONY);
                        }
                        if(awaria_m > 0)
                        {
                             masfor.setStatus(Maszyna.Stan.AWARIA_M);
                        }
                        if(awaria_f > 0)
                        {
                             masfor.setStatus(Maszyna.Stan.AWARIA_F);
                        }
                        if( przezbrajanie > 0)
                        {
                             masfor.setStatus(Maszyna.Stan.PRZEZBRAJANIE);
                        }
                        if(proby_tech > 0)
                        {
                             masfor.setStatus(Maszyna.Stan.PROBY);
                        }
                        if(brak_zaop > 0)
                        {
                             masfor.setStatus(Maszyna.Stan.BRAK_ZAOPATRZENIA);
                        }
                        if(postoj > 0)
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
                            Runnable task = new Runnable()

                            {
                            public void run()

                            {
                                    alertSQL.show();
                            }
                            };
                        
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
            case "ST_23":
                btnST_23.setStyle(mas.getStyl());
                break;
            case "ST_25":
                btnST_25.setStyle(mas.getStyl());
                break;
            case "ST_47":
                btnST_47.setStyle(mas.getStyl());
                break;
            case "ST_55":
                btnST_55.setStyle(mas.getStyl());
                break;
            case "ST_48":
                btnST_48.setStyle(mas.getStyl());
                break;
            case "ST_52":
                btnST_52.setStyle(mas.getStyl());
                break;
            case "ST_53":
                btnST_53.setStyle(mas.getStyl());
                break;
            case "ST_04":
                btnST_04.setStyle(mas.getStyl());
                break;
            case "ST_27":
                btnST_27.setStyle(mas.getStyl());
                break;
            case "ST_57":
                btnST_57.setStyle(mas.getStyl());
                break;
            case "ST_59":
                btnST_59.setStyle(mas.getStyl());
                break;
            case "ST_68":
                btnST_68.setStyle(mas.getStyl());
                break;
            case "ST_50":
                btnST_50.setStyle(mas.getStyl());
                break;
            case "ST_49":
                btnST_49.setStyle(mas.getStyl());
                break;
            case "ST_46":
                btnST_46.setStyle(mas.getStyl());
                break;
            case "ST_51":
                btnST_51.setStyle(mas.getStyl());
                break;
            case "ST_29":
                btnST_29.setStyle(mas.getStyl());
                break;
            case "ST_66":
                btnST_66.setStyle(mas.getStyl());
                break;
            default:
                break;    
        }
        //maszyny nie podłączone
        btnST_63.setStyle(Maszyna.getStylNiePodlaczone());
        btnST_67.setStyle(Maszyna.getStylNiePodlaczone());
        btnST_70.setStyle(Maszyna.getStylNiePodlaczone());
        btnST_65.setStyle(Maszyna.getStylNiePodlaczone());
        btnST_58.setStyle(Maszyna.getStylNiePodlaczone());
        btnST_69.setStyle(Maszyna.getStylNiePodlaczone());
        btnST_61.setStyle(Maszyna.getStylNiePodlaczone());
        btnST_60.setStyle(Maszyna.getStylNiePodlaczone());
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
            finally
            {
                zamknieto_okno = true;
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
               Runnable task = new Runnable()

                {
                public void run()

                {
                    alertInternet.show();
                }
                };
               
           }

           return false;


        } 
    }

    public boolean getZaladowanoOkno()
    {
        return zaladowano_okno;
    }
    public boolean getZamknietoOkno()
    {
        return zamknieto_okno;
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


  
    
    
}
