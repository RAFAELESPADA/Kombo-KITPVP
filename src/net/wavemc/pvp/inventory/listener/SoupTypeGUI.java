package net.wavemc.pvp.inventory.listener;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class SoupTypeGUI implements Listener {

    public static final ItemStack ICON = ItemUtils.getCustomItemStack(Material.MUSHROOM_STEW, "§6Mude seu estilo de sopa", (String) null);

    

    @EventHandler
    private void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
       
        if (event.getCurrentItem() != null && event.getCurrentItem().getItemMeta() != null && event.getView().getTitle().contains("§eMude seu estilo de sopa")) {
            if (event.getCurrentItem().getType() != Material.AIR) {
                event.setCancelled(true);
                player.closeInventory();
                if (event.getCurrentItem().getItemMeta().getDisplayName().contains("Cogumelos")) {
                    if (PlayerCache.cacheSoupType.get(player.getName()) == "mush") {
                        player.sendMessage("§cO seu estilo de sopa já é o selecionado.");
                    } else {
                       
                        if (!PlayerCache.cacheSoupType.containsKey(player.getName()))
                            PlayerCache.cacheSoupType.put(player.getName(), "mush");
                        else
                            PlayerCache.cacheSoupType.replace(player.getName(), "mush");
                        player.sendMessage("§aVocê alterou seu estilo de sopa para: §eCOGUMELOS§a.");
                    }
                } else {

                    if (PlayerCache.cacheSoupType.get(player.getName()) == "cocoa") {
                        player.sendMessage("§cO seu estilo de sopa já é o selecionado.");
                    } else {

                        if (!PlayerCache.cacheSoupType.containsKey(player.getName()))
                            PlayerCache.cacheSoupType.put(player.getName(), "cocoa");
                        else
                            PlayerCache.cacheSoupType.replace(player.getName(), "cocoa");
                        player.sendMessage("§aVocê alterou seu estilo de sopa para: §eCOCOA BEANS§a.");
                    }
                }
            }
        }
    }

    public static void openGUI(Player player) {
        
        final Inventory inv = Bukkit.createInventory(null, 9, "§eMude seu estilo de sopa");
        if (PlayerCache.cacheSoupType.get(player.getName()) == "mush") {
            inv.setItem(3, ItemUtils.getCustomItemStack(Material.RED_MUSHROOM, "§aCogumelos", Arrays.asList("§7Você receberá cogumelos.", " ", "§a§oSelecionado")));
            inv.setItem(5, ItemUtils.editItemStack(ItemUtils.cocoa, "§cCocoa Beans", Arrays.asList("§7Você receberá cocoa beans.", " ", "§c§oNão selecionado")));
        } else {
            inv.setItem(3, ItemUtils.getCustomItemStack(Material.RED_MUSHROOM, "§cCogumelos", Arrays.asList("§7Você receberá cogumelos.", " ", "§c§oNão selecionado")));
            inv.setItem(5, ItemUtils.editItemStack(ItemUtils.cocoa, "§aCocoa Beans", Arrays.asList("§7Você receberá cocoa beans.", " ", "§a§oSelecionado")));
        }
        player.openInventory(inv);
    }

}