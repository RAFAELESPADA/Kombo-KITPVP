package net.wavemc.kit2;



import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import net.wavemc.pvp.kit.Habilidade;
import net.wavemc.pvp.kit.KitHandler2;
import net.wavemc.pvp.kit.KitManager2;

public class NEO extends KitHandler2 {

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
	
	if (KitManager2.getPlayer(player.getName()).haskit2(this) 
			&& (event.getCause().equals(DamageCause.PROJECTILE))) {
		event.setCancelled(true);
	}
}
}