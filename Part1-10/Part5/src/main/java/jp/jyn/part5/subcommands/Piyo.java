package jp.jyn.part5.subcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * 「/hoge piyo」コマンドを処理するクラス
 * @author HimaJyun
 *
 */
public class Piyo implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		sender.sendMessage("あなたは「/hoge piyo」コマンドを実行しました！！");
		return true;
	}

}
