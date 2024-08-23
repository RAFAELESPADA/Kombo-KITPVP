package net.wavemc.warp.provider;

import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;
import net.wavemc.core.bukkit.item.ItemBuilder;
import net.wavemc.core.util.WaveCooldown;
import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.event.WavePlayerDeathEvent;
import net.wavemc.pvp.inventory.Modo;
import net.wavemc.pvp.kit.KitManager;
import net.wavemc.pvp.kit.KitManager2;
import net.wavemc.pvp.warp.WaveWarp;
import net.wavemc.pvp.warp.WarpDuoBattleHandle;
import net.wavemc.pvp.warp.WarpDuoBattleHandle2;
import net.wavemc.pvp.warp.WarpDuoBattleHandle3;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class OneVsOne extends WarpDuoBattleHandle {
	
	public OneVsOne() {
		super("one_vs_one_pos1", "one_vs_one_pos2", "one_vs_one_pos_2_1", "one_vs_one_pos2_2", "one_vs_one_pos_3_1", "one_vs_one_pos3_2");
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

		WaveWarp.DUELS.send(target);
		target.sendMessage("§2Seu oponente deslogou e a partida foi finalizada.");

	}
	  @EventHandler
	    public void craftItem(PrepareItemCraftEvent e) {
		  if (e.getRecipe() == null) {
    	    	return;
    	    }
	        if (e.getRecipe().getResult() == null) {
    	    	return;
    	    }
	        Material itemType = e.getRecipe().getResult().getType();
	        
	        if(itemType==Material.BLAZE_POWDER) {
	            e.getInventory().setResult(new ItemStack(Material.AIR));
	            for(HumanEntity he:e.getViewers()) {
	                if(he instanceof Player) {
	                	if (WaveWarp.DUELS.hasPlayer(he.getName())) {
	                    ((Player)he).sendMessage(ChatColor.RED+"Não crafte isso!");
	                }
	                }
	            }
	        }
	                }

	          	  @EventHandler
	          	    public void craftgItem(PrepareItemCraftEvent e) {
	          		if (e.getRecipe() == null) {
	          	    	return;
	          	    }
	          		if (e.getRecipe().getResult() == null) {
	        	    	return;
	        	    }
	          	        Material itemType = e.getRecipe().getResult().getType();
	          	    
	          	  
	          	        if(itemType==Material.OAK_BOAT) {
	          	            e.getInventory().setResult(new ItemStack(Material.AIR));
	          	            for(HumanEntity he:e.getViewers()) {
	          	                if(he instanceof Player) {
	          	                	if (WaveWarp.DUELS.hasPlayer(he.getName())) {
	          	                    ((Player)he).sendMessage(ChatColor.RED+"Não crafte isso!");
	          	                }
	          	                }
	            }
	        }
	        }
		
		@EventHandler
		public void onInteractv3(EntityDamageByEntityEvent event) {
			if (!(event.getEntity() instanceof Player)) {
				return;
			}
			Player player =  (Player)event.getEntity();
			if (WaveWarp.LAVACHALLENGE.hasPlayer(player.getName())) {
				event.setCancelled(true);
			}
			if (WaveWarp.LAVACHALLENGE.hasPlayer(event.getDamager().getName())) {
				event.setCancelled(true);
			}
		}
	
		@EventHandler
		public void onInteract3(EntityDamageByEntityEvent event) {
			if (!(event.getEntity() instanceof Player)) {
				return;
			}
			Player player =  (Player)event.getEntity();
			if (Duels.protector.containsKey(player.getName())) {
				event.setCancelled(true);
			}
	}
		@EventHandler
		public void onInteract3(EntityDamageEvent event) {
			if (!(event.getEntity() instanceof Player)) {
				return;
			}
			Player player =  (Player)event.getEntity();
			if (Duels.protector.containsKey(player.getName())) {
				event.setCancelled(true);
			}
	}
	


	@EventHandler
	public void C(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		if (event.getPlayer().getItemInHand() == null) {
			return;
		}
		if (event.getPlayer().getItemInHand().hasItemMeta() && event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals("§bDesafiar §7(Clique)") && WaveWarp.DUELS.hasPlayer(player.getName()) && event.getRightClicked() != null && event.getPlayer().getItemInHand().getItemMeta().getDisplayName() != null) {
        if (Duels.c.containsKey(player)) {
            	startBattle(player , (Player)event.getRightClicked());
        }
		}
	}
	@EventHandler
	public void onInteractEntity(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		
		if (!(event.getRightClicked() instanceof Player)
				|| !WaveWarp.DUELS.hasPlayer(player.getName()) 
				|| !WaveWarp.DUELS.hasPlayer(event.getRightClicked().getName())
				|| player.getItemInHand() == null
				|| !ItemBuilder.has(player.getItemInHand(), "1v1", "challenge")) {
			return;
		}
		event.setCancelled(true);
		
		Player target = (Player) event.getRightClicked();
		
		if (findOpponent(target).isPresent()) {
			player.sendMessage("§c§l1V1 §cEsse jogador já está lutando!");
			return;
		}
		
		if (WaveCooldown.inCooldown(target.getName(), "1v1-challenge-" + player.getName())) {
			startBattle(player, target);
			return;
		}
		
		if (WaveCooldown.inCooldown(player.getName(), "1v1-challenge-" + target.getName())) {
			player.sendMessage("§e§l1V1 §eVocê já convidou esse jogador recentemente.");
			return;
		}
		
		WaveCooldown.create(player.getName(), "1v1-challenge-" + target.getName(), TimeUnit.SECONDS, 15);
		target.sendMessage("§e§l1V1 §eVocê foi desafiado por " + player.getName() + " para um 1v1");
		player.sendMessage("§6§l1V1 §6Você foi desafiado " + target.getName() + " para um 1v1");
	}
	
	@EventHandler
	public void onDeath(WavePlayerDeathEvent event) {
		if (!WaveWarp.DUELS.hasPlayer(event.getPlayer().getName()) || !findOpponent(event.getPlayer()).isPresent()) {
			return;
		}
		Player loser = event.getPlayer();
		Player winner = findOpponent(loser).isPresent() ? findOpponent(loser).get() : event.getKiller();
		finalizeBattle(winner);
		
		Random random = new Random();
		WavePlayer loserWavePlayer = WaveBukkit.getInstance().getPlayerManager().getPlayer(loser.getName());
		WavePlayer victimWavePlayer = WaveBukkit.getInstance().getPlayerManager().getPlayer(loser.getName());
		int loserWithdrawnCoins = random.nextInt(20 + 1 - 5) + 5;
		loserWavePlayer.getPvp().adddeathsX1(1);
		loserWavePlayer.getPvp().setKillstreak(0);

		loser.sendMessage("§cVocê perdeu a batalha contra " + winner.getName() + "§c.");
		
		
		if ((victimWavePlayer.getPvp().getXp() - 18) >= 0) {
			victimWavePlayer.getPvp().setXp(victimWavePlayer.getPvp().getXp() - 18);
			loser.sendMessage("§c§l[-] §c18 XP");
		}else {
			victimWavePlayer.getPvp().setXp(0);
			loser.sendMessage("§c§l[-] " + victimWavePlayer.getPvp().getXp() + " XP");
		}

	 WaveBukkit.getInstance().getPlayerManager().getController().save(loserWavePlayer);
		
		new BukkitRunnable() {
			@Override
			public void run() {
				WaveWarp.DUELS.send(loser, true);
			}
		}.runTaskLater(WavePvP.getInstance(), 10);

		winner.setHealth(winner.getMaxHealth());
		winner.sendMessage("§aVocê ganhou o 1v1 contra " + loser.getName() + " §7(" + (event.isValidKill() ? "Conta" : "Não Conta") + ")");
		WaveWarp.DUELS.send(winner, true);

		if (event.isValidKill()) {
			WavePlayer winnerHelixPlayer = WaveBukkit.getInstance().getPlayerManager().getPlayer(winner.getName());
			int winnerAddCoins = random.nextInt(20 + 1 - 10) + 10;
			winnerHelixPlayer.getPvp().addKills(1);
			victimWavePlayer.getPvp().addDeaths(1);
			winnerHelixPlayer.getPvp().addKillstreak(1);
			winnerHelixPlayer.getPvp().addwinsX1(1);
			winnerHelixPlayer.getPvp().addWinstreakX1(1);
			winnerHelixPlayer.getPvp().addCoins(winnerAddCoins);
			winnerHelixPlayer.getPvp().setXp(winnerHelixPlayer.getPvp().getXp() + 22);
			winner.sendMessage("§6§l[+] §6" + winnerAddCoins + " coins");
			winner.sendMessage("§6§l[+] §a22XP");
			WaveBukkit.getInstance().getPlayerManager().getController().save(winnerHelixPlayer);
		}
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onDrop(PlayerDropItemEvent event) {
		if (findOpponent(event.getPlayer()).isPresent()) {
			event.getItemDrop().remove();
		}
	}

	@Override
	public void sendBattleItems(Player player) {
		super.sendBattleItems(player);
		
		player.getInventory().setItem(0, new ItemBuilder("§bEspada", Material.DIAMOND_SWORD)
				.nbt("cancel-drop")
				.toStack()
		);

		player.getInventory().setHelmet(new ItemBuilder("§f§lPVP", Material.IRON_HELMET).toStack());
		player.getInventory().setChestplate(new ItemBuilder("§f§lPVP", Material.IRON_CHESTPLATE).toStack());
		player.getInventory().setLeggings(new ItemBuilder("§f§lPVP", Material.IRON_LEGGINGS).toStack());
		player.getInventory().setBoots(new ItemBuilder("§f§lPVP", Material.IRON_BOOTS).toStack());

		for (int i = 0; i <= 7; i++) {
			player.getInventory().addItem(new ItemBuilder(Material.MUSHROOM_STEW).toStack());
		}
	}

}