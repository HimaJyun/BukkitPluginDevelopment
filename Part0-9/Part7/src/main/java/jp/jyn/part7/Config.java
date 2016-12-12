package jp.jyn.part7;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

/**
 * 設定をまとめて取り扱う構造体
 * @author HimaJyun
 *
 */
public class Config {

	private final Plugin plugin;
	private FileConfiguration config = null;

	private String message;
	private String message2Message3;
	private int value1;
	private double value2;

	private Map<String, String> mapKeys = new HashMap<>();

	public Config(Plugin plugin) {
		this.plugin = plugin;
		// ロードする
		load();
	}

	/**
	 * 設定をロードします
	 */
	public void load() {
		// 設定ファイルを保存
		plugin.saveDefaultConfig();
		if (config != null) { // configが非null == リロードで呼び出された
			plugin.reloadConfig();
		}
		config = plugin.getConfig();

		if (!config.contains("Message")) { // 存在チェック
			plugin.getLogger().info("config.ymlがなんか変です！！");
		} else if (!config.isString("Message")) {
			plugin.getLogger().info("config.ymlのMessageがStringじゃないです！！");
		}

		message = config.getString("Message", "デフォルト値");
		message2Message3 = config.getString("Message2.Message3");

		value1 = config.getInt("Value1");
		value2 = config.getDouble("Value2");

		for (String key : config.getConfigurationSection("MapKeys").getKeys(false)) {
			mapKeys.put(key, config.getString("MapKeys." + key));
		}

		config.options().header("Comment1\nComment2");
	}

	public String getMessage() {
		return message;
	}

	public boolean setMessage(String message) {
		// 例えばnullチェックをしたり
		if (message == null) {
			return false;
		}

		// 値をセットした後に自動で保存したり
		this.message = message;
		config.set("Message", message);
		plugin.saveConfig();

		return true;
	}

	public String getMessage2Message3() {
		return message2Message3;
	}

	public int getValue1() {
		return value1;
	}

	public double getValue2() {
		return value2;
	}

	public Map<String, String> getMapKeys() {
		return mapKeys;
	}

}
