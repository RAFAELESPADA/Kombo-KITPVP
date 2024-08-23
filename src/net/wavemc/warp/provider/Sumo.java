package net.wavemc.warp.provider;

import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;
import net.wavemc.core.bukkit.item.ItemBuilder;
import net.wavemc.core.util.WaveCooldown;
import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.kit.KitManager;
import net.wavemc.pvp.kit.KitManager2;
import net.wavemc.pvp.listener.RTP;
import net.wavemc.pvp.warp.WaveWarp;
import net.wavemc.pvp.warp.WarpDuoBattleHandle2;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.metadata.Metadatable;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Sumo extends WarpDuoBattleHandle2 {
	Path path1 = Paths.get(Bukkit.getServer().getWorldContainer().getAbsolutePath() + "/plugins/WaveCore/", "warps.yml");
	File file = new File(path1.toAbsolutePath().toString());
	YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);

    public Sumo() {
    	super("sumo_pos1", "sumo_pos2", "sumo_pos_2_1", "sumo_pos2_2", "sumo_pos_3_1", "sumo_pos3_2");
		
        new BukkitRunnable() {
            @Override
            public void run() {
                if (fastChallenge.size() < 2) {
                    return;
                }
                Player p1 = fastChallenge.get(0);
                
                Player p2 = fastChallenge.get(1);
 
                if (p1 == null || p2 == null) {
                    fastChallenge.remove(p1);
                    fastChallenge.remove(p2);
                    return;
                }
                else  if (p1 == p2) {
                	cancel();
                	fastChallenge.remove(p1);
                	p1.sendMessage(ChatColor.RED + "A procura falhou! Tente novamente.");
                	return;
                }
                else if (Sumo.isNpc(p2) || Sumo.isNpc(p1)) {
                	cancel();
            	fastChallenge.remove(p1);

            	fastChallenge.remove(p2);
            	p1.sendMessage(ChatColor.DARK_RED + "A procura falhou! Tente novamente.");
            	return;
            }
                startBattle(p1, p2);
            }
            
        }
            
            
        
        .runTaskTimer(WavePvP.getInstance(), 0, 2 * 20L);
    }
    public static boolean isNpc(Object object)
    {
        if ( ! (object instanceof Metadatable)) return false;
        Metadatable metadatable = (Metadatable)object;
        try
        {
            return metadatable.hasMetadata("NPC");
        }
        catch (UnsupportedOperationException e)
        {
            // ProtocolLib
            // UnsupportedOperationException: The method hasMetadata is not supported for temporary players.
            return false;
        }      
    }

    @Override
    public void execute(Player player) {
        super.execute(player);
		KitManager.getPlayer(player.getName()).removeKit();
		KitManager2.getPlayer(player.getName()).removekit2();
        setItems(player);
    }
    @EventHandler
	public void C(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		if (event.getPlayer().getItemInHand() == null) {
			return;
		}
		if (event.getPlayer().getItemInHand().hasItemMeta() && event.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals("§bDesafiar §7(Clique)") && WaveWarp.DUELS.hasPlayer(player.getName()) && event.getRightClicked() != null && event.getPlayer().getItemInHand().getItemMeta().getDisplayName() != null) {
        if (Duels.b.containsKey(player)) {
            	startBattle(player , (Player)event.getRightClicked());
        }
		}
    }
    @Override
    public void setItems(Player player) {
        super.setItems(player);
        player.getInventory().setItem(3, new ItemBuilder("§cDesafiar §7(Clique)", Material.APPLE)
                .lore("§fClique em um jogador para desafiar")
                .nbt("cancel-drop")
                .nbt("cancel-click")
                .nbt("sumo", "challenge")
                .toStack()
        );

        boolean searchPlayers = fastChallenge.contains(player);
        player.getInventory().setItem(5, new ItemBuilder("§bProcurar jogador: " + (searchPlayers ? "§aON" : "§cOFF"), (searchPlayers ? Material.LIME_DYE : Material.LIGHT_GRAY_DYE))
                .lore("§fClique para procurar")
                .nbt("cancel-drop")
                .nbt("cancel-click")
                .nbt("sumo", "fast-challenge")
                .toStack()
        );
        player.updateInventory();
    }
    @EventHandler
	public void onInteract22(EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		Player player =  (Player)event.getEntity();
		if (WaveWarp.DUELS.hasPlayer(player.getName()) && !findOpponent(player).isPresent()) {
			if (event.getCause() == DamageCause.FALL) {
				event.setCancelled(true);
			}
		}
    }
	


    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();

        if (!(event.getRightClicked() instanceof Player)
                || !WaveWarp.DUELS.hasPlayer(player.getName())
                || !WaveWarp.DUELS.hasPlayer(event.getRightClicked().getName())
                || player.getItemInHand() == null
                || !ItemBuilder.has(player.getItemInHand(), "sumo", "challenge")) {
            return;
        }
        event.setCancelled(true);

        Player target = (Player) event.getRightClicked();

        if (battlingPlayers.containsKey(target)) {
            player.sendMessage("§cEsse jogador já está lutando");
            return;
        }

        if (WaveCooldown.inCooldown(target.getName(), "sumo-challenge-" + player.getName())) {
            startBattle(player, target);
            return;
        }

        if (WaveCooldown.inCooldown(player.getName(), "sumo-challenge-" + target.getName())) {
            player.sendMessage("§eVocê já convidou este jogador recentemente");
            return;
        }

        WaveCooldown.create(player.getName(), "sumo-challenge-" + target.getName(), TimeUnit.SECONDS, 15);
        target.sendMessage("§e§lSUMO §eVocê foi convidado por " + player.getName() + " para uma batalha!");
        player.sendMessage("§6§lSUMO §6Você convidou " + target.getName() + " para uma batalha!");
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)
                || !(event.getDamager() instanceof Player)) {
            return;
        }

        Player damager = (Player) event.getDamager();
        Player victim = (Player) event.getEntity();

        Optional<Player> targetOptional;
        if (!(targetOptional = findOpponent(damager)).isPresent()) {
            return;
        }

        Player target = targetOptional.get();

        if (target.equals(victim)) {
            event.setDamage(0);
        }
    }
    
    @EventHandler
    public void onInteracct(PlayerInteractEvent event){
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
            Block block = event.getClickedBlock();
            if(block.getType().equals(Material.CHEST) || block.getType().equals(Material.TRAPPED_CHEST))  {
            	if (!block.hasMetadata("PlacedBlock")) {
                event.setCancelled(true);
                event.getPlayer().sendMessage("Você só pode abrir o báu do Feast!");
            }
            }
        }
            }
        
       
    
    
    @EventHandler
    public void onMoveF(PlayerMoveEvent event) {
        Player player = event.getPlayer();
      

        if (!WaveWarp.DUELS.hasPlayer(player.getName())) {
            return;
        }

        if (!findOpponent(player).isPresent()) {
            return;
        }
        if (!(player.getLocation().getY() <= yaml.getInt("sumo_pos1") - 3)) {
        	return;
        }
        Player target = findOpponent(player).get();
        Random random = new Random();
        finalizeBattle(player);

        WavePlayer loserUser = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
        WavePlayer victimHelixPlayer = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
        WaveWarp.DUELS.send(player);

        
        if ((victimHelixPlayer.getPvp().getXp() - 10) >= 0) {
			victimHelixPlayer.getPvp().setXp(victimHelixPlayer.getPvp().getXp() - 10);
			player.sendMessage("§c§l[-] §c10 XP");
		}else {
			victimHelixPlayer.getPvp().setXp(0);
			player.sendMessage("§c§l[-] " + victimHelixPlayer.getPvp().getXp() + " XP");
		}
        WavePlayer killerUser = WaveBukkit.getInstance().getPlayerManager().getPlayer(target.getName());
        int winnerCoins = random.nextInt(80 + 1 - 25) + 25;
        WaveWarp.DUELS.send(target);
        target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10.0f, 10.0f);
        target.sendMessage("§6§lSUMO §eVocê ganhou a luta contra " + player.getName());
        killerUser.getPvp().addCoins(winnerCoins);
        killerUser.getPvp().addWinsSumo(1);;
        killerUser.getPvp().addWinstreakSumo(1);
        killerUser.getPvp().addXP(15);
        killerUser.getPvp().addKills(1);
        target.sendMessage("§6§l[+] §6" + winnerCoins + " coins");
        target.sendMessage("§6§l[+] §a15XP");
        
        loserUser.getPvp().addDeathsSumo(1);
        player.sendMessage("§4§lSUMO §4Você perdeu a luta contra " + target.getName());
        WavePlayer killerAccount = WaveBukkit.getInstance().getPlayerManager().getPlayer(target.getName());
		
		int killstreak = killerAccount.getPvp().getWinstreaksumo();
		if (String.valueOf(killstreak).contains("5") || (String.valueOf(killstreak).contains("0") || (String.valueOf(killstreak).contains("3")) && killstreak != 0)) {
			Bukkit.broadcastMessage("§6§lWINS §e" + target.getName() + " tem um killstreak de §b" + killstreak + "§e no Sumo!");
		}
		WavePlayer victimA = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
		int killstreak2 = victimA.getPvp().getWinstreaksumo();
		if (killstreak2 >= 3) {
			RTP.broadcast("§6" + victimA.getName() + " §eperdeu seu winstreak de §6" + victimA.getPvp().getWinstreaksumo() + " §e no Sumo para §6" +
	                killerAccount.getName() + "§e!" , target.getWorld());
			victimA.getPvp().setWinstreaksumo(0);
		}
        WaveBukkit.getInstance().getPlayerManager().getController().save(loserUser);
        WaveBukkit.getInstance().getPlayerManager().getController().save(killerUser);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);

        if (!WaveWarp.DUELS.hasPlayer(player.getName())) {
            return;
        }

        if (!block.getType().toString().contains("WATER") || !findOpponent(player).isPresent()) {
            return;
        }

        Player target = findOpponent(player).get();
        Random random = new Random();
        finalizeBattle(player);

        WavePlayer loserUser = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
        WavePlayer victimHelixPlayer = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
        WaveWarp.DUELS.send(player);

        
        if ((victimHelixPlayer.getPvp().getXp() - 10) >= 0) {
			victimHelixPlayer.getPvp().setXp(victimHelixPlayer.getPvp().getXp() - 10);
			player.sendMessage("§c§l[-] §c10 XP");
		}else {
			victimHelixPlayer.getPvp().setXp(0);
			player.sendMessage("§c§l[-] " + victimHelixPlayer.getPvp().getXp() + " XP");
		}
        WavePlayer killerUser = WaveBukkit.getInstance().getPlayerManager().getPlayer(target.getName());
        int winnerCoins = random.nextInt(80 + 1 - 25) + 25;
        WaveWarp.DUELS.send(target);
        target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10.0f, 10.0f);

        target.sendMessage("§a§lSUMO §aVocê ganhou a luta contra " + player.getName());
        killerUser.getPvp().addCoins(winnerCoins);
        killerUser.getPvp().addWinsSumo(1);;
        killerUser.getPvp().addWinstreakSumo(1);
        killerUser.getPvp().addXP(15);
        killerUser.getPvp().addKills(1);
        target.sendMessage("§6§l[+] §6" + winnerCoins + " coins");
        target.sendMessage("§6§l[+] §a15XP");
        
        loserUser.getPvp().addDeathsSumo(1);
        player.sendMessage("§c§lSUMO §cVocê perdeu a luta contra " + target.getName());
WavePlayer killerAccount = WaveBukkit.getInstance().getPlayerManager().getPlayer(target.getName());
		
		int killstreak = killerAccount.getPvp().getWinstreaksumo();
		if (String.valueOf(killstreak).contains("5") || (String.valueOf(killstreak).contains("0") || (String.valueOf(killstreak).contains("3")) && killstreak != 0)) {
			RTP.broadcast("§6§lWINS §e" + target.getName() + " tem um winstreak de §b" + killstreak + "§e no Sumo!", target.getWorld());
		}
		WavePlayer victimA = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
		int killstreak2 = victimA.getPvp().getWinstreaksumo();
		if (killstreak2 >= 3) {
			Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("§6" + victimA.getName() + " §eperdeu seu winstreak de §6" + victimA.getPvp().getWinstreaksumo() + " §e no Sumo para §6" +
	                killerAccount.getName() + "§e!"));
			victimA.getPvp().setWinstreaksumo(0);
        WaveBukkit.getInstance().getPlayerManager().getController().save(loserUser);
       WaveBukkit.getInstance().getPlayerManager().getController().save(killerUser);
    }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        Optional<Player> targetOptional;
        if (!(targetOptional = findOpponent(player)).isPresent()) {
            return;
        }
        Player target = targetOptional.get();
        finalizeBattle(player);

        WaveWarp.DUELS.send(target);
        target.sendMessage("§2Seu oponente deslogou");
    }
}