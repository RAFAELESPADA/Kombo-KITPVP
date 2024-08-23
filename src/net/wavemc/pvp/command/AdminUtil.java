package net.wavemc.pvp.command;



import lombok.Getter;
import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.pvp.kit.provider.WaveActionBar;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.ArrayList;
import java.util.List;

public class AdminUtil {

    @Getter private final static List<String> players = new ArrayList();

    static {
        WaveBukkit.getExecutorService().submit(() -> {
            new BukkitRunnable() {
                @Override
                public void run() {
                    players.stream().filter(
                            player -> Bukkit.getPlayer(player) != null
                    ).forEach(username ->  {
                        Player player = Bukkit.getPlayer(username);
                        WaveActionBar.send(player, "§aVocê está invisível para os jogadores. §f(" + players.size() + " staff no modo Admin)");
                            Bukkit.getOnlinePlayers().forEach(online -> {
                                if (!online.hasPermission("wave.cmd.admin")) {
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
        if (!has(username)) {
            players.add(username);
        }
    }

    public static void remove(String username) {
        players.remove(username);
    }
}