package net.wavemc.pvp.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import com.viaversion.viaversion.api.Via;

import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;
import net.wavemc.core.bukkit.item.ItemBuilder;
import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.cooldown2.WaveCooldown2;
import net.wavemc.pvp.event.WavePlayerDeathEvent;
import net.wavemc.pvp.event.WavePlayerDeathEvent.Reason;
import net.wavemc.pvp.kit.Habilidade;
import net.wavemc.pvp.kit.WaveKit;
import net.wavemc.pvp.kit.WaveKit2;
import net.wavemc.pvp.kit.KitManager;
import net.wavemc.pvp.kit.KitManager2;
import net.wavemc.pvp.kit.provider.EnderMageReal;
import net.wavemc.pvp.kit.provider.GladiatorListener2;
import net.wavemc.pvp.warp.WaveWarp;

public class PlayerDeathListener implements Listener {
	
	private final static HashMap<String, List<String>> lastKillsMap = new HashMap<>();
	public final static HashMap<String, WaveKit> lastKit = new HashMap<>();
	public final static HashMap<String, WaveKit2> lastKit2 = new HashMap<>();
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		Player p = event.getEntity();
		Player killer = event.getEntity().getKiller();
		Location deathLocation = player.getLocation().clone();
		boolean validKill = false;
		if (!PlayerJoin.fall.contains(p)) {
	    	PlayerJoin.fall.add(p);
	    }
		WaveCooldown2.removeCooldown(player , "Kit");
		
		player.getActivePotionEffects().forEach(it -> player.removePotionEffect(it.getType()));
		
		player.spigot().respawn();
		event.setDeathMessage(null);
		event.getDrops().clear();
		if (KitManager.getPlayer(player.getName()).hasKit()) {
			lastKit.put(player.getName(), KitManager.getPlayer(player.getName()).getKit());
		}
		if (KitManager2.getPlayer(player.getName()).haskit2()) {
			lastKit2.put(player.getName(), KitManager2.getPlayer(player.getName()).getkit2());
		}
    	KitManager.getPlayer(player.getName()).removeKit();
    	KitManager2.getPlayer(player.getName()).removekit2();
		if (killer != null) {
			List<String> lastKills = lastKillsMap.containsKey(killer.getName()) ?
					lastKillsMap.get(killer.getName()) : new ArrayList<>();
			
			
			int repeatedKills = (int) lastKills.stream().filter(
					username -> username.equalsIgnoreCase(player.getName())
			).count() + 1;
			
			int allowRepeatedKills = 2;
			if (repeatedKills <= allowRepeatedKills) {
				validKill = true;
				lastKills.add(player.getName());
				
				if (lastKills.size() >= 3) {
					lastKills.clear();
				}
			}
			lastKillsMap.put(killer.getName(), lastKills);
			   WavePlayer wavePlayer = WaveBukkit.getInstance().getPlayerManager().getPlayer(killer.getName());
		        if(wavePlayer == null) return;
				Ranking.checkRank(killer);
			
			
			if (Habilidade.ContainsAbility(player)) {
			Habilidade.removeAbility(player);
			}
			{
			}
		}
		

		WavePlayerDeathEvent wavePlayerDeathEvent2 = new WavePlayerDeathEvent(
				player, killer, deathLocation,
				new ArrayList<>(event.getDrops()),
				Reason.ARENA,
				validKill
		);

		WavePlayerDeathEvent wavePlaye2 = new WavePlayerDeathEvent(
				player, killer, deathLocation,
				new ArrayList<>(event.getDrops()),
				Reason.GLADIATOR,
				validKill
		);
		WavePlayerDeathEvent wavePlayerDeathEvent5 = new WavePlayerDeathEvent(
				player, killer, deathLocation,
				new ArrayList<>(event.getDrops()),
				Reason.FPS,
				validKill
		);
		WavePlayerDeathEvent wavePlayerDeathEventD = new WavePlayerDeathEvent(
				player, killer, deathLocation,
				new ArrayList<>(event.getDrops()),
				Reason.DUELS,
				validKill
		);
		WavePlayerDeathEvent wavePlayerDeathEventDd = new WavePlayerDeathEvent(
				player, killer, deathLocation,
				new ArrayList<>(event.getDrops()),
				Reason.LOBBY,
				validKill
		);
		WavePlayerDeathEvent ritual = new WavePlayerDeathEvent(
				player, killer, deathLocation,
				new ArrayList<>(event.getDrops()),
				Reason.BUILD,
				validKill
		);
		
		
	
		WavePlayerDeathEvent wavePlayerDeathEvent4 = new WavePlayerDeathEvent(
				player, killer, deathLocation,
				new ArrayList<>(event.getDrops()),
				Reason.LAVA,
				validKill
		);
		 if (GladiatorListener2.combateGlad.containsKey(player)) {
             final Player winner = GladiatorListener2.combateGlad.get(player);
             final Player loser = player;
             GladiatorListener2.resetGladiatorListenerByKill(winner, loser);
             GladiatorListener2.combateGlad.remove(winner);
             GladiatorListener2.combateGlad.remove(loser);
             if (WaveWarp.SPAWN.hasPlayer(winner.getName())) {
                 WaveWarp.SPAWN.send(loser);
                 new BukkitRunnable() {
         			@Override
         			public void run() {
                 player.getInventory().setItem(0, (new ItemBuilder("§aKits 1", Material.CHEST))
             	        .nbt("spawn-item", "kits")
             	        .nbt("cancel-drop")
             	        .nbt("cancel-click")
             	        .toStack());
             	    player.getInventory().setItem(1, (new ItemBuilder("§aKits 2", Material.CHEST))
             	            .nbt("spawn-item", "kits2")
             	            .nbt("cancel-drop")
             	            .nbt("cancel-click")
             	            .toStack());
             	    player.getInventory().setItem(2, (new ItemBuilder("§aLoja", Material.EMERALD))
             	            .nbt("spawn-item", "loja")
             	            .nbt("cancel-drop")
             	            .nbt("cancel-click")
             	            .toStack());
             	    player.getInventory().setItem(4, (new ItemBuilder("§aKit Díario", Material.CHEST_MINECART))
             	            .nbt("spawn-item", "kitdiario")
             	            .nbt("cancel-drop")
             	            .nbt("cancel-click")
             	            .toStack());
             	    player.getInventory().setItem(7, (new ItemBuilder("§aExtras", Material.COMPARATOR))
             	            .nbt("spawn-item", "extras")
             	            .nbt("cancel-drop")
             	            .nbt("cancel-click")
             	            .toStack());
             	    player.getInventory().setItem(8, (new ItemBuilder("§aVoltar ao Lobby", Material.NETHER_STAR))
             	            .nbt("spawn-item", "voltar")
             	            .nbt("cancel-drop")
             	            .nbt("cancel-click")
             	            .toStack());
             	   player.updateInventory();
             	   WavePlayer k = WaveBukkit.getInstance().getPlayerManager().getPlayer(killer.getName());

             	   WavePlayer P = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
             	   P.getPvp().addDeaths(1);
             	   k.getPvp().addKills(1);
             	   k.getPvp().addKillstreak(1);
             	   k.getPvp().addCoins(Via.getAPI().getPlayerVersion(killer.getUniqueId()) >= 766 ? 50 : 25);
             	   WaveBukkit.getInstance().getPlayerManager().getController().save(P);

             	   WaveBukkit.getInstance().getPlayerManager().getController().save(k);
         			}}.runTaskLater(WavePvP.getInstance(), 7);
              return;
                 }
         }
		 if (net.wavemc.kit2.GladiatorListener.combateGlad.containsKey(player)) {
             final Player winner = net.wavemc.kit2.GladiatorListener.combateGlad.get(player);
             final Player loser = player;
             net.wavemc.kit2.GladiatorListener.resetGladiatorListenerByKill(winner, loser);
             net.wavemc.kit2.GladiatorListener.combateGlad.remove(winner);
             net.wavemc.kit2.GladiatorListener.combateGlad.remove(loser);
             if (WaveWarp.SPAWN.hasPlayer(winner.getName())) {
             WaveWarp.SPAWN.send(loser);
             new BukkitRunnable() {
      			@Override
      			public void run() {
             player.getInventory().setItem(0, (new ItemBuilder("§aKits 1", Material.CHEST))
            	        .nbt("spawn-item", "kits")
            	        .nbt("cancel-drop")
            	        .nbt("cancel-click")
            	        .toStack());
            	    player.getInventory().setItem(1, (new ItemBuilder("§aKits 2", Material.CHEST))
            	            .nbt("spawn-item", "kits2")
            	            .nbt("cancel-drop")
            	            .nbt("cancel-click")
            	            .toStack());
            	    player.getInventory().setItem(2, (new ItemBuilder("§aLoja", Material.EMERALD))
            	            .nbt("spawn-item", "loja")
            	            .nbt("cancel-drop")
            	            .nbt("cancel-click")
            	            .toStack());
            	    player.getInventory().setItem(4, (new ItemBuilder("§aKit Díario", Material.CHEST_MINECART))
            	            .nbt("spawn-item", "kitdiario")
            	            .nbt("cancel-drop")
            	            .nbt("cancel-click")
            	            .toStack());
            	    player.getInventory().setItem(7, (new ItemBuilder("§aExtras", Material.COMPARATOR))
            	            .nbt("spawn-item", "extras")
            	            .nbt("cancel-drop")
            	            .nbt("cancel-click")
            	            .toStack());
            	    player.getInventory().setItem(8, (new ItemBuilder("§aVoltar ao Lobby", Material.NETHER_STAR))
            	            .nbt("spawn-item", "voltar")
            	            .nbt("cancel-drop")
            	            .nbt("cancel-click")
            	            .toStack());
player.updateInventory();
 WavePlayer k = WaveBukkit.getInstance().getPlayerManager().getPlayer(killer.getName());

 WavePlayer P = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
 P.getPvp().addDeaths(1);
 k.getPvp().addKills(1);
 k.getPvp().addKillstreak(1);
 k.getPvp().addCoins(Via.getAPI().getPlayerVersion(killer.getUniqueId()) >= 766 ? 50 : 25);
 WaveBukkit.getInstance().getPlayerManager().getController().save(P);

 WaveBukkit.getInstance().getPlayerManager().getController().save(k);
     			}}.runTaskLater(WavePvP.getInstance(), 7);
            	    return;
             }
         }
		 else if (WaveWarp.FPS.hasPlayer(player.getName())) {
			 Bukkit.getPluginManager().callEvent(wavePlayerDeathEvent5);	 
		 }
		 else if (WaveWarp.LOBBY.hasPlayer(player.getName())) {
			 Bukkit.getPluginManager().callEvent(wavePlayerDeathEventDd);	 
		 }
		 else if (WaveWarp.ARENABUILD.hasPlayer(player.getName())) {
			 Bukkit.getPluginManager().callEvent(ritual);	 
		 }
		 
		 else if (WaveWarp.LAVACHALLENGE.hasPlayer(player.getName())) {
			 Bukkit.getPluginManager().callEvent(wavePlayerDeathEvent4);	 
		 }
		 else if (WaveWarp.DUELS.hasPlayer(player.getName())) {
			 Bukkit.getPluginManager().callEvent(wavePlayerDeathEventD);	 
		 }
		 else if (WaveWarp.GLADIATOR.hasPlayer(player.getName())) {
			 Bukkit.getPluginManager().callEvent(wavePlaye2);	 
		 }
		 
		 
		 
		 else {
			 Bukkit.getPluginManager().callEvent(wavePlayerDeathEvent2);
		 }
	}
	
	@EventHandler
	public void onPickup(PlayerPickupItemEvent event) {
		ItemStack capacete0 = new ItemStack(Material.MUSHROOM_STEW);
		ItemStack capacete1 = new ItemStack(Material.BOWL);
		ItemStack capacete2 = new ItemStack(Material.BROWN_MUSHROOM);
		ItemStack capacete3 = new ItemStack(Material.RED_MUSHROOM);
		
	if (!KitManager.getPlayer(event.getPlayer().getName()).hasKit() && event.getPlayer().getLocation().getY() > WavePvP.getInstance().getConfig().getInt("SpawnAltura") && EnderMageReal.isSpawn(event.getPlayer().getLocation())) {
		event.setCancelled(true);
	} else if (KitManager.getPlayer(event.getPlayer().getName()).hasKit() || event.getItem().getItemStack().equals(capacete1) || event.getItem().getItemStack().equals(capacete0) || event.getItem().getItemStack().equals(capacete2) || event.getItem().getItemStack().equals(capacete3)){
		event.setCancelled(false);
	}
	}

	 @EventHandler
		public void onDeath2(PlayerDeathEvent event) {
		 EventoUtils.setEvento(false, event.getEntity());
			if (EventoUtils.evento && EventoUtils.getEventoPlayers().size() == 1  && EventoUtils.getEventoPlayers().size() != 0 && EventoUtils.started && !RDMAutomatic.iniciou)
		       EventoUtils.getEventoPlayers().forEach(p -> {
		    	   Bukkit.broadcastMessage("§6GANHADOR DO EVENTO §a§ §C§l " + EventoUtils.getEventoPlayersNames());
		    	   Bukkit.broadcastMessage("§6GANHADOR DO EVENTO §a§ §C§l " + EventoUtils.getEventoPlayersNames());
	            	Bukkit.broadcastMessage("§6GANHADOR DO EVENTO §a§ §C§l " + EventoUtils.getEventoPlayersNames());
	            	Bukkit.broadcastMessage("§6GANHADOR DO EVENTO §a§ §C§l " + EventoUtils.getEventoPlayersNames());
	            	Bukkit.broadcastMessage("§6GANHADOR DO EVENTO §a§ §C§l " + EventoUtils.getEventoPlayersNames());
	            	Bukkit.broadcastMessage("§6GANHADOR DO EVENTO §a§ §C§l " + EventoUtils.getEventoPlayersNames());
	            	Bukkit.broadcastMessage("§6GANHADOR DO EVENTO §a§ §C§l " + EventoUtils.getEventoPlayersNames());
	            	Bukkit.broadcastMessage("§6GANHADOR DO EVENTO §a§ §C§l " + EventoUtils.getEventoPlayersNames());
	            	p.setHealth(20);
	         
	            	
	                p.getWorld().strikeLightning(p.getLocation());
	                p.getWorld().strikeLightning(p.getLocation());
	            	p.getWorld().strikeLightning(p.getLocation());
	            	p.getWorld().strikeLightning(p.getLocation());
	        		WaveWarp.SPAWN.send(p);
	        		EventoUtils.resetEventoClass();
		       });
	 }
		            		
		 
					    
			 
	 @EventHandler
		public void onDeath2(PlayerQuitEvent event) {
		 EventoUtils.setEvento(false, event.getPlayer());
				if (EventoUtils.evento && EventoUtils.getEventoPlayers().size() == 1 && EventoUtils.getEventoPlayers().size() != 0 && EventoUtils.started && !RDMAutomatic.iniciou)
			       EventoUtils.getEventoPlayers().forEach(p -> {
			    	   Bukkit.broadcastMessage("§6GANHADOR DO EVENTO §a§ §C§l " + EventoUtils.getEventoPlayersNames());
		            	Bukkit.broadcastMessage("§6GANHADOR DO EVENTO §a§ §C§l " + EventoUtils.getEventoPlayersNames());
		            	Bukkit.broadcastMessage("§6GANHADOR DO EVENTO §a§ §C§l " + EventoUtils.getEventoPlayersNames());
		            	Bukkit.broadcastMessage("§6GANHADOR DO EVENTO §a§ §C§l " + EventoUtils.getEventoPlayersNames());
		            	Bukkit.broadcastMessage("§6GANHADOR DO EVENTO §a§ §C§l " + EventoUtils.getEventoPlayersNames());
		            	Bukkit.broadcastMessage("§6GANHADOR DO EVENTO §a§ §C§l " + EventoUtils.getEventoPlayersNames());
		            	Bukkit.broadcastMessage("§6GANHADOR DO EVENTO §a§ §C§l " + EventoUtils.getEventoPlayersNames());
		            	p.setHealth(20);
		            
		                p.getWorld().strikeLightning(p.getLocation());
		                p.getWorld().strikeLightning(p.getLocation());
		            	p.getWorld().strikeLightning(p.getLocation());
		            	p.getWorld().strikeLightning(p.getLocation());
		        		WaveWarp.SPAWN.send(p);
		        		EventoUtils.resetEventoClass();
			       });
			}
	 @EventHandler
		public void onDeath3(PlayerQuitEvent event) {
		 EventoUtils.setEvento(false, event.getPlayer());
				if (EventoUtils.evento && EventoUtils.getEventoPlayers().size() == 1 && EventoUtils.getEventoPlayers().size() != 0 && EventoUtils.started && RDMAutomatic.iniciou)
			       EventoUtils.getEventoPlayers().forEach(p -> {
			    	   Bukkit.broadcastMessage("§6GANHADOR DO EVENTO §a§ §C§l " + EventoUtils.getEventoPlayersNames());
		            	Bukkit.broadcastMessage("§6GANHADOR DO EVENTO §a§ §C§l " + EventoUtils.getEventoPlayersNames());
		            	Bukkit.broadcastMessage("§6GANHADOR DO EVENTO §a§ §C§l " + EventoUtils.getEventoPlayersNames());
		            	Bukkit.broadcastMessage("§6GANHADOR DO EVENTO §a§ §C§l " + EventoUtils.getEventoPlayersNames());
		            	Bukkit.broadcastMessage("§6GANHADOR DO EVENTO §a§ §C§l " + EventoUtils.getEventoPlayersNames());
		            	Bukkit.broadcastMessage("§6GANHADOR DO EVENTO §a§ §C§l " + EventoUtils.getEventoPlayersNames());
		            	Bukkit.broadcastMessage("§6GANHADOR DO EVENTO §a§ §C§l " + EventoUtils.getEventoPlayersNames());
		            	p.setHealth(20);
		
		            	    
		            	   
		            	      p.setAllowFlight(false);
		            	      p.setFlying(false);
		            	      WaveWarp.SPAWN.send(p , true);
		            	      p.setFlying(false);
		            	      WavePvP.getInstance().getEventManager().getRdmAutomatic().setGameType(RDMAutomatic.GameType.STOPPED);
		            	      RDMAutomatic.iniciou = false;
		            	      RDMAutomatic.star = false;
		            	      	EventoUtils.setEvento(false, p);
		            	          p.sendMessage("§cThe event ended.");
		            	          p.getActivePotionEffects().forEach(ef -> p.removePotionEffect(ef.getType()));
		            	          Bukkit.broadcastMessage("§eThe player " + p.getName() + " wins the event!");
	            	    	     WavePlayer player = WaveBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
	            	    	      p.sendMessage("§aYou gain 200 of xp");
	            	    	      p.sendMessage("§aYou gain 1000 of coins");
	            	    	      player.getPvp().addCoins(1000);
	            	    	      player.getPvp().addXP(200);
	            	    	      WavePvP.getInstance().getEventManager().getRdmAutomatic().players.remove(p);
	            	    		  WaveBukkit.getInstance().getPlayerManager().getController().save(player);
		            	      EventoUtils.resetEventoClass();
		            	    		Bukkit.broadcastMessage("§6O Evento 1V1 foi finalizado!");
		            	    		 WavePvP.getInstance().getEventManager().getRdmAutomatic().removeFromEvent(p);
		            	      p.setAllowFlight(false);
		            	      
		            	      Bukkit.getConsoleSender().sendMessage("PARANDO EVENTO 1V1");
		            	    
		            	   WavePvP.getInstance().getEventManager().setRdmAutomatic(null);
			       });
			       };
			       @EventHandler
					public void onDeath3(PlayerDeathEvent event) {
					 EventoUtils.setEvento(false, event.getEntity());
							if (EventoUtils.evento && EventoUtils.getEventoPlayers().size() == 1 && EventoUtils.getEventoPlayers().size() != 0 && EventoUtils.started && RDMAutomatic.iniciou)
						       EventoUtils.getEventoPlayers().forEach(p -> {
						    	   Bukkit.broadcastMessage("§6GANHADOR DO EVENTO §C§l " + EventoUtils.getEventoPlayersNames());
					            	Bukkit.broadcastMessage("§6GANHADOR DO EVENTO §C§l " + EventoUtils.getEventoPlayersNames());
					            	Bukkit.broadcastMessage("§6GANHADOR DO EVENTO §C§l " + EventoUtils.getEventoPlayersNames());
					            	Bukkit.broadcastMessage("§6GANHADOR DO EVENTO §C§l " + EventoUtils.getEventoPlayersNames());
					            	Bukkit.broadcastMessage("§6GANHADOR DO EVENTO §C§l " + EventoUtils.getEventoPlayersNames());
					            	Bukkit.broadcastMessage("§6GANHADOR DO EVENTO §C§l " + EventoUtils.getEventoPlayersNames());
					            	Bukkit.broadcastMessage("§6GANHADOR DO EVENTO §C§l " + EventoUtils.getEventoPlayersNames());
					            	p.setHealth(20);
					
					            	    
					            	   
					            	      p.setAllowFlight(false);
					            	      p.setFlying(false);
					            	      WaveWarp.SPAWN.send(p , true);
					            	      p.setFlying(false);
					            	      RDMAutomatic.iniciou = false;
					            	      RDMAutomatic.star = false;
					            	      	EventoUtils.setEvento(false, p);
					            	          p.sendMessage("§cThe event ended.");
					            	          p.getActivePotionEffects().forEach(ef -> p.removePotionEffect(ef.getType()));
						       
					            	      EventoUtils.resetEventoClass();
					            	      WavePvP.getInstance().getEventManager().getRdmAutomatic().removeFromEvent(p);
					            	    		Bukkit.broadcastMessage("§6O EVENTO 1V1 TERMINOU");
					            	    		WavePvP.getInstance().getEventManager().getRdmAutomatic().players.remove(p);
					            	    	      Bukkit.broadcastMessage("§eO jogador " + p.getName() + " ganhou o evento!");
					            	    	      WavePlayer player = WaveBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
					            	    	      p.sendMessage("§aVocê ganhou 200 de xp");
					            	    	      p.sendMessage("§aVocê ganhou 1000 de coins");
					            	    	      player.getPvp().addCoins(1000);
					            	    	      player.getPvp().addXP(200);
					            	    		  WaveBukkit.getInstance().getPlayerManager().getController().save(player);
					            	      p.setAllowFlight(false);
					            	      
					            	      Bukkit.getConsoleSender().sendMessage("PARANDO EVENTO 1V1");
					            	    
					            	   WavePvP.getInstance().getEventManager().setRdmAutomatic(null);
						       });
						       };	 				
	 
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		p.setFireTicks(0);
	}
}
