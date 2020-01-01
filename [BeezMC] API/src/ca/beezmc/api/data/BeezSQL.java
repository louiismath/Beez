package ca.beezmc.api.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import ca.beezmc.api.BeezAPI;

public class BeezSQL {
	
	private Connection connection;
    public static String urlbase,host;
	public static String database;
	public static String user;
	public static String pass;
   
    public BeezSQL(String urlbase, String host, String database, String user, String pass) {
    	BeezSQL.urlbase = urlbase;
    	BeezSQL.host = host;
    	BeezSQL.database = database;
    	BeezSQL.user = user;
    	BeezSQL.pass = pass;
    }
    
    public void check() throws SQLException {
		if (host == null || host.isEmpty() || database == null || database.isEmpty() || user == null || user.isEmpty())
			throw new SQLException();
		openConnection();
		connection.createStatement();
	}
   
	public void openConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://"+host+":"+"/"+database
					+"?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false", user, pass);
		} catch (ClassNotFoundException missing) {
			BeezAPI.warn("Impossible de se connecter à MySQL car ce dernier n'est pas installé.");
		} catch (SQLException ex) {
			BeezAPI.warn("Une erreur s'est produite lors de la connection à la base de données.");
			ex.printStackTrace();
		}
		return;
	}
   
    public void disconnect(){
        if(isConnected()){
            try {
                connection.close();
                BeezAPI.log("Déconnection de la base de données effectuée avec succès");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
   
    public boolean isConnected(){
        return connection != null;
    }
    
    public Connection getConnection() {
		return connection;
	}
    
    public void initialize() {
		Bukkit.getScheduler().runTaskAsynchronously(BeezAPI.get(), new Runnable() {

			@Override
			public void run() {
				// créer les tables
				
			}

		});
	}

}
