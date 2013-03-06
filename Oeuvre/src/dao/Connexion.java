package dao;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class Connexion {
	private Connection conn = null;
	private static Connexion instance = null;

	// On utilise un singleton
	public static Connexion getInstance() {
		if (instance == null)
			instance = new Connexion();
		return instance;
	}

	public void setConnexion() throws Exception  {
		try {
			Properties properties = new Properties();
			properties.setProperty(InitialContext.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			properties.setProperty(InitialContext.PROVIDER_URL,
					"jnp://localhost:1099");
			InitialContext ctxt = new InitialContext(properties);
			if (ctxt != null) {
				DataSource ds = (DataSource) ctxt.lookup("java:/DSOeuvres");
				this.conn = ds.getConnection();
				
			}

		} catch (Exception e) {
			throw e;
		} 
	}

	public Connection getConnexion() {
		return this.conn;
	}

	
}
