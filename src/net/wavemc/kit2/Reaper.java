package net.wavemc.kit2;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.wavemc.pvp.kit.KitHandler2;
import net.wavemc.pvp.kit.KitManager2;

public class Reaper extends KitHandler2 {

	@EventHandler
    public void OnClick(EntityDamageByEntityEvent e)
    {
      if (((e.getEntity() instanceof Player)) && ((e.getDamager() instanceof Player)))
      {
        Player damager = (Player)e.getDamager();
        Player victim = (Player)e.getEntity();

        if (KitManager2.getPlayer(damager.getName()).haskit2(this)){
          if (damager.getInventory().getItemInMainHand().getType() == Material.WOODEN_HOE) {
        	  damager.getLocation().getWorld().playEffect(damager.getLocation(), Effect.GHAST_SHOOT, 8);
        	  damager.getLocation().getWorld().playEffect(damager.getLocation(), Effect.GHAST_SHRIEK, 8);
            victim.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 3));
            victim.playSound(victim.getLocation(), Sound.ENTITY_GHAST_SCREAM, 10, 10);
           
         
      }
        }}}}
