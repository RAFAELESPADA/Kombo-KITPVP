package net.wavemc.pvp.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;
import net.wavemc.pvp.event.WavePlayerDeathEvent;


public class PlayerKillstreakListener implements Listener {
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerDeath(WavePlayerDeathEvent event) {
		if (!event.isValidKill() || !event.hasKiller()) {
			return;
		}
		
		Player killer = event.getKiller();
		WavePlayer killerAccount = WaveBukkit.getInstance().getPlayerManager().getPlayer(killer.getName());
		
		int killstreak = killerAccount.getPvp().getKillstreak();
		if (String.valueOf(killstreak).equals("2")) {
			RTP.broadcast("§6§lKOMBO §e" + killer.getName() + " fez um §4§lDOUBLEKILL!", killer.getWorld());
		}
		if (String.valueOf(killstreak).equals("3")) {
			RTP.broadcast("§6§lKOMBO §e" + killer.getName() + " fez um §4§lTRIPLEKILL!", killer.getWorld());
		}
		if (String.valueOf(killstreak).equals("4")) {
			RTP.broadcast("§6§lKOMBO §e" + killer.getName() + " fez um §4§lQUADRAKILL!", killer.getWorld());
		}
		if (String.valueOf(killstreak).equals("8")) {
			RTP.broadcast("§6§lKOMBO §e" + killer.getName() + " está fazendo um massacre co m§4§l8 KILLS §econsecutivas!", killer.getWorld());
		}
		if (String.valueOf(killstreak).contains("5") || (String.valueOf(killstreak).contains("0")) && killstreak != 0) {
			RTP.broadcast("§6§lKS §e" + killer.getName() + " tem um killstreak de §b" + killstreak + "§e!", killer.getWorld());
		}
		
		Player victim = event.getPlayer();
		WavePlayer victimA = WaveBukkit.getInstance().getPlayerManager().getPlayer(victim.getName());
		int killstreak2 = victimA.getPvp().getKillstreak();
		if (killstreak2 >= 3) {
			RTP.broadcast("§6" + victim.getName() + " §eperdeu seu killstreak de §6" + victimA.getPvp().getKillstreak() + " §epara §6" +
	                killer.getName() + "§e!" , killer.getWorld());
		}
		victimA.getPvp().setKillstreak(0);
	}
	
}
