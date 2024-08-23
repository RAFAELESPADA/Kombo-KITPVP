package net.wavemc.pvp;


import java.util.Collection;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import net.luckperms.api.model.user.UserManager;
import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;

public class SlashBott extends ListenerAdapter {

public void onSlashCommandInteraction(SlashCommandInteractionEvent arg0)
{
    // Only accept commands from guilds
    if (arg0.getGuild() == null)
        return;
    switch (arg0.getName())
    {
    case "serverinfo":
try {
	Guild guild = arg0.getGuild();
	arg0.getChannel().sendMessage("**INFORMAÇÕES DO DISCORD DA KOMBO**").queue();;
			  EmbedBuilder embed = new EmbedBuilder();
			  embed.setTitle(guild.getName());
			  embed.setThumbnail(guild.getIconUrl());
			  embed.addField(":crown: Dono do grupo: ", "rafael_auler2", true);
			  embed.addField(":date: Data de criação: ", Objects.requireNonNull(arg0.getGuild().getTimeCreated().toString()), true);
			  embed.addField(":sound: Quantia de Canais: ", Objects.requireNonNull(String.valueOf(arg0.getGuild().getChannels().size())), true);
		   embed.addField(":diamonds: Recursos: ", Objects.requireNonNull(arg0.getGuild().getFeatures().toString()), true);
		   embed.addField(":partying_face: Quantia de Impulsos: ", Objects.requireNonNull(String.valueOf(arg0.getGuild().getBoostCount())), true);
		   embed.addField(":x: Nível de verificação: ", Objects.requireNonNull(String.valueOf(arg0.getGuild().getVerificationLevel())), true);
		   embed.addField(":person_in_tuxedo: Membros: ", Objects.requireNonNull(String.valueOf(arg0.getGuild().getMemberCount())), true);
		   embed.addField(":closed_lock_with_key: Quantia de Cargos: ", Objects.requireNonNull(String.valueOf(arg0.getGuild().getRoles().size())), true);
		   embed.addField(":id: ID: ", Objects.requireNonNull(arg0.getGuild().getId()), true);	
		   arg0.getChannel().sendMessageEmbeds(embed.build()).queue();

}

catch (Exception e) {
e.printStackTrace();
}

        break;		    
    case "status":
   	 try {
        aa(arg0, arg0.getOption("nick").getAsString()); // content is required so no null-check here
        break;
   	 }
   	 catch (Exception e) {
   	 	e.printStackTrace();
   	 }
    case "ping":
      	 try {
      		arg0.reply("Pong! :)");
      		 break;
      	 }
      	 catch (Exception e) {
      	 	e.printStackTrace();
      	 }

    case "anunciar":
    	try {

            ab(arg0, arg0.getOption("mensagem").getAsString() , arg0.getOption("canal").getAsChannel()); 
            
            break;
       	 }
    	catch (Exception e) {
       	 	e.printStackTrace();
       	 }
    
    default:
        arg0.reply("I can't handle that command right now :(").setEphemeral(true).queue();
    }
}

protected boolean ab(@NotNull SlashCommandInteractionEvent arg0 , String mensagem, Channel channel) {
	   
	   try {
		   if (!arg0.getMember().hasPermission(Permission.ADMINISTRATOR)) {
			   arg0.reply("Você não tem permissão");
			   return true;
		   }
       arg0.getGuild().getTextChannelById(channel.getId()).sendMessage("NOVO ANÚNCIO PÚBLICADO").queue();;
       EmbedBuilder embed = new EmbedBuilder();

		Guild guild = arg0.getGuild();
		  embed.setTitle(guild.getName());
		  embed.addField(":white_check_mark: ", arg0.getOption("mensagem").getAsString(), true);
		  embed.setThumbnail(guild.getIconUrl());
		  arg0.getGuild().getTextChannelById(channel.getId()).sendMessageEmbeds(embed.build()).queue();
		  arg0.getGuild().getTextChannelById(channel.getId()).sendMessage(arg0.getGuild().getPublicRole().getAsMention()).queue();;
	          
	   return true; 
	   }
	   catch (Exception e) {
		   arg0.reply("Um erro ocorreu").setEphemeral(true).queue();
		   }
	return false;
}


public User giveMeADamnUser(UUID uniqueId) {
	 RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
		if (provider == null) return null; 
		    LuckPerms api = provider.getProvider();
    UserManager userManager = api.getUserManager();
    CompletableFuture<User> userFuture = userManager.loadUser(uniqueId);

    return userFuture.join(); 
		}







	protected boolean aa(@NotNull SlashCommandInteractionEvent arg0 , String nick) {
	   WavePlayer p = WaveBukkit.getInstance().getPlayerManager().getPlayer(nick);
       RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
		if (provider != null) {
OfflinePlayer real = Bukkit.getOfflinePlayer(nick);
		    LuckPerms api = provider.getProvider();
			
	   try {

	   arg0.reply("**INFORMAÇÕES DE STATUS DO KITPVP DO JOGADOR **" + p.getName() + "\nKills: " + p.getPvp().getKills() + "\nMortes: " + p.getPvp().getDeaths() + "\nKillStreak atual: " + p.getPvp().getKillstreak() + "\nKills na FPS: " + p.getPvp().getKillsfps() + "\nVezes passada no lava: " + p.getPvp().getPassouchallenge() + "\nWins no duelos (1v1): " + p.getPvp().getWinsx1() + "\nXP: " + p.getPvp().getXp() + "\nCoins: " + p.getPvp().getCoins() + "\nMortes no 1v1: " + p.getPvp().getDeathsx1() + "\nWinStreak no 1v1: " + p.getPvp().getWinstreakx1() + "\nCargo: " + giveMeADamnUser(real.getUniqueId()).getPrimaryGroup().toUpperCase().toString()).queue();
	   return true; 
	   }
	   catch (Exception e) {
		   arg0.reply("Um erro ocorreu").setEphemeral(true).queue();
		   e.printStackTrace();
		   }
	
}
		return false;
	}
	   }
	