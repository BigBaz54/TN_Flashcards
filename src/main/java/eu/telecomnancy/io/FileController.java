package eu.telecomnancy.io;

import java.io.File;
import eu.telecomnancy.controller.DeckListController;
import eu.telecomnancy.model.DeckModel;
import eu.telecomnancy.model.Media;

public class FileController {
    private FileLoader fileLoader;
    private FileSaver fileSaver;
    private FileWriter fileWriter;
    private FileImporter fileImporter;
    private FileExporter fileExporter;

    public FileController(FileReader<DeckModel> fileReader,
            FileWriter fileWriter) {
        this.fileLoader = new FileLoader(fileReader);
        this.fileSaver = new FileSaver(fileWriter);
        this.fileWriter = fileWriter;
        this.fileImporter = new FileImporter();
        this.fileExporter = new FileExporter();
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
