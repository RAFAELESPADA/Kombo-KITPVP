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
import net.wavemc.pvp.kit.WaveKit;
import net.wavemc.pvp.kit.WaveKit2;
import net.wavemc.pvp.kit.KitManager;

public class KitsInventoryPageTwo {

	private final static String inventoryName = "KIT PRIMÁRIO 2/2";
	public static void open(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 6 * 9, inventoryName);
	
	
		
		inventory.setItem(10 , new ItemBuilder("§a" + WaveKit.ANTISTOMPER.getName(), WaveKit.ANTISTOMPER.getIcon())
				.lore("§f" + WaveKit.ANTISTOMPER.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", WaveKit.ANTISTOMPER.getName())
						.toStack()
				);
		inventory.setItem(11 , new ItemBuilder("§a" + WaveKit.STOMPER.getName(), WaveKit.STOMPER.getIcon())
				.lore("§f" + WaveKit2.STOMPER.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", WaveKit2.STOMPER.getName())
						.toStack()
				);
		inventory.setItem(12 , new ItemBuilder("§a" + WaveKit.VIPER.getName(), WaveKit.VIPER.getIcon())
				.lore("§f" + WaveKit.VIPER.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", WaveKit.VIPER.getName())
						.toStack()
				);
		inventory.setItem(13 , new ItemBuilder("§a" + WaveKit.THOR.getName(), WaveKit.THOR.getIcon())
				.lore("§f" + WaveKit.THOR.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", WaveKit.THOR.getName())
						.toStack()
				);
		inventory.setItem(14 , new ItemBuilder("§a" + WaveKit.HULK.getName(), WaveKit.HULK.getIcon())
				.lore("§f" + WaveKit.HULK.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", WaveKit.HULK.getName())
						.toStack()
				);
		inventory.setItem(15 , new ItemBuilder("§a" + WaveKit.REAPER.getName(), WaveKit.REAPER.getIcon())
				.lore("§f" + WaveKit.REAPER.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", WaveKit.REAPER.getName())
						.toStack()
				);
		inventory.setItem(16 , new ItemBuilder("§a" + WaveKit.SWITCHER.getName(), WaveKit.SWITCHER.getIcon())
				.lore("§f" + WaveKit.SWITCHER.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", WaveKit.SWITCHER.getName())
						.toStack()
				);

		inventory.setItem(19 , new ItemBuilder("§a" + WaveKit.PYRO.getName(), WaveKit.PYRO.getIcon())
				.lore("§f" + WaveKit.PYRO.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", WaveKit.PYRO.getName())
						.toStack()
				);
	
	 
				inventory.setItem(45, new ItemBuilder("§aRetornar", Material.ARROW).nbt("voltar")
						.toStack()
				);
				ItemStack i =  new ItemStack(KitManager.getPlayer(player.getName()).getKit().getIcon());
				ItemMeta i2 = i.getItemMeta();
			    ArrayList<String> lore = new ArrayList<String>();
			    lore.add("§f" + KitManager.getPlayer(player.getName()).getKit().getDescription());
			    if (KitManager.getPlayer(player.getName()).getKit() != WaveKit.NENHUM2) {
					i2.setDisplayName(KitManager.getPlayer(player.getName()).getKit().getName());
				    }
					else {
						i =  new ItemStack(Material.ITEM_FRAME);
						ItemMeta i3 = i.getItemMeta();
						i3.setDisplayName("§eSem kits selecionados.");	
						i3.setLore(lore);
						i.setItemMeta(i3);	
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
	
	
}

