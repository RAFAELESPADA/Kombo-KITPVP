package net.wavemc.pvp.kit.provider;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.kit.Ejectable;
import net.wavemc.pvp.kit.KitHandler;
import net.wavemc.pvp.kit.KitManager;
import net.wavemc.pvp.kit.KitManager2;
import net.wavemc.pvp.listener.Jump;
import net.wavemc.pvp.warp.WaveWarp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Grappler extends KitHandler{


	public static int getDistance(Player e) {
		Location loc = e.getLocation().clone();
		double y = loc.getBlockY();
		int distance = 0;
		for (double i = y; i >= 0; i--) {
			loc.setY(i);
			if (loc.getBlock().getType().isSolid())
				break;
			distance++;
		}
		return distance;
	}

	@EventHandler
	public void usarbbf(PlayerMoveEvent e) {
		Player p = e.getPlayer();

		Player target = p;
		MeteorD meteor = new MeteorD();
		if (Jump.recebeu.containsKey(p.getName()) && EnderMageReal.isSpawn(p.getLocation())
				&& e.getPlayer().getLocation().getY() > WavePvP.getInstance().getConfig().getInt("SpawnAltura")
				&& !GladiatorListener2.combateGlad.containsKey(target)
				&& !net.wavemc.kit2.GladiatorListener.combateGlad.containsKey(target) && !Jump.fall.contains(target.getName())) {
			if (meteor.subiu.contains(target)) {
				return;
			}
			
			WaveWarp.SPAWN.send(p, true);
			KitManager.getPlayer(p.getName()).removeKit();
			KitManager2.getPlayer(target.getName()).removekit2();
			p.sendMessage("§cNão volte de kit pro spawn por favor!");
			p.playSound(p.getLocation(), Sound.ENTITY_GHAST_SCREAM, 10, 10);
		}
	}

	@EventHandler
	public void usarf(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if ((p.getItemInHand().getType().equals(Material.LEAD))) {
			if (KitManager.getPlayer(e.getPlayer().getName()).hasKit(this)) {
				Player target = p;
				if ((p.getLocation().getY() > WavePvP.getInstance().getConfig().getInt("SpawnAltura") - 12)
						&& EnderMageReal.isSpawn(p.getLocation()) && !GladiatorListener2.combateGlad.containsKey(target)
						&& !net.wavemc.kit2.GladiatorListener.combateGlad.containsKey(target)) {
					p.sendMessage("§cNão use o grappler perto do spawn!");
					Location tp = p.getLocation();

					tp.setY(tp.getY() - getDistance(p));

					p.teleport(tp);
					return;

				}
			}
		}}}
	
		
