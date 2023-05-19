/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cykleapp;

import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXSpinner;
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
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
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

    private Connection conn_wtryskarki;
    private Connection conn_kolowy;

    
    
    private ArrayList<Maszyna> istniejace_maszyny;
    private String  nazwaMiejsca = "SKOCZOW";
    
    private String data;
    private int wtrysk;
    private int wybrak;
    private int postoj_n;
    private int awaria_m;
    private int awaria_f;
    private int przezbrajanie;
    private int susz_m;
    private int proby_tech;
    private int brak_zaop;
    private int przerwa_p;
    private int brak_oper;
    private int postoj;
    
    private String kol_data;
    private int kol_wtrysk;
    private int kol_wybrak;
    private int kol_postoj_n;
    private int kol_awaria_m;
    private int kol_awaria_f;
    private int kol_przezbrajanie;
    private int kol_susz_m;
    private int kol_proby_tech;
    private int kol_brak_zaop;
    private int kol_przerwa_p;
    private int kol_brak_oper;
    private int kol_postoj;
    private int kol_calkowity_czas;
    
    private float kol_max;
    
    ObservableList<PieChart.Data> daneWykresKolowy;
    
    private LocalDateTime localDateTime_od;
    private LocalDateTime localDateTime_do;
    
    private Timeline wtryskarkiUpdater;
    private Timeline kolowyUpdater;
    private Timeline animacja;
    private Alert alertSQL;
    private Alert alertInternet;
    private final static int czas_odswierzania_wtryskarek = 20;
    private final static int czas_odswierzania_kolowy = 120;
    private final static int czas_animacji = 2;
    private final static int obecnyKomunikat = 0;
    private final static int liczbaKomunikatow = 0;
    
    private double postep;
    
    final static DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("MM-dd HH:mm");
    NumberFormat procentFormat = NumberFormat.getPercentInstance(Locale.US);
    
    String str_dane_od = "";
    String str_dane_do = "";

    private static final int czasZmianyKomunikatu = 20;
    private boolean zaladowano_okno = false;
    private boolean zamknieto_okno = false;
            
    @FXML
    private BorderPane borderPane;
    @FXML
    private ImageView imgHala;
    @FXML
    private JFXSpinner progressBar;
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
    private Button btnLEG_BRAK_OPER;
    @FXML
    private Label labLEG_BRAK_OPER;
    
    @FXML
    private ScrollPane ScrollPane;
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
    private Button btnST_27;
    @FXML
    private Button btnST_55;
    @FXML
    private Button btnST_52;
    @FXML
    private Button btnST_63;
    @FXML
    private Button btnST_58;
    @FXML
    private Button btnST_67;
    @FXML
    private Button btnST_69;
    @FXML
    private Button btnST_70;
    @FXML
    private Button btnST_65;
    @FXML
    private Button btnST_60;
    @FXML
    private Button btnST_73;
    @FXML
    private Button btnST_71;
    @FXML
    private Button btnST_72;
    @FXML
    private Button btnST_74;
    @FXML
    private Button btnST_75;
    @FXML
    private Button btnST_76;
    @FXML
    private Button btnST_77;
    @FXML
    private Button btnST_78;
    @FXML
    private Button btnLEG_SUSZ_M;
    @FXML
    private Label labLEG_SUSZ_M;
    @FXML
    private Button btnLEG_PRZERWA_P;
    @FXML
    private Label labLEG_PRZERWA_P;
    @FXML
    private PieChart halaWykresKolowy;
    
    public static String calculateTime(long seconds) {
        long hours = TimeUnit.SECONDS.toHours(seconds);

        long minute = (TimeUnit.SECONDS.toMinutes(seconds) -
                      (TimeUnit.SECONDS.toHours(seconds)* 60));

        long second = (TimeUnit.SECONDS.toSeconds(seconds) -
                      (TimeUnit.SECONDS.toMinutes(seconds) *60));

    
    return String.format("%02d", hours)+":" +String.format("%02d", minute)+":" + String.format("%02d", second);
    }
  

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

        conn_wtryskarki = mysqlconnect.ConnecrDb();
        conn_kolowy = mysqlconnect.ConnecrDb();
 

        Platform.runLater(new Runnable(){

        @Override
        public void run(){
        //wczytywanie komunikatów

        // koniec wczytywania komunikatów
        kolorLegenda();
        halaWykresKolowy.setTitle("");
        progressBar.setVisible(true);
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
                        aktualizuj_kolowy();
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

                }
            }));
        //Wtryskarki updater
        wtryskarkiUpdater = new Timeline(new KeyFrame(Duration.seconds(czas_odswierzania_wtryskarek), new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    
                    //System.out.println("wczytuje...");
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

                    
                    

                }
            }));  
        wtryskarkiUpdater.setCycleCount(Timeline.INDEFINITE);
        wtryskarkiUpdater.play();
        
        //kolowy updater
        kolowyUpdater = new Timeline(new KeyFrame(Duration.seconds(czas_odswierzania_kolowy), new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    
                    //System.out.println("wczytuje...");
                    //progressBar.setVisible(true);
                    //postep = 0;
                    Platform.runLater(new Runnable()
                    {
                            @Override
                            public void run()
                            {
                                //progressBar.setProgress(postep);
                                
                            }

                    });
                    Runnable task = new Runnable()

                    {
                            public void run()

                            {
                                    aktualizuj_kolowy();
                            }
                    };
                    Thread backgroundThread = new Thread(task);

                    backgroundThread.setDaemon(true);

                    backgroundThread.start();

                    
                    

                }
            }));  
        kolowyUpdater.setCycleCount(Timeline.INDEFINITE);
        kolowyUpdater.play();
  
        zaladowano_okno = true;
    }

    

    public void aktualizuj()
    {
        if(jestInternet())
        {
            System.out.println("Aktualizowanie wtryskarek...");
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
                postoj_n =0;
                awaria_m =0;
                awaria_f =0;
                przezbrajanie =0;
                susz_m = 0;
                proby_tech =0;
                brak_zaop =0;
                przerwa_p = 0;
                brak_oper =0;
                postoj =0;
                
                

                String sql = " SELECT data_g, wtrysk, wybrak, postoj_n, awaria_m, awaria_f, przezbrajanie, susz_m ,proby_tech, brak_zaop, przerwa, brak_oper, postoj FROM techniplast.cykle_szybkie where maszyna = '"+masfor.getNazwa()+"' and data_g > DATE_SUB(NOW(), INTERVAL 5 MINUTE) and lp=(SELECT max(lp) FROM techniplast.cykle_szybkie where maszyna = '"+masfor.getNazwa()+"' and data_g > DATE_SUB(NOW(), INTERVAL 5 MINUTE));";
                //String sql = " SELECT data_g, wtrysk, wybrak, postoj_n, awaria_m, awaria_f, przezbrajanie, proby_tech, brak_zaop, postoj FROM techniplast.cykle_szybkie where maszyna = '"+masfor.getNazwa()+"' ORDER BY lp DESC LIMIT 1;";
                
                ResultSet rs_a;
                PreparedStatement pst_a;
                //System.out.println(sql);
                pst_a = conn_wtryskarki.prepareStatement(sql);
                
                rs_a = pst_a.executeQuery(sql);
                if(rs_a != null)
                {
                while(rs_a.next()) {
                    brakDanych=false;
                    data = rs_a.getString("data_g").substring(10,19);

                    try
                    {
                    wtrysk += Integer.parseInt(rs_a.getString("wtrysk"));
                    }
                    catch(Exception ex)
                    {
                        wtrysk += 0;
                    }
                    try
                    {
                    wybrak += Integer.parseInt(rs_a.getString("wybrak"));
                    }
                    catch(Exception ex)
                    {
                        wybrak += 0;
                    }
                    try
                    {
                    postoj_n += (int)Float.parseFloat(rs_a.getString("postoj_n"));
                    }
                    catch(Exception ex)
                    {
                        postoj_n += 0;
                    }
                    try
                    {
                    awaria_m += (int)Float.parseFloat(rs_a.getString("awaria_m"));
                    }
                    catch(Exception ex)
                    {
                        awaria_m += 0;
                    }
                    try
                    {
                    awaria_f += (int)Float.parseFloat(rs_a.getString("awaria_f"));
                    }
                    catch(Exception ex)
                    {
                        awaria_f += 0;
                    }
                    try
                    {
                    przezbrajanie += (int)Float.parseFloat(rs_a.getString("przezbrajanie"));
                    }
                    catch(Exception ex)
                    {
                        przezbrajanie += 0;
                    }
                     try
                    {
                    susz_m += (int)Float.parseFloat(rs_a.getString("susz_m"));
                    }
                    catch(Exception ex)
                    {
                        susz_m += 0;
                    }
                    try
                    {
                    proby_tech += (int)Float.parseFloat(rs_a.getString("proby_tech"));
                    }
                    catch(Exception ex)
                    {
                        proby_tech += 0;
                    }
                    try
                    {
                        brak_zaop += (int)Float.parseFloat(rs_a.getString("brak_zaop"));
                    }
                    catch(Exception ex)
                    {
                        brak_zaop += 0;
                    }
                    try
                    {
                        przerwa_p += (int)Float.parseFloat(rs_a.getString("przerwa"));
                    }
                    catch(Exception ex)
                    {
                        przerwa_p += 0;
                    }
                    try
                    {
                    brak_oper += (int)Float.parseFloat(rs_a.getString("brak_oper"));
                    }
                    catch(Exception ex)
                    {
                        brak_oper += 0;
                    }
                    try
                    {
                    postoj += (int)Float.parseFloat(rs_a.getString("postoj"));
                    }
                    catch(Exception ex)
                    {
                        postoj += 0;
                    }
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
                        if(postoj_n > 0)
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
                        if( susz_m > 0)
                        {
                             masfor.setStatus(Maszyna.Stan.SUSZ_M);
                        }
                        if(proby_tech > 0)
                        {
                             masfor.setStatus(Maszyna.Stan.PROBY);
                        }
                        if(brak_zaop > 0)
                        {
                             masfor.setStatus(Maszyna.Stan.BRAK_ZAOPATRZENIA);
                        }
                        if(przerwa_p > 0)
                        {
                             masfor.setStatus(Maszyna.Stan.PRZERWA_P);
                        }
                        if(brak_oper > 0)
                        {
                             masfor.setStatus(Maszyna.Stan.BRAK_OPERATORA);
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
            animacja.play();
            
        }
 
    }
    //DODAWANIE WTRYSKAREK DO LAYOUT DODAWANIE WTRYSKAREK DO LAYOUT DODAWANIE WTRYSKAREK DO LAYOUT DODAWANIE WTRYSKAREK DO LAYOUT
    
    public void aktualizuj_kolowy()
    {
        if(jestInternet())
        {
            System.out.println("Aktualizowanie wykresu kolowego");
            try
            {
                kol_data = "";
                kol_wtrysk = 0;
                kol_wybrak = 0;
                kol_postoj_n = 0;
                kol_awaria_m = 0;
                kol_awaria_f =0;
                kol_przezbrajanie = 0;
                kol_susz_m = 0;
                kol_proby_tech = 0;
                kol_brak_zaop = 0;
                kol_przerwa_p = 0;
                kol_brak_oper = 0;
                kol_postoj = 0;
                kol_calkowity_czas = 0;
                
                
                
                LocalDateTime localDateTime_teraz = LocalDateTime.now();
                LocalDateTime localDateTime_start_pierwsza = localDateTime_teraz.withHour(6).withMinute(0).withSecond(0);
                LocalDateTime localDateTime_koniec_pierwsza = localDateTime_start_pierwsza.plusHours(12);
                
                LocalDateTime localDateTime_start_druga = localDateTime_teraz.withHour(18).withMinute(0).withSecond(0);
                LocalDateTime localDateTime_koniec_druga = localDateTime_start_druga.plusHours(12);
                
                localDateTime_od = localDateTime_teraz.minusHours(12);
                localDateTime_do = localDateTime_teraz;
                
                if (localDateTime_teraz.isAfter(localDateTime_start_pierwsza) && localDateTime_teraz.isBefore(localDateTime_koniec_pierwsza)) {
                    System.out.println("Pierwsza zmiana");
                    
                    localDateTime_od = localDateTime_start_pierwsza;
                    localDateTime_do = localDateTime_koniec_pierwsza;
                }
                else if (localDateTime_teraz.isAfter(localDateTime_start_druga) && localDateTime_teraz.isBefore(localDateTime_koniec_druga)) {
                    System.out.println("Pierwsza zmiana");
                    
                    localDateTime_od = localDateTime_start_druga;
                    localDateTime_do = localDateTime_koniec_druga;
                }
                str_dane_od = localDateTime_od.format(CUSTOM_FORMATTER); 
                str_dane_do = localDateTime_do.format(CUSTOM_FORMATTER); 
                System.out.println(str_dane_od + " " + str_dane_do );
                
                
                for(Maszyna masfor:istniejace_maszyny)
                {
                    
                
                

                    String sqlCzas = "SELECT\n" +
                            "(SELECT (IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0))  FROM techniplast.cykle_szybkie where maszyna = '"+masfor.getNazwa()+"' and miejsce = '"+nazwaMiejsca+"' and wtrysk > 0 and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') as wtrysk,\n" +
                            "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM techniplast.cykle_szybkie where maszyna = '"+masfor.getNazwa()+"' and miejsce = '"+nazwaMiejsca+"' and wybrak > 0 and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') as wybrak,\n" +
                            "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM techniplast.cykle_szybkie where maszyna = '"+masfor.getNazwa()+"' and miejsce = '"+nazwaMiejsca+"' and postoj_n > 0 and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') as postoj_n,\n" +
                            "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM techniplast.cykle_szybkie where maszyna = '"+masfor.getNazwa()+"' and miejsce = '"+nazwaMiejsca+"' and awaria_m > 0 and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') as awaria_m,\n" +
                            "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM techniplast.cykle_szybkie where maszyna = '"+masfor.getNazwa()+"' and miejsce = '"+nazwaMiejsca+"' and awaria_f > 0 and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') as awaria_f,\n" +
                            "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM techniplast.cykle_szybkie where maszyna = '"+masfor.getNazwa()+"' and miejsce = '"+nazwaMiejsca+"' and przezbrajanie > 0 and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') as przezbrajanie,\n" +
                            "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM techniplast.cykle_szybkie where maszyna = '"+masfor.getNazwa()+"' and miejsce = '"+nazwaMiejsca+"' and proby_tech > 0 and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') as proby_tech,\n" +
                            "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM techniplast.cykle_szybkie where maszyna = '"+masfor.getNazwa()+"' and miejsce = '"+nazwaMiejsca+"' and brak_zaop > 0 and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') as brak_zaop,\n" +
                            "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM techniplast.cykle_szybkie where maszyna = '"+masfor.getNazwa()+"' and miejsce = '"+nazwaMiejsca+"' and przerwa > 0 and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') as przerwa,\n" +
                            "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM techniplast.cykle_szybkie where maszyna = '"+masfor.getNazwa()+"' and miejsce = '"+nazwaMiejsca+"' and brak_oper > 0 and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') as brak_oper,\n" +
                            "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM techniplast.cykle_szybkie where maszyna = '"+masfor.getNazwa()+"' and miejsce = '"+nazwaMiejsca+"' and susz_m > 0 and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') as susz_m,\n" +
                            "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM techniplast.cykle_szybkie where maszyna = '"+masfor.getNazwa()+"' and miejsce = '"+nazwaMiejsca+"' and postoj > 0 and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') as postoj,\n" +
                            "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM techniplast.cykle_szybkie where maszyna = '"+masfor.getNazwa()+"' and miejsce = '"+nazwaMiejsca+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') as sum_czas;";

                    System.out.println(sqlCzas);

                    ResultSet rs_a;
                PreparedStatement pst_a;
                //System.out.println(sql);
                pst_a = conn_kolowy.prepareStatement(sqlCzas);
                
                rs_a = pst_a.executeQuery(sqlCzas);

                    while(rs_a.next()) {
                        if(rs_a.getString("sum_czas") == null)
                        {
                            //koniec = true;
                            //break;
                        }
                        else
                        {
                            try
                            {
                            kol_wtrysk += Integer.parseInt(rs_a.getString("wtrysk"));
                            }
                            catch(Exception ex)
                            {
                                kol_wtrysk += 0;
                            }
                            try
                            {
                            kol_wybrak += Integer.parseInt(rs_a.getString("wybrak"));
                            }
                            catch(Exception ex)
                            {
                                kol_wybrak += 0;
                            }
                            try
                            {
                            kol_postoj_n += (int)Float.parseFloat(rs_a.getString("postoj_n"));
                            }
                            catch(Exception ex)
                            {
                                kol_postoj_n += 0;
                            }
                            try
                            {
                            kol_awaria_m += (int)Float.parseFloat(rs_a.getString("awaria_m"));
                            }
                            catch(Exception ex)
                            {
                                kol_awaria_m += 0;
                            }
                            try
                            {
                            kol_awaria_f += (int)Float.parseFloat(rs_a.getString("awaria_f"));
                            }
                            catch(Exception ex)
                            {
                                kol_awaria_f += 0;
                            }
                            try
                            {
                            kol_przezbrajanie += (int)Float.parseFloat(rs_a.getString("przezbrajanie"));
                            }
                            catch(Exception ex)
                            {
                                kol_przezbrajanie += 0;
                            }
                            try
                            {
                            kol_proby_tech += (int)Float.parseFloat(rs_a.getString("proby_tech"));
                            }
                            catch(Exception ex)
                            {
                                kol_proby_tech += 0;
                            }
                            try
                            {
                            kol_brak_zaop += (int)Float.parseFloat(rs_a.getString("brak_zaop"));
                            }
                            catch(Exception ex)
                            {
                                kol_brak_zaop += 0;
                            }
                            try
                            {
                            kol_przerwa_p += (int)Float.parseFloat(rs_a.getString("przerwa"));
                            }
                            catch(Exception ex)
                            {
                                kol_przerwa_p += 0;
                            }

                            try
                            {
                            kol_brak_oper += (int)Float.parseFloat(rs_a.getString("brak_oper"));
                            }
                            catch(Exception ex)
                            {
                                kol_brak_oper += 0;
                            }
                            try
                            {
                            kol_susz_m += (int)Float.parseFloat(rs_a.getString("susz_m"));
                            }
                            catch(Exception ex)
                            {
                                kol_susz_m += 0;
                            }
                            try
                            {
                            kol_postoj += (int)Float.parseFloat(rs_a.getString("postoj"));
                            }
                            catch(Exception ex)
                            {
                                kol_postoj += 0;
                            }
                            try
                            {
                            kol_calkowity_czas += (int)Float.parseFloat(rs_a.getString("sum_czas"));
                            }
                            catch(Exception ex)
                            {
                                kol_calkowity_czas += 0;
                            }
                        }
                    }
                }//KONIEC FOR
                
                kol_max = kol_wtrysk+kol_proby_tech+kol_postoj+kol_przezbrajanie+kol_susz_m+kol_postoj_n+kol_awaria_m+kol_awaria_f+kol_brak_zaop+kol_przerwa_p+kol_brak_oper+kol_wybrak;
                
                daneWykresKolowy = FXCollections.observableArrayList(new PieChart.Data("wtrysk "+procentFormat.format(kol_wtrysk/kol_max), kol_wtrysk),
                new PieChart.Data("próby technologiczne "+procentFormat.format(kol_proby_tech/kol_max), kol_proby_tech),
                new PieChart.Data("postój zaplanowany "+procentFormat.format(kol_postoj/kol_max), kol_postoj),
                new PieChart.Data("przezbrajanie "+procentFormat.format(kol_przezbrajanie/kol_max), kol_przezbrajanie),
                new PieChart.Data("suszenie materiału "+procentFormat.format(kol_susz_m/kol_max), kol_susz_m),
                new PieChart.Data("nie zgłoszono "+procentFormat.format(kol_postoj_n/kol_max), kol_postoj_n),
                new PieChart.Data("awaria maszyny "+procentFormat.format(kol_awaria_m/kol_max), kol_awaria_m),
                new PieChart.Data("awaria formy "+procentFormat.format(kol_awaria_f/kol_max), kol_awaria_f),
                new PieChart.Data("brak zaopatrzenia "+procentFormat.format(kol_brak_zaop/kol_max), kol_brak_zaop),
                new PieChart.Data("przerwa pracownika "+procentFormat.format(kol_przerwa_p/kol_max), kol_przerwa_p),
                new PieChart.Data("brak operatora "+procentFormat.format(kol_brak_oper/kol_max), kol_brak_oper),
                new PieChart.Data("wybrak "+procentFormat.format(kol_wybrak/kol_max), kol_wybrak));
                
                
                
                System.out.println("liczba sprawdzanych maszyn: "+istniejace_maszyny.size());
                
                System.out.println("całkowity czas w wykresie kołowym: "+calculateTime(kol_calkowity_czas));
                
                
                 Platform.runLater(new Runnable()

                    {

                    @Override

                    public void run()

                    {
                        halaWykresKolowy.getData().clear();
                        halaWykresKolowy.getData().addAll(daneWykresKolowy);
                        halaWykresKolowy.setTitle("Dane pobierane od: "+str_dane_od+ " do: "+str_dane_do);

                    }

                    });//END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_
            }
            catch(SQLException ex)
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
            
        }//koniec id conn lub internet
    }
    public void przypiszKolory(Maszyna mas)
    {
        
        
        switch (mas.getNazwa()) {
            case "ST_55":
                btnST_55.setStyle(mas.getStyl());
                break;
            case "ST_52":
                btnST_52.setStyle(mas.getStyl());
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
            case "ST_66":
                btnST_66.setStyle(mas.getStyl());
                break;
             case "ST_65":
                btnST_65.setStyle(mas.getStyl());
                break;
            case "ST_70":
                btnST_70.setStyle(mas.getStyl());
                break;
            case "ST_60":
                btnST_60.setStyle(mas.getStyl());
                break;
            case "ST_63":
                btnST_63.setStyle(mas.getStyl());
                break;
            case "ST_67":
                btnST_67.setStyle(mas.getStyl());
                break;
            case "ST_58":
                btnST_58.setStyle(mas.getStyl());
                break;
            case "ST_69":
                btnST_69.setStyle(mas.getStyl());
                break;
            case "ST_71":
                btnST_71.setStyle(mas.getStyl());
                break;
            case "ST_72":
                btnST_72.setStyle(mas.getStyl());
                break;
            case "ST_73":
                btnST_73.setStyle(mas.getStyl());
                break;
            case "ST_74":
                btnST_74.setStyle(mas.getStyl());
                break;
            case "ST_75":
                btnST_75.setStyle(mas.getStyl());
                break;
            case "ST_76":
                btnST_76.setStyle(mas.getStyl());
                break;
            case "ST_77":
                btnST_77.setStyle(mas.getStyl());
                break;
            case "ST_78":
                btnST_78.setStyle(mas.getStyl());
                break;
            default:
                
                break;    
        }

    }
    public void shutdown(){
        //System.out.println("zamykanie okna");
        try{
            if(wtryskarkiUpdater != null)
            {

                if(conn_wtryskarki != null)
                {
                conn_wtryskarki.close();
                }
                if(wtryskarkiUpdater != null)
                {
                wtryskarkiUpdater.stop();
                }  
            }
            if(kolowyUpdater != null)
            {
                if(conn_kolowy != null)
                {
                conn_kolowy.close();
                }
                if(kolowyUpdater != null)
                {
                kolowyUpdater.stop();
                }
            }
        }
        catch (SQLException ex) {
                System.err.println("Błąd: " + ex.getMessage());
            }
            finally
            {
                zamknieto_okno = true;
            }
    }
    public void kolorLegenda()
    {
        btnLEG_PRACA.setStyle(Maszyna.STYL_BPRACA);

        btnLEG_WYBRAK.setStyle(Maszyna.STYL_WYBRAK);

        btnLEG_AWARIA_MASZYNY.setStyle(Maszyna.STYL_AWARIA_MASZYNY);

        btnLEG_AWARIA_FORMY.setStyle(Maszyna.STYL_AWARIA_FORMY);

        btnLEG_PRZEZBRAJANIE.setStyle(Maszyna.STYL_PRZEZBRAJANIE);
        
        btnLEG_SUSZ_M.setStyle(Maszyna.STYL_SUSZ_M);

        btnLEG_PROBY.setStyle(Maszyna.STYL_PROBY_TECHNOLOGICZNE);

        btnLEG_BRAK_Z.setStyle(Maszyna.STYL_BRAK_ZAOPATRZENIA);
        
        btnLEG_BRAK_OPER.setStyle(Maszyna.STYL_BRAK_OPERATORA);
        
        btnLEG_PRZERWA_P.setStyle(Maszyna.STYL_PRZERWA_P);

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
