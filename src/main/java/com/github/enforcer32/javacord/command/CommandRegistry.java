package com.github.enforcer32.javacord.command;

import com.github.enforcer32.javacord.core.Logger;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandRegistry {
	private final List<ICommand> commands;
	private final Map<CommandType, List<ICommand>> commandTypes;

	public CommandRegistry() {
		commands = new ArrayList<>();
		commandTypes = new HashMap<>();

		try {
			var classes = new Reflections("com.github.enforcer32.javacord.command.commands").getTypesAnnotatedWith(Command.class);
			for (var Class : classes) {
				ICommand instance = (ICommand) Class.getDeclaredConstructor().newInstance();
				CommandType type = instance.getClass().getAnnotation(Command.class).type();

				if(commandTypes.containsKey(type))
					commandTypes.get(type).add(instance);
				else
					commandTypes.put(type, new ArrayList<>(){{ add(instance); }});
				addCommand(instance);
			}
		} catch (Exception e) {
			Logger.critical(e.getMessage());
		}
	}

	public void addCommand(ICommand command) {
		if(!hasCommand(command))
			commands.add(command);
	}

	public void removeCommand(ICommand command) {
		if(hasCommand(command))
			commands.remove(command);
	}

	public boolean hasCommand(ICommand command) {
		return getCommand(command.getCommand()) != null;
	}

	public ICommand getCommand(String command) {
		for(ICommand cmd: commands)
			if(cmd.getCommand().equals(command))
				return cmd;
		return null;
	}

	public List<ICommand> getCommands() {
		return commands;
	}

	public Map<CommandType, List<ICommand>> getCommandTypes() {
		return commandTypes;
	}
}
