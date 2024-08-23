package net.wavemc.pvp.kit.provider;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.kit.KitHandler;
import net.wavemc.pvp.kit.KitManager;
import net.wavemc.pvp.kit.KitManager2;

public class EnderMageReal extends KitHandler {



private static Integer getConfig(String s) {
    return WavePvP.getInstance().getConfig().getInt(s);
  }
  public static boolean isSpawn(Location player) {
	    return (player.getZ() > getConfig("SpawnProtecaoZ1") && player.getZ() < getConfig("SpawnProtecaoZ2") && player.getX() > getConfig("SpawnProtecaoX1") && player.getX() < getConfig("SpawnProtecaoX2"));
	  }
	
       
  
}