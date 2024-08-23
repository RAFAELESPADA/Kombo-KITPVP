package net.wavemc.pvp.command;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("wave.cmd.clear")) {
            player.sendMessage("§cVocê não tem permissão.");
            return true;
        }

        Player target;
        if ((target = Bukkit.getPlayer(args.length > 0 ? args[0] : sender.getName())) == null) {
            player.sendMessage("§cJogador offline");
            return true;
        }

        target.getInventory().clear();
        target.getInventory().setArmorContents(null);

        player.sendMessage(target.getName().equals(sender.getName()) ?
                "§7Você limpou seu inventário." : "§bVocê limpou o inventário do jogador §f" + target.getName() + "§b.");
        return true;
    }
}