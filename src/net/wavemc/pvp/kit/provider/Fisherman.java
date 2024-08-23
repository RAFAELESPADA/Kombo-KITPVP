package net.wavemc.pvp.kit.provider;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerFishEvent;
import net.wavemc.core.bukkit.item.ItemBuilder;
import net.wavemc.pvp.kit.Habilidade;
import net.wavemc.pvp.kit.WaveKit;
import net.wavemc.pvp.kit.WaveKit2;
import net.wavemc.pvp.kit.KitHandler;
import net.wavemc.pvp.kit.KitManager;
import net.wavemc.pvp.kit.KitManager2;

public class Fisherman extends KitHandler {
	
	@Override
	public void execute(Player player) {
		super.execute(player);
		
	}
	
	@EventHandler
	public void onFish(PlayerFishEvent event) {
		if (!(event.getCaught() instanceof Player) 
				|| !KitManager.getPlayer(event.getPlayer().getName()).hasKit(this) 
				|| !ItemBuilder.has(event.getPlayer().getItemInHand(), "kit-handler", "fisherman")) {
			return;
		}
		 if (hasCooldown(event.getPlayer())) {
		        sendMessageCooldown(event.getPlayer());
		        event.setCancelled(true);
		        return;
		      }
		Entity caught = event.getCaught();
		 if (KitManager.getPlayer(caught.getName()).hasKit(WaveKit.NEO) || KitManager2.getPlayer(caught.getName()).haskit2(WaveKit2.NEO)) {
			 event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 15.0f, 15.0f);
			 event.getPlayer().sendMessage(ChatColor.AQUA + "Você não pode usar isso em " + caught.getName() + " porque ele está de kit NEO");
				return;
			}
		caught.teleport(event.getPlayer());
		caught.sendMessage("§c§lFISHERMAN: §fVocê foi fisgado por " + event.getPlayer().getName());
		event.getPlayer().sendMessage("§c§lFISHERMAN: §fVocê fisgou " + caught.getName());
		addCooldown(event.getPlayer() , 6);
	}
}
