package net.wavemc.kit2;




/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
import java.util.concurrent.TimeUnit;

import net.wavemc.core.bukkit.item.ItemBuilder;
import net.wavemc.core.util.WaveCooldown;
import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.kit.KitHandler2;
import net.wavemc.pvp.kit.KitManager;
import net.wavemc.pvp.kit.KitManager2;

/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.Effect;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.inventory.InventoryType;
/*     */ import org.bukkit.event.player.PlayerDropItemEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
/*     */ import org.bukkit.scheduler.BukkitScheduler;
/*     */ import org.bukkit.util.Vector;

/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Archer extends KitHandler2 
/*     */ {
	@Override
	public void execute(Player player) {
		super.execute(player);
		
		player.getInventory().setItem(2, new ItemBuilder("§aArco!", Material.BOW)
				.nbt("kit-handler", "arco")
				.nbt("cancel-drop")
				.toStack());
				player.getInventory().setItem(10, new ItemBuilder("§aFlecha!", Material.ARROW)
						.nbt("kit-handler", "flecha").addEnchant(Enchantment.SHARPNESS, 1)
						.nbt("cancel-drop").addFlags(
								ItemFlag.HIDE_ENCHANTS)
						.toStack()
		);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
    public void bowUseEvent(EntityShootBowEvent event) {
        if (event.getEntity() instanceof Player) {
      
            Player player = (Player) event.getEntity();
            if (!KitManager2.getPlayer(event.getEntity().getName()).haskit2(this)) {
            	return;
            }
          player.getInventory().addItem(new ItemStack(Material.ARROW));
    
          
}
	}}

	

