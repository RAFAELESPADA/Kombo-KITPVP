package net.wavemc.pvp.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import net.wavemc.core.bukkit.item.ItemBuilder;
import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.inventory.Extras;
import net.wavemc.pvp.inventory.KitsInventory;
import net.wavemc.pvp.inventory.KitsInventory2;
import net.wavemc.pvp.inventory.ShopInventory;
import net.wavemc.pvp.kit.provider.EnderMageReal;
import net.wavemc.pvp.warp.WaveWarp;


public class OpenSpawnItemsListener implements Listener {
	
	

	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.getItem() == null) {
			return;
		}
		if (event.getItem().getType().equals(Material.MUSHROOM_STEW)) {
			return;
		}
		if (!event.hasItem() || !ItemBuilder.has(event.getItem(), "spawn-item")) {
			return;
		}
		switch (ItemBuilder.getString(event.getItem(), "spawn-item")) {
			case "kits":
				KitsInventory.open(player);
				
				break;
			case "kits2":
				KitsInventory2.open(player);
				
				break;
			case "loja":
			    ShopInventory.open(player);
				
				break;
			case "extras":
			    Extras.open(player, player);
				
				break;
	        case "voltar":
	        WaveWarp.LOBBY.send(player);
		    
		    break;		
	        
		}
	}
	
@EventHandler
public void onInteractt(PlayerPickupItemEvent event) {
	Player player = event.getPlayer();
	if (WaveWarp.SPAWN.hasPlayer(player.getName()) && EnderMageReal.isSpawn(player.getLocation()) && player.getLocation().getY() > WavePvP.getInstance().getConfig().getInt("SpawnAltura")) {
	event.setCancelled(true);
	}
}
	@EventHandler
	public void onInteracttf(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		if (WaveWarp.SPAWN.hasPlayer(player.getName()) && EnderMageReal.isSpawn(player.getLocation()) && player.getLocation().getY() > WavePvP.getInstance().getConfig().getInt("SpawnAltura") && PlayerJoin.fall.contains(player)) {
		event.setCancelled(true);
		}
	}
	 
		@EventHandler
		public void onInteracttgf(PlayerDropItemEvent event) {
			Player player = event.getPlayer();
			if (event.getItemDrop().getItemStack().getType() == Material.IRON_SWORD || event.getItemDrop().getItemStack().getType() == Material.DIAMOND_SWORD) {
			event.setCancelled(true);
			}
	}
		@EventHandler
		public void onInteracttc(ItemSpawnEvent event) {
			Item player = event.getEntity();
			if (EnderMageReal.isSpawn(player.getLocation()) && player.getLocation().getY() > WavePvP.getInstance().getConfig().getInt("SpawnAltura")) {
			event.setCancelled(true);
			player.remove();
			}
}}


