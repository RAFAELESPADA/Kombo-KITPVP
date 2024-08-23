package net.wavemc.pvp.command;

import java.util.concurrent.TimeUnit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.wavemc.core.util.WaveCooldown;
import net.wavemc.pvp.warp.WaveWarp;
import net.wavemc.warp.provider.Gladiator;
import net.wavemc.warp.provider.OneVsOne;
import net.wavemc.warp.provider.Sumo;





public class DuelJoin implements CommandExecutor { 

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("dueljoin")){
			 Player player = (Player)sender;
			    
			
			
			if(args.length < 1){
				sender.sendMessage("§cUso correto: /dueljoin <1v1, sumo , glad>");
				return true;
			}
			else if (!WaveWarp.DUELS.hasPlayer(player.getName())) {
				player.sendMessage("§cVocê não está no duelos");
				return true;
			}
			
			if (WaveCooldown.inCooldown(sender.getName(), "duel-cmd"))  {
				sender.sendMessage("§cEspere " + WaveCooldown.getTime(sender.getName(), "duel-cmd") + "s para usar novamente.");
				return true;
			}
			else if (args[0].equalsIgnoreCase("1v1")) {
				if (!OneVsOne.fastChallenge.contains(player) && !Sumo.fastChallenge.contains(player) && !Gladiator.fastChallenge.contains(player) ) {
				OneVsOne.fastChallenge.add(player);

				}
	

				player.sendMessage("§eProcurando partida...");
		}
			else if (args[0].equalsIgnoreCase("sumo")) {
				if (!OneVsOne.fastChallenge.contains(player) && !Sumo.fastChallenge.contains(player) && !Gladiator.fastChallenge.contains(player) ) {
				Sumo.fastChallenge.add(player);

				}


player.sendMessage("§eProcurando partida...");
		}
			else if (args[0].equalsIgnoreCase("gladiator")) {
				if (!OneVsOne.fastChallenge.contains(player) && !Sumo.fastChallenge.contains(player) && !Gladiator.fastChallenge.contains(player) ) {
				Gladiator.fastChallenge.add(player);

				}
	

				player.sendMessage("§eProcurando partida...");
		
		}
				WaveCooldown.create(sender.getName(), "duel-cmd", TimeUnit.SECONDS, 10);
	}		

	
		return false;
}
	
}
