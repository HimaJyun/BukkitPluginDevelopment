package jp.jyn.part5.subcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * 「/hoge fuga」コマンドを処理するクラス
 * @author HimaJyun
 *
 */
public class Fuga implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		sender.sendMessage("あなたは「/hoge fuga」コマンドを実行しました！！");
		return true;
	}

}
