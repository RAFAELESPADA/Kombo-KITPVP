package net.wavemc.pvp.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.wavemc.pvp.WavePvP;


public class SetFeast implements CommandExecutor {
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("No console");
            return true;
        }
        final Player p = (Player)sender;
        if (label.equalsIgnoreCase("setfeast")) {
        	if (!p.hasPermission("kitpvp.setarena")){
        	  	  sender.sendMessage("§c§lERROR: §fYou dont have permission to do that");
        	  	  return true;
        	    }
                if (args.length == 0) {
                    p.sendMessage(String.valueOf("KITPVP") + "Write: /setfeast (1|30)");
                    return true;
                }
                if (isNumeric(args[0])) {
                    p.sendMessage(String.valueOf("KITPVP") + "§aYou seted the FEAST " + args[0]);
                    WavePvP.getInstance().getConfig().set("Bau" + args[0] + "X", (Object)p.getLocation().getX());
                    WavePvP.getInstance().getConfig().set("Bau" + args[0] + "Y", (Object)p.getLocation().getY());
                    WavePvP.getInstance().getConfig().set("Bau" + args[0] + "Z", (Object)p.getLocation().getZ());
                    WavePvP.getInstance().saveConfig();
                } else {
                 p.sendMessage(ChatColor.RED + "Use apenas números");   	
                    }
                }

		return false;
    }

    	public static boolean isNumeric(String str) {
    		try {
    			Integer.parseInt(str);
    		} catch (NumberFormatException nfe) {
    			return false;
    		}
    		return true;
    	}
}