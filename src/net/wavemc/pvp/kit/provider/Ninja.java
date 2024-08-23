package net.wavemc.pvp.kit.provider;

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
import net.wavemc.pvp.kit.KitHandler;
import net.wavemc.pvp.kit.KitManager;
import net.wavemc.pvp.kit.KitManager2;


public class Ninja extends KitHandler {
	
	public static final HashMap<String, String> map = new HashMap<>();
	
	@EventHandler(ignoreCancelled = true)
	public void onDamage(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player) 
				|| (!(event.getDamager() instanceof Player))) {
			return;
		}
		Player victim = (Player) event.getEntity();
		Player damager = (Player) event.getDamager();
		
		if (KitManager.getPlayer(damager.getName()).hasKit(this) 
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
		if (KitManager.getPlayer(player.getName()).hasKit(this) 
				&& map.containsKey(player.getName())) {
			
			
			String targetName = map.get(player.getName());
			Player targetPlayer;
			if (inCooldown(player) && KitManager.getPlayer(player.getName()).hasKit(this)) {
				sendMessageCooldown(player);
				return;
			}
			if ((targetPlayer = Bukkit.getPlayer(targetName)) != null) {
				if (player.getLocation().distance(targetPlayer.getLocation()) >= 50) {
					player.sendMessage("§cJogador está muito longe (50+ blocos).");
					return;
				}
				if (GladiatorListener2.combateGlad.containsKey(targetPlayer) || net.wavemc.kit2.GladiatorListener.combateGlad.containsKey(targetPlayer)) {
					player.sendMessage("§cO Jogador está no Gladiator.");
					return;
				}
				 if (KitManager.getPlayer(targetPlayer.getName()).hasKit(WaveKit.NEO) || KitManager2.getPlayer(targetPlayer.getName()).haskit2(WaveKit2.NEO)) {
					 event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 15.0f, 15.0f);
					 event.getPlayer().sendMessage(ChatColor.AQUA + "Você não pode usar o ninja em " + targetPlayer.getName() + " porque ele está com o kit NEO");
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
		
		if (KitManager.getPlayer(player.getName()).hasKit(this)) {
			map.remove(player.getName());
		}
		if (map.containsValue(player.getName())) {
			map.entrySet().removeIf(entry -> entry.getValue().equalsIgnoreCase(player.getName()));
		}
	}

}
