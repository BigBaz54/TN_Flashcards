package eu.telecomnancy.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import eu.telecomnancy.model.CardModel;
import eu.telecomnancy.model.DeckModel;

public class FileExporter {
    /*
     * Export a deck to a zip file containing the json file and the images, sounds
     * or videos
     * Assess that the deck as already been saved
     */
    public void export(DeckModel deckModel, File export) throws IOException {
        ArrayList<File> files = new ArrayList<File>();
        files.add(new File("resources/decks/" + deckModel.getName() + ".json"));

        if (!files.get(0).exists()) {
            throw new RuntimeException("Deck has not been saved");
        }

        for (CardModel card : deckModel.getCards()) {
            if (card.getMedia() != null) {
                files.add(card.getMedia().getFile());
            }
        }

        FileOutputStream stream = new FileOutputStream(export);
        ZipOutputStream zipOutputStream = new ZipOutputStream(stream);

        for (File file : files) {
            FileInputStream inStream = new FileInputStream(file);
            ZipEntry zipEntry = new ZipEntry(file.getParentFile().getName() + "/" + file.getName());
            zipOutputStream.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];
            int length;
            while ((length = inStream.read(bytes)) >= 0) {
                zipOutputStream.write(bytes, 0, length);
            }
            inStream.close();
        }

        zipOutputStream.close();
        stream.close();
    }
}
