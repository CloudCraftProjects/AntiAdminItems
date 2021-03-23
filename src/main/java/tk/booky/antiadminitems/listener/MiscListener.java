package tk.booky.antiadminitems.listener;
// Created by booky10 in AntiAdminItems (10:38 16.03.21)

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
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

        ItemStack replaced = ItemProcessor.processItem(event.getItem().getItemStack());
        if (replaced == Constants.REPLACE_ITEM) {
            event.setCancelled(true);
            event.getItem().remove();
        } else {
            event.getItem().setItemStack(replaced);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked().hasPermission(Constants.BYPASS_PERMISSION) || event.getInventory().getSize() == 45 || Constants.EXCLUDED_INVENTORIES.contains(event.getView().getType())) return;

        ItemStack replaced = ItemProcessor.processItem(event.getCurrentItem());
        if (replaced == Constants.REPLACE_ITEM) {
            event.setCurrentItem(Constants.REPLACE_ITEM);
            event.setCancelled(true);
        } else {
            event.setCurrentItem(replaced);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!event.getEntity().isInvulnerable()) return;

        event.setCancelled(true);
        event.setDamage(0);
    }
}