package net.wavemc.pvp.command;



import java.text.DecimalFormat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;
import net.wavemc.core.bukkit.account.provider.PlayerPvP;
import net.wavemc.pvp.WavePvP;
import net.wavemc.pvp.listener.Ranking;

public class RankCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            WavePlayer wavePlayer = WaveBukkit.getInstance().getPlayerManager()
    				.getPlayer(player.getName());
    		PlayerPvP pvp = wavePlayer.getPvp();
    		
            if (wavePlayer == null) {
            	return true;
            }
            player.sendMessage(WavePvP.getInstance().getConfig().getString("Prefix").replace("&", "§") + " §7- §eSistema de ranks");
            Ranking[] values;
            for (int length = (values = Ranking.values()).length, i = 0; i < length; ++i) {
                Ranking rank = values[i];
                if (Ranking.getRank(wavePlayer).getName().equals(rank.getName())) {
                    player.sendMessage("§7(" + rank.getColoredSymbol() + "§7) " + rank.getColoredName() + " §a" + new DecimalFormat().format(rank.getXp()) + " XP   §e< Seu rank");
                } else {
                    player.sendMessage("§7(" + rank.getColoredSymbol() + "§7) " + rank.getColoredName() + " §a" + new DecimalFormat().format(rank.getXp()) + " XP");
                }
            }
            player.sendMessage("§7Seu rank atual é: " + Ranking.getRank(wavePlayer).getColoredName() + "§7.");
            if (Ranking.getRank(wavePlayer) != Ranking.GOD) {
                player.sendMessage("§7Seu próximo rank é: " + Ranking.getRank(wavePlayer).next().getColoredName() + "§7.");
                int pontos_necessarios = Ranking.getRank(wavePlayer).next().getXp() - pvp.getXp();
                player.sendMessage("§7Você tem §a" + pvp.getXp() + " XP §7e precisa de mais §a" + pontos_necessarios + " XP §7para upar para o próximo §6rank§7.");
                player.sendMessage(" ");
                player.sendMessage("§7Progresso para o próximo §6rank§7:");
                int diff = Ranking.getRank(wavePlayer).next().getXp() - Ranking.getRank(wavePlayer).getXp();
                getProgressBar(pontos_necessarios, diff, player);
            }

        } else {
            sender.sendMessage("APENAS PLAYERS");
        }
        return true;
    }

    private static void getProgressBar(int atual, int total, Player player) {
        int barSize = 10;
        int realPorcent = 100 - (int) (((double) atual / (double) total) * 100D);
        int barPorcent = realPorcent / 10;
        StringBuilder bar = new StringBuilder();
        for (int i = 0; i < barSize; i++) {
            if (i < barPorcent) {
                bar.append("§a§m-§r");
            } else if (i == barPorcent) {
                bar.append("§a§m>§r");
            } else {
                bar.append("§7§m-§r");
            }
        }
        player.sendMessage(bar + "§r §7(" + realPorcent + "% concluído)");
    }

}
