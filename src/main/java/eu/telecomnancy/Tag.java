package eu.telecomnancy;

import java.util.ArrayList;
import java.util.List;

public class Tag {
    private static final List<Tag> VALUES = new ArrayList<>();

    private final String name;

    private Tag(String name) {
        this.name = name;
        VALUES.add(this);
    }

    public static Tag createTag(String name) {
        return new Tag(name);
    }

    public String getName() {
        return name;
    }

    public static Tag[] values() {
        return VALUES.toArray(new Tag[0]);
    }

    public static Tag valueOf(String name) {
        for (Tag t : VALUES) {
            if (t.getName().equals(name)) {
                return t;
            }
        }
        throw new IllegalArgumentException("No such tag: " + name);
    }
}