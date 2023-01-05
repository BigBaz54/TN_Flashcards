package eu.telecomnancy.io.file;

import java.io.File;
import java.net.URL;

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
    private DeckListController deckListController;

    public FileController() {
        fileWriter = new FileWriter();
        fileLoader = new FileLoader(new FileReader<DeckModel>(new JsonFormatterDeck()));
        fileSaver = new FileSaver(fileWriter);
        fileImporter = new FileImporter();
        fileExporter = new FileExporter();
    }

    public void setDeckListController(DeckListController deckListController) {
        this.deckListController = deckListController;
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

    public void saveDecks() {
        for (DeckModel deckModel : deckListController.getDeckListModel().getDecks()) {
            saveDeck(deckModel);
        }
    }

    public void saveMedia(Media media) {
        try {
            fileWriter.writeMedia(media);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void importFromFile(File file) {
        try {
            File deck = fileImporter.importFromFile(file);
            DeckModel deckModel = deckListController.createEmptyDeck();
            fileLoader.loadDeck(deck, deckModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void importFromUrl(URL url) {
        try {
            File deck = fileImporter.importFromUrl(url);

            if (deck == null) {
                return;
            }

            DeckModel deckModel = deckListController.createEmptyDeck();
            fileLoader.loadDeck(deck, deckModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportDeck(DeckModel deckModel, File expFile) {
        // Save the deck before exporting it
        saveDeck(deckModel);
        try {
            fileExporter.export(deckModel, expFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
