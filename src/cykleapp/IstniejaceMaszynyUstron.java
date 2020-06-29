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
        CBmaszyna.getItems().add("ARBURG_34");
        CBmaszyna.getItems().add("ARBURG_35");
        CBmaszyna.getItems().add("ARBURG_36");
        ////////////////////////////////////////////////////////////////////////
        CBmaszyna.getItems().add("ARBURG_31");
        CBmaszyna.getItems().add("ARBURG_32");
        CBmaszyna.getItems().add("ARBURG_33");
        ////////////////////////////////////////////////////////////////////////
        CBmaszyna.getItems().add("HAITIAN_39");
        CBmaszyna.getItems().add("HAITIAN_41");
        CBmaszyna.getItems().add("HAITIAN_42");
        
        ////////////////////////////////////////////////////////////////////////
        
        CBmaszyna.setValue("ARBURG_34");
    }
    public static ArrayList<Maszyna> LadujNazwyMaszyn()
    {
        ArrayList<Maszyna> maszyny = new ArrayList<Maszyna>();
        ////////////////////////////////////////////////////////////////////////
        maszyny.add(new Maszyna("ARBURG_34"));
        maszyny.add(new Maszyna("ARBURG_35"));
        maszyny.add(new Maszyna("ARBURG_36"));
        ////////////////////////////////////////////////////////////////////////
        maszyny.add(new Maszyna("ARBURG_31"));
        maszyny.add(new Maszyna("ARBURG_32"));
        maszyny.add(new Maszyna("ARBURG_33"));
        ///////////////////////////////////////////////////////////////////////
        maszyny.add(new Maszyna("HAITIAN_39"));
        maszyny.add(new Maszyna("HAITIAN_41"));
        maszyny.add(new Maszyna("HAITIAN_42"));
       
        return maszyny;
        
    }
    
}
