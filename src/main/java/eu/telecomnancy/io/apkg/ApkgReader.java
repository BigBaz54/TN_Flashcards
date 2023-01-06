package eu.telecomnancy.io.apkg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import eu.telecomnancy.model.ApkgDeckListModel;
import eu.telecomnancy.model.DeckModel;

public class ApkgReader {
    Connection conn = null;
    File apkg;
    File anki2;

    public ApkgReader(File file) {
        // Check if the file is an apkg file or a zip file
        if (!file.getName().endsWith(".apkg") && !file.getName().endsWith(".zip")) {
            throw new RuntimeException("File is not an apkg or zip file");
        }

        this.apkg = file;
    }

    public void setApkgFile(File apkgFile) {
        this.apkg = apkgFile;
    }

    public void apkgToDeckModel(DeckModel model) throws SQLException, IOException {
        // TODO: Add the deck to the model
        ArrayList<String[]> notes = new ArrayList<String[]>();
        model.setName(apkg.getName().substring(0, apkg.getName().length() - 5));
        for (String[] note : notes) {
            model.addCard(note[0], note[1]);
        }
    }

    public ArrayList<String[]> getNotes(Long deckId) throws SQLException, IOException {
        ArrayList<String[]> notes = new ArrayList<String[]>();
        HashSet<Long> notesId = new HashSet<Long>();

        // Extract the anki2 file from the apkg file
        extractAnki2();

        if (isAnki2()) {
            anki2ToDb();
        }

        connect(anki2.getPath());

        String query = "SELECT notes.id, flds FROM notes JOIN cards ON notes.id = cards.nid WHERE did = " + deckId;
        ResultSet rs = executeQuery(query);

        if (rs != null) {
            while (rs.next()) {
                Long id = rs.getLong("id");
                if (!notesId.contains(id)) {
                    notesId.add(id);
                    String flds = rs.getString("flds");
                    notes.add(parseNotes(flds));
                }
            }
        }

        close();
        dbToAnki2();

        return notes;
    }

    private ResultSet executeQuery(String query) throws SQLException {
        if (conn != null) {
            ResultSet rs = conn.createStatement().executeQuery(query);

            return rs;
        }

        return null;
    }

    private String[] parseNotes(String notes) {
        String[] notesArray = notes.split("\u001f");
        return notesArray;
    }

    public String getDecks() throws SQLException, IOException {
        String decks = "";

        // Extract the anki2 file from the apkg file
        extractAnki2();

        if (isAnki2()) {
            anki2ToDb();
        }

        connect(anki2.getPath());

        String query = "SELECT * FROM col";
        ResultSet rs = executeQuery(query);

        if (rs != null) {
            while (rs.next())
                decks = rs.getString("decks");
        }

        close();
        dbToAnki2();

        return decks;
    }

    private void connect(String fileName) throws SQLException {
        String url = "jdbc:sqlite:" + fileName;

        conn = DriverManager.getConnection(url);
    }

    private void close() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    private void apkgToZip() {
        String path = apkg.getPath();
        String newPath = path.substring(0, path.length() - 5) + ".zip";
        apkg.renameTo(new File(newPath));
        apkg = new File(newPath);
    }

    private void zipToApkg() {
        String path = apkg.getPath();
        String newPath = path.substring(0, path.length() - 4) + ".apkg";
        apkg.renameTo(new File(newPath));
        apkg = new File(newPath);
    }

    private void anki2ToDb() {
        String path = anki2.getPath();
        String newPath = path.substring(0, path.length() - 6) + ".db";
        anki2.renameTo(new File(newPath));
        anki2 = new File(newPath);
    }

    private void dbToAnki2() {
        String path = anki2.getPath();
        String newPath = path.substring(0, path.length() - 3) + ".anki2";
        anki2.renameTo(new File(newPath));
        anki2 = new File(newPath);
    }

    private boolean isAnki2() {
        return anki2.getName().endsWith(".anki2");
    }

    private boolean isApkg() {
        return apkg.getName().endsWith(".apkg");
    }

    private void extractAnki2() throws IOException {
        if (isApkg()) {
            apkgToZip();
        }

        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(apkg));
        ZipEntry entry = zis.getNextEntry();

        while (entry != null) {
            if (entry.getName().contains(".anki2")) {
                anki2 = new File("resources/apkg/anki2/" + entry.getName());

                FileOutputStream fos = new FileOutputStream(anki2);

                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
                break;
            }

            entry = zis.getNextEntry();
        }

        zis.closeEntry();
        zis.close();

        zipToApkg();
    }
}
