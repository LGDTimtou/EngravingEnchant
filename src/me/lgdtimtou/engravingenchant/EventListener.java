package me.lgdtimtou.engravingenchant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;



public class EventListener implements Listener {
	
	ItemStack result = null;
	
	public static boolean checkEngraved(ItemStack item) {
		
		try {
			List<String> lore = item.getItemMeta().getLore();
			for (String str : lore) {
				if (str.contains("Engraved"))
					return true;
			}
			return false;
		} catch (Exception e)
		{
			return false;
		}
	}
	



	
	@EventHandler
	public void onPlayerKilled (PlayerDeathEvent event) {
		if (event.getEntity().getKiller() instanceof Player && event.getEntity() instanceof Player) {
			int value = 0;
			Player killer = event.getEntity().getKiller();
			Player killed = event.getEntity();
			if (killer.getInventory().getItemInMainHand() == null) {
				return;
			}
			if (!killer.getInventory().getItemInMainHand().hasItemMeta())
				return;
			if (!killer.getInventory().getItemInMainHand().getItemMeta().hasEnchant(EngravingEnchant.ENGRAVING))
				return;
			
			ItemStack item = killer.getInventory().getItemInMainHand();
			
			ItemMeta meta = item.getItemMeta();
			List<String> lore = meta.getLore();
			
			
			String strlore = Utilities.stringBuilder(lore);

			try {
				value = (char)(Main.getMain().data.getConfig().getInt("Players." + killer.getUniqueId() + ".Item: " + item.getType().toString() + ".Killed." + killed.getName()) + 1);
			} catch (Exception e) {
				value = 1;
			}
			Main.getMain().data.getConfig().set("Players." + killer.getUniqueId() + ".Item: " + item.getType().toString() + ".Killed." + killed.getName() , value);	
			Main.getMain().data.saveConfig();
			
			World world = killer.getWorld();
			if (Main.getMain().settings.getConfig().getBoolean("ParticlesEnabled")) {
				new BukkitRunnable() {
		            int i = 0;
		            int degree = 0;
		            double y = 0;
		            @Override
		            public void run() {
		                if (i == 40) {
		                    cancel();
		                    return;
		                }
		                if (degree >= 360)
		                	degree = degree - 360;
		                Location location = killer.getLocation();
		                double radians = Math.toRadians(degree);
		                double x = Math.cos(radians);
		                double z = Math.sin(radians);		                
		                location.add(x,y,z);
		                world.spawnParticle(Particle.VILLAGER_HAPPY, location, 3);
		                location.subtract(x,y,z);

		                y = y + 0.05;
		                degree = degree + 18;
		                i++;
		            }
		        }.runTaskTimer(Main.getMain(), 0L, 1L);
			}
					
				

	
			
			
			
			
			
			if (!(strlore.toLowerCase().indexOf("killed: ") >= 0)) {
				 
				lore.add("");
				lore.add(Utilities.getConfigString("EngravedPeopleColor") + "Killed: " + killed.getDisplayName() + " " + value);
				meta.setLore(lore);
				item.setItemMeta(meta);
				return;
			}
			
			for (String str : lore) {

				if (str.contains(killed.getName().toString())) { 
					
					 
					int oldvalue = value - 1;


					String newstrlore = strlore.replace(killed.getDisplayName() + " " + oldvalue, killed.getDisplayName() + " " + value);
					
					String[] lorearray = newstrlore.split(", ");

					List<String> newlore = new ArrayList<String>();
					newlore = Arrays.asList(lorearray);
					

					
					lore.clear();
					meta.setLore(lore);
					meta.setLore(newlore);
					
					item.setItemMeta(meta);
					
					
					return;
				}
				
			}
			
			String prelore = strlore.substring(0, strlore.indexOf("Killed"));
			int lenghtprelore = prelore.length();
			
			if (strlore.length() >= (lenghtprelore + 35) && strlore.length() <= (lenghtprelore + 45)) {
				strlore = Utilities.getConfigString("EngravedPeopleColor") + strlore  + killed.getDisplayName() + " " + value;
				 
			}	
			else if(strlore.length() >= (lenghtprelore + 70) && strlore.length() <= (lenghtprelore + 80)) {
				strlore = Utilities.getConfigString("EngravedPeopleColor") + strlore  + killed.getDisplayName() + " " + value;
				 
			}
			else if(strlore.length() >= (lenghtprelore + 105) && strlore.length() <= (lenghtprelore + 115)) {
				strlore = Utilities.getConfigString("EngravedPeopleColor") + strlore  + killed.getDisplayName() + " " + value;
				 
			}
			else if(strlore.length() >= (lenghtprelore + 140) && strlore.length() <= (lenghtprelore + 150)) {
				strlore = Utilities.getConfigString("EngravedPeopleColor") + strlore  + killed.getDisplayName() + " " + value;
				 
			}
			else if(strlore.length() >= (lenghtprelore + 175)) {
				killer.sendMessage(Utilities.getConfigString("MaxNameMessage"));
				return;
			}
				
			else {
				strlore = Utilities.getConfigString("EngravedPeopleColor") +  strlore  + " " + killed.getDisplayName() + " " + value;
				strlore = strlore.replace(",  ", " ");
				 
			}
			String[] lorearray = strlore.split(", ");

			List<String> newlore = new ArrayList<String>();
			newlore = Arrays.asList(lorearray);
			

			
			lore.clear();
			meta.setLore(lore);
			meta.setLore(newlore);
			
			item.setItemMeta(meta);
			
			
		}
	}
	@EventHandler
	public void onAnvilClickEvent(PrepareAnvilEvent event) {
		final Inventory inv = event.getInventory();
		if (!(inv instanceof AnvilInventory))
			return;
		if (inv.getItem(0) == null || inv.getItem(1) == null)
			return;
		ItemStack item1 = inv.getItem(0);
		ItemStack item2 = inv.getItem(1);
		Boolean item1engraved = checkEngraved(item1);
		Boolean item2engraved = checkEngraved(item2);
		
		if (item1engraved && item2engraved) 
			return;
		
		if (!item1engraved && !item2engraved) 
			return;
		
		if (item1.getType() != item2.getType()) {
			if (item2.getType() != Material.ENCHANTED_BOOK) 
				return;
			
		}
		
		
		Material material = inv.getItem(0).getType();
		ItemStack newitem = new ItemStack(material);
		
		newitem.addUnsafeEnchantment(EngravingEnchant.ENGRAVING, 1);
		
		if (item1.getItemMeta().hasEnchants()) {
			newitem.addUnsafeEnchantments(item1.getEnchantments());
		}
		if (item2.getItemMeta().hasEnchants()) {
			newitem.addUnsafeEnchantments(item2.getEnchantments());
		}
		if (item2.getType() == Material.ENCHANTED_BOOK && !item2engraved) {
			EnchantmentStorageMeta meta =(EnchantmentStorageMeta)item2.getItemMeta();
	        Map<Enchantment, Integer> enchants = meta.getStoredEnchants();
	        newitem.addUnsafeEnchantments(enchants);
		}
		
		
		
		
		ItemMeta oldmeta = item1.getItemMeta();
		ItemMeta newmeta = newitem.getItemMeta();
		List<String> newlore = new ArrayList<String>();
		

		
		if (!item1engraved)
			newlore.add(ChatColor.GRAY + "Engraved");
		
	
		if (oldmeta.hasLore())
			for (String l : oldmeta.getLore())
				newlore.add(l);
		
		
		newmeta.setLore(newlore);
		newmeta.setDisplayName(oldmeta.getDisplayName());
		
		
		newitem.setItemMeta(newmeta);
		event.setResult(newitem);
		
		result = event.getResult();

		return;
			 
		
	}
	@EventHandler
	public void onPlayerAnvilClick(InventoryClickEvent event) {
		int slot;
		ItemStack air = new ItemStack(Material.AIR);
		Inventory inv = event.getInventory();
		Player player = (Player) event.getWhoClicked();
		ItemStack item1 = inv.getItem(0);
		ItemStack item2 = inv.getItem(1);
		if (!(inv instanceof AnvilInventory))
			return;
		if (result == null) {
			return;
		}
		if (!(event.getClickedInventory() instanceof AnvilInventory))
			return;

		try {
			slot = event.getSlot();
		} catch (Exception e) {
			return;
		}
		
		if (slot != 2)
			return;
		
		if (item1.getType() == Material.ENCHANTED_BOOK && item2.getType() == Material.ENCHANTED_BOOK && (checkEngraved(item1) || checkEngraved(item2))) {

			Inventory inv2 = event.getView().getBottomInventory();
			
			if (inv2.firstEmpty() == -1) {
				player.getWorld().dropItem(player.getLocation(), item1);
				player.getWorld().dropItem(player.getLocation(), item2);
				inv.setItem(0, air);
				inv.setItem(1, air);
				player.sendMessage(Utilities.getConfigString("BooksErrorMessage"));
				player.closeInventory();
				return;
			}
			player.getInventory().addItem(item1);
			player.getInventory().addItem(item2);
			inv.setItem(0, air);
			inv.setItem(1, air);
			player.sendMessage(Utilities.getConfigString("BooksErrorMessage"));
			player.closeInventory();
			return;
		}

		ItemStack item = inv.getItem(2);
		event.getWhoClicked().setItemOnCursor(item);
		inv.setItem(0, air);
		inv.setItem(1, air);
		inv.setItem(2, air);
		return;
		
		
		
		
	}

}
