package by.bsuir.exam.controller.page;

public enum PageName {
    WRONG_REQUEST(""),
    MAIN("/exam/"),
    TABLE(MAIN.getUrlPattern() + "Table"),

    private final String urlPattern;

    PageName(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public static PageName fromURI(String uri) {
        for (PageName mapping : values()) {
            if (mapping.getUrlPattern().equals(uri)) {
                return mapping;
            }
        }

        return null;
    }
}