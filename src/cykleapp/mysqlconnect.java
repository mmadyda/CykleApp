/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cykleapp;


import java.sql.*;
public class mysqlconnect {
    Connection conn = null;
    private static miejsce m = miejsce.SKOCZOW;
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
                case SKOCZOW:
                    conn = DriverManager.getConnection("jdbc:mysql://10.0.1.215:3306?useSSL=false&autoReconnect=true&useUnicode=no","obiady","technitools192");
                    System.out.println("Połączono z serwerem MySQL SKOCZÓW IP: 10.0.1.215:3306");
                    break;
                case USTRON:
                    conn = DriverManager.getConnection("jdbc:mysql://10.0.1.215:3306?useSSL=false&autoReconnect=true&useUnicode=yes","obiady","technitools192");
                    System.out.println("Połączono z serwerem MySQL USTROŃ IP: 10.0.1.215:3306");
                    break;
                case KONIAKOW:
                    conn = DriverManager.getConnection("jdbc:mysql://10.0.1.215:3306?useSSL=false&autoReconnect=true&useUnicode=yes","obiady","technitools192");
                    System.out.println("Połączono z serwerem MySQL KONIAKÓW IP: 10.0.1.215:3306");
                    break;
                default:
                    conn = DriverManager.getConnection("jdbc:mysql://10.0.1.215:3306?useSSL=false&autoReconnect=true&useUnicode=yes","obiady","technitools192");     
                    System.out.println("Połączono z serwerem MySQL DEFAULT SKOCZÓW IP: 10.0.1.215:3306");
            }
            
            //Connection conn = DriverManager.getConnection("jdbc:mysql://89.161.232.241:3306/14443643_projekt?useSSL=false&autoReconnect=true&useUnicode=yes","14443643_projekt","Pro2017#");
            

            return conn;
        } catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    public static enum miejsce{
        SKOCZOW,USTRON,KONIAKOW
    }
    
    


}

//połączenie ze skoczowem w jednej sieci
//Connection conn = DriverManager.getConnection("jdbc:mysql://10.0.1.215:3306?useSSL=false&autoReconnect=true&useUnicode=yes","obiady","technitools192");
//stare home.pl Connection conn = DriverManager.getConnection("jdbc:mysql://89.161.232.241:3306/14443643_projekt?useSSL=false&autoReconnect=true&useUnicode=yes","14443643_projekt","Pro2017#");
//połączenie z koniakowa  Connection conn = DriverManager.getConnection("jdbc:mysql://91.225.157.226:7777?useSSL=false&autoReconnect=true&useUnicode=yes","obiady","technitools192");