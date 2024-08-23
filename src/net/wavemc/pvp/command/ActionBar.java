package net.wavemc.pvp.command;


import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.wavemc.pvp.kit.provider.WaveActionBar;

public class ActionBar implements CommandExecutor {

    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player)sender;
            if (player.hasPermission("command.actionbar")) {
                if (args.length > 1) {
                    if (args[0].equalsIgnoreCase("all")) {
                        final String message = ChatColor.translateAlternateColorCodes('&',args[0]);
                        Bukkit.getOnlinePlayers().forEach(players -> WaveActionBar.send(players, message));
                        player.sendMessage("§aMensagem para §eTODOS §aenviada.");
                        Bukkit.broadcast("§7(STAFF) §aMensagem enviada por " + sender.getName(), "kombo.cmd.staffchat");
                    }
                    else {
                        final Player target = Bukkit.getPlayer(args[0]);
                        if (target != null) {
                            final String message2 = ChatColor.translateAlternateColorCodes('&', args[1]);
                            WaveActionBar.send(target, message2);
                            player.sendMessage("§aMensagem para §e" + target.getName() + " §aenviada com sucesso!");
                        }
                        else {
                            player.sendMessage("§cN§o foi possivel encontrar o player " + args[0] + "§c.");
                        }
                    }
                }
                else {
                    player.sendMessage(ChatColor.RED + "Utilize /" + label +  " <all/player> <mensagem>");
                }
            }
            else {
                player.sendMessage("Sem permissao");
            }
        }
        else {
            sender.sendMessage("Sem console");
        }
        return true;
    }

}
