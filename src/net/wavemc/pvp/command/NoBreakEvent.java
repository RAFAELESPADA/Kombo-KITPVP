package net.wavemc.pvp.command;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/*    */ 


import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.util.BuildUtil;
import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.kit.provider.GladiatorListener2;
import net.wavemc.pvp.listener.ArenaBuild;
import net.wavemc.pvp.listener.EventoUtils;
import net.wavemc.pvp.warp.WaveWarp;

public final class NoBreakEvent
  implements Listener, CommandExecutor
{
	  public static HashMap<Block, BlockData> blocodata = new HashMap();

	  public static HashMap<Block, Material> blocosalv = new HashMap();
  public static ArrayList<Player> embuild = new ArrayList<Player>();
  Path path1 = Paths.get(Bukkit.getServer().getWorldContainer().getAbsolutePath() + "/plugins/WaveCore/", "warps.yml");
	File file = new File(path1.toAbsolutePath().toString());
	YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    Player p = (Player)sender;
    if (cmd.getName().equalsIgnoreCase("build")) {
      if (p.hasPermission("wavemc.build"))
      {
        if (args.length == 0)
        {
          if (!embuild.contains(p))
          {
           embuild.add(p);
            p.sendMessage(String.valueOf("§c§lBUILD") + "§aAgora você pode construir");
            p.setGameMode(GameMode.CREATIVE);
          }
          else
          {
        	  embuild.remove(p);
            p.sendMessage(String.valueOf("§c§lBUILD") + "§cVocê agora não pode construir");
            p.setGameMode(GameMode.SURVIVAL);
          }
        }
        else
        {
          Player t = Bukkit.getPlayer(args[0]);
          if (t == null)
          {
            p.sendMessage("§c§lBUILD" + "§cJogador offline");
            return true;
          }
          if (!embuild.contains(t))
          {
        	  embuild.add(t);
            p.sendMessage(String.valueOf("§c§lBUILD") + "§aAgora: §7" + t.getName() + " §apode construir");
          }
          else
          {
        	  embuild.remove(t);
            p.sendMessage(String.valueOf("§c§lBUILD") + "§aAgora: §7" + t.getName() + " §anão pode construir");
          }
        }
      }
      else {
        p.sendMessage(String.valueOf("§c§lBUILD") + "§cVocê não tem acesso a esse comando!");
      }
    }
    return false;
  }
  
  @EventHandler(priority = EventPriority.MONITOR)
  public void aoconstruir(BlockPlaceEvent e)
  {
		if (EventoUtils.build && EventoUtils.game.contains(e.getPlayer().getName())) {
	  		  e.setCancelled(false);
	  		return;
	  	}
		if (WaveWarp.ARENABUILD.hasPlayer(e.getPlayer().getName())) {
		
		  		return;
		  		
		}
	  if (!embuild.contains(e.getPlayer())) {
	    	
    	
      e.setCancelled(true);
    }
  }
    @EventHandler(priority = EventPriority.MONITOR)
    public void aoconstruir3(PlayerBucketEmptyEvent e)
    {
    	if (EventoUtils.build && EventoUtils.game.contains(e.getPlayer().getName())) {
    		  e.setCancelled(false);
    		return;
    	}
    	if (WaveWarp.ARENABUILD.hasPlayer(e.getPlayer().getName())) {
			  
		  		return;
		  		
		}
    	  if (!embuild.contains(e.getPlayer())) {
    	
    	
        e.setCancelled(true);
      }
  }
    @EventHandler(priority = EventPriority.MONITOR)
    public void aoconstvruir3(SignChangeEvent e)
    {
    	if (EventoUtils.build && EventoUtils.game.contains(e.getPlayer().getName())) {
    		  e.setCancelled(false);
    		return;
    	}
    	if (WaveWarp.ARENABUILD.hasPlayer(e.getPlayer().getName())) {
			  
		  		return;
		  		
		}
    	  if (!embuild.contains(e.getPlayer())) {
    	
    	
        e.setCancelled(true);
      }
  }
  
    @SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.MONITOR)
  public void aoconstruir(BlockBreakEvent e)
  {
    	if (EventoUtils.build && EventoUtils.game.contains(e.getPlayer().getName()) && e.getBlock().getType() != Material.GLASS) {
  		  e.setCancelled(false);
  		return;
  	}
    	else if (WaveWarp.ARENABUILD.hasPlayer(e.getPlayer().getName()) && e.getPlayer().getLocation().getY() < yaml.getInt("Y-arenabuild") - 1) {
    		if (ArenaBuild.placed_blocks.get(e.getPlayer()) == null) {
    			e.setCancelled(true);
    			e.getPlayer().sendMessage("COLOQUE ALGUM BLOCO ANTES E QUEBRAR ALGUM OUTRO!");
    			
    			return;
    		}
    		e.setCancelled(false);
    		if (ArenaBuild.placed_blocks.get(e.getPlayer()).contains(e.getBlock())) {
    			return;
    		}
    		blocosalv.put(e.getBlock(), e.getBlock().getType());
    		blocodata.put(e.getBlock(), e.getBlock().getBlockData());
    		WaveBukkit.getExecutorService().submit(() -> {
    			new BukkitRunnable() {
    				@Override
    				public void run() {
    					
    					e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_GLASS_BREAK, 10, 10);
    				 Block tp = e.getPlayer().getWorld().getHighestBlockAt(e.getPlayer().getLocation());
                        if (e.getPlayer().getLocation().getY() < yaml.getInt("Y-arenabuild") - 15) {
                        	Location l = tp.getLocation();
    				
                        
    					e.getPlayer().teleport(l);
                        }
    					e.getBlock().setType(blocosalv.get(e.getBlock()));
                        e.getBlock().setBlockData(e.getBlock().getBlockData());
    				}}.runTaskLater(WavePvP.getInstance(), 10 * 10L);
    		
    		});
		  return;		  		
		}
    	
    	else if (GladiatorListener2.combateGlad.containsKey(e.getPlayer()) && e.getBlock().getType() == Material.GLASS) {
    		e.getBlock().setType(Material.BEDROCK);
    		e.setCancelled(true);
    		WaveBukkit.getExecutorService().submit(() -> {
    			new BukkitRunnable() {
    				@Override
    				public void run() {
    					e.getBlock().setType(Material.GLASS);
    				}}.runTaskLater(WavePvP.getInstance(), 20 * 2L);
    		});
    	}
    	else if (!embuild.contains(e.getPlayer())) {    	
      e.setCancelled(true);
    }
  }
}

