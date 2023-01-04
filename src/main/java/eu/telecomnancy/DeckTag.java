package eu.telecomnancy;

import java.util.ArrayList;

public class DeckTag extends Tag {
    private static ArrayList<String> listUsed = new ArrayList<String>();

    public DeckTag(String name) {
        super(name);
        if (!listUsed.contains(name)) {
            listUsed.add(name);
        }
    }

    public static ArrayList<String> getListUsed() {
        return listUsed;
    }
}
