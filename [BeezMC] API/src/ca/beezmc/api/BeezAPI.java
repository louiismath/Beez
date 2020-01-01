package ca.beezmc.api;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import ca.beezmc.api.data.BeezSQL;
import ca.beezmc.api.data.SQLConfig;

public class BeezAPI extends JavaPlugin {
	
	public static BeezAPI instance;
	public static BeezSQL beezSQL;

	@Override
	public void onEnable() {
		instance = this;
		
		new SQLConfig().loadConfig();
		saveDefaultConfig();
		saveConfig();
		
		initializeDatabase();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static BeezAPI get() {
		return instance;
	}
	
	public static void log(String msg) {
		get().getLogger().info(msg);
	}
	
	public static void warn(String msg) {
		get().getLogger().warning(msg);
	}
	
	public void initializeDatabase() {
		Bukkit.getScheduler().runTaskAsynchronously(instance, new Runnable() {

			@Override
			public void run() {
				try {
					BeezSQL database = new BeezSQL("jdbc:mysql://", SQLConfig.sqlConfigFile.getString("host"), SQLConfig.sqlConfigFile.getString("database"), SQLConfig.sqlConfigFile.getString("user"), SQLConfig.sqlConfigFile.getString("pass"));
					database.check();
					database = beezSQL;
					log("Connection à la base de données réussie.");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				beezSQL.initialize();
			}

		});
	}
	
}
