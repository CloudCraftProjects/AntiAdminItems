package tk.booky.antiadminitems.listener;
// Created by booky10 in AntiAdminItems (10:38 16.03.21)

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import tk.booky.antiadminitems.utils.Constants;
import tk.booky.antiadminitems.utils.ItemProcessor;

import static tk.booky.antiadminitems.utils.Constants.BYPASS_PERMISSION;
import static tk.booky.antiadminitems.utils.Constants.REPLACE_ITEM;
import static tk.booky.antiadminitems.utils.ItemProcessor.processItem;
import static tk.booky.antiadminitems.utils.ItemProcessor.processItems;

public class MiscListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (event.getPlayer().hasPermission(BYPASS_PERMISSION)) {
            boolean creative = event.getPlayer().getGameMode().equals(GameMode.CREATIVE);
            PlayerInventory inventory = event.getPlayer().getInventory();
            Inventory enderchest = event.getPlayer().getEnderChest();

            inventory.setContents(processItems(inventory.getContents(), creative));
            enderchest.setContents(processItems(enderchest.getContents(), creative));
        }
    }

    @EventHandler
    public void onPickup(PlayerAttemptPickupItemEvent event) {
        if (!event.getPlayer().hasPermission(BYPASS_PERMISSION)) {
            boolean creative = event.getPlayer().getGameMode().equals(GameMode.CREATIVE);
            ItemStack replaced = processItem(event.getItem().getItemStack(), creative);

            if (replaced == REPLACE_ITEM) {
                event.getItem().setItemStack(replaced);
                event.setCancelled(true);
            } else if (replaced != event.getItem().getItemStack()) {
                event.getItem().setItemStack(replaced);
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getWhoClicked().hasPermission(BYPASS_PERMISSION) && event.getCurrentItem() != null) {
            Inventory inventory = event.getInventory();
            if (inventory.getSize() != 45 || inventory.getSize() > 4) {
                boolean creative = event.getWhoClicked().getGameMode().equals(GameMode.CREATIVE);
                ItemStack replaced = processItem(event.getCurrentItem(), creative, inventory.getType().equals(InventoryType.SHULKER_BOX));

                if (replaced == REPLACE_ITEM) {
                    event.setCurrentItem(replaced);
                    event.setCancelled(true);
                } else if (replaced != event.getCurrentItem()) {
                    event.setCurrentItem(replaced);
                }
            }
        }
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        if (!event.getPlayer().hasPermission(BYPASS_PERMISSION)) {
            Inventory inventory = event.getInventory();
            if (inventory.getSize() != 45 || inventory.getSize() > 4) {
                boolean creative = event.getPlayer().getGameMode().equals(GameMode.CREATIVE);
                inventory.setContents(processItems(inventory.getContents(), creative, inventory.getType().equals(InventoryType.SHULKER_BOX)));
            }
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        if (event.getEntity().isInvulnerable()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity().isInvulnerable()) {
            event.setCancelled(true);
            event.setDamage(0);
        }
    }

    @EventHandler
    public void onCreative(InventoryCreativeEvent event) {
        if (!event.getWhoClicked().hasPermission(BYPASS_PERMISSION)) {
            if (!event.getCursor().getType().isAir()) {
                ItemStack replaced = ItemProcessor.processItem(event.getCursor(), true);
                if (replaced == Constants.REPLACE_ITEM) {
                    event.setCancelled(true);
                } else if (replaced != event.getCursor()) {
                    event.setCurrentItem(replaced);
                }
            }
        }
    }
}
