package jp.jyn.part9;

import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.ShulkerBox;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	@EventHandler(ignoreCancelled = true)
	public void BlockDamage(BlockDamageEvent e) { // 殴った時に作動する
		Block block = e.getBlock(); // 殴られたブロックを取得

		if (block.getType() != Material.CHEST) { // チェスト以外
			return; // 何もしない
			// TODO:トラップチェストにも対応したいなら変更が必要ね。
		} else if (isLargeChest(block)) { // ラージチェストなら何もしない
			return;
		}
		/* こんな感じでも書ける
		 * if(block.getType() != Material.CHEST && isLargeChest(block))
		 */

		// Chestにキャスト
		Chest chest = (Chest) block.getState();
		// チェストのインベントリを取得
		Inventory oldInv = chest.getInventory();

		// 中身を取り出しておく
		HashMap<Integer, ItemStack> items = new HashMap<>();
		// 中身を移す
		for (int i = 0, size = oldInv.getSize(); i < size; ++i) {
			// アイテム取得
			ItemStack item = oldInv.getItem(i);
			if (item == null) { // アイテムがnull(ない)なら次へ
				continue;
			}

			// 追加
			items.put(i, item);
		}
		// 古い方の中身を消してアイテムが飛び出さない(増加しない)様にする
		oldInv.clear();

		// シュルカーボックスに変えちゃう(ちなみにこの時中にアイテムが入っていると飛び出すみたい)
		block.setType(Material.PURPLE_SHULKER_BOX);
		// シュルカーボックスにキャスト
		ShulkerBox box = (ShulkerBox) block.getState();
		// シュルカーボックスのインベントリ
		Inventory newInv = box.getInventory();

		// 予め取り出しておいた中身を入れる
		for (Entry<Integer, ItemStack> item : items.entrySet()) {
			// アイテムを入れる
			newInv.setItem(item.getKey(), item.getValue());
		}

		// ついでに適当にエフェクトでも出しとくか
		Location boxLoc = block.getLocation().clone();
		World world = boxLoc.getWorld();
		// 1ブロック上げとく(そっちの方が見やすい)
		boxLoc.setY(boxLoc.getY() + 1);
		// ダメージの無い爆発
		world.createExplosion(boxLoc, 0);
		// ダメージのない落雷
		world.strikeLightningEffect(boxLoc);
		// パーティクル
		world.spawnParticle(Particle.VILLAGER_HAPPY, boxLoc, 20, 1, 1, 1);
		// 音
		world.playSound(boxLoc, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
	}

	/**
	 * ラージチェストかどうかをチェック(他に良い方法知ってたら教えて)
	 * @param chest 対象のブロック
	 * @return ラージチェストならtrue、違えばfalse
	 */
	private boolean isLargeChest(Block block) {
		if (block.getType() != Material.CHEST) { // そもそもチェストじゃない
			return false;
		}

		// 東西南北でテスト
		for (BlockFace face : new BlockFace[] {
				BlockFace.NORTH,
				BlockFace.SOUTH,
				BlockFace.EAST,
				BlockFace.WEST,
		}) {
			// その位置にあるブロックを取得
			Block relative = block.getRelative(face);
			if (relative.getType() == Material.CHEST) { // それが2個横並び(ラージチェスト)なら
				// true
				return true;
			}
		}

		// ラージチェストじゃない
		return false;
	}

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}

	// どうでも良いけど、今回は説明用でこうしてるだけで
	// やたらめったらコメント書きまくると却って読みづらいよ。
	// コードから読み取れる事はコメントに書くな、読み取れない所だけ書けって誰かが……
}
