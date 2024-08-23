package net.wavemc.pvp.warp;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public abstract class WarpHandle implements Listener {

	public void execute(Player player) {
		player.setGameMode(GameMode.SURVIVAL);
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
		player.setAllowFlight(false);
		player.setFlying(false);
		player.getActivePotionEffects().forEach(potion -> player.removePotionEffect(potion.getType()));
	}
}
