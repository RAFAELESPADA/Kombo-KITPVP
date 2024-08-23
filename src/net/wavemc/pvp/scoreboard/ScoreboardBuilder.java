package net.wavemc.pvp.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;
import net.wavemc.core.bukkit.format.WaveDecimalFormat;
import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.kit.WaveKit2;
import net.wavemc.pvp.kit.KitManager;
import net.wavemc.pvp.kit.KitManager2;
import net.wavemc.pvp.kit.PlayerPvP;
import net.wavemc.pvp.kit.WaveKit;
import net.wavemc.pvp.kit.provider.GladiatorListener2;
import net.wavemc.pvp.listener.EventoUtils;
import net.wavemc.pvp.listener.Ranking;
import net.wavemc.pvp.warp.WaveWarp;

public class ScoreboardBuilder {

	public ScoreboardBuilder(WavePvP plugin) {
		new ScoreboardTask(this).runTaskTimer(plugin, 0, 3 * 20L);
	}
private static String text = "";
private static String text2 = "";
private static String text3 = "";
private static String text4 = "";
private static String text5 = "";
private static String text6 = "";
private static String textlobby = "";
private static String text7 = "";
private static String text9 = "";

private static WaveAnimation waveAnimation44;
private static String textg = "";
private static String textGG = "";

private static String textB = "";
private static WaveAnimation waveAnimation;
private static WaveAnimation waveAnimation2;
private static WaveAnimation waveAnimation3;
private static WaveAnimation waveAnimation4;
private static WaveAnimation waveAnimation5;
private static WaveAnimation waveAnimation6;
private static WaveAnimation waveAnimation7;
private static WaveAnimation waveAnimation8;
private static WaveAnimation waveAnimation9;
private static WaveAnimation waveAnimation99;

private static WaveAnimation waveAnimationGG;
public static void init() {

    waveAnimation44 = new WaveAnimation("BUILD" , "§b§l" , "§f§l" , "§e§l");
    waveAnimation = new WaveAnimation("ARENA" , "§b§l" , "§f§l" , "§e§l");
    waveAnimation2 = new WaveAnimation("EVENTO" , "§b§l" , "§3§l" , "§f§l");
    waveAnimation3 = new WaveAnimation("FPS" , "§b§l" , "§f§l" , "§e§l");
    waveAnimation4 = new WaveAnimation("DUELOS" , "§b§l" , "§f§l" , "§e§l");
    waveAnimation5 = new WaveAnimation("LAVA" , "§b§l" , "§f§l" , "§e§l");
    waveAnimation6 = new WaveAnimation("SUMO" , "§b§l" , "§f§l" , "§e§l");
    waveAnimation7 = new WaveAnimation("KNOCKBACK" , "§3§l" , "§f§l" , "§b§l");
    waveAnimation8 = new WaveAnimation("GLADIATOR" , "§b§l" , "§f§l" , "§b§l");
    waveAnimation9 = new WaveAnimation("POTIONPVP" , "§4§l" , "§f§l" , "§c§l");
    waveAnimationGG = new WaveAnimation("ARENA GLADIATOR" , "§3§l" , "§f§l" , "§b§l");


    waveAnimation2 = new WaveAnimation("EVENTO" , "§b§l" , "§3§l" , "§f§l");
    waveAnimation99 = new WaveAnimation("PVP" , "§b§l" , "§f§l" , "§b§l");
    text = "ARENA";
    textlobby = "PVP";
    text3 = "FPS";
    textg = "GLADIATOR";

    textGG = "ARENA GLADIATOR";
    textB = "BUILD";
    text2 = "EVENTO";
    text5 = "LAVA";
    text4 = "DUELOS";
    Bukkit.getScheduler().runTaskTimer(WavePvP.getInstance(), new Runnable() {
          public void run() {
            ScoreboardBuilder.text = ScoreboardBuilder.waveAnimation.next();
            ScoreboardBuilder.text2 = ScoreboardBuilder.waveAnimation2.next();
            ScoreboardBuilder.text3 = ScoreboardBuilder.waveAnimation3.next();
            ScoreboardBuilder.text4 = ScoreboardBuilder.waveAnimation4.next();
            ScoreboardBuilder.text5 = ScoreboardBuilder.waveAnimation5.next();
            ScoreboardBuilder.text6 = ScoreboardBuilder.waveAnimation6.next();
            ScoreboardBuilder.text7 = ScoreboardBuilder.waveAnimation7.next();
            ScoreboardBuilder.textg = ScoreboardBuilder.waveAnimation8.next();
            ScoreboardBuilder.textGG = ScoreboardBuilder.waveAnimationGG.next();

            ScoreboardBuilder.textB = ScoreboardBuilder.waveAnimation44.next();
            ScoreboardBuilder.text9 = ScoreboardBuilder.waveAnimation9.next();
            ScoreboardBuilder.textlobby = ScoreboardBuilder.waveAnimation99.next();
            for (Player onlines : Bukkit.getOnlinePlayers()) {
              if (onlines == null)
                continue; 
              if (!onlines.isOnline())
                continue; 
              if (onlines.isDead())
                continue; 
              Scoreboard score = onlines.getScoreboard();
              if (score == null)
                continue; 
              Objective objective = score.getObjective(DisplaySlot.SIDEBAR);
              if (objective == null)
                continue; 
              if (WaveWarp.SPAWN.hasPlayer(onlines.getName())) {
              objective.setDisplayName(ScoreboardBuilder.text);
              }
              else if (WaveWarp.FPS.hasPlayer(onlines.getName())) {
                  objective.setDisplayName(ScoreboardBuilder.text3);
                  }
              else if (WaveWarp.DUELS.hasPlayer(onlines.getName()) && !GladiatorListener2.combateGlad.containsKey(onlines)) {
                  objective.setDisplayName(ScoreboardBuilder.text4);
                  }
              else if (WaveWarp.LOBBY.hasPlayer(onlines.getName()) && !EventoUtils.game.contains(onlines.getName())) {
                  objective.setDisplayName(ScoreboardBuilder.textlobby);
                  }
              else if (GladiatorListener2.combateGlad.containsKey(onlines)) {
                  objective.setDisplayName(ScoreboardBuilder.textg);
                  }
              else if (WaveWarp.ARENABUILD.hasPlayer(onlines.getName())) {
                  objective.setDisplayName(ScoreboardBuilder.textB);
                  }
              else if (WaveWarp.GLADIATOR.hasPlayer(onlines.getName())) {
                  objective.setDisplayName(ScoreboardBuilder.textGG);
                  }
              else if (WaveWarp.LAVACHALLENGE.hasPlayer(onlines.getName())) {
                  objective.setDisplayName(ScoreboardBuilder.text5);
                  }
              else  if (EventoUtils.game.contains(onlines.getName()) && WaveWarp.LOBBY.hasPlayer(onlines.getName())) {
            	  objective.setDisplayName(ScoreboardBuilder.text2); 
              }
              
             
            	  
              }
            } 
          
        },  20L, 1L);
  }
	public void build(Player player) {
		 {
			 
			 if (WaveWarp.SPAWN.hasPlayer(player.getName()) && !GladiatorListener2.combateGlad.containsKey(player)) {
				 player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
					Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
					Objective objective = scoreboard.registerNewObjective("pvp", "dummy");
					
					objective.setDisplayName(text);
					objective.setDisplaySlot(DisplaySlot.SIDEBAR);
					
					String l12 = "§3";
					String l11 = " §fMatou: §a";
					String l10 = " §fMorreu: §a";
					String l9 = " §fKillstreak: §a";
					
					
					String l5 = "§1";
					String lb = " §fKit1: §a";
					String lb2 = " §fKit2: §a";
					String l4 = " §fCoins: §a";
					String l3 = " §fRank: ";
					String lx = " §fXP: §a";
					String l2 = "§0";
					String l1 = WavePvP.getInstance().getConfig().getString("IPScore").replace("&", "§");
					
					scoreboard.registerNewTeam("kills").addEntry(l11);
					scoreboard.registerNewTeam("deaths").addEntry(l10);
					scoreboard.registerNewTeam("killstreak").addEntry(l9);
				//	if (KitManager.getPlayer(player.getName()).hasKit()) {
		
					//}
					scoreboard.registerNewTeam("xp").addEntry(lx);
					scoreboard.registerNewTeam("coins").addEntry(l4);
					scoreboard.registerNewTeam("rank").addEntry(l3);
					scoreboard.registerNewTeam("kit").addEntry(lb);
					scoreboard.registerNewTeam("kit2").addEntry(lb2);
					objective.getScore(l12).setScore(11);
					objective.getScore(l11).setScore(10);
					objective.getScore(l10).setScore(9);
					objective.getScore(l9).setScore(8);
					
					objective.getScore(l5).setScore(7);
					objective.getScore(lb).setScore(6);
					objective.getScore(lb2).setScore(5);
					objective.getScore(l4).setScore(4);
					objective.getScore(l3).setScore(3);
					objective.getScore(lx).setScore(2);
					objective.getScore(l2).setScore(1);
					objective.getScore(l1).setScore(0);
					
					player.setScoreboard(scoreboard);
					update(player);
					
			 } 
			 if (WaveWarp.ARENABUILD.hasPlayer(player.getName())) {
				 player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
					Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
					Objective objective = scoreboard.registerNewObjective("pvpb", "dummyb");
					
					objective.setDisplayName(text);
					objective.setDisplaySlot(DisplaySlot.SIDEBAR);
					
					String l12 = "§3";
					String l11 = " §aMatou: §f";
					String l10 = " §aMorreu: §f";
					String l9 = " §aKillstreak: §f";
					
					
					String l5 = "§1";
					String l4 = " §aCoins: §f";
					String l3 = " §aRank: ";
					String lx = " §aXP: §f";
					String l2 = "§0";
					String l1 = WavePvP.getInstance().getConfig().getString("IPScore").replace("&", "§");
					
					scoreboard.registerNewTeam("kills").addEntry(l11);
					scoreboard.registerNewTeam("deaths").addEntry(l10);
					scoreboard.registerNewTeam("killstreak").addEntry(l9);
				//	if (KitManager.getPlayer(player.getName()).hasKit()) {
		
					//}
					scoreboard.registerNewTeam("xp").addEntry(lx);
					scoreboard.registerNewTeam("coins").addEntry(l4);
					scoreboard.registerNewTeam("rank").addEntry(l3);
					objective.getScore(l12).setScore(9);
					objective.getScore(l11).setScore(8);
					objective.getScore(l10).setScore(7);
					objective.getScore(l9).setScore(6);
					
					objective.getScore(l5).setScore(5);
					objective.getScore(l4).setScore(4);
					objective.getScore(l3).setScore(3);
					objective.getScore(lx).setScore(2);
					objective.getScore(l2).setScore(1);
					objective.getScore(l1).setScore(0);
					
					player.setScoreboard(scoreboard);
					update(player);
					
			 } 
		  else if (EventoUtils.game.contains(player.getName()) && WaveWarp.LOBBY.hasPlayer(player.getName()) && !GladiatorListener2.combateGlad.containsKey(player)) {
				player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
			 Scoreboard scoreboard2 = Bukkit.getScoreboardManager().getNewScoreboard();
				Objective objective2 = scoreboard2.registerNewObjective("pvp2", "dummy2");
				
				objective2.setDisplayName(text2);
				objective2.setDisplaySlot(DisplaySlot.SIDEBAR);
				String l9 = "§a";
				String l8 = "§bVocê está no evento";
				String ll = "§c";
				String l7 = "§fSala aberta: ";
				String l6 = "§fPvP: ";
				String l5 = "§fDano Ativo: ";
				String l4 = "§fCoins: §a";
				String l3 = "§fRank: ";
				String l2 = "§0";
				String l1 = WavePvP.getInstance().getConfig().getString("IPScore").replace("&", "§");
				scoreboard2.registerNewTeam("coins").addEntry(l4);
				scoreboard2.registerNewTeam("sala").addEntry(l7);
				scoreboard2.registerNewTeam("pvp").addEntry(l6);
				scoreboard2.registerNewTeam("dano").addEntry(l5);
				scoreboard2.registerNewTeam("rank").addEntry(l3);
				objective2.getScore(l9).setScore(9);
				objective2.getScore(l8).setScore(8);
				objective2.getScore(ll).setScore(7);
				objective2.getScore(l7).setScore(6);
				objective2.getScore(l6).setScore(5);
				objective2.getScore(l5).setScore(4);
				objective2.getScore(l4).setScore(3);
				objective2.getScore(l3).setScore(2);
				objective2.getScore(l2).setScore(1);
				objective2.getScore(l1).setScore(0);
				player.setScoreboard(scoreboard2);
				update(player);
			
		 }
			 else if (WaveWarp.FPS.hasPlayer(player.getName()) && !GladiatorListener2.combateGlad.containsKey(player)) {
					player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
				 Scoreboard scoreboard2 = Bukkit.getScoreboardManager().getNewScoreboard();
					Objective objective2 = scoreboard2.registerNewObjective("pvp3", "dummy3");
					
					objective2.setDisplayName(text3);
					objective2.setDisplaySlot(DisplaySlot.SIDEBAR);
					String l9 = "§c";
					String l8 = "§fKills: §a";
					String l7 = "§fDeaths: §c";
					String l6 = "§fKillStreak: §3";
					String l5 = "§fCoins: §a";
					String l4 = "§fRank: ";
					String l3 = "§fXP: §a";
					String l2 = "§0";
					String l1 = WavePvP.getInstance().getConfig().getString("IPScore").replace("&", "§"); 
					objective2.getScore(l8).setScore(8);
					scoreboard2.registerNewTeam("killfps").addEntry(l8);
					scoreboard2.registerNewTeam("deathfps").addEntry(l7);
					scoreboard2.registerNewTeam("ks").addEntry(l6);
					scoreboard2.registerNewTeam("coins").addEntry(l5);
					scoreboard2.registerNewTeam("rank").addEntry(l4);
					scoreboard2.registerNewTeam("xp").addEntry(l3);
					objective2.getScore(l9).setScore(8);
					objective2.getScore(l8).setScore(7);
					objective2.getScore(l7).setScore(6);
					objective2.getScore(l6).setScore(5);
					objective2.getScore(l5).setScore(4);
					objective2.getScore(l4).setScore(3);
					objective2.getScore(l3).setScore(2);
					objective2.getScore(l2).setScore(1);
					objective2.getScore(l1).setScore(0);
					player.setScoreboard(scoreboard2);
					update(player);
				
			 }
			 else if (WaveWarp.LOBBY.hasPlayer(player.getName()) && !EventoUtils.game.contains(player.getName()) && !GladiatorListener2.combateGlad.containsKey(player)) {
					player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
				 Scoreboard scoreboard2 = Bukkit.getScoreboardManager().getNewScoreboard();
					Objective objective2 = scoreboard2.registerNewObjective("pvpl", "dummy3");
					
					objective2.setDisplayName(textlobby);
					objective2.setDisplaySlot(DisplaySlot.SIDEBAR);
					String l9 = "§c";
					String l8 = "§7Bem-vindo ao PvP";
					String l7 = "§7Selecione um jogo!";
					String l6 = "§f";
					String l5 = "§fCoins: §6";
					String l4 = "§fEm jogo: §a";
					String l3 = "§b";
					String l2 = "§fJogadores: §b";
					String ld = "§d";
					String l1 = WavePvP.getInstance().getConfig().getString("IPScore").replace("&", "§"); 
					objective2.getScore(l8).setScore(8);
					scoreboard2.registerNewTeam("coins").addEntry(l5);
					scoreboard2.registerNewTeam("game").addEntry(l4);
					scoreboard2.registerNewTeam("online").addEntry(l2);
			
					objective2.getScore(l9).setScore(9);
					objective2.getScore(l8).setScore(8);
					objective2.getScore(l7).setScore(7);
					objective2.getScore(l6).setScore(6);
					objective2.getScore(l5).setScore(5);
					objective2.getScore(l4).setScore(4);
					objective2.getScore(l3).setScore(3);
					objective2.getScore(l2).setScore(2);
					objective2.getScore(ld).setScore(1);
					objective2.getScore(l1).setScore(0);
					player.setScoreboard(scoreboard2);
					update(player);
				
			 }
			 else if (GladiatorListener2.combateGlad.containsKey(player)) {
					player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
				 Scoreboard scoreboard2 = Bukkit.getScoreboardManager().getNewScoreboard();
					Objective objective2 = scoreboard2.registerNewObjective("pvpg", "dummyg");
					
					objective2.setDisplayName(textg);
					objective2.setDisplaySlot(DisplaySlot.SIDEBAR);
					
					String l10 = "§eVocê está no glad";
					String l9 = "§c";
					String l7 = "§fOponente";
					String l5 = "§e" + GladiatorListener2.combateGlad.get(player);
					String l2 = "§0";
					String l1 = WavePvP.getInstance().getConfig().getString("IPScore").replace("&", "§"); 

					scoreboard2.registerNewTeam("vida").addEntry(l5);
					objective2.getScore(l10).setScore(5);
					objective2.getScore(l9).setScore(4)
					;					objective2.getScore(l7).setScore(3);
					objective2.getScore(l5).setScore(2);
					objective2.getScore(l2).setScore(1);
					objective2.getScore(l1).setScore(0);
					player.setScoreboard(scoreboard2);
				
			 }
			 
			 else if (WaveWarp.LAVACHALLENGE.hasPlayer(player.getName()) && !GladiatorListener2.combateGlad.containsKey(player)) {
					player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
				 Scoreboard scoreboard2 = Bukkit.getScoreboardManager().getNewScoreboard();
					Objective objective2 = scoreboard2.registerNewObjective("pvp4", "dummy4");
					
					objective2.setDisplayName(text4);
					objective2.setDisplaySlot(DisplaySlot.SIDEBAR);
					
					String l10 = "§eLava Challenge";
					String l9 = "§c";
					String l8 = "§fVida: §c";
					String l5 = "§fCoins: §a";
					String l4 = "§fRank: ";
					String ll = "§fPassou: ";
					String l3 = "§fXP: §a";
					String l2 = "§0";
					String l1 = WavePvP.getInstance().getConfig().getString("IPScore").replace("&", "§"); 
					objective2.getScore(l8).setScore(8);
					scoreboard2.registerNewTeam("vida").addEntry(l8);
					scoreboard2.registerNewTeam("coins").addEntry(l5);
					scoreboard2.registerNewTeam("rank").addEntry(l4);

					scoreboard2.registerNewTeam("passou").addEntry(ll);
					scoreboard2.registerNewTeam("xp").addEntry(l3);
					objective2.getScore(l10).setScore(8);
					objective2.getScore(l9).setScore(7);
					objective2.getScore(l8).setScore(6);
					objective2.getScore(l5).setScore(5);
					objective2.getScore(l4).setScore(4);
					objective2.getScore(ll).setScore(3);
					objective2.getScore(l3).setScore(2);
					objective2.getScore(l2).setScore(1);
					objective2.getScore(l1).setScore(0);
					player.setScoreboard(scoreboard2);
					update(player);
				
			 }
			 else if (WaveWarp.DUELS.hasPlayer(player.getName()) && !GladiatorListener2.combateGlad.containsKey(player)) {
					player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
				 Scoreboard scoreboard2 = Bukkit.getScoreboardManager().getNewScoreboard();
					Objective objective2 = scoreboard2.registerNewObjective("pvp5", "dummy5");
					
					objective2.setDisplayName(text5);
					objective2.setDisplaySlot(DisplaySlot.SIDEBAR);
					
					String l9 = "§eSumô";
					String l8 = "§fGanhou: §a";
					String l7 = "§fPerdeu: §c";
					String l6 = "§fWinStreak: §3";
					String l5 = "§e1v1";
					String l4 = "§fGanhou: §b";
					String l3 = "§fPerdeu: §b";
					String l2 = "§fWinStreak: §b";
					String l1 = "§0";
					String l0 = WavePvP.getInstance().getConfig().getString("IPScore").replace("&", "§"); 
					objective2.getScore(l8).setScore(8);
					scoreboard2.registerNewTeam("winsS").addEntry(l8);
					scoreboard2.registerNewTeam("losesS").addEntry(l7);
					scoreboard2.registerNewTeam("wsS").addEntry(l6);
					scoreboard2.registerNewTeam("wins1").addEntry(l4);
					scoreboard2.registerNewTeam("loses1").addEntry(l3);
					scoreboard2.registerNewTeam("ws1").addEntry(l2);
					objective2.getScore(l9).setScore(9);
					objective2.getScore(l8).setScore(8);
					objective2.getScore(l7).setScore(7);
					objective2.getScore(l6).setScore(6);
					objective2.getScore(l5).setScore(5);
					objective2.getScore(l4).setScore(4);
					objective2.getScore(l3).setScore(3);
					objective2.getScore(l2).setScore(2);
					objective2.getScore(l1).setScore(1);
					objective2.getScore(l0).setScore(0);
					player.setScoreboard(scoreboard2);
					update(player);
			
			 }
			 else if (WaveWarp.GLADIATOR.hasPlayer(player.getName())) {
					player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
				 Scoreboard scoreboard2 = Bukkit.getScoreboardManager().getNewScoreboard();
					Objective objective2 = scoreboard2.registerNewObjective("pvp7", "dummy7");
					
					objective2.setDisplayName(textGG);
					objective2.setDisplaySlot(DisplaySlot.SIDEBAR);
					
					String l9 = "§eGladiator";
					String l4 = "§fGanhou: §b";
					String l3 = "§fPerdeu: §b";
					String l2 = "§fWinStreak: §b";
					String l1 = "§0";
					String l0 = WavePvP.getInstance().getConfig().getString("IPScore").replace("&", "§"); 
					scoreboard2.registerNewTeam("wins1").addEntry(l4);
					scoreboard2.registerNewTeam("loses1").addEntry(l3);
					scoreboard2.registerNewTeam("ws1").addEntry(l2);
					objective2.getScore(l9).setScore(9);
					objective2.getScore(l4).setScore(4);
					objective2.getScore(l3).setScore(3);
					objective2.getScore(l2).setScore(2);
					objective2.getScore(l1).setScore(1);
					objective2.getScore(l0).setScore(0);
					player.setScoreboard(scoreboard2);
					update(player);
			
			 }
			 		 
		
		
		 }
	}
	
	public void update(Player player) {
		//pvppt
		if (player.getScoreboard().getObjective("pvp") == null && player.getScoreboard().getObjective("pvp2") == null && player.getScoreboard().getObjective("pvpb") == null && player.getScoreboard().getObjective("pvpg") == null && player.getScoreboard().getObjective("pvppt") == null && player.getScoreboard().getObjective("pvp3") == null && player.getScoreboard().getObjective("pvp4") == null && player.getScoreboard().getObjective("pvp5") == null && player.getScoreboard().getObjective("pvp6") == null  && player.getScoreboard().getObjective("pvp7") == null && player.getScoreboard().getObjective("pvpl") == null) {
			return;
		}
	
            if (GladiatorListener2.combateGlad.containsKey(player)) {
    	 		return;
            }
		  else if (WaveWarp.SPAWN.hasPlayer(player.getName()) && !GladiatorListener2.combateGlad.containsKey(player)) {
			 WavePlayer wavePlayer = WaveBukkit.getInstance().getPlayerManager()
						.getPlayer(player.getName());
				net.wavemc.core.bukkit.account.provider.PlayerPvP pvp = wavePlayer.getPvp();
				Scoreboard scoreboard = player.getScoreboard();
		
					
				if (KitManager.getPlayer(player.getName()).hasKit() && !KitManager.getPlayer(player.getName()).hasKit(WaveKit.PVP)) {
					scoreboard.getTeam("kit").setSuffix(KitManager.getPlayer(player.getName()).getKit().getName());
				}	else {
						scoreboard.getTeam("kit").setSuffix("Nenhum");
				}
				if (KitManager2.getPlayer(player.getName()).haskit2() && !KitManager2.getPlayer(player.getName()).haskit2(WaveKit2.PVP)) {
					scoreboard.getTeam("kit2").setSuffix(KitManager2.getPlayer(player.getName()).getkit2().getName());
				}	else {
					scoreboard.getTeam("kit2").setSuffix("Nenhum");
			}
				scoreboard.getTeam("kills").setSuffix(WaveDecimalFormat.format(pvp.getKills()));
				scoreboard.getTeam("deaths").setSuffix(WaveDecimalFormat.format(pvp.getDeaths()));
				scoreboard.getTeam("killstreak").setSuffix(WaveDecimalFormat.format(pvp.getKillstreak()));
				scoreboard.getTeam("coins").setSuffix(WaveDecimalFormat.format(pvp.getCoins()));
				
				
				
			
			
				
				scoreboard.getTeam("xp").setSuffix(WaveDecimalFormat.format(pvp.getXp()));
				scoreboard.getTeam("rank").setSuffix((String.valueOf(Ranking.getRank(wavePlayer).getColoredName())));
					}	else if (WaveWarp.FPS.hasPlayer(player.getName())) {
			 
			 WavePlayer wavePlayer = WaveBukkit.getInstance().getPlayerManager()
						.getPlayer(player.getName());
			 net.wavemc.core.bukkit.account.provider.PlayerPvP pvp = wavePlayer.getPvp();
				Scoreboard scoreboard = player.getScoreboard();
		
			 scoreboard.getTeam("killfps").setSuffix(WaveDecimalFormat.format(pvp.getKillsfps()));
				scoreboard.getTeam("deathfps").setSuffix(WaveDecimalFormat.format(pvp.getDeathsfps()));
				scoreboard.getTeam("ks").setSuffix(WaveDecimalFormat.format(pvp.getKillstreak()));
				scoreboard.getTeam("coins").setSuffix(WaveDecimalFormat.format(pvp.getCoins()));
				scoreboard.getTeam("xp").setSuffix(WaveDecimalFormat.format(pvp.getXp()));
				scoreboard.getTeam("rank").setSuffix((String.valueOf(Ranking.getRank(wavePlayer).getColoredName())));
				}
					  else if (WaveWarp.ARENABUILD.hasPlayer(player.getName())) {
							 WavePlayer wavePlayer = WaveBukkit.getInstance().getPlayerManager()
										.getPlayer(player.getName());
								net.wavemc.core.bukkit.account.provider.PlayerPvP pvp = wavePlayer.getPvp();
								Scoreboard scoreboard = player.getScoreboard();
						
									
							
								scoreboard.getTeam("kills").setSuffix(WaveDecimalFormat.format(pvp.getKills()));
								scoreboard.getTeam("deaths").setSuffix(WaveDecimalFormat.format(pvp.getDeaths()));
								scoreboard.getTeam("killstreak").setSuffix(WaveDecimalFormat.format(pvp.getKillstreak()));
								scoreboard.getTeam("coins").setSuffix(WaveDecimalFormat.format(pvp.getCoins()));
								
								
								
							
							
								
								scoreboard.getTeam("xp").setSuffix(WaveDecimalFormat.format(pvp.getXp()));
								scoreboard.getTeam("rank").setSuffix((String.valueOf(Ranking.getRank(wavePlayer).getColoredName())));
									}	else if (WaveWarp.FPS.hasPlayer(player.getName())) {
							 
							 WavePlayer wavePlayer = WaveBukkit.getInstance().getPlayerManager()
										.getPlayer(player.getName());
							 net.wavemc.core.bukkit.account.provider.PlayerPvP pvp = wavePlayer.getPvp();
								Scoreboard scoreboard = player.getScoreboard();
						
							 scoreboard.getTeam("killfps").setSuffix(WaveDecimalFormat.format(pvp.getKillsfps()));
								scoreboard.getTeam("deathfps").setSuffix(WaveDecimalFormat.format(pvp.getDeathsfps()));
								scoreboard.getTeam("ks").setSuffix(WaveDecimalFormat.format(pvp.getKillstreak()));
								scoreboard.getTeam("coins").setSuffix(WaveDecimalFormat.format(pvp.getCoins()));
								scoreboard.getTeam("xp").setSuffix(WaveDecimalFormat.format(pvp.getXp()));
								scoreboard.getTeam("rank").setSuffix((String.valueOf(Ranking.getRank(wavePlayer).getColoredName())));
								}
	 	else if (WaveWarp.LAVACHALLENGE.hasPlayer(player.getName()) && !GladiatorListener2.combateGlad.containsKey(player)) {
	 		WavePlayer wavePlayer = WaveBukkit.getInstance().getPlayerManager()
					.getPlayer(player.getName());
	 		net.wavemc.core.bukkit.account.provider.PlayerPvP pvp = wavePlayer.getPvp();
			Scoreboard scoreboard = player.getScoreboard();
	 		scoreboard.getTeam("vida").setSuffix(WaveDecimalFormat.format(player.getHealth()/2) + " §4\u2764");
			scoreboard.getTeam("coins").setSuffix(WaveDecimalFormat.format(pvp.getCoins()));
			scoreboard.getTeam("xp").setSuffix(WaveDecimalFormat.format(pvp.getXp()));
			scoreboard.getTeam("passou").setSuffix(WaveDecimalFormat.format(pvp.getPassouchallenge()));
			scoreboard.getTeam("rank").setSuffix((String.valueOf(Ranking.getRank(wavePlayer).getColoredName())));
	 	} 
	 	else if (WaveWarp.LOBBY.hasPlayer(player.getName()) && !EventoUtils.game.contains(player.getName()) && !GladiatorListener2.combateGlad.containsKey(player)) {
	 		WavePlayer wavePlayer = WaveBukkit.getInstance().getPlayerManager()
					.getPlayer(player.getName());
	 		net.wavemc.core.bukkit.account.provider.PlayerPvP pvp = wavePlayer.getPvp();
			Scoreboard scoreboard = player.getScoreboard();
	 		scoreboard.getTeam("game").setSuffix(WaveDecimalFormat.format(WaveWarp.SPAWN.getPlayerCount() + WaveWarp.DUELS.getPlayerCount() + WaveWarp.FPS.getPlayerCount() + WaveWarp.LAVACHALLENGE.getPlayerCount()  + WaveWarp.ARENABUILD.getPlayerCount()  + WaveWarp.GLADIATOR.getPlayerCount()));
			scoreboard.getTeam("coins").setSuffix(WaveDecimalFormat.format(pvp.getCoins()));
			scoreboard.getTeam("online").setSuffix(WaveDecimalFormat.format(Bukkit.getOnlinePlayers().size()));
	 	} 
		 else if (EventoUtils.game.contains(player.getName()) && WaveWarp.LOBBY.hasPlayer(player.getName()) && !GladiatorListener2.combateGlad.containsKey(player)) {
			WavePlayer helixPlayer = WaveBukkit.getInstance().getPlayerManager()
						.getPlayer(player.getName());
				net.wavemc.core.bukkit.account.provider.PlayerPvP pvp = helixPlayer.getPvp();
				Scoreboard scoreboard = player.getScoreboard();
			scoreboard.getTeam("coins").setSuffix((String.valueOf(WaveDecimalFormat.format(pvp.getCoins()))));
			scoreboard.getTeam("dano").setSuffix((String.valueOf(EventoUtils.damage ? "§aAtivado" : "§cDesativado")));
			scoreboard.getTeam("sala").setSuffix((String.valueOf((EventoUtils.tp ? "§aSim" : "§cNão"))));
			scoreboard.getTeam("pvp").setSuffix((String.valueOf((EventoUtils.pvp ? "§aAtivado" : "§cDesativado"))));
			scoreboard.getTeam("rank").setSuffix((String.valueOf(Ranking.getRank(helixPlayer).getColoredName())));
		 }
		 else if (WaveWarp.DUELS.hasPlayer(player.getName()) && !GladiatorListener2.combateGlad.containsKey(player)) {
				WavePlayer wavePlayer = WaveBukkit.getInstance().getPlayerManager()
						.getPlayer(player.getName());
				net.wavemc.core.bukkit.account.provider.PlayerPvP pvp = wavePlayer.getPvp();
				Scoreboard scoreboard = player.getScoreboard();
				scoreboard.getTeam("winsS").setSuffix(WaveDecimalFormat.format(pvp.getWinssumo()));
				scoreboard.getTeam("losesS").setSuffix(WaveDecimalFormat.format(pvp.getDeathssumo()));
				scoreboard.getTeam("wsS").setSuffix(WaveDecimalFormat.format(pvp.getWinstreaksumo()));
				scoreboard.getTeam("wins1").setSuffix(WaveDecimalFormat.format(pvp.getWinsx1()));
				scoreboard.getTeam("loses1").setSuffix(WaveDecimalFormat.format(pvp.getDeathsx1()));
				scoreboard.getTeam("ws1").setSuffix(WaveDecimalFormat.format(pvp.getWinstreakx1()));
		} 

		 else if (WaveWarp.GLADIATOR.hasPlayer(player.getName()) && !GladiatorListener2.combateGlad.containsKey(player)) {
				WavePlayer wavePlayer = WaveBukkit.getInstance().getPlayerManager()
						.getPlayer(player.getName());
				net.wavemc.core.bukkit.account.provider.PlayerPvP pvp = wavePlayer.getPvp();
				Scoreboard scoreboard = player.getScoreboard();
				scoreboard.getTeam("wins1").setSuffix(WaveDecimalFormat.format(pvp.getWinsx1()));
				scoreboard.getTeam("loses1").setSuffix(WaveDecimalFormat.format(pvp.getDeathsx1()));
				scoreboard.getTeam("ws1").setSuffix(WaveDecimalFormat.format(pvp.getWinstreakx1()));
		} 
	}
	}
