package net.wavemc.kit2;



import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.kit.WaveKit;
import net.wavemc.pvp.kit.WaveKit2;
import net.wavemc.pvp.kit.KitHandler2;
import net.wavemc.pvp.kit.KitManager;
import net.wavemc.pvp.kit.KitManager2;
import net.wavemc.pvp.kit.provider.GladiatorListener2;


public class Ninja extends KitHandler2 {
	
	public static final HashMap<String, String> map = new HashMap<>();
	
	@EventHandler(ignoreCancelled = true)
	public void onDamage(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player) 
				|| (!(event.getDamager() instanceof Player))) {
			return;
		}
		Player victim = (Player) event.getEntity();
		Player damager = (Player) event.getDamager();
		
		if (KitManager2.getPlayer(damager.getName()).haskit2(this) 
				&& KitManager.getPlayer(victim.getName()).hasKit()) {
			map.put(damager.getName(), victim.getName());
		}
	}
	
	@EventHandler
	public void onSneaking(PlayerToggleSneakEvent event) {
		if (event.isSneaking()) return;
		Player player = event.getPlayer();
		Player mage = event.getPlayer();
		if (mage.getLocation().getY() > WavePvP.getInstance().getConfig().getInt("SpawnAltura")) {
			return;
		 }
		if (KitManager2.getPlayer(player.getName()).haskit2(this) 
				&& map.containsKey(player.getName())) {
			
			
			String targetName = map.get(player.getName());
			Player targetPlayer;
			if (inCooldown(player) && KitManager2.getPlayer(player.getName()).haskit2(this)) {
				sendMessageCooldown(player);
				return;
			}
			if ((targetPlayer = Bukkit.getPlayer(targetName)) != null) {
				if (player.getLocation().distance(targetPlayer.getLocation()) >= 50) {
					player.sendMessage("§cO Jogador está muito longe (50+ blocos).");
					return;
				}
				if (KitManager.getPlayer(targetPlayer.getName()).hasKit(WaveKit.NEO) || KitManager2.getPlayer(targetPlayer.getName()).haskit2(WaveKit2.NEO)) {
					 event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 15.0f, 15.0f);
					 event.getPlayer().sendMessage(ChatColor.AQUA + "Você não pode usar o ninja em " + targetPlayer.getName() + " porque ele está de NEO");
						return;
					}
				if (GladiatorListener2.combateGlad.containsKey(targetPlayer) || net.wavemc.kit2.GladiatorListener.combateGlad.containsKey(targetPlayer)) {
					player.sendMessage("§cO Jogador está no Gladiator.");
					return;
				}
				addCooldown(event.getPlayer(), WavePvP.getInstance().getConfig().getInt("NinjaCooldown"));
				player.teleport(targetPlayer);
				player.sendMessage("§aTeleportado para §f" + targetName);
				player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 10, 10);
			}
		}
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		
		if (KitManager2.getPlayer(player.getName()).haskit2(this)) {
			map.remove(player.getName());
		}
		if (map.containsValue(player.getName())) {
			map.entrySet().removeIf(entry -> entry.getValue().equalsIgnoreCase(player.getName()));
		}
	}

}
