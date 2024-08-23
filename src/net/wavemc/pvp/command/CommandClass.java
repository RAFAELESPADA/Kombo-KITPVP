package net.wavemc.pvp.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.wavemc.pvp.WavePvP;

public class CommandClass {
	
	public WavePvP main = WavePvP.getInstance2();
	
	public boolean isNumeric(String arg0) {
		try {
			Integer.valueOf(arg0);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void send(CommandSender sender, String message) {
		sender.sendMessage(message);
	}
}
