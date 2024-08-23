package net.wavemc.kit2;



import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import net.wavemc.pvp.kit.WaveKit;
import net.wavemc.pvp.kit.WaveKit2;
import net.wavemc.pvp.kit.KitHandler;
import net.wavemc.pvp.kit.KitHandler2;
import net.wavemc.pvp.kit.KitManager2;

public class Turtle extends KitHandler2 {

    @EventHandler (ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player) ||
        !(event.getDamager() instanceof Player)) return;

        Player damager = (Player) event.getDamager();
        if (!KitManager2.getPlayer(damager.getName()).haskit2(WaveKit2.TURTLE) ||
            !damager.isSneaking()) return;

        event.setDamage(event.getDamage() - 4.5);

    }
}