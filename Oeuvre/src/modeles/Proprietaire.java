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
public class Proprietaire {
    private int id_proprietaire;
    private String nom_proprietaire;
    private String prenom_proprietaire;

    public Proprietaire() {
    }

    public int getId_proprietaire() {
        return id_proprietaire;
    }

    public void setId_proprietaire(int id_proprietaire) {
        this.id_proprietaire = id_proprietaire;
    }

    public String getNom_proprietaire() {
        return nom_proprietaire;
    }

    public void setNom_proprietaire(String nom_proprietaire) {
        this.nom_proprietaire = nom_proprietaire;
    }

    public String getPrenom_proprietaire() {
        return prenom_proprietaire;
    }

    public void setPrenom_proprietaire(String prenom_proprietaire) {
        this.prenom_proprietaire = prenom_proprietaire;
    }

    /**
     * Lecture d'un Propriétaire dans la base de données
     * @param id Id du Propriétaire à lire
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
        	
        	ps =  connection.prepareStatement("Select * from proprietaire where id_proprietaire = "+ id+"");
            rs = ps.executeQuery();
        	if (rs.next() ) {
        		this.setId_proprietaire(id);
        		this.setNom_proprietaire(rs.getString("nom_proprietaire"));
        		this.setPrenom_proprietaire(rs.getString("prenom_proprietaire"));

            } else {
                throw new Exception("Proprietaire inconnu !");
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
     * Liste des Propriétaires
     * @return List<Proprietaire> Collection de Propriétaires
     * @throws Exception
     */
    public ArrayList<Proprietaire> Liste() throws Exception {


    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	Connection connection = null;
    	ArrayList<Proprietaire> lProprietaire = new ArrayList<Proprietaire>();

        try {

        	Connexion cnx = new Connexion();
        	cnx.setConnexion();
        	connection = cnx.getConnexion();
        	
        	ps =  connection.prepareStatement("Select * from proprietaire");
        	rs = ps.executeQuery();
        	while (rs.next()){
        		Proprietaire p = new Proprietaire();
        		p.setId_proprietaire(rs.getInt("id_proprietaire"));
        		p.setNom_proprietaire(rs.getString("nom_proprietaire"));
        		p.setPrenom_proprietaire(rs.getString("prenom_proprietaire"));
        		
        		
        		lProprietaire.add(p);
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
        return lProprietaire;
    }
}
