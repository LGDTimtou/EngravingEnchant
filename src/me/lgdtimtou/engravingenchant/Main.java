package me.lgdtimtou.engravingenchant;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.lgdtimtou.engravingenchant.commandsnlisteners.EngraveCommand;
import me.lgdtimtou.engravingenchant.commandsnlisteners.EngraveTabCompleter;
import me.lgdtimtou.engravingenchant.commandsnlisteners.EventListener;
import me.lgdtimtou.engravingenchant.managers.DataManager;
import me.lgdtimtou.engravingenchant.managers.SettingsManager;
import me.lgdtimtou.engravingenchant.registering.EngravingEnchant;

public class Main extends JavaPlugin{

	
	
	FileConfiguration config;
	public DataManager data;
	public SettingsManager settings;
	public Utilities util;
	
	
	public static String enchantName;
	public static String killedPrefix;
	
	@Override
	public void onEnable() {
		EngravingEnchant.register();
		registerCommands();
		registerListener();
		registerConfigs();
		enchantName = this.settings.getConfig().getString("EnchantName");
		killedPrefix = this.settings.getConfig().getString("KilledNamesPrefix");
		
	}
	
	@Override
	public void onDisable() {
	}
	
	
	
	public void registerConfigs() {
		this.data = new DataManager(this);
		data.saveDefaultConfig();
		data.saveConfig();
		this.settings = new SettingsManager(this);
		settings.saveDefaultConfig();
		settings.saveConfig();
		this.util = new Utilities(this);
	}
	public void registerCommands() {
		this.getCommand("en").setExecutor(new EngraveCommand());
		this.getCommand("en").setTabCompleter(new EngraveTabCompleter());
	}
	public void registerListener() {
		Bukkit.getPluginManager().registerEvents(new EventListener(this), this);
	}
}
