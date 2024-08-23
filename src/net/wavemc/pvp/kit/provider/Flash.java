package net.wavemc.pvp.kit.provider;

import java.util.HashSet;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import net.wavemc.core.bukkit.item.ItemBuilder;
import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.kit.WaveKit;
import net.wavemc.pvp.kit.KitHandler;
import net.wavemc.pvp.kit.KitManager;

public class Flash extends KitHandler {

	   @Override
	    public void execute(Player player) {
	        super.execute(player);

	   }
@SuppressWarnings("deprecation")
@EventHandler(priority = EventPriority.LOWEST)
public void onInteract(PlayerInteractEvent event) {
	Player p = event.getPlayer();
	if (event.getItem() == null) {
		return;
	}
	if (event.getItem().getType().equals(Material.MUSHROOM_STEW)) {
		return;
	}
	if (p.getNearbyEntities(5, 5, 5).size() > 0 && p.getNearbyEntities(5, 5, 5) instanceof Player) {
		p.sendMessage("§eNão use o flash aqui!");
		return;
	}
	if (!KitManager.getPlayer(p.getName()).hasKit(WaveKit.FLASH)) {
		return;
	}
	{
		if (!event.hasItem() || !ItemBuilder.has(event.getItem(), "kit-handler", "flash")) return; 
		if (!event.getAction().toString().contains("RIGHT")) 
		return; {
			event.setCancelled(true);
			if (GladiatorListener2.combateGlad.containsKey(p) || net.wavemc.kit2.GladiatorListener.combateGlad.containsKey(p)) {
				p.sendMessage("§cNão use o flash no gladiator.");
				event.setCancelled(true);
				return;
			}
			if (!inCooldown(event.getPlayer())) {
				Block target = p.getTargetBlock(null, 200);
				if (target.getType() != Material.AIR && target.getType() != Material.GLASS) {
					if (target.getRelative(BlockFace.UP).getLocation().getY() > WavePvP.getInstance().getConfig().getInt("SpawnAltura") - 8) {
						p.sendMessage("§cAponte o flash mais para baixo.");
						p.sendMessage("§cEle vai alcançar o spawn.");
						return;
					}
					addCooldown(p, 35);
					p.getWorld().strikeLightningEffect(p.getLocation());
					p.teleport(target.getRelative(BlockFace.UP).getLocation());
				}
			} else {
				sendMessageCooldown(event.getPlayer());
			}
		}
	}
}
}
