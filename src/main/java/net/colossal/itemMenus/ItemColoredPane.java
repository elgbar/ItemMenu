package net.colossal.itemMenus;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * @author kh498
 * @since 0.1.0
 */
public final class ItemColoredPane extends ActionMenuItem {


    /**
     * @param name    Name of the item
     * @param handler What the items does when you click on it
     * @param color   Color of the item
     * @param lore    Lore of the item
     */
    public ItemColoredPane(final String name, final DyeColor color, final ItemClickHandler handler,
                           final String... lore) {
        super(name, handler, new ItemStack(Material.STAINED_GLASS_PANE, 1, color.getData()), lore);
    }

    /**
     * Get a colored pane MenuItem
     *
     * @param name  Name of the item
     * @param color Color of the item
     * @param lore  Lore of the item
     */
    public ItemColoredPane(final String name, final DyeColor color, final String... lore) {
        this(name, color, event -> {}, lore);
    }

    /**
     * Get a colored pane MenuItem
     *
     * @param name  Name of the item
     * @param color Color of the item
     */
    public ItemColoredPane(final String name, final DyeColor color) {
        this(name, color, event -> {}, "");
    }

    /**
     * Create a filler colored pane that has only a space as name and no lore
     *
     * @param color Color of the item
     */
    public ItemColoredPane(final DyeColor color) {
        this(" ", color, "");
    }
}
