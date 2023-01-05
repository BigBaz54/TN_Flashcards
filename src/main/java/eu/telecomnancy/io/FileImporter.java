package eu.telecomnancy.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileImporter {
    public File importFromUrl(URL url) throws URISyntaxException, IOException {
        return checkURL(url) ? importFromFile(Paths.get(url.toURI()).toFile()) : null;
    }

    public File importFromFile(File file) throws IOException {
        File destDir = new File("resources");
        File deck = null;

        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(file));
        ZipEntry zipEntry = zis.getNextEntry();

        while (zipEntry != null) {
            File newFile = newFile(destDir, zipEntry);

            // Remove the files added in zip by MAC OS
            if (".DS_Store".equals(newFile.getName()) || "__MACOSX".equals(newFile.getName())
                    || "._.DS_Store".equals(newFile.getName())) {
                zipEntry = zis.getNextEntry();
                continue;
            }

            if (zipEntry.isDirectory()) {
                if (!newFile.isDirectory() && !newFile.mkdirs()) {
                    zis.closeEntry();
                    zis.close();
                    throw new RuntimeException("Failed to create directory " + newFile);
                }
                zipEntry = zis.getNextEntry();
                continue;
            }

            if (!checkFileSubDir(zipEntry)) {
                zis.closeEntry();
                zis.close();
                throw new RuntimeException("Invalid entry");
            }

            if (zipEntry.getName().endsWith(".json")) {
                deck = newFile;
            }

            FileOutputStream fos = new FileOutputStream(newFile);

            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }

            fos.close();
            zipEntry = zis.getNextEntry();
        }

        zis.closeEntry();
        zis.close();

        if (deck == null) {
            throw new RuntimeException("No deck found");
        }

        return deck;
    }

    private File newFile(File destDir, ZipEntry entry) {
        File destFile = new File(destDir, entry.getName());

        String destDirPath = destDir.getAbsoluteFile().getPath();
        String destFilePath = destFile.getAbsoluteFile().getPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new RuntimeException("Entry is outside of the target dir: " + entry.getName());
        }

        return destFile;
    }

    private boolean checkFileSubDir(ZipEntry entry) {
        File file = new File(entry.getName());
        File parent = file.getParentFile();
        System.out.println(entry.getName());

        List<String> validSubDir = Arrays.asList("decks", "exports", "images", "sounds", "videos", "test");

        return parent != null ? validSubDir.contains(parent.getName()) : false;
    }

    private boolean checkURL(URL url) {
        // Check if the url is a zip file and if it is a valid url
        // Valid url comes from following sites: google drive, dropbox, github
        List<String> validUrl = Arrays.asList("drive.google.com", "dropbox.com", "github.com");

        if (url == null) {
            return false;
        }

        String host = url.getHost();

        if (host == null) {
            return false;
        }

        return validUrl.contains(host);
    }
}
