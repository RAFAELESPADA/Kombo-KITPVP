package net.wavemc.pvp.command;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.wavemc.pvp.warp.WaveWarp;
import net.wavemc.warp.provider.Gladiator;
import net.wavemc.warp.provider.OneVsOne;
import net.wavemc.warp.provider.Sumo;





public class WarpJoin implements CommandExecutor { 

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("warpjoin")){
			 Player player = (Player)sender;
			    
			
			
			if(args.length < 1){
				sender.sendMessage("§cUso correto: /warpjoin <arena, duels, lava , fps>");
				return true;
			}
			else if (!WaveWarp.LOBBY.hasPlayer(player.getName())) {
				player.sendMessage("§cVocê não está no LOBBY DO PVP");
				return true;
			}
			if (args[0].equalsIgnoreCase("arena")) {
				WaveWarp.SPAWN.send(player);
			
		}
			if (args[0].equalsIgnoreCase("duels")) {
				WaveWarp.DUELS.send(player);
			
		}
			if (args[0].equalsIgnoreCase("build")) {
				WaveWarp.ARENABUILD.send(player);
			
		}
			if (args[0].equalsIgnoreCase("lava")) {
				WaveWarp.LAVACHALLENGE.send(player);
			
		}
			if (args[0].equalsIgnoreCase("fps")) {
				WaveWarp.FPS.send(player);
			
		}
		
	}
		return false;
}
	
}
