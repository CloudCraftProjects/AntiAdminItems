package tk.booky.antiadminitems.listener;
// Created by booky10 in AntiAdminItems (10:38 16.03.21)

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Container;
import org.bukkit.block.ShulkerBox;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import static tk.booky.antiadminitems.utils.AdminItems.preventSurvivalUsage;
import static tk.booky.antiadminitems.utils.Constants.BYPASS_PERMISSION;

public class BlockListener implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (!event.getPlayer().hasPermission(BYPASS_PERMISSION)) {
            if (preventSurvivalUsage(event.getBlock().getType())) {
                event.setCancelled(true);
            } else if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                if (event.getBlock().getState() instanceof Container) {
                    event.setDropItems(false);
                }
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (!event.getPlayer().hasPermission(BYPASS_PERMISSION)) {
            if (event.getItemInHand().getType().equals(Material.ENDER_EYE)) {
                Material material = event.getBlock().getType();
                if (preventSurvivalUsage(material)) {
                    event.setCancelled(true);
                } else if (event.getBlock().getState() instanceof Container) {
                    if (!Tag.SHULKER_BOXES.isTagged(material)) {
                        ((ShulkerBox) event.getBlock().getState()).getInventory().clear();
                    }
                }
            }
        }
    }
}
