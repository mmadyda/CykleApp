/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cykleapp;

import com.jfoenix.controls.JFXComboBox;
import java.util.ArrayList;


//DODAWANIE WTRYSKAREK DO LAYOUT DODAWANIE WTRYSKAREK DO LAYOUT DODAWANIE WTRYSKAREK DO LAYOUT DODAWANIE WTRYSKAREK DO LAYOUT
public class IstniejaceMaszynyKoniakow {
    public static void LadujIstniejaceMaszyny(JFXComboBox<String> CBmaszyna)
    {
        CBmaszyna.getItems().clear();
        CBmaszyna.getItems().add("TEST");
        ////////////////////////////////////////////////////////////////////////
        CBmaszyna.getItems().add("KO_1");

        ////////////////////////////////////////////////////////////////////////

        
        ////////////////////////////////////////////////////////////////////////
        
        CBmaszyna.setValue("ST_1");
    }
    public static ArrayList<Maszyna> LadujNazwyMaszyn()
    {
        ArrayList<Maszyna> maszyny = new ArrayList<Maszyna>();
        ////////////////////////////////////////////////////////////////////////
        maszyny.add(new Maszyna("KO_1"));

        ////////////////////////////////////////////////////////////////////////

        return maszyny;
        
    }
    
}
