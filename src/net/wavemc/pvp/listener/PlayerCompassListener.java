package net.wavemc.pvp.listener;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.item.ItemBuilder;
import net.wavemc.pvp.kit.KitManager;
import net.wavemc.pvp.kit.provider.EnderMageReal;


public class PlayerCompassListener implements Listener {

	Path path1 = Paths.get(Bukkit.getServer().getWorldContainer().getAbsolutePath() + "/plugins/WaveCore/", "warps.yml");
	File file = new File(path1.toAbsolutePath().toString());
	YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
	  
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Location l2 = new Location(Bukkit.getWorld(yaml.getString("Mundo-spawn")), yaml.getInt("X-spawn") , yaml.getInt("Y-spawn"), yaml.getInt("Z-spawn"));
		if (event.getItem() == null) {
			return;
		}
		if (event.getItem().getType().equals(Material.MUSHROOM_STEW)) {
			return;
		}
		if (!event.hasItem() || !event.getItem().getType().equals(Material.COMPASS) 
				|| !ItemBuilder.has(event.getItem(), "kit-handler", "search-players")) {
			return;
		}
		event.setCancelled(true);
		
		List<Entity> entities = player.getNearbyEntities(250.0, 250.0, 250.0).stream().filter(
				entity -> entity instanceof Player 
				&& player.getLocation().distance(entity.getLocation()) >= 5 
				&& player.canSee((Player)entity) && !EnderMageReal.isSpawn(entity.getLocation())
		).collect(Collectors.toList());
		
		if (entities.size() == 0) {
			player.sendMessage("§eSem jogadores próximos.");
			{
				player.setCompassTarget(l2);
				player.sendMessage("§eBússola apontado para o spawn");
			};
			return;
		}
		
		Entity entity = entities.get(0);
		player.setCompassTarget(entity.getLocation());
		event.setCancelled(true);
		player.sendMessage("§eSua bússola está apontando para §7" + entity.getName());
	}

}
