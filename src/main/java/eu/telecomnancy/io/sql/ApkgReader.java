package eu.telecomnancy.io.sql;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

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

    public ArrayList<String[]> getNotes() throws SQLException {
        ArrayList<String[]> notes = new ArrayList<String[]>();

        if (isAnki2()) {
            anki2ToDb();
        }

        connect(anki2.getPath());
        if (conn != null) {
            String query = "SELECT * FROM notes";
            ResultSet rs = conn.createStatement().executeQuery(query);
            while (rs.next()) {
                String flds = rs.getString("flds");
                String[] note = new String[2];
                note[0] = flds.split("\u001f")[0];
                note[1] = flds.split("\u001f")[1];
                notes.add(note);
            }

            conn.close();
        }

        dbToAnki2();

        return notes;
    }

    private void connect(String fileName) throws SQLException {
        String url = "jdbc:sqlite:" + fileName;

        conn = DriverManager.getConnection(url);
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

    public void extractAnki2() throws IOException {
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
