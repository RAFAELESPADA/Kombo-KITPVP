package net.wavemc.pvp.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import net.wavemc.pvp.command.NoBreakEvent;
import net.wavemc.pvp.kit.Habilidade;
import net.wavemc.pvp.kit.KitManager;
import net.wavemc.pvp.kit.provider.GladiatorListener2;
import net.wavemc.pvp.warp.WaveWarp;
import net.wavemc.warp.provider.Duels;

public class PlayerQuitListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		event.setQuitMessage(null);

		WaveWarp.removeHandle(player.getName());
	  NoBreakEvent.embuild.remove(player);
	KitManager.remove(player.getName());
	Habilidade.removeAbility(player);
	
		 Scoreboard s = player.getScoreboard();
		  if (player.getScoreboard().getObjective("pvp") == null && player.getScoreboard().getObjective("pvp2") == null && player.getScoreboard().getObjective("pvpg") == null && player.getScoreboard().getObjective("pvppt") == null && player.getScoreboard().getObjective("pvp3") == null && player.getScoreboard().getObjective("pvp4") == null && player.getScoreboard().getObjective("pvp5") == null && player.getScoreboard().getObjective("pvp6") == null  && player.getScoreboard().getObjective("pvp7") == null) {
				return;
			}
		  if (s.getTeam("nhide") == null) {
	      Team t = s.registerNewTeam("nhide");
Player p = player;

	      		for (Location l : ArenaBuild.blocks) {
	      			l.getBlock().setType(Material.AIR);
	      		
	      		return;
	      	}
	      		if (ArenaBuild.placed_blocks.get(p) == null) {
	      			return;
	      		}
	      for (Block b : ArenaBuild.placed_blocks.get(p)) {
	      	if (p == null) {
	      		return;
	      	}
	      	  b.setType(Material.AIR);
	      }
	      for (Location l : ArenaBuild.blocks) {
	      	l.getBlock().setType(Material.AIR);
	      }
	      t.setNameTagVisibility(NameTagVisibility.NEVER);
	      if (t.hasEntry(player.getName())) {
	      t.removeEntry(player.getName());
	      }
		  }
		  if (Duels.protector.containsKey(player.getName())) {
			    Duels.protector.remove(player.getName());
			    }

		 if (GladiatorListener2.combateGlad.containsKey(player)) {
             final Player winner = GladiatorListener2.combateGlad.get(player);
             final Player loser = player;
             GladiatorListener2.resetGladiatorListenerByQuit(winner, loser);
             GladiatorListener2.combateGlad.remove(winner);
             GladiatorListener2.combateGlad.remove(loser);
		 }
		 else if (net.wavemc.kit2.GladiatorListener.combateGlad.containsKey(player)) {
             final Player winner = GladiatorListener2.combateGlad.get(player);
             for (Player p : Bukkit.getOnlinePlayers()) {
            	 if (net.wavemc.kit2.GladiatorListener.combateGlad.containsKey(p)) {
            		 p.damage(p.getHealth()); 
            	 }
            	 
             }
         }
	}

}
