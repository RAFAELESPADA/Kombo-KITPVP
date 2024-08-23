package net.wavemc.pvp.command;


import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.wavemc.core.bukkit.item.ItemBuilder;
import net.wavemc.core.bukkit.util.AdminUtil;

import java.util.HashMap;

public class Admin implements CommandExecutor {

    private static final HashMap<String, ItemStack[]> itemContents = new HashMap<>();
    private static final HashMap<String, ItemStack[]> armorContents = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;

        if (!player.hasPermission("wave.cmd.admin")) {
            player.sendMessage("§cVocê não tem permissão.");
            return true;
        }

        if (!AdminUtil.has(player.getName())) {
            AdminUtil.add(player.getName());
            itemContents.put(player.getName(), player.getInventory().getContents());
            armorContents.put(player.getName(), player.getInventory().getArmorContents());
          
            player.getInventory().clear();
            player.getInventory().setArmorContents(null);
            player.setGameMode(GameMode.CREATIVE);

            player.getInventory().setItem(0, new ItemBuilder("§aBuscar Informações", Material.PAPER)
                    .nbt("admin-item", "search-info")
                    .toStack()
            );



            player.getInventory().setItem(2, new ItemBuilder("§eKnockback", Material.STICK)
                    .addEnchant(Enchantment.KNOCKBACK, 10)
                    .toStack()
            );

            player.getInventory().setItem(4, new ItemBuilder("§cSair do Admin", Material.REDSTONE)
                    .nbt("admin-item", "leave-admin")
                    .toStack()
            );

            player.getInventory().setItem(6, new ItemBuilder("§bTroca Rápida", Material.SLIME_BALL)
                    .nbt("admin-item", "fast-trade")
                    .toStack()
            );

            player.getInventory().setItem(8, new ItemBuilder("§3Testar NoFall", Material.FEATHER)
                    .nbt("admin-item", "test-nofall")
                    .toStack()
            );

            player.sendMessage("§bVocê entrou no modo admin");

            Bukkit.getOnlinePlayers().stream().filter(
                    online -> !online.hasPermission("wave.cmd.admin")
            ).forEach(online -> online.hidePlayer(player));
        }else {
            AdminUtil.remove(player.getName());
            player.setGameMode(GameMode.ADVENTURE);
            player.getInventory().clear();
          
            if (itemContents.containsKey(player.getName())) {
                player.getInventory().setContents(itemContents.get(player.getName()));
            }
            if (armorContents.containsKey(player.getName())) {
                player.getInventory().setArmorContents(armorContents.get(player.getName()));
            }
            player.updateInventory();

            player.sendMessage("§cVocê saiu do modo admin");
            Bukkit.getOnlinePlayers().forEach(online -> online.showPlayer(player));
        }
        return true;
    }
}