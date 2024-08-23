package net.wavemc.pvp.warp;

import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.pvp.kit.KitManager;
import net.wavemc.pvp.kit.KitManager2;
import net.wavemc.pvp.warp.WaveWarp;
import net.wavemc.pvp.warp.WarpHandle;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LavaChallenge extends WarpHandle {
	
	@Override
	public void execute(Player player) {
		super.execute(player);
		ItemStack vermelho = new ItemStack(Material.RED_MUSHROOM, 64);
	
		KitManager.getPlayer(player.getName()).removeKit();
		KitManager2.getPlayer(player.getName()).removekit2();
		  ItemStack marrom = new ItemStack(Material.BROWN_MUSHROOM, 64);
		  
		  
		  ItemStack item = new ItemStack(Material.BOWL, 64);

		  player.getInventory().setItem(13, item);
		  for (int i = 0; i < 36; i++) {
				player.getInventory().addItem(new ItemStack(Material.MUSHROOM_STEW));
			}
		  player.getInventory().setItem(14, vermelho);
		  player.getInventory().setItem(15, marrom);
		  player.getInventory().setItem(16, vermelho);

		  player.getInventory().setItem(17, marrom);
	}

	@EventHandler
	public void onSignChange(SignChangeEvent event) {
		Player player = event.getPlayer();
		String firstLine = event.getLine(0);
		System.out.println("SIGN " + firstLine);

		if (player.hasPermission("wave.sign") && firstLine.startsWith("lc")) {
			String[] lineSplit;
			if ((lineSplit = firstLine.split(" ")).length != 2) {
				return;
			}

			int level;
			try {
				level = Integer.parseInt(lineSplit[1]);
			}catch (NumberFormatException ignored) {
				player.sendMessage("§c§lSIGN §cLevel invalido");
				event.setCancelled(true);
				return;
			}

			if (level == 0 || level > 4) {
				player.sendMessage("§c§lSIGN §cColoque um level de 1 a 4");
				event.setCancelled(true);
				return;
			}

			event.setLine(0, "");
			event.setLine(1, "§c§lLEVEL " + level);
			event.setLine(2, "§b(Clique)");
			event.setLine(3, "");
		}
	}

	@EventHandler
	public void onSignOpen(PlayerInteractEvent event) {
		Player player = event.getPlayer();

		if (event.getClickedBlock() != null && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)
				&& (event.getClickedBlock().getState() instanceof Sign))  {
			Sign sign = (Sign) event.getClickedBlock().getState();
			String[] lines = sign.getLines();

			String levelLine = ChatColor.stripColor(lines[1]);
			String[] lineSplit;

			if ((lineSplit = levelLine.split(" ")).length != 2) {
				return;
			}

			int level;
			try {
				level = Integer.parseInt(lineSplit[1]);
			}catch (NumberFormatException ignored) {
				player.sendMessage("§c");
				player.sendMessage("§c§lSIGN §cLevel invalido");
				return;
			}

			int coins = level * 100;
			int xp = level * 20;
			Bukkit.broadcastMessage("§5§lCHALLENGE §7" + player.getName() + " passou o Lava Challenge! §5§l(LEVEL " + level + ")");
           
			WaveWarp.LAVACHALLENGE.send(player);

			WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName()).getPvp().addChallege(1);
		WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName()).getPvp().addCoins(coins);
			WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName()).getPvp().setXp(WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName()).getPvp().getXp() + xp);
			WaveBukkit.getInstance().getPlayerManager().getController().save(WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName()));
			player.sendMessage("§6§l[+] §a " + xp + "XP");
			player.playSound(player.getLocation(), Sound.BLOCK_GLASS_PLACE, 10.0f, 10.0f);
			player.sendMessage("§6§lCHALLENGE §6Você completou o LEVEL " + level + " e ganhou " + coins + " coins!");
		}
	}
}
