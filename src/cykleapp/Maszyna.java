/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cykleapp;

/**
 *
 * @author Marek Madyda
 */
public class Maszyna {
    private String nazwa;
    private Stan status;
    private String styl;
    private boolean selected;
    
    public enum Stan 
    {
        BRAK_DANYCH,
        
        PRACA,
        PROBY,
        POSTOJ_PLANOWANY,
        PRZEZBRAJANIE,
        POSTOJ_NIEUZASADNIONY,
        AWARIA_M,
        AWARIA_F,
        BRAK_ZAOPATRZENIA,
        BRAK_OPERATORA,
        WYBRAK,
        
        NIE_PODLACZONE;
    }
   
    public static String STYL_BRAK_DANYCH = (
                                "    -fx-background-color: linear-gradient(derive(#1b1b1b,+30%), derive(#1b1b1b,+40%)),\n" +
                                "                          linear-gradient(derive(#1b1b1b,80%), derive(#1b1b1b, 80%)),\n" +
                                "                          linear-gradient(derive(#1b1b1b,30%), derive(#1b1b1b,-10%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 5 5 0 0, 4 4 0 0, 3 3 0 0;\n");

    public static String STYL_BPRACA = (
                                "    -fx-background-color: linear-gradient(derive(#32cd32,+30%), derive(#32cd32,+40%)),\n" +
                                "                          linear-gradient(derive(#32cd32,80%), derive(#32cd32, 80%)),\n" +
                                "                          linear-gradient(derive(#32cd32,30%), derive(#32cd32, -10%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 5 5 0 0, 4 4 0 0, 3 3 0 0;\n");
    
    public static String STYL_PROBY_TECHNOLOGICZNE = (
                                "    -fx-background-color: linear-gradient(derive(#8db600,+30%), derive(#8db600,+40%)),\n" +
                                "                          linear-gradient(derive(#8db600,80%), derive(#8db600, 80%)),\n" +
                                "                          linear-gradient(derive(#8db600,30%), derive(#8db600,-10%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 5 5 0 0, 4 4 0 0, 3 3 0 0;\n");
    
    public static String STYL_POSTOJ_PLANOWANY = (
                                "    -fx-background-color: linear-gradient(derive(#cccccc,+30%), derive(#cccccc,+40%)),\n" +
                                "                          linear-gradient(derive(#cccccc,80%), derive(#cccccc, 80%)),\n" +
                                "                          linear-gradient(derive(#cccccc,30%), derive(#cccccc,-10%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 5 5 0 0, 4 4 0 0, 3 3 0 0;\n");
     
    public static String STYL_PRZEZBRAJANIE = (
                                "    -fx-background-color: linear-gradient(derive(#22bad9,+30%), derive(#22bad9,+40%)),\n" +
                                "                          linear-gradient(derive(#22bad9,80%), derive(#22bad9, 80%)),\n" +
                                "                          linear-gradient(derive(#22bad9,30%), derive(#22bad9,-10%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 5 5 0 0, 4 4 0 0, 3 3 0 0;\n");
     
    public static String STYL_POSTOJ_NIEUZASADNIONY = (
                                "    -fx-background-color: linear-gradient(derive(#c11d38,+30%), derive(#ffc857,+40%)),\n" +
                                "                          linear-gradient(derive(#ffc857,80%), derive(#c11d38, 80%)),\n" +
                                "                          linear-gradient(derive(#c11d38,30%), derive(#ffc857,-10%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 5 5 0 0, 4 4 0 0, 3 3 0 0;\n");
    public static String STYL_AWARIA_MASZYNY = (
                                "    -fx-background-color: linear-gradient(derive(#dc143c,+30%), derive(#dc143c,+40%)),\n" +
                                "                          linear-gradient(derive(#dc143c,80%), derive(#dc143c, 80%)),\n" +
                                "                          linear-gradient(derive(#dc143c,30%), derive(#dc143c,-10%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 5 5 0 0, 4 4 0 0, 3 3 0 0;\n");
    
    public static String STYL_AWARIA_FORMY = (
                                "    -fx-background-color: linear-gradient(derive(#ff6347,+30%), derive(#ff6347,+40%)),\n" +
                                "                          linear-gradient(derive(#ff6347,80%), derive(#ff6347, 80%)),\n" +
                                "                          linear-gradient(derive(#ff6347,30%), derive(#ff6347,-10%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 5 5 0 0, 4 4 0 0, 3 3 0 0;\n");
     public static String STYL_BRAK_ZAOPATRZENIA = (
                                "    -fx-background-color: linear-gradient(derive(#ffd700,+30%), derive(#ffd700,+40%)),\n" +
                                "                          linear-gradient(derive(#ffd700,80%), derive(#ffd700, 80%)),\n" +
                                "                          linear-gradient(derive(#ffd700,30%), derive(#ffd700,-10%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 5 5 0 0, 4 4 0 0, 3 3 0 0;\n");
     public static String STYL_BRAK_OPERATORA = (
                                "    -fx-background-color: linear-gradient(derive(#8e44ad,+30%), derive(#8e44ad,+40%)),\n" +
                                "                          linear-gradient(derive(#8e44ad,80%), derive(#8e44ad, 80%)),\n" +
                                "                          linear-gradient(derive(#8e44ad,30%), derive(#8e44ad,-10%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 5 5 0 0, 4 4 0 0, 3 3 0 0;\n");
     
    
    public static String STYL_WYBRAK = (
                                "    -fx-background-color: linear-gradient(derive(#ff8c00,+30%), derive(#ff8c00,+40%)),\n" +
                                "                          linear-gradient(derive(#ff8c00,80%), derive(#ff8c00, 80%)),\n" +
                                "                          linear-gradient(derive(#ff8c00,30%), derive(#ff8c00,-10%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 5 5 0 0, 4 4 0 0, 3 3 0 0;\n");
    
    
     
     
     
     
     
    
    
     
     
     public static String STYL_NIE_PODLACZONE = (
                                "    -fx-background-color: linear-gradient(derive(#ffffff,-30%), derive(#ffffff,-40%)),\n" +
                                "                          linear-gradient(derive(#ffffff,80%), derive(#ffffff, 80%)),\n" +
                                "                          linear-gradient(derive(#ffffff,30%), derive(#ffffff,-10%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 5 5 0 0, 4 4 0 0, 3 3 0 0;\n");
     
     public static String STYL_TEKST = (" -fx-text-fill: white;\n"
                                 + "-fx-font-family: \"Arial\";\n"
                                 + "-fx-text-fill: linear-gradient(white, #d0d0d0);\n"
                                 + "-fx-font-size: 24px;\n");
     
     
     
    
    public void setNazwa(String nazw)
    {
        nazwa = nazw;
    }
    public String getNazwa()
    {
        return nazwa;
    }
    public void setStatus(Stan statu)
    {
        if(statu.equals(Stan.BRAK_DANYCH))
        {
            styl = Maszyna.STYL_BRAK_DANYCH + Maszyna.STYL_TEKST;
        }
        if(statu.equals(Stan.PRACA))
        {
            styl = Maszyna.STYL_BPRACA + Maszyna.STYL_TEKST;
        }
        if(statu.equals(Stan.WYBRAK))
        {
            styl = Maszyna.STYL_WYBRAK + Maszyna.STYL_TEKST;
        }
        if(statu.equals(Stan.POSTOJ_NIEUZASADNIONY))
        {
            styl = Maszyna.STYL_POSTOJ_NIEUZASADNIONY + Maszyna.STYL_TEKST;
        }
        if(statu.equals(Stan.AWARIA_M))
        {
            styl = Maszyna.STYL_AWARIA_MASZYNY + Maszyna.STYL_TEKST;
        }
        if(statu.equals(Stan.AWARIA_F))
        {
            styl = Maszyna.STYL_AWARIA_FORMY + Maszyna.STYL_TEKST;
        }
        if(statu.equals(Stan.PRZEZBRAJANIE))
        {
            styl = Maszyna.STYL_PRZEZBRAJANIE + Maszyna.STYL_TEKST;
        }
        if(statu.equals(Stan.PROBY))
        {
            styl = Maszyna.STYL_PROBY_TECHNOLOGICZNE + Maszyna.STYL_TEKST;
        }
        if(statu.equals(Stan.BRAK_ZAOPATRZENIA))
        {
            styl = Maszyna.STYL_BRAK_ZAOPATRZENIA + Maszyna.STYL_TEKST;
        }
        if(statu.equals(Stan.POSTOJ_PLANOWANY))
        {
            styl = Maszyna.STYL_POSTOJ_PLANOWANY + Maszyna.STYL_TEKST;
        }
        if(statu.equals(Stan.NIE_PODLACZONE))
        {
            styl = Maszyna.STYL_NIE_PODLACZONE + Maszyna.STYL_TEKST;
        }
        status = statu;
    }
    public Stan getStatus()
    {
        return status;
    }
    public Maszyna(String nazwa_M)
    {
        nazwa = nazwa_M;
        selected = false;
        setStatus(Stan.BRAK_DANYCH);
    }
    public String getStyl()
    {
        return styl;
    }
    public boolean isSelected() {
        return selected;
    }
 
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public String toString() {
        return this.nazwa;
    }
    public static String getStylNiePodlaczone()
    {
        return STYL_NIE_PODLACZONE + STYL_TEKST;
    }
    
}
