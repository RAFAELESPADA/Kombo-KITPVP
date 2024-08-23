package net.wavemc.pvp.kit.provider;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import net.wavemc.pvp.kit.Habilidade;
import net.wavemc.pvp.kit.KitHandler;
import net.wavemc.pvp.kit.KitManager;

public class AntiStomper extends KitHandler {

@Override
public void execute(Player player) {
	super.execute(player);
	
	Habilidade.setAbility(player, "SteelHead");
}

@EventHandler
public void onDamage(EntityDamageEvent event) {
	if (!(event.getEntity() instanceof Player)) {
		return;
	}
	Player player = (Player) event.getEntity();
	
	if (KitManager.getPlayer(player.getName()).hasKit(this) 
			&& (event.getCause().equals(DamageCause.PROJECTILE))) {
		event.setCancelled(true);
	}
}
}