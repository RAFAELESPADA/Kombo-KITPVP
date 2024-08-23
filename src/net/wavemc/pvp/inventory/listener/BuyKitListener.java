package net.wavemc.pvp.inventory.listener;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;
import net.wavemc.core.bukkit.format.WaveDecimalFormat;
import net.wavemc.core.bukkit.item.ItemBuilder;
import net.wavemc.pvp.command.NoBreakEvent;
import net.wavemc.pvp.inventory.Modo;
import net.wavemc.pvp.inventory.Servidores;
import net.wavemc.pvp.inventory.ShopInventory;
import net.wavemc.pvp.kit.WaveKit;
import net.wavemc.pvp.kit.WaveKit2;
import net.wavemc.pvp.kit.provider.GladiatorListener2;
import net.wavemc.pvp.warp.WaveWarp;
import net.wavemc.warp.provider.Duels;
import net.wavemc.warp.provider.Gladiator;
import net.wavemc.warp.provider.OneVsOne;
import net.wavemc.warp.provider.Sumo;

public class BuyKitListener implements Listener {
	
	@EventHandler
	public void onInvClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
	
		if (!event.getView().getTitle().equals(ShopInventory.getInventoryName())) {
			return;
		}
		
		event.setCancelled(true);
		if (event.getCurrentItem() == null) {
			return;
		}
		if (event.getCurrentItem().getItemMeta() == null) {
			return;
		}
		
		WavePlayer helixPlayer = WaveBukkit.getInstance().getPlayerManager()
				.getPlayer(player.getName());
		String kitName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
		
		
		WaveKit2.findKit(kitName).ifPresent(kit -> {
			player.closeInventory();
			if (player.hasPermission("wave.kit2." + kit.getName())) {
				player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 10.0f, 10.0f);
				player.sendMessage("§cVocê ja tem esse kit!");
				return;
			}
			if (helixPlayer.getPvp().getCoins() < kit.getPrice()) {
				int remaingCoins = kit.getPrice() - helixPlayer.getPvp().getCoins();
				player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 10.0f, 10.0f);
				player.sendMessage("§cVocê precisa de " + WaveDecimalFormat.format(remaingCoins) + " coins para comprar esse kit!");
				return;
			}
			
			helixPlayer.getPvp().removeCoins(kit.getPrice());
			WaveBukkit.getInstance().getPlayerManager().getController().save(helixPlayer);
			
			player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10.0f, 10.0f);
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission set wave.kit2." + kit.toString().toLowerCase() + " pvp");

			player.sendMessage("§aVocê comprou o kit " + kit.getName() + " por " + kit.getPrice() + " coins");
		});
	}
	

	 private final boolean isArmor(final ItemStack itemStack) {
	        if (itemStack == null)
	            return false;
	        final String typeNameString = itemStack.getType().name();
	        if (typeNameString.endsWith("_HELMET")
	                || typeNameString.endsWith("_CHESTPLATE")
	                || typeNameString.endsWith("_LEGGINGS")
	                || typeNameString.endsWith("_BOOTS")) {
	            return true;
	            }

	        return false;
	    }

		
	
	/*    */       

	


@EventHandler
public void onIndfvvClicki4(InventoryClickEvent event) {
	Player player = (Player) event.getWhoClicked();
	if (event.getInventory().getType() == InventoryType.PLAYER) {
        return;
    }
	if (!event.getView().getTitle().equals(Servidores.getInventoryName())) {
		return;
	}
	if (event.getCurrentItem() == null) {
		return;
	}
	if (!(event.getCurrentItem().getType() == Material.IRON_CHESTPLATE)) {
		return;
	}
	
	if (!ItemBuilder.has(event.getCurrentItem(), "arena")) {
		return;
	}
	event.setCancelled(true);
	
	player.sendMessage(ChatColor.GREEN + "Enviando você para a Arena.");
	player.closeInventory();
	WaveWarp.SPAWN.send(player);
}
@EventHandler
public void onIndfvvCliHGcki4(InventoryClickEvent event) {
	Player player = (Player) event.getWhoClicked();
	if (event.getInventory().getType() == InventoryType.PLAYER) {
        return;
    }
	if (!event.getView().getTitle().equals(Servidores.getInventoryName())) {
		return;
	}
	if (event.getCurrentItem() == null) {
		return;
	}
	if (!(event.getCurrentItem().getType() == Material.GLASS)) {
		return;
	}
	
	if (!ItemBuilder.has(event.getCurrentItem(), "fps")) {
		return;
	}
	event.setCancelled(true);
	
	player.sendMessage(ChatColor.GREEN + "Enviando você para a FPS.");
	player.closeInventory();
	WaveWarp.FPS.send(player);
}

@EventHandler
public void onInEDdfvvClicki4(InventoryClickEvent event) {
	Player player = (Player) event.getWhoClicked();
	if (event.getInventory().getType() == InventoryType.PLAYER) {
        return;
    }
	if (!event.getView().getTitle().equals(Servidores.getInventoryName())) {
		return;
	}
	if (event.getCurrentItem() == null) {
		return;
	}
	if (!(event.getCurrentItem().getType() == Material.BLAZE_ROD)) {
		return;
	}
	
	if (!ItemBuilder.has(event.getCurrentItem(), "duelsbb")) {
		return;
	}
	event.setCancelled(true);
	
	player.sendMessage(ChatColor.GREEN + "Enviando você para o Duels.");
	player.closeInventory();
	WaveWarp.DUELS.send(player);
}
@EventHandler
public void onInEDdfvvClickti4(InventoryClickEvent event) {
	Player player = (Player) event.getWhoClicked();
	if (event.getInventory().getType() == InventoryType.PLAYER) {
        return;
    }
	if (!event.getView().getTitle().equals(Servidores.getInventoryName())) {
		return;
	}
	if (event.getCurrentItem() == null) {
		return;
	}
	if (!(event.getCurrentItem().getType() == Material.IRON_BARS)) {
		return;
	}
	
	if (!ItemBuilder.has(event.getCurrentItem(), "glad22")) {
		return;
	}
	event.setCancelled(true);
	
	player.sendMessage(ChatColor.GREEN + "Enviando você para o Gladiator.");
	player.closeInventory();
	WaveWarp.GLADIATOR.send(player);
}
@EventHandler
public void onInEDdGfvvClicki4(InventoryClickEvent event) {
	Player player = (Player) event.getWhoClicked();
	if (event.getInventory().getType() == InventoryType.PLAYER) {
        return;
    }
	if (!event.getView().getTitle().equals(Servidores.getInventoryName())) {
		return;
	}
	if (event.getCurrentItem() == null) {
		return;
	}
	if (!(event.getCurrentItem().getType() == Material.LAVA_BUCKET)) {
		return;
	}
	
	if (!ItemBuilder.has(event.getCurrentItem(), "lava")) {
		return;
	}
	event.setCancelled(true);
	
	player.sendMessage(ChatColor.GREEN + "Enviando você para o LavaChallenge.");
	player.closeInventory();
	WaveWarp.LAVACHALLENGE.send(player);
}
@EventHandler
public void onInEDdGfbvvClicki4(InventoryClickEvent event) {
	Player player = (Player) event.getWhoClicked();
	if (event.getInventory().getType() == InventoryType.PLAYER) {
        return;
    }
	if (!event.getView().getTitle().equals(Servidores.getInventoryName())) {
		return;
	}
	if (event.getCurrentItem() == null) {
		return;
	}
	if (!(event.getCurrentItem().getType() == Material.COBBLESTONE)) {
		return;
	}
	
	if (!ItemBuilder.has(event.getCurrentItem(), "arenabuild")) {
		return;
	}
	event.setCancelled(true);
	
	player.sendMessage(ChatColor.GREEN + "Enviando você para a Arena com Build.");
	player.closeInventory();
	WaveWarp.ARENABUILD.send(player);
}
}
