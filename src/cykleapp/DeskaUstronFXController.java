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
public class DeskaUstronFXController implements Initializable {

    private Connection conn_wtryskarki;
    private Connection conn_kolowy;
    private ArrayList<Maszyna> istniejace_maszyny;
    private String  nazwaMiejsca = "USTRON";
    
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
     private final static int czas_odswierzania_kolowy = 300; //5min
    private static int czas_animacji = 2;
    private static int obecnyKomunikat = 0;
    private static int liczbaKomunikatow = 0;
    
    private int liczba_pracujacych_wtryskarek_ustron = 0;
    private double postep;
    
    final static DateTimeFormatter CUSTOM_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    NumberFormat procentFormat = NumberFormat.getPercentInstance(Locale.US);
    
    String str_dane_od = "";
    String str_dane_do = "";

    
    List<String> komunikatyTxt;
    private boolean wczytanoKomunikaty;
    private Timeline komunikatyTimeline;
    private File fileText;
    private static final int czasZmianyKomunikatu = 20;
    private boolean zaladowano_okno = false;
    private boolean zamknieto_okno = false;
    
    
    @FXML
    private PieChart halaWykresKolowy;        
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
    private Button btnUST_33;
    @FXML
    private Button btnUST_44;
    @FXML
    private Button btnUST_45;
    @FXML
    private Button btnUST_46;
    @FXML
    private Button btnUST_43;
    @FXML
    private Button btnUST_53;
    @FXML
    private Button btnUST_50;
    @FXML
    private Button btnUST_52;
    @FXML
    private Button btnUST_41;
    @FXML
    private Button btnLEG_SUSZ_M;
    @FXML
    private Label labLEG_SUSZ_M;
    @FXML
    private Button btnLEG_PRZERWA_P;
    @FXML
    private Label labLEG_PRZERWA_P;
    @FXML
    private Button btnUST_14;
    @FXML
    private Button btnUST_11;
    @FXML
    private Button btnUST_51;
    @FXML
    private Button btnUST_48;
    @FXML
    private Button btnUST_55;
    @FXML
    private Button btnUST_39;
    @FXML
    private Button btnUST_40;
    @FXML
    private Button btnUST_47;
    @FXML
    private Button btnUST_57;
    @FXML
    private Button btnUST_54;
    @FXML
    private Button btnUST_42;
    @FXML
    private Button btnUST_16;
    @FXML
    private Button btnUST_15;
    @FXML
    private Button btnUST_38;
    @FXML
    private Button btnUST_56;
    @FXML
    private Button btnUST_37;
    @FXML
    private Button btnUST_18;
    @FXML
    private Button btnUST_17;
    
    
    
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
        System.out.println("jestem w initialize DeskaFXController");
        conn_wtryskarki = mysqlconnect.ConnecrDb();
        conn_kolowy = mysqlconnect.ConnecrDb();
        wczytanoKomunikaty = false;
        Platform.runLater(new Runnable(){

        @Override
        public void run(){
        //wczytywanie komunikatów
        File komunikaty = new File("komunikaty.txt");
         if(CykleApp.rootPref.get("OPEN_KOMUNIKATY_FILE", "")!= "")
        {
        komunikaty = new File(CykleApp.rootPref.get("OPEN_KOMUNIKATY_FILE", ""));
        } 
        komunikaty(komunikaty);
        // koniec wczytywania komunikatów
        kolorLegenda();
        halaWykresKolowy.setTitle("");
        progressBar.setVisible(true);
        System.out.println("jestem w initialize DeskaFXController 2");
        
        alertSQL = new Alert(Alert.AlertType.ERROR);
        Stage alertSQLStage = (Stage) alertSQL.getDialogPane().getScene().getWindow();
        alertSQLStage.getIcons().add(new Image(this.getClass().getResource("img/icon48.png").toString()));
        alertSQL.setTitle("TECHNIPLAST Analizator pracy wtryskarek: Błąd");
        alertSQL.setHeaderText("Problem bazy danych SQL");
        alertSQL.setContentText("Nie można wczytać danych");
        
        System.out.println("jestem w initialize DeskaFXController 3");
        
        alertInternet = new Alert(Alert.AlertType.WARNING);
        Stage alertInternetStage = (Stage) alertInternet.getDialogPane().getScene().getWindow();
        alertInternetStage.getIcons().add(new Image(this.getClass().getResource("img/icon48.png").toString()));
        alertInternet.setTitle("TECHNIPLAST Analizator pracy wtryskarek: Błąd");
        alertInternet.setHeaderText("Brak połączenia internetowego");
        alertInternet.setContentText("Przywróć połączenie internetowe");
        istniejace_maszyny = IstniejaceMaszynyUstron.LadujNazwyMaszyn();
        
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
        //wtryskarki updater
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
        
        //Zmiana komunikatów
        komunikatyTimeline = new Timeline();
        komunikatyTimeline.setCycleCount(Animation.INDEFINITE);
        KeyFrame zmienKomunikat = new KeyFrame(Duration.seconds(czasZmianyKomunikatu),
                new EventHandler<ActionEvent>() {

                    public void handle(ActionEvent event) {
                        komunikaty(fileText);
                    }
                });

        komunikatyTimeline.getKeyFrames().add(zmienKomunikat);
        komunikatyTimeline.play();
        
         
        zaladowano_okno = true;
    }

    

    public void aktualizuj()
    {
        if(jestInternet())
        {
            System.out.println("Aktualizowanie wtryskarek...");
            
            liczba_pracujacych_wtryskarek_ustron = 0;
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
                    alertSQL.hide();
                }
                
                
                Boolean brakDanych = true;
                wtrysk =0;
                wybrak =0;
                postoj_n =0;
                awaria_m =0;
                awaria_f =0;
                przezbrajanie =0;
                susz_m =0;
                proby_tech =0;
                brak_zaop =0;
                przerwa_p = 0;
                brak_oper =0;
                postoj =0;
                

                String sql = " SELECT data_g, wtrysk, wybrak, postoj_n, awaria_m, awaria_f, przezbrajanie, susz_m, proby_tech, brak_zaop, przerwa, brak_oper, postoj FROM techniplast.cykle_szybkie where maszyna = '"+masfor.getNazwa()+"' and data_g > DATE_SUB(NOW(), INTERVAL 5 MINUTE) and lp=(SELECT max(lp) FROM techniplast.cykle_szybkie where maszyna = '"+masfor.getNazwa()+"' and data_g > DATE_SUB(NOW(), INTERVAL 5 MINUTE));";
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
                    
                    if(wtrysk > 0 || wybrak > 0)
                    {
                        liczba_pracujacych_wtryskarek_ustron++;
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
                        if(wtrysk > 0)
                        {
                             masfor.setStatus(Maszyna.Stan.PRACA);
                        }
                        if(wybrak > 0)
                        {
                             masfor.setStatus(Maszyna.Stan.WYBRAK);
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
            animacja.play(); 
        }
        System.out.println("liczba pracujących wtryskarek: "+liczba_pracujacych_wtryskarek_ustron);
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
                pst_a = conn_wtryskarki.prepareStatement(sqlCzas);
                
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
                        halaWykresKolowy.setTitle("Dane pobierane\nod: "+str_dane_od+ "\ndo: "+str_dane_do);

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

            case "UST_33":
                btnUST_33.setStyle(mas.getStyl());
                break;
            case "UST_44":
                btnUST_44.setStyle(mas.getStyl());
                break;
            case "UST_45":
                btnUST_45.setStyle(mas.getStyl());
                break;
            case "UST_46":
                btnUST_46.setStyle(mas.getStyl());
                break; 
            case "UST_43":
                btnUST_43.setStyle(mas.getStyl());
                break;
            case "UST_53":
                btnUST_53.setStyle(mas.getStyl());
                break;
            case "UST_50":
                btnUST_50.setStyle(mas.getStyl());
                break;
            case "UST_52":
                btnUST_52.setStyle(mas.getStyl());
                break;
            case "UST_41":
                btnUST_41.setStyle(mas.getStyl());
                break;
            case "UST_14":
                btnUST_14.setStyle(mas.getStyl());
                break;                
            case "UST_11":
                btnUST_11.setStyle(mas.getStyl());
                break; 
            case "UST_51":
                btnUST_51.setStyle(mas.getStyl());
                break; 
            case "UST_48":
                btnUST_48.setStyle(mas.getStyl());
                break;
            case "UST_55":
                btnUST_55.setStyle(mas.getStyl());
                break;
            case "UST_39":
                btnUST_39.setStyle(mas.getStyl());
                break;
            case "UST_40":
                btnUST_40.setStyle(mas.getStyl());
                break;
            case "UST_47":
                btnUST_47.setStyle(mas.getStyl());
                break;
            case "UST_57":
                btnUST_57.setStyle(mas.getStyl());
                break;
            case "UST_54":
                btnUST_54.setStyle(mas.getStyl());
                break;
            case "UST_42":
                btnUST_42.setStyle(mas.getStyl());
                break;
            case "UST_16":
                btnUST_16.setStyle(mas.getStyl());
                break;
            case "UST_15":
                btnUST_15.setStyle(mas.getStyl());
                break;
            case "UST_38":
                btnUST_38.setStyle(mas.getStyl());
                break;
            case "UST_56":
                btnUST_56.setStyle(mas.getStyl());
                break;
            case "UST_37":
                btnUST_37.setStyle(mas.getStyl());
                break;
            case "UST_18":
                btnUST_18.setStyle(mas.getStyl());
                break;
            case "UST_17":
                btnUST_17.setStyle(mas.getStyl());
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
        
        btnLEG_PRZERWA_P.setStyle(Maszyna.STYL_PRZERWA_P);
        
        btnLEG_BRAK_OPER.setStyle(Maszyna.STYL_BRAK_OPERATORA);

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
     private void komunikaty(File plik)
     {   
          try 
        { 
             
            if(plik != null)
            {
            komunikatyTxt = new ArrayList<String>();
            //FileInputStream fstream_school = new FileInputStream(plik); 
            //DataInputStream data_input = new DataInputStream(fstream_school); 
            ///BufferedReader buffer = new BufferedReader(new InputStreamReader(data_input)); 
            BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(plik), "UTF8"));
            String str_line; 

            while ((str_line = buffer.readLine()) != null) 
            { 
                str_line = str_line.trim(); 
                if ((str_line.length()!=0))  
                { 
                    komunikatyTxt.add(str_line);
                } 
            }
            //arr = (String[])komunikatyTxt.toArray(new String[komunikatyTxt.size()]);

            wczytanoKomunikaty = true;
            liczbaKomunikatow = komunikatyTxt.size();
            }

        }
        catch (Exception e)  
        {
         // Catch exception if any
            System.err.println("Błąd komunikaty: " + e.getMessage());
        }
         if(wczytanoKomunikaty)
         {
             if(obecnyKomunikat == liczbaKomunikatow)
             {
                 obecnyKomunikat = 0;
             }
             obecnyKomunikat++;
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
            if(!startFXController.stageDeskaUstron.isFullScreen())
            {
            startFXController.stageDeskaUstron.setFullScreen(true);
            }
            else
            {
                startFXController.stageDeskaUstron.setFullScreen(false);
            }
        }
    }
}
