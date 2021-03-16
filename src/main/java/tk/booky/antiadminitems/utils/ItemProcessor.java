package tk.booky.antiadminitems.utils;
// Created by booky10 in AntiAdminItems (10:21 16.03.21)

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionType;

public final class ItemProcessor {

    public static ItemStack processItem(ItemStack item) {
        return processItems(new ItemStack[]{item})[0];
    }

    public static ItemStack[] processItems(ItemStack[] items) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) continue;

            if (items[i].getAmount() < 1) {
                items[i] = Constants.REPLACE_ITEM;
            } else if (Constants.ADMIN_ITEMS.contains(items[i].getType()) || items[i].getType().name().endsWith("_SPAWN_EGG")) {
                items[i] = Constants.REPLACE_ITEM;
            } else {
                ItemMeta meta = items[i].getItemMeta();

                if (items[i].getType().name().endsWith("POTION")) {
                    if (((PotionMeta) meta).getBasePotionData().getType().equals(PotionType.UNCRAFTABLE)) {
                        items[i] = Constants.REPLACE_ITEM;
                        continue;
                    }
                }

                items[i].setAmount(Math.min(items[i].getAmount(), items[i].getMaxStackSize()));
                boolean isAxe = items[i].getType().name().endsWith("_AXE");
                int currentIndex = i;

                meta.getEnchants().forEach((enchantment, level) -> {
                    if (level < enchantment.getStartLevel() || (!enchantment.getItemTarget().includes(items[currentIndex].getType()) && !(isAxe && enchantment.equals(Enchantment.DAMAGE_ALL) && !enchantment.equals(Enchantment.THORNS)))) {
                        meta.removeEnchant(enchantment);
                    } else if (level > enchantment.getMaxLevel()) {
                        meta.removeEnchant(enchantment);
                        meta.addEnchant(enchantment, enchantment.getMaxLevel(), true);
                    }
                });

                meta.setAttributeModifiers(null);
                meta.setDisplayName(ChatColor.stripColor(meta.getDisplayName()));

                items[i].setItemMeta(meta);
            }
        }

        return items;
    }
}