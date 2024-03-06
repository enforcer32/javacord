package com.github.enforcer32.javacord.command.commands.utility;

import com.github.enforcer32.javacord.command.Command;
import com.github.enforcer32.javacord.command.CommandContext;
import com.github.enforcer32.javacord.command.CommandType;
import com.github.enforcer32.javacord.command.ICommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.util.List;

@Command(type = CommandType.UTILITY)
public class InviteCommand implements ICommand {
	@Override
	public void execute(CommandContext ctx) {
		List<String> args = ctx.getArgs();
		TextChannel channel = ctx.getChannel();
		JDA jda = ctx.getJDA();

		if(args.size() == 0) {
			channel.sendMessage(getHelp()).queue();
			return;
		}

		if(args.get(0).equals("server")) {
			channel.sendMessage("Server Invite Link: " + channel.createInvite().setMaxAge(0).complete().getUrl()).queue();
		}
		else if(args.get(0).equals("bot")) {
			channel.sendMessage("Bot Invite Link: " + jda.getInviteUrl(Permission.ADMINISTRATOR)).queue();
		}
	}

	@Override
	public String getCommand() {
		return "invite";
	}

	@Override
	public String getHelp() {
		return "invite [server/bot]";
	}
}
