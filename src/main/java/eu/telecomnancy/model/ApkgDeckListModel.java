package eu.telecomnancy.model;

import java.util.ArrayList;

import eu.telecomnancy.model.compact.ApkgDeckModel;

public class ApkgDeckListModel {
    private ArrayList<ApkgDeckModel> decks;

    public ApkgDeckListModel() {
        this.decks = new ArrayList<ApkgDeckModel>();
    }

    public ArrayList<ApkgDeckModel> getDecks() {
        return decks;
    }

    public void setDecks(ArrayList<ApkgDeckModel> decks) {
        this.decks = decks;
    }

    public void add(ApkgDeckModel deck) {
        this.decks.add(deck);
    }
}
