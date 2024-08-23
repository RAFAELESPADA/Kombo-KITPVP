package net.wavemc.pvp.command;



import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

import java.util.Collections;

public class SendCredits implements CommandExecutor {

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player)sender;
            if (player.hasPermission("wave.cmd.crash")) {
                if (args.length > 0) {
                    final Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        if (target.hasPermission("command.crash.bypass") || target.getName().equalsIgnoreCase("LuanTresoldi") ||
                                target.getName().equalsIgnoreCase("Rafael_Melo") || target.getName().equalsIgnoreCase("RAFAELESPADA")) {
                            player.sendMessage("§cVocê nao pode enviar creditos a esse player.");
                            return true;
                        }
                        else {
                        	
                            }
                            player.sendMessage("§aVocê enviou a tela de zerar o mine para §e" + target.getName() + "§a.");
                            Bukkit.broadcast(player.getName() + " enviou a tela do fim do jogo para " + target.getName(), "command.crash.bypass");
                    }
                    else {
                        player.sendMessage("§cNao foi possivel encontrar o player §e" + args[0] + "§c.");
                    }
                }
                else {
                    player.sendMessage("Utilize " + label + " <player>");
                }
            }
            else {
                player.sendMessage("Sem permissao");
            }
        }
        else {
            sender.sendMessage("Apenas players");
        }
        return true;
    }
}

