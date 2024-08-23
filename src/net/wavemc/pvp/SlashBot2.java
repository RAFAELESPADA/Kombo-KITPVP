package net.wavemc.pvp;

import java.util.Calendar;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.audit.ActionType;
import net.dv8tion.jda.api.audit.AuditLogEntry;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SlashBot2 extends ListenerAdapter {


	public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
	  event.getGuild()
	       .retrieveAuditLogs()
	       .queueAfter(1, TimeUnit.SECONDS, (logs2) -> { // Gotta wait a second for discord to populate the logs properly
	         boolean isBan = false, isKick = false;
	         for (AuditLogEntry log : logs2) {
	             if (log.getTargetIdLong() == event.getUser().getIdLong()) {
	                 isBan = log.getType() == ActionType.BAN;
	                 isKick = log.getType() == ActionType.KICK;
	                 if (isBan) {
	        	         Guild guild = event.getGuild();
	        	         event.getGuild().getMemberById("1096497288379125832").getUser().openPrivateChannel().flatMap(channel -> channel.sendMessage(event.getMember() + " Foi banido do grupo da Kombo!")).queue();
	        	         event.getGuild().getTextChannelById("1262647737015603230").sendMessage("**ARRIVERDECI, NOVO BANIMENTO NO DISCORD**").queue();;
	        			  EmbedBuilder embed = new EmbedBuilder();
	        			  embed.setTitle(guild.getName());
	        			  embed.setThumbnail(guild.getIconUrl());
	        			  embed.addField("Uma pessoa foi banida do grupo!", "Data "+ Calendar.getInstance(TimeZone.getTimeZone("UTC-3")).getTime(), true);
	        			  embed.addField("Discord do usuário: ", Objects.requireNonNull(event.getMember().getUser().toString()), true);
	        			  embed.addField(":x: ", " Por isso você não deve quebrar as regras", true);
	        			  event.getGuild().getTextChannelById("1262647737015603230").sendMessageEmbeds(embed.build()).queue();
	        	         }
	                 else if (isKick) {
	        	         Guild guild = event.getGuild();
	        	         event.getGuild().getMemberById("1096497288379125832").getUser().openPrivateChannel().flatMap(channel -> channel.sendMessage(event.getMember() + " Foi expulso do grupo da Kombo!")).queue();
	        	         event.getGuild().getTextChannelById("1262647737015603230").sendMessage("**ARRIVERDECI, NOVA EXPULSÃO NO DISCORD**").queue();;
	        			  EmbedBuilder embed = new EmbedBuilder();
	        			  embed.setTitle(guild.getName());
	        			  embed.setThumbnail(guild.getIconUrl());
	        			  embed.addField("Uma pessoa foi expulsa do grupo!", "Data "+ Calendar.getInstance(TimeZone.getTimeZone("UTC-3")).getTime(), true);
	        			  embed.addField("Discord do usuário: ", Objects.requireNonNull(event.getMember().getUser().toString()), true);
	        			  embed.addField(":x: ", " Por isso você não deve quebrar as regras", true);
	        			  event.getGuild().getTextChannelById("1262647737015603230").sendMessageEmbeds(embed.build()).queue();
	        	         }
	             

	                 break;
	             }
	         }
	       });
	         	}

public void sendMessage(User user, String content) {
    user.openPrivateChannel()
        .flatMap(channel -> channel.sendMessage(content))
        .queue();
}
}