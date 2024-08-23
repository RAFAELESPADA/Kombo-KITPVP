package net.wavemc.warp.provider;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.item.ItemBuilder;
import net.wavemc.pvp.kit.KitManager;
import net.wavemc.pvp.kit.KitManager2;
import net.wavemc.pvp.listener.Jump;
import net.wavemc.pvp.warp.WarpHandle;
import net.wavemc.pvp.warp.WaveWarp;

public class ArenaBuild extends WarpHandle {
	
	@Override
	public void execute(Player player) {

		KitManager.getPlayer(player.getName()).removeKit();
		KitManager2.getPlayer(player.getName()).removekit2();
		player.getInventory().setHeldItemSlot(0);
		Gladiator gladiator = new Gladiator();
		gladiator.setGladInventory(player);
	}

}