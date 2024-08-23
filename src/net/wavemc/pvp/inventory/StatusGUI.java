package net.wavemc.pvp.inventory;



import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.clip.placeholderapi.PlaceholderAPI;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;
import net.wavemc.core.bukkit.account.provider.PlayerPvP;
import net.wavemc.pvp.inventory.listener.ItemUtils;
import net.wavemc.pvp.inventory.listener.SoupTypeGUI;
import net.wavemc.pvp.listener.Ranking;

public class StatusGUI implements Listener {


    

    @EventHandler
    private void onPlayerInteract(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        if (player.getItemInHand().getType().equals(Material.PLAYER_HEAD)) {
            if (event.getRightClicked().getType() == EntityType.ARMOR_STAND)
                event.setCancelled(true);
        }
    }

    @EventHandler
    private void onInventoryClick(InventoryClickEvent event) {
    	if (event.getInventory().getType() == InventoryType.PLAYER) {
            return;
        }
        if (event.getWhoClicked() instanceof Player && event.getView().getTitle().contains("Perfil de ") && event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    private void onInventboryClick(InventoryClickEvent event) {
    	if (event.getInventory().getType() == InventoryType.PLAYER) {
            return;
        }
        if (event.getWhoClicked() instanceof Player && event.getView().getTitle().equals(Duelos.getInventoryName()) && event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()) {
            event.setCancelled(true);
        }
    }
    

        @EventHandler
        private void onInventbohbyClihck(InventoryClickEvent event) {
        	if (event.getInventory().getType() == InventoryType.PLAYER) {
                return;
            }
            if (event.getWhoClicked() instanceof Player && event.getView().getTitle().equals(Extras.getInventoryName()) && event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()) {
                event.setCancelled(true);
                if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aMudar estilo de sopa")) {
                	SoupTypeGUI.openGUI((Player)event.getWhoClicked());
                }
                if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aEstatísticas gerais")) {
                	StatusGUI.openGUI((Player)event.getWhoClicked(), (Player)event.getWhoClicked());
                }
            }
    }

    @EventHandler
    private void onvInventoryClick(InventoryClickEvent event) {
    	if (event.getInventory().getType() == InventoryType.PLAYER) {
            return;
        }
        if (event.getWhoClicked() instanceof Player && event.getView().getTitle().contains("Perfil de ") && event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()) {
        	if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Duels")) {
        		Player nulo = Bukkit.getPlayerExact(event.getView().getTitle().replace("Perfil de ", ""));
        		if (nulo == null) {
        			event.getWhoClicked().closeInventory();
        			return;
        		}
        		Duelos.open((Player)event.getWhoClicked(), Bukkit.getPlayerExact(event.getView().getTitle().replace("Perfil de ", "")));
        	}
            
        }
    }

    public static void openGUI(Player player, Player target) {
    	 LuckPerms api = LuckPermsProvider.get();
    	 WavePlayer helixPlayer = WaveBukkit.getInstance().getPlayerManager()
 				.getPlayer(target.getName());
    	PlayerPvP pvp = helixPlayer.getPvp();
        Ranking playerRank = Ranking.getRank(helixPlayer);
        Inventory inv = Bukkit.createInventory(null, 27, "Perfil de " + target.getName());
        
            
        
        double kdr = pvp.getDeaths() == 0 ? (double) pvp.getKills() : (double) pvp.getKills() / (double) pvp.getDeaths();
          
        inv.setItem(10, ItemUtils.editItemStack(ItemUtils.getPlayerSkull(target.getName()), "§6Informações", Arrays.asList("§fNick: §a" + target.getName(), "§fCoins: §a" + new DecimalFormat("###,###.##").format(pvp.getCoins()), "§fVIP: §a" + (target.hasPermission("displayname.mystical") || target.hasPermission("group.campeao") || target.hasPermission("group.imperador") || target.hasPermission("wave.beta") || target.hasPermission("wave.slayer") || target.hasPermission("wave.vip") ? "Sim" : "Não") ,ChatColor.WHITE + "Tag Atual: " + ChatColor.BLUE + PlaceholderAPI.setPlaceholders(target, "%luckperms_prefix%").replace("&", "§"), "§fRank: §7(" + playerRank.getColoredSymbol() + "§7) §f- " +  playerRank.getColoredName() , "§fXP: §a" + pvp.getXp())));
        inv.setItem(13, ItemUtils.getCustomItemStack(Material.IRON_CHESTPLATE, "§6PvP", Arrays.asList("§fMatou: §a" + pvp.getKills(), "§fMorreu: §a" + pvp.getDeaths(), "§fKDR: §a" + String.format("%.2f",kdr),"§fKillstreak: §a" + pvp.getKillstreak(),"§fKills na FPS: §a" + pvp.getKillsfps() ,"§fMortes na FPS: §a" + pvp.getDeathsfps() ,"§fVezes passada no Challenge: §a" + pvp.getPassouchallenge())));
        
        inv.setItem(16, ItemUtils.getCustomItemStack(Material.BLAZE_ROD, "§6Duels", Arrays.asList("§7Clique para ver os status do duelos")));
        
        
       player.openInventory(inv);
    }

}
