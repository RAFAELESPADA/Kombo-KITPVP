package net.wavemc.pvp.kit.provider;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.kit.WaveKit;
import net.wavemc.pvp.kit.WaveKit2;
import net.wavemc.pvp.listener.Jump;
import net.wavemc.pvp.kit.KitHandler;
import net.wavemc.pvp.kit.KitManager;
import net.wavemc.pvp.kit.KitManager2;


public class Stomper extends KitHandler {
	private ArrayList<String> fall = new ArrayList<String>();

	public static ArrayList<String> usou = new ArrayList<String>();
	@EventHandler(ignoreCancelled = true)
	public void onDamage(EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		Player player = (Player) event.getEntity();
		
		if (!KitManager.getPlayer(player.getName()).hasKit(this) 
				|| !event.getCause().equals(DamageCause.FALL)) {
			return;
		}

		/*     */       {
		/*  49 */         for (Entity ent : player.getNearbyEntities(5.0, 5.0, 5.0)) {
		/*  50 */           if ((ent instanceof Player))
		/*     */           {
		/*  52 */             Player plr = (Player)ent;
		if (!usou.contains(player.getName())) {
			  
			  player.sendMessage("§cSeu kit Stomper falhou porque você pulou do spawn.");
			  event.setCancelled(true);
			  usou.add(player.getName());
			  return;
			  }
		if (KitManager.getPlayer(plr.getName()).hasKit(WaveKit.ANTISTOMPER) || KitManager2.getPlayer(plr.getName()).haskit2(WaveKit2.ANTISTOMPER)) {
			plr.sendMessage(ChatColor.RED + "Seu antistomper te protegeu.");
			player.sendMessage(ChatColor.RED + "Seu stomper falhou porque o seu inimigo estava de antistomper.");
			event.setCancelled(true);
			return;
		}
		Player mage = (Player) event.getEntity();
		if (mage.getLocation().getY() > WavePvP.getInstance().getConfig().getInt("SpawnAltura") && EnderMageReal.isSpawn(mage.getLocation())) {
			return;
		 }
		if (fall.contains(player.getName())) {
			event.setCancelled(true);
			return;
		}
		else if (!KitManager.getPlayer(plr.getName()).hasKit()) {
			return;
		}
		if (!Jump.recebeu.containsKey(mage.getName())) {
			  
			  player.sendMessage("§eSeu kit Stomper falhou porque você pulou do spawn.");
			  event.setCancelled(true);
			  return;
			  }
		
		/*  58 */             if (plr.isSneaking())
		/*     */             {
		/*  60 */               plr.damage(6.0D, player);
		/*  61 */               plr.sendMessage(ChatColor.GRAY + "Você foi stompado por: " + ChatColor.AQUA + player.getName());
		/*     */             }
		/*     */             else
		/*     */             {
			plr.damage(event.getDamage(), player);
		    plr.damage(player.getFallDistance());
		/*  66 */               plr.sendMessage(ChatColor.GRAY + "Você foi stompado por: " + ChatColor.AQUA +  player.getName());
		/*     */             }
		/*     */           }
		/*     */         }
		/*  71 */         player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
		/*  72 */         event.setDamage(4.0D);
		/*  73 */         return;
		/*     */       }
		/*     */     }
	
	@EventHandler
	public void RemoverDano(PlayerDeathEvent e) 
	{
		usou.remove(e.getEntity().getName());
	}
	  @EventHandler
			public void RemoverDano(EntityDamageEvent e) 
			{
			   if (!(e.getEntity() instanceof Player)) {
		           return;
		       }
			   
				Player p = (Player) e.getEntity();
				if (!KitManager.getPlayer(p.getName()).hasKit(this)) {
					return;
				}
				if (p.getLocation().getY() < 95) {
					return;
				}
				if (e.getCause() == EntityDamageEvent.DamageCause.FALL && this.fall.contains(p.getName())) 
				{
					this.fall.remove(p.getName());
					
				
				}
				else if(e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK)
				{
					this.fall.add(p.getName());
					 Bukkit.getScheduler().scheduleSyncDelayedTask(WavePvP.getInstance() , new BukkitRunnable() {
				         @Override
				         public void run() {
				        	fall.remove(p.getName());
				             }
				         }
				     , 120);
			
				}
			}
}
		/*     */   
		
		
