package jp.jyn.part8;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

/**
 * config.yml以外の設定ファイルも扱えるクラス
 * @author HimaJyun
 *
 */
public class CustomConfig {

	/* 　　　＿＿＿＿
	 * 　　r勺z勺z勺ｭ＼
	 * 　〈/⌒⌒⌒＼乙 ヽ
	 * 　//　 |　|　ヽ〉|
	 * ／｜ /ﾚ| Ｎ∧ |＼|
	 * ７ ﾚｲ=ｭヽ|r=ヽ|＿＞
	 * `ﾚ|ﾊ|Oｿ　 ﾋOｿＶ|Ｎ　＜ｵｼﾞｮｳｻﾏｰ
	 * 　(人ﾞ `＿　ﾞ(ｿ从
	 * 　(ｿﾚ＞――＜(ｿﾉ
	 * 　(ｿ｜ ﾚ|/L/ (ｿ
	 * ［>ヘL/ 只 L[>O<]
	 * （⌒O｜んz>/ /⌒}
	 * ⊂ニ⊃L　 / ∩＜
	 * / /＼/＼[(⌒)|二フ
	 * )ﾉ　/　　 ￣∪ﾀ＼
	 * 　 /ﾋ辷辷辷辷ﾀ　 >
	 * 　 ＼＿＿＿＿＿／
	 * 　　 |　/ |　/
	 */

	private FileConfiguration config = null;
	private final File configFile;
	private final String file;
	private final Plugin plugin;

	/**
	 * config.ymlを設定として読み書きするカスタムコンフィグクラスをインスタンス化します。
	 *
	 * @param plugin
	 *            ロード対象のプラグイン
	 */
	public CustomConfig(Plugin plugin) {
		this(plugin, "config.yml");
	}

	/**
	 * 指定したファイル名で設定を読み書きするカスタムコンフィグクラスをインスタンス化します。
	 *
	 * @param plugin
	 *            ロード対象のプラグイン
	 * @param fileName
	 *            読み込みファイル名
	 */
	public CustomConfig(Plugin plugin, String fileName) {
		this.plugin = plugin;
		this.file = fileName;
		configFile = new File(plugin.getDataFolder(), file);
	}

	/**
	 * デフォルト設定を保存します。
	 */
	public void saveDefaultConfig() {
		if (!configFile.exists()) {
			plugin.saveResource(file, false);
		}
	}

	/**
	 * 読み込んだFileConfiguretionを提供します。
	 *
	 * @return 読み込んだ設定
	 */
	public FileConfiguration getConfig() {
		if (config == null) {
			reloadConfig();
		}
		return config;
	}

	/**
	 * 設定を保存します。
	 */
	public void saveConfig() {
		if (config == null) {
			return;
		}
		try {
			getConfig().save(configFile);
		} catch (IOException ex) {
			plugin.getLogger().log(Level.SEVERE, "Could not save config to " + configFile, ex);
		}
	}

	/**
	 * 設定をリロードします。
	 */
	public void reloadConfig() {
		config = YamlConfiguration.loadConfiguration(configFile);

		final InputStream defConfigStream = plugin.getResource(file);
		if (defConfigStream == null) {
			return;
		}

		config.setDefaults(
				YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, StandardCharsets.UTF_8)));
	}
}
