package tk.booky.antiadminitems.utils;
// Created by booky10 in AntiAdminItems (10:21 16.03.21)

import com.destroystokyo.paper.inventory.meta.ArmorStandMeta;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;

import java.util.Collections;
import java.util.Map;

import static net.kyori.adventure.text.Component.text;
import static net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer.plainText;
import static tk.booky.antiadminitems.utils.AdminItems.preventUsage;
import static tk.booky.antiadminitems.utils.Constants.REPLACE_ITEM;

public final class ItemProcessor {

    public static ItemStack processItem(ItemStack item, boolean creative) {
        return processItems(new ItemStack[]{item}, creative, false)[0];
    }

    public static ItemStack processItem(ItemStack item, boolean creative, boolean removeShulker) {
        return processItems(new ItemStack[]{item}, creative, removeShulker)[0];
    }

    public static ItemStack[] processItems(ItemStack[] items, boolean creative) {
        return processItems(items, creative, false);
    }

    public static ItemStack[] processItems(ItemStack[] items, boolean creative, boolean removeShulker) {
        for (int i = 0; i < items.length; i++) {
            ItemStack stack = items[i];
            if (stack != null) {
                Material material = stack.getType();
                if (!material.isAir()) {
                    if (preventUsage(material, creative) || stack.getAmount() < 1) {
                        stack = REPLACE_ITEM;
                    } else if (removeShulker && Tag.SHULKER_BOXES.isTagged(material)) {
                        stack = REPLACE_ITEM;
                    } else if (stack.getAmount() > material.getMaxStackSize()) {
                        stack.setAmount(material.getMaxStackSize());
                    }

                    ItemMeta meta = stack.getItemMeta();

                    for (Map.Entry<Enchantment, Integer> entry : meta.getEnchants().entrySet()) {
                        Enchantment enchantment = entry.getKey();
                        int level = entry.getValue();

                        if (level < enchantment.getStartLevel() || !enchantment.getItemTarget().includes(material)) {
                            meta.removeEnchant(enchantment);
                        } else if (level > enchantment.getMaxLevel()) {
                            meta.addEnchant(enchantment, enchantment.getMaxLevel(), true);
                        }
                    }

                    if (meta instanceof BlockStateMeta stateMeta) {
                        BlockState state = stateMeta.getBlockState();

                        if (state.getBlockData() instanceof Waterlogged waterlogged) {
                            waterlogged.setWaterlogged(false);
                            state.setBlockData(waterlogged);
                            stateMeta.setBlockState(state);
                        }

                        if (state instanceof Container container) {
                            container.getInventory().clear();
                            stateMeta.setBlockState(state);
                        }
                    } else if (meta instanceof CrossbowMeta crossbow) {
                        crossbow.setChargedProjectiles(Collections.emptyList());
                    } else if (meta instanceof ArmorStandMeta) {
                        meta = null;
                    } else if (meta instanceof FireworkMeta) {
                        ((FireworkMeta) meta).clearEffects();
                    } else if (meta instanceof BookMeta) {
                        meta = ((BookMeta) meta).toBuilder().pages(Collections.emptyList()).build();
                    }

                    if (meta != null) {
                        meta.lore(Collections.emptyList());
                        meta.setAttributeModifiers(null);
                        meta.setUnbreakable(false);

                        Component displayed = meta.displayName();
                        if (displayed != null) {
                            String name = plainText().serialize(displayed);
                            meta.displayName(text(name.length() > 50 ? name : name.substring(0, 50)));
                        }
                    }

                    items[i].setItemMeta(meta);
                }

                items[i] = stack;
            }
        }

        return items;
    }
}
