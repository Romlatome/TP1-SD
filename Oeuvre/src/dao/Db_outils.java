/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import javax.naming.InitialContext;
import javax.sql.DataSource;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;
/**
 *
 * @author alain
 */
public class Db_outils {

    public Db_outils() {
    }

    public int Get_Identifiant(Connection connection, String id) throws Exception {
        CallableStatement cs = null;
             try {
                 Connexion cnx = new Connexion();
                 cnx.setConnexion();
                 connection = cnx.getConnexion();
                 cs = connection.prepareCall("{? = call inc_parametre(?)}");
                 cs.registerOutParameter(1,Types.INTEGER);
                 cs.setString(2,id);
                 cs.execute();
                 return (cs.getInt(1));

        }catch(Exception e){
            throw e;
        }finally{
            try{
                if (cs != null) cs.close();
            }catch(Exception e){
                throw e;
            }
        }
    }
}
