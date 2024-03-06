package com.github.enforcer32.javacord.command.commands.superuser;

import com.github.enforcer32.javacord.command.Command;
import com.github.enforcer32.javacord.command.CommandContext;
import com.github.enforcer32.javacord.command.CommandType;
import com.github.enforcer32.javacord.command.ICommand;
import com.github.enforcer32.javacord.core.Logger;
import com.github.enforcer32.javacord.core.Util;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.net.InetAddress;
import java.util.List;

@Command(type = CommandType.SUPERUSER)
public class ServerCommand implements ICommand {
	@Override
	public void execute(CommandContext ctx) {
		List<String> args = ctx.getArgs();
		TextChannel channel = ctx.getChannel();
		User user = ctx.getAuthor();

		if(args.size() < 1) {
			channel.sendMessage(getHelp()).queue();
			return;
		}

		if(Util.isSuperuser(user)) {
			if(args.get(0).equals("ip"))
				channel.sendMessage(getIP()).queue();
			else
				channel.sendMessage(getHelp()).queue();
		} else {
			channel.sendMessage("Not a Superuser...").queue();
		}
	}

	@Override
	public String getCommand() {
		return "server";
	}

	@Override
	public String getHelp() {
		return "Server Utility Commands\nSuperuser Command!\nserver [ip]";
	}

	private String getIP() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e) {
			Logger.critical(e.getMessage());
		}
		return "null";
	}
}
