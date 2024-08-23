package net.wavemc.pvp.command;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Invsee implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        if (!sender.hasPermission("wave.cmd.invsee")) {
            sender.sendMessage("§cVocê não tem permissão.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("§3" + label + " <player>");
            return true;
        }

        String targetName = args[0];
        Player targetPlayer;

        if ((targetPlayer = Bukkit.getPlayer(targetName)) == null) {
            sender.sendMessage("§cJogador offline");
            return true;
        }

        ((Player)sender).openInventory(targetPlayer.getInventory());
        sender.sendMessage(targetName.equalsIgnoreCase(sender.getName()) ? "§7Você está visualizando seu inventário" : "§7Você está visualizando o inventário do jogador " + targetName);
        return true;
    }
}