package tk.booky.antiadminitems.utils;
// Created by booky10 in AntiAdminItems (10:21 16.03.21)

import com.destroystokyo.paper.inventory.meta.ArmorStandMeta;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Container;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.CrossbowMeta;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.Map;

public final class ItemProcessor {

    public static ItemStack processItem(ItemStack item) {
        return processItems(new ItemStack[]{item})[0];
    }

    public static ItemStack[] processItems(ItemStack[] items) {
        return processItems(items, false);
    }

    @SuppressWarnings("deprecation")
    public static ItemStack[] processItems(ItemStack[] items, boolean removeShulker) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) continue;

            Material material = items[i].getType();
            if (material.isAir()) continue;

            if (removeShulker && Tag.SHULKER_BOXES.isTagged(material)) {
                items[i] = Constants.REPLACE_ITEM;
            } else if (items[i].getAmount() < 1) {
                items[i] = Constants.REPLACE_ITEM;
            } else if (Constants.ADMIN_ITEMS.contains(material) || material.name().endsWith("_SPAWN_EGG")) {
                items[i] = Constants.REPLACE_ITEM;
            } else {
                if (material.name().endsWith("POTION")) {
                    items[i] = Constants.REPLACE_ITEM;
                } else {
                    items[i].setAmount(Math.min(items[i].getAmount(), items[i].getMaxStackSize()));
                    ItemMeta meta = items[i].getItemMeta();

                    for (Map.Entry<Enchantment, Integer> entry : meta.getEnchants().entrySet()) {
                        Enchantment enchantment = entry.getKey();
                        int level = entry.getValue();

                        if (level < enchantment.getStartLevel() || (!enchantment.getItemTarget().includes(material))) {
                            meta.removeEnchant(enchantment);
                        } else if (level > enchantment.getMaxLevel()) {
                            meta.removeEnchant(enchantment);
                            meta.addEnchant(enchantment, enchantment.getMaxLevel(), true);
                        }
                    }

                    if (meta instanceof BlockStateMeta) {
                        BlockData data = ((BlockStateMeta) meta).getBlockState().getBlockData();

                        if (data instanceof Waterlogged) {
                            Waterlogged waterlogged = (Waterlogged) ((BlockStateMeta) meta).getBlockState().getBlockData();
                            waterlogged.setWaterlogged(false);
                            ((BlockStateMeta) meta).getBlockState().setBlockData(waterlogged);
                        }

                        if (data instanceof Container) {
                            ((Container) data).getInventory().clear();
                        }
                    } else if (meta instanceof CrossbowMeta) {
                        ((CrossbowMeta) meta).setChargedProjectiles(Collections.emptyList());
                    } else if (meta instanceof ArmorStandMeta) {
                        meta = null;
                    } else if (meta instanceof FireworkMeta) {
                        ((FireworkMeta) meta).clearEffects();
                    }

                    if (meta != null) {
                        meta.setAttributeModifiers(null);
                        meta.lore(Collections.emptyList());
                        meta.setUnbreakable(false);

                        String name = ChatColor.stripColor(meta.getDisplayName());
                        if (name.length() > 36) name = name.substring(0, 36);
                        meta.setDisplayName(name);
                    }

                    items[i].setItemMeta(meta);
                }
            }
        }

        return items;
    }
}