package net.wavemc.pvp.command;

import lombok.AllArgsConstructor;
import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.warp.WaveWarp;
import net.wavemc.pvp.WavePvP;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@AllArgsConstructor
public class SetWarp implements CommandExecutor {

    private final WavePvP plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        if (!sender.hasPermission("wave.cmd.setwarp")) {
            sender.sendMessage("§cVocê não tem permissão.");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("§b/" + label + " <warp>");
            return true;
        }

        String warpName = args[0];
        WaveWarp warp = WaveBukkit.getInstance().getWarpManager().findWarp(warpName).isPresent() ?
        		WaveBukkit.getInstance().getWarpManager().findWarp(warpName).get() :
                	WaveBukkit.getInstance().getWarpManager().createWarp(warpName);
        warp.setLocation(((Player)sender).getLocation());
        warp.setWorldpenis(((Player)sender).getWorld());
        WaveBukkit.getInstance().getWarpManager().getData().save(warp);
        sender.sendMessage("§6Warp setada");
        return true;
    }
}
