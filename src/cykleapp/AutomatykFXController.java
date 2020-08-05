/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cykleapp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.controls.JFXTogglePane;
import com.sun.javafx.charts.Legend;
import java.io.IOException;
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
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AutomatykFXController implements Initializable { 
    private Runnable taskUpdater;
    
    static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(15);
    static ScheduledFuture<?> t;
    
    private final int przyciskiWidth = 210;
    private Connection conn;
    private ResultSet rs;
    private PreparedStatement pst;
    private LocalDateTime localDateTime_od;
    private LocalDateTime localDateTime_do;
    private String wybranaMaszyna = "";
    private int liczbaMinutAnalizy = 15;
    private int liczbaMinutAnalizyFunkcja = 0;

    
    private String textLabelDialog = "";
    
    private XYChart.Series l_wtrysk;
    private XYChart.Series l_wybrak;
    private XYChart.Series l_postoj_n;
    private XYChart.Series l_awaria_m;
    private XYChart.Series l_awaria_f;
    private XYChart.Series l_przezbrajanie;
    private XYChart.Series l_proby;
    private XYChart.Series l_brak_zaop;
    private XYChart.Series l_postoj;
    
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
    
    private final boolean niePokazujZer = false;
    private boolean normalnyPodglad = true;
    private Timeline wykresUpdater;
    
    @FXML
    private Color x2;
    @FXML
    private Font x1;
    @FXML
    private Color x4;
    @FXML
    private Font x3;
    @FXML
    private SplitPane splitPane;
    @FXML
    private Label labelPodglad;
    @FXML
    private AreaChart<?, ?> wykresLiniowy;
    @FXML
    private JFXComboBox<String> CBmaszyna;
    @FXML
    private JFXDatePicker TBdata_od;
    @FXML
    private Label labelOpcje;
    @FXML
    private JFXTimePicker TBgodzina_od;
    @FXML
    private JFXSlider minutySlider;
    @FXML
    private JFXButton BTNwczytaj;
    @FXML
    private Label Lstatus;
    @FXML
    private JFXSpinner ProgressBar;
    @FXML
    private JFXTogglePane infoDialog;
    @FXML
    private Label labelDialog;
    @FXML
    private JFXCheckBox CBpodglad;
    @FXML
    private AnchorPane AWykresPane;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Label labelLiczbaMinut;
    @FXML
    private Color x21;
    
    private boolean zaladowano_okno = false;
    @FXML
    private JFXComboBox<String> CBmiejsce;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         taskUpdater = new Runnable() 
         {
            public void run() 
            {
                wczytajWykres();
            }
         };
        
        
        //AWykresPane.widthProperty().addListener(observable -> redraw());
        //AWykresPane.heightProperty().addListener(observable -> redraw());
        stageSizeChageListener(splitPane);
        //IstniejaceMaszynyUstron.LadujIstniejaceMaszyny(CBmaszyna);
        IstniejaceMaszynySkoczow.LadujIstniejaceMaszyny(CBmaszyna);
        
        CBmiejsce.getItems().add("Skoczów");
        CBmiejsce.getItems().add("Ustroń");
        CBmiejsce.setValue("Skoczów");

        //IstniejaceMaszynyUstron.LadujIstniejaceMaszyny(CBmaszyna);
        IstniejaceMaszynySkoczow.LadujIstniejaceMaszyny(CBmaszyna);
        
        wybranaMaszyna = CBmaszyna.getValue();
        TBgodzina_od._24HourViewProperty().set(true);
        minutySlider.setMin(0);
        minutySlider.setMax(60);
        minutySlider.setValue(liczbaMinutAnalizy);
        minutySlider.setMajorTickUnit(15);
        minutySlider.setMinorTickCount(14);
        ProgressBar.setVisible(false);
        infoDialog.setBackground(new Background(new BackgroundFill(Color.color(1, 1, 1, 0.8), new CornerRadii(30), Insets.EMPTY)));
        infoDialog.setVisible(false);
        wykresLiniowy.getXAxis().setAnimated(false);
        wykresLiniowy.getXAxis().setAutoRanging(true);
        wykresLiniowy.getYAxis().setAnimated(false);
        wykresLiniowy.getYAxis().setAutoRanging(true);
        wykresLiniowy.getYAxis().setLabel("Wartości");
        wykresLiniowy.getXAxis().setLabel("Czas");
        
        BTNwczytaj.setText("");
        BTNwczytaj.setId("BTNszukaj");
        BTNwczytaj.setEffect(null);
        ustawDate();
        wczytajWykres();
        
        // TODO
        zaladowano_okno = true;
    }
    //////////////////////////////////////////////////////////////////////////////////////////
    private void wczytajWykres()
    {
        liczbaMinutAnalizyFunkcja = liczbaMinutAnalizy;
        //RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ 
        Platform.runLater(new Runnable()
        {
            @Override

            public void run()
            {
                if(normalnyPodglad)
                {
                    Lstatus.setText("Ładowanie danych...");
                }
            }

        });//END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_

        Boolean brakDanych = true;
        
        l_wtrysk = new XYChart.Series();
        l_wybrak = new XYChart.Series();
        l_postoj_n = new XYChart.Series();
        l_awaria_m = new XYChart.Series();
        l_awaria_f = new XYChart.Series();
        l_przezbrajanie = new XYChart.Series();
        l_proby = new XYChart.Series();
        l_brak_zaop = new XYChart.Series();
        l_postoj = new XYChart.Series();
        
        l_wtrysk.setName("wtrysk");
        l_wybrak.setName("wybrak");
        l_postoj_n.setName("postój nieuzasadniony");
        l_awaria_m.setName("awaria maszyny");
        l_awaria_f.setName("awaria formy");
        l_przezbrajanie.setName("przezbrajanie");
        l_proby.setName("próby technologiczne");
        l_brak_zaop.setName("brak zaopatrzenia");
        l_postoj.setName("postój");
        
        wtrysk = 0;
        wybrak = 0;
        brak_operatora = 0;
        awaria_m = 0;
        awaria_f = 0;
        przezbrajanie = 0;
        proby_tech = 0;
        brak_zaop = 0;
        postoj = 0;
                
        
        try
        {
        conn = mysqlconnect.ConnecrDb();
        String sql = "";
        if(normalnyPodglad)
        {
        sql = "SELECT data_g, wtrysk ,wybrak, postoj_n, awaria_m, awaria_f,przezbrajanie,proby_tech,brak_zaop,postoj, czas_cyklu FROM techniplast.cykle_szybkie where maszyna = '"+wybranaMaszyna+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od)+"' and '"+Timestamp.valueOf(localDateTime_od.plusMinutes(liczbaMinutAnalizyFunkcja))+"';";
        
        //RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_
        Platform.runLater(new Runnable()
        {

            @Override

            public void run()
            {
                labelPodglad.setText("Podgląd maszyny "+wybranaMaszyna+" \nz przedziału czasu od: " + Timestamp.valueOf(localDateTime_od).toString().substring(0, 19)+" do: "+Timestamp.valueOf(localDateTime_od.plusMinutes(liczbaMinutAnalizyFunkcja)).toString().substring(0, 19));
            }

        });//END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_
        }
        else
        {
            LocalDateTime localDateTime_od_refresh = LocalDateTime.now();
            LocalDateTime localDateTime_od_refresh_ustaw = LocalDateTime.now().minusMinutes(liczbaMinutAnalizyFunkcja);
            //RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ 
            Platform.runLater(new Runnable()
            {

                    @Override

                    public void run()

                    {
                        TBgodzina_od.setValue(LocalTime.of(localDateTime_od_refresh_ustaw.getHour(), localDateTime_od_refresh_ustaw.getMinute(), 0));
                        TBdata_od.setValue( LocalDate.of(localDateTime_od_refresh_ustaw.getYear(), localDateTime_od_refresh_ustaw.getMonth(),localDateTime_od_refresh_ustaw.getDayOfMonth()));
                        labelPodglad.setText("Podgląd maszyny "+wybranaMaszyna+" \nz przedziału czasu od: " + Timestamp.valueOf(localDateTime_od_refresh.minusMinutes(liczbaMinutAnalizyFunkcja)).toString().substring(0, 19)+" do: "+Timestamp.valueOf(localDateTime_od_refresh).toString().substring(0, 19));

                    }

            });//END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_
            
            sql = "SELECT data_g, wtrysk ,wybrak, postoj_n, awaria_m, awaria_f,przezbrajanie,proby_tech,brak_zaop,postoj, czas_cyklu FROM techniplast.cykle_szybkie where maszyna = '"+wybranaMaszyna+"' and data_g between '"+ Timestamp.valueOf(localDateTime_od_refresh.minusMinutes(liczbaMinutAnalizyFunkcja))+"' and '"+Timestamp.valueOf(localDateTime_od_refresh)+"';";
        }
         //System.out.println(sql);
            pst = conn.prepareStatement(sql);
            
            rs = pst.executeQuery(sql);
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
                
                if(niePokazujZer)
                {
                    if(wtrysk > 0)
                    {
                    l_wtrysk.getData().add(new XYChart.Data(data, wtrysk));
                    }
                    if(wybrak>0)
                    {
                    l_wybrak.getData().add(new XYChart.Data(data, wybrak));
                    }
                    if(brak_operatora>0)
                    {
                    l_postoj_n.getData().add(new XYChart.Data(data, brak_operatora));
                    }
                    if(awaria_m>0)
                    {
                    l_awaria_m.getData().add(new XYChart.Data(data, awaria_m));
                    }
                    if(awaria_f>0)
                    {
                    l_awaria_f.getData().add(new XYChart.Data(data, awaria_f));
                    }
                    if(przezbrajanie >0)
                    {
                    l_przezbrajanie.getData().add(new XYChart.Data(data, przezbrajanie));
                    }
                    if(proby_tech >0)
                    {
                    l_proby.getData().add(new XYChart.Data(data, proby_tech));
                    }
                    if(brak_zaop>0)
                    {
                    l_brak_zaop.getData().add(new XYChart.Data(data, brak_zaop));
                    }
                    if(postoj>0)
                    {
                    l_postoj.getData().add(new XYChart.Data(data, postoj));
                    }
                }
                else
                {
                    l_wtrysk.getData().add(new XYChart.Data(data, wtrysk));
                    l_wybrak.getData().add(new XYChart.Data(data, wybrak));
                    l_postoj_n.getData().add(new XYChart.Data(data, brak_operatora));
                    l_awaria_m.getData().add(new XYChart.Data(data, awaria_m));
                    l_awaria_f.getData().add(new XYChart.Data(data, awaria_f));
                    l_przezbrajanie.getData().add(new XYChart.Data(data, przezbrajanie));
                    l_proby.getData().add(new XYChart.Data(data, proby_tech));
                    l_brak_zaop.getData().add(new XYChart.Data(data, brak_zaop));
                    l_postoj.getData().add(new XYChart.Data(data, postoj));
                }
            }
            conn.close();
             if(brakDanych)
                {
                    //RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ 
                    Platform.runLater(new Runnable()
                    {

                        @Override

                        public void run()

                        {
                            wykresLiniowy.setVisible(false);
                            labelPodglad.setText("Brak danych do wyświetlenia o maszynie "+wybranaMaszyna+" z wybranego przedziału czasu");
                        }

                    });//END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_

                }
             else
                {
                //RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ 
                Platform.runLater(new Runnable()
                {

                    @Override

                    public void run()

                    {
                        wykresLiniowy.getData().clear();
                        wykresLiniowy.getData().addAll(l_wtrysk,l_wybrak,l_postoj_n,l_awaria_m,l_awaria_f,l_przezbrajanie,l_proby,l_brak_zaop,l_postoj);
                        poprawKolory();
                        addWykresEventsListener();
                        addWykresLegendaEventsListener();
                        wykresLiniowy.setVisible(true);
                    }

                });//END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_

                }
            
            
        }
        catch(NumberFormatException | SQLException ex)
        {
                wykresLiniowy.setVisible(false);

                Alert alert = new Alert(Alert.AlertType.ERROR);
                Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                alertStage.getIcons().add(new Image(this.getClass().getResource("img/icon48.png").toString()));
                alert.setTitle("TECHNIPLAST Analizator pracy wtryskarek: Błąd");
                alert.setHeaderText("Problem bazy danych SQL");
                alert.setContentText("Nie można wczytać danych");

                alert.showAndWait();
                System.err.println("Błąd: " + ex.getMessage());
        }
        finally
        {
            //RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ RUNNABLE_ 
            Platform.runLater(new Runnable()
            {
                @Override
                public void run()

                {
                    if(normalnyPodglad)
                    {
                    ProgressBar.setVisible(false);
                    Lstatus.setText("TECHNIPLAST");
                    BTNwczytaj.setDisable(false);
                    

                    }
                }

            });//END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_END_RUNNABLE_

        }
        
        
        
    }//wczytajWykres()
    private void poprawKolory()
    {
           for (int i = 0; i < wykresLiniowy.getData().size(); i++) {
                          for (Node node : wykresLiniowy.lookupAll(".series" + i)) {
                              node.getStyleClass().add("default-color" + i);
                          }
                      }
                      
                                    int i = 0;
                     for (Node node : wykresLiniowy.lookupAll(".chart-legend-item")) {
                         if (node instanceof Label && ((Label) node).getGraphic() != null) {

                             ((Label) node).getGraphic().getStyleClass().add("default-color" + i);
                         }
                         i++;
                     }
    }
    private void stageSizeChageListener(SplitPane box){
           box.widthProperty().addListener(new ChangeListener<Number>() {
               @Override
               public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                   
                   
                   redraw();
                   splitPane.setDividerPositions((przyciskiWidth/ splitPane.getWidth()),0.85);
                   //splitPane.setDividerPosition(2, 0.8);
                   //splitPane.setDividerPositions(0.01,0.80);

               }
           });

           box.heightProperty().addListener(new ChangeListener<Number>() {
               @Override
               public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                   
                   
                   redraw();
                   splitPane.setDividerPositions((przyciskiWidth/ splitPane.getWidth()),0.85);
                   //splitPane.setDividerPosition(2, 0.8);
                   //splitPane.setDividerPositions(0,0.80);
                   //BTNwczytaj.setLayoutY(oldValue.doubleValue());
                   //System.out.println("height: "+oldValue.doubleValue());
               }
           });

       }//stageSizeChageListener 

    @FXML
    private void CBmaszynaAction(ActionEvent event) {
        wybranaMaszyna = CBmaszyna.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void TBdata_odAction(ActionEvent event) {
        localDateTime_od = LocalDateTime.of(TBdata_od.getValue(),LocalTime.of(TBgodzina_od.getValue().getHour(),0,0));
    }

    @FXML
    private void TBgodzina_odAction(ActionEvent event) {
        localDateTime_od = LocalDateTime.of(TBdata_od.getValue(),LocalTime.of(TBgodzina_od.getValue().getHour(),TBgodzina_od.getValue().getMinute(),0));
    }
    private void ustawDate()
    {
        localDateTime_od = LocalDateTime.now().minusMinutes(liczbaMinutAnalizy);
        
        
        TBgodzina_od.setValue(LocalTime.of(localDateTime_od.getHour(), localDateTime_od.getMinute(), 0));
       
        TBdata_od.setValue( LocalDate.of(localDateTime_od.getYear(), localDateTime_od.getMonth(),localDateTime_od.getDayOfMonth()));
        
        

    }


    @FXML
    private void minutySliderMouseRelased(MouseEvent event) {
        liczbaMinutAnalizy = (int)minutySlider.getValue();

        //System.out.println("liczba minut analizy: "+liczbaMinutAnalizy);
    }

    @FXML
    private void BTNwczytajAction(ActionEvent event) {
        if(jestInternet())
        {
          
        
        Lstatus.setText("Ładowanie danych...");
        ProgressBar.setVisible(true);
        BTNwczytaj.setDisable(true);
        CBpodglad.setDisable(true);

      
        Runnable task = new Runnable()
        {
                public void run()

                {
                        wczytajWykres();
                        CBpodglad.setDisable(false);
                }

        };

        Thread backgroundThread = new Thread(task);

        backgroundThread.setDaemon(true);

        backgroundThread.start();
       
     

        }
            
    }

    @FXML
    private void BTNwczytajMouseExited(MouseEvent event) {
        BTNwczytaj.setEffect(null);
    }

    @FXML
    private void BTNwczytajMouseEntered(MouseEvent event) {
        BTNwczytaj.setEffect(new Glow(0.2));
    }
    private void addWykresEventsListener()
    {
     // DYMEK WYKRES LINIOWY
     for (XYChart.Series<?, ?> serie: wykresLiniowy.getData()){
        for (XYChart.Data<?, ?> item: serie.getData()){
            item.getNode().setOnMouseEntered((MouseEvent event) -> {
                //System.out.println("you clicked "+item.toString()+serie.toString());
                if(serie.toString().length() >0)
                {
                String godzina = item.toString().substring(item.toString().indexOf("[") + 1, item.toString().indexOf(","));
                String nazwa = serie.toString().substring(serie.toString().indexOf("[") + 1, serie.toString().indexOf("]"));
                //String wartosc = ""+round(Double.parseDouble(item.toString().substring(12, item.toString().indexOf(",null"))),2);
                String wartosc = item.toString().substring(15, item.toString().indexOf(",null"));
                
                //System.out.println("Godzina: "+godzina);
                //System.out.println(nazwa+": "+wartosc);
                
                textLabelDialog = "Godzina: "+godzina+"\n"+nazwa+": "+wartosc;
               
                labelDialog.setText(textLabelDialog);
                infoDialog.setVisible(true);
                //infoDialog.localToParent(new Point2D(event.getX(),event.getY()));
                
                }
                item.getNode().setEffect(new Glow(0.9));
                });
            }
        }
    
    for (XYChart.Series<?, ?> serie: wykresLiniowy.getData()){
        for (XYChart.Data<?, ?> item: serie.getData()){
            item.getNode().setOnMouseExited((MouseEvent event) -> {      
                item.getNode().setEffect(null);
                textLabelDialog = "";
                infoDialog.setVisible(false);
                });
            }
        }
    //POZYCJA DYMEK WYKRES LINIOWY
    for (XYChart.Series<?, ?> serie: wykresLiniowy.getData()){
        for (XYChart.Data<?, ?> item: serie.getData()){
            item.getNode().setOnMouseMoved((MouseEvent event) -> {      
     
                //infoDialog.setLayoutX(event.getSceneX()-opcjePane.getWidth()-(infoDialog.getWidth()/2));
                //infoDialog.setLayoutY(event.getSceneY()-infoDialog.getHeight()-50+podgladScrollPane.getHvalue());
                infoDialog.setLayoutX(item.getNode().getLayoutX()+event.getX()+wykresLiniowy.getLayoutX()-(wykresLiniowy.getYAxis().getWidth()/2));    
                infoDialog.setLayoutY(item.getNode().getLayoutY()+event.getY()+wykresLiniowy.getLayoutY()-(infoDialog.getHeight()));
                


                infoDialog.toFront();
                });
            }
        }
    }//private void addWykresEventsListener()
    
    private void addWykresLegendaEventsListener()
    {
        for (Node n : wykresLiniowy.getChildrenUnmodifiable()) {
    if (n instanceof Legend) {
        Legend l = (Legend) n;
        for (Legend.LegendItem li : l.getItems()) {
            for (XYChart.Series<?, ?> s : wykresLiniowy.getData()) {
                if (s.getName().equals(li.getText())) {
                    li.getSymbol().setCursor(Cursor.HAND); // Hint user that legend symbol is clickable
                    li.getSymbol().setOnMouseClicked(me -> {
                        if (me.getButton() == MouseButton.SECONDARY) {
                            s.getNode().setVisible(!s.getNode().isVisible()); // Toggle visibility of line
                            for (XYChart.Data<?, ?> d : s.getData()) {
                                if (d.getNode() != null) {
                                    d.getNode().setVisible(s.getNode().isVisible()); // Toggle visibility of every node in the series
                                }
                            }
                        }
                        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                         if (me.getButton() == MouseButton.PRIMARY) { 
                            for (XYChart.Data<?, ?> d : s.getData()) {
                                if (d.getNode() != null) {
                                    d.getNode().toFront();// Toggle visibility of every node in the series
                                }
                            }
                        }
                         ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    });
                    break;
                }
            }
        }
    }
}//addWykresLegendaEventsListener()
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


    @FXML
    private void CBpodgladMouseClicked(MouseEvent event) {
        boolean stan = CBpodglad.isSelected();
  
        if(stan)
        {
            
           
            
            //ProgressBar.setVisible(true);
            LocalDateTime localDateTime_od_refresh_ustaw = LocalDateTime.now().minusMinutes(liczbaMinutAnalizy);
            TBgodzina_od.setValue(LocalTime.of(localDateTime_od_refresh_ustaw.getHour(), localDateTime_od_refresh_ustaw.getMinute(), 0));
            TBdata_od.setValue( LocalDate.of(localDateTime_od_refresh_ustaw.getYear(), localDateTime_od_refresh_ustaw.getMonth(),localDateTime_od_refresh_ustaw.getDayOfMonth()));
            //wczytajWykres();
            normalnyPodglad = false;
            TBdata_od.setDisable(true);
            TBgodzina_od.setDisable(true);
            BTNwczytaj.setDisable(true);
            wykresLiniowy.setAnimated(false);
            ProgressBar.setVisible(true);
            Lstatus.setText("Ładowanie danych...");
            //wykresUpdater.setCycleCount(Timeline.INDEFINITE);
            
            //wykresUpdater.play();
           
            //System.out.println("check box zaznaczony");
            
            
             t = executor.scheduleAtFixedRate(taskUpdater, 0, 15, TimeUnit.SECONDS);
            
        }
        else
        {
            //CBmaszyna.setEditable(true);
            
            //wykresUpdater.stop();

            t.cancel(false);
            Lstatus.setText("TECHNIPLAST");
            ProgressBar.setVisible(false);
            wykresLiniowy.setAnimated(true);
            wykresLiniowy.getXAxis().setAnimated(false);
            wykresLiniowy.getXAxis().setAutoRanging(true);
            wykresLiniowy.getYAxis().setAnimated(false);
            wykresLiniowy.getYAxis().setAutoRanging(true);
            normalnyPodglad = true;
            TBdata_od.setDisable(false);
            TBgodzina_od.setDisable(false);
            BTNwczytaj.setDisable(false);
            //System.out.println("check box niezaznaczony");
            
        }
    }

    public void shutdown(){
        //System.out.println("zamykanie okna");
        if(executor != null)
        {
        executor.shutdown();
        }
    }
    
    public void redraw()
    {
        
        wykresLiniowy.setMaxWidth(splitPane.getWidth()-przyciskiWidth-80);
        wykresLiniowy.setMaxHeight(splitPane.getHeight()-120);
        
        //wykresLiniowy.setPrefWidth(splitPane.getWidth()-przyciskiWidth-80);
        //wykresLiniowy.setPrefHeight(splitPane.getHeight()-170);
        
        wykresLiniowy.setPrefWidth(splitPane.getWidth()-przyciskiWidth-0);
        wykresLiniowy.setPrefHeight(splitPane.getHeight()-150);
    }

    @FXML
    private void minutySliderDragDetected(MouseEvent event) {
    }

    @FXML
    private void minutySliderMouseMoved(MouseEvent event) {
    }

    @FXML
    private void CBpodgladAction(ActionEvent event) {
    }
     public boolean getZaladowanoOkno()
    {
        return zaladowano_okno;
    }

    @FXML
    private void oknoMouseClickedAction(MouseEvent event) {
        if(event.getClickCount() == 2)
        {
            if(!startFXController.stageModulAutomatyka.isFullScreen())
            {
            startFXController.stageModulAutomatyka.setFullScreen(true);
            }
            else
            {
                startFXController.stageModulAutomatyka.setFullScreen(false);
            }
        }
    }

    @FXML
    private void CBmiejsceAction(ActionEvent event) {
        CBmaszyna.getItems().clear();
        if(CBmiejsce.getValue().equals("Ustroń"))
        {
            IstniejaceMaszynyUstron.LadujIstniejaceMaszyny(CBmaszyna);
        }
        if(CBmiejsce.getValue().equals("Skoczów"))
        {
            IstniejaceMaszynySkoczow.LadujIstniejaceMaszyny(CBmaszyna);
        }
        
        wybranaMaszyna = CBmaszyna.getValue();
    }
   
}

