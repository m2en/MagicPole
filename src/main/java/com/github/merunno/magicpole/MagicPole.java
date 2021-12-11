package com.github.merunno.magicpole;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class MagicPole extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("MagicPole Starting....");

        // Create CustomItem //
        ItemStack customItem = new ItemStack(Material.BLAZE_ROD, 1);
        ItemMeta customMeta = customItem.getItemMeta();
        if(customMeta == null) return;

        List<String> customLore = new ArrayList<>();
        customLore.add("魔法を使うことが出来る不思議なアイテムです。");
        customLore.add("持って右クリックすることで詠唱し、HPブースト+スピード上昇の効果を受けることが出来ます。");
        customLore.add("代償にあなたの空腹度を増やすようです.......");

        customMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "MagicPole");
        customMeta.setLore(customLore);

        customItem.setItemMeta(customMeta);

        // Create CustomRecipe //
        NamespacedKey customKey = new NamespacedKey(this, "magic_pole");
        ShapedRecipe customRecipe = new ShapedRecipe(customKey, customItem);

        customRecipe.shape(
                "R G",
                " i ",
                "i a"
        );

        customRecipe.setIngredient('R', Material.RABBIT); // 兎肉
        customRecipe.setIngredient('G', Material.GOLD_BLOCK); //　金のブロック
        customRecipe.setIngredient('i', Material.GOLD_INGOT); // 金のインゴット
        customRecipe.setIngredient('a', Material.APPLE); // りんご

        Bukkit.addRecipe(customRecipe);

    }

    @Override
    public void onDisable() { getLogger().info("MagicPole Shutdown........"); }
}
