package ch.supsi.webapp.web.model;

public enum Type {
    TASK("Task"), STORY("Story"), ISSUE("Issue"), BUG("Bug"), INVESTIGATION("Investigation");

    private final String name;

    Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
