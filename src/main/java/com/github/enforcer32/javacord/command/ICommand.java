package com.github.enforcer32.javacord.command;

public interface ICommand {
	void execute(CommandContext ctx);
	String getCommand();
	String getHelp();
}
