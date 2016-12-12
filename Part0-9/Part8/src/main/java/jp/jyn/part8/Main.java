package jp.jyn.part8;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	/**
	 * config.yml
	 */
	CustomConfig config;
	/**
	 * message.yml
	 */
	CustomConfig message;

	@Override
	public void onEnable() {
		config = new CustomConfig(this);
		message = new CustomConfig(this, "message.yml");

		// TODO:ここで設定ファイルを利用してゴニョゴニョする。
	}
}
