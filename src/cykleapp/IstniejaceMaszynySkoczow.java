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
        //SKOCZÓW
        CBmaszyna.getItems().add("ST_27"); 
        CBmaszyna.getItems().add("ST_46");
        CBmaszyna.getItems().add("ST_48");
        CBmaszyna.getItems().add("ST_49");
        CBmaszyna.getItems().add("ST_50");
        CBmaszyna.getItems().add("ST_51");
        CBmaszyna.getItems().add("ST_53");
        CBmaszyna.getItems().add("ST_55");
        CBmaszyna.getItems().add("ST_57");
        CBmaszyna.getItems().add("ST_58");
        CBmaszyna.getItems().add("ST_59");
        CBmaszyna.getItems().add("ST_60");  
        CBmaszyna.getItems().add("ST_63");
        CBmaszyna.getItems().add("ST_65");
        CBmaszyna.getItems().add("ST_66");
        CBmaszyna.getItems().add("ST_67");
        CBmaszyna.getItems().add("ST_68");
        CBmaszyna.getItems().add("ST_69");
        CBmaszyna.getItems().add("ST_70");
        CBmaszyna.getItems().add("ST_71");
        CBmaszyna.getItems().add("ST_72");        
        CBmaszyna.getItems().add("ST_74");
        CBmaszyna.getItems().add("ST_75");
        CBmaszyna.getItems().add("ST_76");
        
        CBmaszyna.getItems().add("ST_52");
        CBmaszyna.getItems().add("ST_77");
        CBmaszyna.getItems().add("ST_73");
        
        
        

        
        CBmaszyna.setValue("ST_04");
    }
    public static ArrayList<Maszyna> LadujNazwyMaszyn()
    {
        ArrayList<Maszyna> maszyny = new ArrayList<Maszyna>();
        ////////////////////////////////////////////////////////////////////////
        
        //SKOCŻÓW
        
        maszyny.add(new Maszyna("ST_27"));
        maszyny.add(new Maszyna("ST_46"));
        maszyny.add(new Maszyna("ST_48"));
        maszyny.add(new Maszyna("ST_49"));
        maszyny.add(new Maszyna("ST_50"));
        maszyny.add(new Maszyna("ST_51"));
        maszyny.add(new Maszyna("ST_52"));
        maszyny.add(new Maszyna("ST_53"));
        maszyny.add(new Maszyna("ST_55"));
        maszyny.add(new Maszyna("ST_57"));
        maszyny.add(new Maszyna("ST_58"));
        maszyny.add(new Maszyna("ST_59"));
        maszyny.add(new Maszyna("ST_60"));
        maszyny.add(new Maszyna("ST_63"));
        maszyny.add(new Maszyna("ST_65"));
        maszyny.add(new Maszyna("ST_66"));
        maszyny.add(new Maszyna("ST_67"));
        maszyny.add(new Maszyna("ST_68"));
        maszyny.add(new Maszyna("ST_69"));
        maszyny.add(new Maszyna("ST_70"));
        maszyny.add(new Maszyna("ST_71"));
        maszyny.add(new Maszyna("ST_72"));
        maszyny.add(new Maszyna("ST_74"));
        maszyny.add(new Maszyna("ST_75"));
        maszyny.add(new Maszyna("ST_76"));
        
        maszyny.add(new Maszyna("ST_77"));
        maszyny.add(new Maszyna("ST_73"));
        
        
     
        return maszyny;
        
    }
    
}
