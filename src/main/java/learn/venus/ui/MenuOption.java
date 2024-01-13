package learn.venus.ui;

public enum MenuOption {
    EXIT("Exit"),
    DISPLAY_ORBITERS("Display Orbiters"),
    CREATE_ORBITERS("Create Orbiters"),
    UPDATE_ORBITERS("Update Orbiters"),
    DELETE_ORBITERS("Delete Orbiters");

    private final String title;



    MenuOption(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
