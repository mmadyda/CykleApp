/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cykleapp;

import com.jfoenix.controls.JFXComboBox;
import java.util.ArrayList;


//DODAWANIE WTRYSKAREK DO LAYOUT DODAWANIE WTRYSKAREK DO LAYOUT DODAWANIE WTRYSKAREK DO LAYOUT DODAWANIE WTRYSKAREK DO LAYOUT
public class IstniejaceMaszynyUstron {
    public static void LadujIstniejaceMaszyny(JFXComboBox<String> CBmaszyna)
    {
        CBmaszyna.getItems().clear();
        CBmaszyna.getItems().add("TEST");
        ////////////////////////////////////////////////////////////////////////
        CBmaszyna.getItems().add("UST_33");
        CBmaszyna.getItems().add("UST_44");
        CBmaszyna.getItems().add("UST_45");
        ////////////////////////////////////////////////////////////////////////
        CBmaszyna.getItems().add("UST_46");
        CBmaszyna.getItems().add("UST_43");
        CBmaszyna.getItems().add("UST_53");
        CBmaszyna.getItems().add("UST_50");
        CBmaszyna.getItems().add("UST_52");
        CBmaszyna.getItems().add("UST_41");
        ////////////////////////////////////////////////////////////////////////
        
        CBmaszyna.setValue("UST_41");
    }
    public static ArrayList<Maszyna> LadujNazwyMaszyn()
    {
        ArrayList<Maszyna> maszyny = new ArrayList<Maszyna>();
        ////////////////////////////////////////////////////////////////////////
        maszyny.add(new Maszyna("UST_33"));
        maszyny.add(new Maszyna("UST_44"));
        maszyny.add(new Maszyna("UST_45"));
        ////////////////////////////////////////////////////////////////////////
        maszyny.add(new Maszyna("UST_46"));
        maszyny.add(new Maszyna("UST_43"));
        maszyny.add(new Maszyna("UST_53"));
        maszyny.add(new Maszyna("UST_50"));
        maszyny.add(new Maszyna("UST_52"));
        maszyny.add(new Maszyna("UST_41"));

       
        return maszyny;
        
    }
    
}
