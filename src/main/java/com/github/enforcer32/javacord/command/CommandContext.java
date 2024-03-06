package com.github.enforcer32.javacord.command;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.sharding.ShardManager;

import java.util.List;

public class CommandContext {
	private final CommandRegistry registry;
	private final List<String> args;
	private final MessageReceivedEvent event;

	public CommandContext(CommandRegistry registry, List<String> args, MessageReceivedEvent event) {
		this.registry = registry;
		this.args = args;
		this.event = event;
	}

	public CommandRegistry getRegistry() {
		return registry;
	}

	public List<String> getArgs() {
		return this.args;
	}

	public JDA getJDA() {
		return this.event.getJDA();
	}

	public MessageReceivedEvent getEvent() {
		return this.event;
	}

	public Guild getGuild() {
		return this.event.getGuild();
	}

	public User getAuthor() {
		return this.event.getAuthor();
	}

	public Member getAuthorMember() {
		return this.event.getMember();
	}

	public TextChannel getChannel() {
		return this.event.getChannel().asTextChannel();
	}

	public Message getMessage() {
		return this.event.getMessage();
	}

	public ShardManager getShardManager() {
		return this.getJDA().getShardManager();
	}

	public User getSelfUser() {
		return this.getJDA().getSelfUser();
	}

	public Member getSelfMember() {
		return this.getGuild().getSelfMember();
	}
}
