package com.github.enforcer32.javacord.command.commands.utility;

import com.github.enforcer32.javacord.command.Command;
import com.github.enforcer32.javacord.command.CommandContext;
import com.github.enforcer32.javacord.command.CommandType;
import com.github.enforcer32.javacord.command.ICommand;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

@Command(type = CommandType.UTILITY)
public class PingCommand implements ICommand {
	@Override
	public void execute(CommandContext ctx) {
		TextChannel channel = ctx.getChannel();
		channel.sendMessage("PONG! `" + ctx.getJDA().getGatewayPing() + "ms`").queue();
	}

	@Override
	public String getCommand() {
		return "ping";
	}

	@Override
	public String getHelp() {
		return "Get Gateway Ping";
	}
}
