package ca.beezmc.api.data;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import ca.beezmc.api.BeezAPI;

public class SQLConfig {
	
	public static File sqlFile = new File(BeezAPI.get().getDataFolder(), "sql.yml");
	public static FileConfiguration sqlConfigFile = YamlConfiguration.loadConfiguration(sqlFile);
	
	public void loadConfig() {
		if(!BeezAPI.get().getDataFolder().exists()) {
			BeezAPI.get().getDataFolder().mkdir();
		}
		
		if(!sqlFile.exists()) {
			try (InputStream in = BeezAPI.get().getResource("sql.yml")){
				Files.copy(in, sqlFile.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
