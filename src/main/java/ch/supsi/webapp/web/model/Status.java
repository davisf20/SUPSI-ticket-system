package ch.supsi.webapp.web.model;

public enum Status {
    OPEN("Open"), IN_PROGRESS("In progress"), DONE("Done"), CLOSED("Closed");

    private final String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
