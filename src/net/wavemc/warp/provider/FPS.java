package net.wavemc.warp.provider;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.item.ItemBuilder;
import net.wavemc.pvp.kit.KitManager;
import net.wavemc.pvp.kit.KitManager2;
import net.wavemc.pvp.listener.Jump;
import net.wavemc.pvp.warp.WarpHandle;
import net.wavemc.pvp.warp.WaveWarp;

public class FPS extends WarpHandle {
	
	@Override
	public void execute(Player player) {
		super.execute(player);
		player.getInventory().clear();
	    Jump.recebeu.remove(player.getName());
	    if (Duels.protector.containsKey(player.getName())) {
	        Duels.protector.remove(player.getName());
	        }
		KitManager.getPlayer(player.getName()).removeKit();
		KitManager2.getPlayer(player.getName()).removekit2();
		 player.getInventory().setItem(8, new ItemBuilder("§bRetornar §7(Clique)", Material.RED_BED)
					.nbt("cancel-drop")
					.nbt("cancel-click")
					.nbt("voltar", "spawn")
					.toStack()
			);
	}

@EventHandler
public void onInteract22(EntityDamageByEntityEvent event) {
	if (!(event.getEntity() instanceof Player)) {
		return;
	}
	Player player =  (Player)event.getEntity();
		if (Jump.recebeu.containsKey(player.getName())) {
			return;
		}
		else if (WaveWarp.FPS.hasPlayer(player.getName())) {
		event.setCancelled(true);
	}
}
	@EventHandler
	public void onInteract22(PlayerDropItemEvent event) {
		
		Player player = event.getPlayer();
			
		if (WaveWarp.LOBBY.hasPlayer(player.getName()) && (event.getItemDrop().getItemStack().getType() == Material.PLAYER_HEAD || event.getItemDrop().getItemStack().getType() == Material.COMPASS)) {
			event.setCancelled(true);
		}
		}

}