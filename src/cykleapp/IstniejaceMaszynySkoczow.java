/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cykleapp;

import com.jfoenix.controls.JFXComboBox;
import java.util.ArrayList;


//DODAWANIE WTRYSKAREK DO LAYOUT DODAWANIE WTRYSKAREK DO LAYOUT DODAWANIE WTRYSKAREK DO LAYOUT DODAWANIE WTRYSKAREK DO LAYOUT
public class IstniejaceMaszynySkoczow {
    public static void LadujIstniejaceMaszyny(JFXComboBox<String> CBmaszyna)
    {
        CBmaszyna.getItems().add("TEST");
        //STARA HALA
        CBmaszyna.getItems().add("ST_04");
        CBmaszyna.getItems().add("ST_27");
        CBmaszyna.getItems().add("ST_23");
        CBmaszyna.getItems().add("ST_25");
        CBmaszyna.getItems().add("ST_47");
        CBmaszyna.getItems().add("ST_48");
        CBmaszyna.getItems().add("ST_52");
        CBmaszyna.getItems().add("ST_53");
        CBmaszyna.getItems().add("ST_55");
     
        ////////////////////////////////////////////////////////////////////////
        
        //NOWA HALA
        CBmaszyna.getItems().add("ST_49");
        CBmaszyna.getItems().add("ST_29");
        CBmaszyna.getItems().add("ST_50");
        CBmaszyna.getItems().add("ST_57");
        CBmaszyna.getItems().add("ST_51");
        CBmaszyna.getItems().add("ST_68");
        CBmaszyna.getItems().add("ST_66");
        CBmaszyna.getItems().add("ST_46");
        CBmaszyna.getItems().add("ST_59");

        
        CBmaszyna.setValue("ST_04");
    }
    public static ArrayList<Maszyna> LadujNazwyMaszyn()
    {
        ArrayList<Maszyna> maszyny = new ArrayList<Maszyna>();
        ////////////////////////////////////////////////////////////////////////
        
        //STARA HALA
        
        maszyny.add(new Maszyna("ST_04"));
        maszyny.add(new Maszyna("ST_27"));
        maszyny.add(new Maszyna("ST_23"));
        maszyny.add(new Maszyna("ST_25"));
        maszyny.add(new Maszyna("ST_47"));
        maszyny.add(new Maszyna("ST_48"));
        maszyny.add(new Maszyna("ST_52"));
        maszyny.add(new Maszyna("ST_53"));
        maszyny.add(new Maszyna("ST_55"));
        
        ////////////////////////////////////////////////////////////////////////
        
        //NOWA HALA
        maszyny.add(new Maszyna("ST_49"));
        maszyny.add(new Maszyna("ST_29"));
        maszyny.add(new Maszyna("ST_50"));
        maszyny.add(new Maszyna("ST_57"));
        maszyny.add(new Maszyna("ST_51"));
        maszyny.add(new Maszyna("ST_68"));
        maszyny.add(new Maszyna("ST_66"));
        maszyny.add(new Maszyna("ST_46"));
        maszyny.add(new Maszyna("ST_59"));
     
        return maszyny;
        
    }
    
}
