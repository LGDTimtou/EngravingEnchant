package me.lgdtimtou.engravingenchant;

import java.util.List;

import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class Utilities {
	
	static Main plugin;
	
	public Utilities(Main main) {
		plugin = main;
	}
	
	public static String format(String str) {
		String result = ChatColor.translateAlternateColorCodes('&', str);
		return result;
	}
	public static String getConfigString(String str) {
		return format(plugin.settings.getConfig().getString(str));
	}
	public static String stringBuilder(List<String> list) {
		StringBuilder strbl = new StringBuilder();
		
		for (String str : list) {
			strbl.append(str);
			strbl.append(", ");
		}
		
		String string = strbl.toString();
		return string;
		
	}
	public static boolean checkEngraved(ItemStack item) {
		
		try {
			List<String> lore = item.getItemMeta().getLore();
			for (String str : lore) {
				if (str.contains(Main.enchantName))
					return true;
			}
			return false;
		} catch (Exception e)
		{
			return false;
		}
	}
}
