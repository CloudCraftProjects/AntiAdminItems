package tk.booky.antiadminitems.listener;
// Created by booky10 in AntiAdminItems (10:38 16.03.21)

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import tk.booky.antiadminitems.utils.Constants;
import tk.booky.antiadminitems.utils.ItemProcessor;

public class MiscListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (event.getPlayer().hasPermission(Constants.BYPASS_PERMISSION)) return;

        Player player = event.getPlayer();
        player.getInventory().setContents(ItemProcessor.processItems(player.getInventory().getContents()));
        player.getEnderChest().setContents(ItemProcessor.processItems(player.getEnderChest().getContents()));
    }

    @EventHandler
    public void onPickup(PlayerAttemptPickupItemEvent event) {
        if (event.getPlayer().hasPermission(Constants.BYPASS_PERMISSION)) return;
        event.getItem().setItemStack(ItemProcessor.processItem(event.getItem().getItemStack()));
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked().hasPermission(Constants.BYPASS_PERMISSION) || event.getView().getTitle().endsWith("'s Inventory") || Constants.EXCLUDED_INVENTORIES.contains(event.getView().getType())) return;
        HumanEntity player = event.getWhoClicked();

        event.setCurrentItem(ItemProcessor.processItem(event.getCurrentItem()));
        event.getInventory().setContents(ItemProcessor.processItems(event.getInventory().getContents()));
        player.getInventory().setContents(ItemProcessor.processItems(player.getInventory().getContents()));
    }
}