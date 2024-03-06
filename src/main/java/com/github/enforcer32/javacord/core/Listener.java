package com.github.enforcer32.javacord.core;

import com.github.enforcer32.javacord.command.CommandManager;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Listener extends ListenerAdapter {
	private final CommandManager commandManager;

	public Listener() {
		this.commandManager = new CommandManager();
	}

	@Override
	public void onReady(ReadyEvent event) {
		Logger.info("{} is ready", event.getJDA().getSelfUser().getName());
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if(event.isFromGuild()) {
			User user = event.getAuthor();
			if(user.isBot() || event.isWebhookMessage())
				return;

			String raw = event.getMessage().getContentRaw();
			if(raw.startsWith(Config.get("prefix"))) {
				commandManager.handle(event);
			}
		}
	}
}
