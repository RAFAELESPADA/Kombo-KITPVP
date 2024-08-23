package net.wavemc.pvp.command;


import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("wave.cmd.gamemode")) {
            player.sendMessage("§cVocê não tem permissão.");
            return true;
        }

        if (args.length == 0) {
            player.sendMessage("§3/" + label + " <modo de jogo>");
            return true;
        }

        GameMode gameMode = null;
        switch (args[0].toLowerCase()) {
            case "0":
            case "survival":
            case "sobrevivente":
            case "s":
                gameMode = GameMode.SURVIVAL;
                break;
            case "1":
            case "creative":
            case "criativo":
            case "c":
                gameMode = GameMode.CREATIVE;
                break;
            case "2":
            case "adventure":
            case "aventura":
            case "a":
                gameMode = GameMode.ADVENTURE;
                break;
            case "3":
            case "spectator":
            case "espectador":
            case "spec":
                gameMode = GameMode.SPECTATOR;
                break;
        }

        if (gameMode == null) {
            player.sendMessage("§cModo de jogo não encontrado");
            return true;
        }

        player.setGameMode(gameMode);
        player.sendMessage("§bGameMode alterado");
        return true;
    }
}