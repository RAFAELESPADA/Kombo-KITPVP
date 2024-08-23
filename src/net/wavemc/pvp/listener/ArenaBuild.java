package net.wavemc.pvp.listener;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.command.NoBreakEvent;
import net.wavemc.pvp.warp.WaveWarp;

public class ArenaBuild implements Listener {


public static HashMap<Player, Set<Block>> placed_blocks = new HashMap<>();

public static List<Location> blocks = new ArrayList<>();

Path path1 = Paths.get(Bukkit.getServer().getWorldContainer().getAbsolutePath() + "/plugins/WaveCore/", "warps.yml");
File file = new File(path1.toAbsolutePath().toString());
YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
  
@EventHandler
public void onBlockPlace(BlockPlaceEvent event){
  Set<Block> temp_list = new HashSet<Block>();
  // HashSet is like Array. HashSet is a unordered list that allows not duplicates. I think that this is a bit faster and better for performance and uses less ram than a arraylist
  Player player = event.getPlayer();

  if (!WaveWarp.ARENABUILD.hasPlayer(player.getName())) return;
  if(placed_blocks.containsKey(player)){
    temp_list = placed_blocks.get(player);
    temp_list.add(event.getBlock());
    placed_blocks.put(player, temp_list);
  }else{
    temp_list.add(event.getBlock());
    placed_blocks.put(player, temp_list);
  }
}

public void removePlacedBlocks(Player player){
  if(!placed_blocks.containsKey(player))
    return;

  for(Block block : placed_blocks.get(player)){
    block.setType(Material.AIR);
  }
  placed_blocks.remove(player);
}
@EventHandler(priority = EventPriority.HIGHEST)
public void BlockPlaceV(BlockPlaceEvent event) {
 
    
    Player player = event.getPlayer();

    if (!WaveWarp.ARENABUILD.hasPlayer(player.getName())) return;
    blocks.add(event.getBlock().getLocation());
}    

@EventHandler(priority = EventPriority.HIGHEST)
public void BlockPlaceV(PlayerBucketEmptyEvent event) {
 
    
    Player player = event.getPlayer();

    if (!WaveWarp.ARENABUILD.hasPlayer(player.getName())) return;
    blocks.add(event.getBlockClicked().getRelative(event.getBlockFace()).getLocation());
} 
@EventHandler
public void onDestr(PlayerQuitEvent event){
	if (placed_blocks.containsKey(event.getPlayer()))
for (Block b : placed_blocks.get(event.getPlayer())) {
	  b.setType(Material.AIR);
}
  }

  


@EventHandler
public void onBucketEmpty(PlayerBucketEmptyEvent e) {
 
    Player p = e.getPlayer();
 

World l2 = Bukkit.getWorld(yaml.getString("Mundo-arenabuild"));
if (p.getWorld() == l2) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(WavePvP.getInstance(), new Runnable() {
            @Override
            public void run() {
                Block b = e.getBlockClicked().getRelative(e.getBlockFace());
                b.setType(Material.AIR);

            }
        }, 120 * 20L);
}
}
}