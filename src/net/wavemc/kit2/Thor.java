package net.wavemc.kit2;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import net.wavemc.core.bukkit.item.ItemBuilder;
import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.kit.KitHandler2;
import net.wavemc.pvp.kit.KitManager2;

public class Thor extends KitHandler2 {
	
	@Override
	public void execute(Player player) {
		super.execute(player);
		
		player.getInventory().setItem(2, new ItemBuilder("§eCaboom!", Material.GOLDEN_AXE)
				.nbt("cancel-drop")
				.nbt("kit-handler", "thor")
				.toStack()
		);
	}
	
	 /*     */   
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (event.getItem() == null) {
			return;
		}
		if (event.getItem().getType().equals(Material.MUSHROOM_STEW)) {
			return;
		}
		Player player = event.getPlayer();
		if (inCooldown(player) && KitManager2.getPlayer(player.getName()).haskit2(this)) {
			sendMessageCooldown(player);
			return;
		}
		if (!KitManager2.getPlayer(player.getName()).haskit2(this) 
				|| !event.hasItem() || !ItemBuilder.has(event.getItem(), "kit-handler", "thor") 
				|| event.getClickedBlock() == null || event.getClickedBlock().getType().equals(Material.AIR)) {
			return;
		}
		else if (player.getLocation().getY() > WavePvP.getInstance().getConfig().getInt("SpawnAltura")) {
			player.sendMessage("§cDont use the thor on spawn!");
			return;
		}
		
		event.setCancelled(true);
		
		
		
		addCooldown(event.getPlayer(), WavePvP.getInstance().getConfig().getInt("ThorCooldown"));
		player.getWorld().strikeLightning(event.getClickedBlock().getLocation());
		player.getWorld().strikeLightning(event.getClickedBlock().getLocation());
	}

	 /*     */ 
	 @EventHandler
	 /*     */   public void OnBlockBtB(EntityDamageEvent e)
	 /*     */   {
	 /* 110 */     if (!(e.getEntity() instanceof Player)) {
	 /* 111 */       return;
	 /*     */     }
	 /* 113 */     Player p = (Player)e.getEntity();
	 /* 114 */     if (KitManager2.getPlayer(p.getName()).haskit2(this) && (e.getCause() == EntityDamageEvent.DamageCause.LIGHTNING)) {
	 /* 115 */       e.setCancelled(true);
	 /*     */   }
	 }

}
