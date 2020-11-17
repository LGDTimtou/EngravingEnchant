package me.lgdtimtou.engravingenchant.commandsnlisteners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class EngraveTabCompleter implements TabCompleter{

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) return null;
		
		List<String> tabCompleteList = new ArrayList<String>();
		switch(args.length) {
		case 1:
			tabCompleteList.add("add");
			tabCompleteList.add("list");
			tabCompleteList.add("<itemtype>");

			return tabCompleteList;
		case 2:
			Bukkit.getOnlinePlayers().stream().forEach(player -> tabCompleteList.add(player.getName()));
			return tabCompleteList;
		default:
			return null;
		}
		
		
		
	}

}
