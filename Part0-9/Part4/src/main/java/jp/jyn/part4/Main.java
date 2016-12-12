package jp.jyn.part4;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// 権限チェックは先に済ませてしまうと良いでしょう。
		// (コンソールはOPとして扱われる?ので常に権限を持つ)
		if (!sender.hasPermission("part4.op")) { // 権限を「持たない」を確認する時は「!(not)」を付けるのを忘れずに
			sender.sendMessage("あんたには使えないよ！！");
			return true;
		}

		// これは不可能
		//sender.getLocation();

		// コマンドを使ったのがプレイヤーかどうか確認する
		Player player = null;
		if (sender instanceof Player) { // プレイヤー
			// senderをPlayer型に型変換(ダウンキャスト)する
			player = (Player) sender;
		} else { // コンソール
			// コンソールからは使えないコマンドなのでエラーを出力して終わり
			sender.sendMessage("このコマンドはコンソールからは使えません！！");
			return true;
		}

		// ここで適当に何かする
		player.damage(9999);

		return true;
	}

	/*
	コメントアウトの小技
	この様に、＊/の前に//を付けると
	/＊を//＊にするだけでまとめて有効/無効を切り替え出来る
	//*/
}
