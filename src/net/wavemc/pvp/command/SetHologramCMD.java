package net.wavemc.pvp.command;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.warp.WaveWarp;
import net.wavemc.pvp.WavePvP;

public class SetHologramCMD implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		if (!sender.hasPermission("wave.cmd.sethologram")) {
			sender.sendMessage("§cVocê não tem permissão.");
			return true;
		}
		
		if (args.length == 0) {
			sender.sendMessage("§cUse /" + label + " <top-players>");
			return true;
		}
		
		String hologramName = args[0].toLowerCase();
		
		if (hologramName.equals("top-players")) {
			WaveWarp warp = WaveBukkit.getInstance().getWarpManager().findWarp(hologramName).isPresent() ?
					WaveBukkit.getInstance().getWarpManager().findWarp(hologramName).get() 
					: WaveBukkit.getInstance().getWarpManager().createWarp(hologramName);
			
			Location location = ((Player)sender).getLocation().clone().add(0.5, 0, 0.5);
			
			WavePvP.handleTopPlayers(location);
			warp.setLocation(location);
			WaveBukkit.getInstance().getWarpManager().getData().save(warp);
			sender.sendMessage("§aVoc§ setou o holograma " + hologramName + "§a.");
		}else {
			sender.sendMessage("§cHolograma n§o encontrado.");
		}
		return true;
	}

}
