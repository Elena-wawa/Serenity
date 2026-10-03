package io.github.thebusybiscuit.extragear;

import javax.annotation.Nonnull;

import org.bukkit.Material;

import io.github.thebusybiscuit.slimefun5.libraries.xseries.XMaterial;

/**
 * Represents the different base armor material sets that can be used
 * when registering new armor items in ExtraGear.
 *
 * @author TheBusyBiscuit
 */
enum ArmorSet {

    LEATHER(XMaterial.LEATHER_HELMET, XMaterial.LEATHER_CHESTPLATE, XMaterial.LEATHER_LEGGINGS, XMaterial.LEATHER_BOOTS),
    IRON(XMaterial.IRON_HELMET, XMaterial.IRON_CHESTPLATE, XMaterial.IRON_LEGGINGS, XMaterial.IRON_BOOTS),
    GOLD(XMaterial.GOLDEN_HELMET, XMaterial.GOLDEN_CHESTPLATE, XMaterial.GOLDEN_LEGGINGS, XMaterial.GOLDEN_BOOTS),
    DIAMOND(XMaterial.DIAMOND_HELMET, XMaterial.DIAMOND_CHESTPLATE, XMaterial.DIAMOND_LEGGINGS, XMaterial.DIAMOND_BOOTS);

    private final Material helmet;
    private final Material chestplate;
    private final Material leggings;
    private final Material boots;

    ArmorSet(@Nonnull XMaterial helmet, @Nonnull XMaterial chestplate, @Nonnull XMaterial leggings, @Nonnull XMaterial boots) {
        this.helmet = MaterialCompat.safe(helmet);
        this.chestplate = MaterialCompat.safe(chestplate);
        this.leggings = MaterialCompat.safe(leggings);
        this.boots = MaterialCompat.safe(boots);
    }

    @Nonnull
    public Material getHelmet() {
        return helmet;
    }

    @Nonnull
    public Material getChestplate() {
        return chestplate;
    }

    @Nonnull
    public Material getLeggings() {
        return leggings;
    }

    @Nonnull
    public Material getBoots() {
        return boots;
    }

}
