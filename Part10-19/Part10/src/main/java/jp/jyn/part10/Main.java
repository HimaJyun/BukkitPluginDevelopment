package jp.jyn.part10;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) { // コンソールからは動かないようにしておく
			return true;
		}
		Location loc = ((Player) sender).getLocation();
		sound(loc);
		//explosion(loc);
		//particle(loc);
		effect(loc);
		//lightning(loc);

		return true;
	}

	/**
	 * 音を鳴らすサンプル
	 * @param loc
	 */
	private void sound(Location loc) {
		loc.getWorld().playSound(
				loc, // 鳴らす場所
				Sound.ENTITY_PLAYER_LEVELUP, // 鳴らす音
				100, // 音量
				3f // 音程
		);
	}

	/**
	 * 爆発を起こすサンプル
	 * @param loc
	 */
	@SuppressWarnings("unused")
	private void explosion(Location loc) {
		//loc.getWorld().createExplosion(loc, 500);
		//loc.getWorld().createExplosion(loc, 300, true);
		//loc.getWorld().createExplosion(0, 60, 0, 50);
		//loc.getWorld().createExplosion(0, 60, 0, 30, true);
		loc.getWorld().createExplosion(
				loc.getX(),
				loc.getY(),
				loc.getZ(),
				100,
				true, // 火をつける
				false // でもブロックは壊さない
		);
	}

	/**
	 * パーティクルを発生させる
	 * @param loc
	 */
	@SuppressWarnings("unused")
	private void particle(Location loc) {
		//loc.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, loc,10);
		/*
		loc.getWorld().spawnParticle(
				Particle.VILLAGER_HAPPY,
				loc,
				50,
				10, // 散開させるXの範囲
				10, // 散開させるYの範囲
				10 // 散開させるZの範囲
		);//*/
		//*
		loc.getWorld().spawnParticle(
				Particle.CLOUD,
				loc,
				500,
				10, // 散開させるXの範囲
				10, // 散開させるYの範囲
				10, // 散開させるZの範囲
				0.1 // 速度?
		);//*/
	}

	/**
	 * エフェクトを発生させる
	 * @param loc
	 */
	private void effect(Location loc) {
		loc.getWorld().playEffect(
				loc, // 発生させる場所
				Effect.DRAGON_BREATH, // エフェクトの種類
				0, // 大抵の場合は無意味
				10 // エフェクトの範囲
		);
	}

	/**
	 * 雷を落とす
	 * @param loc
	 */
	@SuppressWarnings("unused")
	private void lightning(Location loc) {
		//loc.getWorld().strikeLightning(loc);
		loc.getWorld().strikeLightningEffect(loc);
	}

}
