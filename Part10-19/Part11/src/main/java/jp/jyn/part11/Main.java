package jp.jyn.part11;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	// TODO:何らかの方法で「Plugin」のインスタンスを渡す必要があります。
	// (今回はメインクラスにイベントリスナを書いているのでthisが使えますが、実際はコンストラクタなどで渡す必要あり)
	Plugin plugin = this;

	// 識別子的な、こうやって定数化(final+static)しておくと後の変更が楽
	private final static String DATA_KEY = "SPAWNLOCATION";

	@EventHandler(ignoreCancelled = true)
	public void spawn(CreatureSpawnEvent e) {
		// メタデータを付けたいEntity
		Entity entity = e.getEntity();
		entity.setMetadata(
				DATA_KEY, // key
				new FixedMetadataValue(
						plugin, // プラグイン
						entity.getLocation().clone() // 設定したい値
				));
	}

	@EventHandler(ignoreCancelled = true)
	public void kill(EntityDeathEvent e) {
		// 殺されたエンティティ
		LivingEntity entity = e.getEntity();
		// エンティティを殺したプレイヤー
		Player player = entity.getKiller();

		// null=プレイヤーが殺したのではないなら
		if (player == null) {
			return; // 何もしない
		}

		// メタデータを取得する
		List<MetadataValue> values = entity.getMetadata(DATA_KEY);
		MetadataValue value = null;

		// ループで全部チェックする
		for (MetadataValue v : values) {
			// 名前を比較して同じプラグインか確認
			if (v.getOwningPlugin().getName().equals(plugin.getName())) {
				// 同じなら値をセットしてループ抜ける
				value = v;
				break;
			}
		}

		// nullのまま(見つからなかった)
		if (value == null) {
			return; // TODO:何か処理
		}

		// キャストする
		Location location = (Location) value.value();

		// 出力する
		player.sendMessage(
				"あなたがたった今、命を奪ったMOBは"
						+ location.getWorld().getName()
						+ "の、X="
						+ location.getBlockX()
						+ ",Y="
						+ location.getBlockY()
						+ ",Z="
						+ location.getBlockZ()
						+ "で生まれたMOBです……");
	}

	/**
	 * 値の削除の例示
	 * @param entity 値を削除するエンティティ
	 */
	@SuppressWarnings("unused")
	private void remove(Entity entity) {
		entity.removeMetadata(
				DATA_KEY, // key
				plugin // プラグイン
		);
	}

	/**
	 * 値の存在確認の例示
	 * @param entity 存在確認を行うエンティティ
	 * @return あればtrue
	 */

	@SuppressWarnings("unused")
	private boolean has(Entity entity) {
		return entity.hasMetadata(DATA_KEY);
	}

	/**
	 * キャストが要らない取得の例示
	 * @param value 取得する値
	 */
	@SuppressWarnings("unused")
	private void get(MetadataValue value) {
		value.asBoolean(); // boolean
		value.asByte(); // byte
		value.asDouble(); // double
		value.asFloat(); // float
		value.asInt(); // int
		value.asLong(); // long
		value.asShort(); // short
		value.asString(); // String
	}

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}
}
