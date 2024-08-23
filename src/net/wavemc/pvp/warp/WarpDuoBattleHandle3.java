package net.wavemc.pvp.warp;


import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.util.AdminUtil;
import net.wavemc.core.util.WaveCooldown;
import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.command.NoBreakEvent;
import net.wavemc.pvp.inventory.Modo;
import net.wavemc.pvp.kit.provider.GladiatorListener2;
import net.wavemc.warp.provider.Duels;
import net.wavemc.warp.provider.Gladiator;
import net.wavemc.warp.provider.OneVsOne;
import net.wavemc.warp.provider.Sumo;

public abstract class WarpDuoBattleHandle3 extends WarpHandle {

    private final String warpPos1, warpPos2;

    public WarpDuoBattleHandle3(String warpPos1, String warpPos2) {
        this.warpPos1 = warpPos1;
        this.warpPos2 = warpPos2;
    }
    Path path1 = Paths.get(Bukkit.getServer().getWorldContainer().getAbsolutePath() + "/plugins/WaveCore/", "warps.yml");
	File file = new File(path1.toAbsolutePath().toString());
	YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
    public static List<Player> fastChallenge = new ArrayList<>();
    protected final static HashMap<Player, Player> battlingPlayers = new HashMap<>();
    public static final boolean estabatalhando(Player player) {
        return battlingPlayers.containsKey(player);
    }
    public void setItems(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
    }

    public final Optional<Player> findOpponent(Player player) {
        return battlingPlayers.containsKey(player) ? Optional.of(battlingPlayers.get(player)) :
                battlingPlayers.entrySet().stream().filter(entry -> entry.getValue().equals(player)).map(Map.Entry::getKey).findFirst();
    }

    public final void finalizeBattle(Player player) {
        show(player);


        findOpponent(player).ifPresent(target -> {
            show(target);

        });
        fastChallenge.remove(player);
        battlingPlayers.entrySet().removeIf(entry -> entry.getKey().equals(player) || entry.getValue().equals(player));
    }

    public void sendBattleItems(Player player) {
        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setAllowFlight(false);
        player.setFlying(false);
        player.getInventory().setHeldItemSlot(0);
        player.getActivePotionEffects().forEach(potion -> player.removePotionEffect(potion.getType()));
    }

    public void startBattle(Player p1, Player p2) {
        fastChallenge.remove(p1); fastChallenge.remove(p2);
Duels.protector.remove(p1.getName());
Duels.protector.remove(p2.getName());
        Sumo.fastChallenge.remove(p1); Sumo.fastChallenge.remove(p2);
        OneVsOne.fastChallenge.remove(p1); OneVsOne.fastChallenge.remove(p2);
        Gladiator.fastChallenge.remove(p1);  Gladiator.fastChallenge.remove(p2);
        p1.setHealth(p1.getMaxHealth());
        p2.setHealth(p2.getMaxHealth());
if (p1.getLocation().distance(p2.getLocation()) > 80) {
	finalizeBattle(p1);
	p1.sendMessage("BATALHA FOI FINALIZADA PORQUE VOCÊ ESTAVA SOZINHO NO GLAD!");
	return;
}
if (p1 == p2) {
	finalizeBattle(p1);
	p1.sendMessage("BATALHA FOI FINALIZADA PORQUE VOCÊ ESTAVA SOZINHO NO GLAD!");
	return;
}
else if (Sumo.isNpc(p2) || Sumo.isNpc(p1)) {
	
fastChallenge.remove(p1);

finalizeBattle(p1);
fastChallenge.remove(p2);
p1.sendMessage(ChatColor.DARK_RED + "A procura falhou! Tente novamente.");
return;
}
        Random ran = new Random();

        int x = ran.nextInt(1200);
        int y = ran.nextInt(130);
        int z = ran.nextInt(1200);
        Location newloc = new Location(Bukkit.getWorld(yaml.getString("Mundo-duels")), x, y, z);
        GladiatorListener2.newGladiatorListenerArena(p1, p2, newloc);
        GladiatorListener2.combateGlad.put(p1, p2);
        GladiatorListener2.combateGlad.put(p2, p1);
NoBreakEvent.embuild.add(p2);
NoBreakEvent.embuild.add(p1);
        sendBattleItems(p1); sendBattleItems(p2);
        hide(p1, p2);


Duels.c.remove(p1);
Duels.c.remove(p2);
Duels.b.remove(p2);
Duels.b.remove(p1);
Duels.glad.remove(p2);
Duels.glad.remove(p1);
        battlingPlayers.put(p1, p2);
    }

    public void hide(Player p1, Player p2) {
        Bukkit.getOnlinePlayers().stream().filter(
                online -> !online.getName().equals(p1.getName())
                        && !online.getName().equals(p2.getName())
        ).forEach(online -> {
            p1.hidePlayer(online);
            p2.hidePlayer(online);
        });
    }
	@EventHandler
	public void onInvClicki4(InventoryClickEvent event) {
		if (event.getInventory().getType() == InventoryType.PLAYER) {
            return;
        }
		Player p1 = (Player) event.getWhoClicked();
		Player player = (Player) event.getWhoClicked();
		if (!event.getView().getTitle().equals(Modo.getInventoryName())) {
			return;
		}
		if (event.getCurrentItem() == null) {
			return;
		}
		if (!(event.getCurrentItem().getType() == Material.IRON_BARS)) {
			return;
		}
		if (Sumo.fastChallenge.contains(player) || OneVsOne.fastChallenge.contains(player) || Gladiator.fastChallenge.contains(player)) {
			player.sendMessage(ChatColor.RED + "Você já está em uma fila");
			player.sendMessage(ChatColor.RED + "Para sair da fila use /spawn");
			player.closeInventory();
			event.setCancelled(true);
			return;
		}
		event.setCancelled(true);
		Player p2 = Duels.duels.get(p1);
		if (Duels.duels.containsKey(p1)) {
			p2.sendMessage(ChatColor.GREEN + "Você foi desafiado por " + p1.getName() + " para um Gladiator");
			player.sendMessage(ChatColor.GREEN + "Você desafiou " + p2.getName());
			Duels.glad.put(p2, player);
			player.closeInventory();
			WaveCooldown.create(player.getName(), "1v1g-challenge-" + p2.getName(), TimeUnit.SECONDS, 15);
			WaveBukkit.getExecutorService().submit(() -> {
				new BukkitRunnable() {
					@Override
					public void run() {
						Duels.glad.remove(p2);
						player.closeInventory();
					}}.runTaskLater(WavePvP.getInstance(), 100 * 1L);
			});
			    }
	}
    public void show(Player player) {
        Bukkit.getOnlinePlayers().stream().filter(
                online -> !AdminUtil.has(online.getName())
        ).forEach(player::showPlayer);
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)
                || (!(event.getDamager() instanceof Player))) {
            return;
        }

        Player victim = (Player) event.getEntity();
        Player damager = (Player) event.getDamager();

        if (battlingPlayers.containsKey(victim) && !battlingPlayers.get(victim).getName().equals(damager.getName())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerCommandProcess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        if (fastChallenge.contains(player) && event.getMessage().split(" ")[0].equalsIgnoreCase("spawn")) {
            fastChallenge.remove(player);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        battlingPlayers.forEach((p1, p2)-> {
            p1.hidePlayer(event.getPlayer());
            p2.hidePlayer(event.getPlayer());
        });
    }
}

