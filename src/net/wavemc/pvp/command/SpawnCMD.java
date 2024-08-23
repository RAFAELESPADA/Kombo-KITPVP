package net.wavemc.pvp.command;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;
import net.wavemc.kit2.Stomper;
import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.cooldown2.WaveCooldown2;
import net.wavemc.pvp.kit.Habilidade;
import net.wavemc.pvp.kit.KitManager;
import net.wavemc.pvp.kit.KitManager2;
import net.wavemc.pvp.kit.provider.EnderMageReal;
import net.wavemc.pvp.kit.provider.GladiatorListener2;
import net.wavemc.pvp.listener.Jump;
import net.wavemc.pvp.listener.PlayerJoin;
import net.wavemc.pvp.warp.WaveWarp;
import net.wavemc.pvp.warp.WarpDuoBattleHandle;
import net.wavemc.warp.provider.Gladiator;
import net.wavemc.warp.provider.OneVsOne;
import net.wavemc.warp.provider.Sumo;

public class SpawnCMD extends WarpDuoBattleHandle implements Listener, CommandExecutor {
	
	public SpawnCMD() {
		 super("spawn_pos1", "spawn_pos2", "rolada", "penis", "penisduro", "bucertinha");
		// TODO Auto-generated constructor stub
	}

	public static ArrayList<String> indo = new ArrayList<String>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		Player player = (Player) sender;
		if (WaveWarp.FPS.hasPlayer(player.getName())) {
			WaveWarp.FPS.send(player);
			return true;
		}
		if (WaveWarp.DUELS.hasPlayer(player.getName()) || WaveWarp.LOBBY.hasPlayer(player.getName())) {
			p.sendMessage("Comando desconhecido.");
			return true;
		}
		if (WaveWarp.LAVACHALLENGE.hasPlayer(player.getName())) {
			WaveWarp.LAVACHALLENGE.send(player);
			return true;
		}
		if (WaveWarp.ARENABUILD.hasPlayer(player.getName())) {
			WaveWarp.ARENABUILD.send(player);
			return true;
		}
		if (WaveWarp.SPAWN.hasPlayer(p.getName()) && !KitManager.getPlayer(p.getName()).hasKit() && !KitManager2.getPlayer(p.getName()).haskit2() && !Jump.recebeu.containsKey(player.getName())) {
			p.sendMessage(ChatColor.RED + "Você já está no spawn.");
			return true;
		}
		else if (player.getLocation().getY() > WavePvP.getInstance().getConfig().getInt("SpawnAltura") && PlayerJoin.fall.contains(player)  && EnderMageReal.isSpawn(p.getLocation())) {
	      	player.sendMessage("§cVocê já está no spawn!");
	  		return true;
	  	 }
		 if (GladiatorListener2.combateGlad.containsKey(p)) {
				p.sendMessage("§eComando bloqueado no Gladiator!");
			
				return true;
         }
		 if (net.wavemc.kit2.GladiatorListener.combateGlad.containsKey(p)) {
				p.sendMessage("§eComando bloqueado no Gladiator!");
				
				return true;
         }
		 if (Sumo.fastChallenge.contains(p)) {
			 Sumo.fastChallenge.remove(p);
			 p.sendMessage(ChatColor.GREEN + "Você saiu da fila da sumo");
		 }
		 if (OneVsOne.fastChallenge.contains(p)) {
			 OneVsOne.fastChallenge.remove(p);
			 p.sendMessage(ChatColor.GREEN + "Você saiu da fila da 1v1");
		 }
		 if (Gladiator.fastChallenge.contains(p)) {
			 Gladiator.fastChallenge.remove(p);
			 p.sendMessage(ChatColor.DARK_RED + "Você saiu da fila do gladiator");
		 }
		 
		Habilidade.removeAbility(p);
		WaveWarp.SPAWN.send(p, true);
		p.setFlying(false);
		if (!PlayerJoin.fall.contains(p)) {
	    	PlayerJoin.fall.add(p);
	    
	    }
	    WaveCooldown2.removeCooldown(player, KitManager2.getPlayer(player.getName()).getkit2().getName());
	    net.wavemc.pvp.cooldown1.WaveCooldown2.removeCooldown(player, KitManager.getPlayer(player.getName()).getKit().getName());
		Stomper.usou.remove(p.getName());
		net.wavemc.pvp.kit.provider.Stomper.usou.remove(player.getName());
		p.setGameMode(GameMode.SURVIVAL);
		p.setLevel(0);
		return true;
	
	

		
	}
	@EventHandler
    public void PlayerMove(PlayerMoveEvent event) {
        if (event.isCancelled()) return;
        Player player = event.getPlayer();
        if (!KitManager.getPlayer(player.getName()).hasKit()) {
        	return;
        }
        else if (indo.contains(player.getName())) {
        	indo.remove(player.getName());
        }
}
}