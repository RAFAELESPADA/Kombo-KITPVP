package net.wavemc.pvp.inventory.listener;



import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;

import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedServerPing;

import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;
import net.wavemc.core.bukkit.item.ItemBuilder;
import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.command.AdminUtil;
import net.wavemc.pvp.inventory.KitInventory22;
import net.wavemc.pvp.inventory.KitsInventory;
import net.wavemc.pvp.inventory.KitsInventory2;
import net.wavemc.pvp.inventory.KitsInventoryPageTwo;
import net.wavemc.pvp.kit.WaveKit;
import net.wavemc.pvp.kit.WaveKit2;
import net.wavemc.pvp.kit.KitManager;
import net.wavemc.pvp.kit.KitManager2;

public class SelectKitListener implements Listener {
	
    protected Object motdPingInstance;
	
	@EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        List<Player> admins = Bukkit.getOnlinePlayers().stream().filter(
                online -> AdminUtil.has(online.getName())
        ).collect(Collectors.toList());

        if (!player.hasPermission("wave.cmd.admin")) {
            admins.forEach(player::hidePlayer);
        }
    }

    public void onJoin2(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (AdminUtil.has(player.getName())) {
            player.setGameMode(GameMode.SURVIVAL);
        }
    }
    public void onJoin3(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        for (Player o : Bukkit.getOnlinePlayers()) {
        if (AdminUtil.has(o.getName()) && !player.hasPermission("wave.cmd.vanish")) {
            player.hidePlayer(o);
            Bukkit.getConsoleSender().sendMessage("Escondendo players no /admin para " + player.getName());
        }
        }
    }



    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (event.getItem() == null) {
			return;
		}
        if (event.getItem().getType().equals(Material.MUSHROOM_STEW)) {
			return;
		}
        if (!event.hasItem()) {
            return;
        }
        if (!event.getItem().hasItemMeta()) {
        	return;
        }
        if (event.getItem().getItemMeta().getDisplayName() == null) {
        	return;
        }

        switch (event.getItem().getItemMeta().getDisplayName()) {
            case "§cSair do Admin":
                p.performCommand("admin");
                event.setCancelled(true);

                break;
            case "§bTroca Rápida":
                p.performCommand("admin");
                Bukkit.getScheduler().scheduleSyncDelayedTask(WavePvP.getInstance(), new Runnable() {
                    public void run() {
                        p.performCommand("admin");
                        event.setCancelled(true);

                    }
                }, 24);
                break;
        }
    }

    @EventHandler
    public void onClickPlayer(PlayerInteractEntityEvent event) {
        if (!(event.getRightClicked() instanceof Player)) {
            return;
        }

        Player player = event.getPlayer();
        Player target = (Player) event.getRightClicked();

        if (player.getItemInHand() == null) {
            return;
        }
        if (!event.getPlayer().getItemInHand().hasItemMeta()) {
        	return;
        }
        if (event.getPlayer().getItemInHand().getItemMeta().getDisplayName() == null) {
        	return;
        }

        switch (event.getPlayer().getItemInHand().getItemMeta().getDisplayName()) {
            case "§aBuscar Informações":
                event.setCancelled(true);

                player.performCommand("stats " + target.getName());
                player.performCommand("pinfo " + target.getName());
                break;
            case "§3Testar NoFall":
                event.setCancelled(true);

                target.teleport(target.getLocation().add(0.0D, 11.0D, -0.05D));
                break;
        }
    
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (AdminUtil.has(player.getName())) {
            AdminUtil.remove(player.getName());
            player.setGameMode(GameMode.SURVIVAL);
            Bukkit.getOnlinePlayers().forEach(online -> online.showPlayer(player));
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (AdminUtil.has(event.getPlayer().getName())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (AdminUtil.has(event.getPlayer().getName())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPickupItem(PlayerPickupItemEvent event) {
        if (AdminUtil.has(event.getPlayer().getName())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent event) {
        if (AdminUtil.has(event.getPlayer().getName())) {
            event.setCancelled(true);
        }
    
}
	@EventHandler
	public void onInvClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(KitsInventory.getInventoryName())) {
			return;
		}
		if (event.getCurrentItem() == null) {
			return;
		}
	
			 
			    
				   
				   if (ItemBuilder.has(event.getCurrentItem(), "visual")) {
						 event.setCancelled(true);
						return;
					}
				   
				   

					String kitName3 = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
					
					
					String kitName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
			event.setCancelled(true);
					WaveKit.findKit(kitName).ifPresent(kit -> {
						if (KitManager2.getPlayer(player.getName()).getkit2().getName() == kit.getName()) {
							if (KitManager2.getPlayer(player.getName()).getkit2().getName() == "Nenhum") {
								return;	
							}
							player.sendMessage("§cVocê já selecionou esse kit como secundário!");
							event.setCancelled(true);
							return;
						}
						player.closeInventory();
					});

					String kitName22 = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
					WaveKit.findKit(kitName3).ifPresent(kit ->
					{
						String kitName2 = kit.getName();
						
						if (KitManager2.getPlayer(player.getName()).getkit2().getName() == kit.getName()) {
							if (kit.getName() == "Nenhum") {

								KitManager.getPlayer(player.getName()).removeKit();

								player.closeInventory();
								return;
							}
if (KitManager2.getPlayer(player.getName()).getkit2().getName() == "Nenhum") {
	KitManager.getPlayer(player.getName()).removeKit();

	player.closeInventory();
	return;
}
							player.sendMessage("§cVocê já selecionou esse kit como secundário!");
							event.setCancelled(true);
							player.closeInventory();
							return;
						}
						if (KitManager2.getPlayer(player.getName()).getkit2() == WaveKit2.STOMPER && (kitName22 == "Grappler" || kitName22 == "Kangaroo" || kitName22 == "Flash"  || kitName22 == "Neo" || kitName22 == "AntiStomper" || kitName22 == "Ninja")) {
							player.sendMessage("§c" + kitName2 + " é incompatível com Stomper");
							player.closeInventory();
							return;
						}
						
						
						
						if (KitManager2.getPlayer(player.getName()).getkit2() == WaveKit2.STOMPER && (kitName22 == "Meteor")) {
							player.sendMessage("§c" + kitName2 + " é incompatível com Stomper");
							player.closeInventory();
							return;
						}
						
						if (KitManager2.getPlayer(player.getName()).getkit2() == WaveKit2.STOMPER && (kitName22 == "Grappler")) {
							player.sendMessage("§c" + kitName2 + " é incompatível com Grappler");
							player.closeInventory();
							return;
						}
						if (KitManager2.getPlayer(player.getName()).getkit2() == WaveKit2.NINJA && (event.getCurrentItem().getType() == Material.IRON_BOOTS)) {
							player.sendMessage("§cStomper é incompatível com Ninja");
							player.closeInventory();
							return;
						}
						///erfgh
						if (KitManager.getPlayer(player.getName()).getKit() == WaveKit.STOMPER && (kitName22 == "Grappler" || kitName22 == "Kangaroo" || kitName22 == "Flash"  || kitName22 == "Neo" || kitName22 == "AntiStomper" || kitName22 == "Ninja")) {
							player.sendMessage("§c" + kitName22 + " é incompatível com Stomper");
							player.closeInventory();
							return;
						}
						
						if (KitManager.getPlayer(player.getName()).getKit() == WaveKit.HULK && (event.getCurrentItem().getType() == Material.IRON_BARS)) {
							player.sendMessage("§c" + kitName22 + " é incompatível com Hulk");
							player.closeInventory();
							return;
						}
						if (KitManager.getPlayer(player.getName()).getKit() == WaveKit.STOMPER && (kitName22 == "Meteor")) {
							player.sendMessage("§c" + kitName22 + " é incompatível com Stomper");
							player.closeInventory();
							return;
						}
						if (KitManager.getPlayer(player.getName()).getKit() == WaveKit.PHANTOM && (kitName22 == "Grappler" || kitName22 == "Kangaroo" || kitName22 == "Fisherman" || kitName22 == "Ninja" || kitName22 == "Flash" || kitName22 == "Stomper")) {
							player.sendMessage("§c" + kitName22 + " é incompatível com Phantom");
							player.closeInventory();
							return;
						}
						if (KitManager.getPlayer(player.getName()).getKit() == WaveKit.NINJA && (event.getCurrentItem().getType() == Material.IRON_BOOTS)) {
							player.sendMessage("§cStomper é incompatível com Ninja");
							player.closeInventory();
							return;
						}
						if (KitManager.getPlayer(player.getName()).getKit() == WaveKit.NEO && (event.getCurrentItem().getType() == Material.IRON_BOOTS)) {
							player.sendMessage("§cNeo é incompatível com Stomper");
							player.closeInventory();
							return;
						}
						if ((KitManager.getPlayer(player.getName()).getKit() == WaveKit.FLASH || KitManager.getPlayer(player.getName()).getKit() == WaveKit.NINJA) && (kitName22 == "Grappler" || kitName22 == "Gladiator")) {
							player.sendMessage("§c" + kitName22 + " é incompatível com Flash");
							player.closeInventory();
							return;
						}
						if (KitManager.getPlayer(player.getName()).getKit() == WaveKit.GLADIATOR && (kitName2 == "Flash" || kitName22 == "Ninja")) {
							player.sendMessage("§c" + kitName22 + " é incompatível com Gladiator");
							player.closeInventory();
							return;
						}
							
						if (KitManager2.getPlayer(player.getName()).getkit2() == WaveKit2.NEO && (event.getCurrentItem().getType() == Material.IRON_BOOTS)) {
							player.sendMessage("§cNeo é incompatível com Stomper");
							player.closeInventory();
							return;
						}
						if (KitManager.getPlayer(player.getName()).getKit() == WaveKit.GLADIATOR && (kitName22 == "Flash" || kitName22 == "Ninja")) {
							player.sendMessage("§c" + kitName22 + " é incompatível com Gladiator");
							player.closeInventory();
							return;
						}

						player.closeInventory();
						kit.send(player);
					});  
					return;
	
					
					
			   }

			   
	
	
	
	
		
		
	

	@EventHandler
	public void onInvClick55(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(KitsInventoryPageTwo.getInventoryName())) {
			return;
		}
		if (ItemBuilder.has(event.getCurrentItem(), "visual")) {
			event.setCancelled(true);
			return;
		}
		
		
		if (event.getCurrentItem() == null) {
			return;
		}
		

		String kitName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
		WaveKit.findKit(kitName).ifPresent(kit -> {
			if (KitManager2.getPlayer(player.getName()).getkit2().getName() == kit.getName()) {
				if (KitManager2.getPlayer(player.getName()).getkit2().getName() == "Nenhum") {
					kit.send(player);
					return;	
				}
				player.sendMessage("§cVocê já selecionou esse kit como secundário!");
				event.setCancelled(true);
				return;
			}
			player.closeInventory();
			kit.send(player);
		});  
		
	}

	@EventHandler
	public void onInvClick24(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(KitsInventory.getInventoryName())) {
			return;
		}
		if (!ItemBuilder.has(event.getCurrentItem(), "prox")) {
			return;
		}
		event.setCancelled(true);
		KitsInventoryPageTwo.open(player);
	}


	@EventHandler
	public void onInvClick2456(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(KitsInventory2.getInventoryName())) {
			return;
		}
		if (!ItemBuilder.has(event.getCurrentItem(), "prox")) {
			return;
		}
		event.setCancelled(true);
		KitInventory22.open(player);
	}
	@EventHandler
	public void onInvClick2456yy7(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(KitInventory22.getInventoryName())) {
			return;
		}
		if (!ItemBuilder.has(event.getCurrentItem(), "voltar")) {
			return;
		}
		event.setCancelled(true);
		KitsInventory2.open(player);
	}
	@EventHandler
	public void onInvClick242(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(KitsInventoryPageTwo.getInventoryName())) {
			return;
		}
		if (!ItemBuilder.has(event.getCurrentItem(), "voltar")) {
			return;
		}
		event.setCancelled(true);
		KitsInventory.open(player);
	}


	@EventHandler
	public void onInvClick2(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(KitsInventory2.getInventoryName())) {
			return;
		}
		if (event.getCurrentItem() == null) {
			return;
		}
		event.setCancelled(true);
		if (!player.hasPermission("wave.kit2." + ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()) ) && event.getCurrentItem().getType() != Material.ARROW) {
			player.sendMessage(ChatColor.RED + "Você não tem esse kit liberado.");
			player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 10, 10);
			event.setCancelled(true);
			return;
		
		}
		
		
		String kitName2 = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());

		
		WaveKit2.findKit(kitName2).ifPresent(kit -> {
			if (KitManager.getPlayer(player.getName()).getKit().getName() == kit.getName()) {
				if (kit.name() == "Nenhum") {
					kit.send(player);
					player.closeInventory();
					return;	
				}
				if (KitManager.getPlayer(player.getName()).getKit().getName() == "Nenhum") {
					kit.send(player);
					return;	
				}
				player.sendMessage("§cVocê já selecionou esse kit como primário!");
				event.setCancelled(true);
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == WaveKit.STOMPER && (kitName2 == "Grappler" || kitName2 == "Kangaroo" || kitName2 == "Flash"  || kitName2 == "Neo" || kitName2 == "AntiStomper" || kitName2 == "Ninja")) {
				player.sendMessage("§c" + kitName2 + " é incompatível com Stomper");
				player.closeInventory();
				return;
			}
		
			
			
			
			if (KitManager.getPlayer(player.getName()).getKit() == WaveKit.BOXER && (kitName2 == "Anchor")) {
				player.sendMessage("§c" + kitName2 + " é incompatível com Boxer");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == WaveKit.STOMPER && (kitName2 == "Meteor")) {
				player.sendMessage("§c" + kitName2 + " é incompatível com Stomper");
				player.closeInventory();
				return;
			}
			
			
			if (KitManager.getPlayer(player.getName()).getKit() == WaveKit.PHANTOM && (kitName2 == "Grappler" || kitName2 == "Kangaroo" || kitName2 == "Fisherman" || kitName2 == "Ninja" || kitName2 == "Flash" || kitName2 == "Stomper")) {
				player.sendMessage("§c" + kitName2 + " é incompatível com Phantom");
				player.closeInventory();
				return;
			}

			if (KitManager.getPlayer(player.getName()).getKit() == WaveKit.NINJA && (event.getCurrentItem().getType() == Material.IRON_BOOTS)) {
				player.sendMessage("§cStomper é incompatível com Ninja");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == WaveKit.NEO && (event.getCurrentItem().getType() == Material.IRON_BOOTS)) {
				player.sendMessage("§cNeo é incompatível com Stomper");
				player.closeInventory();
				return;
			}
			if ((KitManager.getPlayer(player.getName()).getKit() == WaveKit.FLASH || KitManager.getPlayer(player.getName()).getKit() == WaveKit.NINJA) && (kitName2 == "Grappler" || kitName2 == "Gladiator")) {
				player.sendMessage("§c" + kitName2 + " é incompatível com Flash");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == WaveKit.GLADIATOR && (kitName2 == "Flash" || kitName2 == "Ninja")) {
				player.sendMessage("§c" + kitName2 + " é incompatível com Gladiator");
				player.closeInventory();
				return;
			}
				
				player.closeInventory();
				kit.send(player);
		});
		
			
	}
	
	@EventHandler
	public void onInvClick5(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(KitInventory22.getInventoryName())) {
			return;
		}
		if (event.getCurrentItem() == null) {
			return;
		}
		if (!player.hasPermission("wave.kit2." + ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName())) && event.getCurrentItem().getType() != Material.ARROW) {
			player.sendMessage(ChatColor.RED + "Você não tem esse kit liberado.");
			player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 10, 10);
			event.setCancelled(true);
			return;
		
		}
		event.setCancelled(true);
		
		String kitName2 = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());

		WaveKit2.findKit(kitName2).ifPresent(kit -> {
			if (KitManager.getPlayer(player.getName()).getKit().getName() == kit.getName()) {
				player.sendMessage("§cVocê já selecionou esse kit como primário!");
				event.setCancelled(true);
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == WaveKit.STOMPER && (kitName2 == "Grappler" || kitName2 == "Kangaroo" || kitName2 == "Flash"  || kitName2 == "Neo" || kitName2 == "AntiStomper" || kitName2 == "Ninja")) {
				player.sendMessage("§c" + kitName2 + " é incompatível com Stomper");
				player.closeInventory();
				return;
			}
			
		
			if (KitManager.getPlayer(player.getName()).getKit() == WaveKit.STOMPER && (kitName2 == "Meteor")) {
				player.sendMessage("§c" + kitName2 + " é incompatível com Stomper");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == WaveKit.PHANTOM && (kitName2 == "Grappler" || kitName2 == "Kangaroo" || kitName2 == "Fisherman" || kitName2 == "Ninja" || kitName2 == "Flash" || kitName2 == "Stomper")) {
				player.sendMessage("§c" + kitName2 + " é incompatível com Phantom");
				player.closeInventory();
				return;
			}

			if (KitManager.getPlayer(player.getName()).getKit() == WaveKit.NINJA && (event.getCurrentItem().getType() == Material.IRON_BOOTS)) {
				player.sendMessage("§cStomper é incompatível com Ninja");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == WaveKit.NEO && (event.getCurrentItem().getType() == Material.IRON_BOOTS)) {
				player.sendMessage("§cNeo é incompatível com Stomper");
				player.closeInventory();
				return;
			}
			if ((KitManager.getPlayer(player.getName()).getKit() == WaveKit.FLASH || KitManager.getPlayer(player.getName()).getKit() == WaveKit.NINJA) && (kitName2 == "Grappler" || kitName2 == "Gladiator")) {
				player.sendMessage("§c" + kitName2 + " é incompatível com Flash");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == WaveKit.GLADIATOR && (kitName2 == "Flash" || kitName2 == "Ninja")) {
				player.sendMessage("§c" + kitName2 + " é incompatível com Gladiator");
				player.closeInventory();
				return;
			}
				
				player.closeInventory();
				kit.send(player);
		});
		
			
	}
	
}