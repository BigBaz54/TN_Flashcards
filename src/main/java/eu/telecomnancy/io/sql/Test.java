package eu.telecomnancy.io.sql;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        File file = new File("resources/decks/Anki.apkg");

        ApkgReader apkgReader = new ApkgReader(file);
        try {
            ArrayList<String[]> arr = apkgReader.getNotes();
            System.out.println(arr.get(0)[0]);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
