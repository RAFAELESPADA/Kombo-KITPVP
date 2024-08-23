package net.wavemc.pvp.command;

import java.util.concurrent.TimeUnit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

import net.wavemc.core.util.WaveCooldown;
import net.wavemc.pvp.WavePvP;

public class ScoreboardCMD implements CommandExecutor {
	
	private final WavePvP plugin;

	public ScoreboardCMD(WavePvP plugin) {
		this.plugin = plugin;
	}


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		
		if (WaveCooldown.inCooldown(sender.getName(), "scoreboard-cmd"))  {
			sender.sendMessage("§cEspere " + WaveCooldown.getTime(sender.getName(), "scoreboard-cmd") + "s para usar novamente.");
			return true;
		}
		
		Player player = (Player) sender;
		boolean enable = player.getScoreboard().getObjective(DisplaySlot.SIDEBAR) == null;
		
		if (enable) {
			plugin.getScoreboardBuilder().build(player);
		}else {
			player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
		}
		
		WaveCooldown.create(sender.getName(), "scoreboard-cmd", TimeUnit.SECONDS, 5);
		player.sendMessage("§bScoreboard " + (enable ? "§aON" : "§cOFF"));
		return true;
	}

}
