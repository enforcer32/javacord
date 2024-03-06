package com.github.enforcer32.javacord.command.commands.utility;

import com.github.enforcer32.javacord.command.*;
import com.github.enforcer32.javacord.core.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.awt.*;
import java.util.List;
import java.util.Map;

@Command(type = CommandType.UTILITY)
public class HelpCommand implements ICommand {
	private String botInviteUrl;
	private CommandContext ctx;

	@Override
	public void execute(CommandContext ctx) {
		this.ctx = ctx;
		List<String> args = ctx.getArgs();
		TextChannel channel = ctx.getChannel();
		botInviteUrl = ctx.getJDA().getInviteUrl(Permission.ADMINISTRATOR);

		if(args.size() > 0) {
			ICommand cmd = ctx.getRegistry().getCommand(args.get(0));
			if(cmd != null) {
				channel.sendMessage(cmd.getHelp()).queue();
				return;
			}
		}

		channel.sendMessageEmbeds(getHelpEmbed().build()).queue();
	}

	@Override
	public String getCommand() {
		return "help";
	}

	@Override
	public String getHelp() {
		return "help menu\n" + "help [COMMAND]";
	}

	private EmbedBuilder getHelpEmbed() {
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("Help Command");
		embed.setColor(Color.CYAN);
		embed.setDescription("Prefix: " + Config.get("prefix") + "\nVersion: 0.0.1");

		embed.addField("\u276F Home Server", "[javacord](#link)", true);
		embed.addField("\u276F Invite Bot", "[javacord](" + botInviteUrl + ")", true);
		embed.addField("\u276F Author", "[Enforcer](https://github.com/enforcer32)", true);
		embed.getFields().addAll(getCommandsEmbed().getFields());

		embed.setFooter("\u276F \u00A9 2023 enforcer32");
		return embed;
	}

	private EmbedBuilder getCommandsEmbed() {
		EmbedBuilder embed = new EmbedBuilder();
		Map<CommandType, List<ICommand>> commands = ctx.getRegistry().getCommandTypes();
		StringBuilder fields = new StringBuilder();

		for(var entry: commands.entrySet()) {
			String section = entry.getKey().toString();
			for(var value: entry.getValue()) {
				var command = value.getCommand();
				fields.append("`" + command + "` ");
			}
			embed.addField("\u276F " + section, fields.toString(), true);
			fields.setLength(0);
		}

		return embed;
	}
}
