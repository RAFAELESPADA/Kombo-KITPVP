package net.wavemc.pvp.command;



import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;

import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.listener.EventoType;
import net.wavemc.pvp.listener.EventoUtils;
import net.wavemc.pvp.listener.RDMAutomatic;
import net.wavemc.pvp.warp.WaveWarp;


public class OneVsOneIniciar2
implements CommandExecutor, Listener
{
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
{
  Player p = (Player)sender;

  
	  if (!p.hasPermission("kombo.cmd.evento")) {
		  p.sendMessage("§cVocê não tem permissão!");
		  return true;
	  }
	  else if (EventoUtils.evento) {
		  p.sendMessage("Um evento ja esta ocorrendo!");
		  return true;
	  }
	  else if (WavePvP.getInstance().getEventManager().isRunningRDM()) {
       p.sendMessage("O evento 1v1 já está ocorrendo");
       return true;
	  
  } else {
	  p.sendMessage("§aIniciando evento 1v1"); 
	  EventoUtils.evento = true;
	  EventoUtils.tp = true;
	  Bukkit.broadcastMessage("§cO evento 1v1 começou.");
      Bukkit.broadcastMessage("§cUse /evento join para entrar");
      Bukkit.broadcastMessage("§cO evento irá começar em 5 minutos");
      for (Player p1 : Bukkit.getOnlinePlayers()) {
      }
		Bukkit.getScheduler().scheduleSyncDelayedTask(WavePvP.getInstance(), new Runnable() {
			public void run() {
				if (!EventoUtils.evento) {
					  return;
				  }
				 EventoType ev = EventoType.getEventoByName("1v1");
			 	 Location evt = ev.getLocation();
			 	 EventoUtils.getEventoPlayers().forEach(p2 -> {
                 	p2.teleport(evt);
			      for (Player p1 : Bukkit.getOnlinePlayers()) {
			        }
			      p2.sendMessage("Evento vai começar");
			});
			 	Bukkit.broadcastMessage("§cTeleportando pessoas ao evento!");
			 	EventoType evento = EventoType.getEventoByName("1v1");
			 
			    Bukkit.broadcastMessage("§aIniciando a explicação do evento §e" + evento.getName().toUpperCase() + "§a...");
                EventoType.explicarEvento(evento);
                EventoUtils.started = true;
			}}, 1200L);
		Bukkit.getScheduler().scheduleSyncDelayedTask(WavePvP.getInstance(), new Runnable() {
			public void run() {
				if (!EventoUtils.evento) {
					  return;
				  }
				 EventoUtils.pvp = true;
				 EventoUtils.damage = true;
				 EventoUtils.build = false;
				 EventoUtils.tp = false;
				Bukkit.broadcastMessage("§c§lO evento vai começar!");
				Bukkit.broadcastMessage("§c§lBoa Sorte!");
			 	WavePvP.getInstance().getEventManager().setRdmAutomatic(new RDMAutomatic());	
		 		Bukkit.getConsoleSender().sendMessage("RDM Automatic iniciado");
			 	 EventoUtils.getEventoPlayers().forEach(p2 -> {
			 		 if (!RDMAutomatic.iniciou) {
			 		RDMAutomatic.iniciou = true;
			 		WavePvP.getInstance().getEventManager().getRdmAutomatic().setGameType(RDMAutomatic.GameType.STARTING);
			 		Bukkit.getConsoleSender().sendMessage("Variavel de inicio do evento 1v1 setada como true");
			 		 }
			 		
			 		
			 		
              
			 	 
			
			 	 });
			}}, 1800L);
	}
  
  Bukkit.getScheduler().scheduleSyncDelayedTask(WavePvP.getInstance(), new Runnable() {
		public void run() {
			if (!EventoUtils.evento || !EventoUtils.started) {
				  return;
			  }
			EventoUtils.getEventoPlayers().forEach(p ->  {
				if (p != null && EventoUtils.getEventoPlayers().size() != 0) {
            	EventoUtils.setEvento(false, p);
                WaveWarp.SPAWN.send(p);
                p.chat("/spawn");
                p.getActivePotionEffects().forEach(ef -> p.removePotionEffect(ef.getType()));
                
                if (EventoUtils.getEventoPlayers().size() == 1) {
                	Bukkit.broadcastMessage("§aVencedor do Evento: " + EventoUtils.getEventoPlayersNames());
                	Bukkit.broadcastMessage("§aVencedor do Evento: " + EventoUtils.getEventoPlayersNames());
                	Bukkit.broadcastMessage("§aVencedor do Evento: " + EventoUtils.getEventoPlayersNames());
                	Bukkit.broadcastMessage("§aVencedor do Evento: " + EventoUtils.getEventoPlayersNames());
                	Bukkit.broadcastMessage("§aVencedor do Evento: " + EventoUtils.getEventoPlayersNames());
                	Bukkit.broadcastMessage("§aVencedor do Evento: " + EventoUtils.getEventoPlayersNames());
                	Bukkit.broadcastMessage("§aVencedor do Evento: " + EventoUtils.getEventoPlayersNames());
                	p.setHealth(20);
                	p.getWorld().strikeLightning(p.getLocation());
            		p.getWorld().strikeLightning(p.getLocation());
            		p.getWorld().strikeLightning(p.getLocation());
            		p.getWorld().strikeLightning(p.getLocation());
                }
                if (WavePvP.getInstance().getEventManager().isRunningRDM()) {
                	WavePvP.getInstance().getEventManager().setRdmAutomatic(null);
                }
                p.sendMessage("§cO evento foi finalizado automaticamente porque o tempo expirou.");
                p.sendMessage("§cO evento durou: §a1 hora e 20 minutos");
			}
            });;
            EventoUtils.resetEventoClass();
		}}, 96000L);
  
return false;
      };
      {


}
{

}
}
