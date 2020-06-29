/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cykleapp;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author <a href="mailto:marekmadyda@gmail.com">Marek Madyda</a>
 */

public class daneObciazenieWtryskarki {
        private final SimpleStringProperty maszyna;
        private final SimpleStringProperty wtrysk;
        private final SimpleStringProperty wybrak;
        private final SimpleStringProperty postoj_n;
        private final SimpleStringProperty awaria_m;
        private final SimpleStringProperty awaria_f;
        private final SimpleStringProperty przezbrajanie;
        private final SimpleStringProperty proby_tech;
        private final SimpleStringProperty brak_zaop;
        private final SimpleStringProperty postoj;
        private final SimpleStringProperty czas_cyklu;
 
    /**
     *
     * @param Idata data zdarzenia
     * @param Iwtrysk liczba wtrysków
     * @param Iwybrak liczba wybraków
     * @param Ibrak_operatora liczba cykli braku operatora
     * @param Iawaria_m liczba cykli awarii maszyny
     * @param Iawaria_f liczba cykli awarii formy
     * @param Iprzezbrajanie liczba cykli przezbrajania
     * @param Iproby_tech liczba cykli prób technologicznych
     * @param Ipostoj liczba cykli postoju
     * @param Iczas_cyklu średni czas cyklu
     */
    public daneObciazenieWtryskarki(String Idata,String Iwtrysk,String Iwybrak,String Ipostoj_n, String Iawaria_m,String Iawaria_f,String Iprzezbrajanie,String Iproby_tech,String Ibrak_zaop,String Ipostoj,String Iczas_cyklu) {
            this.maszyna = new SimpleStringProperty(Idata);
            this.wtrysk = new SimpleStringProperty(Iwtrysk);
            this.wybrak = new SimpleStringProperty(Iwybrak);
            this.postoj_n = new SimpleStringProperty(Ipostoj_n);
            this.awaria_m = new SimpleStringProperty(Iawaria_m);
            this.awaria_f = new SimpleStringProperty(Iawaria_f);
            this.przezbrajanie = new SimpleStringProperty(Iprzezbrajanie);
            this.proby_tech = new SimpleStringProperty(Iproby_tech);
            this.brak_zaop = new SimpleStringProperty(Ibrak_zaop);
            this.postoj = new SimpleStringProperty(Ipostoj);
            this.czas_cyklu = new SimpleStringProperty(Iczas_cyklu);
            
        }
        //DATA
        public String getMaszyna() {
            return maszyna.get();
        }
 
        public void setMaszyna(String Idata) {
            maszyna.set(Idata);
        }
        //WTRYSK
        public String getWtrysk() {
            return wtrysk.get();
        }
 
        public void setWtrysk(String Iwtrysk) {
            wtrysk.set(Iwtrysk);
        }
        //WYBRAK
        public String getWybrak() {
            return wybrak.get();
        }
 
        public void setWybrak(String Iwybrak) {
            wtrysk.set(Iwybrak);
        }
        //BRAK OPERATORA
         public String getPostoj_n() {
            return postoj_n.get();
        }
 
        public void setBrak_operatora(String Ibrak_operatora) {
            postoj_n.set(Ibrak_operatora);
        }
        //AWARIA MASZYNY:
         public String getAwaria_m() {
            return awaria_m.get();
        }
 
        public void setAwaria_m(String Iawaria_m) {
            awaria_m.set(Iawaria_m);
        }
        //AWARIA FORMY
         public String getAwaria_f() {
            return awaria_f.get();
        }
 
        public void setAwaria_f(String Iawaria_f) {
            awaria_f.set(Iawaria_f);
        }
        //PRZEZBRAJANIE
         public String getPrzezbrajanie() {
            return przezbrajanie.get();
        }
 
        public void setPrzezbrajanie(String Iprzezbrajanie) {
            przezbrajanie.set(Iprzezbrajanie);
        }
        //PRÓBY TECHNOLOGICZNE
         public String getProby_tech() {
            return proby_tech.get();
        }
 
        public void setProby_tech(String Iproby_tech) {
            proby_tech.set(Iproby_tech);
        }
        //BRAK ZAOPATRZENIA
         public String getBrak_zaop() {
            return brak_zaop.get();
        }
 
        public void setBrak_zaop(String Ibrak_zaop) {
            brak_zaop.set(Ibrak_zaop);
        }
        //POSTÓJ
         public String getPostoj() {
            return postoj.get();
        }
 
        public void setPostoj(String Ipostoj) {
            postoj.set(Ipostoj);
        }
        //ŚREDNI CZAS CYKLU
         public String getCzas_cyklu() {
            return czas_cyklu.get();
        }
 
        public void setCzas_cyklu(String Iczas_cyklu) {
            czas_cyklu.set(Iczas_cyklu);
        }
        
    }

