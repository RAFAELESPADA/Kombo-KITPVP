package net.wavemc.pvp.kit.provider;


import java.awt.Event;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


import net.wavemc.core.bukkit.item.ItemBuilder;
import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.kit.Habilidade;
import net.wavemc.pvp.kit.WaveKit;
import net.wavemc.pvp.kit.WaveKit2;
import net.wavemc.pvp.kit.KitHandler;
import net.wavemc.pvp.kit.KitManager;
import net.wavemc.pvp.kit.KitManager2;
import net.wavemc.pvp.warp.WaveWarp;


public final class GladiatorListener2 extends KitHandler
{
    private static String prefix;
    public static final HashMap<String, Location> oldLocation;
    public static final HashMap<Player, Player> combateGlad;
    public static final HashMap<String, List<Location>> blocks;
    private static final DecimalFormat format = new DecimalFormat("#.#");
    public static int id;
    
    static {
        GladiatorListener2.prefix = ChatColor.BLUE + "GLAD ";
        oldLocation = new HashMap<String, Location>();
        combateGlad = new HashMap<Player, Player>();
        blocks = new HashMap<String, List<Location>>();
        GladiatorListener2.id = 0;
    }
    @Override
	public void execute(Player player) {
		super.execute(player);
		
		player.getInventory().setItem(1, new ItemBuilder("§bPuxar!", Material.IRON_BARS)
				.nbt("kit-handler", "glad")
				.nbt("cancel-drop")
				.toStack()
		);
	}
    
    public static final void showPlayer(final Player one, final Player two) {
        one.showPlayer(two);
        two.showPlayer(one);
    }
    
    @EventHandler
    public void ender(final PlayerTeleportEvent e) {
        final Player p = e.getPlayer();
        if (GladiatorListener2.combateGlad.containsKey(p) && e.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL) {
            e.setCancelled(true);
        }
    }
    

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
               double i = p.getHealth();
               if (i >= 20) {
            	   return;
               }
               if (e.getItem() == null) {
            	   return;
               }
               if (!(e.getItem().getType() == Material.MUSHROOM_STEW)) {
            	   return;
               }
                p.setHealth(Math.min(p.getMaxHealth(), p.getHealth() + 7));
                p.setFoodLevel(Math.min(20, p.getFoodLevel() + 7));
              if (KitManager.getPlayer(p.getName()).hasKit(WaveKit.AUTOBOWL) || KitManager2.getPlayer(p.getName()).haskit2(WaveKit2.AUTOBOWL)) {
                  p.getInventory().addItem(new ItemStack(Material.BOWL));
                  p.setItemInHand(new ItemStack(Material.AIR));
              }  else {
              
                    p.getInventory().setItemInHand(new ItemStack(Material.BOWL));
                  }
              
                p.updateInventory();
                e.setCancelled(true);
            }
    }
        
        
    

        
    
        




    	  


    	 
    	 
    	 

    	

           
    		


    		
    		
    	


    public static void setOffhandItem(Player p, ItemStack item) {
    		
    		if (versionToNumber() == 18) {
    			p.setItemInHand(item);
    		} else if (versionToNumber() > 18) {
    			p.setItemInHand(item);
    		} else {
    			p.setItemInHand(item);
    		}
    		
    	}
    public static int versionToNumber() {

    	String version = Bukkit.getVersion();

    	if (version.contains("1.8")) {
    		return 18;
    	} else if (version.contains("1.9")) {
    		return 19;
    	} else if (version.contains("1.10")) {
    		return 110;
    	} else if (version.contains("1.11")) {
    		return 111;
    	} else if (version.contains("1.12")) {
    		return 112;
    	} else if (version.contains("1.13")) {
    		return 113;
    	} else if (version.contains("1.14")) {
    		return 114;
    	} else if (version.contains("1.15")) {
    		return 115;
    	} else if (version.contains("1.16")) {
    		return 116;
    	} else if (version.contains("1.17")) {
    		return 117;
    	} else if (version.contains("1.18")) {
    		return 118;
    	}
    		return 500;
    		

        
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public final void onGladiatorListener(final PlayerInteractEntityEvent e) {
        final Player bp = e.getPlayer();
        if (e.getRightClicked() instanceof Player) {
            final Player toGlad = (Player)e.getRightClicked();
            if (!KitManager.getPlayer(bp.getName()).hasKit(this) || !ItemBuilder.has(bp.getItemInHand(), "kit-handler", "glad") || bp.getItemInHand().getType() != Material.IRON_BARS) {
            	return;
            }
                if (toGlad.getGameMode() == GameMode.CREATIVE) {
                    return;
                }
                if (bp.getLocation().getY() > (WavePvP.getInstance().getConfig().getInt("GladAltura")) && EnderMageReal.isSpawn(bp.getLocation())) {
                    bp.sendMessage(String.valueOf(prefix) + " §cVocê não pode desafiar essa pessoa!");
                    return;
                }
                Entity passenger = toGlad.getPassenger();
                if (passenger != null) {
                	bp.sendMessage(String.valueOf(prefix) + " §cVocê não pode desafiar essa pessoa!");
                	return;
                }
                if (GladiatorListener2.combateGlad.containsKey(bp) || net.wavemc.kit2.GladiatorListener.combateGlad.containsKey(bp)) {
					bp.sendMessage("§cVocê já está no gladiator.");
					return;
				}
				if (KitManager.getPlayer(toGlad.getName()).hasKit(WaveKit.NEO) || KitManager2.getPlayer(toGlad.getName()).haskit2(WaveKit2.NEO)) {
					bp.playSound(bp.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 15.0f, 15.0f);
					bp.sendMessage(ChatColor.RED + "Você não pode desafiar " + ChatColor.DARK_RED + toGlad.getName() + ChatColor.RED + " porque ele está com o kit" + ChatColor.DARK_RED + " NEO");
					return;
				}
                GladiatorListener2.combateGlad.put(bp, toGlad);
                GladiatorListener2.combateGlad.put(toGlad, bp);
                newGladiatorListenerArena(bp, toGlad, bp.getLocation());
            }
        }
   
    
    public static final Object newGladiatorListenerArena(final Player p1, final Player p2, final Location loc) {
        if (GladiatorListener2.id > 15) {
            GladiatorListener2.id = 0;
        }
        ++GladiatorListener2.id;
        double x = loc.getX();
        final double y = loc.getY();
        double z = loc.getZ();
        final Random random = new Random();
        double x1 = x;
        double z1 = z;
        final Location loc2 = new Location(p1.getWorld(), x1, y + 60, z1);
        final Location loc3 = new Location(p1.getWorld(), x1, y + 60.0, z1);
        final Location loc4 = new Location(p1.getWorld(), x1, y + 60.0, z1);
        final List<Location> location = new ArrayList<Location>();
        location.clear();
        for (int blockX = -9; blockX <= 9; ++blockX) {
            for (int blockZ = -9; blockZ <= 9; ++blockZ) {
                for (int blockY = -1; blockY <= 9; ++blockY) {
                    final Block b = loc2.clone().add((double)blockX, (double)blockY, (double)blockZ).getBlock();
                    if (!b.isEmpty()) {
                        final Location newLoc = new Location(p1.getWorld(), loc2.getBlockX() + x, y + 50, loc2.getBlockZ() + z);
                        return newGladiatorListenerArena(p1, p2, newLoc);
                    }
                    if (blockY == 9) {
                        location.add(loc2.clone().add((double)blockX, (double)blockY, (double)blockZ));
                    }
                    else if (blockY == -1) {
                        location.add(loc2.clone().add((double)blockX, (double)blockY, (double)blockZ));
                    }
                    else if (blockX == -9 || blockZ == -9 || blockX == 9 || blockZ == 9) {
                        location.add(loc2.clone().add((double)blockX, (double)blockY, (double)blockZ));
                    }
                }
            }
        }
        for (final Location arena : location) {
            arena.getBlock().setType(Material.GLASS);
        }
        GladiatorListener2.oldLocation.put(p1.getName(), p1.getLocation());
        GladiatorListener2.oldLocation.put(p2.getName(), p2.getLocation());
        GladiatorListener2.blocks.put(p1.getName(), location);
        GladiatorListener2.blocks.put(p2.getName(), location);
        p1.teleport(new Location(p1.getWorld(), loc3.getX() + 5.5, loc3.getY() + 1.0, loc3.getZ() + 5.5, 140.0f, 0.0f));
        p2.teleport(new Location(p2.getWorld(), loc4.getX() - 5.5, loc4.getY() + 1.0, loc2.getZ() - 5.5, -40.0f, 0.0f));
        p1.sendMessage(String.valueOf(GladiatorListener2.prefix) + "§fVocê desafiou §e" + p2.getName() + " §fpara um 1v1 no Gladiator!");
        p2.sendMessage(String.valueOf(GladiatorListener2.prefix) + "§fVocê desafiou §e" + p1.getName() + " §fpara um 1v1 no Gladiator!");
        showPlayer(p1, p2);
        Bukkit.getScheduler().scheduleSyncDelayedTask(WavePvP.getInstance(), new Runnable() {
        	public void run() {
        		if (combateGlad.containsKey(p1) && combateGlad.containsKey(p2)) {
        		Kangaroo.darEfeito(p1, PotionEffectType.WITHER, 9999, 1);
        		Kangaroo.darEfeito(p2, PotionEffectType.WITHER, 9999, 1);
        	}
        	}
    }, 20 * 60 *5L);
        Bukkit.getScheduler().scheduleSyncDelayedTask(WavePvP.getInstance(), new Runnable() {
        	public void run() {
        		if (combateGlad.containsKey(p1) && combateGlad.containsKey(p2)) {
        		p1.damage(p1.getHealth());
        	}
        	}
    }, 20 * 60 *8L);
        return null;
        }
    
    public static final void resetGladiatorListenerByKill(final Player winner, final Player loser) {
        for (int i = 1; i < 5; ++i) {
            winner.teleport((Location)GladiatorListener2.oldLocation.get(winner.getName()));
        }
        for (int i = 1; i < 5; ++i) {
            loser.teleport((Location)GladiatorListener2.oldLocation.get(loser.getName()));
        }
        for (final PotionEffect pot : winner.getActivePotionEffects()) {
            winner.removePotionEffect(pot.getType());
        }
        for (final PotionEffect pot : loser.getActivePotionEffects()) {
            loser.removePotionEffect(pot.getType());
        }
        for (final Location loc : GladiatorListener2.blocks.get(winner.getName())) {
            loc.getBlock().setType(Material.AIR);
        }
        for (final Location loc : GladiatorListener2.blocks.get(loser.getName())) {
            loc.getBlock().setType(Material.AIR);
        }
        GladiatorListener2.blocks.remove(winner.getName());
        GladiatorListener2.oldLocation.remove(winner.getName());
        GladiatorListener2.blocks.remove(loser.getName());
        GladiatorListener2.oldLocation.remove(loser.getName());
        GladiatorListener2.combateGlad.remove(winner);
        GladiatorListener2.combateGlad.remove(loser);
    }
    
    public static final void resetGladiatorListenerByScreenshare(final Player winner, final Player loser) {
    	if (WaveWarp.DUELS.hasPlayer(winner.getName())) {
    		WaveWarp.DUELS.send(winner, true);
    		WaveWarp.DUELS.send(loser, true);
    		return;
    	}
        for (int i = 1; i < 5; ++i) {
            winner.teleport((Location)GladiatorListener2.oldLocation.get(winner.getName()));
        }
        for (final PotionEffect pot : winner.getActivePotionEffects()) {
            winner.removePotionEffect(pot.getType());
        }
        for (final PotionEffect pot : loser.getActivePotionEffects()) {
            loser.removePotionEffect(pot.getType());
        }
        for (final Location loc : GladiatorListener2.blocks.get(winner.getName())) {
            loc.getBlock().setType(Material.AIR);
        }
        for (final Location loc : GladiatorListener2.blocks.get(loser.getName())) {
            loc.getBlock().setType(Material.AIR);
        }
        GladiatorListener2.blocks.remove(winner.getName());
        GladiatorListener2.oldLocation.remove(winner.getName());
        GladiatorListener2.blocks.remove(loser.getName());
        GladiatorListener2.oldLocation.remove(loser.getName());
        loser.sendMessage(String.valueOf(GladiatorListener2.prefix) + "§fVoc\u00ea perdeu o combate pois foi puxado para screeshare.");
        winner.sendMessage(String.valueOf(GladiatorListener2.prefix) + "§e" + loser.getName() + " §ffoi puxado para screenshare.");
    }
    
    public static final void resetGladiatorListenerByQuit(final Player winner, final Player loser) {
      {
    	  if (WaveWarp.DUELS.hasPlayer(winner.getName())) {
      		WaveWarp.DUELS.send(winner, true);
      		WaveWarp.DUELS.send(loser, true);
      		return;
      	}
        	if (winner != null) {
            winner.teleport((Location)GladiatorListener2.oldLocation.get(winner.getName()));
            winner.getActivePotionEffects().forEach(potion -> winner.removePotionEffect(potion.getType()));	
        }  
        	if (loser != null) {
        		loser.getActivePotionEffects().forEach(potion -> loser.removePotionEffect(potion.getType()));	
        }
        	
        	if (blocks.get(winner.getName()) != null) {
        for (final Location loc : GladiatorListener2.blocks.get(winner.getName())) {
        	if (winner != null) {
            loc.getBlock().setType(Material.AIR);
        	}
        }
        }
        	if (blocks.get(loser.getName()) != null) {
        for (final Location loc : GladiatorListener2.blocks.get(loser.getName())) {
        	if (loser != null) {
            loc.getBlock().setType(Material.AIR);
        }
        }
        }
        GladiatorListener2.blocks.remove(winner.getName());
        GladiatorListener2.oldLocation.remove(winner.getName());
        GladiatorListener2.blocks.remove(loser.getName());
        GladiatorListener2.oldLocation.remove(loser.getName());
        GladiatorListener2.combateGlad.remove(winner);
        GladiatorListener2.combateGlad.remove(loser);
        if (winner != null) {
        winner.sendMessage(String.valueOf(GladiatorListener2.prefix) + "§fO Jogador §e" +  loser.getName() + " §fdeslogou.");
    }
      }
        }
      public static final void resetGladiatorListenerByGlad(final Player winner, final Player loser) {
          {
            	if (winner != null) {
                winner.getActivePotionEffects().forEach(potion -> winner.removePotionEffect(potion.getType()));	
            }  
            	if (loser != null) {
            		loser.getActivePotionEffects().forEach(potion -> loser.removePotionEffect(potion.getType()));	
            }
            	
            	if (blocks.get(winner.getName()) != null) {
            for (final Location loc : GladiatorListener2.blocks.get(winner.getName())) {
            	if (winner != null) {
                loc.getBlock().setType(Material.AIR);
            	}
            }
            }
            	if (blocks.get(loser.getName()) != null) {
            for (final Location loc : GladiatorListener2.blocks.get(loser.getName())) {
            	if (loser != null) {
                loc.getBlock().setType(Material.AIR);
            }
            }
            }
            GladiatorListener2.blocks.remove(winner.getName());
            GladiatorListener2.oldLocation.remove(winner.getName());
            GladiatorListener2.blocks.remove(loser.getName());
            GladiatorListener2.oldLocation.remove(loser.getName());
            GladiatorListener2.combateGlad.remove(winner);
            GladiatorListener2.combateGlad.remove(loser);
            }
            
        }
        @EventHandler
        public void PlayerMove(PlayerMoveEvent event) {
            if (event.isCancelled()) return;
            Player player = event.getPlayer();
            Block block = event.getTo().getBlock().getRelative(BlockFace.DOWN);
            final Player winner = GladiatorListener2.combateGlad.get(player);
            if (!combateGlad.containsKey(player) || !combateGlad.containsKey(winner)) {
            	return;
            }
            if (!block.toString().contains("GLASS") || !block.toString().contains("AIR")) {
            	return;
            }
            resetGladiatorListenerBySpawn(winner , player);
            }
    
    
    public static final void resetGladiatorListenerBySpawn(final Player winner, final Player loser) {
    	if (WaveWarp.DUELS.hasPlayer(winner.getName())) {
    		WaveWarp.DUELS.send(winner, true);
    		WaveWarp.DUELS.send(loser, true);
    		return;
    	}
        for (int i = 1; i < 5; ++i) {
            winner.teleport((Location)GladiatorListener2.oldLocation.get(winner.getName()));
        }
        for (final PotionEffect pot : winner.getActivePotionEffects()) {
            winner.removePotionEffect(pot.getType());
        }
        for (final PotionEffect pot : loser.getActivePotionEffects()) {
            loser.removePotionEffect(pot.getType());
        }
        for (final Location loc : GladiatorListener2.blocks.get(winner.getName())) {
            loc.getBlock().setType(Material.AIR);
        }
        for (final Location loc : GladiatorListener2.blocks.get(loser.getName())) {
            loc.getBlock().setType(Material.AIR);
        }
        GladiatorListener2.blocks.remove(winner.getName());
        GladiatorListener2.oldLocation.remove(winner.getName());
        GladiatorListener2.blocks.remove(loser.getName());
        GladiatorListener2.oldLocation.remove(loser.getName());
        GladiatorListener2.combateGlad.remove(winner);
        GladiatorListener2.combateGlad.remove(loser);
        winner.sendMessage(String.valueOf(GladiatorListener2.prefix) + "§fO Jogador §e" +  loser.getName() + " §fretornou pro spawn.");
    }
}
