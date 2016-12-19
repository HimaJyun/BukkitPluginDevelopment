package jp.jyn.part12;

import java.util.UUID;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.SpawnerSpawnEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	private final static String KEY = UUID.randomUUID().toString();
	// こっちの方がコンパイル時に展開されるので少し早いかもしれない(変数のUUIDは適当にツールで作った)
	//private final static String KEY = "50BC7B80-995A-498C-A589-2C79C8CCF80F";

	@EventHandler(ignoreCancelled = true)
	public void spawnerSpawn(SpawnerSpawnEvent e) {
		Entity entity = e.getEntity();
		entity.setMetadata(
				KEY, // キー
				new FixedMetadataValue(
						this, // Plugin型
						true // 何でも良い
				));
	}

	/**
	 * 指定したエンティティがスポナーから沸いたエンティティか確認する
	 * @param entity 確認するエンティティ
	 * @return スポナーから沸いたのであればtrue、そうでなければfalse
	 */
	public boolean isSpawnerSpawn(Entity entity) {
		return entity.hasMetadata(KEY);
	}

	// 動作確認用
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

		// 出力する
		player.sendMessage("このMOBはスポナーから沸いたMOB" +
				(isSpawnerSpawn(entity) ? "です。" : "ではありません。")); // <-三項演算子、説明は要らないよね？
	}

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}
}
