package io.github.elenawawa.serenity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bstats.bukkit.Metrics;

import io.github.thebusybiscuit.slimefun5.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun5.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun5.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun5.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun5.api.researches.Research;
import io.github.thebusybiscuit.slimefun5.core.guide.wiki.WikiText;
import io.github.thebusybiscuit.slimefun5.core.guide.wiki.WikiTopic;
import io.github.thebusybiscuit.slimefun5.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun5.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun5.libraries.dough.collections.Pair;
import io.github.thebusybiscuit.slimefun5.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;
import io.github.thebusybiscuit.slimefun5.utils.ChatUtils;


public class Serenity extends JavaPlugin implements SlimefunAddon {

    private int researchId = 3300;
    private ItemGroup itemGroup;

    @Override
    public void onEnable() {
        // Consolidated metrics: only start our own bStats if the server opted out (metrics.disable-addon-metrics = false).
        if (Slimefun.getCfg().contains("metrics.disable-addon-metrics") && !Slimefun.getCfg().getBoolean("metrics.disable-addon-metrics")) {
            new Metrics(this, 6469);
        }

        
        itemGroup = new ItemGroup(new io.github.thebusybiscuit.slimefun5.libraries.keys.NamespacedKey("serenity", "serene"), CustomItemStack.create(MaterialCompat.safe(XMaterial.AMYTHEST), "\u00a76Serenity"), 1).setTheme("Misc");

        registerSword(XMaterial.IRON_SWORD, "COBALT", SlimefunItems.COBALT_INGOT, Arrays.asList(new Pair<>(Enchantment.DAMAGE_ALL, 7), new Pair<>(Enchantment.DURABILITY, 7)));
        registerArmor(ArmorSet.IRON, "COBALT", SlimefunItems.COBALT_INGOT, Arrays.asList(new Pair<>(Enchantment.PROTECTION_ENVIRONMENTAL, 7), new Pair<>(Enchantment.DURABILITY, 7)));

        Slimefun.getItemTranslationService().registerTranslations(this);

        // Register this addon's own in-game wiki page (core does not auto-generate addon wikis).
        registerWiki();
    }

    private void registerWiki() {
        WikiText wiki = Slimefun.getWikiText();

        Map<ItemGroup, List<String>> groupedItems = new LinkedHashMap<>();
        for (SlimefunItem item : Slimefun.getRegistry().getEnabledSlimefunItems()) {
            try {
                if (item.getAddon() != this) {
                    continue;
                }
                ItemGroup group = item.getItemGroup();
                groupedItems.computeIfAbsent(group, key -> new ArrayList<>()).add(item.getId());

                List<String> page = describeItem(item.getId());
                if (page != null) {
                    wiki.set(item.getId(), page);
                }
            } catch (Exception | LinkageError ignored) {
                // Skip items that fail to resolve their group/addon on legacy versions.
            }
        }

        for (Map.Entry<ItemGroup, List<String>> entry : groupedItems.entrySet()) {
            ItemGroup group = entry.getKey();
            String groupKey = group.getKey().getKey();
            String topicId = "addon_extragear_" + groupKey;

            wiki.registerTopic(new WikiTopic(
                topicId,
                topicDisplayName(groupKey),
                topicIcon(groupKey),
                topicTagline(groupKey)
            ));
            wiki.setMechanic(topicId, describeCategory(groupKey));
            wiki.setTopicItems(topicId, entry.getValue());
        }
    }

    @Nonnull
    private String topicDisplayName(@Nonnull String groupKey) {
        switch (groupKey) {
            case "items": return "Serenity";
            default: return "Serenity";
        }
    }

    @Nonnull
    private XMaterial topicIcon(@Nonnull String groupKey) {
        switch (groupKey) {
            case "items": return XMaterial.DIAMOND_CHESTPLATE;
            default: return XMaterial.IRON_SWORD;
        }
    }

    @Nonnull
    private String topicTagline(@Nonnull String groupKey) {
        switch (groupKey) {
            case "items": return "penis";
            default: return "didy";
        }
    }

    @Nonnull
    private List<String> describeCategory(@Nonnull String groupKey) {
        switch (groupKey) {
            case "items":
                return Arrays.asList(
                    "no"
                );
            default:
                return Arrays.asList(
                    "ok",
                    "",
                    "&7Click an item below for its recipe & details."
                );
        }
    }

    @Nullable
    private List<String> describeItem(@Nonnull String itemId) {
        switch (itemId) {
            
            case "COBALT_SWORD":
                return Arrays.asList(
                    "big",
                    "penis"
                );
            default:
                break;
        }

        if (itemId.endsWith("_HELMET")) {
            return Arrays.asList(
                "&7The helmet of an ExtraGear armor set.",
                "&7Comes with built-in protection enchantments.",
                "&7Crafted in the &bArmor Forge&7 from its metal ingots."
            );
        }
        if (itemId.endsWith("_CHESTPLATE")) {
            return Arrays.asList(
                "&7The chestplate of an ExtraGear armor set - the",
                "&7most protective piece of the set.",
                "&7Crafted in the &bArmor Forge&7 from its metal ingots."
            );
        }
        if (itemId.endsWith("_LEGGINGS")) {
            return Arrays.asList(
                "&7The leggings of an ExtraGear armor set.",
                "&7Come with built-in protection enchantments.",
                "&7Crafted in the &bArmor Forge&7 from its metal ingots."
            );
        }
        if (itemId.endsWith("_BOOTS")) {
            return Arrays.asList(
                "&7The boots of an ExtraGear armor set.",
                "&7Come with built-in protection enchantments.",
                "&7Crafted in the &bArmor Forge&7 from its metal ingots."
            );
        }

        return null;
    }

    private void registerSword(@Nonnull XMaterial type, @Nonnull String component, @Nonnull SlimefunItemStack item, @Nonnull List<Pair<Enchantment, Integer>> enchantments) {
        SlimefunItemStack is = new SlimefunItemStack(component + "_SWORD", MaterialCompat.safe(type));

        for (Pair<Enchantment, Integer> enchantment : enchantments) {
            is.addUnsafeEnchantment(enchantment.getFirstValue(), enchantment.getSecondValue());
        }

        ItemStack ingredient = item.item();
        SlimefunItem slimefunItem = new SlimefunItem(itemGroup, is, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[] { null, ingredient, null, null, ingredient, null, null, new ItemStack(MaterialCompat.safe(XMaterial.STICK)), null });
        slimefunItem.register(this);

        researchId++;

        Research research = new Research(new io.github.thebusybiscuit.slimefun5.libraries.keys.NamespacedKey("extragear", component.toLowerCase() + "_sword"), researchId, ChatUtils.humanize(component) + " Sword", 3);
        research.addItems(slimefunItem);
        research.register();
    }

    private void registerArmor(@Nonnull ArmorSet armorset, @Nonnull String component, @Nonnull SlimefunItemStack item, @Nonnull List<Pair<Enchantment, Integer>> enchantments) {
        String humanizedComponent = ChatUtils.humanize(component);
        SlimefunItemStack[] armor = { new SlimefunItemStack(component + "_HELMET", armorset.getHelmet()),
                new SlimefunItemStack(component + "_CHESTPLATE", armorset.getChestplate()),
                new SlimefunItemStack(component + "_LEGGINGS", armorset.getLeggings()),
                new SlimefunItemStack(component + "_BOOTS", armorset.getBoots()) };

        for (Pair<Enchantment, Integer> enchantment : enchantments) {
            for (SlimefunItemStack armorPiece : armor) {
                armorPiece.addUnsafeEnchantment(enchantment.getFirstValue(), enchantment.getSecondValue());
            }
        }

        ItemStack ingredient = item.item();
        SlimefunItem helmet = new SlimefunItem(itemGroup, armor[0], RecipeType.ARMOR_FORGE, new ItemStack[] { ingredient, ingredient, ingredient, ingredient, null, ingredient, null, null, null });
        helmet.register(this);

        SlimefunItem chestplate = new SlimefunItem(itemGroup, armor[1], RecipeType.ARMOR_FORGE, new ItemStack[] { ingredient, null, ingredient, ingredient, ingredient, ingredient, ingredient, ingredient, ingredient });
        chestplate.register(this);

        SlimefunItem leggings = new SlimefunItem(itemGroup, armor[2], RecipeType.ARMOR_FORGE, new ItemStack[] { ingredient, ingredient, ingredient, ingredient, null, ingredient, ingredient, null, ingredient });
        leggings.register(this);

        SlimefunItem boots = new SlimefunItem(itemGroup, armor[3], RecipeType.ARMOR_FORGE, new ItemStack[] { null, null, null, ingredient, null, ingredient, ingredient, null, ingredient });
        boots.register(this);

        researchId++;

        Research research = new Research(new io.github.thebusybiscuit.slimefun5.libraries.keys.NamespacedKey("extragear", component.toLowerCase() + "_armor"), researchId, humanizedComponent + " Armor", 5);
        research.addItems(helmet, chestplate, leggings, boots);
        research.register();
    }

    @Nonnull
    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Nonnull
    @Override
    public String getBugTrackerURL() {
        return "https://github.com/Elena-wawa/Serenity/issues";
    }

}

