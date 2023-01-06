package eu.telecomnancy.io.apkg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import eu.telecomnancy.io.file.FileWriter;
import eu.telecomnancy.io.json.JsonApkgMediaFormatter;
import eu.telecomnancy.model.ApkgMedia;
import eu.telecomnancy.model.ApkgNote;
import eu.telecomnancy.model.Media;
import eu.telecomnancy.model.MediaType;

public class ApkgReader {
    private Connection conn = null;
    private File apkg;
    private File anki2;
    private File media;
    private ArrayList<File> files;
    private JsonApkgMediaFormatter jsonApkgMediaFormatter;

    public ApkgReader(File file) {
        // Check if the file is an apkg file or a zip file
        if (!file.getName().endsWith(".apkg") && !file.getName().endsWith(".zip")) {
            throw new RuntimeException("File is not an apkg or zip file");
        }

        this.apkg = file;
        this.files = new ArrayList<File>();
        this.jsonApkgMediaFormatter = new JsonApkgMediaFormatter(new ApkgMedia());
    }

    public void setApkgFile(File apkgFile) {
        this.apkg = apkgFile;
    }

    public ArrayList<ApkgNote> getNotes(Long deckId) throws SQLException, IOException {
        ArrayList<ApkgNote> notes = new ArrayList<ApkgNote>();
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

    private ApkgNote parseNotes(String notes) {
        String[] notesArray = notes.split("\u001f");
        ApkgNote note;
        // Check if question contains an image tag
        if (notesArray[0].contains("<img src=")) {
            String[] img = notesArray[0].split("<img src=");
            int index = img[1].indexOf(">");
            String imgName = img[1].substring(1, index - 3);

            // Remove trailing \
            if (imgName.contains("\\")) {
                imgName = imgName.substring(0, imgName.length() - 1);
            }

            System.out.println("Image name: " + imgName);
            String next = img[1].substring(index);
            notesArray[0] = img[0] + " ... " + next;
            note = new ApkgNote(removeHTMLTags(notesArray[0]), removeHTMLTags(notesArray[1]),
                    new Media(imgName, MediaType.IMG));
        } else
            note = new ApkgNote(removeHTMLTags(notesArray[0]), removeHTMLTags(notesArray[1]));

        return note;
    }

    private String removeHTMLTags(String text) {
        return text.replaceAll("\\<.*?\\>", "");
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

        return removeHTMLTags(decks);
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
            } else {
                String path = "resources/temp/" + entry.getName();
                if (entry.getName().equals("media"))
                    path += ".json";

                File file = new File(path);

                if (file.getName().equals("media.json"))
                    media = file;
                else
                    files.add(file);

                FileOutputStream fos = new FileOutputStream(file);

                int len;
                while ((len = zis.read(buffer)) > 0)
                    fos.write(buffer, 0, len);

                fos.close();
            }

            entry = zis.getNextEntry();
        }

        zis.closeEntry();
        zis.close();

        extractImages();

        zipToApkg();
    }

    private String getMediaContent() {
        String content = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(media));
            String line;

            while ((line = br.readLine()) != null) {
                content += line;
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    private void extractImages() {
        ApkgMedia media = new ApkgMedia();
        jsonApkgMediaFormatter.fromJson(getMediaContent(), media);

        for (File file : files) {
            String fileName = file.getName();
            Integer key = Integer.parseInt(fileName);

            String newName = media.getPath(key);
            String newPath = "resources/images/" + newName;

            FileWriter.writeFile(file, newPath);
            file.delete();
        }

        this.media.delete();
        media = null;
        files = new ArrayList<>();
    }
}
