package net.wavemc.pvp.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class Aplicar implements CommandExecutor {
	


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player target;
		
		if (label.equalsIgnoreCase("aplicar") && sender instanceof Player) {
	         target = (Player)sender;
	         target.sendMessage("§b§lLINK DO FORMULÁRIO: §fhttps://docs.google.com/forms/d/15cX6OHd98XKeFv7S-2sGhDyokp1UPDKyRuVss7ryr8g/viewform?pli=1&pli=1");
	         return true;
}
		return false;
	}
}