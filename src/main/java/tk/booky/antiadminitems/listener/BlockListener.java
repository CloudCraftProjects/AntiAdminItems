package tk.booky.antiadminitems.listener;
// Created by booky10 in AntiAdminItems (10:38 16.03.21)

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import tk.booky.antiadminitems.utils.Constants;

public class BlockListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (event.getPlayer().hasPermission(Constants.BYPASS_PERMISSION) || !Constants.ADMIN_ITEMS.contains(event.getBlock().getType()) || event.getBlock().getType().equals(Material.SPAWNER)) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (event.getPlayer().hasPermission(Constants.BYPASS_PERMISSION) || !Constants.ADMIN_ITEMS.contains(event.getBlock().getType())) return;
        event.setCancelled(true);
    }
}