package net.wavemc.warp.provider;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import net.wavemc.core.bukkit.api.ActionBar;
import net.wavemc.core.bukkit.item.ItemBuilder;
import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.cooldown2.WaveCooldown2;
import net.wavemc.pvp.kit.KitManager;
import net.wavemc.pvp.kit.KitManager2;
import net.wavemc.pvp.listener.Jump;
import net.wavemc.pvp.warp.WarpHandle;
import net.wavemc.pvp.warp.WaveWarp;

public class Lobby2 extends WarpHandle {
	
	@Override
	public void execute(Player player) {
		super.execute(player);
		player.getInventory().clear();
		    WaveCooldown2.removeCooldown(player, KitManager2.getPlayer(player.getName()).getkit2().getName());
		    net.wavemc.pvp.cooldown1.WaveCooldown2.removeCooldown(player, KitManager.getPlayer(player.getName()).getKit().getName());
	    Jump.recebeu.remove(player.getName());
	    if (Duels.protector.containsKey(player.getName())) {
	        Duels.protector.remove(player.getName());
	        }
		KitManager.getPlayer(player.getName()).removeKit();
		KitManager2.getPlayer(player.getName()).removekit2();
		 player.getInventory().setItem(3, new ItemBuilder("§bModos de jogo §7(Clique)", Material.COMPASS)
					.nbt("cancel-drop")
					.nbt("cancel-click")
					.nbt("modos", "spawn")
					.toStack()
			);
		 player.getInventory().setItem(5, new ItemBuilder(getHead(player))
					.nbt("cancel-drop")
					.nbt("cancel-click")
					.nbt("modos", "spawn")
					.toStack()
			);
		 player.getInventory().setItem(4, new ItemBuilder("§bKangarinho §7(Clique)", Material.FIREWORK_ROCKET)
					.nbt("cancel-drop")
					.nbt("cancel-click")
					.nbt("kit-handler", "kangaroo")
					.toStack()
			);
	}
	public static ItemStack getHead(Player player) {
	      ItemStack item = new ItemStack(Material.PLAYER_HEAD);
	      SkullMeta skull = (SkullMeta) item.getItemMeta();
	      skull.setDisplayName("§aSeu perfil §7(Clique)");
	      ArrayList<String> lore = new ArrayList<String>();
	      lore.add("§7Clique para ver seu perfil");
	      skull.setLore(lore);
	      skull.setOwner(player.getName());
	      item.setItemMeta(skull);
	      return item;
	  }

@EventHandler
public void onInteract22(EntityDamageByEntityEvent event) {
	if (!(event.getEntity() instanceof Player)) {
		return;
	}
	Player player =  (Player)event.getEntity();
		
	if (WaveWarp.LOBBY.hasPlayer(player.getName()) && !player.getWorld().equals(Bukkit.getWorld(WavePvP.getInstance().getConfig().getString("EVENTOSPAWNMUNDO")))) {
		event.setCancelled(true);
	}
		}


@EventHandler
public void onInteract22V(EntityDamageEvent event) {
	if (!(event.getEntity() instanceof Player)) {
		return;
	}
	Player player =  (Player)event.getEntity();
		
	if (WaveWarp.LOBBY.hasPlayer(player.getName()) && !player.getWorld().equals(Bukkit.getWorld(WavePvP.getInstance().getConfig().getString("EVENTOSPAWNMUNDO")))) {
		event.setCancelled(true);
	}
		}

}