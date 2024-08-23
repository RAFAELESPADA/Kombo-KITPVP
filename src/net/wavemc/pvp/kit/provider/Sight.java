package net.wavemc.pvp.kit.provider;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.wavemc.pvp.kit.KitHandler;
import net.wavemc.pvp.kit.KitManager;

public class Sight extends KitHandler {
	
	@EventHandler(ignoreCancelled = true)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player) 
				|| (!(event.getDamager() instanceof Player))) {
			return;
		}
		
		Player damager = (Player) event.getDamager();
		if (!KitManager.getPlayer(damager.getName()).hasKit(this)) {
			return;
		}
		if (event.isCancelled()) {
			return;
		}
		Player victim = (Player) event.getEntity();
		int percentage = new Random().nextInt(100);
		
		if (percentage < 27) {
			victim.sendMessage(ChatColor.RED + "Uma pessoa de kit sight te deu cegueira");
			victim.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 4 * 20, 1));
		}
	}
}
