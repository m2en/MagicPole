package com.github.merunno.magicpole.Listener;

import com.github.merunno.magicpole.MagicPole;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class ChantingListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        ItemStack item = e.getItem();
        Location loc = player.getLocation();
        if(item == null) return;
        if(!item.hasItemMeta()) return;
        if(Objects.requireNonNull(item.getItemMeta()).getDisplayName().equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "MagicPole") && item.getType() == Material.BLAZE_ROD) {
            int foodlv = player.getFoodLevel();
            if(foodlv <= 0) { // 満腹度が0だった場合は詠唱をキャンセル
                player.sendMessage(ChatColor.RED + "お腹が空いて魔法を詠唱できない.........");
                FoodError(loc);
                return;
            }
            player.sendMessage(ChatColor.AQUA + "詠唱を行った......10秒待機が必要みたいだ......");
            new BukkitRunnable() {
                @Override
                public void run() {
                    player.sendMessage(ChatColor.YELLOW + "詠唱した。体力ブースト・スピードの効果を獲得。");
                    // 満腹度の処理 //
                    player.setFoodLevel(foodlv - 2);
                    // サウンド・パーティクル //
                    Chanting(loc);
                    for(int i = 0; i < 10; i++) {
                        ChantingSound(loc);
                    }
                    // エフェクト //
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 20*15, 1), true);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20*15, 1), true);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.sendMessage(ChatColor.YELLOW + "エフェクト効果が切れたようです。");
                        }
                    }.runTaskLater(JavaPlugin.getPlugin(MagicPole.class), 20*15);
                }
            }.runTaskLater(JavaPlugin.getPlugin(MagicPole.class), 20*10);
        }
    }

    private void FoodError(Location loc) { /* Sound: ミツバチの退出音 */
        Objects.requireNonNull(loc.getWorld()).playSound(loc, Sound.BLOCK_BEEHIVE_EXIT, 2, 1);
    }

    private void ChantingSound(Location loc) { /* Sound: バブル */
        Objects.requireNonNull(loc.getWorld()).playSound(loc, Sound.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, 2, 1);
    }

    private void Chanting(Location loc) { /* Particle: ドラゴンブレス */
        Objects.requireNonNull(loc.getWorld()).playEffect(loc, Effect.DRAGON_BREATH, 0, 10);
    }
}
