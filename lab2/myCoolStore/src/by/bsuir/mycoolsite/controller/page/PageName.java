package by.bsuir.mycoolsite.controller.page;

/**
 * Enum representing different page names and their corresponding URL patterns.
 */
public enum PageName {
    WRONG_REQUEST(""),
    MAIN("/myCoolStore/"),
    CART(MAIN.getUrlPattern() + "Cart"),
    FILM(MAIN.getUrlPattern() + "Film"),
    LIBRARY(MAIN.getUrlPattern() + "Library"),
    ADD_FILM(MAIN.getUrlPattern() + "Admin/Film"),
    BAN_LIST(MAIN.getUrlPattern() + "Admin/BanList");

    private final String urlPattern;

    /**
     * Constructor for PageName enum.
     *
     * @param urlPattern String representing the URL pattern for the page
     */
    PageName(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    /**
     * Gets the URL pattern for the page.
     *
     * @return String representing the URL pattern
     */
    public String getUrlPattern() {
        return urlPattern;
    }

    /**
     * Converts a URI to a corresponding PageName enum value.
     *
     * @param uri String representing the URI
     * @return PageName enum value corresponding to the URI, or null if not found
     */
    public static PageName fromURI(String uri) {
        for (PageName mapping : values()) {
            if (mapping.getUrlPattern().equals(uri)) {
                return mapping;
            }
        }

        return null;
    }
}
