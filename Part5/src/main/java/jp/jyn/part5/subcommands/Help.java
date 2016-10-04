package jp.jyn.part5.subcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * ヘルプコマンド
 * @author HimaJyun
 *
 */
public class Help implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		sender.sendMessage(new String[] {
				"このプラグインでは以下のコマンドが使えます！！",
				"/hoge fuga - fugaコマンド",
				"/hoge piyo - piyoコマンド",
				"/hoge help - このヘルプを表示します"
		});

		return true;
	}
}
