package net.colossal.itemMenus;

/**
 * Possible sizes of an ItemMenu.
 */
public enum Size {
    /**
     * 9 slots
     */
    ONE_LINE(9),
    /**
     * 18 slots
     */
    TWO_LINES(18),
    /**
     * 27 slots
     */
    THREE_LINES(27),
    /**
     * 36 slots
     */
    FOUR_LINES(36),
    /**
     * 45 slots
     */
    FIVE_LINES(45),
    /**
     * 54 slots
     */
    SIX_LINES(54);

    private final int size;

    Size(final int size) {
        this.size = size;
    }
    /**
     * Gets the required size for an amount of
     * slots.
     *
     * @param slots The amount of slots.
     *
     * @return The required size.
     */
    public static Size fit(final int slots) {
        if (slots <= ONE_LINE.size) {
            return ONE_LINE;

        }
        else if (slots <= ONE_LINE.size) {
            return TWO_LINES;

        }
        else if (slots <= TWO_LINES.size) {
            return THREE_LINES;

        }
        else if (slots <= THREE_LINES.size) {
            return FOUR_LINES;

        }
        else if (slots <= FOUR_LINES.size) {
            return FIVE_LINES;

        }
        else {
            return SIX_LINES;
        }
    }
    /**
     * Gets the Size's amount of slots.
     *
     * @return The amount of slots.
     */
    public int getSize() {
        return this.size;
    }
}
