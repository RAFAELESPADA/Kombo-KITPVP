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
import net.wavemc.pvp.kit.WaveKit2;
import net.wavemc.pvp.kit.KitManager;
import net.wavemc.pvp.kit.KitManager2;


public class KitsInventory2 {

	private final static String inventoryName = "KIT SECUNDÁRIO";
	public static void open(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 6 * 9, inventoryName);
		
		
    if (player.hasPermission("wave.kit2.anchor")) {
	inventory.setItem(13 , new ItemBuilder("§a" + WaveKit2.ANCHOR.getName(), WaveKit2.ANCHOR.getIcon())
			.lore("§f" + WaveKit2.ANCHOR.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui2", WaveKit2.ANCHOR.getName())
					.toStack()
			);
    }	else {
    	inventory.setItem(13 , new ItemBuilder("§c" + WaveKit2.ANCHOR.getName(), new ItemStack(Material.RED_STAINED_GLASS_PANE))
    			.lore("§f" + WaveKit2.ANCHOR.getDescription())
    					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
    							ItemFlag.HIDE_DESTROYS,
    							ItemFlag.HIDE_ENCHANTS,
    							ItemFlag.HIDE_PLACED_ON,
    							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
    							ItemFlag.HIDE_UNBREAKABLE)
    					.nbt("kit-visual", WaveKit2.ANCHOR.getName())
    					.toStack()
    			);
    }
		
	inventory.setItem(10 , new ItemBuilder("§a" + "Nenhum", WaveKit2.PVP.getIcon())
			.lore("§f" + WaveKit2.PVP.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
							ItemFlag.HIDE_UNBREAKABLE).addEnchant(Enchantment.SHARPNESS, 1)
					.nbt("kit-gui2", WaveKit2.PVP.getName())
					.toStack()
			);
	inventory.setItem(11 , new ItemBuilder("§a" + WaveKit2.ARCHER.getName(), WaveKit2.ARCHER.getIcon())
			.lore("§f" + WaveKit2.ARCHER.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui2", WaveKit2.ARCHER.getName())
					.toStack()
			);
	if (player.hasPermission("wave.kit2.boxer")) {
	inventory.setItem(14 , new ItemBuilder("§a" + WaveKit2.BOXER.getName(), WaveKit2.BOXER.getIcon())
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
    	inventory.setItem(14 , new ItemBuilder("§c" + WaveKit2.BOXER.getName(), new ItemStack(Material.RED_STAINED_GLASS_PANE))
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
	if (player.hasPermission("wave.kit2.snail")) {
	inventory.setItem(15 , new ItemBuilder("§a" + WaveKit2.SNAIL.getName(), WaveKit2.SNAIL.getIcon())
			.lore("§f" + WaveKit2.SNAIL.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui2", WaveKit2.SNAIL.getName())
					.toStack()
			);
	} else {
    	inventory.setItem(15 , new ItemBuilder("§c" + WaveKit2.SNAIL.getName(), new ItemStack(Material.RED_STAINED_GLASS_PANE))
    			.lore("§f" + WaveKit2.SNAIL.getDescription())
    					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
    							ItemFlag.HIDE_DESTROYS,
    							ItemFlag.HIDE_ENCHANTS,
    							ItemFlag.HIDE_PLACED_ON,
    							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
    							ItemFlag.HIDE_UNBREAKABLE)
    					.nbt("kit-visual", WaveKit2.SNAIL.getName())
    					.toStack()
    			);
    }
	inventory.setItem(12 , new ItemBuilder("§a" + WaveKit2.KANGAROO.getName(), WaveKit2.KANGAROO.getIcon())
			.lore("§f" + WaveKit2.KANGAROO.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui2", WaveKit2.KANGAROO.getName())
					.toStack()
			);
	if (player.hasPermission("wave.kit2.fisherman")) {
	inventory.setItem(14 , new ItemBuilder("§a" + WaveKit2.FISHERMAN.getName(), WaveKit2.FISHERMAN.getIcon())
			.lore("§f" + WaveKit2.FISHERMAN.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui2", WaveKit2.FISHERMAN.getName())
					.toStack()
			);
	} else {
    	inventory.setItem(14 , new ItemBuilder("§c" + WaveKit2.FISHERMAN.getName(), new ItemStack(Material.RED_STAINED_GLASS_PANE))
    			.lore("§f" + WaveKit2.FISHERMAN.getDescription())
    					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
    							ItemFlag.HIDE_DESTROYS,
    							ItemFlag.HIDE_ENCHANTS,
    							ItemFlag.HIDE_PLACED_ON,
    							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
    							ItemFlag.HIDE_UNBREAKABLE)
    					.nbt("kit-visual", WaveKit2.FISHERMAN.getName())
    					.toStack()
    			);
    }
	
	if (player.hasPermission("wave.kit2.stomper")) {
		inventory.setItem(34 , new ItemBuilder("§a" + WaveKit2.STOMPER.getName(), Material.IRON_BOOTS)
				.lore("§f" + WaveKit2.STOMPER.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", WaveKit2.STOMPER.getName())
						.toStack()
				);
		} else {
	    	inventory.setItem(34 , new ItemBuilder("§c" + WaveKit2.STOMPER.getName(), new ItemStack(Material.RED_STAINED_GLASS_PANE))
	    			.lore("§f" + WaveKit2.STOMPER.getDescription())
	    					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
	    							ItemFlag.HIDE_DESTROYS,
	    							ItemFlag.HIDE_ENCHANTS,
	    							ItemFlag.HIDE_PLACED_ON,
	    							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
	    							ItemFlag.HIDE_UNBREAKABLE)
	    					.nbt("kit-visual", WaveKit2.STOMPER.getName())
	    					.toStack()
	    			);
	    }

	if (player.hasPermission("wave.kit2.thor")) {
		inventory.setItem(25 , new ItemBuilder("§a" + WaveKit2.THOR.getName(), WaveKit2.THOR.getIcon())
				.lore("§f" + WaveKit2.THOR.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", WaveKit2.THOR.getName())
						.toStack()
				);
		} else {
	    	inventory.setItem(25 , new ItemBuilder("§c" + WaveKit2.THOR.getName(), new ItemStack(Material.RED_STAINED_GLASS_PANE))
	    			.lore("§f" + WaveKit2.THOR.getDescription())
	    					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
	    							ItemFlag.HIDE_DESTROYS,
	    							ItemFlag.HIDE_ENCHANTS,
	    							ItemFlag.HIDE_PLACED_ON,
	    							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
	    							ItemFlag.HIDE_UNBREAKABLE)
	    					.nbt("kit-visual", WaveKit2.THOR.getName())
	    					.toStack()
	    			);
	    }

	if (player.hasPermission("wave.kit2.viper")) {
		inventory.setItem(16 , new ItemBuilder("§a" + WaveKit2.VIPER.getName(), WaveKit2.VIPER.getIcon())
				.lore("§f" + WaveKit2.VIPER.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", WaveKit2.VIPER.getName())
						.toStack()
				);
		} else {
	    	inventory.setItem(16 , new ItemBuilder("§c" + WaveKit2.VIPER.getName(), new ItemStack(Material.RED_STAINED_GLASS_PANE))
	    			.lore("§f" + WaveKit2.VIPER.getDescription())
	    					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
	    							ItemFlag.HIDE_DESTROYS,
	    							ItemFlag.HIDE_ENCHANTS,
	    							ItemFlag.HIDE_PLACED_ON,
	    							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
	    							ItemFlag.HIDE_UNBREAKABLE)
	    					.nbt("kit-visual", WaveKit2.VIPER.getName())
	    					.toStack()
	    			);
	    }
	if (player.hasPermission("wave.kit2.antistomper")) {
	inventory.setItem(19 , new ItemBuilder("§a" + WaveKit2.ANTISTOMPER.getName(), WaveKit2.ANTISTOMPER.getIcon())
			.lore("§f" + WaveKit2.ANTISTOMPER.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui2", WaveKit2.ANTISTOMPER.getName())
					.toStack()
			);
	} else {
    	inventory.setItem(19 , new ItemBuilder("§c" + WaveKit2.ANTISTOMPER.getName(), new ItemStack(Material.RED_STAINED_GLASS_PANE))
    			.lore("§f" + WaveKit2.ANTISTOMPER.getDescription())
    					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
    							ItemFlag.HIDE_DESTROYS,
    							ItemFlag.HIDE_ENCHANTS,
    							ItemFlag.HIDE_PLACED_ON,
    							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
    							ItemFlag.HIDE_UNBREAKABLE)
    					.nbt("kit-visual", WaveKit2.ANTISTOMPER.getName())
    					.toStack()
    			);
    }
	if (player.hasPermission("wave.kit2.gladiator")) {
	inventory.setItem(20 , new ItemBuilder("§a" + WaveKit2.GLADIATOR.getName(), WaveKit2.GLADIATOR.getIcon())
			.lore("§f" + WaveKit2.GLADIATOR.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui2", WaveKit2.GLADIATOR.getName())
					.toStack()
			);
	} else {
    	inventory.setItem(20 , new ItemBuilder("§c" + WaveKit2.GLADIATOR.getName(), new ItemStack(Material.RED_STAINED_GLASS_PANE))
    			.lore("§f" + WaveKit2.GLADIATOR.getDescription())
    					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
    							ItemFlag.HIDE_DESTROYS,
    							ItemFlag.HIDE_ENCHANTS,
    							ItemFlag.HIDE_PLACED_ON,
    							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
    							ItemFlag.HIDE_UNBREAKABLE)
    					.nbt("kit-visual", WaveKit2.GLADIATOR.getName())
    					.toStack()
    			);
    }
	if (player.hasPermission("wave.kit2.magma")) {
		inventory.setItem(21 , new ItemBuilder("§a" + WaveKit2.MAGMA.getName(), WaveKit2.MAGMA.getIcon())
				.lore("§f" + WaveKit2.MAGMA.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", WaveKit2.MAGMA.getName())
						.toStack()
				);
	} else {
    	inventory.setItem(21 , new ItemBuilder("§c" + WaveKit2.MAGMA.getName(), new ItemStack(Material.RED_STAINED_GLASS_PANE))
    			.lore("§f" + WaveKit2.MAGMA.getDescription())
    					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
    							ItemFlag.HIDE_DESTROYS,
    							ItemFlag.HIDE_ENCHANTS,
    							ItemFlag.HIDE_PLACED_ON,
    							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
    							ItemFlag.HIDE_UNBREAKABLE)
    					.nbt("kit-visual", WaveKit2.MAGMA.getName())
    					.toStack()
    			);
    }
	if (player.hasPermission("wave.kit2.monk")) {
				inventory.setItem(22 , new ItemBuilder("§a" + WaveKit2.MONK.getName(), WaveKit2.MONK.getIcon())
						.lore("§f" + WaveKit2.MONK.getDescription())
								.addFlags(ItemFlag.HIDE_ATTRIBUTES,
										ItemFlag.HIDE_DESTROYS,
										ItemFlag.HIDE_ENCHANTS,
										ItemFlag.HIDE_PLACED_ON,
										ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
										ItemFlag.HIDE_UNBREAKABLE)
								.nbt("kit-gui2", WaveKit2.MONK.getName())
								.toStack()
						);
	} else {
    	inventory.setItem(22 , new ItemBuilder("§c" + WaveKit2.MONK.getName(), new ItemStack(Material.RED_STAINED_GLASS_PANE))
    			.lore("§f" + WaveKit2.MONK.getDescription())
    					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
    							ItemFlag.HIDE_DESTROYS,
    							ItemFlag.HIDE_ENCHANTS,
    							ItemFlag.HIDE_PLACED_ON,
    							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
    							ItemFlag.HIDE_UNBREAKABLE)
    					.nbt("kit-visual", WaveKit2.MONK.getName())
    					.toStack()
    			);
    }
	if (player.hasPermission("wave.kit2.neo")) {
				inventory.setItem(23 , new ItemBuilder("§a" + WaveKit2.NEO.getName(), WaveKit2.NEO.getIcon())
						.lore("§f" + WaveKit2.NEO.getDescription())
								.addFlags(ItemFlag.HIDE_ATTRIBUTES,
										ItemFlag.HIDE_DESTROYS,
										ItemFlag.HIDE_ENCHANTS,
										ItemFlag.HIDE_PLACED_ON,
										ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
										ItemFlag.HIDE_UNBREAKABLE)
								.nbt("kit-gui2", WaveKit2.NEO.getName())
								.toStack()
						);
	} else {
    	inventory.setItem(23 , new ItemBuilder("§c" + WaveKit2.NEO.getName(), new ItemStack(Material.RED_STAINED_GLASS_PANE))
    			.lore("§f" + WaveKit2.NEO.getDescription())
    					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
    							ItemFlag.HIDE_DESTROYS,
    							ItemFlag.HIDE_ENCHANTS,
    							ItemFlag.HIDE_PLACED_ON,
    							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
    							ItemFlag.HIDE_UNBREAKABLE)
    					.nbt("kit-visual", WaveKit2.NEO.getName())
    					.toStack()
    			);
    }
	if (player.hasPermission("wave.kit2.ninja")) {
				inventory.setItem(24 , new ItemBuilder("§a" + WaveKit2.NINJA.getName(), WaveKit2.NINJA.getIcon())
						.lore("§f" + WaveKit2.NINJA.getDescription())
								.addFlags(ItemFlag.HIDE_ATTRIBUTES,
										ItemFlag.HIDE_DESTROYS,
										ItemFlag.HIDE_ENCHANTS,
										ItemFlag.HIDE_PLACED_ON,
										ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
										ItemFlag.HIDE_UNBREAKABLE)
								.nbt("kit-gui2", WaveKit2.NINJA.getName())
								.toStack()
						);
	} else {
		inventory.setItem(24 , new ItemBuilder("§c" + WaveKit2.NINJA.getName(), new ItemStack(Material.RED_STAINED_GLASS_PANE))
    			.lore("§f" + WaveKit2.NINJA.getDescription())
    					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
    							ItemFlag.HIDE_DESTROYS,
    							ItemFlag.HIDE_ENCHANTS,
    							ItemFlag.HIDE_PLACED_ON,
    							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
    							ItemFlag.HIDE_UNBREAKABLE)
    					.nbt("kit-visual", WaveKit2.NINJA.getName())
    					.toStack()
    			);
    
	}
	if (player.hasPermission("wave.kit2.phantom")) {
				inventory.setItem(33 , new ItemBuilder("§a" + WaveKit2.PHANTOM.getName(), WaveKit2.PHANTOM.getIcon())
						.lore("§f" + WaveKit2.PHANTOM.getDescription())
								.addFlags(ItemFlag.HIDE_ATTRIBUTES,
										ItemFlag.HIDE_DESTROYS,
										ItemFlag.HIDE_ENCHANTS,
										ItemFlag.HIDE_PLACED_ON,
										ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
										ItemFlag.HIDE_UNBREAKABLE)
								.nbt("kit-gui2", WaveKit2.PHANTOM.getName())
								.toStack()
						);
	} else {
		inventory.setItem(33 , new ItemBuilder("§c" + WaveKit2.PHANTOM.getName(), new ItemStack(Material.RED_STAINED_GLASS_PANE))
    			.lore("§f" + WaveKit2.PHANTOM.getDescription())
    					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
    							ItemFlag.HIDE_DESTROYS,
    							ItemFlag.HIDE_ENCHANTS,
    							ItemFlag.HIDE_PLACED_ON,
    							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
    							ItemFlag.HIDE_UNBREAKABLE)
    					.nbt("kit-visual", WaveKit2.PHANTOM.getName())
    					.toStack()
    			);
	}
	if (player.hasPermission("wave.kit2.switcher")) {
		inventory.setItem(32 , new ItemBuilder("§a" + WaveKit2.SWITCHER.getName(), WaveKit2.SWITCHER.getIcon())
				.lore("§f" + WaveKit2.SWITCHER.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", WaveKit2.SWITCHER.getName())
						.toStack()
				);
} else {
inventory.setItem(32 , new ItemBuilder("§c" + WaveKit2.SWITCHER.getName(), new ItemStack(Material.RED_STAINED_GLASS_PANE))
		.lore("§f" + WaveKit2.SWITCHER.getDescription())
				.addFlags(ItemFlag.HIDE_ATTRIBUTES,
						ItemFlag.HIDE_DESTROYS,
						ItemFlag.HIDE_ENCHANTS,
						ItemFlag.HIDE_PLACED_ON,
						ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
						ItemFlag.HIDE_UNBREAKABLE)
				.nbt("kit-visual", WaveKit2.SWITCHER.getName())
				.toStack()
		);
}
		if (player.hasPermission("wave.kit2.poseidon")) {
				inventory.setItem(28 , new ItemBuilder("§a" + WaveKit2.POSEIDON.getName(), WaveKit2.POSEIDON.getIcon())
						.lore("§f" + WaveKit2.POSEIDON.getDescription())
								.addFlags(ItemFlag.HIDE_ATTRIBUTES,
										ItemFlag.HIDE_DESTROYS,
										ItemFlag.HIDE_ENCHANTS,
										ItemFlag.HIDE_PLACED_ON,
										ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
										ItemFlag.HIDE_UNBREAKABLE)
								.nbt("kit-gui2", WaveKit2.POSEIDON.getName())
								.toStack()
						);
		} else {
						inventory.setItem(28 , new ItemBuilder("§c" + WaveKit2.POSEIDON.getName(), new ItemStack(Material.RED_STAINED_GLASS_PANE))
				    			.lore("§f" + WaveKit2.POSEIDON.getDescription())
				    					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
				    							ItemFlag.HIDE_DESTROYS,
				    							ItemFlag.HIDE_ENCHANTS,
				    							ItemFlag.HIDE_PLACED_ON,
				    							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
				    							ItemFlag.HIDE_UNBREAKABLE)
				    					.nbt("kit-visual", WaveKit2.POSEIDON.getName())
				    					.toStack()
				    			);
		}
						if (player.hasPermission("wave.kit2.sight")) {
				inventory.setItem(29 , new ItemBuilder("§a" + WaveKit2.SIGHT.getName(), WaveKit2.SIGHT.getIcon())
						.lore("§f" + WaveKit2.SIGHT.getDescription())
								.addFlags(ItemFlag.HIDE_ATTRIBUTES,
										ItemFlag.HIDE_DESTROYS,
										ItemFlag.HIDE_ENCHANTS,
										ItemFlag.HIDE_PLACED_ON,
										ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
										ItemFlag.HIDE_UNBREAKABLE)
								.nbt("kit-gui2", WaveKit2.SIGHT.getName())
								.toStack()
						);
						
						} else {
							inventory.setItem(29 , new ItemBuilder("§c" + WaveKit2.SIGHT.getName(), new ItemStack(Material.RED_STAINED_GLASS_PANE))
					    			.lore("§f" + WaveKit2.SIGHT.getDescription())
					    					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
					    							ItemFlag.HIDE_DESTROYS,
					    							ItemFlag.HIDE_ENCHANTS,
					    							ItemFlag.HIDE_PLACED_ON,
					    							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
					    							ItemFlag.HIDE_UNBREAKABLE)
					    					.nbt("kit-visual", WaveKit2.SIGHT.getName())
					    					.toStack()
					    			);
						}
					
								if (player.hasPermission("wave.kit2.turtle")) {
				inventory.setItem(30 , new ItemBuilder("§a" + WaveKit2.TURTLE.getName(), WaveKit2.TURTLE.getIcon())
						.lore("§f" + WaveKit2.TURTLE.getDescription())
								.addFlags(ItemFlag.HIDE_ATTRIBUTES,
										ItemFlag.HIDE_DESTROYS,
										ItemFlag.HIDE_ENCHANTS,
										ItemFlag.HIDE_PLACED_ON,
										ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
										ItemFlag.HIDE_UNBREAKABLE)
								.nbt("kit-gui2", WaveKit2.TURTLE.getName())
								.toStack()
						);
								} else {
						inventory.setItem(30 , new ItemBuilder("§c" + WaveKit2.TURTLE.getName(), new ItemStack(Material.RED_STAINED_GLASS_PANE))
				    			.lore("§f" + WaveKit2.TURTLE.getDescription())
				    					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
				    							ItemFlag.HIDE_DESTROYS,
				    							ItemFlag.HIDE_ENCHANTS,
				    							ItemFlag.HIDE_PLACED_ON,
				    							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
				    							ItemFlag.HIDE_UNBREAKABLE)
				    					.nbt("kit-visual", WaveKit2.TURTLE.getName())
				    					.toStack()
				    			);
								}
						if (player.hasPermission("wave.kit2.autobowl")) {
				inventory.setItem(31 , new ItemBuilder("§a" + WaveKit2.AUTOBOWL.getName(), WaveKit2.AUTOBOWL.getIcon())
						.lore("§f" + WaveKit2.AUTOBOWL.getDescription())
								.addFlags(ItemFlag.HIDE_ATTRIBUTES,
										ItemFlag.HIDE_DESTROYS,
										ItemFlag.HIDE_ENCHANTS,
										ItemFlag.HIDE_PLACED_ON,
										ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
										ItemFlag.HIDE_UNBREAKABLE)
								.nbt("kit-gui2", WaveKit2.AUTOBOWL.getName())
								.toStack()
						);
						} else {
						inventory.setItem(31 , new ItemBuilder("§c" + WaveKit2.AUTOBOWL.getName(), new ItemStack(Material.RED_STAINED_GLASS_PANE))
				    			.lore("§f" + WaveKit2.AUTOBOWL.getDescription())
				    					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
				    							ItemFlag.HIDE_DESTROYS,
				    							ItemFlag.HIDE_ENCHANTS,
				    							ItemFlag.HIDE_PLACED_ON,
				    							ItemFlag.HIDE_ADDITIONAL_TOOLTIP,
				    							ItemFlag.HIDE_UNBREAKABLE)
				    					.nbt("kit-visual", WaveKit2.AUTOBOWL.getName())
				    					.toStack()
				    			);
						}
	
		
		
		if (KitManager2.getPlayer(player.getName()).getkit2() != WaveKit2.PVP) {
			ItemStack i =  new ItemStack(KitManager2.getPlayer(player.getName()).getkit2().getIcon());
			ItemMeta i2 = i.getItemMeta();
			 ArrayList<String> lore = new ArrayList<String>();
			i2.setDisplayName(KitManager2.getPlayer(player.getName()).getkit2().getName());
		   
		    lore.add("§e" + KitManager2.getPlayer(player.getName()).getkit2().getDescription());
			i2.setLore(lore);
			i.setItemMeta(i2);
			inventory.setItem(53, new ItemBuilder("§aProxímo", Material.ARROW).nbt("prox")
					.toStack()
			);
			inventory.setItem(49, new ItemBuilder(i).nbt("visual")
					.toStack()
			);
		    }
			else {
				ItemStack i =  new ItemStack(Material.ITEM_FRAME);
				ItemMeta i3 = i.getItemMeta();
				i3.setDisplayName("§eSem kits selecionados.");	
				ArrayList<String> lore = new ArrayList<String>();
		
				i3.setLore(lore);
				i.setItemMeta(i3);
				inventory.setItem(53, new ItemBuilder("§aProxímo", Material.ARROW).nbt("prox")
						.toStack()
				);
				inventory.setItem(49, new ItemBuilder(i).nbt("visual")
						.toStack()
				);
			}
		
		player.openInventory(inventory);
		
	}
								

				
					
		
					
	
		
				
	
	public static String getInventoryName() {
		return inventoryName;
	}



	
}
