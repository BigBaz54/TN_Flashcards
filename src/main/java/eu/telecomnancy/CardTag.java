package eu.telecomnancy;

import java.util.ArrayList;

public class CardTag extends Tag {
    private static ArrayList<String> listUsed = new ArrayList<String>();
    
    public CardTag(String name) {
        if (!listUsed.contains(name)) {
            listUsed.add(name);
        }
        this.name = name;
    }

    public static ArrayList<String> getListUsed() {
        return listUsed;
    }
}
