package net.wavemc.pvp.kit.provider;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDismountEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import net.wavemc.core.bukkit.item.ItemBuilder;
import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.command.ServerTimerEvent;
import net.wavemc.pvp.command.ServerTimerEvent2;
import net.wavemc.pvp.cooldown2.UpdateEvent;
import net.wavemc.pvp.cooldown2.UpdateEvent2;
import net.wavemc.pvp.cooldown2.WaveCooldown2;
import net.wavemc.pvp.kit.Ejectable;
import net.wavemc.pvp.kit.WaveKit;
import net.wavemc.pvp.kit.KitHandler;
import net.wavemc.pvp.kit.KitManager;
import net.wavemc.pvp.kit.KitManager2;

public class Jumper extends KitHandler implements Ejectable {
	
	private final List<UUID> track = new ArrayList<>();
	private final List<UUID> moving = new ArrayList<>();

	@Override
    public void execute(Player player) {
        super.execute(player);
    }
	
	@Override
	public void eject(Player player) {
		track.remove(player.getUniqueId());
		moving.remove(player.getUniqueId());
	}
	
	
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onDamage(EntityDamageEvent event) {
		if (event.isCancelled())
			return;
		if (event.getEntity() instanceof Player) {
			DamageCause cause = event.getCause();
			if (cause == DamageCause.FALL || cause == DamageCause.SUFFOCATION) {
				if (moving.contains(((Player) event.getEntity()).getUniqueId())) {
					event.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onTimer(ServerTimerEvent event) {
		Iterator<UUID> iterator = track.iterator();
		while (iterator.hasNext()) {
			Player next = Bukkit.getPlayer(iterator.next());
			if (next == null) {
				iterator.remove();
				continue;
			}
			next.getWorld().playEffect(next.getLocation(), Effect.MOBSPAWNER_FLAMES, 10);
		}
	}
	@EventHandler
	public void onUpdate2(ServerTimerEvent2 event) {
		if (event.getCurrentTick() % 2 > 0)
			return;
		for (UUID uuid : net.wavemc.pvp.cooldown2.WaveCooldown2.map.keySet()) {
			Player player = Bukkit.getPlayer(uuid);
			if (player != null) {
				List<net.wavemc.pvp.cooldown2.WaveCooldownAPI> list = net.wavemc.pvp.cooldown2.WaveCooldown2.map.get(uuid);
				Iterator<net.wavemc.pvp.cooldown2.WaveCooldownAPI> it = list.iterator();

				net.wavemc.pvp.cooldown2.WaveCooldownAPI found = null;
				while (it.hasNext()) {
					net.wavemc.pvp.cooldown2.WaveCooldownAPI cooldown = it.next();
					if (!cooldown.expired()) {
						if (cooldown instanceof net.wavemc.pvp.cooldown2.ItemCooldown) {
							ItemStack hand = player.getEquipment().getItemInHand();
							if (hand != null && hand.getType() != Material.AIR) {
								net.wavemc.pvp.cooldown2.ItemCooldown item = (net.wavemc.pvp.cooldown2.ItemCooldown) cooldown;
								if (hand.equals(item.getItem())) {
									item.setSelected(true);
									found = item;
									break;
								}
							}
							continue;
						}
						found = cooldown;
						continue;
					}
					it.remove();
					Bukkit.getServer().getPluginManager().callEvent(new net.wavemc.pvp.cooldown2.CooldownFinishEvent(player, cooldown));
					 player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
				}

				if (found != null) {
					net.wavemc.pvp.cooldown2.CooldownStartEvent e = new net.wavemc.pvp.cooldown2.CooldownStartEvent(player, found);
					Bukkit.getPluginManager().callEvent(e);
					if (!e.isCancelled()) {
						net.wavemc.pvp.cooldown2.WaveCooldown2.display(player, found);
					}
				} else if (list.isEmpty()) {
				
					WaveCooldown2.map.remove(uuid);
				} else {
					net.wavemc.pvp.cooldown2.WaveCooldownAPI cooldown = list.get(0);
					if (cooldown instanceof net.wavemc.pvp.cooldown2.ItemCooldown) {
						net.wavemc.pvp.cooldown2.ItemCooldown item = (net.wavemc.pvp.cooldown2.ItemCooldown) cooldown;
						if (item.isSelected()) {
							item.setSelected(false);
							
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onUpdate(ServerTimerEvent event) {
		if (event.getCurrentTick() % 2 > 0)
			return;

		for (UUID uuid : net.wavemc.pvp.cooldown1.WaveCooldown2.map.keySet()) {
			Player player = Bukkit.getPlayer(uuid);
			if (player != null) {
				
				List<net.wavemc.pvp.cooldown1.WaveCooldownAPI> list = net.wavemc.pvp.cooldown1.WaveCooldown2.map.get(uuid);
				Iterator<net.wavemc.pvp.cooldown1.WaveCooldownAPI> it = list.iterator();

				net.wavemc.pvp.cooldown1.WaveCooldownAPI found = null;
				while (it.hasNext()) {
					net.wavemc.pvp.cooldown1.WaveCooldownAPI cooldown = it.next();
					if (!cooldown.expired()) {
						if (cooldown instanceof net.wavemc.pvp.cooldown1.ItemCooldown) {
							ItemStack hand = player.getEquipment().getItemInHand();
							if (hand != null && hand.getType() != Material.AIR) {
								net.wavemc.pvp.cooldown1.ItemCooldown item = (net.wavemc.pvp.cooldown1.ItemCooldown) cooldown;
								if (hand.equals(item.getItem())) {
									item.setSelected(true);
									found = item;
									break;
								}
							}
							continue;
						}
						found = cooldown;
						continue;
					}
					it.remove();
					Bukkit.getServer().getPluginManager().callEvent(new net.wavemc.pvp.cooldown1.CooldownFinishEvent(player, cooldown));
					 player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
				}

				if (found != null) {
					net.wavemc.pvp.cooldown1.CooldownStartEvent e = new net.wavemc.pvp.cooldown1.CooldownStartEvent(player, found);
					Bukkit.getPluginManager().callEvent(e);
					if (!e.isCancelled()) {
						net.wavemc.pvp.cooldown1.WaveCooldown2.display(player, found);
					}
				} else if (list.isEmpty()) {
					WaveActionBar.send(player, " ");
					net.wavemc.pvp.cooldown1.WaveCooldown2.map.remove(uuid);
				} else {
					net.wavemc.pvp.cooldown1.WaveCooldownAPI cooldown = list.get(0);
					if (cooldown instanceof net.wavemc.pvp.cooldown1.ItemCooldown) {
						net.wavemc.pvp.cooldown1.ItemCooldown item = (net.wavemc.pvp.cooldown1.ItemCooldown) cooldown;
						if (item.isSelected()) {
							item.setSelected(false);
							WaveActionBar.send(player, " ");
						}
					}
				}
			}
		}
	}
	

	@EventHandler
	public void onDismount(EntityDismountEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			track.remove(player.getUniqueId());
			cancelMoving(player);
		}
	}
	
	public void cancelMoving(Player player) {
		new BukkitRunnable() {

			@Override
			public void run() {
				moving.remove(player.getUniqueId());
			}			
		}.runTaskLater(WavePvP.getInstance(), WavePvP.getInstance().getConfig().getInt("JumperCooldown"));
	}
	

		
	}

