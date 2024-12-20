/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cykleapp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.controls.JFXTogglePane;
import com.jfoenix.controls.JFXTreeView;
import insidefx.undecorator.Undecorator;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.CheckTreeView;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import  org.apache.poi.hssf.usermodel.HSSFSheet;
import  org.apache.poi.hssf.usermodel.HSSFWorkbook;
import  org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.IndexedColors;
/**
 *
 * @author <a href="mailto:marekmadyda@gmail.com">Marek Madyda</a>
 */
public class obciazenieFXController implements  Initializable {

    @FXML
    private Color x2;
    @FXML
    private Font x1;
    @FXML
    private Button BTNwczytaj;
    @FXML
    private Color x4;
    @FXML
    private Font x3;
    @FXML
    private StackedBarChart<?, ?> WykresSlupkowy;

    private Stage windowStage;
    private Connection conn;
    private ResultSet rs;
    private PreparedStatement pst;
    private final int przyciskiWidth = 200;

    private LocalDateTime localDateTime_od;
    private LocalDateTime localDateTime_do;
    
    private LocalDateTime infoDateTime_od;
    private LocalDateTime infoDateTime_do;
    
    
    private String wybraneMaszyny = "";
    private int liczba_godzin_dni = 12;
    @FXML
    private JFXDatePicker TBdata_od;
    @FXML
    private JFXTimePicker TBgodzina_od;
    //private JFXComboBox<String> CBmaszyna;
    @FXML
    private Label Lstatus;
    @FXML
    private JFXSpinner ProgressBar;
    
    private ContextMenu contextMenuWykresSlupkowy;
    private ContextMenu contextMenuWykresKolowy;
    private ContextMenu contextMenuWykresLiniowy;
    private ContextMenu contextMenuOkno;
    
    private ContextMenu contextMenuTableCykle;
    private ContextMenu contextMenuTableCykleZbiorczo;
    
    private String textLabelDialog;
    
    private String maszyna;
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
    
    
    private boolean sprawdzajGodziny = true;
    private boolean sprawdzajDni = false;
    
    
    private XYChart.Series s_wtrysk;
    private XYChart.Series s_wybrak;
    private XYChart.Series s_postoj_n;
    private XYChart.Series s_awaria_m;
    private XYChart.Series s_awaria_f;
    private XYChart.Series s_przezbrajanie;
    private XYChart.Series s_susz_m;
    private XYChart.Series s_proby_tech;
    private XYChart.Series s_brak_zaop;
    private XYChart.Series s_przerwa_p;
    private XYChart.Series s_brak_oper;
    private XYChart.Series s_postoj;
    
    private XYChart.Series l_czas_cykl;
    private double sr_czas_cykl;
    
    ObservableList<PieChart.Data> daneWykresKolowy;
    @FXML
    private PieChart WykresKolowy;
    @FXML
    private JFXTogglePane infoDialog;
    @FXML
    private Label labelDialog;
    @FXML
    private AnchorPane podgladPane;
    @FXML
    private AnchorPane opcjePane;
    @FXML
    private ScrollPane podgladScrollPane;
    @FXML
    private Label LABpodglad;
    @FXML
    private VBox vbox;
    @FXML
    private SplitPane splitPane;
    @FXML
    private ScrollPane wlasciwosciScrollPane;
    @FXML
    private AnchorPane wlasciwosciPane;
    @FXML
    private AreaChart<?, ?> WykresLiniowy;
    @FXML
    private JFXRadioButton RBustawGodzine;
    @FXML
    private ToggleGroup godziny;
    @FXML
    private JFXRadioButton RBpierwszaZmiana;
    @FXML
    private JFXRadioButton RBdrugaZmiana;
    @FXML
    private TableView<daneObciazenieWtryskarki> TableCykle;

    
    private TableColumn TBCmaszyna;
    private TableColumn TBCwtrysk;
    private TableColumn TBCwybrak;
    private TableColumn TBCpostoj_n;
    private TableColumn TBCawaria_m;
    private TableColumn TBCawaria_f;
    private TableColumn TBCprzezbrajanie;
    private TableColumn TBCsusz_m;
    private TableColumn TBCproby_tech;
    private TableColumn TBCbrak_zaop;
    private TableColumn TBCprzerwa_p;
    private TableColumn TBCbrak_oper;
    private TableColumn TBCpostoj;
    private TableColumn TBCczas_cyklu;
    
    private ObservableList<daneObciazenieWtryskarki> daneSlupkoweDoTabeli;
 
    @FXML
    private HBox progrHBox;
    
    @FXML
    private TableView<zbiorczeDaneWtryskarki> TableCykleZbiorczo;
    private TableColumn TBZnazwa;
    private TableColumn TBZwartosc;
    private ObservableList<zbiorczeDaneWtryskarki> daneKoloweDoTabeli;
    @FXML
    private JFXButton BTNhide;
    private static boolean pierwszyHide = true;
    @FXML
    private JFXSlider SLDczas_ladowania;
    @FXML
    private JFXRadioButton RBgodziny;
    @FXML
    private ToggleGroup jednostka;
    @FXML
    private JFXRadioButton RBdni;
    @FXML
    private BorderPane borderPane;
    @FXML
    private CheckTreeView<String> ustronTree;

    private boolean zaladowano_okno = false;
    
    public static String calculateTime(long seconds) {
        long hours = TimeUnit.SECONDS.toHours(seconds);

        long minute = (TimeUnit.SECONDS.toMinutes(seconds) -
                      (TimeUnit.SECONDS.toHours(seconds)* 60));

        long second = (TimeUnit.SECONDS.toSeconds(seconds) -
                      (TimeUnit.SECONDS.toMinutes(seconds) *60));

    
    return String.format("%02d", hours)+":" +String.format("%02d", minute)+":" + String.format("%02d", second);
    }

    @FXML
    private void oknoMouseClickedAction(MouseEvent event) {
          if(event.getClickCount() == 2)
        {
            if(!startFXController.stageAnalizujWiele.isFullScreen())
            {
            startFXController.stageAnalizujWiele.setFullScreen(true);
            }
            else
            {
                startFXController.stageAnalizujWiele.setFullScreen(false);
            }
        }
    }
  
    

    

    public enum J_ANALIZY
    {
        GODZINA,
        DZIEN
    };
    private J_ANALIZY wybrana_analiza = J_ANALIZY.GODZINA;

    

    
     private void wczytajWykresMaszyn()
    {
        //RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ 
        liczba_godzin_dni = (int)SLDczas_ladowania.getValue();
         boolean nie_wybrano_maszyn = false;
        //System.out.println("wczytaj wykres godzin LICZBA GODZIN: "+SLDczas_ladowania.getValue());
       
        if(liczba_godzin_dni == 0)
        {
            Platform.runLater(new Runnable()
         {

            @Override
            public void run()

            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                alertStage.getIcons().add(new Image(this.getClass().getResource("img/icon48.png").toString()));
                alert.setTitle("TECHNIPLAST Analizator pracy wtryskarek: Błąd");
                alert.setHeaderText("Wprowadzono błędną ilość godzin");
                alert.setContentText("Nie można wczytać danych");

                alert.showAndWait();
                ProgressBar.setVisible(false);
                Lstatus.setText("TECHNIPLAST");
                BTNwczytaj.setDisable(false);
            }

        });//END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_
                return;
        }
         Platform.runLater(new Runnable()
         {

            @Override
            public void run()

            {
            WykresSlupkowy.setVisible(false);
            WykresKolowy.setVisible(false);
            WykresLiniowy.setVisible(false);
            TableCykle.setVisible(false);
            TableCykleZbiorczo.setVisible(false);
            LABpodglad.setText("Ładowanie danych . . .");
            }

        });//END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_
        

        infoDateTime_od = localDateTime_od;
        
        //RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ 
        Platform.runLater(new Runnable()
        {
        @Override

            public void run()

            {
            WykresSlupkowy.getData().clear();
            WykresKolowy.getData().clear();
            WykresLiniowy.getData().clear();
            }

        });//END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_

            //System.out.println("Wczytywanie...");
            //LocalDateTime dateTime_do = LocalDateTime.of(sql_date_do, sql_time_do);
          
            
            maszyna = "";
            wtrysk = 0;
            wybrak = 0;
            postoj_n = 0;
            awaria_m = 0;
            awaria_f =0;
            przezbrajanie = 0;
            susz_m = 0;
            proby_tech = 0;
            brak_zaop = 0;
            przerwa_p = 0;
            brak_oper = 0;
            postoj = 0;
            
            
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
            
            //RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ 
            Platform.runLater(new Runnable()
            {
            @Override
            public void run()
            {
                
                s_wtrysk = new XYChart.Series();
                s_wtrysk.setName("wtrysk");

                s_wybrak = new XYChart.Series();
                s_wybrak.setName("wybrak");

                //////////////////////////////////////
                s_postoj_n = new XYChart.Series();
                s_postoj_n.setName("Nie zgłoszono");

                s_awaria_m = new XYChart.Series();
                s_awaria_m.setName("awaria maszyny");

                s_awaria_f = new XYChart.Series();
                s_awaria_f.setName("awaria formy");

                s_przezbrajanie = new XYChart.Series();
                s_przezbrajanie.setName("przezbrajanie");
                
                s_susz_m = new XYChart.Series();
                s_susz_m.setName("suszenie materiału");

                s_proby_tech = new XYChart.Series();
                s_proby_tech.setName("próby technologiczne");

                s_brak_zaop = new XYChart.Series();
                s_brak_zaop.setName("brak zaopatrzenia");
                
                s_przerwa_p = new XYChart.Series();
                s_przerwa_p.setName("przerwa pracownika");
                
                s_brak_oper = new XYChart.Series();
                s_brak_oper.setName("brak operatora");

                s_postoj = new XYChart.Series();
                s_postoj.setName("postój");



                l_czas_cykl = new XYChart.Series();
                l_czas_cykl.setName("średni czas cyklu");

                //TABELA WYKRESU SŁUPKOWEGO I LINIOWEGO:
                TableCykle.getColumns().clear();

                TBCmaszyna = new TableColumn("nazwa\nmaszyny");
                TBCwtrysk = new TableColumn("wtrysk");
                TBCwybrak = new TableColumn("wybrak");
                TBCpostoj_n = new TableColumn("nie\nzgłoszono");
                TBCawaria_m = new TableColumn("awaria\nmaszyny");
                TBCawaria_f = new TableColumn("awaria\nformy");
                TBCprzezbrajanie = new TableColumn("przezbrajanie");
                TBCsusz_m = new TableColumn("suszenie\nmateriału");
                TBCproby_tech = new TableColumn("próby\ntechnologiczne");
                TBCbrak_zaop = new TableColumn("brak\nzaopatrzenia");
                TBCprzerwa_p = new TableColumn("przerwa\npracownika");
                TBCbrak_oper = new TableColumn("brak\noperatora");
                TBCpostoj = new TableColumn("postój");
                TBCczas_cyklu = new TableColumn("średni czas\ncyklu [s]");

                TBCmaszyna.setCellValueFactory(new PropertyValueFactory<>("maszyna"));
                TBCwtrysk.setCellValueFactory(new PropertyValueFactory<>("wtrysk"));
                TBCwybrak.setCellValueFactory(new PropertyValueFactory<>("wybrak"));
                TBCpostoj_n.setCellValueFactory(new PropertyValueFactory<>("postoj_n"));
                TBCawaria_m.setCellValueFactory(new PropertyValueFactory<>("awaria_m"));
                TBCawaria_f.setCellValueFactory(new PropertyValueFactory<>("awaria_f"));
                TBCprzezbrajanie.setCellValueFactory(new PropertyValueFactory<>("przezbrajanie"));
                TBCsusz_m.setCellValueFactory(new PropertyValueFactory<>("susz_m"));
                TBCproby_tech.setCellValueFactory(new PropertyValueFactory<>("proby_tech"));
                TBCbrak_zaop.setCellValueFactory(new PropertyValueFactory<>("brak_zaop"));
                TBCprzerwa_p.setCellValueFactory(new PropertyValueFactory<>("przerwa_p"));
                TBCbrak_oper.setCellValueFactory(new PropertyValueFactory<>("brak_oper"));
                TBCpostoj.setCellValueFactory(new PropertyValueFactory<>("postoj"));
                TBCczas_cyklu.setCellValueFactory(new PropertyValueFactory<>("czas_cyklu"));


                daneSlupkoweDoTabeli = FXCollections.observableArrayList();

                //TABELA WYKRESU KOŁOWEGO

                TableCykleZbiorczo.getColumns().clear();
                TBZnazwa = new TableColumn("Nazwa");
                TBZwartosc = new TableColumn("Czas");

                TBZnazwa.setSortable(false);
                TBZwartosc.setSortable(false);

                TBZnazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
                TBZwartosc.setCellValueFactory(new PropertyValueFactory<>("wartosc"));

                daneKoloweDoTabeli = FXCollections.observableArrayList();
            
            }

        });//END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_
            try {
            
            conn = mysqlconnect.ConnecrDb();
           
            
            
            //nt liczba_maszyn = IstniejaceMaszynyUstron.LadujNazwyMaszyn().size();
            int i_ta_maszyna = 0;
            wybraneMaszyny = "";
            
            ObservableList<TreeItem<String>> maszyny = ustronTree.getCheckModel().getCheckedItems();
            int liczba_maszyn = 0;
             for(TreeItem<String> analizowanaMaszyna:maszyny)
          {
              if(analizowanaMaszyna.getValue().equals("Wtryskarki")||analizowanaMaszyna.getValue().equals("Ustroń")||analizowanaMaszyna.getValue().equals("Skoczów")||analizowanaMaszyna.getValue().equals("Koniaków"))
              {
                  continue;
              }     
              liczba_maszyn++;
          }
            
            if(liczba_maszyn == 0)
            {
                nie_wybrano_maszyn = true;
            }
            else
            {
          kol_calkowity_czas = 0;
          for(TreeItem<String> analizowanaMaszyna:maszyny)
          {
              if(analizowanaMaszyna.getValue().equals("Wtryskarki")||analizowanaMaszyna.getValue().equals("Ustroń")||analizowanaMaszyna.getValue().equals("Skoczów")||analizowanaMaszyna.getValue().equals("Koniaków"))
              {
                  continue;
              }     

             String nazwaAnalizowanejMaszyny = analizowanaMaszyna.getValue();
             sr_czas_cykl = 0;
             i_ta_maszyna++;
            
             wybraneMaszyny += nazwaAnalizowanejMaszyny +" ";
             //kolko postepu
             ProgressBar.setProgress((i_ta_maszyna)/(new Double(liczba_maszyn)));
             
             //System.out.println(Timestamp.valueOf(localDateTime_od.plusHours(i)));
            if(wybrana_analiza == J_ANALIZY.GODZINA)
            {
                localDateTime_do = localDateTime_od.plusHours(liczba_godzin_dni); 
            }
            if(wybrana_analiza == J_ANALIZY.DZIEN)
            {
                localDateTime_do = localDateTime_od.plusDays(liczba_godzin_dni);
            }
             
             //Dane czasowe
             
            String sqlCzas = "";
            if(localDateTime_od.isBefore(LocalDateTime.now().minusDays(7)))
            { //SQL czas wykres kołowy
                System.out.println("DANE WOLNE \n");
                
                sqlCzas = "SELECT\n" +
                        "(SELECT (IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0))  FROM ((SELECT * FROM techniplast.cykle_szybkie where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') union (SELECT * FROM techniplast.cykle_wolne where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"')) as T where maszyna = '"+nazwaAnalizowanejMaszyny+"' and wtrysk > 0) as wtrysk,\n" +
                        "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM ((SELECT * FROM techniplast.cykle_szybkie where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') union (SELECT * FROM techniplast.cykle_wolne where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"')) as T  where maszyna = '"+nazwaAnalizowanejMaszyny+"' and wybrak > 0)  as wybrak,\n" +
                        "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM ((SELECT * FROM techniplast.cykle_szybkie where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') union (SELECT * FROM techniplast.cykle_wolne where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"')) as T  where maszyna = '"+nazwaAnalizowanejMaszyny+"' and postoj_n > 0) as postoj_n,\n" +
                        "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM ((SELECT * FROM techniplast.cykle_szybkie where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') union (SELECT * FROM techniplast.cykle_wolne where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"')) as T  where maszyna = '"+nazwaAnalizowanejMaszyny+"' and awaria_m > 0) as awaria_m,\n" +
                        "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM ((SELECT * FROM techniplast.cykle_szybkie where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') union (SELECT * FROM techniplast.cykle_wolne where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"')) as T  where maszyna = '"+nazwaAnalizowanejMaszyny+"' and awaria_f > 0) as awaria_f,\n" +
                        "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM ((SELECT * FROM techniplast.cykle_szybkie where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') union (SELECT * FROM techniplast.cykle_wolne where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"')) as T  where maszyna = '"+nazwaAnalizowanejMaszyny+"' and przezbrajanie > 0) as przezbrajanie,\n" +
                        "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM ((SELECT * FROM techniplast.cykle_szybkie where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') union (SELECT * FROM techniplast.cykle_wolne where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"')) as T  where maszyna = '"+nazwaAnalizowanejMaszyny+"' and proby_tech > 0) as proby_tech,\n" +
                        "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM ((SELECT * FROM techniplast.cykle_szybkie where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') union (SELECT * FROM techniplast.cykle_wolne where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"')) as T  where maszyna = '"+nazwaAnalizowanejMaszyny+"' and brak_zaop > 0) as brak_zaop,\n" +
                        "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM ((SELECT * FROM techniplast.cykle_szybkie where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') union (SELECT * FROM techniplast.cykle_wolne where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"')) as T  where maszyna = '"+nazwaAnalizowanejMaszyny+"' and przerwa > 0) as przerwa,\n" +
                        "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM ((SELECT * FROM techniplast.cykle_szybkie where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') union (SELECT * FROM techniplast.cykle_wolne where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"')) as T  where maszyna = '"+nazwaAnalizowanejMaszyny+"' and brak_oper > 0) as brak_oper,\n" +
                        "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM ((SELECT * FROM techniplast.cykle_szybkie where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') union (SELECT * FROM techniplast.cykle_wolne where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"')) as T  where maszyna = '"+nazwaAnalizowanejMaszyny+"' and susz_m > 0) as susz_m,\n" +
                        "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM ((SELECT * FROM techniplast.cykle_szybkie where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') union (SELECT * FROM techniplast.cykle_wolne where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"')) as T  where maszyna = '"+nazwaAnalizowanejMaszyny+"' and postoj > 0) as postoj,\n" +
                        "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM ((SELECT * FROM techniplast.cykle_szybkie where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') union (SELECT * FROM techniplast.cykle_wolne where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"')) as T  where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g) as sum_czas";

            }
            else
            {
                System.out.println("DANE SZYBKIE \n");
               //SQL czas wykres kołowy
                sqlCzas = "SELECT\n" +
                        "(SELECT (IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0))  FROM techniplast.cykle_szybkie where maszyna = '"+nazwaAnalizowanejMaszyny+"' and wtrysk > 0 and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') as wtrysk,\n" +
                        "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM techniplast.cykle_szybkie where maszyna = '"+nazwaAnalizowanejMaszyny+"' and wybrak > 0 and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') as wybrak,\n" +
                        "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM techniplast.cykle_szybkie where maszyna = '"+nazwaAnalizowanejMaszyny+"' and postoj_n > 0 and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') as postoj_n,\n" +
                        "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM techniplast.cykle_szybkie where maszyna = '"+nazwaAnalizowanejMaszyny+"' and awaria_m > 0 and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') as awaria_m,\n" +
                        "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM techniplast.cykle_szybkie where maszyna = '"+nazwaAnalizowanejMaszyny+"' and awaria_f > 0 and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') as awaria_f,\n" +
                        "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM techniplast.cykle_szybkie where maszyna = '"+nazwaAnalizowanejMaszyny+"' and przezbrajanie > 0 and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') as przezbrajanie,\n" +
                        "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM techniplast.cykle_szybkie where maszyna = '"+nazwaAnalizowanejMaszyny+"' and proby_tech > 0 and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') as proby_tech,\n" +
                        "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM techniplast.cykle_szybkie where maszyna = '"+nazwaAnalizowanejMaszyny+"' and brak_zaop > 0 and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') as brak_zaop,\n" +
                        "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM techniplast.cykle_szybkie where maszyna = '"+nazwaAnalizowanejMaszyny+"' and przerwa > 0 and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') as przerwa,\n" +
                        "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM techniplast.cykle_szybkie where maszyna = '"+nazwaAnalizowanejMaszyny+"' and brak_oper > 0 and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') as brak_oper,\n" +
                        "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM techniplast.cykle_szybkie where maszyna = '"+nazwaAnalizowanejMaszyny+"' and susz_m > 0 and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') as susz_m,\n" +
                        "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM techniplast.cykle_szybkie where maszyna = '"+nazwaAnalizowanejMaszyny+"' and postoj > 0 and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') as postoj,\n" +
                        "(SELECT IFNULL(sum(TIMESTAMPDIFF(second, pop_insert, data_g )), 0)  FROM techniplast.cykle_szybkie where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') as sum_czas;";


            }
            System.out.println(sqlCzas);

            pst = conn.prepareStatement(sqlCzas);
            
            rs = pst.executeQuery(sqlCzas);
            
            while(rs.next()) {
                if(rs.getString("sum_czas") == null)
                {
                    //koniec = true;
                    //break;

                    

                    
                }
                else
                {
                    try
                    {
                    kol_wtrysk += Integer.parseInt(rs.getString("wtrysk"));
                    }
                    catch(Exception ex)
                    {
                        kol_wtrysk += 0;
                    }
                    try
                    {
                    kol_wybrak += Integer.parseInt(rs.getString("wybrak"));
                    }
                    catch(Exception ex)
                    {
                        kol_wybrak += 0;
                    }
                    try
                    {
                    kol_postoj_n += (int)Float.parseFloat(rs.getString("postoj_n"));
                    }
                    catch(Exception ex)
                    {
                        kol_postoj_n += 0;
                    }
                    try
                    {
                    kol_awaria_m += (int)Float.parseFloat(rs.getString("awaria_m"));
                    }
                    catch(Exception ex)
                    {
                        kol_awaria_m += 0;
                    }
                    try
                    {
                    kol_awaria_f += (int)Float.parseFloat(rs.getString("awaria_f"));
                    }
                    catch(Exception ex)
                    {
                        kol_awaria_f += 0;
                    }
                    try
                    {
                    kol_przezbrajanie += (int)Float.parseFloat(rs.getString("przezbrajanie"));
                    }
                    catch(Exception ex)
                    {
                        kol_przezbrajanie += 0;
                    }
                    try
                    {
                    kol_proby_tech += (int)Float.parseFloat(rs.getString("proby_tech"));
                    }
                    catch(Exception ex)
                    {
                        kol_proby_tech += 0;
                    }
                    try
                    {
                    kol_brak_zaop += (int)Float.parseFloat(rs.getString("brak_zaop"));
                    }
                    catch(Exception ex)
                    {
                        kol_brak_zaop += 0;
                    }
                    try
                    {
                    kol_przerwa_p += (int)Float.parseFloat(rs.getString("przerwa"));
                    }
                    catch(Exception ex)
                    {
                        kol_przerwa_p += 0;
                    }
                    
                    try
                    {
                    kol_brak_oper += (int)Float.parseFloat(rs.getString("brak_oper"));
                    }
                    catch(Exception ex)
                    {
                        kol_brak_oper += 0;
                    }
                    try
                    {
                    kol_susz_m += (int)Float.parseFloat(rs.getString("susz_m"));
                    }
                    catch(Exception ex)
                    {
                        kol_susz_m += 0;
                    }
                    try
                    {
                    kol_postoj += (int)Float.parseFloat(rs.getString("postoj"));
                    }
                    catch(Exception ex)
                    {
                        kol_postoj += 0;
                    }
                    try
                    {
                    kol_calkowity_czas += (int)Float.parseFloat(rs.getString("sum_czas"));
                    }
                    catch(Exception ex)
                    {
                        kol_calkowity_czas += 0;
                    }

                  
                }
            }

           
            
             maszyna = "";
             wtrysk = 0;
             wybrak = 0;
             postoj_n = 0;
             awaria_m = 0;
             awaria_f =0;
             przezbrajanie = 0;
             susz_m = 0;
             proby_tech = 0;
             brak_zaop = 0;
             przerwa_p = 0;
             brak_oper = 0;
             postoj = 0;
             

            
            
            String sql = "";
            if(localDateTime_od.isBefore(LocalDateTime.now().minusDays(7)))
            {
                System.out.println("DANE WOLNE \n");
                sql = "SELECT maszyna, sum(wtrysk) ,sum(wybrak), sum(postoj_n), sum(awaria_m), sum(awaria_f),sum(przezbrajanie),sum(susz_m),sum(proby_tech),sum(brak_zaop),sum(przerwa), sum(brak_oper),sum(postoj), avg(nullif(czas_cyklu,0)) as 'avg(czas_cyklu)' FROM( "
                        + "(SELECT * FROM techniplast.cykle_wolne where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"') "
                        + "UNION "
                        + "(SELECT * FROM techniplast.cykle_szybkie where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"')) AS T ";
            }
            else
            {
                System.out.println("DANE SZYBKIE \n");
                sql = "SELECT maszyna, sum(wtrysk) ,sum(wybrak), sum(postoj_n), sum(awaria_m), sum(awaria_f),sum(przezbrajanie),sum(susz_m),sum(proby_tech),sum(brak_zaop),sum(przerwa),sum(brak_oper),sum(postoj), avg(nullif(czas_cyklu,0)) as 'avg(czas_cyklu)' FROM techniplast.cykle_szybkie where maszyna = '"+nazwaAnalizowanejMaszyny+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_do)+"';";
            }
            System.out.println("DZIEŃ SQL: "+sql);
            
            
            
                
            //System.out.println(sql);
            pst = conn.prepareStatement(sql);
            
            rs = pst.executeQuery(sql);
            
            while(rs.next()) {
                if(rs.getString("maszyna") == null)
                {
                    //koniec = true;
                    //break;
                    maszyna = nazwaAnalizowanejMaszyny;
                    wtrysk = 0;
                    wtrysk = 0;
                    wybrak = 0;
                    postoj_n = 0;
                    awaria_m = 0;
                    awaria_f = 0;
                    przezbrajanie = 0;
                    susz_m = 0;
                    proby_tech = 0;
                    brak_zaop = 0;
                    przerwa_p = 0;
                    brak_oper = 0;
                    postoj = 0;
                    
                }
                else
                {
                    maszyna = rs.getString("maszyna");
                    try
                    {
                    wtrysk = Integer.parseInt(rs.getString("sum(wtrysk)"));
                    }
                    catch(Exception ex)
                    {
                        wtrysk = 0;
                    }
                    try
                    {
                    wybrak = Integer.parseInt(rs.getString("sum(wybrak)"));
                    }
                    catch(Exception ex)
                    {
                        wybrak = 0;
                    }
                    try
                    {
                    postoj_n = (int)Float.parseFloat(rs.getString("sum(postoj_n)"));
                    }
                    catch(Exception ex)
                    {
                        postoj_n = 0;
                    }
                    try
                    {
                    awaria_m = (int)Float.parseFloat(rs.getString("sum(awaria_m)"));
                    }
                    catch(Exception ex)
                    {
                        awaria_m = 0;
                    }
                    try
                    {
                    awaria_f = (int)Float.parseFloat(rs.getString("sum(awaria_f)"));
                    }
                    catch(Exception ex)
                    {
                        awaria_f = 0;
                    }
                    try
                    {
                    przezbrajanie = (int)Float.parseFloat(rs.getString("sum(przezbrajanie)"));
                    }
                    catch(Exception ex)
                    {
                        przezbrajanie = 0;
                    }
                     try
                    {
                    susz_m = (int)Float.parseFloat(rs.getString("sum(susz_m)"));
                    }
                    catch(Exception ex)
                    {
                        susz_m = 0;
                    }
                    try
                    {    
                    proby_tech = (int)Float.parseFloat(rs.getString("sum(proby_tech)"));
                    }
                    catch(Exception ex)
                    {
                        proby_tech = 0;
                    }
                    try
                    {
                    brak_zaop = (int)Float.parseFloat(rs.getString("sum(brak_zaop)"));
                    }
                    catch(Exception ex)
                    {
                        brak_zaop = 0;
                    }
                    try
                    {
                    przerwa_p = (int)Float.parseFloat(rs.getString("sum(przerwa)"));
                    }
                    catch(Exception ex)
                    {
                        przerwa_p = 0;
                    }
                    try
                    {
                    brak_oper = (int)Float.parseFloat(rs.getString("sum(brak_oper)"));
                    }
                    catch(Exception ex)
                    {
                        brak_oper = 0;
                    }
                    try
                    {
                    postoj = (int)Float.parseFloat(rs.getString("sum(postoj)"));
                    }
                    catch(Exception ex)
                    {
                        postoj = 0;
                    }

                    String svg_czas_cykl = rs.getString("avg(czas_cyklu)");
                    if(svg_czas_cykl != null)
                    {
                        sr_czas_cykl = Double.parseDouble(svg_czas_cykl);
                    }
                    else
                    {
                        sr_czas_cykl = 0;
                    }
                     
                }
                /*
                //kol_data = "";
                kol_wtrysk += wtrysk;
                kol_wybrak += wybrak;
                kol_postoj_n += postoj_n;
                kol_awaria_m += awaria_m ;
                kol_awaria_f += awaria_f;
                kol_przezbrajanie += przezbrajanie;
                kol_proby_tech += proby_tech;
                kol_brak_zaop += brak_zaop;
                kol_brak_oper += brak_oper;
                kol_postoj += postoj;
                */
                infoDateTime_do = localDateTime_do;
                
            }
            
            
            daneSlupkoweDoTabeli.add(new daneObciazenieWtryskarki(maszyna+"",wtrysk+"",wybrak+"",postoj_n+"",awaria_m+"",awaria_f+"",przezbrajanie+"",susz_m+"",proby_tech+"",brak_zaop+"",przerwa_p+"",brak_oper+"",postoj+"",(round(sr_czas_cykl,3)+"").replace(".", ",")));
            
            
            
            l_czas_cykl.getData().add(new XYChart.Data(maszyna, sr_czas_cykl));
            
            
            s_wtrysk.getData().add(new XYChart.Data(maszyna, wtrysk));
            s_wybrak.getData().add(new XYChart.Data(maszyna, wybrak));
            s_postoj_n.getData().add(new XYChart.Data(maszyna, postoj_n));
            
            s_awaria_m.getData().add(new XYChart.Data(maszyna, awaria_m));
            s_awaria_f.getData().add(new XYChart.Data(maszyna, awaria_f));
            s_przezbrajanie.getData().add(new XYChart.Data(maszyna, przezbrajanie));
            s_susz_m.getData().add(new XYChart.Data(maszyna, susz_m));
            s_proby_tech.getData().add(new XYChart.Data(maszyna, proby_tech));
            s_brak_zaop.getData().add(new XYChart.Data(maszyna, brak_zaop));
            s_przerwa_p.getData().add(new XYChart.Data(maszyna, przerwa_p));
            s_brak_oper.getData().add(new XYChart.Data(maszyna, brak_oper));
            s_postoj.getData().add(new XYChart.Data(maszyna, postoj));
            
            
            

            
            //DODAWANIE DO TABELI
            
            
            }
            }
            
            daneWykresKolowy = FXCollections.observableArrayList(new PieChart.Data("wtrysk "+calculateTime(kol_wtrysk), kol_wtrysk),
                new PieChart.Data("próby technologiczne "+calculateTime(kol_proby_tech), kol_proby_tech),
                new PieChart.Data("postój zaplanowany "+calculateTime(kol_postoj), kol_postoj),
                new PieChart.Data("przezbrajanie "+calculateTime(kol_przezbrajanie), kol_przezbrajanie),
                new PieChart.Data("suszenie materiału "+calculateTime(kol_susz_m), kol_susz_m),
                new PieChart.Data("nie zgłoszono "+calculateTime(kol_postoj_n), kol_postoj_n),
                new PieChart.Data("awaria maszyny "+calculateTime(kol_awaria_m), kol_awaria_m),
                new PieChart.Data("awaria formy "+calculateTime(kol_awaria_f), kol_awaria_f),
                new PieChart.Data("brak zaopatrzenia "+calculateTime(kol_brak_zaop), kol_brak_zaop),
                new PieChart.Data("przerwa pracownika "+calculateTime(kol_przerwa_p), kol_przerwa_p),
                new PieChart.Data("brak operatora "+calculateTime(kol_brak_oper), kol_brak_oper),
                new PieChart.Data("wybrak "+calculateTime(kol_wybrak), kol_wybrak));
                
                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                ///KOLEJNOSC w tabeli wykresu kolowego
                daneKoloweDoTabeli.add(new zbiorczeDaneWtryskarki("Całkowity czas",calculateTime(kol_calkowity_czas)));
                daneKoloweDoTabeli.add(new zbiorczeDaneWtryskarki("Wtrysk",calculateTime(kol_wtrysk)));
                daneKoloweDoTabeli.add(new zbiorczeDaneWtryskarki("Próby technologiczne",calculateTime(kol_proby_tech)));
                daneKoloweDoTabeli.add(new zbiorczeDaneWtryskarki("Postój zaplanowany",calculateTime(kol_postoj)));
                daneKoloweDoTabeli.add(new zbiorczeDaneWtryskarki("Przezbrajanie",calculateTime(kol_przezbrajanie)));
                daneKoloweDoTabeli.add(new zbiorczeDaneWtryskarki("Suszenie materiału",calculateTime(kol_susz_m)));
                daneKoloweDoTabeli.add(new zbiorczeDaneWtryskarki("Nie zgłoszono",calculateTime(kol_postoj_n)));
                daneKoloweDoTabeli.add(new zbiorczeDaneWtryskarki("Awaria maszyny",calculateTime(kol_awaria_m)));
                daneKoloweDoTabeli.add(new zbiorczeDaneWtryskarki("Awaria formy",calculateTime(kol_awaria_f)));
                daneKoloweDoTabeli.add(new zbiorczeDaneWtryskarki("Brak zaopatrzenia",calculateTime(kol_brak_zaop)));
                daneKoloweDoTabeli.add(new zbiorczeDaneWtryskarki("Przerwa pracownika",calculateTime(kol_przerwa_p)));
                daneKoloweDoTabeli.add(new zbiorczeDaneWtryskarki("Brak operatora",calculateTime(kol_brak_oper)));
                daneKoloweDoTabeli.add(new zbiorczeDaneWtryskarki("Wybrak",calculateTime(kol_wybrak)));
                
                
     
                //System.out.println("DZIELNIK CZASU CYKLU przed if: "+dzielnikCzasuCyklu);
                /*
                if(sr_czas_cykl > 0)
                {
                   
                double sredniCzasCyklu = sr_czas_cykl;
                    
                daneKoloweDoTabeli.add(new zbiorczeDaneWtryskarki("czas cyklu [s]",(round(sredniCzasCyklu,3)+"").replace(".", ",")));
                }
                else
                {
                    daneKoloweDoTabeli.add(new zbiorczeDaneWtryskarki("czas cyklu [s]","0"));
                }
                */
                
                 if(nie_wybrano_maszyn)
                {
            //RUNNABLE_RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_        
            Platform.runLater(new Runnable()
            {

                @Override

                public void run()

                {
                    WykresSlupkowy.setVisible(false);
                    WykresKolowy.setVisible(false);
                    WykresLiniowy.setVisible(false);
                    TableCykle.setVisible(false);
                    TableCykleZbiorczo.setVisible(false);
                    LABpodglad.setText("Brak danych do wyświetlenia o maszynach z wybranego przedziału czasu");
                }

            });//END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_

                }
                else
                {

                    //RUNNABLE_RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_       
                    Platform.runLater(new Runnable()

                    {

                    @Override

                    public void run()

                    {
                    
                    WykresKolowy.getData().addAll(daneWykresKolowy);
                    //KOLEJNOŚĆ wykres słupkowy
                    WykresSlupkowy.getData().addAll(s_wtrysk,s_proby_tech,s_postoj,s_przezbrajanie,s_susz_m,s_postoj_n,s_awaria_m,s_awaria_f,s_brak_zaop,s_przerwa_p,s_brak_oper,s_wybrak); 
                    WykresLiniowy.getData().addAll(l_czas_cykl);
                    
                    TableCykle.setSelectionModel(null);
                    TableCykle.setItems(daneSlupkoweDoTabeli);
                    //KOLEJNOŚĆ TABELA wykres słupkowy
                    TableCykle.getColumns().addAll(TBCmaszyna,TBCwtrysk,TBCproby_tech,TBCpostoj,TBCprzezbrajanie,TBCsusz_m,TBCpostoj_n,TBCawaria_m,TBCawaria_f,TBCbrak_zaop,TBCprzerwa_p,TBCbrak_oper,TBCwybrak,TBCczas_cyklu);
                    TableCykle.setMinWidth(1000);
                    TableCykle.setMinHeight(325);
                    TableCykle.autosize();
                    
                    TableCykleZbiorczo.setSelectionModel(null);
                    TableCykleZbiorczo.setItems(daneKoloweDoTabeli);
                    TableCykleZbiorczo.getColumns().addAll(TBZnazwa,TBZwartosc);
                    TableCykleZbiorczo.setMinWidth(200);
                    TableCykleZbiorczo.setMinHeight(270);
                    TableCykleZbiorczo.autosize();
            
                    WykresSlupkowy.setVisible(true);
                    WykresKolowy.setVisible(true);
                    WykresLiniowy.setVisible(true);
                    TableCykle.setVisible(true);
                    TableCykleZbiorczo.setVisible(true);
                    
                    
                    //POPRAWIANIE KOLOROW
                    poprawKolory();
                      
                    LABpodglad.setText("Podgląd maszyn \nz przedziału czasu od: " + Timestamp.valueOf(infoDateTime_od).toString().substring(0, 16)+" do: "+Timestamp.valueOf(infoDateTime_do).toString().substring(0, 16));
                    //LINECHART
                    
                    }

                    });//END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_

                        
                }
                
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                conn.close();
                
                //RUNNABLE_RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_       
                Platform.runLater(new Runnable()

                {

                    @Override
                    public void run()

                    {
                        WykresSlupkowy.requestLayout();
                        WykresSlupkowy.getYAxis().setLabel("Wartości");
                        WykresSlupkowy.getXAxis().setLabel("Godziny");
                        WykresLiniowy.setId("czas_cyklu");
                        WykresLiniowy.getYAxis().setLabel("Czas cyklu [s]");
                        WykresLiniowy.getXAxis().setLabel("Godziny");
                                
                        addWykresGodzinEventsListener();
                    }

                });//END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_


            
            }
            catch (NumberFormatException | SQLException ex) {
                Platform.runLater(new Runnable()
            {
            @Override

                public void run()

                {
                WykresSlupkowy.setVisible(false);
                WykresKolowy.setVisible(false);
                WykresLiniowy.setVisible(false);
                TableCykle.setVisible(false);
                TableCykleZbiorczo.setVisible(false);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                alertStage.getIcons().add(new Image(this.getClass().getResource("img/icon48.png").toString()));
                alert.setTitle("TECHNIPLAST Analizator pracy wtryskarek: Błąd");
                alert.setHeaderText("Problem bazy danych SQL");
                alert.setContentText("Nie można wczytać danych");

                alert.showAndWait();
                System.err.println("Błąd: " + ex.getMessage());
                             }

            });//END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_
            }
            finally
            {
            //RUNNABLE_RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ 
            Platform.runLater(new Runnable()
            {
            @Override

                public void run()

                {
                    ProgressBar.setVisible(false);
                    Lstatus.setText("TECHNIPLAST");
                    BTNwczytaj.setDisable(false);
                }

            });//END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_

            }// za try          
           
            
             
    }// WCZYTAJ WYKRES GODZIN
    
    @FXML
    private void BTNwczytajAction(ActionEvent event) 
    {  
        Lstatus.setText("Ładowanie danych...");
        ProgressBar.setVisible(true);
        ProgressBar.setProgress(0);
        BTNwczytaj.setDisable(true);
        
        if(jestInternet())
        {
            Runnable taskWczytywanie = new Runnable()
            {
                public void run()

                {

                    wczytajWykresMaszyn();
         
                }

            };

            Thread wczytywanieDanych = new Thread(taskWczytywanie);
            wczytywanieDanych.setName("Analiza obciazenia-wczytywanie danych");
            wczytywanieDanych.setDaemon(true);
            wczytywanieDanych.start();
        }
        else
        {
            ProgressBar.setVisible(false);
            Lstatus.setText("TECHNIPLAST");
            BTNwczytaj.setDisable(false);
        }

            
            
      

}//BTNwczytajAction
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        ustawDate();
        aktualizujTreeView();
        Lstatus.setText("TECHNIPLAST");
        ProgressBar.setVisible(false);
        infoDialog.setVisible(false);
        TableCykle.setVisible(false);
        SLDczas_ladowania.setShowTickLabels(true);
        SLDczas_ladowania.setShowTickMarks(true);
        TableCykleZbiorczo.setVisible(false);
        infoDialog.setBackground(new Background(new BackgroundFill(Color.color(1, 1, 1, 0.8), new CornerRadii(30), Insets.EMPTY)));
        //splitPane.setDividerPositions((150/ splitPane.getWidth()),0.8);
        wybrana_analiza = J_ANALIZY.GODZINA;
        SLDczas_ladowania.setMin(0);
        SLDczas_ladowania.setMax(24);
        SLDczas_ladowania.setValue(12);
        SLDczas_ladowania.setMajorTickUnit(12);
        SLDczas_ladowania.setMinorTickCount(11);
        stageSizeChageListener(splitPane);
        RBustawGodzine.getToggleGroup().selectToggle(RBustawGodzine);

        //DODAWANIE MASZYN DO WYBORU
        /*
        CBmaszyna.getItems().add("ARBURG_34");
        CBmaszyna.getItems().add("ARBURG_35");
        
        CBmaszyna.setValue("ARBURG_34");
        */
        //IstniejaceMaszyny.LadujIstniejaceMaszyny(CBmaszyna);
        
        
        WykresSlupkowy.requestLayout();
        WykresSlupkowy.getYAxis().setLabel("Wartości");
        WykresSlupkowy.getYAxis().autosize();

             
        WykresSlupkowy.getXAxis().setLabel("Godziny");
        WykresSlupkowy.getXAxis().autosize();
        
        WykresLiniowy.setId("czas_cyklu");
        WykresLiniowy.requestLayout();
        WykresLiniowy.getYAxis().setLabel("Czas cyklu [s]");
        WykresLiniowy.getYAxis().autosize();

             
        WykresLiniowy.getXAxis().setLabel("Godziny");
        WykresLiniowy.getXAxis().autosize();
       
        
        //wczytajWykresGodzin();

       
        Lstatus.setText("TECHNIPLAST");

        addWykresGodzinEventsListener();
        
        TBgodzina_od._24HourViewProperty().set(true);
        infoDialog.setOpacity(100);
        
        WykresSlupkowy.getXAxis().setAnimated(false);
        WykresSlupkowy.getXAxis().setAutoRanging(true);
        WykresSlupkowy.getYAxis().setAnimated(false);
        WykresSlupkowy.getYAxis().setAutoRanging(true);
        
        // WYLACZENIE DZIADOSTWA
        WykresLiniowy.getXAxis().setAnimated(false);
        WykresLiniowy.getXAxis().setAutoRanging(true);
        WykresLiniowy.getYAxis().setAnimated(false);
        WykresLiniowy.getYAxis().setAutoRanging(true);
       // WykresKolowy.setVisible(true);
        BTNwczytaj.setText("");
        BTNwczytaj.setId("BTNszukaj");
        BTNwczytaj.setEffect(null);
        
        BTNhide.setText("");
        BTNhide.setId("BTNhide");
        BTNhide.setEffect(null);
        opcjePane.widthProperty().addListener((obs, oldVal, newVal) -> {

        });
        
        opcjePane.heightProperty().addListener((obs, oldVal, newVal) -> {
            //BTNwczytaj.setLayoutY(obs.getValue().doubleValue() - (BTNwczytaj.getHeight()*2));
        //System.out.println(obs.getValue());
        });
        WykresSlupkowy.setVisible(false);
        WykresKolowy.setVisible(false);
        WykresLiniowy.setVisible(false);
        TableCykle.setVisible(false);
        TableCykleZbiorczo.setVisible(false);
        LABpodglad.setText("\nWybierz wtryskarki, ustaw date i godzinę, oraz okres czasu analizy");
     
        
        //wczytajWykresMaszyn();
        zaladowano_okno = true;
    }
    private void setStyle()
    {
         for (Node n: WykresSlupkowy.lookupAll(".default-color0.chart-bar")) n.setStyle(" -fx-bar-fill: #00bbfa;\n" +
"    -fx-background-color: linear-gradient(derive(-fx-bar-fill,-30%), derive(-fx-bar-fill,-40%)),\n" +
"                          linear-gradient(derive(-fx-bar-fill,80%), derive(-fx-bar-fill, 0%)),\n" +
"                          linear-gradient(derive(-fx-bar-fill,30%), derive(-fx-bar-fill,-10%));\n" +
"    -fx-background-insets: 0,1,2;\n" +
"    -fx-background-radius: 5 5 0 0, 4 4 0 0, 3 3 0 0;");
         
         for (Node n: WykresSlupkowy.lookupAll(".default-color0.chart-legend-item-symbol")) n.setStyle(" -fx-bar-fill: #00bbfa;\n" +
"    -fx-background-color: linear-gradient(derive(-fx-bar-fill,-30%), derive(-fx-bar-fill,-40%)),\n" +
"                          linear-gradient(derive(-fx-bar-fill,80%), derive(-fx-bar-fill, 0%)),\n" +
"                          linear-gradient(derive(-fx-bar-fill,30%), derive(-fx-bar-fill,-10%));\n" +
"    -fx-background-insets: 0,1,2;\n" +
"    -fx-background-radius: 5 5 0 0, 4 4 0 0, 3 3 0 0;");
         
             
             
             for (Node n: WykresKolowy.lookupAll(".data0.chart-pie")) n.setStyle(" -fx-bar-fill: #00bbfa;\n" +
"    -fx-background-color: linear-gradient(derive(-fx-bar-fill,-30%), derive(-fx-bar-fill,-40%)),\n" +
"                          linear-gradient(derive(-fx-bar-fill,80%), derive(-fx-bar-fill, 0%)),\n" +
"                          linear-gradient(derive(-fx-bar-fill,30%), derive(-fx-bar-fill,-10%));\n" +
"    -fx-background-insets: 0,1,2;\n" +
"    -fx-background-radius: 5 5 0 0, 4 4 0 0, 3 3 0 0;");
             
             
    }
    private void addWykresGodzinEventsListener()
    {
        
     
        
        
    // DYMEK WYKRES KOŁOWY
    for (PieChart.Data item: WykresKolowy.getData()){
        item.getNode().setOnMouseEntered((MouseEvent event) -> {
            //System.out.println("you clicked "+item.toString());
            String nazwa =  item.toString().substring(item.toString().indexOf("[") + 1, item.toString().indexOf(","));
            String wartosc = item.toString().substring(item.toString().indexOf(",")+1,item.toString().length()-1);
            //System.out.println(nazwa+  ": "+wartosc);
            
            textLabelDialog = nazwa;
               
            labelDialog.setText(textLabelDialog);
            infoDialog.setVisible(true);
            item.getNode().setEffect(new Glow(0.9));
        });
            
    }
    ///////////////////////////////////////////////////////////////////////
    for (PieChart.Data item: WykresKolowy.getData()){
        item.getNode().setOnMouseExited((MouseEvent event) -> {
            textLabelDialog = "";
            infoDialog.setVisible(false);
            item.getNode().setEffect(null);
        });
            
    }
    //POZYCJA DYMEK WYKRES KOŁOWY
    for (PieChart.Data item: WykresKolowy.getData()){
        item.getNode().setOnMouseMoved((MouseEvent event) -> {
            //STARE LICZENIE
             //infoDialog.setLayoutX(event.getX()-opcjePane.getWidth()-(infoDialog.getWidth()/2));
             //infoDialog.setLayoutY(event.getY()-infoDialog.getHeight()-50);
               
             //infoDialog.setLayoutX(WykresKolowy.getLayoutX()+event.getX()+(WykresKolowy.getWidth()));    
             //infoDialog.setLayoutY(WykresKolowy.getLayoutY()+event.getY()+(50));
             
             infoDialog.setLayoutX(item.getNode().getLayoutX()+event.getX()+WykresKolowy.getLayoutX()-(infoDialog.getWidth()/2));    
             infoDialog.setLayoutY(item.getNode().getLayoutY()+event.getY()+WykresKolowy.getLayoutY()-(infoDialog.getHeight()));
             infoDialog.toFront();
             
        });
            
    }
    // DYMEK WYKRES SŁUPKOWY
    for (Series<?, ?> serie: WykresSlupkowy.getData()){
        for (XYChart.Data<?, ?> item: serie.getData()){
            item.getNode().setOnMouseEntered((MouseEvent event) -> {
                //System.out.println("you clicked "+item.toString()+serie.toString());
                if(serie.toString().length() >0)
                {
                     System.out.println(item.toString());
                String maszyna = item.toString().substring(item.toString().indexOf("[") + 1, item.toString().indexOf(","));
                String nazwa = serie.toString().substring(serie.toString().indexOf("[") + 1, serie.toString().indexOf("]"));
                String wartosc = item.toString().substring(item.toString().indexOf(",") + 1, item.toString().indexOf(",null"));
                
                //System.out.println("Godzina: "+godzina);
                //System.out.println(nazwa+": "+wartosc);
                
                textLabelDialog = ""+maszyna+"\n"+nazwa+": "+wartosc;
               
                labelDialog.setText(textLabelDialog);
                infoDialog.setVisible(true);
                //infoDialog.localToParent(new Point2D(event.getX(),event.getY()));
                
                }
                item.getNode().setEffect(new Glow(0.9));
                });
            }
        }
    
    for (Series<?, ?> serie: WykresSlupkowy.getData()){
        for (XYChart.Data<?, ?> item: serie.getData()){
            item.getNode().setOnMouseExited((MouseEvent event) -> {      
                item.getNode().setEffect(null);
                textLabelDialog = "";
                infoDialog.setVisible(false);
                });
            }
        }
    //POZYCJA DYMEK WYKRES SŁUPKOWY
    for (Series<?, ?> serie: WykresSlupkowy.getData()){
        for (XYChart.Data<?, ?> item: serie.getData()){
            item.getNode().setOnMouseMoved((MouseEvent event) -> {      
     
                //infoDialog.setLayoutX(event.getSceneX()-opcjePane.getWidth()-(infoDialog.getWidth()/2));
                //infoDialog.setLayoutY(event.getSceneY()-infoDialog.getHeight()-50+podgladScrollPane.getHvalue());
                
                //infoDialog.setLayoutX(WykresSlupkowy.getLayoutX()+event.getX()+(WykresSlupkowy.getWidth()/2));    
                // infoDialog.setLayoutY(WykresSlupkowy.getLayoutY()+event.getY()+(50));
                 
                 //infoDialog.setLayoutX(item.getNode().getLayoutX()+event.getX()+(0));    
                 //infoDialog.setLayoutY(item.getNode().getLayoutY()+event.getY()+(20));
                 
                infoDialog.setLayoutX(item.getNode().getLayoutX()+event.getX()+WykresSlupkowy.getLayoutX()-(WykresSlupkowy.getYAxis().getWidth()/2));    
                infoDialog.setLayoutY(item.getNode().getLayoutY()+event.getY()+WykresSlupkowy.getLayoutY()-(infoDialog.getHeight()));
                


                infoDialog.toFront();
                });
            }
        }
    // DYMEK WYKRES LINIOWY
     for (Series<?, ?> serie: WykresLiniowy.getData()){
        for (XYChart.Data<?, ?> item: serie.getData()){
            item.getNode().setOnMouseEntered((MouseEvent event) -> {
                //System.out.println("you clicked "+item.toString()+serie.toString());
                if(serie.toString().length() >0)
                {
                   
                String maszyna = item.toString().substring(item.toString().indexOf("[") + 1, item.toString().indexOf(","));
                //String nazwa = serie.toString().substring(serie.toString().indexOf("[") + 1, serie.toString().indexOf("]"));
                String nazwa = "średni czas cyklu";
                String wartosc = ""+round(Double.parseDouble(item.toString().substring(item.toString().indexOf(",")+1, item.toString().indexOf(",null"))),2);
                
                //System.out.println("Godzina: "+godzina);
                //System.out.println(nazwa+": "+wartosc);
                
                textLabelDialog = ""+maszyna+"\n"+nazwa+": \n"+wartosc;
                
               
                labelDialog.setText(textLabelDialog);
                infoDialog.setVisible(true);
                //infoDialog.localToParent(new Point2D(event.getX(),event.getY()));
                
                }
                item.getNode().setEffect(new Glow(0.9));
                });
            }
        }
    
    for (Series<?, ?> serie: WykresLiniowy.getData()){
        for (XYChart.Data<?, ?> item: serie.getData()){
            item.getNode().setOnMouseExited((MouseEvent event) -> {      
                item.getNode().setEffect(null);
                textLabelDialog = "";
                infoDialog.setVisible(false);
                });
            }
        }
    //POZYCJA DYMEK WYKRES LINIOWY
    for (Series<?, ?> serie: WykresLiniowy.getData()){
        for (XYChart.Data<?, ?> item: serie.getData()){
            item.getNode().setOnMouseMoved((MouseEvent event) -> {      
     
                //infoDialog.setLayoutX(event.getSceneX()-opcjePane.getWidth()-(infoDialog.getWidth()/2));
                //infoDialog.setLayoutY(event.getSceneY()-infoDialog.getHeight()-50+podgladScrollPane.getHvalue());
                infoDialog.setLayoutX(item.getNode().getLayoutX()+event.getX()+WykresLiniowy.getLayoutX()-(WykresLiniowy.getYAxis().getWidth()/2));    
                infoDialog.setLayoutY(item.getNode().getLayoutY()+event.getY()+WykresLiniowy.getLayoutY()-(infoDialog.getHeight()));
                


                infoDialog.toFront();
                });
            }
        }
    
    
    //ZAPISYWANIE JAKO OBRAZ wykres słupkowy
        contextMenuWykresSlupkowy = new ContextMenu();
        MenuItem zapiszJakoSlupkowyItem = new MenuItem("Zapisz wykres słupkowy jako obraz");
        contextMenuWykresSlupkowy.getItems().add(zapiszJakoSlupkowyItem);
        contextMenuWykresSlupkowy.getItems().add(createScrollPaneZapiszObrazMenuItem());
        
        //zapisywanie słupkowego jako PNG
        zapiszJakoSlupkowyItem.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            WritableImage image = WykresSlupkowy.snapshot(new SnapshotParameters(), null);
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Zapisz wykres słupkowy jako PNG");
            fileChooser.setInitialFileName("Wykres słupkowy.png");
             fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            if(CykleApp.rootPref.get("SAVE_DIR", "")!= "")
                {
                fileChooser.setInitialDirectory(new File(CykleApp.rootPref.get("SAVE_DIR", "")));
                } 
            File fileSlupkowy = fileChooser.showSaveDialog(contextMenuWykresSlupkowy);
            CykleApp.rootPref.put("SAVE_DIR", fileSlupkowy.getParent());
             try {
                    if(fileSlupkowy != null)
                    {
                     ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", fileSlupkowy);
                    }
                 } 
             catch (IOException e) 
             {
                 System.err.println("Błąd: " + e.getMessage());
             }
            
        }
        });
       //ZAPISYWANIE JAKO OBRAZ wykres kołowy
        contextMenuWykresKolowy = new ContextMenu();
        MenuItem zapiszJakoKolowyItem = new MenuItem("Zapisz wykres czasowy jako obraz");
        contextMenuWykresKolowy.getItems().add(zapiszJakoKolowyItem);
        contextMenuWykresKolowy.getItems().add(createScrollPaneZapiszObrazMenuItem());
        
         //ZAPISYWANIE JAKO OBRAZ wykres kołowy
        zapiszJakoKolowyItem.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            WritableImage image = WykresKolowy.snapshot(new SnapshotParameters(), null);
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Zapisz wykres czasowy jako PNG");
            fileChooser.setInitialFileName("Wykres czasowy.png");
             fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            if(CykleApp.rootPref.get("SAVE_DIR", "")!= "")
                {
                fileChooser.setInitialDirectory(new File(CykleApp.rootPref.get("SAVE_DIR", "")));
                } 
            File fileKolowy = fileChooser.showSaveDialog(contextMenuWykresKolowy);
            CykleApp.rootPref.put("SAVE_DIR", fileKolowy.getParent());
            
             try {
                     if(fileKolowy != null)
                     {
                     ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", fileKolowy);
                     }
                 } 
             catch (IOException e) 
             {
                 System.err.println("Błąd: " + e.getMessage());
             }
            
        }
        });
         //ZAPISYWANIE JAKO OBRAZ wykres liniowy
        contextMenuWykresLiniowy = new ContextMenu();
        MenuItem zapiszJakoCzasCykluItem = new MenuItem("Zapisz wykres liniowy jako obraz");
        contextMenuWykresLiniowy.getItems().add(zapiszJakoCzasCykluItem);
        contextMenuWykresLiniowy.getItems().add(createScrollPaneZapiszObrazMenuItem());
        
         //zapisywanie kołowego jako PNG
        zapiszJakoCzasCykluItem.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            WritableImage image = WykresLiniowy.snapshot(new SnapshotParameters(), null);
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Zapisz wykres czasu cykli jako PNG");
            fileChooser.setInitialFileName("Średni czas cyklu.png");
             fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            if(CykleApp.rootPref.get("SAVE_DIR", "")!= "")
                {
                fileChooser.setInitialDirectory(new File(CykleApp.rootPref.get("SAVE_DIR", "")));
                } 
            File fileKolowy = fileChooser.showSaveDialog(contextMenuWykresLiniowy);
            CykleApp.rootPref.put("SAVE_DIR", fileKolowy.getParent());
            
             try {
                     if(fileKolowy != null)
                     {
                     ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", fileKolowy);
                     }
                 } 
             catch (IOException e) 
             {
                 System.err.println("Błąd: " + e.getMessage());
             }
            
        }
        });
        //ZAPISYWANIE TABELI WYKRESU SŁUPKOWEGO JAKO PNG i XLS
        contextMenuTableCykle = new ContextMenu();
        MenuItem zapiszJakoTableCykleMenuItem = new MenuItem("Zapisz tabelę jako obraz");
        MenuItem zapiszJakoXLSTableCykleMenuItem = new MenuItem("Zapisz tabelę jako XLS");
        contextMenuTableCykle.getItems().add(zapiszJakoTableCykleMenuItem);
        contextMenuTableCykle.getItems().add(zapiszJakoXLSTableCykleMenuItem);
 
        
         //zapisywanie kołowego jako PNG
        zapiszJakoTableCykleMenuItem.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            WritableImage image = TableCykle.snapshot(new SnapshotParameters(), null);
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Zapisz tabelę jako PNG");
            fileChooser.setInitialFileName("Tabela danych.png");
             fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            if(CykleApp.rootPref.get("SAVE_DIR", "")!= "")
                {
                fileChooser.setInitialDirectory(new File(CykleApp.rootPref.get("SAVE_DIR", "")));
                } 
            File fileKolowy = fileChooser.showSaveDialog(contextMenuTableCykle);
            CykleApp.rootPref.put("SAVE_DIR", fileKolowy.getParent());
            
             try {
                     if(fileKolowy != null)
                     {
                     ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", fileKolowy);
                     }
                 } 
             catch (IOException e) 
             {
                 System.err.println("Błąd: " + e.getMessage());
             }
            
        }

        });
         //ZAPISYWANIE TABELI JAKO XLS
        zapiszJakoXLSTableCykleMenuItem.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            System.out.println("ZAPISYWANIE TABELI");
            WritableImage image = TableCykle.snapshot(new SnapshotParameters(), null);
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Zapisz tabelę jako XLS");
            fileChooser.setInitialFileName("Tabela danych.xls");
             fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XLS", "*.xls")
            );
            if(CykleApp.rootPref.get("SAVE_DIR", "")!= "")
                {
                fileChooser.setInitialDirectory(new File(CykleApp.rootPref.get("SAVE_DIR", "")));
                } 
            File fileXLS = fileChooser.showSaveDialog(contextMenuTableCykle);
            CykleApp.rootPref.put("SAVE_DIR", fileXLS.getParent());
   
            if(fileXLS != null)
                 {
                 writeExcelDaneWtryskarki(fileXLS);
                 }

        }

        });
        //ZAPISYWANIE TABELI WYKRESU KOŁOWEGO JAKO PNG
        contextMenuTableCykleZbiorczo = new ContextMenu();
        MenuItem zapiszJakoTableCykleZbiorczoMenuItem = new MenuItem("Zapisz tabelę jako obraz");
        MenuItem zapiszJakoXLSTableCykleZbiorczoMenuItem = new MenuItem("Zapisz tabelę jako XLS");
        contextMenuTableCykleZbiorczo.getItems().add(zapiszJakoTableCykleZbiorczoMenuItem);
        contextMenuTableCykleZbiorczo.getItems().add(zapiszJakoXLSTableCykleZbiorczoMenuItem);
 
        
         //zapisywanie kołowego jako PNG
        zapiszJakoTableCykleZbiorczoMenuItem.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            WritableImage image = TableCykleZbiorczo.snapshot(new SnapshotParameters(), null);
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Zapisz tabelę jako PNG");
            fileChooser.setInitialFileName("Tabela danych czasowych.png");
             fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            if(CykleApp.rootPref.get("SAVE_DIR", "")!= "")
                {
                fileChooser.setInitialDirectory(new File(CykleApp.rootPref.get("SAVE_DIR", "")));
                } 
            File fileKolowy = fileChooser.showSaveDialog(contextMenuTableCykleZbiorczo);
            CykleApp.rootPref.put("SAVE_DIR", fileKolowy.getParent());
            
             try {
                     if(fileKolowy != null)
                     {
                     ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", fileKolowy);
                     }
                 } 
             catch (IOException e) 
             {
                 System.err.println("Błąd: " + e.getMessage());
             }
            
        }
        });
         //ZAPISYWANIE TABELI JAKO XLS
        zapiszJakoXLSTableCykleZbiorczoMenuItem.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            
            FileChooser fileChooser = new FileChooser();
            
            fileChooser.setTitle("Zapisz tabelę jako XLS");
            fileChooser.setInitialFileName("Tabela danych czasowych.xls");
             fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XLS", "*.xls")
            );
             if(CykleApp.rootPref.get("SAVE_DIR", "")!= "")
                {
                fileChooser.setInitialDirectory(new File(CykleApp.rootPref.get("SAVE_DIR", "")));
                }
             
            File fileXLS = fileChooser.showSaveDialog(contextMenuTableCykle);
            CykleApp.rootPref.put("SAVE_DIR", fileXLS.getParent());
            if(fileXLS != null)
                     {
                     writeExcelDaneWtryskarkiZbiorcze(fileXLS);
                     }
            
        }

        });
    }//WYKRES GODZIN EVENT LISTENER
    
     private void addWykresDniEventsListener()
    {
        
     
        
        
    // DYMEK WYKRES KOŁOWY
    for (PieChart.Data item: WykresKolowy.getData()){
        item.getNode().setOnMouseEntered((MouseEvent event) -> {
            //System.out.println("you clicked "+item.toString());
            String nazwa =  item.toString().substring(item.toString().indexOf("[") + 1, item.toString().indexOf(","));
            String wartosc = item.toString().substring(item.toString().indexOf(",")+1,item.toString().length()-1);
            //System.out.println(nazwa+  ": "+wartosc);
            
            textLabelDialog = nazwa+  ": "+wartosc;
               
            labelDialog.setText(textLabelDialog);
            infoDialog.setVisible(true);
            item.getNode().setEffect(new Glow(0.9));
        });
            
    }
    ///////////////////////////////////////////////////////////////////////
    for (PieChart.Data item: WykresKolowy.getData()){
        item.getNode().setOnMouseExited((MouseEvent event) -> {
            textLabelDialog = "";
            infoDialog.setVisible(false);
            item.getNode().setEffect(null);
        });
            
    }
    //POZYCJA DYMEK WYKRES KOŁOWY
    for (PieChart.Data item: WykresKolowy.getData()){
        item.getNode().setOnMouseMoved((MouseEvent event) -> {
            //STARE LICZENIE
             //infoDialog.setLayoutX(event.getX()-opcjePane.getWidth()-(infoDialog.getWidth()/2));
             //infoDialog.setLayoutY(event.getY()-infoDialog.getHeight()-50);
               
             //infoDialog.setLayoutX(WykresKolowy.getLayoutX()+event.getX()+(WykresKolowy.getWidth()));    
             //infoDialog.setLayoutY(WykresKolowy.getLayoutY()+event.getY()+(50));
             
             infoDialog.setLayoutX(item.getNode().getLayoutX()+event.getX()+WykresKolowy.getLayoutX()-(infoDialog.getWidth()/2));    
             infoDialog.setLayoutY(item.getNode().getLayoutY()+event.getY()+WykresKolowy.getLayoutY()-(infoDialog.getHeight()));
             infoDialog.toFront();
             
        });
            
    }
    // DYMEK WYKRES SŁUPKOWY
    for (Series<?, ?> serie: WykresSlupkowy.getData()){
        for (XYChart.Data<?, ?> item: serie.getData()){
            item.getNode().setOnMouseEntered((MouseEvent event) -> {
                //System.out.println("you clicked "+item.toString()+serie.toString());
                if(serie.toString().length() >0)
                {
                String godzina = item.toString().substring(item.toString().indexOf("[") + 1, item.toString().indexOf(","));
                String nazwa = serie.toString().substring(serie.toString().indexOf("[") + 1, serie.toString().indexOf("]"));
                String wartosc = item.toString().substring(16, item.toString().indexOf(",null"));
                
                //System.out.println("Godzina: "+godzina);
                //System.out.println(nazwa+": "+wartosc);
                
                textLabelDialog = "Data: "+godzina+"\n"+nazwa+": "+wartosc;
               
                labelDialog.setText(textLabelDialog);
                infoDialog.setVisible(true);
                //infoDialog.localToParent(new Point2D(event.getX(),event.getY()));
                
                }
                item.getNode().setEffect(new Glow(0.9));
                });
            }
        }
    
    for (Series<?, ?> serie: WykresSlupkowy.getData()){
        for (XYChart.Data<?, ?> item: serie.getData()){
            item.getNode().setOnMouseExited((MouseEvent event) -> {      
                item.getNode().setEffect(null);
                textLabelDialog = "";
                infoDialog.setVisible(false);
                });
            }
        }
    //POZYCJA DYMEK WYKRES SŁUPKOWY
    for (Series<?, ?> serie: WykresSlupkowy.getData()){
        for (XYChart.Data<?, ?> item: serie.getData()){
            item.getNode().setOnMouseMoved((MouseEvent event) -> {      
     
                //infoDialog.setLayoutX(event.getSceneX()-opcjePane.getWidth()-(infoDialog.getWidth()/2));
                //infoDialog.setLayoutY(event.getSceneY()-infoDialog.getHeight()-50+podgladScrollPane.getHvalue());
                
                //infoDialog.setLayoutX(WykresSlupkowy.getLayoutX()+event.getX()+(WykresSlupkowy.getWidth()/2));    
                // infoDialog.setLayoutY(WykresSlupkowy.getLayoutY()+event.getY()+(50));
                 
                 //infoDialog.setLayoutX(item.getNode().getLayoutX()+event.getX()+(0));    
                 //infoDialog.setLayoutY(item.getNode().getLayoutY()+event.getY()+(20));
                 
                infoDialog.setLayoutX(item.getNode().getLayoutX()+event.getX()+WykresSlupkowy.getLayoutX()-(WykresSlupkowy.getYAxis().getWidth()/2));    
                infoDialog.setLayoutY(item.getNode().getLayoutY()+event.getY()+WykresSlupkowy.getLayoutY()-(infoDialog.getHeight()));
                


                infoDialog.toFront();
                });
            }
        }
    // DYMEK WYKRES LINIOWY
     for (Series<?, ?> serie: WykresLiniowy.getData()){
        for (XYChart.Data<?, ?> item: serie.getData()){
            item.getNode().setOnMouseEntered((MouseEvent event) -> {
                //System.out.println("you clicked "+item.toString()+serie.toString());
                if(serie.toString().length() >0)
                {
                String godzina = item.toString().substring(item.toString().indexOf("[") + 1, item.toString().indexOf(","));
                String nazwa = serie.toString().substring(serie.toString().indexOf("[") + 1, serie.toString().indexOf("]"));
                String wartosc = ""+round(Double.parseDouble(item.toString().substring(16, item.toString().indexOf(",null"))),2);
                
                //System.out.println("Godzina: "+godzina);
                //System.out.println(nazwa+": "+wartosc);
                
                textLabelDialog = "Data: "+godzina+"\n"+nazwa+": \n"+wartosc;
               
                labelDialog.setText(textLabelDialog);
                infoDialog.setVisible(true);
                //infoDialog.localToParent(new Point2D(event.getX(),event.getY()));
                
                }
                item.getNode().setEffect(new Glow(0.9));
                });
            }
        }
    
    for (Series<?, ?> serie: WykresLiniowy.getData()){
        for (XYChart.Data<?, ?> item: serie.getData()){
            item.getNode().setOnMouseExited((MouseEvent event) -> {      
                item.getNode().setEffect(null);
                textLabelDialog = "";
                infoDialog.setVisible(false);
                });
            }
        }
    //POZYCJA DYMEK WYKRES LINIOWY
    for (Series<?, ?> serie: WykresLiniowy.getData()){
        for (XYChart.Data<?, ?> item: serie.getData()){
            item.getNode().setOnMouseMoved((MouseEvent event) -> {      
     
                //infoDialog.setLayoutX(event.getSceneX()-opcjePane.getWidth()-(infoDialog.getWidth()/2));
                //infoDialog.setLayoutY(event.getSceneY()-infoDialog.getHeight()-50+podgladScrollPane.getHvalue());
                infoDialog.setLayoutX(item.getNode().getLayoutX()+event.getX()+WykresLiniowy.getLayoutX()-(WykresLiniowy.getYAxis().getWidth()/2));    
                infoDialog.setLayoutY(item.getNode().getLayoutY()+event.getY()+WykresLiniowy.getLayoutY()-(infoDialog.getHeight()));
                


                infoDialog.toFront();
                });
            }
        }
    
    
    //ZAPISYWANIE JAKO OBRAZ wykres słupkowy
        contextMenuWykresSlupkowy = new ContextMenu();
        MenuItem zapiszJakoSlupkowyItem = new MenuItem("Zapisz wykres słupkowy jako obraz");
        contextMenuWykresSlupkowy.getItems().add(zapiszJakoSlupkowyItem);
        contextMenuWykresSlupkowy.getItems().add(createScrollPaneZapiszObrazMenuItem());
        
        //zapisywanie słupkowego jako PNG
        zapiszJakoSlupkowyItem.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            WritableImage image = WykresSlupkowy.snapshot(new SnapshotParameters(), null);
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Zapisz wykres słupkowy jako PNG");
            fileChooser.setInitialFileName("Wykres słupkowy.png");
             fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            if(CykleApp.rootPref.get("SAVE_DIR", "")!= "")
                {
                fileChooser.setInitialDirectory(new File(CykleApp.rootPref.get("SAVE_DIR", "")));
                }

            File fileSlupkowy = fileChooser.showSaveDialog(contextMenuWykresSlupkowy);
            CykleApp.rootPref.put("SAVE_DIR", fileSlupkowy.getParent());
            
             try {
                    if(fileSlupkowy != null)
                    {
                     ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", fileSlupkowy);
                    }
                 } 
             catch (IOException e) 
             {
                 System.err.println("Błąd: " + e.getMessage());
             }
            
        }
        });
       //ZAPISYWANIE JAKO OBRAZ wykres kołowy
        contextMenuWykresKolowy = new ContextMenu();
        MenuItem zapiszJakoKolowyItem = new MenuItem("Zapisz wykres czasowy jako obraz");
        contextMenuWykresKolowy.getItems().add(zapiszJakoKolowyItem);
        contextMenuWykresKolowy.getItems().add(createScrollPaneZapiszObrazMenuItem());
        
         //ZAPISYWANIE JAKO OBRAZ wykres kołowy
        zapiszJakoKolowyItem.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            WritableImage image = WykresKolowy.snapshot(new SnapshotParameters(), null);
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Zapisz wykres czasowy jako PNG");
            fileChooser.setInitialFileName("Wykres czasowy.png");
             fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            if(CykleApp.rootPref.get("SAVE_DIR", "")!= "")
                {
                fileChooser.setInitialDirectory(new File(CykleApp.rootPref.get("SAVE_DIR", "")));
                } 
            File fileKolowy = fileChooser.showSaveDialog(contextMenuWykresKolowy);
            CykleApp.rootPref.put("SAVE_DIR", fileKolowy.getParent());
            
             try {
                     if(fileKolowy != null)
                     {
                     ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", fileKolowy);
                     }
                 } 
             catch (IOException e) 
             {
                 System.err.println("Błąd: " + e.getMessage());
             }
            
        }
        });
         //ZAPISYWANIE JAKO OBRAZ wykres liniowy
        contextMenuWykresLiniowy = new ContextMenu();
        MenuItem zapiszJakoCzasCykluItem = new MenuItem("Zapisz wykres liniowy jako obraz");
        contextMenuWykresLiniowy.getItems().add(zapiszJakoCzasCykluItem);
        contextMenuWykresLiniowy.getItems().add(createScrollPaneZapiszObrazMenuItem());
        
         //zapisywanie kołowego jako PNG
        zapiszJakoCzasCykluItem.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            WritableImage image = WykresLiniowy.snapshot(new SnapshotParameters(), null);
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Zapisz wykres czasu cykli jako PNG");
            fileChooser.setInitialFileName("Średni czas cyklu.png");
             fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            if(CykleApp.rootPref.get("SAVE_DIR", "")!= "")
                {
                fileChooser.setInitialDirectory(new File(CykleApp.rootPref.get("SAVE_DIR", "")));
                } 
            File fileKolowy = fileChooser.showSaveDialog(contextMenuWykresLiniowy);
            CykleApp.rootPref.put("SAVE_DIR", fileKolowy.getParent());
            
            
             try {
                     if(fileKolowy != null)
                     {
                     ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", fileKolowy);
                     }
                 } 
             catch (IOException e) 
             {
                 System.err.println("Błąd: " + e.getMessage());
             }
            
        }
        });
        //ZAPISYWANIE TABELI WYKRESU SŁUPKOWEGO JAKO PNG i XLS
        contextMenuTableCykle = new ContextMenu();
        MenuItem zapiszJakoTableCykleMenuItem = new MenuItem("Zapisz tabelę jako obraz");
        MenuItem zapiszJakoXLSTableCykleMenuItem = new MenuItem("Zapisz tabelę jako XLS");
        contextMenuTableCykle.getItems().add(zapiszJakoTableCykleMenuItem);
        contextMenuTableCykle.getItems().add(zapiszJakoXLSTableCykleMenuItem);
 
        
         //zapisywanie kołowego jako PNG
        zapiszJakoTableCykleMenuItem.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            WritableImage image = TableCykle.snapshot(new SnapshotParameters(), null);
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Zapisz tabelę jako PNG");
            fileChooser.setInitialFileName("Tabela danych.png");
             fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            if(CykleApp.rootPref.get("SAVE_DIR", "")!= "")
                {
                fileChooser.setInitialDirectory(new File(CykleApp.rootPref.get("SAVE_DIR", "")));
                } 
            File fileKolowy = fileChooser.showSaveDialog(contextMenuTableCykle);
            CykleApp.rootPref.put("SAVE_DIR", fileKolowy.getParent());
            
             try {
                     if(fileKolowy != null)
                     {
                     ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", fileKolowy);
                     }
                 } 
             catch (IOException e) 
             {
                 System.err.println("Błąd: " + e.getMessage());
             }
            
        }

        });
         //ZAPISYWANIE TABELI JAKO XLS
         /*
        zapiszJakoXLSTableCykleMenuItem.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            WritableImage image = TableCykle.snapshot(new SnapshotParameters(), null);
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Zapisz tabelę jako XLS");
            fileChooser.setInitialFileName("Tabela danych.xls");
             fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XLS", "*.xls")
            );
             
            
            File fileXLS = fileChooser.showSaveDialog(contextMenuTableCykle);
             if(fileXLS != null)
                     {
                     writeExcelDaneWtryskarki(fileXLS);
                     }
          
            
        }

        });*/
        //ZAPISYWANIE TABELI WYKRESU KOŁOWEGO JAKO PNG
        contextMenuTableCykleZbiorczo = new ContextMenu();
        MenuItem zapiszJakoTableCykleZbiorczoMenuItem = new MenuItem("Zapisz tabelę jako obraz");
        MenuItem zapiszJakoXLSTableCykleZbiorczoMenuItem = new MenuItem("Zapisz tabelę jako XLS");
        contextMenuTableCykleZbiorczo.getItems().add(zapiszJakoTableCykleZbiorczoMenuItem);
        contextMenuTableCykleZbiorczo.getItems().add(zapiszJakoXLSTableCykleZbiorczoMenuItem);
 
        
         //zapisywanie kołowego jako PNG
        zapiszJakoTableCykleZbiorczoMenuItem.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            WritableImage image = TableCykleZbiorczo.snapshot(new SnapshotParameters(), null);
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Zapisz tabelę jako PNG");
            fileChooser.setInitialFileName("Tabela danych czasowych.png");
             fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            if(CykleApp.rootPref.get("SAVE_DIR", "")!= "")
                {
                fileChooser.setInitialDirectory(new File(CykleApp.rootPref.get("SAVE_DIR", "")));
                } 
            File fileKolowy = fileChooser.showSaveDialog(contextMenuTableCykleZbiorczo);
            CykleApp.rootPref.put("SAVE_DIR", fileKolowy.getParent());
            
             try {
                     if(fileKolowy != null)
                     {
                     ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", fileKolowy);
                     }
                 } 
             catch (IOException e) 
             {
                 System.err.println("Błąd: " + e.getMessage());
             }
            
        }
        });
         //ZAPISYWANIE TABELI JAKO XLS
         /*
        zapiszJakoXLSTableCykleZbiorczoMenuItem.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            
            FileChooser fileChooser = new FileChooser();
            System.out.println("Preferences get"+ CykleApp.rootPref.get("SAVE_DIR", ""));
            if(CykleApp.rootPref.get("SAVE_DIR", "")!= "")
                {
                fileChooser.setInitialDirectory(new File(CykleApp.rootPref.get("SAVE_DIR", "")));
                }
            fileChooser.setTitle("Zapisz tabelę danych zbiorczych jako XLS");
            fileChooser.setInitialFileName("Tabela danych zbiorczych.xls");
             fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("XLS", "*.xls")
            );
             
            File fileXLS = fileChooser.showSaveDialog(contextMenuTableCykle);
            CykleApp.rootPref.put("SAVE_DIR", fileXLS.getPath());
            try {
                if(fileXLS != null)
                     {
                     writeExcelDaneWtryskarkiZbiorcze(fileXLS);
                     }
            } catch (Exception ex) {
                System.err.println("Błąd: " + ex.getMessage());
            }
            
        }

        });
         */
    }//WYKRES DNI EVENT LISTENER
    


    @FXML
    private void TBdata_odAction(ActionEvent event) 
    {
        localDateTime_od = LocalDateTime.of(TBdata_od.getValue(),LocalTime.of(TBgodzina_od.getValue().getHour(),0,0));
    }

    @FXML
    private void TBgodzina_odAction(ActionEvent event)
    {
        localDateTime_od = LocalDateTime.of(TBdata_od.getValue(),LocalTime.of(TBgodzina_od.getValue().getHour(),0,0));
        
        
    }


    @FXML
    private void WykresKolowyMouseEntered(MouseEvent event) {

    }

    @FXML
    private void slupkowyContextMenu(ContextMenuEvent event) {
        contextMenuWykresSlupkowy.show(WykresSlupkowy, event.getScreenX(), event.getScreenY());
    }

    @FXML
    private void kolowyContextMenu(ContextMenuEvent event) {
        contextMenuWykresKolowy.show(WykresKolowy, event.getScreenX(), event.getScreenY());
    }
    @FXML
    private void liniowyContextMenu(ContextMenuEvent event) {
        contextMenuWykresLiniowy.show(WykresLiniowy, event.getScreenX(), event.getScreenY());
    }

    @FXML
    private void BTNwczytajClicked(MouseEvent event) {
    }

    @FXML
    private void BTNwczytajMouseExited(MouseEvent event) {
       
        BTNwczytaj.setEffect(null);
        
    }

    @FXML
    private void BTNwczytajMouseEntered(MouseEvent event) {
        BTNwczytaj.setEffect(new Glow(0.2));
    }
    public void setStage(Stage st)
    {
        windowStage = st;
    }
    public SplitPane getSplitPane()
    {
        return splitPane;
    }
    private void stageSizeChageListener(SplitPane box){
        box.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                splitPane.setDividerPositions((przyciskiWidth/ splitPane.getWidth()),0.85);
                //splitPane.setDividerPosition(2, 0.8);
                //splitPane.setDividerPositions(0.01,0.80);
                
            }
        });

        box.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                splitPane.setDividerPositions((przyciskiWidth/ splitPane.getWidth()),0.85);
                //splitPane.setDividerPosition(2, 0.8);
                //splitPane.setDividerPositions(0,0.80);
                //BTNwczytaj.setLayoutY(oldValue.doubleValue());
                //System.out.println("height: "+oldValue.doubleValue());
            }
        });

    }//stageSizeChageListener
    public static double round(double value, int places) 
    {
    if (places < 0) throw new IllegalArgumentException();

    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
    }
    
    private void ustawDate()
    {
        localDateTime_od = LocalDateTime.now().minusHours(11).withMinute(0).withSecond(0);
        
        
        TBgodzina_od.setValue(LocalTime.of(localDateTime_od.getHour(), 0, 0));
       
        TBdata_od.setValue( LocalDate.of(localDateTime_od.getYear(), localDateTime_od.getMonth(),localDateTime_od.getDayOfMonth()));
        
        

    }

    @FXML
    private void RBustawGodzineAction(ActionEvent event) {
        TBgodzina_od.setDisable(false);
        TBgodzina_od.setEditable(true);
        
    }

    @FXML
    private void RBpierwszaZmianaAction(ActionEvent event) {
        TBgodzina_od.setValue(LocalTime.of(6, 0, 0));
        TBgodzina_od.setEditable(false);
        TBgodzina_od.setDisable(true);
        localDateTime_od = LocalDateTime.of(TBdata_od.getValue(),LocalTime.of(TBgodzina_od.getValue().getHour(),0,0));
        
    }

    @FXML
    private void RBdrugaZmianaAction(ActionEvent event) {
        TBgodzina_od.setValue(LocalTime.of(18, 0, 0));
        TBgodzina_od.setEditable(false);
        TBgodzina_od.setDisable(true);
        localDateTime_od = LocalDateTime.of(TBdata_od.getValue(),LocalTime.of(TBgodzina_od.getValue().getHour(),0,0));
    }
    private  MenuItem createScrollPaneZapiszObrazMenuItem()
    {
        MenuItem oknoZapiszObrazItem = new MenuItem("Zapisz wszystkie wykresy jako obraz");
        
        //zapisywanie słupkowego jako PNG
        oknoZapiszObrazItem.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            WritableImage image = podgladPane.snapshot(new SnapshotParameters(), null);
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Zapisz wykresy jako PNG");
            fileChooser.setInitialFileName("Wykresy.png");
             fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            if(CykleApp.rootPref.get("SAVE_DIR", "")!= "")
                {
                fileChooser.setInitialDirectory(new File(CykleApp.rootPref.get("SAVE_DIR", "")));
                } 
            File fileSlupkowy = fileChooser.showSaveDialog(contextMenuOkno);
            CykleApp.rootPref.put("SAVE_DIR", fileSlupkowy.getParent());
             try {
                    if(fileSlupkowy != null)
                    {
                     ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", fileSlupkowy);
                    }
                 } 
             catch (IOException e) 
             {
                 System.err.println("Błąd: " + e.getMessage());
             }
            
        }
        });
        return oknoZapiszObrazItem;
    }

    @FXML
    private void podgladScrollContextMenu(ContextMenuEvent event) {
        //contextMenuOkno.show(podgladScrollPane, event.getScreenX(), event.getScreenY());
    }

    @FXML
    private void podgladContextMenu(ContextMenuEvent event) {
        
    }

    private void zakonczMenuItemAction(ActionEvent event) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource("img/icon48.png").toString()));
        alert.setTitle("TECHNIPLAST Analizator pracy wtryskarek");
        alert.setHeaderText("Czy na pewno zamknąć program ?");
        
        
        ButtonType buttonTypeTak = new ButtonType("Tak",ButtonData.NO);
        ButtonType buttonTypeAnuluj = new ButtonType("Anuluj",ButtonData.YES);
        alert.getButtonTypes().setAll(buttonTypeAnuluj,buttonTypeTak);
        //alert.setContentText("Are you ok with this?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeTak){
            System.exit(0);
        } else {
            // ... user chose CANCEL or closed the dialog
        }
        
    }
    private void poprawKolory()
    {
           for (int i = 0; i < WykresSlupkowy.getData().size(); i++) {
                          for (Node node : WykresSlupkowy.lookupAll(".series" + i)) {
                              node.getStyleClass().add("default-color" + i);
                          }
                      }
                      
                                    int i = 0;
                     for (Node node : WykresSlupkowy.lookupAll(".chart-legend-item")) {
                         if (node instanceof Label && ((Label) node).getGraphic() != null) {

                             ((Label) node).getGraphic().getStyleClass().add("default-color" + i);
                         }
                         i++;
                     }
    }
    private void oProgramieMenuItemAction(ActionEvent event) {
        //Okienko o programie
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         Image logo = new Image(getClass().getResource("img/logo_male.png").toExternalForm());
         Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
         ImageView imageView = new ImageView(logo);
         alert.setGraphic(imageView);
         alertStage.getIcons().add(new Image(this.getClass().getResource("img/icon48.png").toString()));
         alert.setTitle("TECHNIPLAST Analizator pracy wtryskarek");
         
         alert.setHeaderText("Analizator pracy wtryskarek\nwersja 2018");
         
         
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
                                  URI mailto = new URI("mailto:marekmadyda@gmail.com?subject=Analizator pracy wtryskarek");
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
                                  URI mailto = new URI("mailto:marek_madyda@techniplast.pl?subject=Analizator pracy wtryskarek");
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
    private void TableCykleContextMenu(ContextMenuEvent event) {
        contextMenuTableCykle.show(TableCykle, event.getScreenX(), event.getScreenY());
    }

    @FXML
    private void TableCykleZbiorczoContextMenu(ContextMenuEvent event) {
        contextMenuTableCykleZbiorczo.show(TableCykle, event.getScreenX(), event.getScreenY());
    }
    
     public void writeExcelDaneWtryskarki(File file){
            if(file != null)
            {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Dane wtryskarek");  
            
            //HSSFCellStyle cellStyleData = workbook.createCellStyle();
            //CreationHelper createHelper = workbook.getCreationHelper();
            //cellStyleData.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd hh:mm:ss"));
            
            DataFormat formatNumber = workbook.createDataFormat();
            CellStyle styleNumber = workbook.createCellStyle();
             styleNumber.setDataFormat(formatNumber.getFormat("#,#"));
            
            
            //HSSFRow rowDate = sheet.createRow((short)0);
            //rowDate.createCell(0).setCellValue("Dane z przedziału czasowego");
            //rowDate.createCell(1).setCellValue(Timestamp.valueOf(sSQL_data_ostatniego_zapytaniaOD));
            //rowDate.createCell(2).setCellValue(Timestamp.valueOf(sSQL_data_ostatniego_zapytaniaDO));
            //rowDate.getCell(1).setCellStyle(cellStyleData);
            //rowDate.getCell(2).setCellStyle(cellStyleData);

            
            HSSFRow rodPrzerwa = sheet.createRow((short)1);
            
            HSSFRow rowhead = sheet.createRow((short)0);
            rowhead.createCell(0).setCellValue("Nazwa wtryskarki");
            rowhead.createCell(1).setCellValue("Wtrysk");
            rowhead.createCell(2).setCellValue("Wybrak");
            rowhead.createCell(3).setCellValue("Nie zgłoszono");
            rowhead.createCell(4).setCellValue("Awaria maszyny");
            rowhead.createCell(5).setCellValue("Awaria formy");
            rowhead.createCell(6).setCellValue("Przezbrajanie");
            rowhead.createCell(7).setCellValue("Suszenie materiału");
            rowhead.createCell(8).setCellValue("Próby technologiczne");
            rowhead.createCell(9).setCellValue("Brak zaopatrzenia");
            rowhead.createCell(10).setCellValue("Przerwa pracownika");
            rowhead.createCell(11).setCellValue("Brak operatora");
            rowhead.createCell(12).setCellValue("Postój zaplanowany");
            rowhead.createCell(13).setCellValue("Średni czas cyklu, s");
            
            int i = 0;
             for (daneObciazenieWtryskarki dane : daneSlupkoweDoTabeli) {
                 HSSFRow row = sheet.createRow((short)(i+1));
                row.createCell(0).setCellValue(dane.getMaszyna());
                row.createCell(1).setCellValue(dane.getWtrysk());
                row.createCell(2).setCellValue(dane.getWybrak());
                row.createCell(3).setCellValue(dane.getPostoj_n());   
                row.createCell(4).setCellValue(dane.getAwaria_m());
                row.createCell(5).setCellValue(dane.getAwaria_f());
                row.createCell(6).setCellValue(dane.getPrzezbrajanie());
                row.createCell(7).setCellValue(dane.getSusz_m());
                row.createCell(8).setCellValue(dane.getProby_tech());
                row.createCell(9).setCellValue(dane.getBrak_zaop());
                row.createCell(10).setCellValue(dane.getPrzerwa_p());
                row.createCell(11).setCellValue(dane.getBrak_oper());
                row.createCell(12).setCellValue(dane.getPostoj());
                row.createCell(13).setCellValue(dane.getCzas_cyklu());
                
                //row.getCell(0).setCellStyle(styleNumber);
                row.getCell(1).setCellStyle(styleNumber);
                row.getCell(2).setCellStyle(styleNumber);
                row.getCell(3).setCellStyle(styleNumber);
                row.getCell(4).setCellStyle(styleNumber);
                row.getCell(5).setCellStyle(styleNumber);
                row.getCell(6).setCellStyle(styleNumber);
                row.getCell(7).setCellStyle(styleNumber);
                row.getCell(8).setCellStyle(styleNumber);
                row.getCell(9).setCellStyle(styleNumber);
                row.getCell(10).setCellStyle(styleNumber);
                row.getCell(11).setCellStyle(styleNumber);
                row.getCell(12).setCellStyle(styleNumber);
                row.getCell(13).setCellStyle(styleNumber);
                
                i++;
            }

            try {
             FileOutputStream fileOut = new FileOutputStream(file);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();

        } catch (Exception ex) {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                 alertStage.getIcons().add(new Image(this.getClass().getResource("img/icon48.png").toString()));
                 alert.setTitle("TECHNIPLAST obiady: Błąd Zapisu");
                 alert.setHeaderText("Brak zapisu pliku");
                 alert.setContentText("Błąd : " + ex.getMessage());

                 alert.showAndWait();
                 System.err.println("Błąd w funkcji POI: " + ex.getMessage());
        }
         }
    }
    
    public void writeExcelDaneWtryskarkiZbiorcze(File file){
            if(file != null)
            {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Dane czasowe");  
            
            //HSSFCellStyle cellStyleData = workbook.createCellStyle();
            //CreationHelper createHelper = workbook.getCreationHelper();
            //cellStyleData.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd hh:mm:ss"));
            
            DataFormat formatNumber = workbook.createDataFormat();
            CellStyle styleNumber = workbook.createCellStyle();
             styleNumber.setDataFormat(formatNumber.getFormat("#,#"));
            
            
            //HSSFRow rowDate = sheet.createRow((short)0);
            //rowDate.createCell(0).setCellValue("Dane z przedziału czasowego");
            //rowDate.createCell(1).setCellValue(Timestamp.valueOf(sSQL_data_ostatniego_zapytaniaOD));
            //rowDate.createCell(2).setCellValue(Timestamp.valueOf(sSQL_data_ostatniego_zapytaniaDO));
            //rowDate.getCell(1).setCellStyle(cellStyleData);
            //rowDate.getCell(2).setCellStyle(cellStyleData);


            
            HSSFRow rowhead = sheet.createRow((short)0);
            rowhead.createCell(0).setCellValue("Nazwa");
            rowhead.createCell(1).setCellValue("Czas");

            
            int i = 0;
             for (zbiorczeDaneWtryskarki dane : daneKoloweDoTabeli) {
                 HSSFRow row = sheet.createRow((short)(i+1));
                row.createCell(0).setCellValue(dane.getNazwa());
                row.createCell(1).setCellValue(dane.getWartosc());

                
                //row.getCell(0).setCellStyle(styleNumber);
                row.getCell(0).setCellStyle(styleNumber);
                row.getCell(1).setCellStyle(styleNumber);
         
                i++;
            }

            try {
                FileOutputStream fileOut = new FileOutputStream(file);
                workbook.write(fileOut);
                fileOut.close();
                workbook.close();

            }catch (Exception ex) {
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                 alertStage.getIcons().add(new Image(this.getClass().getResource("img/icon48.png").toString()));
                 alert.setTitle("TECHNIPLAST obiady: Błąd Zapisu");
                 alert.setHeaderText("Brak zapisu pliku");
                 alert.setContentText("Błąd zapisu: " + ex.getMessage());

                 alert.showAndWait();
                 System.err.println("Błąd w funkcji POI: " + ex.getMessage());
        }
         }
    }

    @FXML
    private void BTNhideAction(ActionEvent event) {
         if(pierwszyHide)
         {
            
            KeyValue keyValue = new KeyValue(splitPane.getDividers().get(1).positionProperty(), ((TableCykle.getLayoutX()+TableCykle.getPrefWidth())/splitPane.getWidth()));
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), keyValue));
            timeline.play();
           
            ////////////////////////////////////////////////////////////////////////////////////////////////////
            RotateTransition rt = new RotateTransition(Duration.millis(1000), BTNhide);
            rt.setToAngle(180);
            rt.setCycleCount(1);
            rt.play();
            pierwszyHide = false;
  

       

            
         }
         else
         {
            KeyValue keyValue2 = new KeyValue(splitPane.getDividers().get(1).positionProperty(), 0.85);
            Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(500), keyValue2));
            timeline2.play();
                 
            ////////////////////////////////////////////////////////////////////////////////////////
            RotateTransition rt2 = new RotateTransition(Duration.millis(1000), BTNhide);
            rt2.setToAngle(0);
            rt2.setCycleCount(1);
            rt2.play();
            pierwszyHide = true;
              
            
         }
       
    }

    @FXML
    private void BTNhideMouseExited(MouseEvent event) {
        BTNhide.setEffect(null);
    }

    @FXML
    private void BTNhideMouseEntered(MouseEvent event) {
        BTNhide.setEffect(new Glow(0.3));
    }
    boolean jestInternet()
    {
        try 
        {
            URL url = new URL("http://www.google.com");

            URLConnection connection = url.openConnection();
            connection.connect();
            return true;

        }catch (IOException e){
  
           Alert alert = new Alert(AlertType.ERROR);
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




    @FXML
    private void RBgodzinyAction(ActionEvent event) {
        TBgodzina_od.setDisable(false);
        RBustawGodzine.setDisable(false);
        RBpierwszaZmiana.setDisable(false);
        RBdrugaZmiana.setDisable(false);
        SLDczas_ladowania.setMin(0);
        SLDczas_ladowania.setMax(24);
        SLDczas_ladowania.setValue(12);
        SLDczas_ladowania.setMajorTickUnit(12);
        SLDczas_ladowania.setMinorTickCount(11);
        //liczba_godzin = (int) SLDczas_ladowania.getValue();
        wybrana_analiza = J_ANALIZY.GODZINA;
        TBgodzina_od.setValue(LocalTime.of(LocalDateTime.now().minusHours(11).withMinute(0).withSecond(0).getHour(), 0, 0));
        TBdata_od.setValue( LocalDate.of(LocalDateTime.now().minusHours(11).withMinute(0).withSecond(0).getYear(), LocalDateTime.now().minusHours(11).withMinute(0).withSecond(0).getMonth(),LocalDateTime.now().minusHours(11).withMinute(0).withSecond(0).getDayOfMonth()));
        
        
        
    }

    @FXML
    private void RBdniAction(ActionEvent event) {
        TBgodzina_od.setDisable(true);
        RBustawGodzine.setDisable(true);
        RBpierwszaZmiana.setDisable(true);
        RBdrugaZmiana.setDisable(true);
        SLDczas_ladowania.setMin(0);
        SLDczas_ladowania.setMax(31);
        SLDczas_ladowania.setValue(7);
        SLDczas_ladowania.setMajorTickUnit(7);
        SLDczas_ladowania.setMinorTickCount(6);
        //liczba_godzin = (int) SLDczas_ladowania.getValue();
        wybrana_analiza = J_ANALIZY.DZIEN;
        TBgodzina_od.setValue(LocalTime.of(0, 0, 0));
        
        TBdata_od.setValue( LocalDate.of(LocalDateTime.now().withMinute(0).withSecond(0).minusDays(6).getYear(), LocalDateTime.now().withMinute(0).withSecond(0).minusDays(6).getMonth(),LocalDateTime.now().withMinute(0).withSecond(0).minusDays(6).getDayOfMonth()));
        
    }
    public void aktualizujTreeView()
    {
       
    CheckBoxTreeItem<String> wtryskarki = new CheckBoxTreeItem<String>("Wtryskarki");
    CheckBoxTreeItem<String> ustron = new CheckBoxTreeItem<String>("Ustroń");
    CheckBoxTreeItem<String> skoczow = new CheckBoxTreeItem<String>("Skoczów");
    CheckBoxTreeItem<String> koniakow = new CheckBoxTreeItem<String>("Koniaków");
    
    wtryskarki.getChildren().add(skoczow);
    wtryskarki.getChildren().add(ustron);
    wtryskarki.getChildren().add(koniakow);
    //wtryskarki.getChildren().add(koniakow);

    wtryskarki.setExpanded(true);
    //ustron.setExpanded(true);
   
    for(Maszyna analizowanaMaszyna:IstniejaceMaszynyUstron.LadujNazwyMaszyn())
    {
         ustron.getChildren().add(new CheckBoxTreeItem<String>(analizowanaMaszyna.getNazwa()));

    }
    for(Maszyna analizowanaMaszyna:IstniejaceMaszynySkoczow.LadujNazwyMaszyn())
    {
         skoczow.getChildren().add(new CheckBoxTreeItem<String>(analizowanaMaszyna.getNazwa()));

    }
    for(Maszyna analizowanaMaszyna:IstniejaceMaszynyKoniakow.LadujNazwyMaszyn())
    {
         koniakow.getChildren().add(new CheckBoxTreeItem<String>(analizowanaMaszyna.getNazwa()));

    }
            
            
     // Create the CheckTreeView with the data 
    //ustronTree = new CheckTreeView<>(root);
    ustronTree.setRoot(wtryskarki);
    

       
    }
    public void shutdown(){
       
    }
    public boolean getZaladowanoOkno()
    {
        return zaladowano_okno;
    }

   
}


