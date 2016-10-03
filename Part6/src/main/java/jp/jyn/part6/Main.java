package jp.jyn.part6;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		// 他のクラスから登録する場合
		//getServer().getPluginManager().registerEvents(new BlockPlace(), this);

		// コンストラクタで登録する場合
		new BlockPlace(this);
	}

	@Override
	public void onDisable() {
		// 特定のイベントだけを解除する場合
		//BlockPlaceEvent.getHandlerList().unregister(this);
		// 全てのイベントを解除する場合
		HandlerList.unregisterAll(this);
	}
}
