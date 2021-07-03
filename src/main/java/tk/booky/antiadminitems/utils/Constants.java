package tk.booky.antiadminitems.utils;
// Created by booky10 in AntiAdminItems (10:21 16.03.21)

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public final class Constants {

    public static final List<Material> ADMIN_ITEMS = Arrays.asList(Material.COMMAND_BLOCK, Material.COMMAND_BLOCK_MINECART, Material.SPAWNER, Material.CHAIN_COMMAND_BLOCK, Material.REPEATING_COMMAND_BLOCK, Material.BARRIER, Material.JIGSAW, Material.STRUCTURE_BLOCK, Material.STRUCTURE_VOID, Material.BEDROCK, Material.END_PORTAL_FRAME);
    public static final List<InventoryType> EXCLUDED_INVENTORIES = Arrays.asList(InventoryType.ANVIL, InventoryType.SMITHING, InventoryType.ENCHANTING, InventoryType.GRINDSTONE, InventoryType.LOOM);
    public static final ItemStack REPLACE_ITEM = new ItemStack(Material.AIR);
    public static final String BYPASS_PERMISSION = "adminitems.use";
}