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


public class SumoKillStreak implements Listener {
		

			
	
			
			

		
		
	@EventHandler(priority = EventPriority.MONITOR)
	public void destrlçoySumo(Player killer) {

		if (!WaveWarp.DUELS.hasPlayer(killer.getName())) {
			return;
		}
		
	}
	public static void checkKijllStreakLose(int winstreak, Player killer, String victim) {
		WavePlayer victimA = WaveBukkit.getInstance().getPlayerManager().getPlayer(victim);
	
		if (winstreak >= 3) {
			Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("§6" + victimA.getName() + " §eperdeu seu winstreak de §6" + victimA.getPvp().getKillstreak() + " §e na Sumo para §6" +
	                killer.getName() + "§e!"));
		}
}
}