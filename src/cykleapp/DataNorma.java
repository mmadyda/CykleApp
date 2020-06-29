/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cykleapp;

import java.time.LocalDateTime;

/**
 *
 * @author marek
 */
public class DataNorma {
    private Double norma;
    private LocalDateTime data;
    public DataNorma(Double nNorma,LocalDateTime nData)
    {
        norma = nNorma;
        data = nData;
    }
    public double getNorma()
    {
        return norma;
    }
    public LocalDateTime getDate()
    {
        return data;
    }
    public void setNorma(double nNorma)
    
    {
        norma = nNorma;
    }
    public void setData(LocalDateTime nData)
    {
        data = nData;
    }
    @Override
    public String toString()
    {
        String ret = "Norma: "+norma+" Data: "+data.getYear()+"-"+data.getMonth()+"-"+data.getDayOfMonth()+" "+data.getHour()+":"+data.getMinute()+":"+data.getSecond()+"\n";
        
        return ret;
    }
    
}
