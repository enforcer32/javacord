package com.github.enforcer32.javacord.command;

import com.github.enforcer32.javacord.core.Config;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CommandManager {
	private final CommandRegistry registry;

	public CommandManager() {
		this.registry = new CommandRegistry();
	}

	public void handle(MessageReceivedEvent event) {
		if(event.isFromGuild()) {
			String[] raw = event.getMessage().getContentRaw().replaceFirst("(?i)" + Pattern.quote(Config.get("prefix")), "").split("\\s+");
			String command = raw[0];
			ICommand cmd = registry.getCommand(command);

			if(cmd != null) {
//            event.getChannel().sendTyping().queue();
				List<String> args = Arrays.asList(raw).subList(1, raw.length);
				cmd.execute(new CommandContext(registry, args, event));
			}
		}
	}
}
