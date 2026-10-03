package io.github.elena-wawa.serenity;

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

        
        itemGroup = new ItemGroup(new io.github.thebusybiscuit.slimefun5.libraries.keys.NamespacedKey("extragear", "items"), CustomItemStack.create(MaterialCompat.safe(XMaterial.DIAMOND_SWORD), "\u00a76ExtraGear"), 1).setTheme("tools");

        registerSword(XMaterial.IRON_SWORD, "COPPER", SlimefunItems.COPPER_INGOT, Arrays.asList(new Pair<>(Enchantment.DAMAGE_UNDEAD, 2)));
        registerArmor(ArmorSet.LEATHER, "COPPER", SlimefunItems.COPPER_INGOT, Arrays.asList(new Pair<>(Enchantment.PROTECTION_EXPLOSIONS, 2)));

        registerSword(XMaterial.IRON_SWORD, "TIN", SlimefunItems.TIN_INGOT, Arrays.asList(new Pair<>(Enchantment.DAMAGE_ALL, 1)));
        registerArmor(ArmorSet.IRON, "TIN", SlimefunItems.TIN_INGOT, Arrays.asList(new Pair<>(Enchantment.PROTECTION_EXPLOSIONS, 3)));

        registerSword(XMaterial.IRON_SWORD, "SILVER", SlimefunItems.SILVER_INGOT, Arrays.asList(new Pair<>(Enchantment.DAMAGE_ALL, 2)));
        registerArmor(ArmorSet.IRON, "SILVER", SlimefunItems.SILVER_INGOT, Arrays.asList(new Pair<>(Enchantment.PROTECTION_ENVIRONMENTAL, 2)));

        registerSword(XMaterial.IRON_SWORD, "ALUMINUM", SlimefunItems.ALUMINUM_INGOT, Arrays.asList(new Pair<>(Enchantment.DAMAGE_ARTHROPODS, 3)));
        registerArmor(ArmorSet.IRON, "ALUMINUM", SlimefunItems.ALUMINUM_INGOT, Arrays.asList(new Pair<>(Enchantment.PROTECTION_EXPLOSIONS, 2), new Pair<>(Enchantment.DURABILITY, 2)));

        registerSword(XMaterial.IRON_SWORD, "LEAD", SlimefunItems.LEAD_INGOT, Arrays.asList(new Pair<>(Enchantment.DAMAGE_ALL, 3), new Pair<>(Enchantment.DURABILITY, 8)));
        registerArmor(ArmorSet.IRON, "LEAD", SlimefunItems.LEAD_INGOT, Arrays.asList(new Pair<>(Enchantment.PROTECTION_ENVIRONMENTAL, 3), new Pair<>(Enchantment.DURABILITY, 8)));

        registerSword(XMaterial.IRON_SWORD, "ZINC", SlimefunItems.ZINC_INGOT, Arrays.asList(new Pair<>(Enchantment.DAMAGE_ALL, 2)));
        registerArmor(ArmorSet.IRON, "ZINC", SlimefunItems.ZINC_INGOT, Arrays.asList(new Pair<>(Enchantment.PROTECTION_ENVIRONMENTAL, 3)));

        registerSword(XMaterial.IRON_SWORD, "MAGNESIUM", SlimefunItems.MAGNESIUM_INGOT, Arrays.asList(new Pair<>(Enchantment.DAMAGE_ALL, 2), new Pair<>(Enchantment.DURABILITY, 5)));
        registerArmor(ArmorSet.IRON, "MAGNESIUM", SlimefunItems.MAGNESIUM_INGOT, Arrays.asList(new Pair<>(Enchantment.PROTECTION_ENVIRONMENTAL, 2), new Pair<>(Enchantment.DURABILITY, 5)));

        registerSword(XMaterial.IRON_SWORD, "STEEL", SlimefunItems.STEEL_INGOT, Arrays.asList(new Pair<>(Enchantment.DAMAGE_ALL, 5), new Pair<>(Enchantment.DURABILITY, 6)));
        registerArmor(ArmorSet.IRON, "STEEL", SlimefunItems.STEEL_INGOT, Arrays.asList(new Pair<>(Enchantment.PROTECTION_ENVIRONMENTAL, 3), new Pair<>(Enchantment.DURABILITY, 4)));

        registerSword(XMaterial.IRON_SWORD, "BRONZE", SlimefunItems.BRONZE_INGOT, Arrays.asList(new Pair<>(Enchantment.DAMAGE_ALL, 3), new Pair<>(Enchantment.DURABILITY, 6)));
        registerSword(XMaterial.IRON_SWORD, "DURALUMIN", SlimefunItems.DURALUMIN_INGOT, Arrays.asList(new Pair<>(Enchantment.DAMAGE_ALL, 3), new Pair<>(Enchantment.DURABILITY, 6)));
        registerSword(XMaterial.IRON_SWORD, "BILLON", SlimefunItems.BILLON_INGOT, Arrays.asList(new Pair<>(Enchantment.DAMAGE_ALL, 4), new Pair<>(Enchantment.DURABILITY, 5)));
        registerSword(XMaterial.IRON_SWORD, "BRASS", SlimefunItems.BRASS_INGOT, Arrays.asList(new Pair<>(Enchantment.DAMAGE_UNDEAD, 4), new Pair<>(Enchantment.DURABILITY, 6)));
        registerSword(XMaterial.IRON_SWORD, "ALUMINUM_BRASS", SlimefunItems.ALUMINUM_BRASS_INGOT, Arrays.asList(new Pair<>(Enchantment.DAMAGE_ARTHROPODS, 4), new Pair<>(Enchantment.DURABILITY, 4)));
        registerSword(XMaterial.IRON_SWORD, "ALUMINUM_BRONZE", SlimefunItems.ALUMINUM_BRONZE_INGOT, Arrays.asList(new Pair<>(Enchantment.DAMAGE_ARTHROPODS, 4), new Pair<>(Enchantment.DURABILITY, 5)));
        registerSword(XMaterial.IRON_SWORD, "CORINTHIAN_BRONZE", SlimefunItems.CORINTHIAN_BRONZE_INGOT, Arrays.asList(new Pair<>(Enchantment.DAMAGE_ALL, 5), new Pair<>(Enchantment.DURABILITY, 5)));
        registerSword(XMaterial.IRON_SWORD, "SOLDER", SlimefunItems.SOLDER_INGOT, Arrays.asList(new Pair<>(Enchantment.DAMAGE_ALL, 4), new Pair<>(Enchantment.DURABILITY, 6)));
        registerSword(XMaterial.IRON_SWORD, "DAMASCUS_STEEL", SlimefunItems.DAMASCUS_STEEL_INGOT, Arrays.asList(new Pair<>(Enchantment.DAMAGE_ALL, 6), new Pair<>(Enchantment.DURABILITY, 7)));
        registerSword(XMaterial.IRON_SWORD, "HARDENED", SlimefunItems.HARDENED_METAL_INGOT, Arrays.asList(new Pair<>(Enchantment.DAMAGE_ALL, 7), new Pair<>(Enchantment.DURABILITY, 10)));
        registerSword(XMaterial.IRON_SWORD, "REINFORCED", SlimefunItems.REINFORCED_ALLOY_INGOT, Arrays.asList(new Pair<>(Enchantment.DAMAGE_ALL, 8), new Pair<>(Enchantment.DURABILITY, 8)));
        registerSword(XMaterial.IRON_SWORD, "FERROSILICON", SlimefunItems.FERROSILICON, Arrays.asList(new Pair<>(Enchantment.DAMAGE_UNDEAD, 8), new Pair<>(Enchantment.DURABILITY, 4)));
        registerSword(XMaterial.GOLDEN_SWORD, "GILDED_IRON", SlimefunItems.GILDED_IRON, Arrays.asList(new Pair<>(Enchantment.DAMAGE_ARTHROPODS, 8), new Pair<>(Enchantment.DURABILITY, 10)));
        registerSword(XMaterial.IRON_SWORD, "NICKEL", SlimefunItems.NICKEL_INGOT, Arrays.asList(new Pair<>(Enchantment.DAMAGE_ALL, 6), new Pair<>(Enchantment.DURABILITY, 5)));

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
            case "items": return "Extra Gear: Tools & Armor";
            default: return "Extra Gear";
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
            case "items": return "&7Metal swords & full armor sets";
            default: return "&7Extra tools and armor sets";
        }
    }

    @Nonnull
    private List<String> describeCategory(@Nonnull String groupKey) {
        switch (groupKey) {
            case "items":
                return Arrays.asList(
                    "&7ExtraGear forges weapons and armor from the many",
                    "&7metals and alloys Slimefun adds to the game.",
                    "",
                    "&7&lSwords &7are crafted in the &bEnhanced Crafting",
                    "&7Table&7 from two ingots and a stick, in the usual",
                    "&7sword pattern. Each metal yields a sword with its",
                    "&7own pre-applied enchantments, scaling from soft",
                    "&7Copper up to powerful Reinforced and Cobalt blades.",
                    "",
                    "&7&lArmor sets &7are crafted in the &bArmor Forge&7",
                    "&7as helmet, chestplate, leggings and boots. Every",
                    "&7piece carries built-in protection enchantments,",
                    "&7so a full set is far tougher than its vanilla base.",
                    "",
                    "&7Because the enchantments are baked in, the gear",
                    "&7cannot be disenchanted - it is ready to wear or",
                    "&7swing the moment it is crafted.",
                    "",
                    "&7Click an item below for its recipe & details."
                );
            default:
                return Arrays.asList(
                    "&7Extra tools and armor sets forged from metals.",
                    "",
                    "&7Click an item below for its recipe & details."
                );
        }
    }

    @Nullable
    private List<String> describeItem(@Nonnull String itemId) {
        switch (itemId) {
            case "COPPER_SWORD":
                return Arrays.asList(
                    "&7An entry-level blade forged from Copper.",
                    "&7Carries &bSmite II&7, making it handy for clearing",
                    "&7out early-game undead such as zombies and skeletons."
                );
            case "TIN_SWORD":
            case "ZINC_SWORD":
            case "SILVER_SWORD":
                return Arrays.asList(
                    "&7A light, cheap sword forged from a base metal.",
                    "&7Comes pre-enchanted with &bSharpness&7 for a small",
                    "&7but reliable damage boost over a vanilla blade."
                );
            case "ALUMINUM_SWORD":
            case "ALUMINUM_BRASS_SWORD":
            case "ALUMINUM_BRONZE_SWORD":
                return Arrays.asList(
                    "&7An aluminium-based blade built for pest control.",
                    "&7Carries &bBane of Arthropods&7, tearing through",
                    "&7spiders, silverfish and other bugs with ease."
                );
            case "MAGNESIUM_SWORD":
            case "BRONZE_SWORD":
            case "DURALUMIN_SWORD":
            case "BILLON_SWORD":
            case "SOLDER_SWORD":
                return Arrays.asList(
                    "&7A solid mid-tier alloy sword.",
                    "&7Pre-enchanted with &bSharpness&7 and &bUnbreaking&7,",
                    "&7balancing extra damage with a longer lifespan."
                );
            case "LEAD_SWORD":
            case "STEEL_SWORD":
            case "CORINTHIAN_BRONZE_SWORD":
            case "NICKEL_SWORD":
                return Arrays.asList(
                    "&7A heavy, dependable alloy blade.",
                    "&7Combines a strong &bSharpness&7 enchantment with",
                    "&bUnbreaking&7 for sustained combat use."
                );
            case "BRASS_SWORD":
            case "FERROSILICON_SWORD":
                return Arrays.asList(
                    "&7A specialist anti-undead blade.",
                    "&7Carries powerful &bSmite&7 plus &bUnbreaking&7,",
                    "&7devastating against zombies, skeletons and wither",
                    "&7skeletons in the Nether."
                );
            case "GILDED_IRON_SWORD":
                return Arrays.asList(
                    "&7A gilded blade with an ornate golden edge.",
                    "&7Carries strong &bBane of Arthropods&7 and high",
                    "&bUnbreaking&7, lasting far longer than gold should."
                );
            case "DAMASCUS_STEEL_SWORD":
                return Arrays.asList(
                    "&7A patterned blade folded from Damascus Steel.",
                    "&7Carries heavy &bSharpness&7 and &bUnbreaking&7,",
                    "&7a true upper-tier weapon."
                );
            case "HARDENED_SWORD":
            case "REINFORCED_SWORD":
            case "COBALT_SWORD":
                return Arrays.asList(
                    "&7A top-tier blade forged from a hardened alloy.",
                    "&7Carries some of the strongest &bSharpness&7 and",
                    "&bUnbreaking&7 in ExtraGear - a late-game powerhouse."
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

