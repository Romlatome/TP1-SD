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
import java.util.ArrayList;
import java.util.List;

import dao.*;



/**
 *
 * @author arsane
 */
public class Oeuvre {

    private int id_oeuvre;
    private int id_proprietaire;
    private String titre;
    private String etat_oeuvrevente;
    private double prix;
    private Proprietaire proprietaire;

   
	public Oeuvre() {
    }

    /**
     * Initialise le Propriétaire d'une oeuvre
     * @param id_proprietaire Id du propriétaire de l'oeuvre
     * @throws Exception
     */
    public Oeuvre(int id_proprietaire) throws Exception {
        Proprietaire prop = new Proprietaire();
        prop.Lire_Id(id_proprietaire);
        setProprietaire(prop);
        this.setId_proprietaire(prop.getId_proprietaire());

    }

    public int getId_oeuvre() {
        return id_oeuvre;
    }

    public void setId_oeuvre(int id_oeuvre) {
        this.id_oeuvre = id_oeuvre;
    }

    public int getId_proprietaire() {
        return id_proprietaire;
    }

    public void setId_proprietaire(int id_proprietaire) {
        this.id_proprietaire = id_proprietaire;
    }
    
    public String getEtat_oeuvrevente() {
		return etat_oeuvrevente;
	}

	public void setEtat_oeuvrevente(String etat_oeuvrevente) {
		this.etat_oeuvrevente = etat_oeuvrevente;
	}

    /**
     * @return the proprietaire
     */
    public Proprietaire getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Proprietaire proprietaire) {
        this.proprietaire = proprietaire;
    }
    
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    
    
    
    /**
     * Lecture d'une Oeuvre dans la base de données
     * @param id Id de l'oeuvre à lire
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
        	
        	ps =  connection.prepareStatement("Select * from oeuvrevente where id_oeuvrevente ="+id);
        	rs = ps.executeQuery();
        	while (rs.next()){
        		
        		this.setId_oeuvre(rs.getInt("id_oeuvrevente"));
        		this.setTitre(rs.getString("titre_oeuvrevente"));
        		this.setPrix(rs.getDouble("prix_oeuvrevente"));
        		this.setId_proprietaire(rs.getInt("id_proprietaire"));
        		this.setEtat_oeuvrevente(rs.getString("etat_oeuvrevente"));
        		Proprietaire p = new Proprietaire();
        		p.Lire_Id(rs.getInt("id_proprietaire"));
        		this.setProprietaire(p);
        		
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
     * Liste des oeuvres rÈservÈes
     */
    public void OeuvreReservÈe(int id_oeuvre) throws Exception{
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	Connection connection = null;

    	  try {

          	Connexion cnx = new Connexion();
          	cnx.setConnexion();
          	connection = cnx.getConnexion();
          	
          	ps =  connection.prepareStatement("UPDATE oeuvrevente SET etat_oeuvrevente ='R' WHERE id_oeuvrevente = '"+id_oeuvre+"';");
          	 ps.execute();
          	
          	
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
     * Liste des oeuvre
     * @return List<Oeuvre> Collection d'oeuvres
     * @throws Exception
     */
    public ArrayList<Oeuvre> Liste() throws Exception {
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	Connection connection = null;
    	ArrayList<Oeuvre> lOeuvres = new ArrayList<Oeuvre>();

        try {

        	Connexion cnx = new Connexion();
        	cnx.setConnexion();
        	connection = cnx.getConnexion();
        	
        	ps =  connection.prepareStatement("Select * from oeuvrevente");
        	rs = ps.executeQuery();
        	while (rs.next()){
        		Oeuvre o = new Oeuvre();
        		o.setId_oeuvre(rs.getInt("id_oeuvrevente"));
        		o.setTitre(rs.getString("titre_oeuvrevente"));
        		o.setPrix(rs.getDouble("prix_oeuvrevente"));

        		o.setEtat_oeuvrevente(rs.getString("etat_oeuvrevente"));
        		Proprietaire p = new Proprietaire();
        		p.Lire_Id(rs.getInt("id_proprietaire"));
        		o.setProprietaire(p);
        		lOeuvres.add(o);
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
        return lOeuvres;
    }

    /**
     * Met à jour une oeuvre dans la base de données
     * @throws Exception
     */
    public void Modifier() throws Exception {
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	Connection connection = null;

    	 try {

         	Connexion cnx = new Connexion();
         	cnx.setConnexion();
         	connection = cnx.getConnexion();
         	String mysql = "UPDATE oeuvrevente SET titre_oeuvrevente ='"+this.titre+"',prix_oeuvrevente ='"+this.prix+"',id_proprietaire ='"+this.id_proprietaire+"' WHERE id_oeuvrevente = '"+this.id_oeuvre+"';";
         	ps =  connection.prepareStatement(mysql);
         	System.out.println(mysql);
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
     * Insert une oeuvre dans la base de données
     * @throws Exception
     */
    public void Ajouter() throws Exception {


       // Db_outils db_outils;
        PreparedStatement ps = null;
    	ResultSet rs = null;
    	Connection connection = null;
    	
        try {

        	Connexion c = new Connexion();
        	c.setConnexion();
        	connection = c.getConnexion();
        
        	CallableStatement cstmt = connection.prepareCall("{? = call inc_parametre(?)}");
        	cstmt.setInt(2,1);
        	cstmt.registerOutParameter(1, Types.INTEGER);
        	cstmt.execute();
        	System.out.println();
        	String mysql = "INSERT INTO oeuvrevente VALUES ("+cstmt.getInt(1)+",'"+ this.titre+"','L',"+this.prix+","+this.proprietaire.getId_proprietaire()+")";
        	
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
