package jp.jyn.part5;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		// hogeコマンドの処理をCommandクラスに任せる
		getCommand("hoge").setExecutor(new Command());
	}

	@Override
	public void onDisable() {
		// 登録解除したい時は(多分)こうすればいい
		getCommand("hoge").setExecutor(this);
	}
}
