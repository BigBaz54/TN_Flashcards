package eu.telecomnancy.io;

import java.io.File;
import eu.telecomnancy.controller.DeckListController;
import eu.telecomnancy.io.json.JsonFormatterDeck;
import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.model.Media;

public class FileController {
    private FileLoader fileLoader;
    private FileSaver fileSaver;
    private FileWriter fileWriter;
    private FileImporter fileImporter;
    private FileExporter fileExporter;

    public FileController() {
        fileWriter = new FileWriter();
        fileLoader = new FileLoader(new FileReader<DeckModel>(new JsonFormatterDeck()));
        fileSaver = new FileSaver(fileWriter);
        fileImporter = new FileImporter();
        fileExporter = new FileExporter();
    }

    public void setDeckListController(DeckListController deckListController) {
        fileLoader.setDeckListController(deckListController);
    }

    public void loadDecks() {
        fileLoader.loadDecks();
    }

    public void saveDeck(DeckModel deckModel) {
        try {
            fileSaver.saveDeck(deckModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveMedia(Media media) {
        try {
            fileWriter.writeMedia(media);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void importDeck(File file) {
        FileWriter.createDir();
        try {
            fileImporter.imports(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportDeck(DeckModel deckModel) {
        try {
            fileExporter.export(deckModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
