package net.wavemc.pvp.listener;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;
import org.bukkit.util.Vector;

import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.item.ItemBuilder;
import net.wavemc.core.bukkit.util.AdminUtil;
import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.command.VanishUtil;
import net.wavemc.pvp.inventory.listener.PlayerCache;
import net.wavemc.pvp.kit.Habilidade;
import net.wavemc.pvp.kit.Habilidade2;
import net.wavemc.pvp.kit.WaveKit;
import net.wavemc.pvp.kit.WaveKit2;
import net.wavemc.pvp.kit.KitHandler;
import net.wavemc.pvp.kit.KitHandler2;
import net.wavemc.pvp.kit.KitManager;
import net.wavemc.pvp.kit.KitManager2;
import net.wavemc.pvp.warp.WaveWarp;





public class Jump implements Listener {
	public static HashMap<String, Boolean> recebeu = new HashMap();
	public static HashMap<String, Boolean> caiu = new HashMap();
	Path path1 = Paths.get(Bukkit.getServer().getWorldContainer().getAbsolutePath() + "/plugins/WaveCore/", "warps.yml");
	File file = new File(path1.toAbsolutePath().toString());
	YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
	public static ArrayList<String> fall = new ArrayList<String>();
	public KitHandler obj = new KitHandler();
	public KitHandler2 obj2 = new KitHandler2();
	public void Atirar(Player p) {
		int y = 8;
		Block block = p.getLocation().getBlock().getRelative(0, -1, 0);
		if (block.getType() == Material.SPONGE) {
			Vector vector = new Vector(0, y, 0);
			p.setVelocity(vector);
			fall.remove(p.getName());
			 
			fall.add(p.getName());
		}
	}
	public void Atirar2(Player p) {
		final Location loc = p.getEyeLocation();
    
        final Vector sponge = p.getLocation().getDirection().multiply(3.8).setY(0.55);
		Block block = p.getLocation().getBlock().getRelative(0, -1, 0);
		if (block.getType() == Material.SLIME_BLOCK) {
			p.setVelocity(sponge);
			fall.remove(p.getName());
			fall.add(p.getName());
		}
	}
	
	
	@EventHandler
	private void Jumps(PlayerDropItemEvent e) {
		if (!WaveWarp.FPS.hasPlayer(e.getPlayer().getName())) {
			return;
		}
		if (e.getItemDrop().getItemStack().getType() == Material.IRON_HELMET || e.getItemDrop().getItemStack().getType() == Material.IRON_CHESTPLATE || e.getItemDrop().getItemStack().getType() == Material.IRON_LEGGINGS || e.getItemDrop().getItemStack().getType() == Material.IRON_BOOTS) {
			e.getItemDrop().remove();
		}
	}

	@EventHandler
	private void Jumps(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player p = (Player)e.getEntity();
		if (e.getCause() != DamageCause.FALL) {
			return;
		}
		if (!WaveWarp.FPS.hasPlayer(p.getName())) {
			return;
		}
		else if (!recebeu.containsKey(p.getName())) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	private void Jumps(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		Player player = e.getPlayer();
		Atirar(p);
		Atirar2(p);	
		if (WaveWarp.FPS.hasPlayer(p.getName()) && p.getLocation().getY() <=  yaml.getInt("Y-fps") - 2 && !recebeu.containsKey(p.getName())) { 
			p.getInventory().clear();
			player.getInventory().setHelmet(new ItemBuilder(Material.IRON_HELMET).toStack());
			player.getInventory().setChestplate(new ItemBuilder(Material.IRON_CHESTPLATE).toStack());
			player.getInventory().setLeggings(new ItemBuilder(Material.IRON_LEGGINGS).toStack());
			player.getInventory().setBoots(new ItemBuilder(Material.IRON_BOOTS).toStack());
			
			ItemStack vermelho = new ItemStack(Material.RED_MUSHROOM, 64);
			
			KitManager.getPlayer(player.getName()).removeKit();
			KitManager2.getPlayer(player.getName()).removekit2();
			  ItemStack marrom = new ItemStack(Material.BROWN_MUSHROOM, 64);
			  
			 
			  
			  ItemStack item = new ItemStack(Material.BOWL, 64);
			  player.getInventory().setItem(14, vermelho);
			  player.getInventory().setItem(15, marrom);
			  player.getInventory().setItem(13, item);
			  if (!recebeu.containsKey(p.getName())) {
			  recebeu.put(p.getName(), true);
			  player.sendMessage("§cVocê perdeu a proteção do spawn.");
			  }
			 
			  for (int i = 0; i < 33; i++) {
					player.getInventory().addItem(new ItemStack(Material.MUSHROOM_STEW));
				}
			  ItemStack espada = new ItemBuilder("§fEspada", Material.DIAMOND_SWORD).addFlags(ItemFlag.HIDE_ATTRIBUTES).
						addEnchant(Enchantment.SHARPNESS, 1)
						.nbt("cancel-drop")
						.toStack();
				player.getInventory().setItem(0, espada);
			  
		}
		if (player.getLocation().getBlockY() < WavePvP.getInstance().getConfig().getInt("SpawnAltura") && (!KitManager.getPlayer(player.getName()).hasKit() && !KitManager2.getPlayer(player.getName()).haskit2()) && !WaveWarp.DUELS.hasPlayer(p.getName()) && !WaveWarp.LOBBY.hasPlayer(p.getName())  && !WaveWarp.ARENABUILD.hasPlayer(p.getName())  && !WaveWarp.FPS.hasPlayer(p.getName()) && !WaveWarp.LAVACHALLENGE.hasPlayer(p.getName()) && !WaveWarp.GLADIATOR.hasPlayer(p.getName()) && !recebeu.containsKey(player.getName()) && !AdminUtil.has(player.getName()) && !VanishUtil.has(player.getName())) {	  
		Items(player);	

		  ItemStack espada = new ItemBuilder("§fEspada", Material.STONE_SWORD).addFlags(ItemFlag.HIDE_ATTRIBUTES).addEnchant(Enchantment.SHARPNESS, 1)
					.nbt("cancel-drop")
					.toStack();
			player.getInventory().setItem(0, espada);
Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed nenhum kit! (Pulou do spawn) - 2");
						recebeu.put(player.getName(), true);
						}			
						else if (player.getLocation().getBlockY() < WavePvP.getInstance().getConfig().getInt("SpawnAltura") && (KitManager.getPlayer(player.getName()).hasKit() && !KitManager2.getPlayer(player.getName()).haskit2())  && !WaveWarp.DUELS.hasPlayer(p.getName()) && !WaveWarp.LOBBY.hasPlayer(p.getName()) && !WaveWarp.ARENABUILD.hasPlayer(p.getName()) && !WaveWarp.FPS.hasPlayer(p.getName()) && !WaveWarp.DUELS.hasPlayer(p.getName()) && !WaveWarp.GLADIATOR.hasPlayer(p.getName()) && !WaveWarp.LAVACHALLENGE.hasPlayer(p.getName())  && !recebeu.containsKey(player.getName()) && !AdminUtil.has(player.getName()) && !VanishUtil.has(player.getName())) {
							WaveKit2.findKit("PvP").ifPresent(kit -> {
								player.closeInventory();
								kit.send(player);
							});
	Items(player);
	recebeu.put(player.getName(), true);					
}
		
						else if (player.getLocation().getBlockY() < WavePvP.getInstance().getConfig().getInt("SpawnAltura") && (!KitManager.getPlayer(player.getName()).hasKit() && KitManager2.getPlayer(player.getName()).haskit2()) && !WaveWarp.ARENABUILD.hasPlayer(p.getName())  && !WaveWarp.FPS.hasPlayer(p.getName()) && !WaveWarp.DUELS.hasPlayer(p.getName())  && !WaveWarp.GLADIATOR.hasPlayer(p.getName()) && !WaveWarp.LAVACHALLENGE.hasPlayer(p.getName()) && !WaveWarp.LOBBY.hasPlayer(p.getName())  && !recebeu.containsKey(player.getName()) && !AdminUtil.has(player.getName()) && !VanishUtil.has(player.getName())) {
							
							WaveKit.findKit("PvP").ifPresent(kit -> {
								player.closeInventory();
								kit.send(player);
							});	
							Items(player);
							
							
							
							recebeu.put(player.getName(), true);
							 final ItemStack ItemMudado = player.getInventory().getItem(2);
							   player.getInventory().setItem(2, new ItemStack(Material.MUSHROOM_STEW));
					           player.getInventory().setHeldItemSlot(1);
					           player.getInventory().setItem(1, ItemMudado);
										Bukkit.getConsoleSender().sendMessage(player.getName() + " Escolheu apenas o kit secundário " + KitManager2.getPlayer(player.getName()).getkit2().getName());
						}
						else if (player.getLocation().getBlockY() < WavePvP.getInstance().getConfig().getInt("SpawnAltura") && (KitManager.getPlayer(player.getName()).hasKit() && KitManager2.getPlayer(player.getName()).haskit2())  && !WaveWarp.DUELS.hasPlayer(p.getName()) && !WaveWarp.FPS.hasPlayer(p.getName()) && !WaveWarp.ARENABUILD.hasPlayer(p.getName()) && !WaveWarp.GLADIATOR.hasPlayer(p.getName()) && !WaveWarp.LAVACHALLENGE.hasPlayer(p.getName()) && !WaveWarp.LOBBY.hasPlayer(p.getName()) && !recebeu.containsKey(player.getName()) && !AdminUtil.has(player.getName()) && !VanishUtil.has(player.getName())) {
		                Items(player);
		                
		                if (KitManager.getPlayer(player.getName()).hasKit(WaveKit.NEO) && KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.STOMPER)) {
		                	WaveWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você está com uma combinação de kits proibida e foi mandado de volta ao spawn!");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(WaveKit.STOMPER) && KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.PHANTOM)) {
		                	WaveWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você está com uma combinação de kits proibida e foi mandado de volta ao spawn!");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(WaveKit.PHANTOM) && KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.STOMPER)) {
		                	WaveWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você está com uma combinação de kits proibida e foi mandado de volta ao spawn!");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).getKit().getName() == KitManager2.getPlayer(player.getName()).getkit2().getName()) {
		                	WaveWarp.SPAWN.send(player);
		                	p.sendMessage(ChatColor.RED + "Você não pode usar dois kits do mesmo tipo ao mesmo tempo!");
		                	p.sendMessage(ChatColor.RED + "Você escolheu dois kits: " + KitManager.getPlayer(player.getName()).getKit().getName()); 
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(WaveKit.STOMPER) && KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.NEO)) {
		                	WaveWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você está com uma combinação de kits proibida e foi mandado de volta ao spawn!");
		                	return;
		                }
		                
		                if (KitManager.getPlayer(player.getName()).hasKit(WaveKit.STOMPER) && KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.KANGAROO)) {
		                	WaveWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você está com uma combinação de kits proibida e foi mandado de volta ao spawn!");
		                	return;
		                }
		           
		                if (KitManager.getPlayer(player.getName()).hasKit(WaveKit.GLADIATOR) && KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.NINJA)) {
		                	WaveWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você está com uma combinação de kits proibida e foi mandado de volta ao spawn!");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(WaveKit.NINJA) && KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.GLADIATOR)) {
		                	WaveWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você está com uma combinação de kits proibida e foi mandado de volta ao spawn!");
		                	return;
		                }
		                
		                if (KitManager.getPlayer(player.getName()).getKit().name() == KitManager2.getPlayer(player.getName()).getkit2().name() && KitManager.getPlayer(player.getName()).getKit() != WaveKit.PVP) {
		                	WaveWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.GOLD + "Você está com uma combinação de kits proibida e foi mandado de volta ao spawn!");
		                	return;
		                }
		               
		                if (KitManager.getPlayer(player.getName()).getKit().name() == KitManager2.getPlayer(player.getName()).getkit2().name() && KitManager.getPlayer(player.getName()).getKit() != WaveKit.PVP) {
		                	WaveWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.GOLD + "Você está com uma combinação de kits proibida e foi mandado de volta ao spawn!");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(WaveKit.ANCHOR) && KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.BOXER)) {
		                	WaveWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você está com uma combinação de kits proibida e foi mandado de volta ao spawn!");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(WaveKit.MAGMA) && KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.VIPER)) {
		                	WaveWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você está com uma combinação de kits proibida e foi mandado de volta ao spawn!");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(WaveKit.BOXER) && KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.ANCHOR)) {
		                	WaveWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você está com uma combinação de kits proibida e foi mandado de volta ao spawn!");
		                	return;
		                }
		                
		                if (KitManager.getPlayer(player.getName()).hasKit(WaveKit.KANGAROO) && KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.STOMPER)) {
		                	WaveWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você está com uma combinação de kits proibida e foi mandado de volta ao spawn!");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(WaveKit.STOMPER) && KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.NINJA)) {
		                	WaveWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você está com uma combinação de kits proibida e foi mandado de volta ao spawn!");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(WaveKit.METEOR) && KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.STOMPER)) {
		                	WaveWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você está com uma combinação de kits proibida e foi mandado de volta ao spawn!");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(WaveKit.METEOR) && KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.PHANTOM)) {
		                	WaveWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você está com uma combinação de kits proibida e foi mandado de volta ao spawn!");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(WaveKit.NINJA) && KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.STOMPER)) {
		                	WaveWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você está com uma combinação de kits proibida e foi mandado de volta ao spawn!");
		                	return;
		                }
		                
		                
		                if (KitManager.getPlayer(player.getName()).hasKit(WaveKit.HULK) && KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.GLADIATOR)) {
		                	WaveWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você está com uma combinação de kits proibida e foi mandado de volta ao spawn!");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(WaveKit.ANTISTOMPER) && KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.STOMPER)) {
		                	WaveWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você está com uma combinação de kits proibida e foi mandado de volta ao spawn!");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(WaveKit.STOMPER) && KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.ANTISTOMPER)) {
		                	WaveWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você está com uma combinação de kits proibida e foi mandado de volta ao spawn!");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(WaveKit.PHANTOM) && KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.STOMPER)) {
		                	WaveWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você está com uma combinação de kits proibida e foi mandado de volta ao spawn!");
		                	return;
		                }
		                if (KitManager.getPlayer(player.getName()).hasKit(WaveKit.PHANTOM) && KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.STOMPER)) {
		                	WaveWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você está com uma combinação de kits proibida e foi mandado de volta ao spawn!");
		                	return;
		                }
		                
		                
		               
		                if (KitManager.getPlayer(player.getName()).hasKit(WaveKit.FLASH) && KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.STOMPER)) {
		                	WaveWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você está com uma combinação de kits proibida e foi mandado de volta ao spawn!");
		                	return;
		                }
		                
		                if (KitManager.getPlayer(player.getName()).hasKit(WaveKit.FLASH) && KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.KANGAROO)) {
		                	WaveWarp.SPAWN.send(player);
		                	KitManager.getPlayer(player.getName()).removeKit();
		            		KitManager2.getPlayer(player.getName()).removekit2();
		                	p.sendMessage(ChatColor.RED + "Você está com uma combinação de kits proibida e foi mandado de volta ao spawn!");
		                	return;
		                }
		 					Bukkit.getConsoleSender().sendMessage(player.getName() + " escolheu o kit 1: " + KitManager.getPlayer(player.getName()).getKit().toString());
							Bukkit.getConsoleSender().sendMessage(player.getName() + " escolheu o kit 2: " + KitManager2.getPlayer(player.getName()).getkit2().toString());						
							recebeu.put(player.getName(), true);
		}
	}
	
	
	

public void Items(Player player) {
	player.setGameMode(GameMode.SURVIVAL);
	player.getInventory().clear();
	player.getInventory().setArmorContents(null);
	player.setAllowFlight(false);
	player.setFlying(false);
	player.getInventory().setItem(1 , new ItemStack(Material.MUSHROOM_STEW));
	player.getInventory().setItem(2 , new ItemStack(Material.MUSHROOM_STEW));
	player.getActivePotionEffects().forEach(potion -> player.removePotionEffect(potion.getType()));
	player.getInventory().setHeldItemSlot(0);
	/* 348 */  
	
	
	if (KitManager.getPlayer(player.getName()).hasKit(WaveKit.ARCHER)) {
		player.getInventory().setItem(1, new ItemBuilder("§aArco!", Material.BOW)
				.nbt("kit-handler", "arco")
				.nbt("cancel-drop")
				.toStack());
				player.getInventory().setItem(10, new ItemBuilder("§aArrow!", Material.ARROW)
						.nbt("kit-handler", "flecha").addEnchant(Enchantment.SHARPNESS, 1).addEnchant(Enchantment.POWER, 1).amount(20)
						.nbt("cancel-drop").addFlags(
								ItemFlag.HIDE_ENCHANTS)
						.toStack()
		);
				Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed archer kit!");
	}
	
	
				if (KitManager.getPlayer(player.getName()).hasKit( WaveKit.BOXER)) {
					Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed boxer kit!");
				}
				
				
					if (KitManager.getPlayer(player.getName()).hasKit( WaveKit.FISHERMAN)) {
					player.getInventory().setItem(1, new ItemBuilder("§aFisgar!", Material.FISHING_ROD)
							.nbt("kit-handler", "fisherman")
							.nbt("cancel-drop")
							.toStack()
					);
					Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed fisherman kit!");
					}
				
					
					if (KitManager.getPlayer(player.getName()).hasKit( WaveKit.PHANTOM)) {
						 player.getInventory().setItem(1, new ItemBuilder(Material.BOOK)
					                .displayName("§aPhantom")
					                .nbt("cancel-drop")
					                .nbt("kit-handler", "phantom")
					                .toStack()
					        );
						Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed PHANTOM kit!");
						}
					if (KitManager.getPlayer(player.getName()).hasKit(WaveKit.FLASH)) {
						 player.getInventory().setItem(1, new ItemBuilder("§aFlash!", Material.REDSTONE_TORCH)
					                .addEnchant(Enchantment.KNOCKBACK, 2).addFlags(ItemFlag.HIDE_ENCHANTS)
					                .nbt("cancel-drop").nbt("kit-handler", "flash")
					                .toStack());
							Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed flash kit!");
						}
					if (KitManager.getPlayer(player.getName()).hasKit(WaveKit.GRANDPA)) {
					player.getInventory().setItem(1, new ItemBuilder("§aGrandpa!", Material.STICK)
			                .addEnchant(Enchantment.KNOCKBACK, 2)
			                .nbt("cancel-drop")
			                .toStack()
			        );
					Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed grandpa kit!");
					}
				
					if (KitManager.getPlayer(player.getName()).hasKit( WaveKit.REAPER)) {
						player.getInventory().setItem(1, new ItemBuilder("§aCeifador", Material.WOODEN_HOE)
				                .addEnchant(Enchantment.KNOCKBACK, 1)
				        				.addFlags(ItemFlag.HIDE_ENCHANTS)
				                .nbt("cancel-drop")
				                .toStack()
				        );
						Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed reaper kit!");
						}

					if (KitManager.getPlayer(player.getName()).hasKit( WaveKit.METEOR)) {
						player.getInventory().setItem(1, new ItemBuilder("§aMeteor!", Material.FIRE_CHARGE)
				                .addEnchant(Enchantment.KNOCKBACK, 1)
				        				.addFlags(ItemFlag.HIDE_ENCHANTS)
				                .nbt("cancel-drop")
				                .toStack()
				        );
						Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed Meteoro kit!");
						}
					if (KitManager.getPlayer(player.getName()).hasKit( WaveKit.GLADIATOR)) {
						player.getInventory().setItem(1, new ItemBuilder("§bPuxar!", Material.IRON_BARS)
								.nbt("kit-handler", "glad")
								.nbt("cancel-drop")
								.toStack()
						);
						Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed gladiator kit!");
						}

					if (KitManager.getPlayer(player.getName()).hasKit( WaveKit.PYRO)) {
						player.getInventory().setItem(1, new ItemBuilder("§bAtirar!", Material.FIRE_CHARGE)
								.nbt("kit-handler", "pyro")
								.nbt("cancel-drop")
								.toStack()
						);
						Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed pyro kit!");
						}
	
					if (KitManager.getPlayer(player.getName()).hasKit( WaveKit.KANGAROO)) {
						player.getInventory().setItem(1, new ItemBuilder("§aPular!", Material.FIREWORK_ROCKET)
								.nbt("kit-handler", "kangaroo")
								.nbt("cancel-drop")
								.toStack()
						);
						Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed kangaroo kit!");
						}
					if (KitManager.getPlayer(player.getName()).hasKit( WaveKit.MILKMAN)) {
						player.getInventory().setItem(1, new ItemBuilder(Material.MILK_BUCKET)
				                .displayName("§fMilk Bucket")
				                .nbt("cancel-drop")
				                .nbt("kit-handler", "milkman")
				                .toStack()
				        );
						Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed milkman kit!");
						}
					if (KitManager.getPlayer(player.getName()).hasKit( WaveKit.MONK)) {
					player.getInventory().setItem(1, new ItemBuilder("§eEmbaralhar!", Material.BLAZE_ROD)
							.nbt("kit-handler", "monk")
							.nbt("cancel-drop")
							.toStack()
					);
					Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed monk kit!");
					}
					
					
					if (KitManager.getPlayer(player.getName()).hasKit( WaveKit.SWITCHER)) {
						player.getInventory().setItem(1, new ItemBuilder(Material.SNOWBALL)
			                     .amount(2)
			                     .nbt("cancel-drop").nbt("kit-handler", "switcher")
			                     .toStack());
						Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed switcher kit!");
					}
					if (KitManager.getPlayer(player.getName()).hasKit(WaveKit.THOR)) {
					player.getInventory().setItem(1, new ItemBuilder("§eCaboom!", Material.GOLDEN_AXE)
							.nbt("cancel-drop")
							.nbt("kit-handler", "thor")
							.toStack()
					);
					Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed thor kit!");
					}
					
						if (KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.ARCHER)) {
							player.getInventory().setItem(2, new ItemBuilder("§aArco!", Material.BOW)
									.nbt("kit-handler", "arco")
									.nbt("cancel-drop")
									.toStack());
									player.getInventory().setItem(10, new ItemBuilder("§aFlecha!", Material.ARROW)
											.nbt("kit-handler", "flecha").addEnchant(Enchantment.SHARPNESS, 1)
											.nbt("cancel-drop").amount(20).addFlags(
													ItemFlag.HIDE_ENCHANTS)
											.toStack()
							);
						}
							
							
							if (KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.FISHERMAN)) {
								player.getInventory().setItem(2, new ItemBuilder("§aFisgar!", Material.FISHING_ROD)
										.nbt("kit-handler", "fisherman")
										.nbt("cancel-drop")
										.toStack()
								);
							}
							if (KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.PHANTOM)) {
								player.getInventory().setItem(2, new ItemBuilder(Material.BOOK)
						                .displayName("§aPhantom")
						                .nbt("cancel-drop")
						                .nbt("kit-handler", "phantom")
						                .toStack()
						        );
							Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed PHANTOM kit!");
							}
								if (KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.GLADIATOR)) {
									player.getInventory().setItem(2, new ItemBuilder("§bPuxar!", Material.IRON_BARS)
											.nbt("kit-handler", "glad")
											.nbt("cancel-drop")
											.toStack()
									);
								}
								
								if (KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.KANGAROO)) {
									player.getInventory().setItem(2, new ItemBuilder("§aPular!", Material.FIREWORK_ROCKET)
											.nbt("kit-handler", "kangaroo")
											.nbt("cancel-drop")
											.toStack()
									);
								}
								if (KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.MONK)) {
									player.getInventory().setItem(2, new ItemBuilder("§eEmbaralhar!", Material.BLAZE_ROD)
											.nbt("kit-handler", "monk")
											.nbt("cancel-drop")
											.toStack()
									);
									}
								
								if (KitManager.getPlayer(player.getName()).hasKit(WaveKit.PVP) && KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.PVP)) {

									  ItemStack espada = new ItemBuilder("§fEspada", Material.STONE_SWORD).addFlags(ItemFlag.HIDE_ATTRIBUTES)
												.nbt("cancel-drop").addEnchant(Enchantment.SHARPNESS, 1)
												.toStack();
										player.getInventory().setItem(0, espada);
								} else {

									  ItemStack espada = new ItemBuilder("§fEspada", Material.STONE_SWORD).addFlags(ItemFlag.HIDE_ATTRIBUTES)
												.nbt("cancel-drop")
												.toStack();
										player.getInventory().setItem(0, espada);
									}
								
									if (KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.SWITCHER)) {
									 player.getInventory().setItem(2, new ItemBuilder(Material.SNOWBALL)
								                .amount(2)
								                .nbt("cancel-drop").nbt("kit-handler", "switcher")
								                .toStack()
								        );
									}
									if (KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.REAPER)) {
										 player.getInventory().setItem(2, new ItemBuilder(Material.WOODEN_HOE)
									     
									                .nbt("cancel-drop").nbt("kit-handler", "reaper")
									                .toStack()
									        );
										}
									if (KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.THOR)) {
										player.getInventory().setItem(2, new ItemBuilder("§eCaboom!", Material.GOLDEN_AXE)
												.nbt("cancel-drop")
												.nbt("kit-handler", "thor")
												.toStack()
										);
										}
								
										

	
	 if (!KitManager.getPlayer(player.getName()).hasKit(WaveKit.NEO)) {
		 Habilidade.removeAbility(player);
		}
	
	player.getInventory().setItem(8, new ItemBuilder("§9Bússola", Material.COMPASS)
			.nbt("kit-handler", "search-players")
			.nbt("cancel-drop")
			.toStack());
	   
	
	player.getInventory().setItem(13, new ItemStack(Material.BOWL, 32));
	  if (PlayerCache.cacheSoupType.get(player.getName()) == "mush") {
	player.getInventory().setItem(14, new ItemStack(Material.RED_MUSHROOM, 32));
	player.getInventory().setItem(15, new ItemStack(Material.BROWN_MUSHROOM, 32));
	  }
	else   if (PlayerCache.cacheSoupType.get(player.getName()) == "cocoa") {
		player.getInventory().setItem(14, new ItemStack(Material.COCOA_BEANS, 32));
	}
	else {

		player.getInventory().setItem(14, new ItemStack(Material.RED_MUSHROOM, 32));
		player.getInventory().setItem(15, new ItemStack(Material.BROWN_MUSHROOM, 32));
	}
	
player.getInventory().setItem(3 , new ItemStack(Material.MUSHROOM_STEW));
		player.getInventory().setItem(4 , new ItemStack(Material.MUSHROOM_STEW));
		player.getInventory().setItem(5 , new ItemStack(Material.MUSHROOM_STEW));
		player.getInventory().setItem(6 , new ItemStack(Material.MUSHROOM_STEW));
		player.getInventory().setItem(7 , new ItemStack(Material.MUSHROOM_STEW));
		player.getInventory().setItem(9 , new ItemStack(Material.MUSHROOM_STEW));
		if (!KitManager.getPlayer(player.getName()).hasKit(WaveKit.ARCHER) && !KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.ARCHER)) {
		player.getInventory().setItem(10 , new ItemStack(Material.MUSHROOM_STEW));
		}
		
		if (KitManager.getPlayer(player.getName()).hasKit( WaveKit.PVP) && KitManager2.getPlayer(player.getName()).haskit2( WaveKit2.PVP)) {
			ItemStack espada = new ItemBuilder("§fEspada", Material.STONE_SWORD).addFlags(ItemFlag.HIDE_ATTRIBUTES).
					addEnchant(Enchantment.SHARPNESS, 1)
					.nbt("cancel-drop")
					.toStack();
			player.getInventory().setItem(0, espada);
			Bukkit.getConsoleSender().sendMessage(player.getName() + " Choosed pvp kit!");
			}
		player.getInventory().setItem(11 , new ItemStack(Material.MUSHROOM_STEW));
		player.getInventory().setItem(12 , new ItemStack(Material.MUSHROOM_STEW));
		player.getInventory().setItem(16 , new ItemStack(Material.MUSHROOM_STEW));
		player.getInventory().setItem(17 , new ItemStack(Material.MUSHROOM_STEW));
		player.getInventory().setItem(18 , new ItemStack(Material.MUSHROOM_STEW));
		player.getInventory().setItem(19 , new ItemStack(Material.MUSHROOM_STEW));
		player.getInventory().setItem(20 , new ItemStack(Material.MUSHROOM_STEW));
		player.getInventory().setItem(21 , new ItemStack(Material.MUSHROOM_STEW));
		player.getInventory().setItem(22 , new ItemStack(Material.MUSHROOM_STEW));
		player.getInventory().setItem(23 , new ItemStack(Material.MUSHROOM_STEW));
		player.getInventory().setItem(24 , new ItemStack(Material.MUSHROOM_STEW));
		player.getInventory().setItem(25 , new ItemStack(Material.MUSHROOM_STEW));
		player.getInventory().setItem(26 , new ItemStack(Material.MUSHROOM_STEW));
		player.getInventory().setItem(27 , new ItemStack(Material.MUSHROOM_STEW));
		player.getInventory().setItem(28 , new ItemStack(Material.MUSHROOM_STEW));
		player.getInventory().setItem(29 , new ItemStack(Material.MUSHROOM_STEW));
		player.getInventory().setItem(30 , new ItemStack(Material.MUSHROOM_STEW));
		player.getInventory().setItem(31 , new ItemStack(Material.MUSHROOM_STEW));
		player.getInventory().setItem(32 , new ItemStack(Material.MUSHROOM_STEW));
		player.getInventory().setItem(33 , new ItemStack(Material.MUSHROOM_STEW));
		player.getInventory().setItem(34 , new ItemStack(Material.MUSHROOM_STEW));
		player.getInventory().setItem(35 , new ItemStack(Material.MUSHROOM_STEW));
		player.updateInventory();

}

@EventHandler(priority = EventPriority.HIGHEST)
public void RemoverDanoEspomka(EntityDamageEvent e) 
{
	if (e.getCause() == EntityDamageEvent.DamageCause.FALL) { 
	{
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player p = (Player)e.getEntity();
		Block block = p.getLocation().getBlock().getRelative(0, -1, 0);
		if (block.getType() == Material.SPONGE) {
			e.setCancelled(true);
			return;
		}
		else if (!caiu.containsKey(p.getName())) {
			e.setCancelled(true);
			return;
		}
		else {
			e.setCancelled(e.isCancelled());
			return;
	}
	}
	}
}
	 
	   @EventHandler
		public void RemoverDano(EntityDamageEvent e) 
		{
		   if (!(e.getEntity() instanceof Player)) {
	           return;
	       }
			Player p = (Player) e.getEntity();
			if (e.getCause() == EntityDamageEvent.DamageCause.FALL && fall.contains(p.getName())) 
			{
				fall.remove(p.getName());
				e.setCancelled(true);
				EntityDamageEvent event = new EntityDamageEvent(p, EntityDamageEvent.DamageCause.FALL, 1.0F);
				p.setLastDamageCause(event);
				Bukkit.getServer().getPluginManager().callEvent(event);
				if (!caiu.containsKey(p.getName())) {
	caiu.put(p.getName(), true);
				}
			}
			else if(e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK)
			{
				if (e.isCancelled()) {
					return;
				}
				if (!caiu.containsKey(p.getName())) {
					caiu.put(p.getName(), true);
				}
				fall.remove(p.getName());
			}
		}

	   @EventHandler
		  public void onKill(PlayerDeathEvent e) {
		      Player k = (Player)e.getEntity().getKiller();
		      Player p = (Player)e.getEntity();
		      if (p == null || k == null) {
		      } 
}
}
	   
	