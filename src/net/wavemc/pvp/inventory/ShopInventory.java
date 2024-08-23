package net.wavemc.pvp.inventory;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import net.wavemc.core.bukkit.format.WaveDecimalFormat;
import net.wavemc.core.bukkit.item.ItemBuilder;
import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.kit.KitManager;
import net.wavemc.pvp.kit.KitManager2;
import net.wavemc.pvp.kit.WaveKit2;
import net.wavemc.pvp.warp.WaveWarp;

public class ShopInventory {
	
	private final static String inventoryName = "LOJA DE KITS";
	


	public static void open(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 6 * 9, inventoryName);
		ItemStack visualItem = new ItemBuilder(WavePvP.getInstance().getConfig().getString("ShopItem").replace("&", "§"), Material.WHITE_STAINED_GLASS_PANE).toStack();
		

		List<WaveKit2> availableKits = WaveKit2.getKits().stream().filter(
				kit -> !KitManager2.getPlayer(player.getName()).getAvailablekit2s().contains(kit) && !kit.equals(WaveKit2.PVP)
		).collect(Collectors.toList());
		if (availableKits.size() > 0) {
			for (int i = 0; i <= 8; i++) {
				inventory.setItem(i, visualItem);
			}
			
			for (int i = 45; i <= 53; i++) {
				inventory.setItem(i, visualItem);
			}
			
			for (int i = 0; i <= 45; i += 9) {
				inventory.setItem(i, visualItem);
			}
			
			for (int i = 8; i <= 53; i += 9) {
				inventory.setItem(i, visualItem);
			}
		WaveKit2.getKits().stream().filter(warp -> warp != WaveKit2.PVP && !KitManager2.getPlayer(player.getName()).getAvailablekit2s().contains(warp)).forEach(warp -> {
			inventory.addItem(new ItemBuilder("§e" + warp.getName(), warp.getIcon())
					.lore("§7Compre agora por §e" + WaveDecimalFormat.format(warp.getPrice()) + " Coins")
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("shop-kit", warp.getName())
					.toStack());
			;
		});
		}	else {
			inventory.setItem(31 , new ItemBuilder("§cNada aqui!", new ItemStack(Material.RED_STAINED_GLASS_PANE))
	    			.lore("§7" + "Parece que você já tem todos os Kits")
	    					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
	    							ItemFlag.HIDE_DESTROYS,
	    							ItemFlag.HIDE_ENCHANTS,
	    							ItemFlag.HIDE_PLACED_ON,
	    							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
	    							ItemFlag.HIDE_UNBREAKABLE)
	    					.nbt("kit-visual", WaveKit2.THOR.getName())
	    					.toStack());
		}
		player.openInventory(inventory);
	
	}

		
		


	
	
	public static String getInventoryName() {
		return inventoryName;
	}

}
