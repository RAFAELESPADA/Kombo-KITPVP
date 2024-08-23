package net.wavemc.pvp.listener;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import lombok.Getter;
import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;
import net.wavemc.core.bukkit.account.provider.PlayerPvP;
import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.kit.provider.WaveActionBar;

@Getter
public enum Ranking {

    GOD(WavePvP.getInstance().getConfig().getString("GodRank"), '✯', ChatColor.DARK_PURPLE, WavePvP.getInstance().getConfig().getInt("GodKills")),
    LENDARIO(WavePvP.getInstance().getConfig().getString("LendarioRank"), '۞', ChatColor.AQUA, WavePvP.getInstance().getConfig().getInt("LendarioKills")),
    RUBY(WavePvP.getInstance().getConfig().getString("RubyRank"), '✧', ChatColor.DARK_RED, WavePvP.getInstance().getConfig().getInt("RubyKills")),
    SAFIRA(WavePvP.getInstance().getConfig().getString("SafiraRank"), '✪', ChatColor.BLUE, WavePvP.getInstance().getConfig().getInt("SafiraKills")),
    CRISTAL(WavePvP.getInstance().getConfig().getString("CristalRank"), '❂', ChatColor.GOLD, WavePvP.getInstance().getConfig().getInt("CristalKills")),
    ESMERALDA(WavePvP.getInstance().getConfig().getString("EsmeraldaRank"), '✵', ChatColor.DARK_GREEN, WavePvP.getInstance().getConfig().getInt("EsmeraldaKills")),
    DIAMANTE(WavePvP.getInstance().getConfig().getString("DiamanteRank"), '✹', ChatColor.AQUA,  WavePvP.getInstance().getConfig().getInt("DiamanteKills")),
    OURO(WavePvP.getInstance().getConfig().getString("OuroRank"), '✸', ChatColor.GOLD, WavePvP.getInstance().getConfig().getInt("OuroKills")),
    PRATA(WavePvP.getInstance().getConfig().getString("PrataRank"), '✸', ChatColor.WHITE, WavePvP.getInstance().getConfig().getInt("PrataKills")),
    VETERANO(WavePvP.getInstance().getConfig().getString("VeteranoRank"), '✶', ChatColor.RED, WavePvP.getInstance().getConfig().getInt("VeteranoKills")),
    EXPERIENTE(WavePvP.getInstance().getConfig().getString("ExperienteRank"), '✶', ChatColor.DARK_GRAY, WavePvP.getInstance().getConfig().getInt("ExperienteKills")),
    AVANCADO(WavePvP.getInstance().getConfig().getString("AvancadoRank"), '✶', ChatColor.GOLD, WavePvP.getInstance().getConfig().getInt("AvancadoKills")),
    INICIANTE(WavePvP.getInstance().getConfig().getString("InicianteRank"), '=', ChatColor.WHITE, WavePvP.getInstance().getConfig().getInt("InicianteKills")),
    UNRANKED(WavePvP.getInstance().getConfig().getString("UnrankedRank"), '☄', ChatColor.GRAY, 0);

    private final String name;
    private final char symbol;
    private final ChatColor color;
    private final int xp;

    public Ranking next() {
        return Ranking.values()[(this.ordinal() - 1) % Ranking.values().length];
    }
    public Ranking prev() {
        return Ranking.values()[(this.ordinal() + 1) % Ranking.values().length];
    }

    Ranking(String name, char symbol, ChatColor color, int xp) {
        this.name = name;
        this.symbol = symbol;
        this.color = color;
        this.xp = xp;
    }

    public String getColoredName() {
        return color + name;
    }

    public String getColoredSymbol() {
        return String.valueOf(color.toString() + symbol);
    }

    public String getColoredSymbolName() {
        return this.getColoredSymbol() + " " + name;
    }

    public static Ranking getRank(WavePlayer playerData) {
        int xp = playerData.getPvp().getXp();
        for (Ranking rank : Ranking.values()) {
            if (xp >= rank.getXp())
                return rank;
        }
        return GOD;
    }

	
	   public static void checkRank(Player p) {
	        WavePlayer wavePlayer = WaveBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
	        if(wavePlayer == null) return;
			PlayerPvP pvp = wavePlayer.getPvp();

			if (pvp.getXp() == WavePvP.getInstance().getConfig().getInt("InicianteKills")) {
		        p.sendMessage("Você upou para o rank " + WavePvP.getInstance().getConfig().getString("InicianteRank").toUpperCase());
	        Bukkit.broadcastMessage("§a" + p.getName() + " §fupou do rank " + WavePvP.getInstance().getConfig().getString("UnrankedRank").toUpperCase() +  " para o Rank " + WavePvP.getInstance().getConfig().getString("InicianteRank").toUpperCase());
	        
	        for (Player p1 : Bukkit.getOnlinePlayers()) {
	        	p1.playSound(p1.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 10.0f, 1f);
	        	  WaveActionBar.send(p1, "§a" + p.getName() + " §fupou para o Ranking " + WavePvP.getInstance().getConfig().getString("InicianteRank").toUpperCase());
	        }
			}
	    else if (pvp.getXp() == WavePvP.getInstance().getConfig().getInt("AvancadoKills")) {
	    	   p.sendMessage("Você upou para o rank " + WavePvP.getInstance().getConfig().getString("AvancadoRank").toUpperCase());
		        Bukkit.broadcastMessage("§a" + p.getName() + " §fupou do rank " + WavePvP.getInstance().getConfig().getString("InicianteRank").toUpperCase() +  " para o Rank " + WavePvP.getInstance().getConfig().getString("AvancadoRank").toUpperCase());
		        
		        for (Player p1 : Bukkit.getOnlinePlayers()) {
		        	p1.playSound(p1.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 10.0f, 1f);
		        	  WaveActionBar.send(p1, "§a" + p.getName() + " §fupou para o Ranking " + WavePvP.getInstance().getConfig().getString("AvancadoRank").toUpperCase());
	        	
	        }	        
	        }
	        else if (pvp.getXp() == WavePvP.getInstance().getConfig().getInt("ExperienteKills")) {
	        	   p.sendMessage("Você upou para o rank " + WavePvP.getInstance().getConfig().getString("ExperienteRank").toUpperCase());
	   	        Bukkit.broadcastMessage("§a" + p.getName() + " §fupou do rank " + WavePvP.getInstance().getConfig().getString("AvancadoRank").toUpperCase() +  " para o Rank " + WavePvP.getInstance().getConfig().getString("ExperienteRank").toUpperCase());
	   	        
	   	        for (Player p1 : Bukkit.getOnlinePlayers()) {
	   	        	p1.playSound(p1.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 10.0f, 1f);
	   	        	  WaveActionBar.send(p1, "§a" + p.getName() + " §fupou para o Ranking " + WavePvP.getInstance().getConfig().getString("ExperienteRank").toUpperCase());
	        }
	        }
	        else if (pvp.getXp() == WavePvP.getInstance().getConfig().getInt("VeteranoKills")) {
	        	   p.sendMessage("Você upou para o rank " + WavePvP.getInstance().getConfig().getString("VeteranoRank").toUpperCase());
		   	        Bukkit.broadcastMessage("§a" + p.getName() + " §fupou do rank " + WavePvP.getInstance().getConfig().getString("ExperienteRank").toUpperCase() +  " para o Rank " + WavePvP.getInstance().getConfig().getString("VeteranoRank").toUpperCase());
		   	        
		   	        for (Player p1 : Bukkit.getOnlinePlayers()) {
		   	        	p1.playSound(p1.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 10.0f, 1f);
		   	        	  WaveActionBar.send(p1, "§a" + p.getName() + " §fupou para o Ranking " + WavePvP.getInstance().getConfig().getString("VeteranoRank").toUpperCase());
		        }
	        }
	        else if (pvp.getXp() == WavePvP.getInstance().getConfig().getInt("PrataKills")) {
	        	   p.sendMessage("Você upou para o rank " + WavePvP.getInstance().getConfig().getString("PrataRank").toUpperCase());
		   	        Bukkit.broadcastMessage("§a" + p.getName() + " §fupou do rank " + WavePvP.getInstance().getConfig().getString("VeteranoRank").toUpperCase() +  " para o Rank " + WavePvP.getInstance().getConfig().getString("PrataRank").toUpperCase());
		   	        
		   	        for (Player p1 : Bukkit.getOnlinePlayers()) {
		   	        	p1.playSound(p1.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 10.0f, 1f);
		   	        	  WaveActionBar.send(p1, "§a" + p.getName() + " §fupou para Ranking " + WavePvP.getInstance().getConfig().getString("PrataRank").toUpperCase());
		        }
	        }
	        else if (pvp.getXp() == WavePvP.getInstance().getConfig().getInt("OuroKills")) {
	        	 p.sendMessage("Você upou para o rank " + WavePvP.getInstance().getConfig().getString("OuroRank").toUpperCase());
		   	        Bukkit.broadcastMessage("§a" + p.getName() + " §fupou do rank " + WavePvP.getInstance().getConfig().getString("PrataRank").toUpperCase() +  " para o Rank " + WavePvP.getInstance().getConfig().getString("OuroRank").toUpperCase());
		   	        
		   	        for (Player p1 : Bukkit.getOnlinePlayers()) {
		   	        	p1.playSound(p1.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 10.0f, 1f);
		   	        	  WaveActionBar.send(p1, "§a" + p.getName() + " §fupou para o Ranking " + WavePvP.getInstance().getConfig().getString("OuroRank").toUpperCase());
		        }
	        }
	        else if (pvp.getXp() == WavePvP.getInstance().getConfig().getInt("DiamanteKills")) {
	        	 p.sendMessage("Você upou para o rank " + WavePvP.getInstance().getConfig().getString("DiamanteRank").toUpperCase());
		   	        Bukkit.broadcastMessage("§a" + p.getName() + " §fupou do rank " + WavePvP.getInstance().getConfig().getString("OuroRank").toUpperCase() +  " para o Rank " + WavePvP.getInstance().getConfig().getString("DiamanteRank").toUpperCase());
		   	        
		   	        for (Player p1 : Bukkit.getOnlinePlayers()) {
		   	        	p1.playSound(p1.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 10.0f, 1f);
		   	        	  WaveActionBar.send(p1, "§a" + p.getName() + " §fupou para o Ranking " + WavePvP.getInstance().getConfig().getString("DiamanteRank").toUpperCase());
		        }
	        }
	        else if (pvp.getXp() == WavePvP.getInstance().getConfig().getInt("EsmeraldaKills")) {
	        	 p.sendMessage("Você upou para o rank " + WavePvP.getInstance().getConfig().getString("EsmeraldaRank").toUpperCase());
		   	        Bukkit.broadcastMessage("§a" + p.getName() + " §fupou do rank " + WavePvP.getInstance().getConfig().getString("DiamanteRank").toUpperCase() +  " para o Rank " + WavePvP.getInstance().getConfig().getString("EsmeraldaRank").toUpperCase());
		   	        
		   	        for (Player p1 : Bukkit.getOnlinePlayers()) {
		   	        	p1.playSound(p1.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 10.0f, 1f);
		   	        	  WaveActionBar.send(p1, "§a" + p.getName() + " §fupou para o Ranking " + WavePvP.getInstance().getConfig().getString("EsmeraldaRank").toUpperCase());
		        }
	        }
	        else if (pvp.getXp() == WavePvP.getInstance().getConfig().getInt("CristalKills")) {
	        	 p.sendMessage("Você upou para o rank " + WavePvP.getInstance().getConfig().getString("CristalRank").toUpperCase());
		   	        Bukkit.broadcastMessage("§a" + p.getName() + " §fupou do rank " + WavePvP.getInstance().getConfig().getString("EsmeraldaRank").toUpperCase() +  " para o Rank " + WavePvP.getInstance().getConfig().getString("CristalRank").toUpperCase());
		   	        
		   	        for (Player p1 : Bukkit.getOnlinePlayers()) {
		   	        	p1.playSound(p1.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 10.0f, 1f);
		   	        	  WaveActionBar.send(p1, "§a" + p.getName() + " §fupou para o Ranking " + WavePvP.getInstance().getConfig().getString("CristalRank").toUpperCase());
		        }
	        }
	        else if (pvp.getXp() == WavePvP.getInstance().getConfig().getInt("SafiraKills")) {
	        	p.sendMessage("Você upou para o rank " + WavePvP.getInstance().getConfig().getString("SafiraRank").toUpperCase());
	   	        Bukkit.broadcastMessage("§a" + p.getName() + " §fupou do rank " + WavePvP.getInstance().getConfig().getString("CristalRank").toUpperCase() +  " para o Rank " + WavePvP.getInstance().getConfig().getString("SafiraRank").toUpperCase());
	   	        
	   	        for (Player p1 : Bukkit.getOnlinePlayers()) {
	   	        	p1.playSound(p1.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 10.0f, 1f);
	   	        	  WaveActionBar.send(p1, "§a" + p.getName() + " §fupou para o Ranking " + WavePvP.getInstance().getConfig().getString("SafiraRank").toUpperCase());
	        }
        }
	        else if (pvp.getXp() == WavePvP.getInstance().getConfig().getInt("RubyKills")) {
	        	p.sendMessage("Você upou para o rank " + WavePvP.getInstance().getConfig().getString("RubyRank").toUpperCase());
	   	        Bukkit.broadcastMessage("§a" + p.getName() + " §fupou do rank " + WavePvP.getInstance().getConfig().getString("SafiraRank").toUpperCase() +  " para o Rank " + WavePvP.getInstance().getConfig().getString("RubyRank").toUpperCase());
	   	        
	   	        for (Player p1 : Bukkit.getOnlinePlayers()) {
	   	        	p1.playSound(p1.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 10.0f, 1f);
	   	        	  WaveActionBar.send(p1, "§a" + p.getName() + " §fupou para o Ranking " + WavePvP.getInstance().getConfig().getString("RubyRank").toUpperCase());
	        }
        }
	        else if (pvp.getXp() == WavePvP.getInstance().getConfig().getInt("LendarioKills")) {
	        	p.sendMessage("Você upou para o rank " + WavePvP.getInstance().getConfig().getString("LendarioRank").toUpperCase());
	   	        Bukkit.broadcastMessage("§a" + p.getName() + " §fupou do rank " + WavePvP.getInstance().getConfig().getString("RubyRank").toUpperCase() +  " para o Rank " + WavePvP.getInstance().getConfig().getString("LendarioRank").toUpperCase());
	   	        
	   	        for (Player p1 : Bukkit.getOnlinePlayers()) {
	   	        	p1.playSound(p1.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 10.0f, 1f);
	   	        	  WaveActionBar.send(p1, "§a" + p.getName() + " §fupou para o Ranking " + WavePvP.getInstance().getConfig().getString("LendarioRank").toUpperCase());
	        }
        }
	        	
	        else if (pvp.getXp() == WavePvP.getInstance().getConfig().getInt("GodKills")) {
	        	p.sendMessage("Você upou para o rank " + WavePvP.getInstance().getConfig().getString("GodRank").toUpperCase());
	   	        Bukkit.broadcastMessage("§a" + p.getName() + " §fupou do rank " + WavePvP.getInstance().getConfig().getString("LendarioRank").toUpperCase() +  " para o Rank " + WavePvP.getInstance().getConfig().getString("GodRank").toUpperCase());
	   	        
	   	        for (Player p1 : Bukkit.getOnlinePlayers()) {
	   	        	p1.playSound(p1.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 10.0f, 1f);
	   	        	  WaveActionBar.send(p1, "§a" + p.getName() + " §fupou para o Ranking " + WavePvP.getInstance().getConfig().getString("GodRank").toUpperCase());
	        }
        }
	      
	   }
	public int getXp() {
		return xp;
	}
	
	public String getName() {
		return name;
	}
    }


