package com.github.enforcer32.javacord.core;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class javacord {
	public static void run(String token) throws LoginException {
		JDABuilder.createLight(token, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT)
				.setActivity(Activity.listening("Java..."))
				.addEventListeners(new Listener())
				.build();
	}

	public static void main(String[] args) {
		try {
			String token = Config.get("token");
			run(token);
		} catch (Exception e) {
			Logger.critical(e.getMessage());
		}
	}
}
