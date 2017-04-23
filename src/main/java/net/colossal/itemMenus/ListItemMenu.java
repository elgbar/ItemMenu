package net.colossal.itemMenus;

import com.google.common.base.Preconditions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/**
 * View a {@link ArrayList
 * ArrayList}{@code <}{@link net.colossal.itemMenus.MenuItem MenuItem}{@code >} using the
 * {@link ItemMenu ItemMenu} system.
 * <p>
 * If there are more than 45 (9 items in each row * 5 columns) items then you can scroll between
 * pages.
 * <p>
 * <b>Note:</b> You need JVM 1.8 or higher to run this due to lambda expressions.
 *
 * @author kh498
 * @since 0.1.0
 */
public class ListItemMenu {

    /* constants */
    private static final int FIVE_LINES_SIZE = Size.FIVE_LINES.getSize();
    public static final int GOLD_INDEX = CommonPositions.MIDDLE.getPosition(Size.FIVE_LINES);
    /* set by the user */
    private final ArrayList<MenuItem> menuItemList;
    private final String title;

    /* generated */
    private final int pages;
    private final ItemMenu[] cache;
    private boolean showGold;

    public ListItemMenu(final ArrayList<MenuItem> menuItemList, final boolean showGold, final String title) {
        Preconditions.checkArgument(title.length() <= 22, "Title cannot be longer than 22 characters");

        this.menuItemList = menuItemList;
        this.showGold = showGold;
        this.title = title;
        this.pages = dividedRoundedUp(menuItemList.size(), FIVE_LINES_SIZE);
        this.cache = new ItemMenu[this.pages];
    }

    public void openMenu(final Player player) {
        openMenu(player, 0);
    }

    private void openMenu(final Player player, final int page) {
        final ItemMenu menu;
        if (this.cache[page] != null) {
            menu = this.cache[page];
        }
        else {
            menu = new ItemMenu(this.title + " - " + (page + 1) + "/" + this.pages, Size.SIX_LINES);

            //The panes at the bottom of the menu,
            final MenuItem bottomPane = new ItemColoredPane(DyeColor.BLACK);
            for (int m = 1; m < 8; m++) {
                menu.setItem(FIVE_LINES_SIZE + m, bottomPane);
            }

            int index = FIVE_LINES_SIZE * page;
            for (int l = 0; l < FIVE_LINES_SIZE; l++, index++) {
                if (this.menuItemList.size() > index) {
                    menu.setItem(l, this.menuItemList.get(index));
                }
            }
            this.cache[page] = menu.copy();
        }

        menu.setItem(CommonPositions.LEFT, Size.FIVE_LINES, prevItem(player, page));
        menu.setItem(CommonPositions.RIGHT, Size.FIVE_LINES, nextItem(player, page));

        final ItemMenu finalMenu = menu;
        Bukkit.getScheduler().scheduleSyncDelayedTask(ItemMenuListener.getPlugin(), () -> finalMenu.open(player),
                                                      2L); // wait 2 ticks before opening the menu
    }

    private MenuItem nextItem(final Player player, final int page) {
        return (new ActionMenuItem(ChatColor.WHITE + "Next page", event -> {
            final int newPage = page + 1;
            if (newPage < ListItemMenu.this.pages) {
                event.setWillClose(true);
                openMenu(player, (newPage >= ListItemMenu.this.pages) ? ListItemMenu.this.pages - 1 : newPage);
            }
        }, new ItemStack(Material.SLIME_BALL)));
    }

    private MenuItem prevItem(final Player player, final int page) {
        return (new ActionMenuItem(ChatColor.WHITE + "Previous page", event -> {
            final int newPage = page - 1;
            if (newPage >= 0) {
                event.setWillClose(true);
                openMenu(player, newPage);
            }
        }, new ItemStack(Material.MAGMA_CREAM)));
    }

    /**
     * @param i first number
     * @param j second number
     *
     * @return If any parameter is less than 1 returns 1. If not it returns the rounded number up how
     * many times j fit in i
     */
    public static int dividedRoundedUp(final int i, final int j) {
        if (i <= 0 || j <= 0) {
            return 1;
        }

        final int modular = i % j;
        final int fit;
        if (modular == 0) {
            fit = i / j;
        }
        else {
            fit = 1 + ((i - modular) / j);
        }
        return fit;
    }

    /**
     * @return the List of menuItem that will be displayed
     */
    public final ArrayList<MenuItem> getMenuItemList() {
        return this.menuItemList;
    }

    /**
     * @return the showGold
     */
    public final boolean isShowingGold() {
        return this.showGold;
    }

    /**
     * @param showGold the showGold to set
     */
    public final void setShowGold(final boolean showGold) {
        this.showGold = showGold;
    }

    /**
     * @return the title
     */
    public final String getTitle() {
        return this.title;
    }

    /**
     * @return the pages
     */
    public final int getPages() {
        return this.pages;
    }

    @Override
    public String toString() {
        return "ListItemMenu{" + "showGold=" + this.showGold + ", title='" + this.title + '\'' + ", pages=" +
               this.pages + '}';
    }
}
