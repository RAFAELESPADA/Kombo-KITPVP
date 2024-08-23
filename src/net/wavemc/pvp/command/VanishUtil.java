package net.wavemc.pvp.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.pvp.kit.provider.WaveActionBar;
public class VanishUtil {
	  private static final List<String> players = new ArrayList<>();
	  
	  public static List<String> getPlayers() {
	    return players;
	  }
static {
	  WaveBukkit.getExecutorService().submit(() -> {
          new BukkitRunnable() {
              @Override
              public void run() {
                  players.stream().filter(
                          player -> Bukkit.getPlayer(player) != null
                  ).forEach(username ->  {
                      Player player = Bukkit.getPlayer(username);
                      WaveActionBar.send(player, "§aVocê está em modo vanish. §f(§a" + players.size() + " §fstaffers no modo vanish)");
                          Bukkit.getOnlinePlayers().forEach(online -> {
                              if (!online.hasPermission("wave.vervanish")) {
                                  online.hidePlayer(player);
                              }

                          });
                  });

              };
          }.runTaskTimer(WaveBukkit.getInstance(), 0, 2 * 20L);
      });
  }
		  
public static boolean has(String username) {
    return players.contains(username);
  }
  
  public static void add(String username) {
    if (!has(username))
      players.add(username); 
  }
  
  public static void remove(String username) {
    players.remove(username);
  }
}