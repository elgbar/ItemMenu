package net.colossal.itemMenus;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * A {@link net.colossal.itemMenus.StaticMenuItem} that opens the
 * {@link net.colossal.itemMenus.ItemMenu}'s parent menu if it exists.
 */
public class BackMenuItem extends StaticMenuItem {

    public BackMenuItem() {
        super(ChatColor.RED + "Back", new ItemStack(Material.FENCE_GATE));
    }

    @Override
    public void onItemClick(final ItemClickEvent event) {
        event.setWillGoBack(true);
    }
}
