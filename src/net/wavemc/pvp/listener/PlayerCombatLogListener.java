package net.wavemc.pvp.listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import javax.net.ssl.HttpsURLConnection;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import net.wavemc.core.util.WaveCooldown;
import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.command.VanishUtil;
import net.wavemc.pvp.warp.WaveWarp;


public class PlayerCombatLogListener implements Listener {

	private final List<String> allowCommands = Arrays.asList("tell", "r", "report", "reportar", "admin", "tag");
	private final static TimeUnit timeUnit = TimeUnit.SECONDS;
	private final static long time = 15;
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player) 
				|| (!(event.getDamager() instanceof Player))) {
			return;
		}
		Player victim = (Player) event.getEntity();
		Player damager = (Player) event.getDamager();
		if (WaveWarp.DUELS.hasPlayer(damager.getName())) {
			return;
		}
		if (victim.getGameMode().equals(GameMode.CREATIVE) || damager.getGameMode().equals(GameMode.CREATIVE)) {
			return;
		}
		if (!WaveCooldown.has(victim.getName(), "combat")) {
		damager.sendMessage(ChatColor.RED + "Você agora está em combate com " + victim.getName());
		victim.sendMessage(ChatColor.RED + "Você agora está em combate com " + damager.getName());
		}

		WaveCooldown.create(victim.getName(), "combat", timeUnit, time);
		WaveCooldown.create(damager.getName(), "combat", timeUnit, time);
		if (victim.isFlying()) {
		victim.setAllowFlight(victim.hasPermission("wave.ignorarcombatelog"));
		victim.setFlying(victim.hasPermission("wave.ignorarcombatelog"));
	}
	}
	
	@EventHandler
	public void onCommandPreProcess(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		String command = event.getMessage().split(" ")[0].toLowerCase();
		if (WaveCooldown.inCooldown(player.getName(), "combat") && !allowCommands.contains(command) && !player.hasPermission("wave.ignorarcombatelog")) {
			event.setCancelled(true);
			player.sendMessage("§cEspere " + WaveCooldown.getTime(player.getName(), "combat") + " segundos para usar comandos novamente!");
		}
	}
	@EventHandler
    public void aosair(PlayerQuitEvent e) {
        final Player p = e.getPlayer();
        if (WaveCooldown.inCooldown(p.getName(), "combat")) {
            p.setLastDamage(p.getHealth());
            WaveWarp.SPAWN.send(p);
            WaveCooldown.delete(p.getName(), "combat");
        }
	}
	
		

				
			
			
		
			
			    
			
			    
			
			        
			 
			 

		
		

	
	@EventHandler
	public void onCommandPreProcess2(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		String command = event.getMessage().split(" ")[0].toLowerCase();
		if (command.contains("admin") && VanishUtil.has(player.getName())) {
			event.setCancelled(true);
			player.sendMessage("§csaia do vanish antes de entrar no modo admin!");
		}
	}
	
	
	
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		if  (WaveCooldown.inCooldown(player.getName(), "combat")) {
			WaveCooldown.delete(player.getName(), "combat");
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		if  (WaveCooldown.inCooldown(player.getName(), "combat")) {
			WaveCooldown.delete(player.getName(), "combat");
			player.setHealth(0);
			WaveWarp.SPAWN.send(player);
			Bukkit.broadcastMessage(ChatColor.RED + player.getName() + " deslogou em combate e levou kill.");
		}
	
	}


	
			
		
}
