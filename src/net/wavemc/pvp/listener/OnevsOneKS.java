package net.wavemc.pvp.listener;



import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;
import net.wavemc.pvp.event.WavePlayerDeathEvent;
import net.wavemc.pvp.warp.WaveWarp;


public class OnevsOneKS implements Listener {
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerDeath(WavePlayerDeathEvent event) {
		if (!event.isValidKill() || !event.hasKiller()) {
			return;
		}
		
		Player killer = event.getKiller();
		if (!WaveWarp.DUELS.hasPlayer(killer.getName())) {
			return;
		}
		WavePlayer killerAccount = WaveBukkit.getInstance().getPlayerManager().getPlayer(killer.getName());
		
		int killstreak = killerAccount.getPvp().getWinstreakx1();
		if (String.valueOf(killstreak).contains("5") || (String.valueOf(killstreak).contains("0")) && killstreak != 0) {
			RTP.broadcast("§6§lWINS §e" + killer.getName() + " tem um winstreak de §b" + killstreak + "§e na 1v1!", killer.getWorld());
		}
		
		Player victim = event.getPlayer();
		WavePlayer victimA = WaveBukkit.getInstance().getPlayerManager().getPlayer(victim.getName());
		int killstreak2 = victimA.getPvp().getWinstreakx1();
		if (killstreak2 >= 3) {
			RTP.broadcast("§6" + victimA.getName() + " §eperdeu seu winstreak de §6" + victimA.getPvp().getWinstreakx1() + " §e na 1V1 para §6" +
	                killer.getName() + "§e!", killer.getWorld());
		}
		victimA.getPvp().setWinstreakx1(0);
	}
	public static void checkKillStreakLose(int winstreak, Player killer, String victim) {
		WavePlayer victimA = WaveBukkit.getInstance().getPlayerManager().getPlayer(victim);
	
		if (winstreak >= 3) {
			Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("§6" + victimA.getName() + " §eperdeu seu winstreak de §6" + victimA.getPvp().getKillstreak() + " §e na Sumo para §6" +
	                killer.getName() + "§e!"));
		}
}
}