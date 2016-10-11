package jp.jyn.part7;

import java.util.Map.Entry;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	private Config config;

	@Override
	public void onEnable() {
		config = new Config(this);

		i("Message: " + config.getMessage());
		i("Message2.Message3: " + config.getMessage2Message3());
		i("Value1: " + config.getValue1());
		i("Value2: " + config.getValue2());

		for (Entry<String, String> a : config.getMapKeys().entrySet()) {
			i("MapKeys." + a.getKey() + ": " + a.getValue());
		}

		config.setMessage("aaa");

		i("Message: " + config.getMessage());

	}

	/**
	 * 単なるロガーのラッパー
	 * @param msg
	 */
	private void i(String msg) {
		getLogger().info(msg);
	}
}
