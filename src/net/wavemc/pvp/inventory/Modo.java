package net.wavemc.pvp.inventory;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import net.wavemc.core.bukkit.item.ItemBuilder;


public class Modo implements Listener {

	private static String inventoryName = "Desafiar";
	
	public static void open(Player player, Player target) {
		
		inventoryName = "Desafiar - " + target.getName();
		Inventory inventory = Bukkit.createInventory(null, 9, inventoryName);
		

		inventory.setItem(4, new ItemBuilder("§cSumo", Material.LEAD).nbt("sumoi")
				.toStack()
		);
		inventory.setItem(5, new ItemBuilder("§c1V1", Material.MUSHROOM_STEW).nbt("1v1i")
				.toStack()
		);
		inventory.setItem(3, new ItemBuilder("§cGladiator", Material.IRON_BARS).nbt("gladi")
				.toStack()
		);
		
		player.openInventory(inventory);
	}
	public static String getInventoryName() {
		return inventoryName;
	}
}