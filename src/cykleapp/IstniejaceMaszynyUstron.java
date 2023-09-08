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
        CBmaszyna.getItems().add("UST_46");
        CBmaszyna.getItems().add("UST_43");
        CBmaszyna.getItems().add("UST_53");
        CBmaszyna.getItems().add("UST_50");
        CBmaszyna.getItems().add("UST_52");
        CBmaszyna.getItems().add("UST_41");
        ////////////////////////////////////////////////////////////////////////
        CBmaszyna.getItems().add("UST_11");
        CBmaszyna.getItems().add("UST_14");
        CBmaszyna.getItems().add("UST_15");
        CBmaszyna.getItems().add("UST_16");
        CBmaszyna.getItems().add("UST_17");
        CBmaszyna.getItems().add("UST_18");
        CBmaszyna.getItems().add("UST_37");
        CBmaszyna.getItems().add("UST_38");
        CBmaszyna.getItems().add("UST_39");
        CBmaszyna.getItems().add("UST_40");
        CBmaszyna.getItems().add("UST_42");
        CBmaszyna.getItems().add("UST_47");
        CBmaszyna.getItems().add("UST_48");
        CBmaszyna.getItems().add("UST_51");
        CBmaszyna.getItems().add("UST_54");
        CBmaszyna.getItems().add("UST_55");
        CBmaszyna.getItems().add("UST_56");
        CBmaszyna.getItems().add("UST_57");
        
        
        
        CBmaszyna.setValue("UST_41");
    }
    public static ArrayList<Maszyna> LadujNazwyMaszyn()
    {
        ArrayList<Maszyna> maszyny = new ArrayList<Maszyna>();
        ////////////////////////////////////////////////////////////////////////
        maszyny.add(new Maszyna("UST_33"));
        maszyny.add(new Maszyna("UST_44"));
        maszyny.add(new Maszyna("UST_45"));
        maszyny.add(new Maszyna("UST_46"));
        maszyny.add(new Maszyna("UST_43"));
        maszyny.add(new Maszyna("UST_53"));
        maszyny.add(new Maszyna("UST_50"));
        maszyny.add(new Maszyna("UST_52"));
        maszyny.add(new Maszyna("UST_41"));
        ////////////////////////////////////////////////////////////////////////
        maszyny.add(new Maszyna("UST_11"));
        maszyny.add(new Maszyna("UST_14"));
        maszyny.add(new Maszyna("UST_15"));
        maszyny.add(new Maszyna("UST_16"));
        maszyny.add(new Maszyna("UST_17"));
        maszyny.add(new Maszyna("UST_18"));
        maszyny.add(new Maszyna("UST_37"));
        maszyny.add(new Maszyna("UST_38"));
        maszyny.add(new Maszyna("UST_39"));
        maszyny.add(new Maszyna("UST_40"));
        maszyny.add(new Maszyna("UST_42"));
        maszyny.add(new Maszyna("UST_47"));
        maszyny.add(new Maszyna("UST_48"));
        maszyny.add(new Maszyna("UST_51"));
        maszyny.add(new Maszyna("UST_54"));
        maszyny.add(new Maszyna("UST_55"));
        maszyny.add(new Maszyna("UST_56"));
        maszyny.add(new Maszyna("UST_57"));

       
        return maszyny;
        
    }
    
}
