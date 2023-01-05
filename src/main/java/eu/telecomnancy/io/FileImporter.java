package eu.telecomnancy.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileImporter {
    public void imports(File file) throws IOException {
        File destDir = new File("resources");

        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(file));
        ZipEntry zipEntry = zis.getNextEntry();

        while (zipEntry != null) {
            File newFile = newFile(destDir, zipEntry);

            if (!checkFileSubDir(zipEntry)) {
                zis.closeEntry();
                zis.close();
                throw new RuntimeException("Invalid entry");
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

        List<String> validSubDir = Arrays.asList("decks", "exports", "images", "sounds", "videos");

        return validSubDir.contains(file.getParentFile().getName());
    }
}
