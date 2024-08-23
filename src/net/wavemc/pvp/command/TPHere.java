package net.wavemc.pvp.command;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPHere implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("wave.cmd.tphere")) {
            player.sendMessage("§cVocê não tem permissão.");
            return true;
        }

        if (args.length == 0)  {
            player.sendMessage("§b/" + label + " <jogador>");
            return true;
        }

        Player target;
        if ((target = Bukkit.getPlayer(args[0])) == null) {
            player.sendMessage("§cJogador offline");
            return true;
        }

        if (target.getName().equalsIgnoreCase(player.getName())) {
            player.sendMessage("§bVocê não pode puxar sí mesmo");
            return true;
        }

        target.teleport(player);
        player.sendMessage("§aVocê puxou " + target.getName());
        return true;
    }
}