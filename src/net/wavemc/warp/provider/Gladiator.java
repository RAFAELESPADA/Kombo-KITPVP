package net.wavemc.warp.provider;



import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;
import net.wavemc.core.bukkit.item.ItemBuilder;
import net.wavemc.core.util.WaveCooldown;
import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.event.WavePlayerDeathEvent;
import net.wavemc.pvp.inventory.Modo;
import net.wavemc.pvp.inventory.Servidores;
import net.wavemc.pvp.inventory.StatusGUI;
import net.wavemc.pvp.kit.KitManager;
import net.wavemc.pvp.kit.KitManager2;
import net.wavemc.pvp.kit.provider.GladiatorListener2;
import net.wavemc.pvp.warp.WaveWarp;
import net.wavemc.pvp.warp.WarpDuoBattleHandle3;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Gladiator extends WarpDuoBattleHandle3 {
	private ItemStack machado = new ItemBuilder(Material.STONE_AXE).toStack(),
			picareta = new ItemBuilder(Material.STONE_PICKAXE).toStack(),
			pote = new ItemBuilder(Material.BOWL).amount(64).toStack(),
			espada = new ItemBuilder(Material.DIAMOND_SWORD).addEnchant(Enchantment.SHARPNESS, 1).toStack(),
			muro = new ItemBuilder(Material.COBBLESTONE_WALL).amount(64).toStack(),

			pedra = new ItemBuilder(Material.COBBLESTONE).amount(64).toStack(),
			sopa = new ItemBuilder(Material.MUSHROOM_STEW).toStack(),
			cocoa = new ItemBuilder(Material.COCOA_BEANS).amount(64).toStack(),

					cocoa2 = new ItemBuilder(Material.COCOA_BEANS).amount(64).toStack(),
			madeira = new ItemBuilder(Material.OAK_PLANKS).amount(64).toStack(),
			agua = new ItemBuilder(Material.WATER_BUCKET).toStack(),
			lava = new ItemBuilder(Material.LAVA_BUCKET).toStack(),
			capacete = new ItemBuilder(Material.IRON_HELMET).toStack(),
			peitoral = new ItemBuilder(Material.IRON_CHESTPLATE).toStack(),
			calça = new ItemBuilder(Material.IRON_LEGGINGS).toStack(),
			bota = new ItemBuilder(Material.IRON_BOOTS).toStack();
	public static List<Location> blocksV = new ArrayList<>();
			private Integer[] soupSlots = { 4, 5, 6, 7, 29, 30, 31, 32, 33, 34, 35};
			private Integer[] cocoaSlots = { 14, 15, 16};
			private Integer[] cocoaSlots2 = { 23, 24, 25 };
	public Gladiator() {
		super("gladiator1", "gladiator2");
		new BukkitRunnable() {
			@Override
			public void run() {
				if (fastChallenge.size() < 2) {
					return;
				}
				
				Player p1 = fastChallenge.get(0);
				Player p2 = fastChallenge.get(1);
				
				if (p1 == null || p2 == null) {
					fastChallenge.remove(p1);
					fastChallenge.remove(p2);
					return;
				}
				else if (p1 == p2) {
                	cancel();
                	fastChallenge.remove(p1);
                	p1.sendMessage(ChatColor.RED + "A procura falhou! Tente novamente.");
                	return;
                }
				else if (Sumo.isNpc(p2) || Sumo.isNpc(p1)) {
                	cancel();
            	fastChallenge.remove(p1);

            	fastChallenge.remove(p2);
            	p1.sendMessage(ChatColor.DARK_RED + "A procura falhou! Tente novamente.");
            	return;
            }
				
				startBattle(p1, p2);
				 GladiatorListener2.combateGlad.put(p1, p2);
	             GladiatorListener2.combateGlad.put(p2, p1);
			}
		}.runTaskTimer(WavePvP.getInstance(), 0, 2 * 20L);
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();

		Optional<Player> targetOptional;
		if (!(targetOptional = findOpponent(player)).isPresent()) {
			return;
		}

		Player target = targetOptional.get();
		findOpponent(target);
		finalizeBattle(target);
		GladiatorListener2.resetGladiatorListenerByQuit(target, player);
		WaveWarp.DUELS.send(target);
		target.sendMessage("§2Seu oponente deslogou e a batalha acabou automaticamente.");

	}

	               
	            
	 @EventHandler(priority = EventPriority.HIGHEST)
	    public void BlockPlaceV(BlockPlaceEvent event) {
	     
	        
	        Player player = event.getPlayer();

	        if (!WaveWarp.DUELS.hasPlayer(player.getName())) return;
	        blocksV.add(event.getBlock().getLocation());
	    }    

	 @EventHandler(priority = EventPriority.HIGHEST)
	    public void BlockPlaceV(PlayerBucketEmptyEvent event) {
	     
	        
	        Player player = event.getPlayer();

	        if (!WaveWarp.DUELS.hasPlayer(player.getName())) return;
	        blocksV.add(event.getBlockClicked().getRelative(event.getBlockFace()).getLocation());
	    } 
	        

	
	
	


	@EventHandler
	public void B(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		if (event.getPlayer().getItemInHand() == null) {
			return;
		}
		if (event.getPlayer().getItemInHand().getItemMeta() == null) {
			return;
		}
		if (event.getPlayer().getItemInHand().getItemMeta().getDisplayName() == null) {
			return;
		}
	Player target = (Player)event.getRightClicked();
		if (event.getPlayer().getItemInHand().hasItemMeta() && event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals("§bDesafiar §7(Clique)") && WaveWarp.DUELS.hasPlayer(player.getName()) && event.getRightClicked() != null && event.getPlayer().getItemInHand().getItemMeta().getDisplayName() != null) {
			if (WaveCooldown.inCooldown(player.getName(), "1v1g-challenge-" + target.getName()) || WaveCooldown.inCooldown(player.getName(), "1v1-challenge-" + target.getName()) || WaveCooldown.inCooldown(player.getName(), "1v1s-challenge-" + target.getName())) {
				player.sendMessage("§eVocê já convidou esse jogador recentemente");
				return;
			}
		Modo.open(player, (Player)event.getRightClicked());
		Duels.duels.put(player, (Player)event.getRightClicked());
        if (Duels.glad.containsKey(player)) {
            	startBattle(player , (Player)event.getRightClicked());
        }
		WaveBukkit.getExecutorService().submit(() -> {
			new BukkitRunnable() {
				@Override
				public void run() {
					Duels.duels.remove(player);
					player.closeInventory();
				}}.runTaskLater(WavePvP.getInstance(), 600 * 1L);
		});
	
	}
	}
		@EventHandler
		public void Bc(PlayerInteractEvent event) {
			Player player = event.getPlayer();
			if (event.getItem() == null) {
				return;
			}
			if (event.getItem().getType().equals(Material.MUSHROOM_STEW)) {
				return;
			}
			if (event.getItem().getItemMeta() == null) {
				return;
			}
			if (event.getItem().getItemMeta().getDisplayName() == null) {
				return;
			}
			if (event.getItem().hasItemMeta() && event.getItem().getItemMeta().getDisplayName().equals("§bRetornar §7(Clique)") && (WaveWarp.FPS.hasPlayer(player.getName()) || WaveWarp.DUELS.hasPlayer(player.getName()) || WaveWarp.GLADIATOR.hasPlayer(player.getName())) && event.hasItem() && event.getItem() != null && event.getItem().getItemMeta().getDisplayName() != null) {
				 if (Sumo.fastChallenge.contains(player)) {
					 Sumo.fastChallenge.remove(player);
					 player.sendMessage(ChatColor.GREEN + "Você saiu da fila da sumo");
				 }
				 if (OneVsOne.fastChallenge.contains(player)) {
					 OneVsOne.fastChallenge.remove(player);
					 player.sendMessage(ChatColor.GREEN + "Você saiu da fila da 1v1");
				 }
				 if (Gladiator.fastChallenge.contains(player)) {
					 Gladiator.fastChallenge.remove(player);
					 player.sendMessage(ChatColor.DARK_RED + "Você saiu da fila do gladiator");
				 }
			WaveWarp.LOBBY.send(player , true);
		}
	}
		@EventHandler
		public void BcV(PlayerInteractEvent event) {
			Player player = event.getPlayer();
			if (event.getItem() == null) {
				return;
			}
			if (event.getItem().getType().equals(Material.MUSHROOM_STEW)) {
				return;
			}
			if (event.getItem().getItemMeta() == null) {
				return;
			}
			if (event.getItem().getItemMeta().getDisplayName() == null) {
				return;
			}
			if (event.getItem().hasItemMeta() && event.getItem().getItemMeta().getDisplayName().equals("§bModos de jogo §7(Clique)") && (WaveWarp.LOBBY.hasPlayer(player.getName())) && event.hasItem() && event.getItem() != null && event.getItem().getItemMeta().getDisplayName() != null) {
				Servidores.open(player);
		}
		}
			@EventHandler
			public void BghcV(PlayerInteractEvent event) {
				Player player = event.getPlayer();
				if (event.getItem() == null) {
					return;
				}
				if (event.getItem().getType().equals(Material.MUSHROOM_STEW)) {
					return;
				}
				if (event.getItem().getItemMeta() == null) {
					return;
				}
				if (event.getItem().getItemMeta().getDisplayName() == null) {
					return;
				}
				if (event.getItem().hasItemMeta() && event.getItem().getItemMeta().getDisplayName().equals("§aSeu perfil §7(Clique)") && (WaveWarp.LOBBY.hasPlayer(player.getName())) && event.hasItem() && event.getItem() != null && event.getItem().getItemMeta().getDisplayName() != null) {
					StatusGUI.openGUI(player, player);
			}
	}
	@EventHandler
	public void onInteractEntity(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		
		if (!(event.getRightClicked() instanceof Player)
				|| !WaveWarp.GLADIATOR.hasPlayer(player.getName()) 
				|| !WaveWarp.GLADIATOR.hasPlayer(event.getRightClicked().getName())
				|| player.getItemInHand() == null
				|| !ItemBuilder.has(player.getItemInHand(), "1v1g", "challenge")) {
			return;
		}
		event.setCancelled(true);
		Player target = (Player) event.getRightClicked();
		
		if (findOpponent(target).isPresent()) {
			player.sendMessage("§c§lGLAD §cEsse jogador já está lutando");
			return;
		}
		
		if (WaveCooldown.inCooldown(target.getName(), "1v1g-challenge-" + player.getName())) {
			startBattle(player, target);
			 GladiatorListener2.combateGlad.put(player, target);
             GladiatorListener2.combateGlad.put(target, player);
			return;
		}
		
		
		
		WaveCooldown.create(player.getName(), "1v1g-challenge-" + target.getName(), TimeUnit.SECONDS, 15);
		target.sendMessage("§e§lGLAD §eVocê foi desafiado por " + player.getName() + " para uma luta");
		player.sendMessage("§6§lGLAD §6Você foi desafiado por " + target.getName() + " para uma luta");
	}
	 public static void clearBlocks(Player p) {
	        blocksV.forEach(blockLoc -> {
	            for (int x =blockLoc.getBlockX() + 7; x >= blockLoc.getBlockX() - 7; x--) {
	                for (int z = blockLoc.getBlockZ() + 7; z >= blockLoc.getBlockZ() - 7; z--) {
	                	blockLoc.getWorld().getBlockAt(x, blockLoc.getBlockY(), z).setType(Material.AIR);
	                }
	            if (blockLoc.getBlock().getType() != Material.AIR) {
	                blockLoc.getBlock().setType(Material.AIR);
	            }
	        }; 
	        });
	 
	        
	       blocksV.clear();
	    }

	@EventHandler
	public void onDeath(WavePlayerDeathEvent event) {
		if (!WaveWarp.DUELS.hasPlayer(event.getPlayer().getName()) || !findOpponent(event.getPlayer()).isPresent()) {
			return;
		}
		Player loser = event.getPlayer();
		Player winner = findOpponent(loser).isPresent() ? findOpponent(loser).get() : event.getKiller();
		finalizeBattle(winner);
		GladiatorListener2.resetGladiatorListenerByGlad(winner , loser);
		Random random = new Random();
		WavePlayer loserWavePlayer = WaveBukkit.getInstance().getPlayerManager().getPlayer(loser.getName());
		WavePlayer victimWavePlayer = WaveBukkit.getInstance().getPlayerManager().getPlayer(loser.getName());
		int loserWithdrawnCoins = random.nextInt(20 + 1 - 5) + 5;
		loserWavePlayer.getPvp().adddeathsX1(1);
		loserWavePlayer.getPvp().setKillstreak(0);
		
		loser.sendMessage("§cVocê perdeu a batalha contra " + winner.getName() + "§c.");
		
		if ((victimWavePlayer.getPvp().getXp() - 8) >= 0) {
			victimWavePlayer.getPvp().setXp(victimWavePlayer.getPvp().getXp() - 8);
			loser.sendMessage("§c§l[-] §c8 XP");
		}else {
			victimWavePlayer.getPvp().setXp(0);
			loser.sendMessage("§c§l[-] " + victimWavePlayer.getPvp().getXp() + " XP");
		}
		 clearBlocks(winner);
	WaveBukkit.getInstance().getPlayerManager().getController().save(loserWavePlayer);
		
		new BukkitRunnable() {
			@Override
			public void run() {
				WaveWarp.DUELS.send(loser, true);
				WaveWarp.DUELS.send(winner, true);
			}
		}.runTaskLater(WavePvP.getInstance(), 10);
		winner.setHealth(winner.getMaxHealth());
	GladiatorListener2.combateGlad.remove(winner);
	GladiatorListener2.combateGlad.remove(loser);
	winner.getInventory().clear();
		winner.sendMessage("§aVocê venceu a batalha contra " + loser.getName() + " §7(" + (event.isValidKill() ? "Conta" : "Não conta") + ")");

		if (event.isValidKill()) {
			WavePlayer winnerHelixPlayer = WaveBukkit.getInstance().getPlayerManager().getPlayer(winner.getName());
			int winnerAddCoins = random.nextInt(20 + 1 - 10) + 10;
			winnerHelixPlayer.getPvp().addKills(1);
			victimWavePlayer.getPvp().addDeaths(1);
			winnerHelixPlayer.getPvp().addKillstreak(1);
			winnerHelixPlayer.getPvp().addwinsX1(1);
			winnerHelixPlayer.getPvp().addWinstreakX1(1);
			winnerHelixPlayer.getPvp().addCoins(winnerAddCoins);
			winnerHelixPlayer.getPvp().setXp(winnerHelixPlayer.getPvp().getXp() + 10);
			winner.sendMessage("§6§l[+] §6" + winnerAddCoins + " coins");
			winner.sendMessage("§6§l[+] §a10XP");
			WaveBukkit.getInstance().getPlayerManager().getController().save(winnerHelixPlayer);
		}
	}
	@EventHandler
	public void onDeath2(WavePlayerDeathEvent event) {
		if (!WaveWarp.GLADIATOR.hasPlayer(event.getPlayer().getName()) || !findOpponent(event.getPlayer()).isPresent()) {
			return;
		}
		Player loser = event.getPlayer();
		Player winner = findOpponent(loser).isPresent() ? findOpponent(loser).get() : event.getKiller();
		finalizeBattle(winner);
		GladiatorListener2.resetGladiatorListenerByGlad(winner , loser);
		Random random = new Random();
		WavePlayer loserWavePlayer = WaveBukkit.getInstance().getPlayerManager().getPlayer(loser.getName());
		WavePlayer victimWavePlayer = WaveBukkit.getInstance().getPlayerManager().getPlayer(loser.getName());
		int loserWithdrawnCoins = random.nextInt(20 + 1 - 5) + 5;
		loserWavePlayer.getPvp().adddeathsX1(1);
		loserWavePlayer.getPvp().setKillstreak(0);
		loserWavePlayer.getPvp().setWinstreakx1(0);
		
		loser.sendMessage("§cVocê perdeu a batalha contra " + winner.getName() + "§c.");

		loser.spigot().respawn();
		if ((victimWavePlayer.getPvp().getXp() - 8) >= 0) {
			victimWavePlayer.getPvp().setXp(victimWavePlayer.getPvp().getXp() - 8);
			loser.sendMessage("§c§l[-] §c8 XP");
		}else {
			victimWavePlayer.getPvp().setXp(0);
			loser.sendMessage("§c§l[-] " + victimWavePlayer.getPvp().getXp() + " XP");
		}
		 clearBlocks(winner);
	WaveBukkit.getInstance().getPlayerManager().getController().save(loserWavePlayer);
		
		new BukkitRunnable() {
			@Override
			public void run() {
				loser.spigot().respawn();
				WaveWarp.GLADIATOR.send(loser, true);
				WaveWarp.GLADIATOR.send(winner, true);
			}
		}.runTaskLater(WavePvP.getInstance(), 10);
		winner.setHealth(winner.getMaxHealth());
	GladiatorListener2.combateGlad.remove(winner);
	GladiatorListener2.combateGlad.remove(loser);
	winner.getInventory().clear();
		winner.sendMessage("§aVocê venceu a batalha contra " + loser.getName() + " §7(" + (event.isValidKill() ? "Conta" : "Não conta") + ")");

		if (event.isValidKill()) {
			WavePlayer winnerHelixPlayer = WaveBukkit.getInstance().getPlayerManager().getPlayer(winner.getName());
			int winnerAddCoins = random.nextInt(20 + 1 - 10) + 10;
			winnerHelixPlayer.getPvp().addKills(1);
			victimWavePlayer.getPvp().addDeaths(1);
			winnerHelixPlayer.getPvp().addKillstreak(1);
			winnerHelixPlayer.getPvp().addwinsX1(1);
			winnerHelixPlayer.getPvp().addWinstreakX1(1);
			winnerHelixPlayer.getPvp().addCoins(winnerAddCoins);
			winnerHelixPlayer.getPvp().setXp(winnerHelixPlayer.getPvp().getXp() + 10);
			winner.sendMessage("§6§l[+] §6" + winnerAddCoins + " coins");
			winner.sendMessage("§6§l[+] §a10XP");
			WaveBukkit.getInstance().getPlayerManager().getController().save(winnerHelixPlayer);
		}
	}
	
	
	
	public void setGladInventory(Player player) {
		player.setGameMode(GameMode.SURVIVAL);
		
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);

		player.getInventory().setItem(0, espada);
		player.getInventory().setItem(1, agua);
		player.getInventory().setItem(2, lava);
		player.getInventory().setItem(3, madeira);
		player.getInventory().setItem(8, muro);
		player.getInventory().setItem(9, pedra);
		player.getInventory().setItem(10, pedra);
		player.getInventory().setItem(11, pedra);
		player.getInventory().setItem(12, pedra);
		player.getInventory().setItem(17, machado);
		player.getInventory().setItem(18, capacete);
		player.getInventory().setItem(19, peitoral);
		player.getInventory().setItem(20, calça);
		player.getInventory().setItem(21, bota);
		player.getInventory().setItem(26, picareta);
		player.getInventory().setItem(27, lava);
		player.getInventory().setItem(28, lava);

		player.getInventory().setHelmet(capacete);
		player.getInventory().setChestplate(peitoral);
		player.getInventory().setLeggings(calça);
		player.getInventory().setBoots(bota);
		
		player.getInventory().setItem(13, pote);
		player.getInventory().setItem(22, pote);
		
		for (Integer slot : soupSlots) {
			 player.getInventory().setItem(slot, sopa);
		}
		
		for (Integer slot : cocoaSlots) {
		 	 player.getInventory().setItem(slot, cocoa);
		}
		for (Integer slot : cocoaSlots2) {
		 	 player.getInventory().setItem(slot, cocoa2);
		}
		player.updateInventory();
	}

	@Override
	public void sendBattleItems(Player player) {
		super.sendBattleItems(player);
		
		setGladInventory(player);
		}


}