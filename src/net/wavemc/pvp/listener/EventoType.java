package net.wavemc.pvp.listener;




import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;

import lombok.Getter;
import net.wavemc.pvp.WavePvP;

@Getter
public enum EventoType {


    PVP("ArenaPvP", Arrays.asList("Olá a todos! Bem vindo ao &c&lArena PvP&l&f!", "No evento, vocês receberam um kit com items", "Com o tempo com jogadores diminuindo vocês serão teleportados a um novo local.", "Todos tem 20 segundos para se preparar.", "Agora vamos as &c&lREGRAS DO EVENTO&l&f:", "&fNão tente escapar da arena pvp.", "&fNão faça mais times que o promotor deixar." , "Começando evento! Boa sorte"), new Location(Bukkit.getWorld(WavePvP.getInstance().getConfig().getString("ARENAMUNDO")), WavePvP.getInstance().getConfig().getInt("EventoArenaX"), WavePvP.getInstance().getConfig().getInt("EventoArenaY"), WavePvP.getInstance().getConfig().getInt("EventoArenaZ"))),
    LAVA("Lava", Arrays.asList("Olá a todos! Bem vindo ao evento &4&lLava Challenge", "&fAo inicio do evento todos receberam um kit com sopas.", "Todos precisarão tankar a lava que vocês serão colocados!", "Suas sopas NÃO serão reabastecidas durante o evento, então use elas com cautela.", "Vocês terão 10 segundos para se prepararem!", "Começando evento! Boa sorte!"), new Location(Bukkit.getWorld(WavePvP.getInstance().getConfig().getString("LAVAMUNDO")), WavePvP.getInstance().getConfig().getInt("EventoLavaX"), WavePvP.getInstance().getConfig().getInt("EventoLavaY"), WavePvP.getInstance().getConfig().getInt("EventoLavaZ"))),
    MDR("Mdr", Arrays.asList("Welcome to the event Mom of Street!", "At event start you guys will receive a kit with unlimited golden apples!", "The main objetive is to pass the street without getting killed!", "If you die uou will be desqualified.", "If you play around or disobey the event promoter you will also be disqualified.", "Todos terão 10 segundos para se prepararem!", "Começando! Boa sorte"), new Location(Bukkit.getWorld(WavePvP.getInstance().getConfig().getString("MDRMUNDO")), WavePvP.getInstance().getConfig().getInt("EventoMDRX"), WavePvP.getInstance().getConfig().getInt("EventoMDRY"), WavePvP.getInstance().getConfig().getInt("EventoMDRZ"))),
    _1v1("1v1", Arrays.asList("Bem vindo ao evento 1vs1!", "No início do evento vocês serão puxados de 2 em 2 para uma luta individual", "Quem ganhar voltará ao evento!", "E quem perder voltará ao spawn!", "Começando evento. Divirta-se"), new Location(Bukkit.getWorld("Eventos"), 1438.017, 102.00000, 131.789)),
    ;
    private final String name;
    private final List<String> explicacao;
    private final Location location;
    EventoType(String name, List<String> explicacao, Location location) {
        this.name = name;
        this.explicacao = explicacao;
        this.location = location;
    }

    public static EventoType getEventoByName(String name) {
        for (EventoType evento : EventoType.values()) {
        	 if (evento.getName().equalsIgnoreCase(name))
                 return evento;
         }
        return null;
    }
    public String getName() {
		return name;
	}
    public List<String> getExplicacao() {
		return explicacao;
	}
    public Location getLocation() {
		return location;
	}

    public static void explicarEvento(EventoType evento) {
        List<String> explic = evento.getExplicacao();
        int actualsec = 1;
        for (String message : explic) {
            Bukkit.getScheduler().runTaskLater(WavePvP.getInstance(), () -> {
            	EventoUtils.getEventoPlayers().forEach(p -> {
               p.sendMessage(WavePvP.getInstance().getConfig().getString("Prefix").replace("&", "§") + " §f" + ChatColor.translateAlternateColorCodes('&', message));
               p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
        });
            	
    }, actualsec * 20);
            actualsec += 5;
        }
}
}


// mdr 801.5 100 519.5
// lava 641.5 118 518.5
// pvp 732.5 80 521.5
// 1v1 868.5 95 457.5