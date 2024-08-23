package net.wavemc.pvp.kit.provider;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.kit.KitHandler;
import net.wavemc.pvp.kit.KitManager;

public class Anchor extends KitHandler {
	
	 private ArrayList<UUID> anchoring;
	    
	    public Anchor() {
	        this.anchoring = new ArrayList<UUID>();
	    }
	    
	    @EventHandler
	    public void onVelocity(final PlayerVelocityEvent event) {
	        if (this.anchoring.contains(event.getPlayer().getUniqueId())) {
	            event.setVelocity(new Vector());
	            this.anchoring.remove(event.getPlayer().getUniqueId());
	        }
	    }
	    
	    @EventHandler(priority = EventPriority.MONITOR)
	    public void onAnchor(final EntityDamageByEntityEvent event) {
	        if (event.isCancelled()) {
	            return;
	        }
	        if (!(event.getEntity() instanceof Player) || !(event.getDamager() instanceof Player)) {
	            return;
	        }
	        final Player player = (Player)event.getEntity();
	        final Player damager = (Player)event.getDamager();
	        if (KitManager.getPlayer(player.getName()).hasKit(this) || KitManager.getPlayer(damager.getName()).hasKit(this)) {
	            player.setVelocity(new Vector());
	            this.anchoring.add(player.getUniqueId());
	            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
	            damager.playSound(damager.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
	            for (Entity p : damager.getNearbyEntities(12, 20, 20)) {
	            	if (p instanceof Player) {
	            		((Player) p).playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 10, 10);
	            	}
	            }
	            Bukkit.getScheduler().scheduleSyncDelayedTask(WavePvP.getInstance(), () -> player.setVelocity(new Vector()));
	        }
	}
}
