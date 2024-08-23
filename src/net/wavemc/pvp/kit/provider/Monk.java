package net.wavemc.pvp.kit.provider;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import net.wavemc.core.bukkit.item.ItemBuilder;
import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.kit.KitHandler;
import net.wavemc.pvp.kit.KitManager;

public class Monk extends KitHandler {
	
	@Override
	public void execute(Player player) {
		super.execute(player);
		
	}
	
	@EventHandler
	public void onEntityDamageByEntity(PlayerInteractAtEntityEvent event) {
		if (!(event.getRightClicked() instanceof Player)) {
			return;
		}
		final Player jogadorClicado = (Player)event.getRightClicked();
		Player player = event.getPlayer();
		if (inCooldown(player) && KitManager.getPlayer(player.getName()).hasKit(this)) {
			sendMessageCooldown(player);
			return;
		}
		else if (event.getPlayer().getLocation().getY() > WavePvP.getInstance().getConfig().getInt("SpawnAltura") && KitManager.getPlayer(event.getPlayer().getName()).hasKit(this)  && EnderMageReal.isSpawn(player.getLocation())) {
        	event.getPlayer().sendMessage("§cNão use o monk no spawn!");
        	event.setCancelled(true);
			return;
		
}
		if (!KitManager.getPlayer(player.getName()).hasKit(this) 
				|| !ItemBuilder.has(player.getItemInHand(), "kit-handler", "monk")) {
			return;
		}
	       final int random = new Random().nextInt(jogadorClicado.getInventory().getSize() - 10 + 1 + 10);
           final ItemStack ItemSelecionado = jogadorClicado.getInventory().getItem(random);
           final ItemStack ItemMudado = jogadorClicado.getItemInHand();
           jogadorClicado.setItemInHand(ItemSelecionado);
           jogadorClicado.getInventory().setItem(random, ItemMudado);
           jogadorClicado.sendMessage("§6Seu inventário foi embaralhado por §f" + player.getName());
   		player.sendMessage("§6Você bagunçou o inventário de §f" + jogadorClicado.getName());
           addCooldown(player, 10);
           Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)WavePvP.getInstance(), (Runnable)new Runnable() {
               @Override
               public void run() {
                   player.sendMessage("§aCooldown acabou.");
               }
           }, 20L * 10);
       }

	
	}

