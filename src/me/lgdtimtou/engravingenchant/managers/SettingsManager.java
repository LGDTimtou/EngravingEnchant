package me.lgdtimtou.engravingenchant.managers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.lgdtimtou.engravingenchant.Main;

public class SettingsManager {
	private Main plugin;
	private FileConfiguration settingsConfig = null;
	private File configFile = null;
	
	public SettingsManager(Main plugin) {
		this.plugin = plugin;
		saveDefaultConfig();
	}
	
	public void reloadConfig() {
		if (this.configFile == null)
			this.configFile = new File(this.plugin.getDataFolder(), "settings.yml");
		
		this.settingsConfig = YamlConfiguration.loadConfiguration(this.configFile);
		
		InputStream defaultStream = this.plugin.getResource("settings.yml");
		if (defaultStream != null) {
			YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
			this.settingsConfig.setDefaults(defaultConfig);
		}
	}
	
	public FileConfiguration getConfig() {
		if (this.settingsConfig == null) 
			reloadConfig();
		
		return this.settingsConfig;
		
	}
	
	public void saveConfig() {
		if (this.settingsConfig == null || this.configFile == null)
			return;
		
		try {
			this.getConfig().save(this.configFile);
		} catch (IOException e) {
			plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.configFile, e);
		}
		
	}
	
	public void saveDefaultConfig() {
		if (this.configFile == null)
			this.configFile = new File(this.plugin.getDataFolder(), "settings.yml");
		if (!this.configFile.exists())
			this.plugin.saveResource("settings.yml", false);
	}
}
