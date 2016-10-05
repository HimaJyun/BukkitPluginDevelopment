package jp.jyn.part3;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// 引数を取得
		String subCommand = args.length == 0 ? "" : args[0];

		if (command.getName().equalsIgnoreCase("sample")) { // sampleコマンドの場合

			if (subCommand.equalsIgnoreCase("a")) { // /sample aの場合
				sender.sendMessage("/sample aコマンドを実行しました！！");
			} else if (subCommand.equalsIgnoreCase("b")) { // /sample bの場合
				sender.sendMessage("/sample bコマンドを実行しました");
			} else { // それ以外の時は使い方を表示
				sender.sendMessage(new String[] {
						"/sample a - aCommand",
						"/sample b - bCommand"
				});
			}

		} else if (command.getName().equalsIgnoreCase("example")) { // exampleコマンドの場合

			if (subCommand.equalsIgnoreCase("a")) { // /example aの場合
				sender.sendMessage("/example aコマンドを実行しました！！");
			} else if (subCommand.equalsIgnoreCase("b")) { // /example bの場合
				sender.sendMessage("/example bコマンドを実行しました");
			} else { // それ以外の時は使い方を表示
				sender.sendMessage(new String[] {
						"/example a - aCommand",
						"/example b - bCommand"
				});
			}

		}
		return true;
	}
}
