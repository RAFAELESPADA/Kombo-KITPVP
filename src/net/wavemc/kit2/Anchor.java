package net.wavemc.kit2;


import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.util.Vector;

import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.kit.KitHandler2;
import net.wavemc.pvp.kit.KitManager;
import net.wavemc.pvp.kit.KitManager2;

public class Anchor extends KitHandler2 {
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
        if (KitManager2.getPlayer(player.getName()).haskit2(this) || KitManager2.getPlayer(damager.getName()).haskit2(this)) {
            player.setVelocity(new Vector());
            this.anchoring.add(player.getUniqueId());
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1f, 1f);
            Bukkit.getScheduler().scheduleSyncDelayedTask(WavePvP.getInstance(), () -> player.setVelocity(new Vector()));
        }
	}
}
