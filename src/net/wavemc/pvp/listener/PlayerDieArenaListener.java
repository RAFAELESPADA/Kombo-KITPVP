package net.wavemc.pvp.listener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.viaversion.viaversion.api.Via;

import lombok.NonNull;
import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;
import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.event.WavePlayerDeathEvent;
import net.wavemc.pvp.event.WavePlayerDeathEvent.Reason;
import net.wavemc.pvp.warp.WaveWarp;

public class PlayerDieArenaListener implements Listener {
	
	@EventHandler
	public void onPlayerDieInArena(WavePlayerDeathEvent event) {
		if (event.getReason() != Reason.ARENA) {
			return;
		}
		Player player = event.getPlayer();
		World w = player.getWorld();
		Jump.recebeu.remove(player.getName());
		List<ItemStack> drops = new ArrayList<>(event.getDrops());
		Location deathLocation = event.getDeathLocation();
		if (drops.size() > 0) {
			deathLocation.getWorld().playEffect(deathLocation, Effect.LAVA_INTERACT, 4);
		}
		player.spigot().respawn();
		ItemStack capacete0 = new ItemStack(Material.MUSHROOM_STEW);
		ItemStack capacete1 = new ItemStack(Material.BOWL);
		ItemStack capacete2 = new ItemStack(Material.BROWN_MUSHROOM);
		ItemStack capacete3 = new ItemStack(Material.RED_MUSHROOM);
		ItemStack capacete4 = new ItemStack(Material.COCOA_BEANS);
		event.getDrops().clear();
		drops.clear();
		new BukkitRunnable() {
			@Override
			public void run() {
				WaveWarp.SPAWN.send(player, true);
			}
		}.runTaskLater(WavePvP.getInstance(), 3);
		
		if (event.hasKiller()) {
			Player killer = event.getKiller();
			if (player == killer) {
				WavePlayer victimWavePlayer2 = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
				player.sendMessage("§cVocê se matou e não ganhou kills e nem coins.");
				victimWavePlayer2.getPvp().addDeaths(1);
				return;
			}
			Random random = new Random();
			if (event.getPlayer().getInventory().getHelmet() != null) {
				w.dropItemNaturally(deathLocation, event.getPlayer().getInventory().getHelmet());
			}
			if (event.getPlayer().getInventory().getChestplate() != null) {
				w.dropItemNaturally(deathLocation, event.getPlayer().getInventory().getChestplate());
			}
			if (event.getPlayer().getInventory().getLeggings() != null) {
				w.dropItemNaturally(deathLocation, event.getPlayer().getInventory().getLeggings());
			}
			if (event.getPlayer().getInventory().getBoots() != null) {
				w.dropItemNaturally(deathLocation, event.getPlayer().getInventory().getBoots());
			}
			for (int i = 0; i < 64; i++) {
				
				w.dropItemNaturally(deathLocation, capacete1);
				w.dropItemNaturally(deathLocation, capacete2);
				w.dropItemNaturally(deathLocation, capacete3);
					w.dropItemNaturally(deathLocation, capacete1);
					w.dropItemNaturally(deathLocation, capacete4);
					w.dropItemNaturally(deathLocation, capacete4);
				
			}
			for (int i = 0; i < 33; i++) {
			w.dropItemNaturally(deathLocation, capacete0);
			}
			WavePlayer killerWavePlayer = WaveBukkit.getInstance().getPlayerManager().getPlayer(killer.getName());
			killer.playSound(killer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10.0f, 10.0f);
			killer.sendMessage("§3Você matou " + player.getName() + ". §8(" + (event.isValidKill() ? "Conta" : "Não conta" + ")"));
			if (event.isValidKill()) {
				int killerAddCoins = !killer.hasPermission("wave.doublexp") ? random.nextInt(15) + 15 : random.nextInt(30) + 20;
				killerWavePlayer.getPvp().addKills(1);
				killerWavePlayer.getPvp().addKillstreak(1);
				killerWavePlayer.getPvp().addCoins(killerAddCoins);
				killerWavePlayer.getPvp().addXP(25);
				if (Via.getAPI().getPlayerVersion(killer.getUniqueId()) >= 498) {

					killerWavePlayer.getPvp().addCoins(killerAddCoins);
					killer.sendMessage("§eVocê mais ganhou coins de bônus por estar na 1.14.4 ou superior");
					
				}
				killer.sendMessage("§6§l[+] §6" + killerAddCoins + " coins" + (killer.hasPermission("wave.doublexp") ? " [2X]" : "!"));
				killer.sendMessage("§6§l[+] §a25XP");
			}
			WavePlayer victimWavePlayer = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
			
			if ((victimWavePlayer.getPvp().getXp() - 20) >= 0) {
				victimWavePlayer.getPvp().setXp(victimWavePlayer.getPvp().getXp() - 20);
				player.sendMessage("§c§l[-] §c20 XP");
			}else {
				victimWavePlayer.getPvp().setXp(0);
				player.sendMessage("§c§l[-] " + victimWavePlayer.getPvp().getXp() + " XP");
			}
			victimWavePlayer.getPvp().addDeaths(1);
			player.sendMessage("§cVocê morreu para o jogador " + killer.getName());
			player.playSound(player.getLocation(), Sound.ENTITY_BAT_DEATH, 10f, 10f);
					WaveBukkit.getInstance().getPlayerManager().getController().save(victimWavePlayer);
					WaveBukkit.getInstance().getPlayerManager().getController().save(killerWavePlayer);
		}else {
			player.sendMessage("§cVocê morreu por razões desconhecidas e seus status não foram alterados.");
		}
		
		Location spawnLocation = WaveBukkit.getInstance().getWarpManager().findWarp("spawn").isPresent() ?
				WaveBukkit.getInstance().getWarpManager().findWarp("spawn").get().getLocation() : player.getLocation().getWorld().getSpawnLocation();
		player.teleport(spawnLocation);
	}
	
	@EventHandler
	public void onPlayerDieInArena2(WavePlayerDeathEvent event) {
		if (event.getReason() != Reason.FPS) {
			return;
		}
		Player player = event.getPlayer();
		List<ItemStack> drops = new ArrayList<>(event.getDrops());
		player.spigot().respawn();
		drops.clear();
		new BukkitRunnable() {
			@Override
			public void run() {
				WaveWarp.FPS.send(player, true);
			}
		}.runTaskLater(WavePvP.getInstance(), 3);
		
		if (event.hasKiller()) {
			ItemStack capacete0 = new ItemStack(Material.MUSHROOM_STEW);
			ItemStack capacete1 = new ItemStack(Material.BOWL , 64);
			ItemStack capacete2 = new ItemStack(Material.BROWN_MUSHROOM , 64);
			ItemStack capacete3 = new ItemStack(Material.RED_MUSHROOM, 64);
			Player killer = event.getKiller();
			if (player == killer) {
				WavePlayer victimWavePlayer2 = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
				player.sendMessage("Você se matou e não ganhou kills e nem coins.");
				victimWavePlayer2.getPvp().addDeaths(1);
				
				return;
			}
			Random random = new Random();
			   killer.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
			      killer.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
			      killer.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
			      killer.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
			WavePlayer killerWavePlayer = WaveBukkit.getInstance().getPlayerManager().getPlayer(killer.getName());
			killer.playSound(killer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10.0f, 10.0f);
			killer.sendMessage("§3Você matou " + player.getName() + ". §8(" + (event.isValidKill() ? "Conta" : "Não conta" + ")"));
			if (event.isValidKill()) {
				int killerAddCoins = !killer.hasPermission("wave.doublexp") ? random.nextInt(15) + 15 : random.nextInt(37) + 20;
				killerWavePlayer.getPvp().addKillsFPS(1);
				killerWavePlayer.getPvp().addKillstreak(1);
				killerWavePlayer.getPvp().addXP(25);
				if (Via.getAPI().getPlayerVersion(killer.getUniqueId()) >= 498) {

					killerWavePlayer.getPvp().addCoins(killerAddCoins);
					killer.sendMessage("§eVocê mais ganhou coins de bônus por estar na 1.14.4 ou superior");
					
				}
				killerWavePlayer.getPvp().addCoins(killerAddCoins);
				killer.sendMessage("§6§l[+] §6" + killerAddCoins + " coins" + (killer.hasPermission("wave.doublexp") ? " [2X]" : "!"));
				killer.sendMessage("§6§l[+] §a25XP");
			}
			
for (int i2 = 0; i2 < 35; i2++) {
				Location deathLocation = killer.getLocation();
				World w = killer.getWorld();
				w.dropItemNaturally(deathLocation, capacete0);
				
			}
Location deathLocation = killer.getLocation();
World w = killer.getWorld();
w.dropItemNaturally(deathLocation, capacete2);
w.dropItemNaturally(deathLocation, capacete3);
	w.dropItemNaturally(deathLocation, capacete1);
			WavePlayer victimWavePlayer = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
			victimWavePlayer.getPvp().addDeathsFPS(1);
			
			if ((victimWavePlayer.getPvp().getXp() - 20) >= 0) {
				victimWavePlayer.getPvp().setXp(victimWavePlayer.getPvp().getXp() - 20);
				player.sendMessage("§c§l[-] §c20 XP");
			}else {
				victimWavePlayer.getPvp().setXp(0);
				player.sendMessage("§c§l[-] " + victimWavePlayer.getPvp().getXp() + " XP");
			}
			killerWavePlayer.getPvp().addKills(1);
			victimWavePlayer.getPvp().addDeaths(1);
			player.sendMessage("§cVocê morreu para o jogador " + killer.getName());
			player.playSound(player.getLocation(), Sound.ENTITY_BAT_DEATH, 10f, 10f);
					WaveBukkit.getInstance().getPlayerManager().getController().save(victimWavePlayer);
					WaveBukkit.getInstance().getPlayerManager().getController().save(killerWavePlayer);
			}	else {
			player.sendMessage("§cVocê morreu por razões desconhecidas e seus status não foram alterados.");
		}
	}

	@EventHandler
	public void onPlayerDieInArGena2(WavePlayerDeathEvent event) {
		if (event.getReason() != Reason.BUILD) {
			return;
		}
		Player player = event.getPlayer();
		List<ItemStack> drops = new ArrayList<>(event.getDrops());
		player.spigot().respawn();
		drops.clear();
		new BukkitRunnable() {
			@Override
			public void run() {
				WaveWarp.ARENABUILD.send(player, true);
			}
		}.runTaskLater(WavePvP.getInstance(), 3);
		
		if (event.hasKiller()) {
			ItemStack capacete0 = new ItemStack(Material.MUSHROOM_STEW);
			ItemStack capacete1 = new ItemStack(Material.BOWL , 64);
			ItemStack capacete2 = new ItemStack(Material.BROWN_MUSHROOM , 64);
			ItemStack capacete3 = new ItemStack(Material.RED_MUSHROOM, 64);
			Player killer = event.getKiller();
			if (player == killer) {
				WavePlayer victimWavePlayer2 = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
				player.sendMessage("Você se matou e não ganhou kills e nem coins.");
				victimWavePlayer2.getPvp().addDeaths(1);
				
				return;
			}
			Random random = new Random();
			   killer.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
			      killer.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
			      killer.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
			      killer.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
			WavePlayer killerWavePlayer = WaveBukkit.getInstance().getPlayerManager().getPlayer(killer.getName());
			killer.playSound(killer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10.0f, 10.0f);
			killer.sendMessage("§3Você matou " + player.getName() + ". §8(" + (event.isValidKill() ? "Conta" : "Não conta" + ")"));
			if (event.isValidKill()) {
				int killerAddCoins = !killer.hasPermission("wave.doublexp") ? random.nextInt(15) + 15 : random.nextInt(37) + 20;
				killerWavePlayer.getPvp().addKillsFPS(1);
				killerWavePlayer.getPvp().addKillstreak(1);
				killerWavePlayer.getPvp().addXP(25);
				if (Via.getAPI().getPlayerVersion(killer.getUniqueId()) >= 498) {

					killerWavePlayer.getPvp().addCoins(killerAddCoins);
					killer.sendMessage("§eVocê mais ganhou coins de bônus por estar na 1.14.4 ou superior");
					
				}
				killerWavePlayer.getPvp().addCoins(killerAddCoins);
				killer.sendMessage("§6§l[+] §6" + killerAddCoins + " coins" + (killer.hasPermission("wave.doublexp") ? " [2X]" : "!"));
				killer.sendMessage("§6§l[+] §a25XP");
			}
			
for (int i2 = 0; i2 < 35; i2++) {
				Location deathLocation = killer.getLocation();
				World w = killer.getWorld();
				w.dropItemNaturally(deathLocation, capacete0);
				
			}
Location deathLocation = killer.getLocation();
World w = killer.getWorld();
w.dropItemNaturally(deathLocation, capacete2);
w.dropItemNaturally(deathLocation, capacete3);
	w.dropItemNaturally(deathLocation, capacete1);
			WavePlayer victimWavePlayer = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
			victimWavePlayer.getPvp().addDeathsFPS(1);
			
			if ((victimWavePlayer.getPvp().getXp() - 20) >= 0) {
				victimWavePlayer.getPvp().setXp(victimWavePlayer.getPvp().getXp() - 20);
				player.sendMessage("§c§l[-] §c20 XP");
			}else {
				victimWavePlayer.getPvp().setXp(0);
				player.sendMessage("§c§l[-] " + victimWavePlayer.getPvp().getXp() + " XP");
			}
			killerWavePlayer.getPvp().addKills(1);
			victimWavePlayer.getPvp().addDeaths(1);
			player.sendMessage("§cVocê morreu para o jogador " + killer.getName());
			player.playSound(player.getLocation(), Sound.ENTITY_BAT_DEATH, 10f, 10f);
					WaveBukkit.getInstance().getPlayerManager().getController().save(victimWavePlayer);
					WaveBukkit.getInstance().getPlayerManager().getController().save(killerWavePlayer);
			}	else {
			player.sendMessage("§cVocê morreu por razões desconhecidas e seus status não foram alterados.");
		}
	}
	@EventHandler
	public void onPlayerDieGInArena2(WavePlayerDeathEvent event) {
		if (event.getReason() != Reason.LOBBY) {
			return;
		}
		Player player = event.getPlayer();
		List<ItemStack> drops = new ArrayList<>(event.getDrops());
		player.spigot().respawn();
		drops.clear();
		new BukkitRunnable() {
			@Override
			public void run() {
				WaveWarp.SPAWN.send(player, true);
			}
		}.runTaskLater(WavePvP.getInstance(), 3);
		
		if (event.hasKiller()) {
			ItemStack capacete0 = new ItemStack(Material.MUSHROOM_STEW);
			ItemStack capacete1 = new ItemStack(Material.BOWL , 64);
			ItemStack capacete2 = new ItemStack(Material.BROWN_MUSHROOM , 64);
			ItemStack capacete3 = new ItemStack(Material.RED_MUSHROOM, 64);
			Player killer = event.getKiller();
			if (player == killer) {
				WavePlayer victimWavePlayer2 = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
				player.sendMessage("Você se matou e não ganhou kills e nem coins.");
				victimWavePlayer2.getPvp().addDeaths(1);
				
				return;
			}
			Bukkit.broadcastMessage(ChatColor.BLUE + player.getName() + " morreu no evento para: " + killer.getName());
			Random random = new Random();
			   killer.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
			      killer.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
			      killer.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
			      killer.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
			WavePlayer killerWavePlayer = WaveBukkit.getInstance().getPlayerManager().getPlayer(killer.getName());
			killer.playSound(killer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10.0f, 10.0f);
			killer.sendMessage("§3Você matou " + player.getName() + ". §8(" + (event.isValidKill() ? "Conta" : "Não conta" + ")"));
			if (event.isValidKill()) {
				int killerAddCoins = !killer.hasPermission("wave.doublexp") ? random.nextInt(15) + 15 : random.nextInt(37) + 20;
				killerWavePlayer.getPvp().addKillsFPS(1);
				killerWavePlayer.getPvp().addKillstreak(1);
				killerWavePlayer.getPvp().addXP(25);
			
				killerWavePlayer.getPvp().addCoins(killerAddCoins);
				if (Via.getAPI().getPlayerVersion(killer.getUniqueId()) >= 498) {

					killerWavePlayer.getPvp().addCoins(killerAddCoins);
					killer.sendMessage("§eVocê mais ganhou coins de bônus por estar na 1.14.4 ou superior");
					
				}
				killer.sendMessage("§6§l[+] §6" + killerAddCoins + " coins" + (killer.hasPermission("wave.doublexp") ? " [2X]" : "!"));
				killer.sendMessage("§6§l[+] §a25XP");
			}
			
for (int i2 = 0; i2 < 35; i2++) {
				Location deathLocation = killer.getLocation();
				World w = killer.getWorld();
				w.dropItemNaturally(deathLocation, capacete0);
				
			}
Location deathLocation = killer.getLocation();
World w = killer.getWorld();
w.dropItemNaturally(deathLocation, capacete2);
w.dropItemNaturally(deathLocation, capacete3);
	w.dropItemNaturally(deathLocation, capacete1);
			WavePlayer victimWavePlayer = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
			victimWavePlayer.getPvp().addDeathsFPS(1);
			
			if ((victimWavePlayer.getPvp().getXp() - 20) >= 0) {
				victimWavePlayer.getPvp().setXp(victimWavePlayer.getPvp().getXp() - 20);
				player.sendMessage("§c§l[-] §c20 XP");
			}else {
				victimWavePlayer.getPvp().setXp(0);
				player.sendMessage("§c§l[-] " + victimWavePlayer.getPvp().getXp() + " XP");
			}
			killerWavePlayer.getPvp().addKills(1);
			victimWavePlayer.getPvp().addDeaths(1);
			player.sendMessage("§cVocê morreu para o jogador " + killer.getName());
			player.playSound(player.getLocation(), Sound.ENTITY_BAT_DEATH, 10f, 10f);
					WaveBukkit.getInstance().getPlayerManager().getController().save(victimWavePlayer);
					WaveBukkit.getInstance().getPlayerManager().getController().save(killerWavePlayer);
			}	else {
			player.sendMessage("§cVocê morreu por razões desconhecidas e seus status não foram alterados.");
		}
	}
	
	}
