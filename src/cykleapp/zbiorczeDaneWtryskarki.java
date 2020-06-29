/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cykleapp;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author DELL
 */
public class zbiorczeDaneWtryskarki {
    private final SimpleStringProperty nazwa;
    private final SimpleStringProperty wartosc;
    
    
    public zbiorczeDaneWtryskarki(String Inazwa,String Iwartosc)
    {
        this.nazwa = new SimpleStringProperty(Inazwa);
        this.wartosc = new SimpleStringProperty(Iwartosc);
    }
    
        //NAZWA
        public String getNazwa() {
            return nazwa.get();
        }
 
        public void setNazwa(String Inazwa) {
            nazwa.set(Inazwa);
        }
        //WARTOŚĆ
        public String getWartosc() {
            return wartosc.get();
        }
 
        public void setWartosc(String Iwartosc) {
            wartosc.set(Iwartosc);
        }
}
