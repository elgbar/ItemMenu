package net.colossal.itemMenus;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 * Allows you to set the {@link net.colossal.itemMenus.MenuItem} that created the
 * Inventory as the Inventory's holder.
 */
public class ItemMenuHolder implements InventoryHolder {
    private final ItemMenu menu;
    private final Inventory inventory;

    public ItemMenuHolder(final ItemMenu menu, final Inventory inventory) {
        this.menu = menu;
        this.inventory = inventory;
    }

    /**
     * Gets the {@link net.colossal.itemMenus.MenuItem} holding the Inventory.
     *
     * @return The {@link net.colossal.itemMenus.MenuItem} holding the Inventory.
     */
    public ItemMenu getMenu() {
        return this.menu;
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }
}
