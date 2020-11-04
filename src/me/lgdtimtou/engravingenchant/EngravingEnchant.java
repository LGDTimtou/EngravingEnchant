package me.lgdtimtou.engravingenchant;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;

public class EngravingEnchant{
	public final static Enchantment ENGRAVING = new EnchantmentWrapper("engraving", "Engraving", 1);
	
	
	public static void register() {
		boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(ENGRAVING);
		
		if (!registered)
			registerEnchantment(ENGRAVING);
	}
	
	
	
	public static void registerEnchantment(Enchantment enchantment) {
		boolean registered = true;
		try {
			Field f = Enchantment.class.getDeclaredField("acceptingNew");
			f.setAccessible(true);
			f.set(null, true);
			Enchantment.registerEnchantment(enchantment);
		} catch (Exception e) {
			registered = false;
			e.printStackTrace();
		}
		if (registered)
			Bukkit.getConsoleSender().sendMessage("The Engraving enchantment has been registered!");
		
	}

}
