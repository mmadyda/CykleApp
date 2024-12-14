/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cykleapp;


import java.sql.*;
public class mysqlconnect {
    Connection conn = null;
    private static miejsce m = miejsce.FIRMA;
    public static void setMiejsce(miejsce mi)
    {
        m = mi;
    }
    public static miejsce getMiejsce()
    {
        return m;
    }

    public static Connection ConnecrDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn;
            

            switch(m){
                case FIRMA:
                    conn = DriverManager.getConnection("jdbc:mysql://10.0.1.215:3306?useSSL=false&autoReconnect=true&useUnicode=no","x","xxxx");
                    System.out.println("Połączono z serwerem MySQL sieć firmowa IP: 10.0.1.215:3306");
                    break;
                case DOM:
                    //połączenie poza siecią firmową
                    conn = DriverManager.getConnection("jdbc:mysql://91.225.157.226:6666?useSSL=false&autoReconnect=true&useUnicode=yes","x","xxxx");
                    System.out.println("Połączono z serwerem MySQL sieć zewnętrzna IP: 91.225.157.226:6666");
                    break;
                default:
                    conn = DriverManager.getConnection("jdbc:mysql://10.0.1.215:3306?useSSL=false&autoReconnect=true&useUnicode=yes","x","xxxx");     
                    System.out.println("Połączono z serwerem MySQL sieć firmowa IP: 10.0.1.215:3306");
            }
            

            return conn;
        } catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    public static enum miejsce{
        FIRMA, DOM
    }
    
    


}
