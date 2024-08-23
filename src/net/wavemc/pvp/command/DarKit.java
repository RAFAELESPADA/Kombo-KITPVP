package net.wavemc.pvp.command;


import net.wavemc.pvp.kit.WaveKit;


import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DarKit implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("givekit")) {
            if (!sender.hasPermission("wave.cmd.givekit")) {
                sender.sendMessage("§cVocê não tem permiss§o");
                return true;
            }
            if (args.length < 2) {
                sender.sendMessage("§cUtilize /givekit <player> <kit>");

                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage("§cNão foi possível encontrar o player §e" + args[0] + "§c.");
                return true;
            }
            WaveKit kit = WaveKit.getKitTypeByName(args[1]);
            if (kit == null) {
                sender.sendMessage("§cNão foi possível encontrar o kit \"§e" + args[1] + "§c\".");
                return true;
            }
            if (target.hasPermission("wave.kit2." + kit)) {
                sender.sendMessage("§cEsse player ja possui esse kit.");
                return true;
            }
            sender.sendMessage("§aO player §e" + target.getName() + " §crecebeu o kit §e" + kit + "§a.");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + target.getName() + " permission set wave.kit2." + kit.toString());
            target.sendTitle("§a§lPARABÉNS", "§aVocê recebeu o kit §e" + kit + "§a!");
            Bukkit.broadcast("§4§lKIT §a" + sender.getName() + " §fDeu o kit §e" + kit + "§f para §a" + target.getName(), "kombo.cmd.report");
       
        }
        return false;
    }
  
}