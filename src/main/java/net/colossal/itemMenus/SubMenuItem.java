package net.colossal.itemMenus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * A {@link net.colossal.itemMenus} that opens a sub
 * {@link ItemMenu}.
 */
public class SubMenuItem extends MenuItem {
    private final ItemMenu menu;

    public SubMenuItem(final String displayName, final ItemMenu menu, final ItemStack icon, final String... lore) {
        super(displayName, icon, lore);
        this.menu = menu;
    }

    @Override
    public void onItemClick(final ItemClickEvent event) {
        event.setWillClose(true);
        final UUID ID = event.getPlayer().getUniqueId();
        Bukkit.getScheduler().scheduleSyncDelayedTask(ItemMenuListener.getPlugin(), () -> {
            final Player p = Bukkit.getPlayer(ID);
            if (p != null && SubMenuItem.this.menu != null) {
                SubMenuItem.this.menu.open(p);
            }
        }, 1L);
    }
}
