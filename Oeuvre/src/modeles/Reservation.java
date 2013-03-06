/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modeles;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import outils.Utilitaire;
import dao.*;



/**
 *
 * @author alain
 */
public class Reservation {

    private int id_oeuvre;
    private int id_adherent;
    private Date date_reservation;
    private String statut;
    private Adherent adherent;
    private Oeuvre oeuvre;

    public Reservation() {
    }

    /**
     * Initialise l'Adhérent et l'Oeuvre de la Reservation
     * @param id_oeuvre Id de l'oeuvre réservée
     * @param id_adherent Id de l'adhérent réservant
     * @throws Exception
     */
    public Reservation(int id_oeuvre, int id_adherent) throws Exception {
    	
    	Oeuvre o = new Oeuvre();
    	o.Lire_Id(id_oeuvre);
    	this.setOeuvre(o);
    	this.setId_oeuvre(o.getId_oeuvre());
    	
    	Adherent a = new Adherent();
    	a.Lire_Id(id_adherent);
    	this.setAdherent(a);
    	this.setId_adherent(a.getId_adherent());
    
    }
    
    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }


    public Oeuvre getOeuvre() {
        return oeuvre;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
    }

    public int getId_oeuvre() {
        return id_oeuvre;
    }

    public void setId_oeuvre(int id_oeuvre) {
        this.id_oeuvre = id_oeuvre;
    }

    public int getId_adherent() {
        return id_adherent;
    }

    public void setId_adherent(int id_adherent) {
        this.id_adherent = id_adherent;
    }

    public java.util.Date getDate_reservation() {
        return date_reservation;
    }

    /**
     * Convertit une chaîne en date avant de l'affecter
     * @param date_reservation
     * @throws Exception
     */
    public void setDate_reservation(String date_reservation) throws Exception {
        this.date_reservation = Utilitaire.StrToDate(date_reservation, "yyyy-MM-dd");
    }


    /**
     * Lire une reservation
     * @return List<Reservation> Collection de Réservations
     * @throws Exception
     */
    public void Lire(int id) throws Exception {
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	Connection connection = null;
    	

        try {

        	Connexion cnx = new Connexion();
        	cnx.setConnexion();
        	connection = cnx.getConnexion();
        	
        	ps =  connection.prepareStatement("Select * from reservation where id_oeuvrevente="+id);
        	rs = ps.executeQuery();
        	while (rs.next()){
        		int id_oeuvre = rs.getInt("id_oeuvrevente");
        		int id_adherent = rs.getInt("id_adherent");
        		
        		Oeuvre o = new Oeuvre();
            	o.Lire_Id(id_oeuvre);
            	this.setOeuvre(o);
            	this.setId_oeuvre(o.getId_oeuvre());
            	
            	Adherent a = new Adherent();
            	a.Lire_Id(id_adherent);
            	this.setAdherent(a);
            	this.setId_adherent(a.getId_adherent());
        		
            	this.setStatut(rs.getString("statut"));
        		System.out.println("date"+rs.getString("date_reservation"));
        		this.setDate_reservation(rs.getString("date_reservation"));
       	} 
        	
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }}
    
    /**
     * Liste des Réservations en Attente
     * @return List<Reservation> Collection de Réservations
     * @throws Exception
     */
    public ArrayList<Reservation> Liste() throws Exception {
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	Connection connection = null;
    	
    	ArrayList<Reservation> lReservations = new ArrayList<Reservation>();
    	

        try {

        	Connexion cnx = new Connexion();
        	cnx.setConnexion();
        	connection = cnx.getConnexion();
        	
        	ps =  connection.prepareStatement("Select * from reservation");
        	rs = ps.executeQuery();
        	while (rs.next()){
        		int id_oeuvre = rs.getInt("id_oeuvrevente");
        		int id_adherent = rs.getInt("id_adherent");
        		Reservation r = new Reservation(id_oeuvre,id_adherent);
        		r.setStatut(rs.getString("statut"));
        		System.out.println("date"+rs.getString("date_reservation"));
        		r.setDate_reservation(rs.getString("date_reservation"));
        		lReservations.add(r);
        	} 
        	
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lReservations;
    }
    /**
     * Met à jour une Réservation dans la base de données
     * @throws Exception
     */
    public void Modifier() throws Exception {
    	PreparedStatement ps = null;
    	
    	Connection connection = null;

        try {
        		
        	Connexion c = new Connexion();
        	c.setConnexion();
        	connection = c.getConnexion();
        	String mysql = "UPDATE reservation SET statut ='Confirmee' WHERE id_oeuvrevente ="+this.getId_oeuvre()+";";
        	ps = connection.prepareStatement(mysql);
        	
        	ps.execute();

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Insert une Réservation dans la base de données
     * @throws Exception
     */
    public void Ajouter() throws Exception {
    	PreparedStatement ps = null;
    	
    	Connection connection = null;


        try {
        	
        	Connexion c = new Connexion();
        	c.setConnexion();
        	connection = c.getConnexion();
        	DateFormat dateFormatpers = new SimpleDateFormat("yyyy-MM-dd");
			String dd = dateFormatpers.format(this.getDate_reservation());
        	System.out.println("blablab" + dd);
        	String mysql = "INSERT INTO reservation VALUES ('"+this.getOeuvre().getId_oeuvre()+"','"+this.getAdherent().getId_adherent()+"','Attente',\'"+dd+"\')";
        	
        	ps = connection.prepareStatement(mysql);
        	
        	ps.execute();


        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
