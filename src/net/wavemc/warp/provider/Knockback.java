package net.wavemc.warp.provider;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.wavemc.core.bukkit.item.ItemBuilder;
import net.wavemc.pvp.kit.KitManager;
import net.wavemc.pvp.kit.KitManager2;
import net.wavemc.pvp.warp.WarpHandle;
public class Knockback extends WarpHandle {
	
	@Override
	public void execute(Player player) {
		super.execute(player);
		
		player.getInventory().setItem(0, new ItemBuilder("§bGraveto", Material.STICK).addEnchant(org.bukkit.enchantments.Enchantment.KNOCKBACK, 5)
				.nbt("cancel-drop")
				.toStack()
		);
		KitManager.getPlayer(player.getName()).removeKit();
		KitManager2.getPlayer(player.getName()).removekit2();
		player.getInventory().setBoots(new ItemBuilder("§6§lWARP KNOCKBACK", Material.DIAMOND_BOOTS).addEnchant(org.bukkit.enchantments.Enchantment.FEATHER_FALLING, 4).toStack());
		
		for (int i = 0; i < 36; i++) {
			player.getInventory().addItem(new ItemStack(Material.MUSHROOM_STEW));
		}
	}
}