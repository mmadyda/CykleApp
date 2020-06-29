/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cykleapp;

/**
 *
 * @author DELL
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
        WYBRAK,
        POSTOJ_NIEUZASADNIONY,
        AWARIA_M,
        AWARIA_F,
        PRZEZBRAJANIE,
        PROBY,
        BRAK_ZAOPATRZENIA,
        POSTOJ_PLANOWANY;
    }
   
    public static String STYL_BRAK_DANYCH = (
                                "    -fx-background-color: linear-gradient(derive(#2F4F4F,-30%), derive(#2F4F4F,-40%)),\n" +
                                "                          linear-gradient(derive(#2F4F4F,80%), derive(#2F4F4F, 0%)),\n" +
                                "                          linear-gradient(derive(#2F4F4F,30%), derive(#2F4F4F,-10%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 5 5 0 0, 4 4 0 0, 3 3 0 0;\n");

    public static String STYL_BPRACA = (
                                "    -fx-background-color: linear-gradient(derive(#22bad9,-30%), derive(#22bad9,-40%)),\n" +
                                "                          linear-gradient(derive(#22bad9,80%), derive(#22bad9, 0%)),\n" +
                                "                          linear-gradient(derive(#22bad9,30%), derive(#22bad9,-10%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 5 5 0 0, 4 4 0 0, 3 3 0 0;\n");
    
    public static String STYL_WYBRAK = (
                                "    -fx-background-color: linear-gradient(derive(#ff6600,-30%), derive(#ff6600,-40%)),\n" +
                                "                          linear-gradient(derive(#ff6600,80%), derive(#ff6600, 0%)),\n" +
                                "                          linear-gradient(derive(#ff6600,30%), derive(#ff6600,-10%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 5 5 0 0, 4 4 0 0, 3 3 0 0;\n");
    
    public static String STYL_AWARIA_MASZYNY = (
                                "    -fx-background-color: linear-gradient(derive(#cc0000,-30%), derive(#cc0000,-40%)),\n" +
                                "                          linear-gradient(derive(#cc0000,80%), derive(#cc0000, 0%)),\n" +
                                "                          linear-gradient(derive(#cc0000,30%), derive(#cc0000,-10%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 5 5 0 0, 4 4 0 0, 3 3 0 0;\n");
    
     public static String STYL_AWARIA_FORMY = (
                                "    -fx-background-color: linear-gradient(derive(#b84dff,-30%), derive(#b84dff,-40%)),\n" +
                                "                          linear-gradient(derive(#b84dff,80%), derive(#b84dff, 0%)),\n" +
                                "                          linear-gradient(derive(#b84dff,30%), derive(#b84dff,-10%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 5 5 0 0, 4 4 0 0, 3 3 0 0;\n");
     
     public static String STYL_PRZEZBRAJANIE = (
                                "    -fx-background-color: linear-gradient(derive(#39e600,-30%), derive(#39e600,-40%)),\n" +
                                "                          linear-gradient(derive(#39e600,80%), derive(#39e600, 0%)),\n" +
                                "                          linear-gradient(derive(#39e600,30%), derive(#39e600,-10%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 5 5 0 0, 4 4 0 0, 3 3 0 0;\n");
     
     public static String STYL_PROBY_TECHNOLOGICZNE = (
                                "    -fx-background-color: linear-gradient(derive(#333399,-30%), derive(#333399,-40%)),\n" +
                                "                          linear-gradient(derive(#333399,80%), derive(#333399, 0%)),\n" +
                                "                          linear-gradient(derive(#333399,30%), derive(#333399,-10%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 5 5 0 0, 4 4 0 0, 3 3 0 0;\n");
     
     public static String STYL_BRAK_ZAOPATRZENIA = (
                                "    -fx-background-color: linear-gradient(derive(#f5f53d,-30%), derive(#f5f53d,-40%)),\n" +
                                "                          linear-gradient(derive(#f5f53d,80%), derive(#f5f53d, 0%)),\n" +
                                "                          linear-gradient(derive(#f5f53d,30%), derive(#f5f53d,-10%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 5 5 0 0, 4 4 0 0, 3 3 0 0;\n");
     
     public static String STYL_POSTOJ_PLANOWANY = (
                                "    -fx-background-color: linear-gradient(derive(#cccccc,-30%), derive(#cccccc,-40%)),\n" +
                                "                          linear-gradient(derive(#cccccc,80%), derive(#cccccc, 0%)),\n" +
                                "                          linear-gradient(derive(#cccccc,30%), derive(#cccccc,-10%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 5 5 0 0, 4 4 0 0, 3 3 0 0;\n");
     
     public static String STYL_POSTOJ_NIEUZASADNIONY = (
                                "    -fx-background-color: linear-gradient(derive(#000000,-30%), derive(#000000,-40%)),\n" +
                                "                          linear-gradient(derive(#000000,80%), derive(#000000, 0%)),\n" +
                                "                          linear-gradient(derive(#000000,30%), derive(#000000,-10%));\n" +
                                "    -fx-background-insets: 0,1,2;\n" +
                                "    -fx-background-radius: 5 5 0 0, 4 4 0 0, 3 3 0 0;\n");
     
     public static String STYL_TEKST = (" -fx-text-fill: white;\n"
                                 + "-fx-font-family: \"Arial\";\n"
                                 + "-fx-text-fill: linear-gradient(white, #d0d0d0);\n"
                                 + "-fx-font-size: 16px;\n");
     
     
     
    
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
    
}
