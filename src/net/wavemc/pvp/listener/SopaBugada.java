package net.wavemc.pvp.listener;

import java.util.stream.Stream;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.permissions.Permission;

import net.wavemc.pvp.WavePvP;

public class SopaBugada implements Listener {

	   public final Permission BYPASS_PERM = new Permission("*"); // If the user has this permission then the blocker will be canceled

	   @EventHandler
	   public void OnChat(AsyncPlayerChatEvent event) {
	      Player player = event.getPlayer();
	      if (!player.hasPermission(BYPASS_PERM)) {
	         String msg = event.getMessage().toLowerCase().replaceAll("[-_*. ]", "");

                 if (msg.contains("sopa bugada")) {
	        	  event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.RED + "Já sabemos desse bug!");
	        	 }
	      }
	         }
		   @EventHandler
		   public void OnChaGt(AsyncPlayerChatEvent event) {
		      Player player = event.getPlayer();
		      if (!player.hasPermission(BYPASS_PERM)) {
		         String msg = event.getMessage().toLowerCase().replaceAll("[-_*. ]", "");

	                 if (msg.contains("sopa bug")) {
		        	  event.setCancelled(true);
	                event.getPlayer().sendMessage(ChatColor.RED + "Já sabemos desse bug!");
		        	 }

		         }
	      
	   }
}
	
