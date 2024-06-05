package io.github.adamson;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MCChangeSpawner extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().addRecipe(createSpawnerRecipe("skeleton", Material.SKELETON_SKULL));
        getServer().addRecipe(createSpawnerRecipe("zombie", Material.ZOMBIE_HEAD));
        getServer().addRecipe(createSpawnerRecipe("wither_skeleton", Material.WITHER_SKELETON_SKULL));
        getServer().addRecipe(createSpawnerRecipe("creeper", Material.CREEPER_HEAD));
        getServer().addRecipe(createSpawnerRecipe("piglin", Material.PIGLIN_HEAD));
    }

    private Recipe createSpawnerRecipe(String typeName, Material headMaterial) {
        ItemStack spawnerItem = new ItemStack(Material.SPAWNER);
        ItemMeta spawnerMeta = spawnerItem.getItemMeta();
        spawnerMeta.setDisplayName(ChatColor.RESET + Arrays.stream(typeName.replace("_", " ").split(" "))
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1)).collect(Collectors.joining(" ")) + " Spawner");

        ArrayList<String> lore = new ArrayList<>();
        lore.add(typeName);
        spawnerMeta.setLore(lore);
        spawnerItem.setItemMeta(spawnerMeta);

        NamespacedKey key = new NamespacedKey(this, typeName + "_spawner");

        ShapedRecipe recipe = new ShapedRecipe(key, spawnerItem);
        recipe.shape("SH");

        recipe.setIngredient('S', Material.SPAWNER);
        recipe.setIngredient('H', headMaterial);

        return recipe;
    }
}
