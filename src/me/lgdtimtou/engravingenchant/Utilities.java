package me.lgdtimtou.engravingenchant;

import java.util.List;

import net.md_5.bungee.api.ChatColor;

public class Utilities {
	public static String format(String str) {
		String result = ChatColor.translateAlternateColorCodes('&', str);
		return result;
	}
	public static String getConfigString(String str) {
		return Utilities.format(Main.getMain().settings.getConfig().getString(str));
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
}
