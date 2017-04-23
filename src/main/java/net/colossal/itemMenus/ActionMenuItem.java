package net.colossal.itemMenus;

import org.bukkit.inventory.ItemStack;

public class ActionMenuItem extends MenuItem {
    private ItemClickHandler handler;

    public ActionMenuItem(final String displayName, final ItemClickHandler handler, final ItemStack icon,
                          final String... lore) {
        super(displayName, icon, lore);
        this.handler = handler;
    }

    @Override
    public void onItemClick(final ItemClickEvent event) {
        this.handler.onItemClick(event);
    }

    public ItemClickHandler getHandler() {
        return this.handler;
    }

    public void setHandler(final ItemClickHandler handler) {
        this.handler = handler;
    }
}
