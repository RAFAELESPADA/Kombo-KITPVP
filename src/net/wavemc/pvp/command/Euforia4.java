package net.wavemc.pvp.command;





import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.wavemc.pvp.WavePvP;

public class Euforia4 implements CommandExecutor {

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player player2 = (Player)sender;
            if (!player2.hasPermission("kombo.cmd.report")) {
                player2.sendMessage(ChatColor.RED + "Sem permissão");
                return true;
                }
            if (!WavePvP.euforia) {
            	 for (Player player : Bukkit.getOnlinePlayers()) {
				player.sendTitle("§c§lFÚRIA", "§fTodos ficam fortes");
				player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 120*20, 0));
				player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1F, 10F);
            	 }
				WavePvP.euforia = true;
				
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp group default permission settemp wave.kit2.* true 30m");

				Bukkit.getWorlds().forEach(world -> world.setTime(18000));
				Bukkit.broadcast("§4§lFÚRIA §7Ativado manualmente por " + player2.getName(), "kombo.cmd.report");
				Bukkit.broadcastMessage("§cO evento fúria foi iniciado");
				Bukkit.broadcastMessage("§cPor dois minutos todos serão fortes");
				Bukkit.broadcastMessage("§cE o server estará de noite");
				 }
         else {
        	
					Bukkit.broadcastMessage("§aO evento Fúria foi finalizado!");
					WavePvP.euforia = false;
					Bukkit.getWorlds().forEach(world -> world.setTime(100));
					
					    Bukkit.broadcast("§4§lFÚRIA §7Desativado manualmente por " + player2.getName(), "kombo.cmd.report");
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp group default permission unsettemp kombo.kit.*");
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp group default permission unsettemp kombo.kit2.*");
					 for (Player p1 : Bukkit.getOnlinePlayers()) {
						
							
					      	p1.playSound(p1.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
					        p1.getActivePotionEffects().forEach(potion -> p1.removePotionEffect(potion.getType()));
					      }
					 Bukkit.broadcastMessage("§aO evento fúria acabou!");
						
		return false;
    }
		return false;
}
		return false;}}
