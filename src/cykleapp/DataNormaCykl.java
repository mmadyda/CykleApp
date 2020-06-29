/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cykleapp;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author marek
 */
public class DataNormaCykl {
    private final SimpleStringProperty norma;
    private final SimpleStringProperty cykle;
    private final SimpleStringProperty data;
    private final SimpleStringProperty brak;
    public DataNormaCykl(String nData, String nNorma, String nCykle,String nBrak)
    {
        norma = new SimpleStringProperty(nNorma);
        data = new SimpleStringProperty(nData);
        cykle = new SimpleStringProperty(nCykle);
        brak = new SimpleStringProperty(nBrak);
    }
    public String getNorma()
    {
        return norma.get();
    }
    public String getCykle()
    {
        return cykle.get();
    }
    public String getData()
    {
        return data.get();
    }
    public String getBrak()
    {
        return brak.get();
    }
    public void setNorma(String nNorma)
    
    {
        norma.set(nNorma);
    }
    public void setCykle(String nCykle)
    {
        cykle.set(nCykle);
    }
    public void setData(String nData)
    {
        data.set(nData);
    }
    public void setBrak(String nBrak)
    {
        brak.set(nBrak);
    }
}
