/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modeles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.*;


/**
 *
 * @author alain
 */
public class Adherent {
    private int id_adherent;
    private String nom_adherent;
    private String prenom_adherent;

    public Adherent() {
    }

    /**
     * @return the id_adherent
     */
    public int getId_adherent() {
        return id_adherent;
    }

    /**
     * @param id_adherent the id_adherent to set
     */
    public void setId_adherent(int id_adherent) {
        this.id_adherent = id_adherent;
    }

    /**
     * @return the nom_adherent
     */
    public String getNom_adherent() {
        return nom_adherent;
    }

    /**
     * @param nom_adherent the nom_adherent to set
     */
    public void setNom_adherent(String nom_adherent) {
        this.nom_adherent = nom_adherent;
    }

    /**
     * @return the prenom_adherent
     */
    public String getPrenom_adherent() {
        return prenom_adherent;
    }

    /**
     * @param prenom_adherent the prenom_adherent to set
     */
    public void setPrenom_adherent(String prenom_adherent) {
        this.prenom_adherent = prenom_adherent;
    }

    /**
     * Lecture d'un adhérent dans la base de données
     * @param id Id de l'adhérent à lire
     * @throws Exception
     */
    public void Lire_Id(int id) throws Exception {
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	Connection connection = null;

        try {

        	Connexion cnx = new Connexion();
        	cnx.setConnexion();
        	connection = cnx.getConnexion();
        	
        	ps =  connection.prepareStatement("Select * from adherent where id_adherent ="+id);
        	rs = ps.executeQuery();
        	while (rs.next()){
        		
        		this.setId_adherent(rs.getInt("id_adherent"));
        		this.setNom_adherent(rs.getString("nom_adherent"));
        		this.setPrenom_adherent(rs.getString("prenom_adherent"));	
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
    }

    /**
     * Liste des adhérents
     * @return List<Adherent> Collection d'adhérents
     * @throws Exception
     */
    public ArrayList<Adherent> Liste() throws Exception {
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	Connection connection = null;
    	ArrayList<Adherent> lAdherent = new ArrayList<Adherent>();

        try {

        	Connexion cnx = new Connexion();
        	cnx.setConnexion();
        	connection = cnx.getConnexion();
        	
        	ps =  connection.prepareStatement("Select * from adherent");
        	rs = ps.executeQuery();
        	while (rs.next()){
        		Adherent a = new Adherent();
        		a.setId_adherent(rs.getInt("id_adherent"));
        		a.setNom_adherent(rs.getString("nom_adherent"));
        		a.setPrenom_adherent(rs.getString("prenom_adherent"));	
        		lAdherent.add(a);
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
        return lAdherent;
    }
    
}
