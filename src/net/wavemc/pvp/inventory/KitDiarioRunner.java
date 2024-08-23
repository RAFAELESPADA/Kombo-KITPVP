package net.wavemc.pvp.inventory;



	import java.util.Calendar;

	import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.wavemc.pvp.WavePvP;



	public class KitDiarioRunner {
		
		public  KitDiarioRunner() {
			
			new BukkitRunnable() {
				public void run() {
					Calendar calendar1 = Calendar.getInstance();
					int hora = calendar1.get(Calendar.HOUR_OF_DAY);
					int minutos = calendar1.get(Calendar.MINUTE);
					int segundos = calendar1.get(Calendar.SECOND);
					if (hora == 00 && minutos == 00 && segundos == 00) {
					for (Player p : Bukkit.getOnlinePlayers()) {
						KitDiario.setandokit.remove(p.getName());
						
					}
					KitDiario.setandokit.clear();
					}
				}
			}.runTaskTimer(WavePvP.getInstance(), 0, 1 * 20);
		}
	}

