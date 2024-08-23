package net.wavemc.pvp.listener;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import org.bukkit.scheduler.BukkitRunnable;
import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.kit.provider.EnderMageReal;
import net.wavemc.pvp.kit.provider.GladiatorListener2;
import net.wavemc.pvp.warp.WaveWarp;

public class EntityCalculateDamageListener implements Listener {
	 @EventHandler(priority = EventPriority.HIGHEST)
	    public void onDamageWoodword(final EntityDamageByEntityEvent event) {
		  if (event.isCancelled() || !(event.getDamager() instanceof Player) || !(event.getEntity() instanceof Player)) {
	            return;
	        }
	        final Player p = (Player)event.getDamager();
		 if (!Jump.caiu.containsKey(p.getName())) {
				Jump.caiu.put(p.getName(), true);

												}	
	 }

	    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	    public void onDamageWoodSword(final EntityRegainHealthEvent event) {
	        if (event.getRegainReason() == EntityRegainHealthEvent.RegainReason.SATIATED) {
	        	event.setCancelled(true);
	        }
	        }
	    @SuppressWarnings("deprecation")
		@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	    public void onDamageWoodSword(final EntityDamageByEntityEvent event) {
	        if (event.isCancelled() || !(event.getDamager() instanceof Player) || !(event.getEntity() instanceof Player)) {
	            return;
	        }
	        
	        final Player p = (Player)event.getDamager();
	        p.getInventory().getItemInMainHand().setDurability((short)0);
	        final ItemStack item = ((Player)event.getDamager()).getItemInHand();
					if (!Jump.caiu.containsKey(p.getName())) {
					Jump.caiu.put(p.getName(), true);
													}							
				
	  
			
		
	        if (item != null) {
	    
	            if (item.getType().equals((Object)Material.WOODEN_SWORD)) {
	                event.setDamage(isCritical((LivingEntity)p) ? (event.getDamage() / 1.7) : (event.getDamage() / 2.0));
	            }
	            else if (item.getType().equals((Object)Material.STONE_SWORD)) {
	                event.setDamage(isCritical((LivingEntity)p) ? (event.getDamage() / 1.8) : (event.getDamage() / 2.0));
	            }
	            else if (item.getType().equals((Object)Material.IRON_SWORD)) {
	                event.setDamage(isCritical((LivingEntity)p) ?  (event.getDamage() / 1.7) : (event.getDamage() / 2.0));
	            }
	            else if (item.getType().equals((Object)Material.DIAMOND_SWORD)) {
	                event.setDamage(isCritical((LivingEntity)p) ? (event.getDamage() / 1.7) : (event.getDamage() / 2.0));
	            }
	            else if (item.getType().equals((Object)Material.GOLDEN_SWORD)) {
	                event.setDamage(isCritical((LivingEntity)p) ? (event.getDamage() / 1.8) : (event.getDamage() / 2.0));
	            }
	            else if (item.getType().equals((Object)Material.GOLDEN_AXE)) {
	                event.setDamage(isCritical((LivingEntity)p) ? (event.getDamage() / 2) : (event.getDamage() / 2));
	            }
	            else if (item.getType().equals((Object)Material.WOODEN_AXE)) {
	                event.setDamage(isCritical((LivingEntity)p) ? (event.getDamage() / 2) : (event.getDamage() / 2));
	            }
	            else if (item.getType().equals((Object)Material.IRON_AXE)) {
	                event.setDamage(isCritical((LivingEntity)p) ? (event.getDamage() / 2) : (event.getDamage() / 2));
	            }
	            else if (item.getType().equals((Object)Material.STONE_AXE)) {
	                event.setDamage(isCritical((LivingEntity)p) ? (event.getDamage() / 2) : (event.getDamage() / 2));
	            }
	            else if (item.getType().equals((Object)Material.DIAMOND_AXE)) {
	                event.setDamage(isCritical((LivingEntity)p) ? (event.getDamage() / 2) : (event.getDamage() / 2));
	            }
	        }
	    }
	    public static boolean isCritical(final LivingEntity e) {
	        return !e.isOnGround() && e.getFallDistance() > 0.0;
	    }
	    @EventHandler
		  public void onMove2ght(EntityDamageEvent e) {
	    	if (e.getEntity().getLocation().getY() > WavePvP.getInstance().getConfig().getInt("SpawnAltura") && EnderMageReal.isSpawn(e.getEntity().getLocation()) && !GladiatorListener2.combateGlad.containsKey(e.getEntity()) && !net.wavemc.kit2.GladiatorListener.combateGlad.containsKey(e.getEntity())) {
	    		if (EventoUtils.game.contains(e.getEntity().getName())) {
			    	return;
			    }
	    		e.setCancelled(true);
	    	}
	    	}
	    	@EventHandler
	    	public void onEspada2(EntityDamageByEntityEvent e) {
	    		if (!(e.getDamager() instanceof Player)) {
	    			return;
	    		}
	    		if (!(e.getEntity() instanceof Player)) {
	    			return;
	    		}
	    		if (EventoUtils.game.contains(e.getEntity().getName())) {
			    	return;
			    }
	    		if (WaveWarp.DUELS.hasPlayer(e.getEntity().getName())) {
	    			return;
	    		}
	    		
		    	if (e.getEntity().getLocation().getY() > WavePvP.getInstance().getConfig().getInt("SpawnAltura") && EnderMageReal.isSpawn(e.getEntity().getLocation()) && !GladiatorListener2.combateGlad.containsKey(e.getEntity()) && !net.wavemc.kit2.GladiatorListener.combateGlad.containsKey(e.getEntity())) {
	    		e.setCancelled(true);
		    } 
	    	}
	    @EventHandler
		  public void onMove2t(EntityDamageEvent e) {
		    if (!(e.getEntity() instanceof Player)) {
	return;
		    }
		    Player p = (Player)e.getEntity();
			    if (!WaveWarp.SPAWN.hasPlayer(p.getName())) {
			    	return;
			    }
			    if (EventoUtils.game.contains(p.getName())) {
			    	return;
			    }
			    if (WaveWarp.DUELS.hasPlayer(e.getEntity().getName())) {
	    			return;
	    		}
			    	if (p.getLocation().getY() > WavePvP.getInstance().getConfig().getInt("SpawnAltura") && EnderMageReal.isSpawn(e.getEntity().getLocation()) && !GladiatorListener2.combateGlad.containsKey(e.getEntity()) && !net.wavemc.kit2.GladiatorListener.combateGlad.containsKey(e.getEntity())) {
			    	e.setCancelled(true);
			    	}
			    }
	    
	


	
		
	



         
          
	
	


	 


	@EventHandler
	public void otnShot(EntityDamageByEntityEvent e) {
		
			
			if (e.getEntity() instanceof Player && e.getDamager() instanceof Arrow) {
				
				Player damagedPlayer = (Player) e.getEntity();
				Arrow arrow = (Arrow) e.getDamager();
				
				if (arrow.getShooter() != null && arrow.getShooter() instanceof Player) {
					
					Player shooter = (Player) arrow.getShooter();
					
					// ARROW HEALTH MESSAGE
					
					if (damagedPlayer.getName() != shooter.getName()) {
						
						new BukkitRunnable() {
							
							@Override
							public void run() {
								
								double health = Math.round(damagedPlayer.getHealth() * 10.0) / 10.0;
								
									if (health != 20.0) {	
										
										shooter.sendMessage(ChatColor.AQUA + damagedPlayer.getName() + ChatColor.YELLOW + " está com " + health + " de vida!");									
									}						
																}							
							}
							
						.runTaskLater(WavePvP.getInstance(), 2L);
						
					}
				}
			}
		}
	
	
	
	
	
	        
	        
	 }	    

                            
                        
                
