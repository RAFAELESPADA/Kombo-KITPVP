package net.wavemc.pvp.kit.provider;

import net.wavemc.core.bukkit.item.ItemBuilder;
import net.wavemc.core.util.WaveCooldown;
import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.kit.KitHandler;
import net.wavemc.pvp.kit.KitManager;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.Plugin;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Phantom extends KitHandler {
	public static HashMap<String, ItemStack[]> salvararmor;
    public static ArrayList<String> emphantom;
	 static {
		 
	        Phantom.salvararmor = new HashMap<String, ItemStack[]>();
	        Phantom.emphantom = new ArrayList<String>();
	    }
    @Override
    public void execute(Player player) {
        super.execute(player);

    }
    public static void tirarArmadura(final Player p) {
        p.getInventory().setHelmet(new ItemStack(Material.AIR));
        p.getInventory().setChestplate(new ItemStack(Material.AIR));
        p.getInventory().setLeggings(new ItemStack(Material.AIR));
        p.getInventory().setBoots(new ItemStack(Material.AIR));
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
    	if (event.getItem() == null) {
			return;
		}
    	if (event.getItem().getType().equals(Material.MUSHROOM_STEW)) {
			return;
		}
        if (!event.hasItem() || !ItemBuilder.has(event.getItem(), "kit-handler", "phantom")) return;

if (!KitManager.getPlayer(event.getPlayer().getName()).hasKit(this)) {
	return;
}
        if (WaveCooldown.inCooldown(event.getPlayer().getName(), "kit-phantom")) {
            event.getPlayer().sendMessage("§cSeu kit está em Cooldown.");
            return;
        }
        addCooldown(event.getPlayer(), 25);
        event.setCancelled(true);
        Player p = event.getPlayer();
        Phantom.salvararmor.put(p.getName(), p.getInventory().getArmorContents());
        ItemStack Capacete = new ItemStack(Material.LEATHER_HELMET);
        /*  89 */       LeatherArmorMeta kCapacete = (LeatherArmorMeta)Capacete.getItemMeta();
        /*  90 */       kCapacete.setColor(Color.WHITE);
        /*  91 */       Capacete.setItemMeta(kCapacete);
        /*  92 */       Capacete.addEnchantment(Enchantment.PROTECTION, 3);
        /*  93 */       ItemStack Peitoral = new ItemStack(Material.LEATHER_CHESTPLATE);
        /*  94 */       LeatherArmorMeta kPeitoral = (LeatherArmorMeta)Peitoral.getItemMeta();
        /*  95 */       kPeitoral.setColor(Color.WHITE);
        /*  96 */       Peitoral.setItemMeta(kPeitoral);
        /*  97 */       Peitoral.addEnchantment(Enchantment.PROTECTION, 3);
        /*  98 */       ItemStack Calss = new ItemStack(Material.LEATHER_LEGGINGS);
        /*  99 */       LeatherArmorMeta kCalss = (LeatherArmorMeta)Calss.getItemMeta();
        /* 100 */       kCalss.setColor(Color.WHITE);
        /* 101 */       Calss.setItemMeta(kCalss);
        /* 102 */       Calss.addEnchantment(Enchantment.PROTECTION, 3);
        /* 103 */       ItemStack Bota = new ItemStack(Material.LEATHER_BOOTS);
        /* 104 */       LeatherArmorMeta kBota = (LeatherArmorMeta)Capacete.getItemMeta();
        /* 105 */       kBota.setColor(Color.WHITE);
        /* 106 */       Bota.setItemMeta(kBota);
        /* 107 */       Bota.addEnchantment(Enchantment.PROTECTION, 3);
        
        /*     */       
        /* 110 */       event.getPlayer().getInventory().setHelmet(Capacete);
        /* 111 */       event.getPlayer().getInventory().setChestplate(Peitoral);
        /* 112 */       event.getPlayer().getInventory().setLeggings(Calss);
        /* 113 */       event.getPlayer().getInventory().setBoots(Bota);
        /* 114 */       event.getPlayer().updateInventory();
        WaveCooldown.create(event.getPlayer().getName(), "kit-phantom", TimeUnit.SECONDS, 25);
        event.getPlayer().setAllowFlight(true);
        event.getPlayer().setFlying(true);
        Phantom.emphantom.add(p.getName());
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)WavePvP.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
            	p.sendMessage("§e5 segundos restante.");
            }
        }, 0L);
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)WavePvP.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
            	p.sendMessage("§e4 segundos restante.");
            }
        }, 20L);
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)WavePvP.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
            	p.sendMessage("§e3 segundos restante.");
            }
        }, 40L);
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)WavePvP.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
            	p.sendMessage("§e2 segundos restante.");
            }
        }, 60L);
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)WavePvP.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
            	p.sendMessage("§e1 segundos restante.");
            }
        }, 80L);
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)WavePvP.getInstance(), (Runnable)new Runnable() {
            @Override
            public void run() {
            	p.sendMessage("§eSeu modo voô acabou.");
                Phantom.emphantom.remove(p.getName());
                p.getInventory().setArmorContents((ItemStack[])Phantom.salvararmor.get(p.getName()));
                p.updateInventory();
                p.setAllowFlight(false);
                p.setFlying(false);
            }
        }, 100L);
    }

@EventHandler
public void aoinv(final InventoryClickEvent e) {
    try {
        final Player p = (Player)e.getWhoClicked();
        if (Phantom.emphantom.contains(p.getName()) && e.getSlotType().equals((Object)InventoryType.SlotType.ARMOR)) {
            e.setCancelled(true);
        }
    }
    catch (Exception ex) {}
}
}      
      