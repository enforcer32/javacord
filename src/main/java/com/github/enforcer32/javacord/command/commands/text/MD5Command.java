package com.github.enforcer32.javacord.command.commands.text;

import com.github.enforcer32.javacord.command.Command;
import com.github.enforcer32.javacord.command.CommandContext;
import com.github.enforcer32.javacord.command.CommandType;
import com.github.enforcer32.javacord.command.ICommand;
import com.github.enforcer32.javacord.core.Config;
import com.github.enforcer32.javacord.core.Logger;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Command(type = CommandType.TEXT)
public class MD5Command implements ICommand {
	@Override
	public void execute(CommandContext ctx) {
		TextChannel channel = ctx.getChannel();
		Message message = ctx.getMessage();

		if(ctx.getArgs().isEmpty()) {
			channel.sendMessage(getHelp()).queue();
			return;
		}

		String text = message.getContentRaw().replace(Config.get("PREFIX") + getCommand() + " ", "");
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(text.getBytes(StandardCharsets.UTF_8));
			channel.sendMessage(String.format("%032x", new BigInteger(1, md5.digest()))).queue();
		} catch (NoSuchAlgorithmException e) {
			Logger.critical(e.getMessage());
		}
	}

	@Override
	public String getCommand() {
		return "md5";
	}

	@Override
	public String getHelp() {
		return "md5 [text]";
	}
}
