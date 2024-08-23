package net.wavemc.pvp.inventory;




import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;
import net.wavemc.core.bukkit.account.provider.PlayerPvP;
import net.wavemc.core.bukkit.item.ItemBuilder;


public class Extras implements Listener {

	private final static String inventoryName = "Extras";
	
	public static void open(Player player, Player target) {
		Inventory inventory = Bukkit.createInventory(null, 27, inventoryName);

   	 WavePlayer helixPlayer = WaveBukkit.getInstance().getPlayerManager()
				.getPlayer(target.getName());
   	PlayerPvP pvp = helixPlayer.getPvp();

		inventory.setItem(12, new ItemBuilder("§aMudar estilo de sopa", Material.MUSHROOM_STEW).nbt("penisgrande")
				.toStack()
		);
		inventory.setItem(14, new ItemBuilder("§aEstatísticas gerais", Material.DIAMOND_SWORD).nbt("penisgrande2").toStack());
		
		
		player.openInventory(inventory);
	}
	public static String getInventoryName() {
		return inventoryName;
	}
}
