package net.wavemc.pvp.inventory;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import net.wavemc.core.bukkit.item.ItemBuilder;
import net.wavemc.pvp.kit.WaveKit;
import net.wavemc.pvp.kit.KitManager;


public class KitsInventory {

	private final static String inventoryName = "KIT PRIMÁRIO 1/2";
	public static void open(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 6 * 9, inventoryName);
		
	inventory.setItem(11 , new ItemBuilder("§a" + WaveKit.ANCHOR.getName(), WaveKit.ANCHOR.getIcon())
			.lore("§f" + WaveKit.ANCHOR.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", WaveKit.ANCHOR.getName())
					.toStack()
			);
		
	inventory.setItem(10 , new ItemBuilder("§a" + WaveKit.PVP.getName(), WaveKit.PVP.getIcon())
			.lore("§f" + WaveKit.PVP.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
							ItemFlag.HIDE_UNBREAKABLE).addEnchant(Enchantment.SHARPNESS, 1)
					.nbt("kit-gui", WaveKit.PVP.getName())
					.toStack()
			);
	inventory.setItem(12 , new ItemBuilder("§a" + WaveKit.ARCHER.getName(), WaveKit.ARCHER.getIcon())
			.lore("§f" + WaveKit.ARCHER.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", WaveKit.ARCHER.getName())
					.toStack()
			);
	
	inventory.setItem(13 , new ItemBuilder("§a" + WaveKit.BOXER.getName(), WaveKit.BOXER.getIcon())
			.lore("§f" + WaveKit.BOXER.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", WaveKit.BOXER.getName())
					.toStack()
			);
	inventory.setItem(14 , new ItemBuilder("§a" + WaveKit.SNAIL.getName(), WaveKit.SNAIL.getIcon())
			.lore("§f" + WaveKit.SNAIL.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", WaveKit.SNAIL.getName())
					.toStack()
			);
	inventory.setItem(15 , new ItemBuilder("§a" + WaveKit.KANGAROO.getName(), WaveKit.KANGAROO.getIcon())
			.lore("§f" + WaveKit.KANGAROO.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", WaveKit.KANGAROO.getName())
					.toStack()
			);
	inventory.setItem(16 , new ItemBuilder("§a" + WaveKit.FISHERMAN.getName(), WaveKit.FISHERMAN.getIcon())
			.lore("§f" + WaveKit.FISHERMAN.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", WaveKit.FISHERMAN.getName())
					.toStack()
			);
	
	inventory.setItem(19 , new ItemBuilder("§a" + WaveKit.FLASH.getName(), WaveKit.FLASH.getIcon())
			.lore("§f" + WaveKit.FLASH.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", WaveKit.FLASH.getName())
					.toStack()
			);
	inventory.setItem(20 , new ItemBuilder("§a" + WaveKit.GLADIATOR.getName(), WaveKit.GLADIATOR.getIcon())
			.lore("§f" + WaveKit.GLADIATOR.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", WaveKit.GLADIATOR.getName())
					.toStack()
			);
	inventory.setItem(21 , new ItemBuilder("§a" + WaveKit.GRANDPA.getName(), WaveKit.GRANDPA.getIcon())
			.lore("§f" + WaveKit.GRANDPA.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", WaveKit.GRANDPA.getName())
					.toStack()
			);
		inventory.setItem(22 , new ItemBuilder("§a" + WaveKit.MAGMA.getName(), WaveKit.MAGMA.getIcon())
				.lore("§f" + WaveKit.MAGMA.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", WaveKit.MAGMA.getName())
						.toStack()
				);
			inventory.setItem(23 , new ItemBuilder("§a" + WaveKit.MILKMAN.getName(), WaveKit.MILKMAN.getIcon())
					.lore("§f" + WaveKit.MILKMAN.getDescription())
							.addFlags(ItemFlag.HIDE_ATTRIBUTES,
									ItemFlag.HIDE_DESTROYS,
									ItemFlag.HIDE_ENCHANTS,
									ItemFlag.HIDE_PLACED_ON,
									ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
									ItemFlag.HIDE_UNBREAKABLE)
							.nbt("kit-gui", WaveKit.MILKMAN.getName())
							.toStack()
					);
				inventory.setItem(24 , new ItemBuilder("§a" + WaveKit.MONK.getName(), WaveKit.MONK.getIcon())
						.lore("§f" + WaveKit.MONK.getDescription())
								.addFlags(ItemFlag.HIDE_ATTRIBUTES,
										ItemFlag.HIDE_DESTROYS,
										ItemFlag.HIDE_ENCHANTS,
										ItemFlag.HIDE_PLACED_ON,
										ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
										ItemFlag.HIDE_UNBREAKABLE)
								.nbt("kit-gui", WaveKit.MONK.getName())
								.toStack()
						);
				inventory.setItem(25 , new ItemBuilder("§a" + WaveKit.NEO.getName(), WaveKit.NEO.getIcon())
						.lore("§f" + WaveKit.NEO.getDescription())
								.addFlags(ItemFlag.HIDE_ATTRIBUTES,
										ItemFlag.HIDE_DESTROYS,
										ItemFlag.HIDE_ENCHANTS,
										ItemFlag.HIDE_PLACED_ON,
										ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
										ItemFlag.HIDE_UNBREAKABLE)
								.nbt("kit-gui", WaveKit.NEO.getName())
								.toStack()
						);

				inventory.setItem(28 , new ItemBuilder("§a" + WaveKit.NINJA.getName(), WaveKit.NINJA.getIcon())
						.lore("§f" + WaveKit.NINJA.getDescription())
								.addFlags(ItemFlag.HIDE_ATTRIBUTES,
										ItemFlag.HIDE_DESTROYS,
										ItemFlag.HIDE_ENCHANTS,
										ItemFlag.HIDE_PLACED_ON,
										ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
										ItemFlag.HIDE_UNBREAKABLE)
								.nbt("kit-gui", WaveKit.NINJA.getName())
								.toStack()
						);
				inventory.setItem(29 , new ItemBuilder("§a" + WaveKit.PHANTOM.getName(), WaveKit.PHANTOM.getIcon())
						.lore("§f" + WaveKit.PHANTOM.getDescription())
								.addFlags(ItemFlag.HIDE_ATTRIBUTES,
										ItemFlag.HIDE_DESTROYS,
										ItemFlag.HIDE_ENCHANTS,
										ItemFlag.HIDE_PLACED_ON,
										ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
										ItemFlag.HIDE_UNBREAKABLE)
								.nbt("kit-gui", WaveKit.PHANTOM.getName())
								.toStack()
						);
				inventory.setItem(30 , new ItemBuilder("§a" + WaveKit.POSEIDON.getName(), WaveKit.POSEIDON.getIcon())
						.lore("§f" + WaveKit.POSEIDON.getDescription())
								.addFlags(ItemFlag.HIDE_ATTRIBUTES,
										ItemFlag.HIDE_DESTROYS,
										ItemFlag.HIDE_ENCHANTS,
										ItemFlag.HIDE_PLACED_ON,
										ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
										ItemFlag.HIDE_UNBREAKABLE)
								.nbt("kit-gui", WaveKit.POSEIDON.getName())
								.toStack()
						);
				inventory.setItem(31 , new ItemBuilder("§a" + WaveKit.SIGHT.getName(), WaveKit.SIGHT.getIcon())
						.lore("§f" + WaveKit.SIGHT.getDescription())
								.addFlags(ItemFlag.HIDE_ATTRIBUTES,
										ItemFlag.HIDE_DESTROYS,
										ItemFlag.HIDE_ENCHANTS,
										ItemFlag.HIDE_PLACED_ON,
										ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
										ItemFlag.HIDE_UNBREAKABLE)
								.nbt("kit-gui", WaveKit.SIGHT.getName())
								.toStack()
						);
		
				inventory.setItem(32 , new ItemBuilder("§a" + WaveKit.TURTLE.getName(), WaveKit.TURTLE.getIcon())
						.lore("§f" + WaveKit.TURTLE.getDescription())
								.addFlags(ItemFlag.HIDE_ATTRIBUTES,
										ItemFlag.HIDE_DESTROYS,
										ItemFlag.HIDE_ENCHANTS,
										ItemFlag.HIDE_PLACED_ON,
										ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
										ItemFlag.HIDE_UNBREAKABLE)
								.nbt("kit-gui", WaveKit.TURTLE.getName())
								.toStack()
						);
				inventory.setItem(33 , new ItemBuilder("§a" + WaveKit.AUTOBOWL.getName(), WaveKit.AUTOBOWL.getIcon())
						.lore("§f" + WaveKit.AUTOBOWL.getDescription())
								.addFlags(ItemFlag.HIDE_ATTRIBUTES,
										ItemFlag.HIDE_DESTROYS,
										ItemFlag.HIDE_ENCHANTS,
										ItemFlag.HIDE_PLACED_ON,
										ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
										ItemFlag.HIDE_UNBREAKABLE)
								.nbt("kit-gui", WaveKit.AUTOBOWL.getName())
								.toStack()
						);

				inventory.setItem(34 , new ItemBuilder("§a" + WaveKit.METEOR.getName(), WaveKit.METEOR.getIcon())
						.lore("§f" + WaveKit.METEOR.getDescription())
								.addFlags(ItemFlag.HIDE_ATTRIBUTES,
										ItemFlag.HIDE_DESTROYS,
										ItemFlag.HIDE_ENCHANTS,
										ItemFlag.HIDE_PLACED_ON,
										ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
										ItemFlag.HIDE_UNBREAKABLE)
								.nbt("kit-gui", WaveKit.METEOR.getName())
								.toStack()
						);
	
		
		inventory.setItem(53, new ItemBuilder("§aProxímo", Material.ARROW).nbt("prox")
				.toStack()
		);
		
		ItemStack i =  new ItemStack(KitManager.getPlayer(player.getName()).getKit().getIcon());
		ItemMeta i2 = i.getItemMeta();
	    ArrayList<String> lore = new ArrayList<String>();
	    i2.setDisplayName(KitManager.getPlayer(player.getName()).getKit().getName());
	    lore.add("§e" + KitManager.getPlayer(player.getName()).getKit().getDescription());
		i2.setLore(lore);
		i.setItemMeta(i2);
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
		inventory.setItem(49, new ItemBuilder(i).nbt("visual")
				.toStack()
		);
		player.openInventory(inventory);
	}

				
					
		
					
	
		
				
	
	public static String getInventoryName() {
		return inventoryName;
	}



	
}
