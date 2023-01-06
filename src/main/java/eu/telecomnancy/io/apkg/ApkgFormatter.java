package eu.telecomnancy.io.apkg;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import eu.telecomnancy.io.json.JsonApkgFormatter;
import eu.telecomnancy.model.ApkgDeckListModel;
import eu.telecomnancy.model.compact.ApkgDeckModel;

public class ApkgFormatter {
    private ApkgReader apkgReader;
    private JsonApkgFormatter jsonApkgFormatter;

    public ApkgFormatter(ApkgReader apkgReader) {
        this.apkgReader = apkgReader;
        this.jsonApkgFormatter = new JsonApkgFormatter(new ApkgDeckListModel());
    }

    public ApkgFormatter(File file) {
        this.apkgReader = new ApkgReader(file);
        this.jsonApkgFormatter = new JsonApkgFormatter(new ApkgDeckListModel());
    }

    public void setApkgFile(File file) {
        apkgReader.setApkgFile(file);
    }

    public ApkgDeckListModel getAllDecks() throws SQLException, IOException {
        ApkgDeckListModel decks = new ApkgDeckListModel();
        String json = apkgReader.getDecks();

        jsonApkgFormatter.fromJson(json, decks);

        for (ApkgDeckModel deck : decks.getDecks()) {
            deck.setNotes(apkgReader.getNotes(deck.getId()));
        }

        return decks;
    }
}
