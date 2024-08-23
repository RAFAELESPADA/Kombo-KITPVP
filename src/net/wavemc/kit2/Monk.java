package net.wavemc.kit2;


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
import net.wavemc.pvp.kit.KitHandler2;
import net.wavemc.pvp.kit.KitManager;
import net.wavemc.pvp.kit.KitManager2;
import net.wavemc.pvp.kit.provider.EnderMageReal;

public class Monk extends KitHandler2 {
	
	@Override
	public void execute(Player player) {
		super.execute(player);
		
		player.getInventory().setItem(2, new ItemBuilder("§eEmbaralhar!", Material.BLAZE_ROD)
				.nbt("kit-handler", "monk")
				.nbt("cancel-drop")
				.toStack()
		);
	}
	
	@EventHandler
	public void onEntityDamageByEntity(PlayerInteractAtEntityEvent event) {
		if (!(event.getRightClicked() instanceof Player)) {
			return;
		}
		final Player jogadorClicado = (Player)event.getRightClicked();
		Player player = event.getPlayer();
		if (inCooldown(player) && KitManager2.getPlayer(player.getName()).haskit2(this)) {
			sendMessageCooldown(player);
			return;
		}
		else if (event.getPlayer().getLocation().getY() > WavePvP.getInstance().getConfig().getInt("SpawnAltura") && KitManager2.getPlayer(event.getPlayer().getName()).haskit2(this)  && EnderMageReal.isSpawn(player.getLocation())) {
        	event.getPlayer().sendMessage("§cNão use o monk no spawn!");
        	event.setCancelled(true);
			return;
		}
		if (!KitManager2.getPlayer(player.getName()).haskit2(this) 
				|| !ItemBuilder.has(player.getItemInHand(), "kit-handler", "monk")) {
			return;
		}
	       final int random = new Random().nextInt(jogadorClicado.getInventory().getSize() - 10 + 1 + 10);
           final ItemStack ItemSelecionado = jogadorClicado.getInventory().getItem(random);
           final ItemStack ItemMudado = jogadorClicado.getItemInHand();
           jogadorClicado.setItemInHand(ItemSelecionado);
           jogadorClicado.getInventory().setItem(random, ItemMudado);
           jogadorClicado.sendMessage("§6Você teve o seu inventário embaralharado por §f" + player.getName());
   		player.sendMessage("§6Você embaralhou o inventário de §f" + jogadorClicado.getName());
           addCooldown(player, 10);
           Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)WavePvP.getInstance(), (Runnable)new Runnable() {
               @Override
               public void run() {
                   player.sendMessage("§aO Cooldown acabou.");
               }
           }, 20L * 10);
       }

	
	}


