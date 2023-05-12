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
        SUSZ_M,
        POSTOJ_NIEUZASADNIONY,
        AWARIA_M,
        AWARIA_F,
        BRAK_ZAOPATRZENIA,
        PRZERWA_P,
        BRAK_OPERATORA,
        WYBRAK,
        
        NIE_PODLACZONE;
    }
   
    public static String STYL_BRAK_DANYCH = (
                                "    -fx-background-color: linear-gradient(derive(#1b1b1b,+80%), derive(#1b1b1b,+90%)),\n" +
                                "                          linear-gradient(derive(#1b1b1b,20%), derive(#1b1b1b, 30%)),\n" +
                                "                          radial-gradient(center 50% 50%,radius 120%,derive(#1b1b1b,-15%), derive(#1b1b1b,30%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 3 3 3 3, 3 3 3 3, 3 3 3 3;\n");

    public static String STYL_BPRACA = (
                                "    -fx-background-color: linear-gradient(derive(#32cd32,+80%), derive(#32cd32,+90%)),\n" +
                                "                          linear-gradient(derive(#32cd32,20%), derive(#32cd32, 30%)),\n" +
                                "                          radial-gradient(center 50% 50%,radius 100%,derive(#32cd32,-15%), derive(#32cd32, 30%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 3 3 3 3, 3 3 3 3, 3 3 3 3;\n");
    

    public static String STYL_PROBY_TECHNOLOGICZNE = (
                                "    -fx-background-color: linear-gradient(derive(#8db600,+80%), derive(#8db600,+90%)),\n" +
                                "                          linear-gradient(derive(#8db600,20%), derive(#8db600, 30%)),\n" +
                                "                          radial-gradient(center 50% 50%,radius 120%,derive(#8db600,-15%), derive(#8db600,30%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 3 3 3 3, 3 3 3 3, 3 3 3 3;\n");
    
    public static String STYL_POSTOJ_PLANOWANY = (
                                "    -fx-background-color: linear-gradient(derive(#cccccc,+80%), derive(#cccccc,+90%)),\n" +
                                "                          linear-gradient(derive(#cccccc,20%), derive(#cccccc, 30%)),\n" +
                                "                          radial-gradient(center 50% 50%,radius 120%,derive(#cccccc,-15%), derive(#cccccc,30%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 3 3 3 3, 3 3 3 3, 3 3 3 3;\n");
     
    public static String STYL_PRZEZBRAJANIE = (
                                "    -fx-background-color: linear-gradient(derive(#22bad9,+80%), derive(#22bad9,+90%)),\n" +
                                "                          linear-gradient(derive(#22bad9,20%), derive(#22bad9, 30%)),\n" +
                                "                          radial-gradient(center 50% 50%,radius 120%,derive(#22bad9,-15%), derive(#22bad9,30%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 3 3 3 3, 3 3 3 3, 3 3 3 3;\n");
    
    public static String STYL_SUSZ_M = (
                                "    -fx-background-color: linear-gradient(derive(#8d6e63,+80%), derive(#8d6e63,+90%)),\n" +
                                "                          linear-gradient(derive(#8d6e63,20%), derive(#8d6e63, 30%)),\n" +
                                "                          radial-gradient(center 50% 50%,radius 120%,derive(#8d6e63,-15%), derive(#8d6e63,30%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 3 3 3 3, 3 3 3 3, 3 3 3 3;\n");
     
    public static String STYL_POSTOJ_NIEUZASADNIONY = (
                                "    -fx-background-color: linear-gradient(derive(#fd930d,80%), derive(#fd930d,90%)),\n" +
                                "                          linear-gradient(derive(#fd930d,20%), derive(#fd930d, 30%)),\n" +
                                "                          radial-gradient(center 50% 50%,radius 100%, #c11d38, #FDDA0D);\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 3 3 3 3, 3 3 3 3, 4 4 4 4;\n");
    public static String STYL_AWARIA_MASZYNY = (
                                "    -fx-background-color: linear-gradient(derive(#dc143c,+80%), derive(#dc143c,+90%)),\n" +
                                "                          linear-gradient(derive(#dc143c,20%), derive(#dc143c, 30%)),\n" +
                                "                          radial-gradient(center 50% 50%,radius 120%,derive(#dc143c,-15%), derive(#dc143c,30%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 3 3 3 3, 3 3 3 3, 3 3 3 3;\n");
    
    public static String STYL_AWARIA_FORMY = (
                                "    -fx-background-color: linear-gradient(derive(#ff6347,+80%), derive(#ff6347,+90%)),\n" +
                                "                          linear-gradient(derive(#ff6347,20%), derive(#ff6347, 30%)),\n" +
                                "                          radial-gradient(center 50% 50%,radius 120%,derive(#ff6347,-15%), derive(#ff6347,30%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 3 3 3 3, 3 3 3 3, 3 3 3 3;\n");
    public static String STYL_BRAK_ZAOPATRZENIA = (
                                "    -fx-background-color: linear-gradient(derive(#ffd700,+80%), derive(#ffd700,+90%)),\n" +
                                "                          linear-gradient(derive(#ffd700,20%), derive(#ffd700, 30%)),\n" +
                                "                          radial-gradient(center 50% 50%,radius 120%,derive(#ffd700,-15%), derive(#ffd700,30%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 3 3 3 3, 3 3 3 3, 3 3 3 3;\n");
    
    public static String STYL_PRZERWA_P = (
                                "    -fx-background-color: linear-gradient(derive(#008080,+80%), derive(#008080,+90%)),\n" +
                                "                          linear-gradient(derive(#008080,20%), derive(#008080, 30%)),\n" +
                                "                          radial-gradient(center 50% 50%,radius 120%,derive(#008080,-15%), derive(#008080,30%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 3 3 3 3, 3 3 3 3, 3 3 3 3;\n");
    
    
    public static String STYL_BRAK_OPERATORA = (
                                "    -fx-background-color: linear-gradient(derive(#8e44ad,+80%), derive(#8e44ad,+90%)),\n" +
                                "                          linear-gradient(derive(#8e44ad,20%), derive(#8e44ad, 30%)),\n" +
                                "                          radial-gradient(center 50% 50%,radius 120%,derive(#8e44ad,-15%), derive(#8e44ad,30%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 3 3 3 3, 3 3 3 3, 3 3 3 3;\n");
     
    
    public static String STYL_WYBRAK = (
                                "    -fx-background-color: linear-gradient(derive(#ff8c00,+80%), derive(#ff8c00,+90%)),\n" +
                                "                          linear-gradient(derive(#ff8c00,20%), derive(#ff8c00, 30%)),\n" +
                                "                          radial-gradient(center 50% 50%,radius 120%,derive(#ff8c00,-15%), derive(#ff8c00,30%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 3 3 3 3, 3 3 3 3, 3 3 3 3;\n");
    
     
     public static String STYL_NIE_PODLACZONE = (
                                "    -fx-background-color: linear-gradient(derive(#ffffff,-80%), derive(#ffffff,-90%)),\n" +
                                "                          linear-gradient(derive(#ffffff,20%), derive(#ffffff, 30%)),\n" +
                                "                          radial-gradient(center 50% 50%,radius 120%,derive(#ffffff,-15%), derive(#ffffff,30%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 3 3 3 3, 3 3 3 3, 3 3 3 3;\n");
     
     public static String STYL_TEKST = (" -fx-text-fill: white;\n"
                                 + "-fx-font-family: \"Calibri\";\n"
                                 + "-fx-text-fill: #E5E4E2;\n"
                                 + "-fx-font-size: 28px;\n");
     
     
     //linear-gradient(white, #d0d0d0)
    
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
        if(statu.equals(Stan.SUSZ_M))
        {
            styl = Maszyna.STYL_SUSZ_M + Maszyna.STYL_TEKST;
        }
        if(statu.equals(Stan.PROBY))
        {
            styl = Maszyna.STYL_PROBY_TECHNOLOGICZNE + Maszyna.STYL_TEKST;
        }
        if(statu.equals(Stan.BRAK_ZAOPATRZENIA))
        {
            styl = Maszyna.STYL_BRAK_ZAOPATRZENIA + Maszyna.STYL_TEKST;
        }
        if(statu.equals(Stan.PRZERWA_P))
        {
            styl = Maszyna.STYL_PRZERWA_P + Maszyna.STYL_TEKST;
        }
        if(statu.equals(Stan.BRAK_OPERATORA))
        {
            styl = Maszyna.STYL_BRAK_OPERATORA + Maszyna.STYL_TEKST;
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
