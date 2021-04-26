package tk.booky.antiadminitems.listener;
// Created by booky10 in AntiAdminItems (10:38 16.03.21)

import org.bukkit.Material;
import org.bukkit.block.Container;
import org.bukkit.block.ShulkerBox;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import tk.booky.antiadminitems.utils.Constants;
import tk.booky.antiadminitems.utils.ItemProcessor;

public class BlockListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (event.getPlayer().hasPermission(Constants.BYPASS_PERMISSION)) return;
        if (event.getBlock().getState() instanceof Container) ((Container) event.getBlock().getState()).getInventory().setContents(ItemProcessor.processItems(((Container) event.getBlock().getState()).getInventory().getContents()));
        if (!Constants.ADMIN_ITEMS.contains(event.getBlock().getType()) || event.getBlock().getType().equals(Material.SPAWNER)) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (event.getPlayer().hasPermission(Constants.BYPASS_PERMISSION)) return;

        if (!Constants.ADMIN_ITEMS.contains(event.getBlock().getType())) {
            if (!(event.getBlock().getState() instanceof ShulkerBox)) return;
            ShulkerBox shulker = (ShulkerBox) event.getBlock().getState();
            shulker.getInventory().setContents(ItemProcessor.processItems(shulker.getInventory().getContents(), true));
        } else {
            if (event.getItemInHand().getType().equals(Material.ENDER_EYE) && event.getBlock().getType().equals(Material.END_PORTAL_FRAME)) return;
            event.setCancelled(true);
        }
    }
}