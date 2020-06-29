/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cykleapp;

import insidefx.undecorator.Undecorator;
import insidefx.undecorator.UndecoratorScene;
import java.awt.Dimension;
import java.awt.SplashScreen;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author <a href="mailto:marekmadyda@gmail.com">Marek Madyda</a>
 */
public class CykleApp extends Application {
    private SplitPane splitPane;
    private  cykleFXController controller;
    private Stage windowStage;
    private Stage preloaderStage;
    public static Preferences rootPref;
  
    @Override
    public void start(Stage stage) throws Exception {
        rootPref = Preferences.userNodeForPackage( CykleApp.class );
        windowStage = stage;
        //initPreloader();
        PauseTransition pause = new PauseTransition(Duration.seconds(5));
        pause.setOnFinished(e -> {
            preloaderStage.hide();
            windowStage.show();
        });
        //pause.play();
        
        windowStage = stage;
        
        //SPRAWDZANIE INTERNETU
        try 
        {
            URL url = new URL("http://www.google.com");

            URLConnection connection = url.openConnection();
            connection.connect();

        }catch (IOException e){
           //pause.stop();
           //preloaderStage.hide();
           windowStage.hide();
           
           Alert alert = new Alert(AlertType.ERROR);
           Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
           alertStage.getIcons().add(new Image(this.getClass().getResource("img/icon48.png").toString()));
           alert.setTitle("TECHNIPLAST Analizator pracy wtryskarek: Błąd");
           alert.setHeaderText("Brak połączenia internetowego");
           alert.setContentText("Program nie zostanie uruchomiony");

           alert.showAndWait();
           System.exit(0);

        } 
          
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("start.fxml"));
        
        controller = (cykleFXController)loader.getController();
        
        
        //Parent root = (Parent)loader.load();
        Region root = (Region)loader.load();
        //Scene scene = new Scene(root);
        UndecoratorScene scene = new UndecoratorScene(windowStage, StageStyle.UTILITY, root, null);
        
        
        //windowStage.setTitle("TECHNIPLAST Analizator pracy wtryskarek");
        windowStage.setTitle("");
        //stage.setResizable(false);
        //stage.initStyle(StageStyle.DECORATED);
        windowStage.initStyle(StageStyle.TRANSPARENT);
        String css = this.getClass().getResource("styleStart.css").toExternalForm(); 
        scene.getStylesheets().add(css);
        windowStage.getIcons().add(new Image(getClass().getResourceAsStream("img/icon48.png")));
        
         //Undecorator undecorator = new Undecorator(windowStage, (Region) root);
          Undecorator undecorator = scene.getUndecorator();
        // undecorator.setStageStyle(StageStyle.UTILITY);
         undecorator.getStylesheets().add(css);
       
         undecorator.getStylesheets().add("skin/undecoratorStart.css");
       
        windowStage.setOnCloseRequest(windowEvent -> {
            //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            //double width = screenSize.getWidth();
            //double height = screenSize.getHeight();
            Alert alert = new Alert(AlertType.CONFIRMATION);
            //alert.initOwner(stage);
            
            ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(this.getClass().getResource("img/icon48.png").toString()));

            //stage.getIcons().add(new Image(this.getClass().getResource("img/icon48.png").toString()));
            alert.setTitle("TECHNIPLAST Analizator pracy wtryskarek");
            alert.setHeaderText("Czy na pewno zamknąć program ?");  

            ButtonType buttonTypeTak = new ButtonType("Tak",ButtonBar.ButtonData.NO);
            ButtonType buttonTypeAnuluj = new ButtonType("Anuluj",ButtonBar.ButtonData.YES);
            alert.getButtonTypes().setAll(buttonTypeAnuluj,buttonTypeTak);
            //alert.setContentText("Are you ok with this?");
            Optional<ButtonType> result = alert.showAndWait();
            //alert.setX((width/2)-(alert.getWidth()/2));
            //alert.setY((height/2)-(alert.getHeight()/2));

            if (result.get() == buttonTypeTak){
                alert.close();
            } else {
                windowEvent.consume();
                
            }

});
        
        windowStage.initStyle(StageStyle.TRANSPARENT);
             
         undecorator.installAccelerators(scene);
        undecorator.setFadeInTransition();
        undecorator.initDecoration();
        //undecorator.getStylesheets().add("skin/undecoratorGlowny.css");
    // undecorator.getStylesheets().add("skin/undecoratorUtilityStage.css");
    
        //scene = new Scene(undecorator);
       // scene = new UndecoratorScene(windowStage, root);
       
        scene.setFill(Color.TRANSPARENT);
        

        windowStage.setScene(scene);
        windowStage.setWidth(640);
        windowStage.setHeight(480);
            
        windowStage.setMinWidth(640);
        windowStage.setMinHeight(480);
        windowStage.setResizable(false);
       

        windowStage.show();
        
        
        //stage.setScene(scene);
        //stage.show();
        
        
        
        
        
   
    }
    public void initPreloader() throws IOException
    {
        preloaderStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("splash/splash.fxml"));
        
        //controller = (SplashController)loader.getController();
        Parent root = (Parent)loader.load();
        Scene scene = new Scene(root);
        preloaderStage.setScene(scene);
        
       // preloaderStage.setTitle("TECHNIPLAST Analizator pracy wtryskarek");
        preloaderStage.setResizable(false);
        String css = this.getClass().getResource("splash/SplashStyle.css").toExternalForm(); 
        scene.getStylesheets().add(css);
        preloaderStage.getIcons().add(new Image(getClass().getResourceAsStream("img/icon48.png")));
        preloaderStage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        
        preloaderStage.show();
        preloaderStage.toFront();
    }
    
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
