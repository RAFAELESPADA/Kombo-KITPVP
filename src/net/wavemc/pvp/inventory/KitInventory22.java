package net.wavemc.pvp.inventory;



import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.wavemc.core.bukkit.item.ItemBuilder;

import net.wavemc.pvp.kit.WaveKit2;

import net.wavemc.pvp.kit.KitManager2;

public class KitInventory22 {

	private final static String inventoryName = "KIT SECUNDÁRIO 2/2";
	public static void open(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 6 * 9, inventoryName);
	
		ItemStack visualItem = new ItemStack(randomGlass());
		
	
		if (player.hasPermission("wave.kit2.reaper")) {
			inventory.setItem(10 , new ItemBuilder("§a" + WaveKit2.REAPER.getName(), WaveKit2.REAPER.getIcon())
					.lore("§f" + WaveKit2.REAPER.getDescription())
							.addFlags(ItemFlag.HIDE_ATTRIBUTES,
									ItemFlag.HIDE_DESTROYS,
									ItemFlag.HIDE_ENCHANTS,
									ItemFlag.HIDE_PLACED_ON,
									ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
									ItemFlag.HIDE_UNBREAKABLE)
							.nbt("kit-gui2", WaveKit2.REAPER.getName())
							.toStack()
					);
			} else {
		    	inventory.setItem(10 , new ItemBuilder("§c" + WaveKit2.REAPER.getName(), new ItemStack(Material.RED_STAINED_GLASS_PANE))
		    			.lore("§f" + WaveKit2.VIPER.getDescription())
		    					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
		    							ItemFlag.HIDE_DESTROYS,
		    							ItemFlag.HIDE_ENCHANTS,
		    							ItemFlag.HIDE_PLACED_ON,
		    							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
		    							ItemFlag.HIDE_UNBREAKABLE)
		    					.nbt("kit-visual", WaveKit2.REAPER.getName())
		    					.toStack()
		    			);
		    }
		if (player.hasPermission("wave.kit2.boxer")) {
			inventory.setItem(11 , new ItemBuilder("§a" + WaveKit2.BOXER.getName(), WaveKit2.BOXER.getIcon())
					.lore("§f" + WaveKit2.BOXER.getDescription())
							.addFlags(ItemFlag.HIDE_ATTRIBUTES,
									ItemFlag.HIDE_DESTROYS,
									ItemFlag.HIDE_ENCHANTS,
									ItemFlag.HIDE_PLACED_ON,
									ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
									ItemFlag.HIDE_UNBREAKABLE)
							.nbt("kit-gui2", WaveKit2.BOXER.getName())
							.toStack()
					);
			} else {
		    	inventory.setItem(11 , new ItemBuilder("§c" + WaveKit2.BOXER.getName(), new ItemStack(Material.RED_STAINED_GLASS_PANE))
		    			.lore("§f" + WaveKit2.BOXER.getDescription())
		    					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
		    							ItemFlag.HIDE_DESTROYS,
		    							ItemFlag.HIDE_ENCHANTS,
		    							ItemFlag.HIDE_PLACED_ON,
		    							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
		    							ItemFlag.HIDE_UNBREAKABLE)
		    					.nbt("kit-visual", WaveKit2.BOXER.getName())
		    					.toStack()
		    			);
		    }
		
			
		inventory.setItem(45, new ItemBuilder("§aRetornar", Material.ARROW).nbt("voltar")
				.toStack()
		);
		
		ItemStack i =  new ItemStack(KitManager2.getPlayer(player.getName()).getkit2().getIcon());
		ItemMeta i2 = i.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
	    lore.add("§f" + KitManager2.getPlayer(player.getName()).getkit2().getDescription());
	    if (KitManager2.getPlayer(player.getName()).getkit2() != WaveKit2.PVP) {
		i2.setDisplayName(KitManager2.getPlayer(player.getName()).getkit2().getName());
	    }
		else {
			i2.setDisplayName("§eSem kits selecionados.");	
			i =  new ItemStack(Material.ITEM_FRAME);
		}
		i2.setLore(lore);
		i.setItemMeta(i2);
		inventory.setItem(49, new ItemBuilder(i).nbt("visual")
				.toStack()
		);
		player.openInventory(inventory);
			}
		

					
				
	
	public static String getInventoryName() {
		return inventoryName;
	}
	private static ItemStack randomGlass() {
		int randomId = new Random().nextInt(14);
		return new ItemStack(new ItemStack(Material.AIR));
	}
	
}

