package jp.jyn.part5;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import jp.jyn.part5.subcommands.Fuga;
import jp.jyn.part5.subcommands.Help;
import jp.jyn.part5.subcommands.Piyo;

/**
 * コマンドのメインクラス(/hogeコマンドを分岐する)
 * @author HimaJyun
 *
 */
public class Command implements CommandExecutor {

	// サブコマンドが詰まってる
	private Map<String, CommandExecutor> sub = new HashMap<>();

	// 引数がない場合の処理(空白を含むなどして引数としてありえない形にしておきましょう(衝突防止))
	private final String NO_ARGS = "NO ARGS";
	// もしくは、helpとして扱っても良いかも知れません。(と言うか普通はそれで良い)
	//private final String NO_ARGS = "help";

	/**
	 * コンストラクタ(ここでサブコマンドを登録)
	 */
	public Command() {
		sub.put("fuga", new Fuga()); // /hoge fugaコマンドはFugaクラスに任せる
		sub.put("piyo", new Piyo()); // /hoge piyoコマンドはPiyoクラスに任せる

		// サブコマンドがなかった場合はHelpを表示、とかにしておくと良い
		sub.put(NO_ARGS, new Help());
	}

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
		if (0 < args.length) { // 引数がある
			// 1つめの引数(args[0])を小文字に変換する(/hoge fugaでも/hoge FUGAでも実行出来る様に)
			String subCommand = args[0].toLowerCase(Locale.ENGLISH);
			// そのサブコマンドに対応したExecutorを取得する
			CommandExecutor executor = sub.get(subCommand);

			// 非null == サブコマンドが登録されていたなら実行する
			// containsKeyでチェックする必要はない(HashMapのcontainsKey自体がgetして非nullならtrue、みたいな実装になっている)
			if (command != null) {
				// 実行して終了する
				return executor.onCommand(sender, command, label, args);
			}
		}

		// ここまで来た==サブコマンドがなかったか、登録されていなかった時は
		// 引数がない時の処理(helpを表示)を実行する
		return sub.get(NO_ARGS).onCommand(sender, command, label, args);
	}

}
