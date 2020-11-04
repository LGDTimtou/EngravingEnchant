package me.lgdtimtou.engravingenchant;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

	public static Main main;
	FileConfiguration config;
	public DataManager data;
	public SettingsManager settings;
	
	@Override
	public void onEnable() {
		main = this;
		EngravingEnchant.register();
		Bukkit.getPluginManager().registerEvents(new EventListener(), this);
		this.getCommand("engrave").setExecutor(new EngraveCommand());
		this.getCommand("en").setExecutor(new EngraveCommand());
		
		this.data = new DataManager(this);
		data.saveDefaultConfig();
		data.saveConfig();
		this.settings = new SettingsManager(this);
		settings.saveDefaultConfig();
		settings.saveConfig();
	}
	
	@Override
	public void onDisable() {
	}
	
	public static Main getMain() {
		return main;
	}
}
