package eu.telecomnancy.io.sql;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        File file = new File("resources/decks/Anki.apkg");

        ApkgReader apkgReader = new ApkgReader(file);
        try {
            apkgReader.getNotes();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
