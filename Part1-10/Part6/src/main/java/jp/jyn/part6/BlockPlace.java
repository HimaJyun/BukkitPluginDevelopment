package jp.jyn.part6;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;

public class BlockPlace implements Listener {

	public BlockPlace(Plugin plugin) {
		// ここで登録している
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void 関数名は何でも良い(BlockPlaceEvent e) {
		Player player = e.getPlayer(); // ブロックを置いたプレイヤーを取得する
		Block block = e.getBlock(); // 設置したブロックを取得

		// メッセージを組み立て
		StringBuilder builder = new StringBuilder("BlockPlaceEventが発生：");
		builder.append("設置した物=").append(block.toString()).append(" ");

		// プレイヤーにメッセージを送信
		player.sendMessage(builder.toString());
	}

	/**
	 * イベントの登録を解除します
	 */
	public void unregister() {
		// 以下の様に、イベントクラス側から解除する事も可能
		BlockPlaceEvent.getHandlerList().unregister(this);
		HandlerList.unregisterAll(this);
	}
}
