package net.wavemc.kit2;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import net.wavemc.pvp.kit.WaveKit;
import net.wavemc.pvp.kit.KitHandler2;
import net.wavemc.pvp.kit.KitManager;
import net.wavemc.pvp.kit.KitManager2;

public class Boxer extends KitHandler2 {
	@Override
	public void execute(Player player) {
		super.execute(player);
		
	}
	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		 Player rightClicked = (Player) event.getEntity();
		 if (!KitManager2.getPlayer(rightClicked.getName()).haskit2(this) || event.getCause() == EntityDamageEvent.DamageCause.VOID)
		      return; 
	     event.setDamage(event.getDamage() - 0.4);
	}
	@EventHandler
	public void onDamage2(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		 if (!KitManager2.getPlayer(event.getDamager().getName()).haskit2(this) || event.getCause() == EntityDamageEvent.DamageCause.VOID)
		      return; 
	     event.setDamage(event.getDamage() + 0.5);
	}
}
