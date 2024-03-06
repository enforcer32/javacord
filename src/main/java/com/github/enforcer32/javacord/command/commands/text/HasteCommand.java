package com.github.enforcer32.javacord.command.commands.text;

import com.github.enforcer32.javacord.command.Command;
import com.github.enforcer32.javacord.command.CommandContext;
import com.github.enforcer32.javacord.command.CommandType;
import com.github.enforcer32.javacord.command.ICommand;
import com.github.enforcer32.javacord.core.Logger;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.utils.MarkdownSanitizer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Command(type = CommandType.TEXT)
public class HasteCommand implements ICommand {
	@Override
	public void execute(CommandContext ctx) {
		List<String> args = ctx.getArgs();
		TextChannel channel = ctx.getChannel();
		Message textMessage = ctx.getMessage();
		String mdSanitizedText = null;

		if(args.isEmpty()) {
			channel.sendMessage(getHelp()).queue();
			return;
		}

		// Handle Content Raw
		String rawText = textMessage.getContentRaw().replaceAll("(.*)\\bhaste\\b[\\n\\r\\s]+", "");
		String formattedText = MarkdownSanitizer.sanitize(rawText);

		try {
			String pasteLink = createPaste(formattedText);
			channel.sendMessage(pasteLink).queue();
		} catch (IOException e) {
			Logger.critical(e.getMessage());
		}
	}

	@Override
	public String getCommand() {
		return "haste";
	}

	@Override
	public String getHelp() {
		return "haste [text]";
	}

	private String createPaste(String text) throws IOException {
		String response = null;
		String requestURL = "https://hastebin.com/documents";
		byte[] data = text.getBytes(StandardCharsets.UTF_8);
		int dataLen = data.length;
		DataOutputStream os;

		URL url = new URL(requestURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setInstanceFollowRedirects(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Length", Integer.toString(dataLen));
		conn.setUseCaches(false);

		try {
			os = new DataOutputStream(conn.getOutputStream());
			os.write(data);
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			response = br.readLine();
		} catch (IOException e) {
			Logger.critical(e.getMessage());
		}

		if(response.contains("\"key\"")) {
			response = response.substring(response.indexOf(":") + 2, response.length() - 2);
			response = requestURL + "/" + response;
		}

		return response.replace("/documents", "");
	}
}
