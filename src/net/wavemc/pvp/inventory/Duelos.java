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


public class Duelos implements Listener {

	private final static String inventoryName = "Estatísticas - Duelos";
	
	public static void open(Player player, Player target) {
		Inventory inventory = Bukkit.createInventory(null, 27, inventoryName);

   	 WavePlayer helixPlayer = WaveBukkit.getInstance().getPlayerManager()
				.getPlayer(target.getName());
   	PlayerPvP pvp = helixPlayer.getPvp();

		inventory.setItem(12, new ItemBuilder("§cSoup 1v1", Material.MUSHROOM_STEW).nbt("swertgh").lore("§fWins na 1V1: §a" + pvp.getWinsx1() ,"§fPerdas na 1V1: §a" + pvp.getDeathsx1() ,"§fWinStreak na 1V1: §a" + pvp.getWinstreakx1())
				.toStack()
		);
		inventory.setItem(14, new ItemBuilder("§cSumô", Material.LEAD).nbt("swertgh").lore("§fWins no Sumô: §a" + pvp.getWinssumo() ,"§fPerdas no Sumô: §a" + pvp.getDeathssumo() ,"§fWinStreak na Sumô: §a" + pvp.getWinstreaksumo()).toStack());
		
		
		player.openInventory(inventory);
	}
	public static String getInventoryName() {
		return inventoryName;
	}
}
